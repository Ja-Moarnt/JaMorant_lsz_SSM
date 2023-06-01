package com.JaMorant.SSM.vo.thc;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Time;

/**
 * @author:JaMorant
 * @time:2023/1/27 17:15
 * @explain:
 */
@ApiModel("囤管员自填表单")
@Data
public class SaveTungyInfo {

    @ApiModelProperty(value = "小程序open id")
    private String openId;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "城市id")
    private Long chengshi_id;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "工作起始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time timeStart;

    @ApiModelProperty(value = "工作结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time timeEnd;

}
