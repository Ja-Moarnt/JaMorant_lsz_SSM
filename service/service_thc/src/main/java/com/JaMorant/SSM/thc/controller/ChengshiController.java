package com.JaMorant.SSM.thc.controller;


import com.JaMorant.SSM.model.thc.Chengshi;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.thc.service.ChengshiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 城市 前端控制器
 * </p>
 *
 * @author JaMorant
 * @since 2023-02-19
 */
@RestController
@Api(tags = "城市接口")
@RequestMapping(value = "/admin/thc/chengshi")
public class ChengshiController {

    @Autowired
    private ChengshiService chengshiService;

    //城市分类列表
    //懒加载，每次查询一层数据
    @ApiOperation("城市分类列表")
    @GetMapping("getChildChengshi/{id}")
    public Result getChildChengshi(@PathVariable Long id) {
        List<Chengshi> list = chengshiService.selectChengshiList(id);
        return Result.ok(list);
    }

    //城市分类导出
    @ApiOperation("城市分类导出")
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        chengshiService.exportData(response);
    }

    //城市分类导入
    @ApiOperation("城市分类导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        chengshiService.importData(file);
        return Result.ok(null);
    }

    @ApiOperation("根据城市id获取城市信息")
    @GetMapping("GetChengshi/{id}")
    public Chengshi GetChengshi(@PathVariable Long id) {
        Chengshi chengshi = chengshiService.getById(id);
        return chengshi;
    }
}

