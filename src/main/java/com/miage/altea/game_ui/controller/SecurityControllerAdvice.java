package com.miage.altea.game_ui.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class SecurityControllerAdvice {


    @ModelAttribute(value="user")
    Object principal(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}