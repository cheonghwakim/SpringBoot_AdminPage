package com.study.admin.repository;

import com.study.admin.AdminApplicationTests;
import com.study.admin.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemRepositoryTest extends AdminApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = new Item();
        item.setStatus("UNREGISTERED");
        item.setName("삼성 노트북");
        item.setTitle("삼성 노트북 A100");
        item.setContent("2019년형 노트북 입니다.");
        item.setPrice(900000);
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("partner01");
        item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);
        assertNotNull(newItem);
    }

    @Test
    public void read(){
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);
        assertTrue(item.isPresent());
    }

    @Test
    public void update(){


    }

    @Test
    public void delete(){


    }
}



