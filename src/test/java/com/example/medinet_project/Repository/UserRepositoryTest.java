package com.example.medinet_project.Repository;

import com.example.medinet_project.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Test
    void isUserExistById(){
        User user=new User();
        user.email="test@gmail.com";
        user.password="1234";
        user.confirmPassword="1234";
        user.setEnabled(true);
        userRepository.save(user);
        Boolean actualResult = userRepository.isPersonExitsByEmail("test@gmail.com");
        assertThat(actualResult).isTrue();
    }
}
