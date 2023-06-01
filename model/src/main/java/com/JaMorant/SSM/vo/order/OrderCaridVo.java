package com.JaMorant.SSM.vo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderCaridVo {

//	@ApiModelProperty(value = "商品id")
//	private Long goodsId;

//	@ApiModelProperty(value = "优惠券id")
//	private Long couponId;

	@ApiModelProperty(value = "车辆id")
	private Long carId;

	@ApiModelProperty(value = "orderid")
	private Long orderId;
}

