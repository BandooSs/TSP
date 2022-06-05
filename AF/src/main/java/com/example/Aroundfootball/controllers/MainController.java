package com.example.Aroundfootball.controllers;

import com.example.Aroundfootball.models.User;
import com.example.Aroundfootball.models.enums.Role;
import com.example.Aroundfootball.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserRepository userRepository;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addnewUser(User user, Map<String,Object> model){
        User userFromDb=userRepository.findByUsername(user.getUsername());
        if(userFromDb!=null){
            model.put("message","User exists!!!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }

}
