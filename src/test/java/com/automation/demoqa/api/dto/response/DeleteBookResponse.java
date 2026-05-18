package com.automation.demoqa.api.dto.response;

import lombok.Data;

@Data
public class DeleteBookResponse {
    private String userId;
    private String isbn;
    private String message;
}
