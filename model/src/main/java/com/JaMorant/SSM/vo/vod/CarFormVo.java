package com.JaMorant.SSM.vo.vod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;

/**
 * @author helen
 * @since 2020/6/6
 */
@ApiModel("车辆基本信息")
@Data
public class CarFormVo {
    @ApiModelProperty(value = "id")
    private Long Id;

    @ApiModelProperty(value = "车牌号")
    private String carId;

    @ApiModelProperty(value = "司机Id")
    private Long teacherId;

    @ApiModelProperty(value = "货柜id")
    private String GuiId;

    //为了修改的时候不把值设置为空
    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "订单流水号")
    private String OrderLiu;

    @ApiModelProperty(value = "审核状态")
    private String CarStatus;

    @ApiModelProperty(value = "商品分类ID")
    private Long subjectId;

    @ApiModelProperty(value = "商品分类父级ID")
    private Long subjectParentId;

    @ApiModelProperty(value = "货物id")
    private Long goodsId;

    @ApiModelProperty(value = "广告id")
    private Long videoId;

    @ApiModelProperty(value = "广告名称")
    private String title;

    @ApiModelProperty(value = "城市id")
    private Long chengshiId;

    @ApiModelProperty(value = "城市父级id")
    private Long chengshiParentId;

    @ApiModelProperty(value = "城市名")
    private String ChengShi;

    //可改
    @ApiModelProperty(value = "工作起始时间")
    private Time timeStart;

    @ApiModelProperty(value = "工作结束时间")
    private Time timeEnd;

    //不可改
    @ApiModelProperty(value = "盈利总额")
    private BigDecimal yingli;

    //特定修改逻辑
    @ApiModelProperty(value = "当前分成额")
    private BigDecimal fengcheng;

    @ApiModelProperty(value = "基础分成值")
    private BigDecimal jifeng;
}
