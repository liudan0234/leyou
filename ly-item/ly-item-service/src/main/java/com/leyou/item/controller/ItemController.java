package com.leyou.item.controller;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.entity.Item;
import com.leyou.item.service.impl.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/item")
    public ResponseEntity<Item> savaItem(Item item){
        //判断价格是否为空
        if(item.getPrice() == null){
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            //throw new RuntimeException("价格不能为空");
            throw new LyException(ExceptionEnum.PRICE_CANNOT_BE_NULL);
        }
        Item result = itemService.savaItem(item);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
