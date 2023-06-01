package com.JaMorant.SSM.vod.controller;


import com.JaMorant.SSM.model.vod.GoodsVideo;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vod.service.GoodsVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-04-22
 */
@RestController
@Api(tags = "视频管理")
@RequestMapping(value="/admin/vod/video")
//@CrossOrigin
public class VideoController {

    @Autowired
    private GoodsVideoService goodsVideoService;

    @ApiOperation(value = "获取全部广告")
    @GetMapping("VideoAll")
    public Result VideoAll() {
        List<GoodsVideo> goodsVideoByGoodsId = goodsVideoService.getVideoAll();
        return Result.ok(goodsVideoByGoodsId);
    }
    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result getbyID(@PathVariable Long id) {
        GoodsVideo byId = goodsVideoService.getById(id);
        return Result.ok(byId);
    }
    @ApiOperation(value = "获取")
    @GetMapping("getbygoodsId/{id}")
    public Result getbyGoodsID(@PathVariable Long id) {
        List<GoodsVideo> goodsVideoByGoodsId = goodsVideoService.getGoodsVideoByGoodsId(id);
        return Result.ok(goodsVideoByGoodsId);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@RequestBody GoodsVideo goodsVideo) {
        goodsVideoService.save(goodsVideo);
        return Result.ok(null);
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateById(@RequestBody GoodsVideo goodsVideo) {
        goodsVideoService.updateById(goodsVideo);
        return Result.ok(null);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        goodsVideoService.removeGoodsVideoById(id);
        return Result.ok(null);
    }
}

