package com.leyou.item.service;

import com.leyou.item.dto.CategoryDTO;
import com.leyou.item.entity.TbCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 服务类
 * </p>
 *
 * @author DuoLai21
 * @since 2020-12-18
 */
public interface TbCategoryService extends IService<TbCategory> {
    /**
     * 根据父类id查询 集合信息
     * @param parentId
     * @return
     */
    List<CategoryDTO> findCategoryListById(Long parentId);

    List<CategoryDTO> findBrandIdByCategory(Long brandId);
}
