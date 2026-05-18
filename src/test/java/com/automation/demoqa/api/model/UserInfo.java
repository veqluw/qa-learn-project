package com.automation.demoqa.api.model;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {
    private String userID;

    private String username;

    private List<Book> books;
}
