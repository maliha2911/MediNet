package com.example.medinet_project.Service;

import com.example.medinet_project.Model.Cart;
import com.example.medinet_project.Model.Medicine;
import com.example.medinet_project.Repository.CartRepository;
import com.example.medinet_project.Repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    MedicineRepository medicineRepository;

    public List<Medicine> medicineListForCart(String email){
        List<Medicine>medicineListforCart= new ArrayList<Medicine>();
        List<Medicine> medicines=medicineRepository.findMedicine();
        List<Cart>cartList=cartRepository.findbyEmail(email);
        for (int i=0; i<cartList.size();i++) {
            int getPId = cartList.get(i).cartId;
            for (int j = 0; j < medicines.size(); j++) {
                if (getPId == medicines.get(j).id) {
                    medicineListforCart.add(medicines.get(j));
                    //cart er quantity ke medicine er product quantity te newa+ type changed from string to int
                    try {
                        medicines.get(j).productQuantity = Integer.parseInt(cartList.get(i).quantity);
                        //subTotal;
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return medicineListforCart;


    }
    public void saveCart(Cart cart, String email){
        cart.email=email;
        cartRepository.save(cart);
    }
    public List<Cart> getCartByEmail(String email){
        return cartRepository.findbyEmail(email);

    }
    public int subTotal(String email){
        List<Medicine> medicines=medicineRepository.findMedicine();
        int subTotal=1;
        List<Cart>cartList=cartRepository.findbyEmail(email);
        for (int i=0; i<cartList.size();i++) {
            int getPId = cartList.get(i).cartId;
            for (int j = 0; j < medicines.size(); j++) {
                if (getPId == medicines.get(j).id) {
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
        subTotal=subTotal-1;
        return subTotal;

    }
}
