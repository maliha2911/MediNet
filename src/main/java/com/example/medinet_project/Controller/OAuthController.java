package com.example.medinet_project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@Controller
public class OAuthController {

    private final String URI_BASE = "/oauth2/authorization/";
    private final List<String> clients =
            List.of("Google");


    @GetMapping("/oauth2login")
    public String oAuth2LoginPage(Model model) {

        Map<String, String> clientUrls =
                clients.stream().collect(
                        toMap(identity(),
                                client -> URI_BASE + client.toLowerCase()));

        model.addAttribute("clientUrls", clientUrls);
        return "login";
    }
}
