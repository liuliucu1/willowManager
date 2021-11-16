package com.example.test.service.Impl;

import com.example.test.config.Result;
import com.example.test.dao.InfoMapper;
import com.example.test.dao.PersonMapper;
import com.example.test.models.MUser;
import com.example.test.models.Vertify;
import com.example.test.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService {
    @Autowired
    PersonMapper personMapper;

    @Autowired
    InfoMapper infoMapper;

    //登陆失败返回0，学生成功返回1，老师成功返回2
    public MUser login(String u, String p) {

        Vertify m = personMapper.getV(u, p);

        if (m == null) {
            return null;
        } else if (m.getIdentity() == 0) {
            if (isUser(u) == null) {
                String token = TokenUtil.getToken(u, p);
                MUser m_user = new MUser(0, token);
                personMapper.insert_token(u, token);
                return m_user;
            } else {
                String s = personMapper.getToken(u);
                System.out.println(s);
                MUser m_user = new MUser(0, s);
                return m_user;
            }
        } else if (m.getIdentity() == 1) {

            if (isUser(u) == null) {
                String token = TokenUtil.getToken(u, p);
                MUser m_user = new MUser(1, token);
                personMapper.insert_token(u, token);
                return m_user;
            } else {
                MUser m_user = new MUser(1, personMapper.getToken(u));
                return m_user;
            }

        } else if (m.getIdentity() == 2) {
            if (isUser(u) == null) {
                String token = TokenUtil.getToken(u, p);
                MUser m_user = new MUser(2, token);
                personMapper.insert_token(u, token);
                return m_user;
            } else {
                MUser m_user = new MUser(2, personMapper.getToken(u));
                return m_user;
            }

        }
        return null;
    }

    public String isUser(String account) {
        try {
            if (personMapper.getToken(account).length() != 0) {
                return personMapper.getToken(account);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public Result changPass(String before, String want, String account) {
        if (before.compareTo(want) == 0) {
            return Result.getResult(2, "无需修改");
        } else {
            Vertify m = personMapper.getV(account, MD5Util.passwordHash(before));
            if (m == null) {
                return Result.fail("修改失败");
            } else {
                personMapper.change_p(MD5Util.passwordHash(want), account);
                return Result.success("修改成功");
            }
        }
    }

    public boolean findPassword(String account) {
        String uuid = UUID.randomUUID().toString();
        personMapper.change_p(MD5Util.passwordHash(uuid), account);
        if (isUser(account) == null) {
            return false;
        }
        String recipient = infoMapper.getMail(account);
        try {
            MailUtil.sendEmail(recipient, "willow找回密码", "willow用户，您的新密码是：" + uuid, null);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String addPerson(String idCard, String phone, String password, String mail, String address, String name, int identity) {
        if (IdcardUtil.validateIdCard18(idCard) && InfoCorr.isMobileNum(phone)) {
            String account = IdUtil.generateId();
            if (isUser(account) == null) {
                String pa = MD5Util.MD5(password);
                personMapper.add(idCard, account, pa, identity);
                infoMapper.add(idCard, phone, name, account, mail, address);
            } else {
                while (isUser(account) == null) {
                    account = IdUtil.generateId();
                }
                personMapper.add(idCard, account, password, identity);
                infoMapper.add(idCard, phone, name, account, mail, address);

            }
            return account;
        } else {
            return "-1";
        }
    }

}
