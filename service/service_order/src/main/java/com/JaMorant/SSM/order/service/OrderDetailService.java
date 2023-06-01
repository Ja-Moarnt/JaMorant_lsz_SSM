package com.JaMorant.SSM.order.service;

import com.JaMorant.SSM.model.order.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-04-28
 */
public interface OrderDetailService extends IService<OrderDetail> {
    //根据订单id获取商品详情列表
    List<OrderDetail> getOrderDetailByOderid(Long id);

    //根据订单id和商品goodsid是否已经添加
    OrderDetail getOrderDetailByOderidAndgoodsid(Long orderid,Long goodsid);
    //获取近一周每个订单的订单详情外加经纬度（AI算法需要）
    List<OrderDetail> GetWeekGoods();
}
