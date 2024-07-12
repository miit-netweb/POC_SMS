package com.microservice.limits.controller;

import com.microservice.limits.bean.Limits;
import com.microservice.limits.configuration.LimitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

    @Autowired
    private LimitConfig limitConfig;
    @GetMapping("/limits")
    public Limits retrieveLimits(){
        return new Limits(limitConfig.getMinimum(),limitConfig.getMaximum());
    }
}
