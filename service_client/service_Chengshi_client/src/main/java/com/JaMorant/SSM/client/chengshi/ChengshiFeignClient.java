package com.JaMorant.SSM.client.chengshi;

import com.JaMorant.SSM.model.thc.Chengshi;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-thc")
public interface ChengshiFeignClient {

    @ApiOperation("根据城市id获取城市信息")
    @GetMapping ("/admin/thc/chengshi/GetChengshi/{id}")
    public Chengshi GetChengshi(@PathVariable Long id);



}
