package com.JaMorant.SSM.vod.count;

import com.JaMorant.SSM.vod.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author:JaMorant
 * @time:2023/3/10 20:53
 * @explain:
 */
@RestController
@Api(tags = "车辆统计（数据可视化接口）")
@RequestMapping("/admin/vod/count")
public class CarCountController {
    @Autowired
    private CarService carService;

    @GetMapping("/carCount")
    @ApiOperation("已激活车辆的全部数量")
    public Integer getCarCount(){
        return carService.getCarCountAll();
    }


    @GetMapping("/carCountByCity")
    @ApiOperation("根据目前经纬度统计城市的车辆数数")
    public List<Map<String, Object>> carCountByCity() throws IOException {
        return carService.getCityCarCount();
    }
}
