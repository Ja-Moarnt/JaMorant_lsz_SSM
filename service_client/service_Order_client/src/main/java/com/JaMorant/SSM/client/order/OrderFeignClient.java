package com.JaMorant.SSM.client.order;

import com.JaMorant.SSM.vo.order.OrderInfoVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-order")
public interface OrderFeignClient {

    @ApiOperation("根据订单id获取订单号")
    @GetMapping("/api/order/orderInfo/getByLiuId/{orderId}")
    public String getById(@ApiParam(value = "订单ID", required = true)
            @PathVariable Long orderId);

    @ApiOperation("根据订单id获取订单" )
    @GetMapping("/api/order/orderInfo/GetOrderInfoVoByorderId")
    public OrderInfoVo GetOrderInfoVoByorderId(@RequestParam Long orderId);



}
