package com.automation.demoqa.api.test;

import com.automation.demoqa.api.assertation.BookAssertation;
import com.automation.demoqa.api.data.factory.BookDataFactory;
import com.automation.demoqa.api.dto.ApiResponse;
import com.automation.demoqa.api.dto.response.AddBooksResponse;
import com.automation.demoqa.api.dto.response.DeleteBookResponse;
import com.automation.demoqa.api.dto.response.DeleteBooksListResponse;
import com.automation.demoqa.api.dto.response.UpdateBookResponse;
import com.automation.demoqa.api.model.*;
import com.automation.demoqa.api.step.BookStep;
import com.automation.demoqa.api.step.UserStep;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.automation.demoqa.api.data.provider.UserProvider.validUser;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.junit.jupiter.api.Assertions.*;

@Epic("BookStore API")
@Feature("Book management")
@Owner("veqluw")
public class BookControllerTest {

    private final BookStep bookStep = new BookStep();
    private final UserStep userStep = new UserStep();

    JwtToken token;
    BooksList allBooks;
    String userId;
    UserInfo createdUser;

    @BeforeEach
    void setup() {
        User user = validUser();
        createdUser = userStep.createUser(user).body();

        userId = createdUser.getUserID();
        token = userStep.generateToken(user).body();

        allBooks = bookStep.getAllBooks().body();
    }

    @Test
    @Story("Get books")
    @DisplayName("Getting all books")
    @Severity(BLOCKER)
    public void shouldReturnAllBooks() {
        assertNotNull(allBooks.getBooks());
        assertFalse(allBooks.getBooks().isEmpty());
    }

    @Test
    @Story("Get books")
    @DisplayName("Getting a book")
    @Severity(NORMAL)
    public void shouldReturnOneBook() {
        ApiResponse<Book> response = bookStep.getOneBook(allBooks.getBooks().getFirst().getIsbn());

        Book book = response.body();

        BookAssertation.assertStatus(response.statusCode(), 200);
        BookAssertation.assertTwoBooksEqual(book, allBooks.getBooks().getFirst());
    }

    @Test
    @Story("Get books")
    @DisplayName("Getting a book with an invalid book id")
    @Severity(NORMAL)
    public void shouldFailReturnOneBookWhenBookIdIsInvalid() {
        ApiResponse<Book> response = bookStep.getOneBook("invalid-isbn");

        Book book = response.body();

        BookAssertation.assertStatus(response.statusCode(), 400);
        BookAssertation.assertBookHasNoData(book);
    }

    @Test
    @Story("Add books")
    @DisplayName("Creating a new books list")
    @Severity(BLOCKER)
    public void shouldAddBooksToUser() {
        List<Isbn> isbns = BookDataFactory.defaultIsbns(allBooks.getBooks());

        ApiResponse<AddBooksResponse> response = bookStep.addBooksToUser(userId, isbns, token);
        List<Book> userBooks = userStep.getUserById(userId, token).body().getBooks();

        BookAssertation.assertStatus(response.statusCode(), 201);
        BookAssertation.assertBooksAdded(isbns, userBooks);
    }

    @Test
    @Story("Add books")
    @DisplayName("Creating a new books list with an invalid user id")
    @Severity(NORMAL)
    public void shouldFailAddBooksListToUserWhenUserIdIsInvalid() {
        List<Isbn> isbns = BookDataFactory.defaultIsbns(allBooks.getBooks());

        ApiResponse<AddBooksResponse> response = bookStep.addBooksToUser("invalid-id", isbns, token);

        BookAssertation.assertStatus(response.statusCode(), 401);

    }

    @Test
    @Story("Delete books")
    @DisplayName("Deleting all books from user")
    @Severity(NORMAL)
    public void shouldDeleteBooksFromUser() {
        List<Isbn> isbns = BookDataFactory.defaultIsbns(allBooks.getBooks());

        prepareBooksForUser(userId, isbns, token);

        ApiResponse<DeleteBooksListResponse> response = bookStep.deleteAllBooksFromUser(userId, token);

        List<Book> userBooks = userStep.getUserById(userId, token).body().getBooks();

        BookAssertation.assertStatus(response.statusCode(), 204);
        BookAssertation.assertBooksEmpty(userBooks);

    }

    @Test
    @Story("Delete books")
    @DisplayName("Deleting all books from user with an invalid user id")
    @Severity(NORMAL)
    public void shouldFailDeleteBooksListFromUserWhenUserIdIsInvalid() {
        ApiResponse<DeleteBooksListResponse> response = bookStep.deleteAllBooksFromUser("invalid-id", token);

        BookAssertation.assertStatus(response.statusCode(), 401);

    }

