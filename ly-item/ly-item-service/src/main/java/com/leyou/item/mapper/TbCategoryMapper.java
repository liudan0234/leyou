package com.leyou.item.mapper;

import com.leyou.item.entity.TbCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.print.attribute.standard.MediaSize;
import java.util.List;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 Mapper 接口
 * </p>
 *
 * @author DuoLai21
 * @since 2020-12-18
 */
public interface TbCategoryMapper extends BaseMapper<TbCategory> {

    @Select("select a.* from tb_category a  , tb_category_brand b where a.id=b.category_id and b.brand_id=#{brandId}")
    List<TbCategory> selectCategoryByBrandId(@Param("brandId") Long brandId);

}
