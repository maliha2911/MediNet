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
    public String getIndex_reg(Model model) {
        return "index";
    }
    //Signup_Receiving
    @GetMapping("/register")
    public String getSignup(Model model) {
        User user=new User();
        model.addAttribute(user);
        return "register";
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
    @GetMapping("/login")
    public String getLogin(Model model) {
        Login login = new Login();
        model.addAttribute(login);
        return "login";
    }
    @PostMapping("/postlogininfo")
    public String postLogin (Login login,Model model) throws NullPointerException{
        List<Medicine> medicineList=new ArrayList<Medicine>();
        Login login1 = new Login();


        List<User> user = userRepository.findByEmail(login.email);
        if (user.size() >= 1){
            String checkpass = user.get(0).getPassword();
            if (checkpass.equals(login.password)) {
                model.addAttribute(login1);
                Temporary temporary=new Temporary();
                temporary.setEmail(login.email);
                if(temporary.getEmail().equals("admin@admin.com"))
                    return "adminUse";
                else {
                    Cart cart = new Cart();

                    medicineList = medicineRepository.findMedicine();
                    System.out.println(medicineList.get(0));
                    model.addAttribute(medicineList);
                    model.addAttribute(cart);
                    return "store";
                }
            }
            else {return "login_failure";}
        }

        else {return "login_failure";}
    }

}
