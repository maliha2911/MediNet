package com.example.medinet_project.Repository;


import com.example.medinet_project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    @Override
    Optional<User> findById(Integer integer);
    @Query(value = "select * from users where email= :email", nativeQuery = true)
    public List<User> findByEmail(@Param("email") String email);


}
