package com.JaMorant.SSM.order.service.impl;

import com.JaMorant.SSM.model.order.OrderDetail;
import com.JaMorant.SSM.model.order.OrderInfo;
import com.JaMorant.SSM.order.mapper.OrderDetailMapper;
import com.JaMorant.SSM.order.service.OrderDetailService;
import com.JaMorant.SSM.order.service.OrderInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-04-28
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
    @Autowired
    private OrderInfoService orderInfoService;
    @Override
    public List<OrderDetail> getOrderDetailByOderid(Long id){

        QueryWrapper<OrderDetail> wrapper=new QueryWrapper<>();
        if (!StringUtils.isEmpty(id)) {
            wrapper.eq("order_id", id);
        }
//        }else{
//            wrapper.eq("order_id",-404);
//        }
        List<OrderDetail> orderDetails = baseMapper.selectList(wrapper);
        return orderDetails;
    }

    @Override
    public OrderDetail getOrderDetailByOderidAndgoodsid(Long orderid, Long goodsid) {
        QueryWrapper<OrderDetail> wrapper=new QueryWrapper<>();
        if (StringUtils.isEmpty(orderid)||StringUtils.isEmpty(goodsid)) {
            return null;
        }
            wrapper.eq("goods_id", goodsid)
                    .eq("order_id",orderid);

        OrderDetail orderDetail = baseMapper.selectOne(wrapper);
        return orderDetail;
    }

    @Override
    public List<OrderDetail> GetWeekGoods() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date WeekAgo = calendar.getTime();
        Date now=new Date();
        QueryWrapper<OrderDetail> wrapper=new QueryWrapper<>();
        wrapper.ge("create_time",WeekAgo);
        wrapper.le("create_time",now);
        List<OrderDetail> orderDetails = baseMapper.selectList(wrapper);
        for (OrderDetail orderDetail:orderDetails){
            orderDetail=setJingWeiDu(orderDetail);
        }
        return orderDetails;
    }

    public OrderDetail setJingWeiDu(OrderDetail orderDetail) {
        Long orderId = orderDetail.getOrderId();
        OrderInfo order = orderInfoService.getById(orderId);
        orderDetail.getParam().put("longitude",order.getLongitude());
        orderDetail.getParam().put("latitude",order.getLatitude());
        orderDetail.getParam().put("userId",order.getUserId());
        orderDetail.getParam().put("carId",order.getCarId());
        return orderDetail;
    }

}
