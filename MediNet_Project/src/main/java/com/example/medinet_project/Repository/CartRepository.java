package com.example.medinet_project.Repository;

import com.example.medinet_project.Model.Cart;
import com.example.medinet_project.Model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Override
    Optional<Cart> findById(Integer integer);
    @Query(value = "select * from cart where email=:em", nativeQuery = true)
    public List<Cart> findbyEmail(String em);
    @Query(value = "select * from cart", nativeQuery = true)
    public List<Cart> findByAll();

}
