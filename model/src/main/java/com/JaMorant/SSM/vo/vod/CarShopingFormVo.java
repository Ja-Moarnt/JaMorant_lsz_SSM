package com.JaMorant.SSM.vo.vod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author helen
 * @since 2020/6/6
 */
@ApiModel("车辆商品基本信息")
@Data
public class CarShopingFormVo {
    @ApiModelProperty(value = "id")
    private Long Id;

    @ApiModelProperty(value = "货物id")
    private Long goodsId;

    @ApiModelProperty(value = "商品分类ID")
    private Long subjectId;

    @ApiModelProperty(value = "商品分类父级ID")
    private Long subjectParentId;

    @ApiModelProperty(value = "商品名称")
    private String title;

    @ApiModelProperty(value = "车id")
    private Long carId;

    @ApiModelProperty(value = "剩余货物数量")
    private Integer shengCount;

    @ApiModelProperty(value = "历史售出数量")
    private Integer buyCount;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
}
