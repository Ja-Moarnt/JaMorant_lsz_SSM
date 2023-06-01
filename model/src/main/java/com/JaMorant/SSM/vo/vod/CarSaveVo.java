package com.JaMorant.SSM.vo.vod;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Time;

/**
 * @author helen
 * @since 2020/6/6
 */
@ApiModel("车辆激活信息")
@Data
public class CarSaveVo {

    @ApiModelProperty(value = "车牌号")
    private String carId;

    @ApiModelProperty(value = "司机Id")
    private Long teacherId;

    @ApiModelProperty(value = "货柜id")
    private String GuiId;

    @ApiModelProperty(value = "城市id")
    private Long ChengshiId;

    @ApiModelProperty(value = "工作起始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time timeStart;

    @ApiModelProperty(value = "工作结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time timeEnd;

}
