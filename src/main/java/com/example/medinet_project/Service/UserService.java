package com.example.medinet_project.Service;

import com.example.medinet_project.Model.User;
import com.example.medinet_project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public UserService(UserRepository userRepository)
    {
        // this keyword refers to current instance
        this.userRepository = userRepository;
    }
    public int userValidate(User user, HttpSession httpSession){

        List<User> userList=userRepository.findByEmail(user.getEmail());
        if(!userList.isEmpty()){
            if(user.getEmail().equals("admin@admin.com"))
                return 1;
            else if(userList.get(0).getPassword().equals(user.getPassword()) && userList.get(0).isEnabled()){
                return 2;
            }
        }
        return 0;


    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
