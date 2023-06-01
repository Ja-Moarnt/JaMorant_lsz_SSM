package com.JaMorant.SSM.vo.thc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author helen
 * @since 2020/6/5
 */
@ApiModel("城市分类列表")
@Data
public class ChengshiVo {

    @ApiModelProperty(value = "城市分类ID")
    private Long id;

    @ApiModelProperty(value = "城市分类名称")
    private String title;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "城市二级分类列表")
    private List<ChengshiVo> children = new ArrayList<>();
}
