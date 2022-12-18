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
import com.example.medinet_project.Service.AdminService;
import com.example.medinet_project.Service.MedicineService;
import com.example.medinet_project.Util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
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
    @Autowired
    AdminService adminService;
    @Autowired
    MedicineService medicineService;

    @GetMapping("/adminForm")
    public String getAdminForm(Model model) {
        Medicine medicine = new Medicine();
        model.addAttribute(medicine);
        return "adminForm";
    }
    @PostMapping("/postAdminForm")
    public RedirectView postAdminForm(Medicine medicine,Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        medicineService.addMedicine(medicine, model, multipartFile);
        return new RedirectView("/productList");
    }
    @GetMapping("/adminUse")
    public String getadminUse(Model model) {
        return "adminUse";
    }
    @GetMapping("/orderList")
    public String getadminOrder(Model model,Cart cart){
        model.addAttribute(cart);
        model.addAttribute("finalBuyerInfoList", adminService.finalBuyerInfoList());
        return "adminOrder";


    }
    @GetMapping("/productList")
    public String getProductList(Model model){
        List<Medicine> medicines=medicineRepository.findAll();
        model.addAttribute("medicines", medicines);
        return "productList";
    }
    @GetMapping("/productList/delete/{id}")
    public RedirectView deleteProduct(Model model, @PathVariable String id){
        medicineRepository.delete(medicineRepository.findById(Integer.parseInt(id)).get());
        return new RedirectView("/productList");
    }

}
