package com.gpcodefusion.assetmanager.controller;

import org.springframework.ui.Model;
import com.gpcodefusion.assetmanager.model.Login;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "auth/login"; // Login page ko start page banaya
    }


    @GetMapping("/landing")
    public String landing(HttpSession session, HttpServletResponse response, Model model) {
        Login user = (Login) session.getAttribute("loggedInUser");

        return "landing";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    @GetMapping("/qrbarcode")
    public String QR() {
        return "qr_barcode";
    }
    @GetMapping("/Chart")
    public String chart() {
        return "Chart";
    }

    @GetMapping("/AssetMT")
    public String assetmt() {
        return "Asset/AssetMT";
    }


    @GetMapping("/about")
    public String showAboutPage() {
        return "about"; // refers to contact.html in templates
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat"; // refers to contact.html in templates
    }







}

