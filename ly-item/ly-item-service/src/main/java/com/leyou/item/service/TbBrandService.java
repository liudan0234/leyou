package com.leyou.item.service;

import com.leyou.common.vo.PageResult;
import com.leyou.item.dto.BrandDTO;
import com.leyou.item.entity.TbBrand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务类
 * </p>
 *
 * @author DuoLai21
 * @since 2020-12-18
 */
public interface TbBrandService extends IService<TbBrand> {

    PageResult<BrandDTO> findBrandByPage(String key, Integer page, Integer rows, String sortBy, boolean desc);

    void savaBrandByCategory(TbBrand tbBrand, List<Long> cids);

    void updateBrand(TbBrand tbBrand, List<Long> cids);
}
