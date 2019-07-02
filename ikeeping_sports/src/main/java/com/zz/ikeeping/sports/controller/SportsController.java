package com.zz.ikeeping.sports.controller;

import com.zz.ikeeping.sports.service.SportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SportsController {
    @Autowired
    private SportsService service;

    @GetMapping("sports/calculate.do")
    public String calBMI(@RequestParam("weight") float weight, @RequestParam("height") float height){
        return service.calBMI(height, weight);
    }
}
