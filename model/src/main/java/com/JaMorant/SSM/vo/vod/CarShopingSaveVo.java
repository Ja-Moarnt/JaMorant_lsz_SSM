package com.JaMorant.SSM.vo.vod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author helen
 * @since 2020/6/6
 */
@ApiModel("车辆商品补货基本信息")
@Data
public class CarShopingSaveVo {

    @ApiModelProperty(value = "货物id")
    private Long goodsId;

    @ApiModelProperty(value = "车id")
    private Long carId;

}
