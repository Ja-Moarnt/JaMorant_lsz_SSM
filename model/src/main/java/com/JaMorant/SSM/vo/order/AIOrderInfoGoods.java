package com.JaMorant.SSM.vo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author:JaMorant
 * @time:2023/4/12 20:19
 * @explain:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIOrderInfoGoods {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "商品id")
    private Long goodsId;

    @ApiModelProperty(value = "车辆id")
    private Long carId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "车牌号")
    private String CarName;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "地理经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "地理纬度")
    private BigDecimal latitude;

    public AIOrderInfoGoods(Long userId, Long carId, BigDecimal longitude, BigDecimal latitude) {
        this.userId = userId;
        this.carId = carId;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
