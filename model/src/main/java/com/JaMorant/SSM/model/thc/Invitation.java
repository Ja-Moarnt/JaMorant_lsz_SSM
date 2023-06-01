package com.JaMorant.SSM.model.thc;

import com.JaMorant.SSM.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "补货邀约")
@TableName("invitation")
public class Invitation extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "车id")
	@TableField("car_id")
	private Long carId;

	@ApiModelProperty(value = "囤管员id")
	@TableField("tungy_id")
	private Long tungyId;

	@ApiModelProperty(value = "福利说明")
	@TableField("welfare")
	private String welfare;


}
