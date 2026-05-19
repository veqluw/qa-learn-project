package com.automation.reqres.api.test;

import com.automation.reqres.api.model.user.UserFull;
import com.automation.reqres.api.model.user.UserList;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.automation.reqres.api.data.Users.userGenerator;
import static com.automation.reqres.api.step.UserControllerStep.*;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    @Test
    @DisplayName("Getting users list")
    @Severity(NORMAL)
    public void userList() {
        UserList usersList = getUsersList(2, 30);

        assertEquals(2, usersList.getPage());
        assertNotNull(usersList.getData());
        assertFalse(usersList.getData().isEmpty());
    }

    @Test
    @DisplayName("Creating a new user")
    @Severity(BLOCKER)
    public void createNewUser() {
        UserFull user = userGenerator();

        UserFull createdUser = createUser(user);

        assertEquals(user.getFirstName(), createdUser.getFirstName());
        assertEquals(user.getLastName(), createdUser.getLastName());
        assertEquals(user.getEmail(), createdUser.getEmail());
    }

    @Test
    @DisplayName("Editing user")
    @Severity(BLOCKER)
    public void changeUserInfo() {
        UserFull user = userGenerator();
        Map<String, String> fieldsForChange = Map.of(
                "first_name", "Dan",
                "last_name", "Smith"
        );

        UserFull createdUser = createUser(user);
        UserFull changedUser = updateUserById(createdUser.getId(), fieldsForChange);

        assertEquals(fieldsForChange.get("first_name"), changedUser.getFirstName());
        assertEquals(fieldsForChange.get("last_name"), changedUser.getLastName());
    }

    @Test
    @DisplayName("Deleting user")
    @Severity(BLOCKER)
    public void deleteUser() {
        UserFull user = userGenerator();

        UserFull createdUser = createUser(user);
        deleteUserById(createdUser.getId());
        failedGetUserById(createdUser.getId());
    }

}
