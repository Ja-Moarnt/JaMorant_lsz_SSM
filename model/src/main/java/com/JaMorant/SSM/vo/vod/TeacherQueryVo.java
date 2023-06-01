package com.JaMorant.SSM.vo.vod;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class TeacherQueryVo {

	@ApiModelProperty(value = "司机姓名")
	private String name;

	@ApiModelProperty(value = "电话")
	private String phone;

	@ApiModelProperty(value = "入驻时间")
	private String joinDateBegin;

	@ApiModelProperty(value = "入驻时间")
	private String joinDateEnd;


}

