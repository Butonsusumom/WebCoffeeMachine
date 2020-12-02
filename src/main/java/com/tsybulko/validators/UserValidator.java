package com.tsybulko.validators;

import com.tsybulko.entity.users.Role;
import com.tsybulko.entity.users.User;
import com.tsybulko.services.PasswordService;
import com.tsybulko.services.factory.ServiceFactory;
import com.tsybulko.services.factory.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static UserValidator userValidatorInstance;
    private static ServiceFactory serviceFactory;

    private UserValidator() {
        serviceFactory = ServiceFactoryImpl.getServiceFactoryInstance();
    }

    public static UserValidator getUserValidatorInstance() {
        if (userValidatorInstance == null) {
            synchronized (UserValidator.class) {
                if (userValidatorInstance == null)
                    userValidatorInstance = new UserValidator();
            }
        }
        return userValidatorInstance;
    }

    public boolean validateLoginIn(String login, String password) {
        if (!hasScript(login) && !hasScript(password)) {
            User user = serviceFactory.getUserService().getUser(login);
            if (user != null) {
                return PasswordService.getPasswordServiceInstance().encrypt(password).equals(user.getPassword());
            }
        }
        return false;
    }

    public boolean validateSession(HttpServletRequest request, Role role) {
        User sessionUser = (User) request.getSession().getAttribute("currentUser");
        if (sessionUser == null || sessionUser.getRole() != role)
            return false;
        User user = serviceFactory.getUserService().getUser(sessionUser.getLogin());
        if (sessionUser.equals(user)) {
            user.setPassword(null);
            request.getSession().setAttribute("currentUser", user);
            return true;
        }
        return false;
    }

    public boolean validateRegister(User user, HttpServletRequest request) {
        boolean result = true;
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (password != null && confirmPassword != null) {
            if (password.equals(confirmPassword)) {
                if (!validatePassword(user.getPassword()))
                    result = false;
            } else {
                request.setAttribute("passwordMessage", "pass_dont_match");
                result = false;
            }
        } else
            result = false;
        if (validateLogin(user.getLogin())) {
            if (ServiceFactoryImpl.getServiceFactoryInstance().getUserService().getUser(user.getLogin()) != null) {
                request.setAttribute("loginMessage", "login_exists");
                result = false;
            } else
                request.setAttribute("loginValue", user.getLogin());
        } else
            result = false;
        if (validateName(user.getFirstName()))
            request.setAttribute("firstNameValue", user.getFirstName());
        else
            result = false;
        if (validateName(user.getLastName()))
            request.setAttribute("lastNameValue", user.getLastName());
        else
            result = false;
        if (validateName(user.getMiddleName()))
            request.setAttribute("middleNameValue", user.getMiddleName());
        else
            result = false;

        return result;
    }

    private boolean validateName(String name) {
        if (name == null || hasScript(name))
            return false;
        boolean result = false;
        String nameRegex = new String("^[a-zA-Zа-яА-ЯїЇіІєЄёЁ][- a-zA-Zа-яА-ЯїЇіІєЄёЁ']{1,100}$");
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        while (matcher.find())
            result = matcher.group().equals(name) ? true : result;
        return result;
    }

    private boolean validatePassword(String password) {
        if (password == null || hasScript(password))
            return false;
        boolean result = false;
        String passwordRegex = new String("^[a-zA-Z0-9]{6,25}$");
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        while (matcher.find())
            result = matcher.group().equals(password) ? true : result;
        return result;
    }

    private boolean validateLogin(String login) {
        if (login == null || hasScript(login))
            return false;
        boolean result = false;
        String loginRegex = new String("^[-a-zA-Z0-9_]{6,25}$");
        Pattern pattern = Pattern.compile(loginRegex);
        Matcher matcher = pattern.matcher(login);
        while (matcher.find())
            result = matcher.group().equals(login) ? true : result;
        return result;
    }

    private boolean hasScript(String line) {
        if (line == null)
            return true;
        String scriptRegex = new String("<script");
        Pattern pattern = Pattern.compile(scriptRegex);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }
}
