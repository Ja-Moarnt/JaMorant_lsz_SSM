package com.JaMorant.SSM.vo.thc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "补货单搜索")
public class PurchaseInfoQueryVo  {


	@ApiModelProperty(value = "囤管员id")
	private Long tungyId;

	@ApiModelProperty(value = "车id")
	private Long carId;


	@ApiModelProperty(value = "创建时间")
	private String createTimeBegin;

	@ApiModelProperty(value = "截止时间")
	private String createTimeEnd;


	@ApiModelProperty(value = "囤货单单状态")
	private String purchaseStatus;

}
