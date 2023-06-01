package com.JaMorant.SSM.vod.api;

import com.JaMorant.SSM.model.vod.Goods;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vo.vod.GoodsQueryVo;
import com.JaMorant.SSM.vod.service.GoodsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author:JaMorant
 * @time:2023/1/9 14:37
 * @explain:
 */

@RestController
@RequestMapping(value="/api/vod/goods")
public class GoodsApiController {

    @Autowired
    private GoodsService goodsService;

    //课程id查询课程信息
    @ApiOperation("根据ID查询课程")
    @GetMapping("inner/getById/{goodsId}")
    public Goods getById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long goodsId){
        Goods byId = goodsService.getById(goodsId);
        return byId;
    }

    //根据课程分类查询课程列表（分页）
    @ApiOperation("根据商品分类查询商品列表")
    @GetMapping("{subjectParentId}/{page}/{limit}")
    public Result findPageCourse(@ApiParam(value = "课程一级分类ID", required = true)
                                 @PathVariable Long subjectParentId,
                                 @ApiParam(name = "page", value = "当前页码", required = true)
                                 @PathVariable Long page,
                                 @ApiParam(name = "limit", value = "每页记录数", required = true)
                                 @PathVariable Long limit) {
        //封装条件
        GoodsQueryVo goodsQueryVo = new GoodsQueryVo();
        goodsQueryVo.setSubjectParentId(subjectParentId);
        //创建page对象
        Page<Goods> pageParam = new Page<>(page,limit);
        Map<String,Object> map = goodsService.findPageCourse(pageParam,goodsQueryVo);
        return Result.ok(map);
    }

    //根据课程id查询课程详情
    @ApiOperation("根据课程id查询课程详情")
    @GetMapping("getInfo/{goodsId}")
    public Result getInfo(@PathVariable Long goodsId) {
        Map<String,Object> map = goodsService.getInfoById(goodsId);
        return Result.ok(map);
    }

    @ApiOperation("根据关键字查询课程")
    @GetMapping("inner/findByKeyword/{keyword}")
    public List<Goods> findByKeyword(
            @ApiParam(value = "关键字", required = true)
            @PathVariable String keyword){
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("title",keyword);
        List<Goods> list = goodsService.list(wrapper);
        return list;
    }
}
