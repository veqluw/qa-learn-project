package com.automation.demoqa.api.model;

import lombok.Data;

import java.time.Instant;

@Data
public class JwtToken {
    private String token;

    private Instant expires;

    private String status;

    private String result;

}
