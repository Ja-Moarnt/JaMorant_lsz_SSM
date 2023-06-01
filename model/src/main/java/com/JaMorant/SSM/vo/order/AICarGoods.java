package com.JaMorant.SSM.vo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:JaMorant
 * @time:2023/4/12 20:19
 * @explain:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AICarGoods {

    @ApiModelProperty(value = "商品id")
    private Long goodsId;

    @ApiModelProperty(value = "车辆id")
    private Long carId;

    @ApiModelProperty(value = "车牌号")
    private String CarName;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品图")
    private String goodsImg;

}
