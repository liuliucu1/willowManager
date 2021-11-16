package com.example.test.dao;

import com.example.test.models.Vertify;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PersonMapper {
    @Insert("insert into `vertify`(idcard,account,password,identity) values(#{idcard},#{account},#{password},#{identity})")
    void add(@Param("idcard") String idcard, @Param("account") String account, @Param("password") String password, @Param("identity") int identity);


    @Update("update `vertify` set password = #{password} where account = #{account}")
    void change_p(@Param("password") String password, @Param("account") String account);//修改密码；

    @Select("select * from `vertify` where account = #{account} and password = #{password}")
    Vertify getV(String account, String password);

    @Insert("Insert into `user`(account,token) values(#{account},#{token})")
    void insert_token(String account, String token);

    @Select("select account from `user` where token = #{token}")
    String getAccount(String token);

    @Select("select token from `user` where account = #{account}")
    String getToken(String account);

}
