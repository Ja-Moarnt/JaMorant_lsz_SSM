package com.JaMorant.SSM.model.vod;

import com.JaMorant.SSM.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;

/**
 * @author:JaMorant
 * @time:2022/12/29 16:17
 * @explain:
 */
@Data
@ApiModel(description = "车辆实体类")
@TableName("car")
public class Car extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "车牌号")
    @TableField("car_id")
    private String carId;

    @ApiModelProperty(value = "货柜id")
    @TableField("Gui_id")
    private String GuiId;

    @ApiModelProperty(value = "审核状态")
    @TableField("car_status")
    private String CarStatus;

    @ApiModelProperty(value = "司机ID")
    @TableField("teacher_id")
    private Long teacherId;

    @ApiModelProperty(value = "广告id")
    @TableField(value ="video_id",updateStrategy = FieldStrategy.IGNORED)
    private Long videoId;

    @ApiModelProperty(value = "订单id")
    @TableField(value = "order_id",updateStrategy = FieldStrategy.IGNORED)
    private Long orderId;

    @ApiModelProperty(value = "城市id")
    @TableField("chengshi_id")
    private Long chengshiId;

    @ApiModelProperty(value = "工作起始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @TableField("time_start")
    private Time timeStart;

    @ApiModelProperty(value = "工作结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @TableField("time_end")
    private Time timeEnd;

    @ApiModelProperty(value = "盈利总额")
    @TableField("yingli")
    private BigDecimal yingli;

    @ApiModelProperty(value = "分成额")
    @TableField("fengcheng")
    private BigDecimal fengcheng;

    @ApiModelProperty(value = "基础分成值")
    @TableField("jifeng")
    private BigDecimal jifeng;

    @ApiModelProperty(value = "地理经度")
    @TableField("longitude")
    private BigDecimal longitude;

    @ApiModelProperty(value = "地理纬度")
    @TableField("latitude")
    private BigDecimal latitude;

}
