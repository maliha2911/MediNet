package com.example.medinet_project.Repository;

import com.example.medinet_project.Model.Cart;
import com.example.medinet_project.Model.Medicine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;
    @Test
    void isProductExistById(){
        Cart cart=new Cart();
        cart.cartId=25;
        cart.email="xyz@gmail.com";
        cart.quantity="4";
        cartRepository.save(cart);

        Boolean actualResult = cartRepository.isProductExitsByEmail("xyz@gmail.com");
        assertThat(actualResult).isTrue();
    }

}
