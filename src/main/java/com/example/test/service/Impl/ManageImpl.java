package com.example.test.service.Impl;

import com.example.test.dao.ManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageImpl {
    @Autowired
    ManageMapper n;


    public void setStartTime(String startTime, String endTime) {

        n.startClass(1, startTime, endTime, 1);
    }

    public String getStartTime(){
       return n.getStartTime(1);
    }
    public String getEndTime(){
        return n.getEndTime(1);
    }
    public void End(){
        n.closeClass(0,1);
    }
    public int getChoice(){
        return n.getChoice(1);
    }
}
