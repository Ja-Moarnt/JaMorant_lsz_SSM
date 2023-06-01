package com.JaMorant.SSM.model.thc;

import com.JaMorant.SSM.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "补货单")
@TableName("purchase_info")
public class PurchaseInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "囤管员id")
	@TableField("tungy_id")
	private Long tungyId;

	@ApiModelProperty(value = "车id")
	@TableField("car_id")
	private Long carId;

	@ApiModelProperty(value = "补货单单交易编号（第三方支付用)")
	@TableField("out_trade_no")
	private String outTradeNo;

	@ApiModelProperty(value = "预约时间起始")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("start_time")
	private Date startTime;

	@ApiModelProperty(value = "预约时间截止")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("end_time")
	private Date endTime;


	@ApiModelProperty(value = "囤货单状态")
	@TableField("purchase_status")
	private String purchaseStatus;

}
