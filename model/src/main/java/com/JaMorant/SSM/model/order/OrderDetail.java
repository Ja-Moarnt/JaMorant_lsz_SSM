package com.JaMorant.SSM.model.order;

import com.JaMorant.SSM.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(description = "订单详情")
@TableName("order_detail")
public class OrderDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "商品id")
	@TableField("goods_id")
	private Long goodsId;

	@ApiModelProperty(value = "商品名称")
	@TableField("goods_name")
	private String goodsName;

	@ApiModelProperty(value = "商品图片")
	@TableField("cover")
	private String cover;

	@ApiModelProperty(value = "订单编号")
	@TableField("order_id")
	private Long orderId;


	@ApiModelProperty(value = "单价")
	@TableField("price")
	private BigDecimal price;

	@ApiModelProperty(value = "购买数量")
	@TableField("buy_count")
	private Integer buyCount;

}
