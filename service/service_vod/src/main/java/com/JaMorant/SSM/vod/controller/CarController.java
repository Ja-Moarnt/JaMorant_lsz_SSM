package com.JaMorant.SSM.vod.controller;


import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vo.vod.CarFormVo;
import com.JaMorant.SSM.vo.vod.CarQueryVo;
import com.JaMorant.SSM.vo.vod.CarSaveVo;
import com.JaMorant.SSM.vod.service.CarService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆 前端控制器
 * </p>
 *
 * @author JaMorant
 * @since 2023-01-13
 */
@Api(tags = "车辆管理")
@RestController
@RequestMapping("/admin/vod/car")
public class CarController {
    @Autowired
    private CarService carService;

    @ApiOperation("查询所有车辆")
    @GetMapping("findAll")
    public Result findAllCarShoping() {

        List<Car> list = carService.list();
        return Result.ok(list).message("查询数据成功");
    }

    //汽车列表
    @ApiOperation("条件查询分页全部司机车辆（后端管理端使用）")
    @GetMapping("{page}/{limit}")
    public Result carList(@PathVariable Long page,
                          @PathVariable Long limit,
                          CarQueryVo carQueryVo) {
        Page<Car> pageParam = new Page<>(page,limit);
        Map<String,Object> map = carService.findPageCar(pageParam,carQueryVo);
        return Result.ok(map);
    }

    //审核状态未完成的汽车列表
    @ApiOperation("条件查询分页司机车辆(审核完成的)")
    @GetMapping("findYes/{page}/{limit}")
    public Result carListYes(@PathVariable Long page,
                          @PathVariable Long limit,
                          CarQueryVo carQueryVo) {
        if(carQueryVo.getTeacherId()==null)return Result.fail(null).message("carid为空");
        Page<Car> pageParam = new Page<>(page,limit);
        Map<String,Object> map = carService.findPageCarYes(pageParam,carQueryVo);
        return Result.ok(map);
    }
    //审核状态未完成的汽车列表
    @ApiOperation("条件查询分页司机车辆(正在审核的)")
    @GetMapping("findNot/{page}/{limit}")
    public Result carListNot(@PathVariable Long page,
                          @PathVariable Long limit,
                          CarQueryVo carQueryVo) {
        if(carQueryVo.getTeacherId()==null)return Result.fail(null).message("carid为空");
        Page<Car> pageParam = new Page<>(page,limit);
        Map<String,Object> map = carService.findPageCarNot(pageParam,carQueryVo);
        return Result.ok(map);
    }

    //添加课程基本信息
    @ApiOperation("添加车辆(车辆激活)")
    @PostMapping("save")
    public Result save(@RequestBody CarSaveVo carSaveVo) {
        //判断时间是否出错
        if (carSaveVo.getTimeStart().after(carSaveVo.getTimeEnd())){
            return Result.fail(null).message("时间出错");
        }
        Long carid=carService.saveCar(carSaveVo);
        return Result.ok(carid);
    }
//
//
//
//
    //5 根据id查询
    @ApiOperation("根据id查询车辆")
    @GetMapping("getCar/{id}")
    public Result getCar(@PathVariable Long id) {
        CarFormVo car = carService.getCarInfoById(id);
        return Result.ok(car);
    }

    //6 修改-最终实现
    @ApiOperation("修改车辆最终实现")
    @PostMapping("updateCar")
    public Result updateCarShoping(@RequestBody Car car) {
        //判断时间是否出错
        if (car.getTimeStart().after(car.getTimeEnd())){
            return Result.fail(null).message("时间出错");
        }
        //数据库中原来这条记录
        Car car1 = carService.getById(car.getId());
        //根据之前的修改时间和现在的时间差计算盈利
        BigDecimal fengcheng = car1.getFengcheng();
        Date updateTime = car1.getUpdateTime();
        // 获取时间差，单位为毫秒
        long milliseconds  = new Date().getTime() - updateTime.getTime();
        // 毫秒转化为小时
        double hours = (double)milliseconds / 1000 / 60 / 60;
        // 将double类型的值转化为BigDecimal类型的值
        BigDecimal hoursBigDecimal = new BigDecimal(Double.toString(hours));
        //将时间和分成做乘法进行修改盈利
        BigDecimal yingli = car1.getYingli();
        yingli=yingli.add(fengcheng.multiply(hoursBigDecimal));
        car.setYingli(yingli);

        //最后修改分成
        if (car.getVideoId()==null){
            //表示将广告设置为空,那么就将分成设置为0
            car.setFengcheng(new BigDecimal("0.0"));
        }else {
                //如果是设置广告的修改，则将分成改为基本分成
                BigDecimal jifeng = car.getJifeng();
                car.setFengcheng(jifeng);
        }
        //以下是不可改内容填写
        car.setOrderId(car1.getOrderId());
        carService.updateById(car);
        return Result.ok(car.getId());
    }

    //6 修改-最终实现
    @ApiOperation("司机端更改车辆广告")
    @PutMapping("updateCarVideo")
    public Result updateCarVideo(@RequestParam Long carId,
                                 @RequestParam Long videoId) {
        if (carId==null)return Result.fail(null).message("carId不能为空！！");
        Car car = carService.getById(carId);
        if (car==null)return Result.fail(null).message("没有找到相应的car！");
        car.setVideoId(videoId);
        Result result = this.updateCarShoping(car);
        return result;
    }

    // remove/1
    //2 逻辑删除讲师
    @ApiOperation("逻辑删除车辆")
    @DeleteMapping("remove/{id}")
    public Result removeCarShoping(@ApiParam(name = "id", value = "ID", required = true)
                                   @PathVariable Long id) {
        boolean b = carService.removeById(id);
        return Result.ok(null);
    }

    //7 批量删除讲师
    // json数组 [1,2,3]
    @ApiOperation("批量删除车辆")
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestBody List<Long> idList) {
        boolean isSuccess = carService.removeByIds(idList);
        if(isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

}

