package com.automation.demoqa.api.data.factory;

import com.automation.demoqa.api.model.Book;
import com.automation.demoqa.api.model.Isbn;
import com.automation.demoqa.api.dto.CreateBooksList;

import java.util.List;

public class BookDataFactory {

    public static CreateBooksList createBooksRequest(String userId, List<Isbn> isbns) {
        CreateBooksList request = new CreateBooksList();
        request.setUserId(userId);
        request.setCollectionOfIsbns(isbns);
        return request;
    }


    public static List<Isbn> defaultIsbns(List<Book> books) {
        return List.of(
                new Isbn(books.get(0).getIsbn()),
                new Isbn(books.get(1).getIsbn())
        );
    }

}
