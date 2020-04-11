package com.sayuri.emanagerapi.service;

import com.sayuri.emanagerapi.model.CustomerCategory;
import com.sayuri.emanagerapi.model.CustomerCategoryPrice;
import com.sayuri.emanagerapi.model.EConsumption;
import com.sayuri.emanagerapi.repository.CustomerCategoryRepo;
import com.sayuri.emanagerapi.repository.EConsumptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class EConsumptionService {

    @Autowired
    private EConsumptionRepo repo;

    @Autowired
    private CustomerCategoryRepo customerCategoryRepo;

    public EConsumption addOrSave(EConsumption eConsumption) {

//        Calendar cal = Calendar.getInstance();
//        cal.setTime(eConsumption.getConsumptionDate());
//        int weekNo = cal.get(Calendar.WEEK_OF_YEAR);
//        eConsumption.setWeekNo(weekNo);

        return repo.save(eConsumption);
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
}
