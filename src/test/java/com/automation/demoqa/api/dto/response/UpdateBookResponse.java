package com.automation.demoqa.api.dto.response;

import com.automation.demoqa.api.model.Book;
import lombok.Data;

import java.util.List;

@Data
public class UpdateBookResponse {
    private String userId;
    private String username;
    private List<Book> books;

}
