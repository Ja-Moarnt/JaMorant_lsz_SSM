package com.JaMorant.SSM.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author:JaMorant
 * @time:2023/1/27 18:02
 * @explain:
 */
@ApiModel("openid")
@Data
public class Openid {
    @ApiModelProperty(value = "小程序open id")
    private String openId;
}
