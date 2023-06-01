package com.JaMorant.SSM.vo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderFormVo  {

//	@ApiModelProperty(value = "商品id")
//	private Long goodsId;

//	@ApiModelProperty(value = "优惠券id")
//	private Long couponId;

	@ApiModelProperty(value = "用户id")
	private Long userId;

	@ApiModelProperty(value = "车辆id")
	private Long carId;

	@ApiModelProperty(value = "优惠券领取表id")
	private Long couponUseId;
}

