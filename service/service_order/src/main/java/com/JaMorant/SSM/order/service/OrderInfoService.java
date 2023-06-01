package com.JaMorant.SSM.order.service;

import com.JaMorant.SSM.model.order.OrderInfo;
import com.JaMorant.SSM.vo.order.OrderEndVo;
import com.JaMorant.SSM.vo.order.OrderFormVo;
import com.JaMorant.SSM.vo.order.OrderInfoQueryVo;
import com.JaMorant.SSM.vo.order.OrderInfoVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-04-28
 */
public interface OrderInfoService extends IService<OrderInfo> {


    //订单列表
    Map<String, Object> selectOrderInfoPage(Page<OrderInfo> pageParam, OrderInfoQueryVo orderInfoQueryVo);

    //生成订单方法
    Long submitOrder(OrderFormVo orderFormVo);

//    //订单id获取订单信息
//    OrderInfoVo getOrderInfoById(Long id);

    //订单id获取订单信息
    OrderInfoVo getOrderInfoById(Long id);

    //更新订单状态 ：已经支付
    void updateOrderStatus(String out_trade_no);
    //向订单中添加商品
    Long SaveGoodsToOrder(Long orderid, Long carshoping);
    //结束订单
    Long endOrder(OrderEndVo orderEndVo) throws IOException;

    List<OrderInfoVo> getOrderByUserid(Long id);

    Long SaveGoodsToOrderBycarid(Long carid, Long goodsid);

    List<OrderInfoVo> getOrderByCarId(Long id);

    public Map<String, Integer> getProvinceOrderCount() throws IOException;

    public List<Map<String, Object>> getCityOrderCount() throws IOException;


    List<OrderInfo> findByCreateTimeBetween(Date date2, Date date1);

    List<OrderInfoVo> getOrderByteacherId(Long id);

    OrderInfoVo GetOrderInfoVoByorderId(Long orderId);

    List<OrderInfo> getAll();
}
