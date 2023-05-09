package com.example.atrium.controllers;

import com.example.atrium.models.Session;
import com.example.atrium.models.Topic;
import com.example.atrium.models.User;
import com.example.atrium.models.nondb.Login;
import com.example.atrium.models.nondb.LoginForm;
import com.example.atrium.models.nondb.RegisterForm;
import com.example.atrium.workers.interfaces.Authentication;
import com.example.atrium.workers.interfaces.SessionTracker;
import com.example.atrium.workers.interfaces.TopicsTracker;
import jakarta.servlet.http.HttpServletRequest;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pages")
public class PagesController {

    @Autowired
    Authentication controller;

    @Autowired
    SessionTracker sessionTracker;

    @Autowired
    TopicsTracker topicsTracker;

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request){

        ResponseEntity<User> status = sessionTracker.isSessionGoing(request.getRemoteAddr());

        if(status.getStatusCode().equals(HttpStatus.OK))
            model.addAttribute("profile",status.getBody());

        return "index";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request){
        ResponseEntity<User> session = sessionTracker.isSessionGoing(request.getRemoteAddr());

        if(session.getStatusCode().equals(HttpStatus.OK)) {
            User user = session.getBody();
            ResponseEntity<List<Topic>> topics = topicsTracker.topics(user);

            model.addAttribute("profile", user);
            model.addAttribute("topics", topics.getBody());
            return "profile";
        }
        return "redirect:home";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpServletRequest request){
        ResponseEntity<HttpStatus> status = controller.logout(request.getRemoteAddr());
        model.addAttribute("status",status);
        return "redirect:home";
    }

    @GetMapping("/login")
    public String loginGet(Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(Model model, @ModelAttribute("loginForm")LoginForm loginForm, HttpServletRequest request){
        String ipAddress = request.getRemoteAddr();
        ResponseEntity<HttpStatus> status = controller.login(ipAddress,new Login(loginForm.getEmail(), loginForm.getPassword()));

        model.addAttribute("status", status);

        if(status.getStatusCode().equals(HttpStatus.NOT_FOUND) || status.getStatusCode().equals(HttpStatus.UNAUTHORIZED) || status.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR))
            return "login";

        return "redirect:home";
    }


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new RegisterForm());
        return "registration";
    }

    @PostMapping("/register")
    public String applyRegister(Model model , @ModelAttribute("user") RegisterForm user) {
        ResponseEntity<HttpStatus> status = controller.register(user);

        //the account exists , can't create new
        if(status.getStatusCode().equals(HttpStatus.OK)){
            model.addAttribute("exist",true);
            return "registration";
        }

        return "redirect:login";
    }
}
