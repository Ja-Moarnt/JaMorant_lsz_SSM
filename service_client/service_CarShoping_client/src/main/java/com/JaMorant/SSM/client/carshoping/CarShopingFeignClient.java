package com.JaMorant.SSM.client.carshoping;

import com.JaMorant.SSM.result.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-vod")
public interface CarShopingFeignClient {

    @ApiOperation("根据carshopingid获取货物信息")
    @GetMapping("/admin/vod/carshoping/getCarShoping/{id}")
    public Result getById(
            @ApiParam(value = "货物ID", required = true)
            @PathVariable Long id);



}
