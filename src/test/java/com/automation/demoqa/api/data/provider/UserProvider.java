package com.automation.demoqa.api.data.provider;

import com.automation.demoqa.api.data.factory.UserDataFactory;
import com.automation.demoqa.api.model.User;

public class UserProvider {

    public static User validUser() {
        return UserDataFactory.defaultUser().build();
    }

}
