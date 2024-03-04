package com.example.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblog.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Insert("insert INTO User(loginName, password, createTime) values(#{loginName},#{password},#{createTime})")
    int Register(User user);

    @Select("select * from User where loginName=#{loginName}")
    User GetUser(String loginName);
}
