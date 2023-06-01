package com.JaMorant.SSM.model.vod;

import com.JaMorant.SSM.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:JaMorant
 * @time:2022/12/30 14:07
 * @explain:
 */

@Data
@ApiModel(description = "商品实体类")
@TableName("goods")
public class Goods extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "课程专业ID")
    @TableField("subject_id")
    private Long subjectId;

    @ApiModelProperty(value = "课程专业父级ID")
    @TableField("subject_parent_id")
    private Long subjectParentId;

    @ApiModelProperty(value = "课程标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    @TableField("price")
    private BigDecimal price;



    @ApiModelProperty(value = "课程封面图片路径")
    @TableField("cover")
    private String cover;



    @ApiModelProperty(value = "课程状态 0未发布 1已发布")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "课程发布时间")
    @TableField("publish_time")
    private Date publishTime;

    @ApiModelProperty(value = "商品简介")
    @TableField("description")
    private String description;

}
