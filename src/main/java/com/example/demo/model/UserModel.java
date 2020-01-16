package com.example.demo.model;

public class UserModel {

    private String name;

    private String surname;

    private String title;

    private String workplace;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    @Override
    public String toString() {
        return "{ \"name\": \"" + name + "\", \"surname\": \"" + surname + "\", \"title\": \"" + title + "\", \"workplace\": \"" + workplace + "\" }";
    }
}
