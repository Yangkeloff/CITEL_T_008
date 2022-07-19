package com.example.management.controller;

import com.example.management.service.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddController {

    @Autowired
    AddService addService;

    @RequestMapping("add")
    public String add(@RequestBody String postJson) {
        addService.add(postJson);
        return "success";
    }
}
