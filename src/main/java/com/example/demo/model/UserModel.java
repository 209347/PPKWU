package com.example.demo.model;

public class UserModel {

    private String name;

    private String surname;

    private String title;

    private String workPlace;

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

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    @Override
    public String toString() {
        return "{ \"name\": \"" + name + "\", \"surname\": \"" + surname + "\", \"title\": \"" + title + "\", \"workplace\": \"" + workPlace + "\" }";
    }
}
