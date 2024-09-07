package com.example.wss.service;

import com.example.wss.mapper.UserMapper;
import com.example.wss.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    public void saveUser(User user) {
        // Save the new user in the database
        userMapper.insertUser(user);
    }
}