package com.leyou.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.BeanHelper;
import com.leyou.common.vo.PageResult;
import com.leyou.item.dto.BrandDTO;
import com.leyou.item.entity.TbBrand;
import com.leyou.item.entity.TbCategory;
import com.leyou.item.entity.TbCategoryBrand;
import com.leyou.item.mapper.TbBrandMapper;
import com.leyou.item.service.TbBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leyou.item.service.TbCategoryBrandService;
import com.leyou.item.service.TbCategoryService;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务实现类
 * </p>
 *
 * @author DuoLai21
 * @since 2020-12-18
 */
@Service
public class TbBrandServiceImpl extends ServiceImpl<TbBrandMapper, TbBrand> implements TbBrandService {

    @Override
    public PageResult<BrandDTO> findBrandByPage(String key, Integer page, Integer rows, String sortBy, boolean desc) {

        //构造分页的两个参数
        Page<TbBrand> bPage = new Page<>(page, rows);
        //构造器,  添加构造查询条件
        QueryWrapper<TbBrand> queryWrapper = new QueryWrapper<>();

        //判断不为空
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.lambda().like(TbBrand::getId, key).or().like(TbBrand::getLetter, key);
        }
        //添加排序
        if (!StringUtils.isEmpty(desc)) {
            if (desc) {
                queryWrapper.orderByDesc(sortBy);
            } else {
                queryWrapper.orderByAsc(sortBy);
            }
        }
        //查询分页对象
        IPage<TbBrand> brandIPage = this.page(bPage, queryWrapper);
        List<TbBrand> items = brandIPage.getRecords();
        if (CollectionUtils.isEmpty(items)) {
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        //转换对象
        List<BrandDTO> brandDTOList = BeanHelper.copyWithCollection(items, BrandDTO.class);
        PageResult<BrandDTO> pageResult = new PageResult<>(brandDTOList, brandIPage.getTotal(), brandIPage.getPages());
        return pageResult;
    }
    @Autowired
    private TbCategoryBrandService categoryBrandService;
    @Override
    public void savaBrandByCategory(TbBrand tbBrand, List<Long> cids) {
        boolean save = this.save(tbBrand);
        if (!save){
            throw new LyException(ExceptionEnum.BRAND_SAVA_ERROR);
        }
        Long id = tbBrand.getId();
        //保存中间表
        ArrayList<TbCategoryBrand> CategoryBrands = new ArrayList<>();
        for (Long cid : cids) {
            TbCategoryBrand tbCategoryBrand = new TbCategoryBrand();
            tbCategoryBrand.setBrandId(id);
            tbCategoryBrand.setCategoryId(cid);
            CategoryBrands.add(tbCategoryBrand);
        }
        //保存
        boolean b = categoryBrandService.saveBatch(CategoryBrands);
        if (!b){
            throw new LyException(ExceptionEnum.BRAND_SAVA_ERROR);
        }
    }
    @Override
    public void updateBrand(TbBrand tbBrand, List<Long> cids) {
        Long brandId = tbBrand.getId();
        boolean b = this.updateById(tbBrand);
        if (!b){
            throw new LyException(ExceptionEnum.BRAND_SAVA_ERROR);
        }

        //删除中间表
        QueryWrapper<TbCategoryBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbCategoryBrand::getBrandId,brandId);
        boolean re = categoryBrandService.remove(queryWrapper);
        if (!re){
           throw new LyException(ExceptionEnum.BRAND_UPDATE_ERROR);
        }
        //新增中间表数据
        ArrayList<TbCategoryBrand> tbCategoryBrands = new ArrayList<>();
        for (Long cid : cids) {
            TbCategoryBrand tbCategoryBrand = new TbCategoryBrand();
            tbCategoryBrand.setBrandId(brandId);
            tbCategoryBrand.setCategoryId(cid);
            tbCategoryBrands.add(tbCategoryBrand);
        }
        boolean batch = categoryBrandService.saveBatch(tbCategoryBrands);
        if (!batch){
            throw new LyException(ExceptionEnum.BRAND_UPDATE_ERROR);
        }

    }
}
