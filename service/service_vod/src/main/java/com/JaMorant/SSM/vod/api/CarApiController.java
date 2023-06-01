package com.JaMorant.SSM.vod.api;

import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vo.order.OrderCaridVo;
import com.JaMorant.SSM.vod.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author:JaMorant
 * @time:2023/1/18 13:32
 * @explain:
 */
@RestController
@Api(tags = "车辆外部接口")
@RequestMapping(value="/api/vod/car")
public class CarApiController {
    @Autowired
    private CarService carService;

    //根据carid设置orderid的值
    @ApiOperation("根据carid设置orderid的值")
    @PostMapping ("inner/SetOrderIdByCarId")
    public Result SetOrderIdByCarId(@RequestBody OrderCaridVo orderCaridVo){
        Long oo=carService.SetorderidBycarId(orderCaridVo);
        return Result.ok(oo);
    }

    //根据carid设置orderid的值
    @ApiOperation("根据司机id获取其名下全部车辆")
    @GetMapping("inner/getcarListByteacherId/{teacherid}")
    public Result getcarListByteacherId(@PathVariable Long teacherid){
        List<Car> carList=carService.getcarListByteacherId(teacherid);
        return Result.ok(carList);
    }
    //根据carid设置orderid的值
    @ApiOperation("根据司机id获取其名下全部车辆(返回list集合，用于后端远程调用)")
    @GetMapping("inner/getCarListByteacherId/{teacherid}")
    public List<Car> getCarListByteacherId(@PathVariable Long teacherid){
        List<Car> carList=carService.getcarListByteacherId(teacherid);
        return carList;
    }

    //根据carid获取orderid的值
    @ApiOperation("根据carid获取orderid的值")
    @GetMapping("inner/getOrderBycarId/{carid}")
    public Long getOrderBycarId(
            @ApiParam(value = "车ID", required = true)
            @PathVariable Long carid){
        Car car = carService.getById(carid);
        Long orderId = car.getOrderId();
        return orderId;
    }

    @ApiOperation(value = "根据城市id遍历出该城市所有车辆")
    @GetMapping ("GetCarBychengshiId")
    public Result saveopenid(@RequestParam Long chengshiId) {
        if (chengshiId==null)return Result.fail(null).message("该城市id输入为空");
        List<Car> carList=carService.GetCarBychengshiId(chengshiId);
        return Result.ok(carList);
    }

    @ApiOperation(value = "根据城市id遍历出该城市所有车辆(供远程调用，返回为车辆集合)")
    @GetMapping ("GetCarListBychengshiId")
    public List<Car> GetCarListBychengshiId(@RequestParam Long chengshiId) {
        if (chengshiId==null)return null;
        List<Car> carList=carService.GetCarBychengshiId(chengshiId);
        return carList;
    }

    @ApiOperation(value = "根据carid获取车辆（用补货单完善的远程调用）")
    @GetMapping ("GetCarById")
    public Car GetCarById(@RequestParam Long  carId) {
        if (carId==null)return null;
        Car car = carService.getById(carId);
        return car;
    }
    //根据carid设置orderid的值
    @ApiOperation("上传车辆地理位置")
    @GetMapping  ("location")
    public Result UploadLocation(@RequestParam Long carId,
                                 @RequestParam BigDecimal log,
                                 @RequestParam BigDecimal lat){
        int q=carService.UploadLocation(carId,log,lat);
        if (q!=0)
        return Result.ok(null).message("上传成功！");
        return Result.fail(null).message("上传失败！");
    }
}
