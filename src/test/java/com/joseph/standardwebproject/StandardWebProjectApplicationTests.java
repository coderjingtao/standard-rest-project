package com.joseph.standardwebproject;

import com.joseph.standardwebproject.dto.CourseDTO;
import com.joseph.standardwebproject.dto.StudentDTO;
import com.joseph.standardwebproject.exception.BusinessResponseEnum;
import com.joseph.standardwebproject.service.BusinessExceptionEnum;
import com.joseph.standardwebproject.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.MessageFormat;

@SpringBootTest
class StandardWebProjectApplicationTests {

    @Test
    void contextLoads() {
        Object license = null;
        BusinessResponseEnum.LICENSE_NOT_FOUND.assertNotNull(license);
    }

    @Test
    void testMessageFormat(){
        String text = MessageFormat.format("you are about to delete {0} rows, {1} columns",5,10);
        System.out.println(text);
    }

    @Test
    void testMyException(){
        User user = null;
        BusinessExceptionEnum.USER_NOT_FOUND.assertNotNull(user);
    }

    @Test
    void testLombok(){
        CourseDTO courseDTO = CourseDTO.of("AWS").setAuthor("Joseph");

        StudentDTO studentDTO = StudentDTO.builder()
                .name("Joseph")
                .age(24)
                .build();
    }

}
