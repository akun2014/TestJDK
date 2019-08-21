package com.ownerkaka.testjdk.spring.web.controller;


import com.ownerkaka.testjdk.support.service.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {

    @Autowired
    BarService barService;

    @Override
    public String toString() {
        barService.bar("success");
        return "success";
    }
}
