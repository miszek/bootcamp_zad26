package com.michalszekalski.bootcampzad26.user;

import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

public class UserRegistrationDto {

    @NotBlank(message = "Field cannot be blank")
    @Email(message = "not properly formatted email")
    private String email;

    @NotBlank(message = "Field cannot be blank")
    @Size(min = 8, message = "password must be at least 8 signs long")
    private String password;

    @NotBlank(message = "Field cannot be blank")
    @Size(min = 3, message = "Field must have at least 3 signs")
    private String firstName;

    @NotBlank(message = "Field cannot be blank")
    @Size(min = 3, message = "Field must have at least 3 signs")
    private String lastName;

    private Set<String> roles = new HashSet<>();

    @NotBlank(message = "Field cannot be blank")
    private String address;

    @NotBlank(message = "Field cannot be blank")
    @Pattern(regexp = "\\d{2}-\\d{3}", message = "must have a format xx-xxx where x is a digit")
    private String zipCode;

    @NotBlank(message = "Field cannot be blank")
    private String city;

    @AssertTrue(message = "regulations consent is obligatory")
    private boolean regulationsConsent;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserRegistrationDto(String email, String password, String firstName, String lastName, Set<String> roles) {
        this(email, password, firstName, lastName);
        this.roles = roles;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isRegulationsConsent() {
        return regulationsConsent;
    }

    public void setRegulationsConsent(boolean regulationsConsent) {
        this.regulationsConsent = regulationsConsent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
