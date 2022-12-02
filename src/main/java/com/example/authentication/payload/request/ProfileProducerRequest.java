package com.example.authentication.payload.request;

public class ProfileProducerRequest {
    private Long id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String email;

    public Long getId() {
        return id;
    }

    public ProfileProducerRequest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public ProfileProducerRequest setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getMiddlename() {
        return middlename;
    }

    public ProfileProducerRequest setMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public ProfileProducerRequest setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileProducerRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}
