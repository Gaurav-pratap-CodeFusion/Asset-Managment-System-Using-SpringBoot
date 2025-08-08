package com.gpcodefusion.assetmanager.controller;

import com.gpcodefusion.assetmanager.model.UsersDetails;
import com.gpcodefusion.assetmanager.service.User_TabService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class User_TabController {

    @Autowired
    private User_TabService userService;

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "user/user_tab"; // user_tab.html
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userID") String userID, RedirectAttributes redirectAttributes) {
        boolean deleted = userService.deleteUserById(userID);
        if (deleted) {
            redirectAttributes.addFlashAttribute("message", "User '" + userID + "' deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("message", "User '" + userID + "' not found.");
        }
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String updateUserPage() {
        return "user/user_update"; // placeholder
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute UsersDetails userInput) {

        try {
            // Step 1: DB se purana user le lo
            Optional<UsersDetails> optionalUser = userService.findById(userInput.getUserID());
            if (optionalUser.isPresent()) {
                UsersDetails existingUser = optionalUser.get();

                if (existingUser != null) {
                    // Step 2: Sirf update hone wale fields set karo
                    existingUser.setUserID(userInput.getUserID());
                    existingUser.setUserName(userInput.getUserName());
                    existingUser.setFirstName(userInput.getFirstName());
                    existingUser.setLastName(userInput.getLastName());
                    existingUser.setEmail(userInput.getEmail());
                    existingUser.setPhoneNumber(userInput.getPhoneNumber());
                    existingUser.setAddress(userInput.getAddress());
                    existingUser.setCountry(userInput.getCountry());
                    existingUser.setUserPassword(userInput.getUserPassword());
                    // ... 9 fields total

//                    // Step 3: Image file ho to save karo
//                    if (!file.isEmpty()) {
//                        String folderPath = "uploads/";
//                        File folder = new File(folderPath);
//                        if (!folder.exists()) folder.mkdirs();
//
//                        String fileName = file.getOriginalFilename();
//                        String filePath = folderPath + fileName;
//
//                        file.transferTo(Paths.get(filePath));
//                        existingUser.setProfileImage(filePath);
//                    }
//                    System.out.println("Received file: " + file.getOriginalFilename());
//                    System.out.println("Size: " + file.getSize());

                    // Step 4: Update
                    userService.updateUser(existingUser);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "redirect:/users";
    }


    @PostMapping("/check")
    public String checkUser(@RequestParam("UserID") String userId, Model model) {
        Optional<UsersDetails> user = userService.findById(userId);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user/user_update_form"; // Show editable form
        } else {
            model.addAttribute("error", "User ID not found!");
            return "user/user_update"; // Show same page with error
        }
    }
}