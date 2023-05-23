package com.joseph.standardwebproject.controller;

import com.joseph.standardwebproject.common.response.CommonResponse;
import com.joseph.standardwebproject.dto.UserInputDTO;
import com.joseph.standardwebproject.entity.User;
import com.joseph.standardwebproject.exception.BusinessResponseEnum;
import com.joseph.standardwebproject.service.UserService;
import jakarta.annotation.Resource;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    //@GetMapping("/users/{userId}")
    //public CommonResponse<User> findUserById(@PathVariable int userId){
    //    User user = userService.getUserById(userId);
    //    BusinessResponseEnum.USER_NOT_FOUND.assertNotNull(user);
    //    return new CommonResponse(user);
    //}

    @GetMapping("/users/{username}")
    @PreAuthorize("hasRole('USER') and #username == authentication.name")
    @PostAuthorize("returnObject.code == 200")
    public CommonResponse<User> findUserByUsername(@PathVariable String username){
        log.info("input username = {}",username);
        User user = new User();
        user.setId(123456);
        user.setName("Oscar");
        user.setAge(8);

        BusinessResponseEnum.USER_NOT_FOUND.assertNotNull(user);
        return new CommonResponse(user);
    }

    @GetMapping("/users")
    @RolesAllowed({"ADMIN","USER"})
    public CommonResponse<List<User>> findAllUsers(){
        User user1 = new User();
        user1.setId(1);
        user1.setName("Joseph");
        user1.setPassword("123456");
        user1.setAge(30);

        User user2 = new User();
        user2.setId(2);
        user2.setName("Josh");
        user2.setPassword("09876");
        user2.setAge(40);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        return new CommonResponse<>(users);
    }

    @PostMapping("/users")
    @Secured({"ADMIN,USER"})
    public UserInputDTO addUser(@Valid UserInputDTO userInputDTO){
        User user = userInputDTO.convertToUser();
        User addedUser = userService.addUser(user);
        UserInputDTO result = userInputDTO.convertFromUser(addedUser);
        return result;
    }
}
