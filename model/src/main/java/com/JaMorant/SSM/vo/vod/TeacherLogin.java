package com.JaMorant.SSM.vo.vod;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeacherLogin {


	@ApiModelProperty(value = "电话")
	private String phone;

	@ApiModelProperty(value = "密码")
	private String password;




}

