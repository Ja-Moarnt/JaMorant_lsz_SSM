package com.JaMorant.SSM.model.vod;

import com.JaMorant.SSM.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author:JaMorant
 * @time:2022/12/29 16:17
 * @explain:
 */
@Data
@ApiModel(description = "车辆货物实体类")
@TableName("carshoping")
public class CarShoping extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "车id")
    @TableField("car_id")
    private Long carId;

    @ApiModelProperty(value = "货物id")
    @TableField("goods_id")
    private Long goodsId;

    @ApiModelProperty(value = "剩余货物数量")
    @TableField("sheng_count")
    private Integer shengCount;

    @ApiModelProperty(value = "历史售出数量")
    @TableField("buy_count")
    private Integer buyCount;



}