    @Test
    @Story("Delete books")
    @DisplayName("Deleting a book from user")
    @Severity(NORMAL)
    public void shouldDeleteBookFromUser() {
        List<Isbn> isbns = BookDataFactory.defaultIsbns(allBooks.getBooks());

        prepareBooksForUser(userId, isbns, token);

        Book book = allBooks.getBooks().getFirst();

        ApiResponse<DeleteBookResponse> response = bookStep.deleteBookFromUser(userId, book.getIsbn(), token);

        List<Book> books = userStep.getUserById(userId, token).body().getBooks();

        BookAssertation.assertStatus(response.statusCode(), 204);
        BookAssertation.assertBookDeleted(books, book);

    }

    @Test
    @Story("Delete books")
    @DisplayName("Deleting a book from user with an invalid user id")
    @Severity(NORMAL)
    public void shouldFailDeleteBookFromUserWhenUserIdIsInvalid() {
        List<Isbn> isbns = BookDataFactory.defaultIsbns(allBooks.getBooks());

        prepareBooksForUser(userId, isbns, token);

        Book book = allBooks.getBooks().getFirst();

        ApiResponse<DeleteBookResponse> response = bookStep.deleteBookFromUser("invalid-id", book.getIsbn(), token);

        List<Book> books = userStep.getUserById(userId, token).body().getBooks();

        BookAssertation.assertStatus(response.statusCode(), 401);
    }

    @Test
    @Story("Delete books")
    @DisplayName("Deleting a book from user with an invalid book id")
    @Severity(NORMAL)
    public void shouldFailDeleteBookFromUserWhenBookIdIsInvalid() {
        List<Isbn> isbns = BookDataFactory.defaultIsbns(allBooks.getBooks());

        prepareBooksForUser(userId, isbns, token);

        ApiResponse<DeleteBookResponse> response = bookStep.deleteBookFromUser(userId, "invalid-isbn", token);

        BookAssertation.assertStatus(response.statusCode(), 400);
    }

    @Test
    @Story("Update book")
    @DisplayName("Updating a book for user")
    @Severity(NORMAL)
    public void shouldUpdateBookToUser() {
        List<Isbn> isbns = BookDataFactory.defaultIsbns(allBooks.getBooks());

        prepareBooksForUser(userId, isbns, token);

        Book book = userStep.getUserById(userId, token)
                .body()
                .getBooks()
                .getFirst();

        Book newBook = allBooks.getBooks().getLast();

        ApiResponse<UpdateBookResponse> response = bookStep.updateBook(book.getIsbn(), newBook.getIsbn(), userId, token);

        List<Book> books = userStep.getUserById(userId, token)
                .body()
                .getBooks();

        BookAssertation.assertStatus(response.statusCode(), 200);
        BookAssertation.assertBookReplaced(newBook, book, books);
    }

    @Test
    @Story("Update book")
    @DisplayName("Updating a book for user with an invalid user id")
    @Severity(NORMAL)
    public void shouldFailUpdateBookToUserWhenUserIdIsInvalid() {
        List<Isbn> isbns = BookDataFactory.defaultIsbns(allBooks.getBooks());

        prepareBooksForUser(userId, isbns, token);

        Book book = userStep.getUserById(userId, token)
                .body()
                .getBooks()
                .getFirst();

        Book newBook = allBooks.getBooks().getLast();

        ApiResponse<UpdateBookResponse> response = bookStep.updateBook(book.getIsbn(), newBook.getIsbn(), "invalid-id", token);

        List<Book> books = userStep.getUserById(userId, token)
                .body()
                .getBooks();

        BookAssertation.assertStatus(response.statusCode(), 401);
        BookAssertation.assertBookNotReplaced(newBook, book, books);
    }

    @Test
    @Story("Update book")
    @DisplayName("Updating a book for user with an invalid book id")
    @Severity(NORMAL)
    public void shouldFailUpdateBookToUserWhenBookIdIsInvalid() {
        List<Isbn> isbns = BookDataFactory.defaultIsbns(allBooks.getBooks());

        prepareBooksForUser(userId, isbns, token);

        Book newBook = allBooks.getBooks().getLast();

        ApiResponse<UpdateBookResponse> response = bookStep.updateBook("invalid-id", newBook.getIsbn(), userId, token);

        BookAssertation.assertStatus(response.statusCode(), 400);

    }

    private ApiResponse<AddBooksResponse>  prepareBooksForUser(String userId, List<Isbn> isbns, JwtToken token) {
        return bookStep.addBooksToUser(userId, isbns, token);
    }

}
