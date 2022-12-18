package com.example.medinet_project.Service;

import com.example.medinet_project.Model.Cart;
import com.example.medinet_project.Model.Medicine;
import com.example.medinet_project.Repository.MedicineRepository;
import com.example.medinet_project.Util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MedicineService {
    @Autowired
    MedicineRepository medicineRepository;
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
    public void addMedicine(Medicine medicine, Model model,MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        medicine.setUrl(fileName);
        Medicine savedMedicines= medicineRepository.save(medicine);
        String uploadDir="S:\\STUDY9thsemester\\CSE470\\MediNet_Project\\src\\main\\resources\\static\\medicine-photos\\"+ savedMedicines.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        List<Medicine> medicineList=medicineRepository.findMedicine();
    }
}
