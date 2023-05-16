package com.joseph.standardwebproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Course {

    @Id
    @JsonProperty("course_id")
    private long id;

    @Size(min = 4,max = 20)
    @JsonProperty("course_name")
    private String name;

    @NotBlank
    @JsonProperty("course_teacher")
    private String author;
}
