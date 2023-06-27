package main.java.jpabasic.reserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testcontroller {

    @GetMapping("/index")
    public String index() {
        System.out.println(" 컨트롤러 작동 ");
        return "/";
    }
}
