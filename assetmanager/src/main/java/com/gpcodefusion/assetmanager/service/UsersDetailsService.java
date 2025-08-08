package com.gpcodefusion.assetmanager.service;

import com.gpcodefusion.assetmanager.model.UsersDetails;
import com.gpcodefusion.assetmanager.repository.UserRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UsersDetailsService {

    @Autowired
    private UserRegisterRepository repository;

    public UsersDetails saveUser(UsersDetails user) {
        // 1. Generate userID (first 4 letters of firstname + last 4 digits of phone)
        String first4 = user.getFirstName().length() >= 4 ? user.getFirstName().substring(0, 4) : user.getFirstName();
        String last4Phone = user.getPhoneNumber().substring(user.getPhoneNumber().length() - 4);
        String userID = first4 + last4Phone;
        user.setUserID(userID);

        // 2. Generate userName = FirstName + LastName
        user.setUserName(user.getFirstName() + " " + user.getLastName());

        // 3. Generate random passcode
        user.setPassCode(generateRandomCode(6));

        return repository.save(user);
    }

    private String generateRandomCode(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            code.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return code.toString();
    }
}

