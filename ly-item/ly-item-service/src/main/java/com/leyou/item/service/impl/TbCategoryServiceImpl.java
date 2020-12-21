package com.leyou.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.BeanHelper;
import com.leyou.item.dto.CategoryDTO;
import com.leyou.item.entity.TbCategory;
import com.leyou.item.mapper.TbCategoryMapper;
import com.leyou.item.service.TbCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 服务实现类
 * </p>
 *
 * @author DuoLai21
 * @since 2020-12-18
 */
@Service
public class TbCategoryServiceImpl extends ServiceImpl<TbCategoryMapper, TbCategory> implements TbCategoryService {

    @Override
    public List<CategoryDTO> findCategoryListById(Long parentId) {
        //构造查询条件
        QueryWrapper<TbCategory> queryWrapper = new QueryWrapper<>();
        //添加查询条件
        queryWrapper.eq("parent_id", parentId);
        //查询数据集合
        List<TbCategory> tbCategoryList = this.list(queryWrapper);
        //判断是否为空
        if (CollectionUtils.isEmpty(tbCategoryList)){
            throw  new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        //转换对象并返回
        return BeanHelper.copyWithCollection(tbCategoryList, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> findBrandIdByCategory(Long brandId) {
        List<TbCategory> tbCategoryList=  this.getBaseMapper().selectCategoryByBrandId(brandId);
        if (CollectionUtils.isEmpty(tbCategoryList)){
            throw  new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return BeanHelper.copyWithCollection(tbCategoryList, CategoryDTO.class);
    }
}
