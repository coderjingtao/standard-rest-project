package com.joseph.standardwebproject.entity;

import com.joseph.standardwebproject.datamask.DataMasking;
import com.joseph.standardwebproject.datamask.DataMaskingFunc;
import lombok.Data;

@Data
//@JsonIgnoreProperties({"id","password"})
public class User {
    private int id;
    private String name;
    private int age;
    //@JsonIgnore
    @DataMasking(maskFunc = DataMaskingFunc.ALL_MASK)
    private String password;
}
