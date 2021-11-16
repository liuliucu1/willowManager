package com.example.test.controllers;

import com.example.test.config.Result;
import com.example.test.dao.InfoMapper;
import com.example.test.dao.PersonMapper;
import com.example.test.service.Impl.InfoServiceImpl;
import com.example.test.util.GJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080/", maxAge = 3600)
public class UserController {
    @Autowired
    InfoMapper infoMapper;
    @Autowired
    PersonMapper personMapper;

    @Autowired
    InfoServiceImpl infoService;

    @RequestMapping(value = "/fixInfo", method = RequestMethod.POST)
    public Result fixInfo(@RequestParam("phone") String phone, @RequestParam("token") String token, @RequestParam("address") String address, @RequestParam("mail") String mail) {
        boolean flag1=true;
        boolean flag2=true;
        boolean flag3=true;
        String A = "";
        if (phone != null) {
           flag1= infoService.updatePhone(phone, token);
           if (!flag1){
               A=A+"2";
           }
        }
        if (address != null) {
            flag2=infoService.updateAddress(address,token);

        }
        if (mail != null) {
         flag3= infoService.updateMail(mail,token);
         if (!flag3){
             A=A+"4";
         }
        }
        if (flag1&&flag2&&flag3){
            return Result.success("修改成功");
        }else {
            return Result.fail(A);
        }
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getInfo(@RequestParam("token") String token) {
        return GJson.toJson(infoService.getUser(token));
    }
}
