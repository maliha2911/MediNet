package com.example.medinet_project.Controller;

import com.example.medinet_project.Model.Buyer;
import com.example.medinet_project.Model.Cart;
import com.example.medinet_project.Model.Medicine;
import com.example.medinet_project.Model.Temporary;
import com.example.medinet_project.Repository.BuyerRepository;
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
    @Autowired
    BuyerRepository buyerRepository;

    @GetMapping("/cart")
    public String getCart(Model model) {
        Medicine medicineObject=new Medicine();
        model.addAttribute(medicineObject);
        return "cartSimple";
    }
//        store 1 ar cart 1 pathano

    @PostMapping("/postCart")
    public String postCart(Model model){
        Buyer buyer=new Buyer();
        model.addAttribute(buyer);
        return "proceedOrder";
    }
    @PostMapping("/confirmOrder")
    public String confirmOrder(Buyer buyer, Model model){
        Temporary tempBuyer=new Temporary();
        buyer.email=tempBuyer.getEmail();
        buyerRepository.save(buyer);
        return "Confirmation";

    }

}
