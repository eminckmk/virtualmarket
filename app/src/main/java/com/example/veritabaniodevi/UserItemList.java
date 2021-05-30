package com.example.veritabaniodevi;

public class UserItemList {

   private String name;
   private String date;
   private String mail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserItemList(String name, String date, String mail) {
        this.name = name;
        this.date = date;
        this.mail = mail;
    }

    public UserItemList() {
    }
}
