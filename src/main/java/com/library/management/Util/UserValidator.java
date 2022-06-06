package com.library.management.Util;

import com.library.management.DataAccessLayer.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public boolean isValid(User user) {
        return !user.getEmail().equals("") && user.getEmail() != null;
    }
}
