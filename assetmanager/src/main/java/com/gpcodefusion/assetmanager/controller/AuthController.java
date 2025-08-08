package com.gpcodefusion.assetmanager.controller;

import com.gpcodefusion.assetmanager.service.UserService;
import com.gpcodefusion.assetmanager.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/forgotPassword")
    public String showForgotPasswordForm() {
        return "auth/forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String processForgotPassword(@RequestParam("email") String email, Model model, HttpServletRequest request) {
        if (email == null || email.trim().isEmpty()) {
            model.addAttribute("message", "Please enter a valid email address.");
            return "auth/forgotPassword";
        }

        String token = userService.generateResetToken(email);
        if (token == null) {
            model.addAttribute("message", "Email address not found.");
        } else {
            String resetUrl = request.getScheme() + "://" + request.getServerName() + ":" +
                    request.getServerPort() + "/resetPassword?token=" + token;

            emailService.sendResetEmail(email, resetUrl);
            model.addAttribute("message", "Password reset link has been sent to your email.");
        }

        return "auth/forgotPassword";
    }


    @GetMapping("/resetPassword")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token);
        return "auth/resetPassword";
    }

    @PostMapping("/resetPassword")
    public String handleResetPassword(
            @RequestParam("token") String token,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model
    ) {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("message", "Passwords do not match!");
            model.addAttribute("token", token);
            return "auth/resetPassword";
        }

        boolean result = userService.resetPassword(token, newPassword);
        model.addAttribute("message", result ? "Password has been reset successfully." : "Invalid or expired token.");
        return "auth/resetPassword";
    }
}
