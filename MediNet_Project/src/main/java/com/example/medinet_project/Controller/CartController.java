package com.example.medinet_project.Controller;

import com.example.medinet_project.Model.Cart;
import com.example.medinet_project.Model.Medicine;
import com.example.medinet_project.Repository.MedicineRepository;
import com.example.medinet_project.Repository.UserRepository;
import com.example.medinet_project.Request.Signup_request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Signup_request signup_request;
    @Autowired
    MedicineRepository medicineRepository;

    @GetMapping("/cart")
    public String getCart(Model model) {
        Medicine medicineObject=new Medicine();
        model.addAttribute(medicineObject);
        return "cartSimple";
    }
//        store 1 ar cart 1 pathano

    @PostMapping("/postCart")
    public String postCart(Cart cart,Model model){
        return "proceedOrder";
    }
    @GetMapping("/proceedOrder")
    public String getProceedOrder(Model model) {
        return "proceedOrder";
    }
}
