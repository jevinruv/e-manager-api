package com.sayuri.emanagerapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class ForumAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String answer;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", timezone = "Asia/Colombo")
    private Date createdDate;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("forumAnswers")
    private ForumQuestion forumQuestion;

    public ForumAnswer() {
        this.createdDate = getDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public ForumQuestion getForumQuestion() {
        return forumQuestion;
    }

    public void setForumQuestion(ForumQuestion forumQuestion) {
        this.forumQuestion = forumQuestion;
    }

    private Date getDate() {

        Date date = new Date();

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//        df.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));

        String strDate = df.format(date);

        Date newDate = null;
        try {
            newDate = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return newDate;
    }

}
