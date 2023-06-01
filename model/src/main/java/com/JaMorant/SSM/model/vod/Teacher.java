package com.JaMorant.SSM.model.vod;

import com.JaMorant.SSM.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "司机实体类")
@TableName("teacher")
public class Teacher extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "司机姓名")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "司机简介")
	@TableField("intro")
	private String intro;


	@ApiModelProperty(value = "司机头像")
	@TableField("avatar")
	private String avatar;

	@ApiModelProperty(value = "司机电话")
	@TableField("phone")
	private String phone;

	@ApiModelProperty(value = "司机密码")
	@TableField("password")
	private String password;

	@ApiModelProperty(value = "小程序open id")
	@TableField("open_id")
	private String openId;

}
