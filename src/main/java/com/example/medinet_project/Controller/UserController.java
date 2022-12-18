package com.example.medinet_project.Controller;

import com.example.medinet_project.Model.*;
import com.example.medinet_project.Repository.UserRepository;
import com.example.medinet_project.Request.Signup_request;
import com.example.medinet_project.Service.EmailVerificationService;
import com.example.medinet_project.Service.SessionService;
import com.example.medinet_project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller

public class UserController {
    @Autowired
    Signup_request signup_request;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailVerificationService emailVerificationService;
    @Autowired
    UserService userService;
    @Autowired
    SessionService sessionService;
    //Signup_Receiving
    @GetMapping("/register")
    public String getSignup(Model model) {
        User user=new User();
        model.addAttribute(user);
        return "register";
    }
    @PostMapping("/register")
    public RedirectView postSignup(User user, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        if ((signup_request.emailCheck(user.email)) && (signup_request.passCheck(user.password, user.confirmPassword))) {
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String encodedPassword = passwordEncoder.encode(user.getPassword());
//            user.setPassword(encodedPassword);

            userRepository.save(user);
            emailVerificationService.register(user, getSiteURL(request));
            return new RedirectView("/login");
        } else return new RedirectView("/register");
    }
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (emailVerificationService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }
    @GetMapping("/login")
    public String getLogin(Model model) {
        Login login = new Login();
        model.addAttribute(login);
        return "login";
    }
    @PostMapping("/login")
    public RedirectView postLogin (User user, Model model, HttpSession httpSession) throws NullPointerException{
        if (userService.userValidate(user, httpSession)==2) {
            sessionService.saveSession(httpSession,user);
            return new RedirectView("/store");
        }
        else if(userService.userValidate(user, httpSession)==1)
            return new RedirectView("/adminUse");
        else return new RedirectView("/login");

    }
}
