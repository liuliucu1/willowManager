package com.example.test.controllers;

import com.example.test.service.Impl.ManageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080/", maxAge = 3600)
public class ManageController {
    @Autowired
    ManageImpl manage;

    @RequestMapping(value = "/setStart", method = RequestMethod.GET)
    public void setManage(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
        manage.setStartTime(startTime,endTime);
    }

    @RequestMapping(value = "/endChoose",method = RequestMethod.GET)
    public void end(){
        manage.End();
    }
}
