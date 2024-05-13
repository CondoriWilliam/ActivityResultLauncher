package com.example.example1;

public class AccountEntity {
    private String username;
    private String password;
    private String phone;
    private String email;
    private String firstname;
    private String lastname;

    public AccountEntity() {
    }
    public AccountEntity(String username, String password, String phone, String email, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
