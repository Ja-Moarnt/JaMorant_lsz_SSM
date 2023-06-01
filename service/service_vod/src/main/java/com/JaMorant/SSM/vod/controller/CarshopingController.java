package com.JaMorant.SSM.vod.controller;


import com.JaMorant.SSM.model.vod.CarShoping;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vo.vod.CarShopingFormVo;
import com.JaMorant.SSM.vo.vod.CarShopingQueryVo;
import com.JaMorant.SSM.vo.vod.CarShopingSaveVo;
import com.JaMorant.SSM.vod.service.CarshopingService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆货物 前端控制器
 * </p>
 *
 * @author JaMorant
 * @since 2022-12-29
 */
@Api(tags = "车辆货物管理接口")
@RestController
@RequestMapping("/admin/vod/carshoping")
public class CarshopingController {
    @Autowired
    private CarshopingService carshopingService;

    @ApiOperation("查询所有货物")
    @GetMapping("findAll")
    public Result findAllCarShoping() {

        List<CarShoping> list = carshopingService.list();
        return Result.ok(list).message("查询数据成功");
    }
    //点播课程列表
    @ApiOperation("条件查询分页司机车辆货物")
    @GetMapping("{page}/{limit}")
    public Result carshopingList(@PathVariable Long page,
                             @PathVariable Long limit,
                             CarShopingQueryVo carShopingQueryVo) {
        Page<CarShoping> pageParam = new Page<>(page,limit);
        Map<String,Object> map = carshopingService.findPageCarShoping(pageParam,carShopingQueryVo);
        return Result.ok(map);
    }

    //点播课程列表
    @ApiOperation("根据carid遍历出该车辆中所有商品")
    @GetMapping("GetGoodsByCarId")
    public Result carshopingList(@RequestParam Long carId) {
        List<CarShoping> carShopingList=carshopingService.findCarShopingByCarid(carId);
        return Result.ok(carShopingList);
    }

    //点播课程列表
    @ApiOperation("司机车辆货物")
    @PostMapping("listBycarid")
    public Result carshopingList(@RequestBody  CarShopingQueryVo carShopingQueryVo) {
        List<CarShoping> carShoping = carshopingService.findCarShoping(carShopingQueryVo);
        return Result.ok(carShoping);
    }

    //添加课程基本信息
    @ApiOperation("添加车辆货物基本信息")
    @PostMapping("save")
    public Result save(@RequestBody CarShopingFormVo carShopingFormVo) {
        //判断该carshoping种car_id和goods_id是否有同一个
        Long carshopingid=carshopingService.isSaveCarShoping(carShopingFormVo.getCarId(),carShopingFormVo.getGoodsId());
        if (carshopingid!=null){
            return Result.fail(carshopingid).message("该在商品已经存在，不可重复添加！");
        }else {
            Long carShopingId = carshopingService.saveCarShopingInfo(carShopingFormVo);
            return Result.ok(carShopingId);
        }
    }

    //添加课程基本信息
    @ApiOperation("补货（硬件）")
    @PostMapping("saveByYing")
    public Result saveByYing(@RequestBody CarShopingSaveVo carShopingSaveVo) {
            Long carShopingId = carshopingService.saveByYing(carShopingSaveVo);
            return Result.ok(carShopingId);

    }




    //5 修改-根据id查询
    @ApiOperation("根据id查询车辆货物")
    @GetMapping("getCarShoping/{id}")
    public Result getCarShoping(@PathVariable Long id) {
        CarShopingFormVo carShopingInfoById = carshopingService.getCarShopingInfoById(id);
        return Result.ok(carShopingInfoById);
    }

    //6 修改-最终实现
    // {...}
    @ApiOperation("修改车辆货物最终实现")
    @PostMapping("updateCarShoping")
    public Result updateCarShoping(@RequestBody CarShopingFormVo carShopingFormVo) {
        carshopingService.updateCarShopingId(carShopingFormVo);
        return Result.ok(carShopingFormVo.getGoodsId());
    }

    // remove/1
    //2 逻辑删除讲师
    @ApiOperation("逻辑删除车辆货物")
    @DeleteMapping("remove/{id}")
    public Result removeCarShoping(@ApiParam(name = "id", value = "ID", required = true)
                                   @PathVariable Long id) {
        carshopingService.removeCarShopingId(id);
        return Result.ok(null);
    }

    //7 批量删除讲师
    // json数组 [1,2,3]
    @ApiOperation("批量删除车辆货物")
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestBody List<Long> idList) {
        boolean isSuccess = carshopingService.removeByIds(idList);
        if(isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }


}

