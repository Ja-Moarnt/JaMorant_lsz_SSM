package com.JaMorant.SSM.model.thc;

import com.JaMorant.SSM.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "囤管员")
@TableName("tungy")
public class Tungy extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "手机号")
	@TableField("phone")
	private String phone;

	@ApiModelProperty(value = "用户密码")
	@TableField("password")
	private String password;

	@ApiModelProperty(value = "用户姓名")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "昵称")
	@TableField("nick_name")
	private String nickName;

	@ApiModelProperty(value = "性别")
	@TableField("sex")
	private Integer sex;

	@ApiModelProperty(value = "头像")
	@TableField("avatar")
	private String avatar;

	@ApiModelProperty(value = "城市")
	@TableField("chengshi_id")
	private Long chengshiId;

	@ApiModelProperty(value = "详细地址")
	@TableField("address")
	private String address;
//	@ApiModelProperty(value = "0：未订阅 1：已订阅")
//	@TableField("subscribe")
//	private Integer subscribe;

	@ApiModelProperty(value = "小程序open id")
	@TableField("open_id")
	private String openId;

	@ApiModelProperty(value = "工作起始时间")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@TableField("time_start")
	private Time timeStart;

	@ApiModelProperty(value = "工作结束时间")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@TableField("time_end")
	private Time timeEnd;

	public Tungy(String openId) {
		this.openId = openId;
	}
}
