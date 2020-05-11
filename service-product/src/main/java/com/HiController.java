package com;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hi")
public class HiController {


    @Value(value ="${server.port}")
    private String port;

    @GetMapping("/hi")
    public String hi() {

        return "hi spring cloud "+port;

    }
}
