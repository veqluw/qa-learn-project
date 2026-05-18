package com.automation.demoqa.api.step;

import com.automation.demoqa.api.dto.ApiResponse;
import com.automation.demoqa.api.dto.response.AddBooksResponse;
import com.automation.demoqa.api.dto.response.DeleteBookResponse;
import com.automation.demoqa.api.dto.response.DeleteBooksListResponse;
import com.automation.demoqa.api.dto.response.UpdateBookResponse;
import com.automation.demoqa.api.model.Book;
import com.automation.demoqa.api.model.BooksList;
import com.automation.demoqa.api.model.Isbn;
import com.automation.demoqa.api.model.JwtToken;
import com.automation.demoqa.api.service.BookService;
import io.qameta.allure.Step;

import java.util.List;

import static com.automation.demoqa.api.util.Wrapper.wrap;

public class BookStep {

    private final BookService bookService = new BookService();

    @Step("Get all books")
    public ApiResponse<BooksList> getAllBooks() {
        return wrap(bookService.getAllBooks(), BooksList.class);
    }

    @Step("Get book by ISBN: {isbn}")
    public ApiResponse<Book> getOneBook(String isbn) {
        return wrap(bookService.getOneBook(isbn), Book.class);
    }

    @Step("Add books to user: {userId}")
    public ApiResponse<AddBooksResponse> addBooksToUser(String userId, List<Isbn> isbns, JwtToken jwtToken) {
        return wrap(bookService.addBooksToUser(userId, isbns, jwtToken), AddBooksResponse.class);
    }

    @Step("Delete all books for user: {userId}")
    public ApiResponse<DeleteBooksListResponse> deleteAllBooksFromUser(String userId, JwtToken jwtToken) {
        return wrap(bookService.deleteAllBooksFromUser(userId, jwtToken), DeleteBooksListResponse.class);
    }

    @Step("Delete book for user: {userId}")
    public ApiResponse<DeleteBookResponse> deleteBookFromUser(String userId, String isbn, JwtToken jwtToken) {
        return wrap(bookService.deleteBookFromUser(userId, isbn, jwtToken), DeleteBookResponse.class);
    }

    @Step("Update book: replace {isbn} with {replaceIsbn} for user {userId}")
    public ApiResponse<UpdateBookResponse> updateBook(String isbn, String replaceIsbn, String userId, JwtToken jwtToken) {
        return wrap(bookService.updateBook(isbn, replaceIsbn, userId, jwtToken), UpdateBookResponse.class);
    }

}
