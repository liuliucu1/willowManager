package com.example.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;


@Component
@Mapper
public interface ManageMapper {
    @Update("update `startclass` set isStart = #{isStart},startTime = #{startTime},endTime = #{endTime} where id = #{id}")
    public void startClass(@Param("isStart") int isStart,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("id") int id);
    @Update("update `startclass` set isStart = #{isStart} where id = #{id}")
    public void closeClass(@Param("isStart") int isStart, @Param("id")int id);
    @Select("select isStart from `startclass` where id = #{id}")
    public int getChoice(@Param("id")int id);
    @Select("select startTime from `startclass` where id = #{id}")
    public String getStartTime(int id);
    @Select("select endTime from `startclass` where id = #{id}")
    public String getEndTime(int id);
}
