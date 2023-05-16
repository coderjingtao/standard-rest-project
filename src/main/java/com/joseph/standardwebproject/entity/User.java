package com.joseph.standardwebproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
//@JsonIgnoreProperties({"id","password"})
public class User {
    private int id;
    private String name;
    private int age;
    @JsonIgnore
    private String password;
}
