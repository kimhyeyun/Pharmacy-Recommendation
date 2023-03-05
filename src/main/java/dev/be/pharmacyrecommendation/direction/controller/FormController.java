package dev.be.pharmacyrecommendation.direction.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class FormController {

    @GetMapping("/")
    public String main(){
        return "main";
    }
}
