package com.gpcodefusion.assetmanager.controller;

import com.gpcodefusion.assetmanager.model.Login;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributeAdvice {

    @ModelAttribute
    public void addGlobalAttributes(Model model, HttpSession session) {
        Login user = (Login) session.getAttribute("loggedInUser");

        if (user != null) {
            model.addAttribute("userName", user.getName() != null ? user.getName() : "Guest");
            model.addAttribute("profileImg", user.getImg() != null ? user.getImg() : "/images/Landing/default.jpg");
        } else {
            model.addAttribute("userName", "Guest");
            model.addAttribute("profileImg", "/images/Landing/default.jpg");
        }
    }
}
