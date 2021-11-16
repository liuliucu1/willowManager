package com.example.test.dao;

import com.example.test.models.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface InfoMapper {
    @Insert("insert into info(idcard,phone,name,account,mail,address) values(#{idcard},#{phone},#{name},#{account},#{mail},#{address})")
    void add(@Param("idcard")String idcard,@Param("phone")String phone,@Param("name")String name,@Param("account")String account,@Param("mail")String mail,@Param("address")String address);

    @Select("SELECT * FROM info")
    List<User> query();

    @Update("Update info SET phone = #{phone} WHERE account = #{account}")
    void updatePhone(@Param("phone") String phone, @Param("account") String account);//可以的话，就还要看一下是判断修改的条件是什么

    @Update("Update info SET address = #{address} WHERE account = #{account}")
    void updateAddress(@Param("address") String address, @Param("account") String account);

    @Update("Update info SET mail = #{mail} WHERE account = #{account}")
    void updateMail(@Param("mail") String mail, @Param("account") String account);

    @Select("select * from info where account = #{account}")
    User getUser(@Param("account") String account);

    @Select("select name from info where account = #{account}")
    String getName(@Param("account") String account);

    @Select("select mail from info where account = #{account}")
    String getMail(@Param("account") String account);
}
