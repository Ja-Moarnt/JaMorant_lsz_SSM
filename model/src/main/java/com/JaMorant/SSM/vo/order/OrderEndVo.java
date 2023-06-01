package com.JaMorant.SSM.vo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderEndVo {

////	@ApiModelProperty(value = "商品id")
////	private Long goodsId;
//
////	@ApiModelProperty(value = "优惠券id")
////	private Long couponId;
//
//	@ApiModelProperty(value = "用户id")
//	private Long userId;

	@ApiModelProperty(value = "车辆id")
	private Long carId;

	@ApiModelProperty(value = "地理经度")
	private BigDecimal longitude;

	@ApiModelProperty(value = "地理纬度")
	private BigDecimal latitude;
}

