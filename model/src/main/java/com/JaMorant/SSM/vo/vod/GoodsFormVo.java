package com.JaMorant.SSM.vo.vod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author helen
 * @since 2020/6/6
 */
@ApiModel("商品基本信息")
@Data
public class GoodsFormVo {

    @ApiModelProperty(value = "商品ID")
    private Long id;


    @ApiModelProperty(value = "商品分类ID")
    private Long subjectId;

    @ApiModelProperty(value = "商品分类父级ID")
    private Long subjectParentId;

    @ApiModelProperty(value = "商品名称")
    private String title;

    @ApiModelProperty(value = "商品单价销售价格，设置为0则免费")
    private BigDecimal price;


    @ApiModelProperty(value = "商品封面图片路径")
    private String cover;

    @ApiModelProperty(value = "商品简介")
    private String description;
}
