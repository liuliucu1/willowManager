package com.example.test.controllers;

import com.alibaba.fastjson.JSONObject;
import com.example.test.config.Result;
import com.example.test.models.MUser;
import com.example.test.service.Impl.LoginService;
import com.example.test.util.GJson;
import com.example.test.util.MD5Util;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {
    @Autowired
    LoginService k;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @CrossOrigin
    public String login(@RequestParam("account") String account, @RequestParam("password") String password) {
        MUser mUser = k.login(account, MD5Util.passwordHash(password));
        if (mUser == null) {
            return GJson.toJson(Result.fail("失败"));
        } else {
            return JSONObject.toJSONString(mUser);
        }
    }


    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @CrossOrigin
    public Result changePassword(@RequestParam("account") String account, @RequestParam("oldP") String oldP, @RequestParam("secondP") String secondP) {
        return k.changPass(oldP, secondP, account);
    }

    @RequestMapping(value = "/findPassword", method = RequestMethod.GET)
    @CrossOrigin
    public Result findpassword(@RequestParam("account") String account) {
        boolean flag = k.findPassword(account);
        if (flag) {
            return Result.success("已发送");
        } else {
            return Result.fail("邮箱失效");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @CrossOrigin
    public Result register(@RequestParam("idCard") String idCard, @RequestParam("phone") String phone, @RequestParam("password") String password,@RequestParam("mail") String mail,@RequestParam("address") String address,@RequestParam("name") String name,@RequestParam("identify") int identify) {
        //同一个idcard和phone不能同时注册
        String r =k.addPerson(idCard, phone, password, mail, address, name, identify);
        if (r.equals("-1")){
            return Result.fail("信息格式有误");
        }
        return Result.success(k.addPerson(idCard, phone, password, mail, address, name, identify));
    }

}
