package com.smartedhub_generator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SMARTEDHUB
 * @description:
 * @author: Junxian Cai
 **/

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String Hello() {
        return "Hello! Test successfully!";
    }
}
