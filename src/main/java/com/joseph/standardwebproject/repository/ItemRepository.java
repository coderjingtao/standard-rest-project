package com.joseph.standardwebproject.repository;

import com.joseph.standardwebproject.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author joseph
 * @create 2023-08-20
 */
@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
}
