package com.example.medinet_project.Controller;

import com.example.medinet_project.Model.*;
import com.example.medinet_project.Repository.MedicineRepository;
import com.example.medinet_project.Repository.UserRepository;
import com.example.medinet_project.Request.Signup_request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Signup_request signup_request;
    @Autowired
    MedicineRepository medicineRepository;

    //homepage
    @GetMapping("/home")
    public String getIndex(Model model) {
        return "index";
    }
    @GetMapping("/faq")
    public String getFaq(Model model) {
        return "faq";
    }

    //Signup_Afterward
    @PostMapping("/postSignup")
    public String postsignup(User user, Model model) {
        if ((signup_request.emailCheck(user.email)) && (signup_request.passCheck(user.password, user.confirmPassword))) {
            userRepository.save(user);
            Login login = new Login();
            model.addAttribute(login);
            return "login";
        } else return "signup_failure";
    }



}
