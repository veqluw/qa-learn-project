package com.automation.demoqa.api.assertation;

import com.automation.demoqa.api.model.Book;
import com.automation.demoqa.api.model.Isbn;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookAssertation {

    public static void assertBooksAdded(List<Isbn> expected, List<Book> actual) {
        List<String> actualIsbns = actual.stream()
                .map(Book::getIsbn)
                .toList();

        List<String> expectedIsbns = expected.stream()
                .map(Isbn::getIsbn)
                .toList();

        assertEquals(new HashSet<>(expectedIsbns), new HashSet<>(actualIsbns));
    }

    public static void assertStatus(int recievedCode, int code) {
        assertEquals(code, recievedCode);
    }

    public static void assertTwoBooksEqual(Book firstBook, Book secondBook) {
        assertEquals(firstBook.getIsbn(), secondBook.getIsbn());
        assertEquals(firstBook.getAuthor(), secondBook.getAuthor());
    }

    public static void assertBookHasNoData(Book book) {
        assertNull(book.getIsbn());
        assertNull(book.getAuthor());
        assertNull(book.getDescription());
        assertNull(book.getPublisher());
        assertNull(book.getSubTitle());
        assertNull(book.getPages());
        assertNull(book.getWebsite());
    }

    public static void assertBookDeleted(List<Book> books, Book deletedBook) {
        assertFalse(books.contains(deletedBook));
    }

    public static void assertBookReplaced(Book newBook, Book replacedBook, List<Book> books) {
        assertFalse(books.contains(replacedBook));
        assertTrue(books.contains(newBook));
    }

    public static void assertBookNotReplaced(Book newBook, Book replacedBook, List<Book> books) {
        assertTrue(books.contains(replacedBook));
        assertFalse(books.contains(newBook));
    }

    public static void assertBooksEmpty(List<Book> books) {
        assertTrue(books.isEmpty());
    }

}
