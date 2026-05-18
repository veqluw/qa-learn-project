package com.automation.demoqa.api.dto;

import com.automation.demoqa.api.model.Isbn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBooksList {

    private String userId;

    private List<Isbn> collectionOfIsbns;

}
