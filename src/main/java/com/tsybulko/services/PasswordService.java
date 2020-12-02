package com.tsybulko.services;

import com.tsybulko.exceptions.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordService {
    private static PasswordService passwordServiceInstance;
    private static final Logger logger = Logger.getLogger(PasswordService.class);

    private PasswordService() {
    }

    public static PasswordService getPasswordServiceInstance() {
        if (passwordServiceInstance == null) {
            synchronized (PasswordService.class) {
                if (passwordServiceInstance == null)
                    passwordServiceInstance = new PasswordService();
            }
        }
        return passwordServiceInstance;
    }

    public String encrypt(String password) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedByteArray = digest.digest(password.getBytes("UTF-8"));
            result = Base64.getEncoder().encodeToString(hashedByteArray);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.log(Level.ERROR, new ServiceException("ServiceException class: "
                    + PasswordService.class + ", method: encrypt().", e));
            e.printStackTrace();
        }
        return result;
    }
}
