package com.JaMorant.SSM.order.controller;


import com.JaMorant.SSM.model.order.OrderDetail;
import com.JaMorant.SSM.model.order.OrderInfo;
import com.JaMorant.SSM.order.service.OrderDetailService;
import com.JaMorant.SSM.order.service.OrderInfoService;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vo.order.OrderInfoQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 订单表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-04-28
 */
@Api(tags = "订单后台接口")
@RestController
@RequestMapping(value="/admin/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderDetailService orderDetailService;

    //订单列表
    @GetMapping("{page}/{limit}")
    public Result listOrder(@PathVariable Long page,
                            @PathVariable Long limit,
                            OrderInfoQueryVo orderInfoQueryVo) {
        //创建page对象
        Page<OrderInfo> pageParam = new Page<>(page,limit);
        Map<String,Object> map =
                orderInfoService.selectOrderInfoPage(pageParam,orderInfoQueryVo);
        return Result.ok(map);
    }

    //根据订单号获取订单详情
    @GetMapping("getOderDetail/{id}")
    public Result listOrder(@PathVariable Long id) {
        List<OrderDetail> orderDetailByOderid = orderDetailService.getOrderDetailByOderid(id);
        return Result.ok(orderDetailByOderid);
    }
}

