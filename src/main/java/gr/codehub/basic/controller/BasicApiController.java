package gr.codehub.basic.controller;

import gr.codehub.basic.model.Product;
import gr.codehub.basic.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicApiController {


    @GetMapping("/admin")
    public User getUser(){
        User user = new User();
        return user;
    }

    @GetMapping("/product")
    public Product getProduct(){
        Product product = new Product();
        return product;
    }


    @GetMapping("/ping")
    public String getPing(){
        return "It works";
    }

}
