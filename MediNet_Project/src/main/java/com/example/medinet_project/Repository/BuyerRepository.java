package com.example.medinet_project.Repository;

import com.example.medinet_project.Model.Buyer;
import com.example.medinet_project.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Integer> {
    @Override
    Optional<Buyer> findById(Integer integer);
    @Query(value = "select * from buyer where email=:em", nativeQuery = true)
    public List<Buyer> findbyEmail(String em);
}
