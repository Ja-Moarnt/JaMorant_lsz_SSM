package com.JaMorant.SSM.vo.vod;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author:JaMorant
 * @time:2022/12/30 15:05
 * @explain:
 */
@Data
public class GoodsQueryVo {
    @ApiModelProperty(value = "商品单价销售价格下限，设置为0则免费")
    private BigDecimal price1;
    @ApiModelProperty(value = "商品单价销售价格上限，设置为0则免费")
    private BigDecimal price2;

    @ApiModelProperty(value = "课程专业ID")
    private Long subjectId;

    @ApiModelProperty(value = "课程专业父级ID")
    private Long subjectParentId;

    @ApiModelProperty(value = "课程标题")
    private String title;

}
