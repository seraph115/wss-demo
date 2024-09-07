package com.example.wss.mapper;

import com.example.wss.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findUserByUsername(String username);

    @Insert("INSERT INTO users (username, password, role) VALUES (#{username}, #{password}, #{role})")
    void insertUser(User user);
}