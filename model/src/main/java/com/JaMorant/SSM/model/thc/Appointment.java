package com.JaMorant.SSM.model.thc;

import com.JaMorant.SSM.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author:JaMorant
 * @time:2023/2/24 19:31
 * @explain:
 */
@Data
@ApiModel(description = "预约信息的实体类")
//@TableName("appointment")
public class Appointment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "司机姓名")
    @TableField("driver_name")
    private String driverName; // 司机姓名

    @ApiModelProperty(value = "司机手机号")
    @TableField("phone_number")
    private String phoneNumber; // 司机手机号

    @ApiModelProperty(value = "商品名称")
    @TableField("goods_name")
    private String goodsName; // 商品名称

    @ApiModelProperty(value = "预约数量")
    @TableField("quantity")
    private Integer quantity; // 预约数量

    @ApiModelProperty(value = "预约时间")
    @TableField("appointment_time")
    private LocalDateTime appointmentTime; // 预约时间

    @ApiModelProperty(value = "是否确认")
    @TableField("confirmed")
    private Boolean confirmed = false; // 是否确认


}
