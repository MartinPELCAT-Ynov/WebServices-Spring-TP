package com.ynov.tpspring.controllers;

import com.ynov.tpspring.entities.Center;
import com.ynov.tpspring.services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/centers")
public class CenterController {

    @Autowired
    private CenterService centerService;


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Center createCenter(@RequestBody Center center) {
        return centerService.createCenter(center);
    }


}
