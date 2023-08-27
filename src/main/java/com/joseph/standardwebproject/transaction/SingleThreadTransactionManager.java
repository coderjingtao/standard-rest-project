package com.joseph.standardwebproject.transaction;

import com.joseph.standardwebproject.repository.ItemDetailRepository;
import com.joseph.standardwebproject.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author joseph
 * @create 2023-08-20
 */
@Component
@RequiredArgsConstructor
public class SingleThreadTransactionManager {

    private final ItemRepository itemRepository;
    private final ItemDetailRepository itemDetailRepository;

    @Transactional
    public void removeMultipleTableRecords(Long id){
        itemRepository.deleteById(id);
        itemDetailRepository.deleteById(id);
    }

}
