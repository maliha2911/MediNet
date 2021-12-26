package com.example.medinet_project.Repository;

import com.example.medinet_project.Model.Medicine;
import com.example.medinet_project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Integer> {
    @Override
    Optional<Medicine> findById(Integer integer);
    @Query(value = "select * from medicine", nativeQuery = true)
    public List<Medicine> findMedicine();

}
