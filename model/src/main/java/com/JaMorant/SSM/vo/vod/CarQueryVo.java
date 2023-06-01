package com.JaMorant.SSM.vo.vod;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CarQueryVo {

	@ApiModelProperty(value = "司机ID")
	private Long teacherId;
//
	@ApiModelProperty(value = "城市ID")
	private Long chengshiId;
//
//	@ApiModelProperty(value = "分类父级父级ID")
//	private Long subjectParentId;
//
//	@ApiModelProperty(value = "商品名称")
//	private String title;

//	@ApiModelProperty(value = "工作起始时间")
//	private Time timeStart;
//
//	@ApiModelProperty(value = "工作结束时间")
//	private Time timeEnd;

}

