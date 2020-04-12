package com.sayuri.emanagerapi.service;

import com.sayuri.emanagerapi.form.ConsumptionEmailForm;
import com.sayuri.emanagerapi.model.CommonValue;
import com.sayuri.emanagerapi.model.CustomerCategory;
import com.sayuri.emanagerapi.model.CustomerCategoryPrice;
import com.sayuri.emanagerapi.model.EConsumption;
import com.sayuri.emanagerapi.repository.CommonValueRepo;
import com.sayuri.emanagerapi.repository.CustomerCategoryRepo;
import com.sayuri.emanagerapi.repository.EConsumptionRepo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class EConsumptionService {

    @Autowired
    private EConsumptionRepo repo;

    @Autowired
    private CustomerCategoryRepo customerCategoryRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    CommonValueRepo commonValueRepo;

    public EConsumption addOrUpdate(EConsumption eConsumption) {

        Optional<EConsumption> consumptionFound = repo.findByConsumptionDate(eConsumption.getConsumptionDate());
        if (eConsumption.getId() == 0 && consumptionFound.isPresent())
            return null;

        EConsumption consumptionSaved = repo.save(eConsumption);

        if(eConsumption.getConsumptionActualCost() > eConsumption.getConsumptionPlannedCost()){
            sendEmail(eConsumption);
        }

        return consumptionSaved;
    }

    public double calculate(int customerCategoryId, double consumptionValue) {

        Optional<CustomerCategory> customerCategoryOptional = customerCategoryRepo.findById(customerCategoryId);

        if (!customerCategoryOptional.isPresent()) return 0;

        CustomerCategory customerCategory = customerCategoryOptional.get();

        //filters to check within ranges used in stream().filter()
        Predicate<CustomerCategoryPrice> isWithinStart = e -> e.getConsumptionRangeStart() <= consumptionValue;
        Predicate<CustomerCategoryPrice> isWithinStop = e -> e.getConsumptionRangeStop() >= consumptionValue;

        // get CustomerCategoryPrice from entered consumptionValue is in define ranges
        Optional<CustomerCategoryPrice> customerCategoryPriceOptional = customerCategory.getCustomerCategoryPrices()
                .stream()
                .filter(isWithinStart.and(isWithinStop))
                .findFirst();

        if (!customerCategoryPriceOptional.isPresent()) return 0;

        CustomerCategoryPrice customerCategoryPrice = customerCategoryPriceOptional.get();

        double energyCharge = customerCategoryPrice.getEnergyCharge();
        double fixedCharge = customerCategoryPrice.getFixedCharge();
        double fuelAdjustmentCharge = customerCategoryPrice.getFuelAdjustmentCharge();
        double total = 0;
        total = fixedCharge + fuelAdjustmentCharge + (energyCharge * consumptionValue);

        return total;
    }

    public void sendEmail(EConsumption eConsumption) {

//        EConsumption eConsumption = consumptionEmailForm.getConsumption();

        CommonValue commonValue = commonValueRepo.findByKey("email-to").get();
        String[] emailTo = commonValue.getValue().split(",");

        String subject = "E-Consumption #" + eConsumption.getId() + " For " + eConsumption.getConsumptionDate();

        double diffCost = eConsumption.getConsumptionActualCost() - eConsumption.getConsumptionPlannedCost();
        double diffUsage = eConsumption.getConsumptionActual() - eConsumption.getConsumptionPlanned();

        String message = "Hi, \n Usage for the following date is higher by " + diffUsage + "kWh. " +
                "Resulting the cost to be high LKR " + diffCost +
                ", where the consumption and cost was planned for " + eConsumption.getConsumptionPlanned() + "kWh & LKR " + eConsumption.getConsumptionPlannedCost() +
                "\n \n Thank You, \n E-Manager";

//        String imageValue = consumptionEmailForm.getReport();

//        sendEmailWithAttachment(emailTo, subject, message, imageValue);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailTo);
        msg.setSubject(subject);
        msg.setText(message);
        javaMailSender.send(msg);
    }

    public void sendEmailWithAttachment(String[] emailTo, String subject, String message, String imageValue) {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try {

            helper = new MimeMessageHelper(msg, true);
            helper.setTo(emailTo);
            helper.setSubject(subject);
            helper.setText(message);
            byte[] imageByte= Base64.decodeBase64(imageValue);
            helper.addAttachment("my_photo.png", new ByteArrayResource(imageByte));

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(msg);
    }

}
