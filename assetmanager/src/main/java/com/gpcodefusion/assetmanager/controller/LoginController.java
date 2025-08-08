package com.gpcodefusion.assetmanager.controller;

import com.gpcodefusion.assetmanager.model.Login;
import com.gpcodefusion.assetmanager.repository.LoginRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {


    @Autowired
    private LoginRepository userRepo;

    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        // ✅ If already logged in, redirect to dashboard
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/landing";
        }
        return "auth/login";
    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clears session
        return "redirect:/landing"; // Force a new request so @ModelAttribute runs
    }


    @PostMapping("/login")
    public String doLogin(@RequestParam String userId,
                          @RequestParam String userPassword,
                          Model model,
                          HttpSession session,
                          HttpServletResponse response) {

        Login user = userRepo.findByUserIdAndUserPassword(userId, userPassword);

        if (user != null) {
            session.setAttribute("loggedInUser", user);
            session.setAttribute("UserName", user.getUserId()); // or getUserName()
            session.setAttribute("PassCode", user.getUserPassword()); // optional
            return "redirect:/landing";
        } else {
            model.addAttribute("mesg", "Invalid UserID or Password");
            return "auth/login";
        }
    }

}
