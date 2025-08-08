package com.gpcodefusion.assetmanager.service;

import com.gpcodefusion.assetmanager.model.UsersDetails;
import com.gpcodefusion.assetmanager.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDetailsRepository userRepo;

    public String generateResetToken(String email) {
        Optional<UsersDetails> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isEmpty()) return null;

        UsersDetails user = optionalUser.get();
        String token = UUID.randomUUID().toString();
        user.setPasswordResetToken(token);
        userRepo.save(user);
        return token;
    }

    public boolean resetPassword(String token, String newPassword) {
        Optional<UsersDetails> optionalUser = userRepo.findByPasswordResetToken(token);
        if (optionalUser.isEmpty()) return false;

        UsersDetails user = optionalUser.get();
        user.setUserPassword(newPassword); // Optionally encrypt
        user.setPasswordResetToken(null);
        userRepo.save(user);
        return true;
    }
}
