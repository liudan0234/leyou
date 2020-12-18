package com.leyou.item.service.impl;

import com.leyou.item.entity.Item;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ItemService {
    public Item savaItem(Item item){
        int id = new Random().nextInt(100);
        item.setId(id);
        return item;
    }
}
