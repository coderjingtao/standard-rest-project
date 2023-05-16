package com.joseph.standardwebproject.service;

import com.joseph.standardwebproject.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUserById(int id){
        User user = new User();
        user.setId(id);
        user.setName("Joseph");
        if(id == 0){
            return null;
        }
        return user;
    }

    public User addUser(User user){
        user.setId(1);
        return user;
    }
}
