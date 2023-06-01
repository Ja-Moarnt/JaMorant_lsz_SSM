package com.JaMorant.SSM.model.vod;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel(description = "商品描述")
@TableName("goods_description")
public class GoodsDescription {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	@TableId(type = IdType.INPUT)
	private Long id;

	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("create_time")
	private Date createTime;

	@ApiModelProperty(value = "更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("update_time")
	private Date updateTime;

	@ApiModelProperty(value = "逻辑删除(1:已删除，0:未删除)")
	@JsonIgnore
	@TableLogic
	@TableField("is_deleted")
	private Integer isDeleted;

	@ApiModelProperty(value = "其他参数")
	@TableField(exist = false)
	private Map<String,Object> param = new HashMap<>();

	@ApiModelProperty(value = "课程ID")
	@TableField("goods_id")
	private Long goodsId;

	@ApiModelProperty(value = "课程简介")
	@TableField("description")
	private String description;

}
