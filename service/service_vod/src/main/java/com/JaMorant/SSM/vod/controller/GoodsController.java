package com.JaMorant.SSM.vod.controller;


import com.JaMorant.SSM.model.vod.Goods;
import com.JaMorant.SSM.model.vod.GoodsVideo;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vo.vod.GoodsFormVo;
import com.JaMorant.SSM.vo.vod.GoodsQueryVo;
import com.JaMorant.SSM.vod.service.GoodsService;
import com.JaMorant.SSM.vod.service.GoodsVideoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author JaMorant
 * @since 2022-12-30
 */
@RestController
@Api(tags = "商品管理接口")
@RequestMapping("/admin/vod/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsVideoService goodsVideoService;

    //查询所有课程
    @ApiOperation("查询所有商品")
    @GetMapping("findAll")
    public Result findAll() {
        List<Goods> list = goodsService.findlist();
        return Result.ok(list);
    }

    //点播课程列表
    @ApiOperation("商品列表")
    @GetMapping("{page}/{limit}")
    public Result goodsList(@PathVariable Long page,
                             @PathVariable Long limit,
                             GoodsQueryVo goodsQueryVo) {
        Page<Goods> pageParam = new Page<>(page,limit);
        Map<String,Object> map = goodsService.findPageCourse(pageParam,goodsQueryVo);
        return Result.ok(map);
    }

    //添加课程基本信息
    @ApiOperation("添加商品基本信息")
    @PostMapping("save")
    public Result save(@RequestBody GoodsFormVo goodsFormVo) {
        Long goodsId = goodsService.saveGoodsInfo(goodsFormVo);
        return Result.ok(goodsId);
    }

    //根据id获取课程信息
    @ApiOperation("根据id获取商品信息")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        GoodsFormVo goodsInfoById = goodsService.getGoodsInfoById(id);
        return Result.ok(goodsInfoById);
    }

    //根据id获取课程信息
    @ApiOperation("根据id获取商品信息(api调用)")
    @GetMapping("getgoods/{id}")
    public GoodsFormVo getgoods(@PathVariable Long id) {
        GoodsFormVo goodsInfoById = goodsService.getGoodsInfoById(id);
        return goodsInfoById;
    }

    //修改课程信息
    @ApiOperation("修改商品信息")
    @PostMapping("update")
    public Result update(@RequestBody GoodsFormVo goodsFormVo) {
        goodsService.updateGoodsId(goodsFormVo);
        //课程id
        return Result.ok(goodsFormVo.getId());
    }



    //课程最终发布
    @ApiOperation("商品上架")
    @PutMapping("publishGoods/{id}")
    public Result publishGoods(@PathVariable Long id) {
        goodsService.publishGoods(id);
        return Result.ok(null);
    }

    //删除课程
    @ApiOperation("删除商品")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        goodsService.removeGoodsId(id);
        return Result.ok(null);
    }

    //根据id获取课程信息
    @ApiOperation("根据id获取商品视频信息")
    @GetMapping("get/goodsvideo/{id}")
    public Result getgoodsvideo(@PathVariable Long id) {
        List<GoodsVideo> goodsGoodsvideoById = goodsVideoService.getGoodsVideoByGoodsId(id);
        return Result.ok(goodsGoodsvideoById);
    }

    //7 批量删除讲师
    // json数组 [1,2,3]
    @ApiOperation("批量删除货物")
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestBody List<Long> idList) {
        goodsService.PiremoveGoodsId(idList);
        return Result.ok(null);

    }

    //根据商品类别获取商品
    @ApiOperation("根据商品类别获取商品")
    @GetMapping("GetgoodsBysubid/{subjectId}")
    public Result GetgoodsBysubid(@PathVariable Long subjectId){
        List<Goods> goods = goodsService.GetgoodsBysubid(subjectId);
        return Result.ok(goods);
    }

}

