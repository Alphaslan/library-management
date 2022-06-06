package com.library.management.util;

import com.library.management.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public boolean isValid(User user) {
        return !user.getEmail().equals("") && user.getEmail() != null;
    }
}
