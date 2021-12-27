package com.example.medinet_project.Controller;

import com.example.medinet_project.Model.Buyer;
import com.example.medinet_project.Model.Cart;
import com.example.medinet_project.Model.FinalBuyerInfo;
import com.example.medinet_project.Model.Medicine;
import com.example.medinet_project.Repository.BuyerRepository;
import com.example.medinet_project.Repository.CartRepository;
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
public class AdminController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Signup_request signup_request;
    @Autowired
    MedicineRepository medicineRepository;
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    CartRepository cartRepository;

    @GetMapping("/adminForm")
    public String getAdminForm(Model model) {
        Medicine medicine = new Medicine();
        model.addAttribute(medicine);
        return "adminForm";
    }
    @PostMapping("/postAdminForm")
    public  String postAdminForm(Medicine medicine1,Model model){
        medicineRepository.save(medicine1);
        List<Medicine> medicineList=medicineRepository.findMedicine();
        Medicine medicineObject=new Medicine();
        Cart cart=new Cart();
        model.addAttribute(cart);
        model.addAttribute(medicineList);
        return "store";
    }
    @GetMapping("/adminUse")
    public String getadminUse(Model model) {
        return "adminUse";
    }
    @GetMapping("/orderList")
    public String getadminOrder(Model model,Cart cart){
        Buyer finalBuyer=new Buyer();
        List<FinalBuyerInfo> finalBuyerInfoList=new ArrayList<FinalBuyerInfo>();
        List<Cart> finalCartList= new ArrayList<Cart>();
        List<Buyer> finalBuyerList= new ArrayList<Buyer>();
        List<Medicine> finalMedicineList=new ArrayList<Medicine>();
        finalCartList=cartRepository.findByAll();
        for(int i=0;i<finalCartList.size();i++){
            finalBuyerList=buyerRepository.findbyEmail(finalCartList.get(i).email);
            finalMedicineList=medicineRepository.findByPid(finalCartList.get(i).cartId);
            FinalBuyerInfo finalBuyerInfo=new FinalBuyerInfo();

            finalBuyerInfo.name=finalBuyerList.get(0).name;
            finalBuyerInfo.address=finalBuyerList.get(0).address;
            finalBuyerInfo.number=finalBuyerList.get(0).number;
            finalBuyerInfo.productName=finalMedicineList.get(0).name;
            finalBuyerInfo.quantity=finalCartList.get(i).quantity;
            finalBuyerInfo.price=finalMedicineList.get(0).price;
            finalBuyerInfoList.add(finalBuyerInfo);

        }
        model.addAttribute(cart);
        model.addAttribute("finalBuyerInfoList", finalBuyerInfoList);
        return "adminOrder";


    }

}
