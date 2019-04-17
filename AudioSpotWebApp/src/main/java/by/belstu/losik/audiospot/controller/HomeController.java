package by.belstu.losik.audiospot.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ComponentScan("by.belstu.losik.audiospot")
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public String showLandingPage() {
        return "redirect:/tracks";
    }
}
