package com.tas.crs.dto;

public class CustomerDto {

    private final String email;
    private final String phone;
    private final String town;
    private final String city;

    public CustomerDto(String email, String phone, String town, String city) {
        this.email = email;
        this.phone = phone;
        this.town = town;
        this.city = city;
    }


    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getTown() {
        return town;
    }

    public String getCity() {
        return city;
    }
}
