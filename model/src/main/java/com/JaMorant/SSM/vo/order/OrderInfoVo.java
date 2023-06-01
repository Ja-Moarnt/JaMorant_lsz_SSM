package com.JaMorant.SSM.vo.order;

import com.JaMorant.SSM.model.order.OrderDetail;
import com.JaMorant.SSM.model.order.OrderInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderInfoVo extends OrderInfo {

	@ApiModelProperty(value = "订单详情")
	private List<OrderDetail> orderDetails;



}

