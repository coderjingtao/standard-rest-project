package com.joseph.standardwebproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author joseph
 * @create 2023-08-20
 */
@Entity
@Table(name = "item")
@Data
public class Item {
    @Id
    private long id;

    private String name;

    private String url;
}
