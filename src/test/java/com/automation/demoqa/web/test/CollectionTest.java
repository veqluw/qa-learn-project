package com.automation.demoqa.web.test;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.automation.demoqa.web.data.Books.git_pocket_guide;
import static com.automation.demoqa.web.data.Users.standard_user;
import static com.automation.demoqa.web.page.LoginPage.openLoginPage;

@Story("Collection")
public class CollectionTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    public void testAddBookToCollection() {
        openLoginPage()
                .successAuth(standard_user)
                .openMainPage()
                .openBookPage(git_pocket_guide)
                .addBookToCollection()
                .logOut("Log out");
    }

    @Test
    @Step("Removing book from collection")
    @Severity(SeverityLevel.BLOCKER)
    public void testRemoveBookFromCollection() {
        openLoginPage()
                .successAuth(standard_user)
                .openMainPage()
                .openBookPage(git_pocket_guide)
                .addBookToCollection()
                .openProfilePage()
                .clickTrashButton(git_pocket_guide)
                .confirmDelete("Delete book")
                        .logOut("Logout");

    }

    // Button "OK" from Modal window does not work at all xd
//    @Test
//    public void removeAllBooksFromCollection() {
//        openLoginPage()
//                .successAuth(standard_user)
//                .openMainPage()
//                .openBookPage(git_pocket_guide)
//                .addBookToCollection()
//                .openMainPage()
//                .openBookPage(speaking_javascript)
//                .addBookToCollection()
//                .openProfilePage()
//                .removeAllBooks()
//                .confirmDelete("Delete All Books")
//                .logOut("Logout");
//    }



 }
