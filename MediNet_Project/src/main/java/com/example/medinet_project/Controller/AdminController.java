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

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Signup_request signup_request;
    @Autowired
    MedicineRepository medicineRepository;

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

}
