package com.JaMorant.SSM.vod.controller;


import com.JaMorant.SSM.model.vod.Subject;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-04-21
 */
@RestController
@Api(tags = "商品分类管理接口")
@RequestMapping(value="/admin/vod/subject")
//@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //课程分类列表
    //懒加载，每次查询一层数据
    @ApiOperation("商品分类列表")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable Long id) {
        List<Subject> list = subjectService.selectSubjectList(id);
        return Result.ok(list);
    }

    //课程分类导出
    @ApiOperation("商品分类导出")
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        subjectService.exportData(response);
    }

    //课程分类导入
    @ApiOperation("商品分类导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        subjectService.importData(file);
        return Result.ok(null);
    }
}

