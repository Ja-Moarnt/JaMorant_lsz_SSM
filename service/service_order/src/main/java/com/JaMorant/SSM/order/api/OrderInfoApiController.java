package com.JaMorant.SSM.order.api;

import com.JaMorant.SSM.model.order.OrderInfo;
import com.JaMorant.SSM.order.service.OrderInfoService;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vo.order.OrderEndVo;
import com.JaMorant.SSM.vo.order.OrderFormVo;
import com.JaMorant.SSM.vo.order.OrderInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/order/orderInfo")
@Api(tags = "订单用户接口")
public class OrderInfoApiController {

    @Autowired
    private OrderInfoService orderInfoService;

    //生成订单方法
    @PostMapping("submitOrder")
    public Result submitOrder(@RequestBody OrderFormVo orderFormVo) {
        Long orderId = orderInfoService.submitOrder(orderFormVo);
        return Result.ok(orderId);
    }

    //根据订单id获取订单流水号
    @GetMapping("/getByLiuId/{orderId}")
    public String getLiushui(@PathVariable Long orderId){
        OrderInfo byId = orderInfoService.getById(orderId);
        return byId.getOutTradeNo();
    }

    //返回的是一个OrderInfoVo单独对象
    @ApiOperation(value = "获取订单详情")
    @GetMapping("getInfo/{id}")
    public Result getInfo(@PathVariable Long id) {
        OrderInfoVo orderInfoVo = orderInfoService.getOrderInfoById(id);
        return Result.ok(orderInfoVo);
    }

    //根据订单id往订单里面添加车辆货物商品
    @ApiOperation(value = "往订单里面添加车辆货物商品")
    @GetMapping ("SaveGoodsToOrder/{orderid}/{goodsid}")
    public Result SaveGoodsToOrder(@PathVariable Long orderid,
                                   @PathVariable Long goodsid) {
        Long orderId = orderInfoService.SaveGoodsToOrder(orderid,goodsid);
        return Result.ok(orderId);
    }
    //根据订单id往订单里面添加车辆货物商品
    @ApiOperation(value = "往订单里面添加车辆货物商品(返回订单id)")
    @GetMapping ("SaveGoodsToOrderBycarid/{carid}/{goodsid}")
    public Result SaveGoodsToOrderBycarid(@PathVariable Long carid,
                                   @PathVariable Long goodsid) {
        Long orderId = orderInfoService.SaveGoodsToOrderBycarid(carid,goodsid);
        if (orderId==null)return Result.fail(null).message("该车目前未有正在执行的订单");
        return Result.ok(orderId);
    }

    //结束订单
    @ApiOperation(value = "结束订单（关门）(返回刚刚结束的订单id)")
    @PostMapping ("endOrder")
    public Result endOrder(@RequestBody OrderEndVo orderEndVo) throws IOException {
        Long orderId = orderInfoService.endOrder(orderEndVo);
        return Result.ok(orderId);
    }

    //返回的是一个OrderInfoVo单独对象
    @ApiOperation(value = "根据userid获取订单")
    @GetMapping("getOrderByUserid/{id}")
    public Result getOrderByUserid(@PathVariable Long id) {
        List<OrderInfoVo> orderInfoVo = orderInfoService.getOrderByUserid(id);
        return Result.ok(orderInfoVo);
    }

    //返回的是一个OrderInfoVo单独对象
    @ApiOperation(value = "根据carId获取订单")
    @GetMapping("getOrderBycarId/{id}")
    public Result getOrderBycarId(@PathVariable Long id) {
        List<OrderInfoVo> orderInfoVo = orderInfoService.getOrderByCarId(id);
        return Result.ok(orderInfoVo);
    }

    //返回的是一个OrderInfoVo单独对象
    @ApiOperation(value = "根据司机id获取订单")
    @GetMapping("getOrderByteacherId/{id}")
    public Result getOrderByteacherId(@PathVariable Long id) {
        List<OrderInfoVo> orderInfoVo = orderInfoService.getOrderByteacherId(id);
        return Result.ok(orderInfoVo);
    }

    //返回的是一个OrderInfoVo单独对象
    @ApiOperation(value = "根据orderId获取订单(返回OrderInfoVo，非结果集)")
    @GetMapping("GetOrderInfoVoByorderId")
    public OrderInfoVo GetOrderInfoVoByorderId(@RequestParam Long orderId) {
        if (orderId==null)return null;
        OrderInfoVo orderInfoVo = orderInfoService.GetOrderInfoVoByorderId(orderId);
        return orderInfoVo;
    }
}
