package com.example.medinet_project.Controller;

import com.example.medinet_project.Model.Buyer;
import com.example.medinet_project.Model.Cart;
import com.example.medinet_project.Model.Medicine;
import com.example.medinet_project.Model.Temporary;
import com.example.medinet_project.Repository.BuyerRepository;
import com.example.medinet_project.Repository.CartRepository;
import com.example.medinet_project.Repository.MedicineRepository;
import com.example.medinet_project.Service.CartService;
import com.example.medinet_project.Service.MedicineService;
import com.example.medinet_project.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MedicineController {
    @Autowired
    MedicineRepository medicineRepository;
    @Autowired
    MedicineService medicineService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    SessionService sessionService;
    @Autowired
    CartService cartService;
    @GetMapping("/store")
    public String getMedicine(Model model) {
        model.addAttribute("medicineList",medicineService.getAllMedicines());
        model.addAttribute("cart", new Cart());
        return "store";
    }
    @PostMapping("/store/cart")
    public String postMedicine(Cart cart, Model model, HttpSession httpSession){
        cartService.saveCart(cart, sessionService.getEmail(httpSession));
        String s=String.valueOf(cartService.subTotal(sessionService.getEmail(httpSession)));
        model.addAttribute("subTotal",s);
        model.addAttribute("medicineListforCart",cartService.medicineListForCart(sessionService.getEmail(httpSession)));
        model.addAttribute("cart", cart);
        System.out.println(s);



        return "cartSimple";
    }


}
