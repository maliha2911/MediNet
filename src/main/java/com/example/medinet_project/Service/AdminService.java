package com.example.medinet_project.Service;

import com.example.medinet_project.Model.Buyer;
import com.example.medinet_project.Model.Cart;
import com.example.medinet_project.Model.FinalBuyerInfo;
import com.example.medinet_project.Model.Medicine;
import com.example.medinet_project.Repository.BuyerRepository;
import com.example.medinet_project.Repository.CartRepository;
import com.example.medinet_project.Repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    MedicineRepository medicineRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    BuyerRepository buyerRepository;
    public List<Buyer> finalBuyerInfoList(){
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
        return finalBuyerList;
    }

}
