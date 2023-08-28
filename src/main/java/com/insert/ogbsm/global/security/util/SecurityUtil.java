package com.insert.ogbsm.global.security.util;


import com.insert.ogbsm.domain.user.User;
import com.insert.ogbsm.domain.user.exception.UserNotLoginException;
import com.insert.ogbsm.global.security.auth.AuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static User getCurrentUserWithLogin() {
        try {
            return getUser();
        } catch (ClassCastException e) {
            throw UserNotLoginException.EXCEPTION;
        }
    }

    public static User getCurrentUserOrNotLogin() {
        return getUser();
    }

    private static User getUser() {

        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof String) {
            throw UserNotLoginException.EXCEPTION;
        }

        AuthDetails authDetails = (AuthDetails) principal;

        return authDetails.getUser();
    }
}
