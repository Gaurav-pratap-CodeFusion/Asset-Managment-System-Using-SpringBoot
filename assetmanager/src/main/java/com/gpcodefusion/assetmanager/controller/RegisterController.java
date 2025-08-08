package com.gpcodefusion.assetmanager.controller;

import com.gpcodefusion.assetmanager.model.UsersDetails;
import com.gpcodefusion.assetmanager.repository.UserRegisterRepository;
import com.gpcodefusion.assetmanager.service.UsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.File;
import java.nio.file.Paths;

@Controller
public class RegisterController {

    @Autowired
    private UserRegisterRepository userRepo;

    @Autowired
    private UsersDetailsService service;

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new UsersDetails());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UsersDetails user,
                               @RequestParam("fileUpload") MultipartFile file,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        try {
            // ✅ Check if email already exists
            if (userRepo.existsByEmail(user.getEmail())) {
                model.addAttribute("mesg", "Email already exist try with another email.");
                return "auth/register";
            }

            // ✅ Save image to local folder (optional)
            if (!file.isEmpty()) {
                String folderPath = "uploads/";
                File folder = new File(folderPath);
                if (!folder.exists()) folder.mkdirs();

                String fileName = file.getOriginalFilename();
                String filePath = folderPath + fileName;

                file.transferTo(Paths.get(filePath));
                user.setProfileImage(filePath);
            }

            // ✅ Save user via service
            service.saveUser(user);
            model.addAttribute("mesg", "Registration Successful!");
            return "auth/register";

        } catch (Exception e) {
            model.addAttribute("mesg", "Registration failed: " + e.getMessage());
            return "auth/register";
        }
    }

}
