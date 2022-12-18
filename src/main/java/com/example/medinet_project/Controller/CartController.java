package com.example.medinet_project.Controller;

import com.example.medinet_project.Model.Buyer;
import com.example.medinet_project.Model.Cart;
import com.example.medinet_project.Model.Medicine;
import com.example.medinet_project.Model.Temporary;
import com.example.medinet_project.Repository.BuyerRepository;
import com.example.medinet_project.Repository.CartRepository;
import com.example.medinet_project.Repository.MedicineRepository;
import com.example.medinet_project.Repository.UserRepository;
import com.example.medinet_project.Request.Signup_request;
import com.example.medinet_project.Service.CartService;
import com.example.medinet_project.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

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
    @Autowired
    SessionService sessionService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartService cartService;

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
    @GetMapping("/store/cart/{id}")
    public String postMedicine(Cart cart, Model model, HttpSession httpSession, @PathVariable String id){
        cart=cartRepository.findAll().get(0);
        cartService.saveCart(cart, sessionService.getEmail(httpSession));
        String s=String.valueOf(cartService.subTotal(sessionService.getEmail(httpSession)));
        model.addAttribute("subTotal",s);
        model.addAttribute("medicineListforCart",cartService.medicineListForCart(sessionService.getEmail(httpSession)));
        model.addAttribute("cart", cart);
        System.out.println(s);
        return "cartSimple";
    }
    @GetMapping("/cartProduct/delete/{id}")
    public RedirectView deleteCartProduct(Model model, @PathVariable String id){
        cartRepository.delete(cartRepository.findById(Integer.parseInt(id)).get());
        return new RedirectView("/store/cart/"+id);
    }
    @PostMapping("/confirmOrder")
    public String confirmOrder(Buyer buyer, Model model, HttpSession httpSession){
        buyer.email=sessionService.getEmail(httpSession);
        buyerRepository.save(buyer);
        return "Confirmation";

    }

}
