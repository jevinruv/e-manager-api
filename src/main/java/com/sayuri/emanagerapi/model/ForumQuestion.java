package com.sayuri.emanagerapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class ForumQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String question;

    @OneToMany(mappedBy = "forumQuestion", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("forumQuestion")
    private List<ForumAnswer> forumAnswers;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", timezone = "Asia/Colombo")
    private Date createdDate;

    public ForumQuestion() {
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<ForumAnswer> getForumAnswers() {
        return forumAnswers;
    }

    public void setForumAnswers(List<ForumAnswer> forumAnswers) {
        this.forumAnswers = forumAnswers;
        this.forumAnswers.forEach(forumAnswer -> forumAnswer.setForumQuestion(this));
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
