package com.automation.demoqa.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String zipCode;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
