package com.automation.demoqa.api.service;

import com.automation.demoqa.api.config.ApiSpec;
import com.automation.demoqa.api.dto.DeleteBook;
import com.automation.demoqa.api.model.Isbn;
import com.automation.demoqa.api.model.JwtToken;
import com.automation.demoqa.api.dto.UpdateBook;
import com.automation.demoqa.api.dto.CreateBooksList;
import io.restassured.response.Response;

import java.util.List;

public class BookService {

    public Response getAllBooks() {
        return ApiSpec.request()
                .basePath("/BookStore/v1/Books")
                .when()
                .get();

    }

    public Response getOneBook(String isbn) {
        return ApiSpec.request()
                .param("ISBN", isbn)
                .basePath("/BookStore/v1/Book")
                .when()
                .get();
    }

    public Response addBooksToUser(String userId, List<Isbn> isbns, JwtToken jwtToken) {
        return ApiSpec.request()
                .header("Authorization", "Bearer " + jwtToken.getToken())
                .basePath("/BookStore/v1/Books")
                .body(new CreateBooksList(userId, isbns))
                .when()
                .post();

    }

    public Response deleteAllBooksFromUser(String userId, JwtToken jwtToken) {
        return ApiSpec.request()
                .header("Authorization", "Bearer " + jwtToken.getToken())
                .param("UserId", userId)
                .basePath("/BookStore/v1/Books")
                .when()
                .delete();
    }

    public Response deleteBookFromUser(String userId, String isbn, JwtToken jwtToken) {
        return ApiSpec.request()
                .header("Authorization", "Bearer " + jwtToken.getToken())
                .basePath("/BookStore/v1/Book")
                .when()
                .body(new DeleteBook(userId, isbn))
                .delete();
    }

    public Response updateBook(String isbn, String replaceIsbn, String userId, JwtToken jwtToken) {
        return ApiSpec.request()
                .header("Authorization", "Bearer " + jwtToken.getToken())
                .basePath("/BookStore/v1/Books/" + isbn)
                .body(new UpdateBook(userId, replaceIsbn))
                .when()
                .put();
    }


}
