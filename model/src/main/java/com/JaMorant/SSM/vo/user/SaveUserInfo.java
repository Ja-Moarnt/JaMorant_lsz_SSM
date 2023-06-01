package com.JaMorant.SSM.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author:JaMorant
 * @time:2023/1/27 17:15
 * @explain:
 */
@ApiModel("用户信息自填表单")
@Data
public class SaveUserInfo {

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
    private Long chengshiId;



}
