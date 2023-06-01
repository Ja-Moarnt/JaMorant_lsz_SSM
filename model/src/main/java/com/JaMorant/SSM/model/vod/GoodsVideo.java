package com.JaMorant.SSM.model.vod;

import com.JaMorant.SSM.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商品宣传视频实体类")
@TableName("goods_video")
public class GoodsVideo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "课程ID")
	@TableField("goods_id")
	private Long goodsId;


	@ApiModelProperty(value = "商品名称")
	@TableField("title")
	private String title;

	@ApiModelProperty(value = "云端视频资源")
	@TableField("video_source_id")
	private String videoSourceId;

	@ApiModelProperty(value = "原始文件名称")
	@TableField("video_original_name")
	private String videoOriginalName;


	@ApiModelProperty(value = "视频时长（秒）")
	@TableField("duration")
	private Float duration;

	@ApiModelProperty(value = "视频源文件大小（字节）")
	@TableField("size")
	private Long size;

	@ApiModelProperty(value = "乐观锁")
	@TableField("version")
	private Long version;

	@ApiModelProperty(value = "状态")
	@TableField("status")
	private Integer status;

}
