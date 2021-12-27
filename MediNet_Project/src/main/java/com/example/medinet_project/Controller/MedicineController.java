package com.example.medinet_project.Controller;

import com.example.medinet_project.Model.Buyer;
import com.example.medinet_project.Model.Cart;
import com.example.medinet_project.Model.Medicine;
import com.example.medinet_project.Model.Temporary;
import com.example.medinet_project.Repository.BuyerRepository;
import com.example.medinet_project.Repository.CartRepository;
import com.example.medinet_project.Repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MedicineController {
    @Autowired
    MedicineRepository medicineRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    BuyerRepository buyerRepository;
    @GetMapping("/store")
    public String getMedicine(Model model) {
        List<Medicine> medicineList=medicineRepository.findMedicine();
        Medicine medicineObject=new Medicine();
        model.addAttribute(medicineList);
        Medicine medicine = new Medicine();
        Cart cart=new Cart();
        model.addAttribute(cart);
        return "store";
    }
    @PostMapping("/postStore")
    public String postMedicine(Cart cart, Model model){
        Cart cart1=new Cart();
        System.out.println(cart.cartId);
        Temporary temporary=new Temporary();
        cart.email=temporary.getEmail();
        cartRepository.save(cart);

        List<Cart>cartList=new ArrayList<Cart>();
        List<Medicine>medicineListforCart= new ArrayList<Medicine>();
        List<Medicine> medicines=new ArrayList<Medicine>();
        medicines=medicineRepository.findMedicine();
        int subTotal=1;
        cartList=cartRepository.findbyEmail(temporary.getEmail());
        for (int i=0; i<cartList.size();i++) {
            int getPId = cartList.get(i).cartId;
            for (int j = 0; j < medicines.size(); j++) {
                if (getPId == medicines.get(j).id) {
                    medicineListforCart.add(medicines.get(j));
                    //cart er quantity ke medicine er product quantity te newa+ type changed from string to int
                    try {
                        medicines.get(j).productQuantity = Integer.parseInt(cartList.get(i).quantity);
                        //subTotal;
                        int priceINT = Integer.parseInt(medicines.get(j).price);

                        subTotal = subTotal + (medicines.get(j).productQuantity * priceINT);
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        System.out.println(subTotal);
        subTotal=subTotal-1;

        String s=String.valueOf(subTotal);
        System.out.println(s);
        model.addAttribute("s",s);
        model.addAttribute("medicineListforCart",medicineListforCart);
        System.out.println(medicineListforCart.get(0));
        model.addAttribute(cart);


        return "cartSimple";
    }


}
