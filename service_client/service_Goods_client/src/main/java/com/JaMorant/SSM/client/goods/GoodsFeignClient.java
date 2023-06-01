package com.JaMorant.SSM.client.goods;

import com.JaMorant.SSM.vo.vod.GoodsFormVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-vod")
public interface GoodsFeignClient {

    @ApiOperation("根据goodsid获取货物信息")
    @GetMapping("/admin/vod/goods/getgoods/{id}")
    public GoodsFormVo getById(@ApiParam(value = "货物ID", required = true)
            @PathVariable Long id);



}
