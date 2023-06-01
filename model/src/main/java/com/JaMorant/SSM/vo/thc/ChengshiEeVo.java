package com.JaMorant.SSM.vo.thc;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * <p>
 * Dict
 * </p>
 *
 * @author qy
 */
@Data
public class ChengshiEeVo {

	@ExcelProperty(value = "id" ,index = 0)
	private Long id;

	@ExcelProperty(value = "城市分类名称" ,index = 1)
	private String title;

	@ExcelProperty(value = "上级id" ,index = 2)
	private Long parentId;

	@ExcelProperty(value = "排序" ,index = 3)
	private Integer sort;


}

