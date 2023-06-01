package com.JaMorant.SSM.vod.controller;


import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vod.service.VideoVisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-04-25
 */
@RestController
@Api(tags ="商品统计管理")
@RequestMapping(value="/admin/vod/videoVisitor")
//@CrossOrigin
public class VideoVisitorController {

    @Autowired
    private VideoVisitorService videoVisitorService;

    //商品统计的接口
    @ApiOperation("商品统计的接口")
    @GetMapping("findCount/{courseId}/{startDate}/{endDate}")
    public Result findCount(@PathVariable Long courseId,
                            @PathVariable String startDate,
                            @PathVariable String endDate) {
        Map<String,Object> map =
                videoVisitorService.findCount(courseId,startDate,endDate);
        return Result.ok(map);
    }
}

