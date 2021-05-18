package com.example.demo;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    //@ResponseBody annotation returns string object => Instead of it, we can make @RestController
    @GetMapping("/hello")
    @ResponseBody
    public Greeting hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/sort")
    @ResponseBody
    public Integer[] sort(@RequestParam(value = "input") Integer[] input) {
        Arrays.sort(input);
        return input;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


}