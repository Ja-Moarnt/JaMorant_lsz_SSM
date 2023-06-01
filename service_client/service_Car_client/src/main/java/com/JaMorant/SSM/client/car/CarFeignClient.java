package com.JaMorant.SSM.client.car;

import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vo.order.OrderCaridVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-vod")
public interface CarFeignClient {

    @ApiOperation("根据carid设置orderid的值")
    @PostMapping ("/api/vod/car/inner/SetOrderIdByCarId")
    public Result SetOrderIdByCarId(@RequestBody OrderCaridVo orderCaridVo);

    @ApiOperation("根据carid获取orderid的值")
    @GetMapping("/api/vod/car/inner/getOrderBycarId/{carid}")
    public Long getOrderBycarId(
            @ApiParam(value = "车ID", required = true)
            @PathVariable Long carid);

    @ApiOperation("根据carid获取车辆")
    @GetMapping("/api/vod/car/GetCarById")
    public Car GetCarById(@RequestParam Long  carId);

    @ApiOperation("根据teacherId获取全部车辆")
    @GetMapping("/api/vod/car/inner/getCarListByteacherId/{teacherid}")
    public List<Car> GetCarListByteacherId(@PathVariable Long teacherid);

    @ApiOperation("根据teacherId获取全部车辆")
    @GetMapping("/api/vod/car/GetCarListBychengshiId")
    public List<Car> GetCarListBychengshiId(@RequestParam Long chengshiId);


}
