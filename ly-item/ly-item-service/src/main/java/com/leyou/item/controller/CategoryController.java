package com.leyou.item.controller;

import com.leyou.item.dto.CategoryDTO;
import com.leyou.item.service.TbCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private TbCategoryService categoryService;

    /**
     * 根据id查询 list列表
     * @param parentId
     * @return
     */
    @GetMapping("/of/parent")
    public  ResponseEntity<List<CategoryDTO>> findCategoryListById(@RequestParam(name = "pid") Long parentId){
        return ResponseEntity.ok(categoryService.findCategoryListById(parentId));
    }

    @GetMapping("/of/brand")
    public @ResponseBody ResponseEntity<List<CategoryDTO>> findBrandIdByCategory(@RequestParam(name = "id") Long brandId){
        List<CategoryDTO> list= categoryService.findBrandIdByCategory(brandId);
        return ResponseEntity.ok(list);
    }

}
