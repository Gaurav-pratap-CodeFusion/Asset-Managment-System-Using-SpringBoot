package com.gpcodefusion.assetmanager.service;

import com.gpcodefusion.assetmanager.model.UsersDetails;
import com.gpcodefusion.assetmanager.repository.User_TabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class User_TabService {
    @Autowired
    private User_TabRepository userRepository;

    public List<UsersDetails> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUserById(String userID) {
        if (userRepository.existsById(userID)) {
            userRepository.deleteById(userID);
            return true;
        }
        return false;
    }

    public Optional<UsersDetails> findById(String id) {
        return userRepository.findById(id);
    }

    public void updateUser(UsersDetails user) {
        userRepository.save(user);
    }



}
