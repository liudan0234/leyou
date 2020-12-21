package com.leyou.item.controller;

import com.leyou.common.vo.PageResult;
import com.leyou.item.dto.BrandDTO;
import com.leyou.item.dto.CategoryDTO;
import com.leyou.item.entity.TbBrand;
import com.leyou.item.service.TbBrandService;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private TbBrandService brandService;

    @GetMapping("/page")
    public @ResponseBody PageResult<BrandDTO> page(@RequestParam(name = "key") String key, @RequestParam(name = "page",defaultValue = "1") Integer page,@RequestParam(name = "rows",defaultValue = "5") Integer rows, @RequestParam(name = "sortBy") String sortBy, @RequestParam(name = "desc") boolean desc) {
        PageResult<BrandDTO> pageResult= brandService.findBrandByPage(key,page,rows,sortBy,desc);
        return pageResult;
    }

    @PostMapping()
    public ResponseEntity<Void> sava(TbBrand tbBrand,@RequestParam(name = "cids") List<Long> cids){
        brandService.savaBrandByCategory(tbBrand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping()
    public ResponseEntity<Void> UpdateBrand(TbBrand tbBrand,@RequestParam(name = "cids") List<Long> cids){
        brandService.updateBrand(tbBrand,cids);
        return ResponseEntity.noContent().build();
    }
}
