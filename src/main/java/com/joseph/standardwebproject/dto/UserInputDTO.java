package com.joseph.standardwebproject.dto;

import com.joseph.standardwebproject.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
public class UserInputDTO {
    private int id;
    @NotNull
    private String name;
    private int age;
    private String password;

    public User convertToUser(){
        UserInputDTOConvert userInputDTOConvert = new UserInputDTOConvert();
        User user = userInputDTOConvert.doForward(this);
        return user;
    }
    public UserInputDTO convertFromUser(User user){
        UserInputDTOConvert userInputDTOConvert = new UserInputDTOConvert();
        UserInputDTO userInputDTO = userInputDTOConvert.doBackward(user);
        return userInputDTO;
    }

    private static class UserInputDTOConvert extends DTOConverter<UserInputDTO, User>{

        @Override
        protected User doForward(UserInputDTO userInputDTO) {
            User user = new User();
            BeanUtils.copyProperties(userInputDTO,user);
            return user;
        }

        @Override
        protected UserInputDTO doBackward(User user) {
            UserInputDTO userInputDTO = new UserInputDTO();
            BeanUtils.copyProperties(user,userInputDTO);
            return userInputDTO;
        }
    }
}
