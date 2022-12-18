package com.example.medinet_project.Service;

import com.example.medinet_project.Model.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionService {
    public void saveSession(HttpSession httpSession, User user){
        httpSession.setAttribute("email", user.email);

    }
    public String getEmail(HttpSession httpSession){
        return (String)httpSession.getAttribute("email");
    }
}
