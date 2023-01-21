package com.example.carintel.model;

public class User {

    String email, id, idnumber, name, phonenumber, search, type;

    public User(){
    }
    public User(String email, String id, String idnumber, String name, String phonenumber, String search, String type){
        this.email = email;
        this.id = id;
        this.idnumber = idnumber;
        this.name = name;
        this.phonenumber = phonenumber;
        this.search = search;
        this.type = type;

    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getIdnumber() {
        return this.idnumber;
    }

    public void setIdnumber(final String idnumber) {
        this.idnumber = idnumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public void setPhonenumber(final String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSearch() {
        return this.search;
    }

    public void setSearch(final String search) {
        this.search = search;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

}
