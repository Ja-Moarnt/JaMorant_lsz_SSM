package com.JaMorant.SSM.order.service.impl;

import com.JaMorant.SSM.client.activity.CouponInfoFeignClient;
import com.JaMorant.SSM.client.car.CarFeignClient;
import com.JaMorant.SSM.client.user.UserInfoFeignClient;
import com.JaMorant.SSM.client.userpay.UserPayFeignClient;
import com.JaMorant.SSM.exception.GgktException;
import com.JaMorant.SSM.model.activity.CouponInfo;
import com.JaMorant.SSM.model.order.OrderDetail;
import com.JaMorant.SSM.model.order.OrderInfo;
import com.JaMorant.SSM.model.user.UserInfo;
import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.order.count.BaiduMapService;
import com.JaMorant.SSM.order.mapper.OrderInfoMapper;
import com.JaMorant.SSM.order.service.OrderDetailService;
import com.JaMorant.SSM.order.service.OrderInfoService;
import com.JaMorant.SSM.utils.OrderNoUtils;
import com.JaMorant.SSM.vo.order.*;
import com.JaMorant.SSM.vo.vod.GoodsFormVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 订单表 订单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-04-28
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    private OrderDetailService orderDetailService;

//    @Autowired
//    private CourseFeignClient courseFeignClient;
//    @Autowired
//    private CarService carService;

    @Autowired
    private CarFeignClient carFeignClient;
    @Autowired
    private CouponInfoFeignClient couponInfoFeignClient;
    @Autowired
    private com.JaMorant.SSM.client.goods.GoodsFeignClient GoodsFeignClient;
    @Autowired
    private UserInfoFeignClient userInfoFeignClient;
    @Autowired
    private BaiduMapService baiduMapService;

    @Autowired
    private UserPayFeignClient userPayFeignClient;
    //生成订单方法
    @Override
    public Long submitOrder(OrderFormVo orderFormVo) {
        //1 获取生成订单条件值
        //Long couponId = orderFormVo.getCouponId();  //优惠券号
        Long carId = orderFormVo.getCarId(); //车辆号
       //Long userId = AuthContextHolder.getUserId();
        Long userId = orderFormVo.getUserId();
        //优惠券领取表id
        Long couponUseId = orderFormVo.getCouponUseId();

        //2 判断当前用户,针对当前课程是否已经生成订单
        //课程id，用户id
//        LambdaQueryWrapper<OrderDetail> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(OrderDetail::getCourseId,courseId);
//        wrapper.eq(OrderDetail::getUserId,userId);
//        OrderDetail orderDetailExists = orderDetailService.getOne(wrapper);
//        if(orderDetailExists != null) {
//            return orderDetailExists.getId();//订单已经存在，直接返回订单id
//        }

//        //3 根据课程id查询课程信息
//        Course course = courseFeignClient.getById(courseId);
//        if(course == null) {
//            throw new GgktException(20001,"课程不存在");
//        }


        UserInfo userInfo = userInfoFeignClient.getById(userId);
        //2 根据用户id查询用户信息
        if (userInfo == null) {
            throw new GgktException(20001, "用户不存在");
        }


        //3 根据优惠券id查询优惠券信息
        BigDecimal couponReduce = new BigDecimal(0);
//        if (couponId != null) {
//            CouponInfo couponInfo = couponInfoFeignClient.getById(couponId);
//            couponReduce = couponInfo.getAmount();
//        }
//        if (couponUseId != null) {
//            Long CouponId = couponInfoFeignClient.getByCouponUserId(couponUseId);
//            CouponInfo byId = couponInfoFeignClient.getById(CouponId);
//            couponReduce=byId.getAmount();
//        }


        //4 封装订单生成需要数据到对象，完成添加订单
        //4.1 封装数据到OrderInfo对象里面，添加订单基本信息表
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setCarId(carId);
        orderInfo.setNickName(userInfo.getNickName());
        orderInfo.setPhone(userInfo.getPhone());
//        orderInfo.setOriginAmount(course.getPrice());
        orderInfo.setCouponUseId(couponUseId);  //将优惠券领取表id填入
        // orderInfo.setFinalAmount(orderInfo.getOriginAmount().subtract(orderInfo.getCouponReduce()));
        orderInfo.setOutTradeNo(OrderNoUtils.getOrderNo());
        //orderInfo.setTradeBody(course.getTitle());
        orderInfo.setOrderStatus("0");
        baseMapper.insert(orderInfo);

/*        //6.2 封装数据到OrderDetail对象里面，添加订单详情信息表
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setOrderId(orderInfo.getId());
//        orderDetail.setUserId(userId);
//        orderDetail.setCourseId(courseId);
//        orderDetail.setCourseName(course.getTitle());
//        orderDetail.setCover(course.getCover());
//        orderDetail.setOriginAmount(course.getPrice());
//        orderDetail.setCouponReduce(new BigDecimal(0));
//        orderDetail.setFinalAmount(orderDetail.getOriginAmount().subtract(orderDetail.getCouponReduce()));
//        orderDetailService.save(orderDetail);*/

//        //5 更新优惠券状态
//        if (couponUseId != null) {
//            //获取优惠金额
//            couponInfoFeignClient.updateCouponInfoUseStatus(orderFormVo.getCouponUseId(), orderInfo.getId());
//        }

        //6、将订单id保存到car中
        OrderCaridVo orderCaridVo = new OrderCaridVo();
        orderCaridVo.setCarId(carId);
        orderCaridVo.setOrderId(orderInfo.getId());
        carFeignClient.SetOrderIdByCarId(orderCaridVo);

        //7 返回订单id
        return orderInfo.getId();
    }

    //订单id获取订单信息
    @Override
    public OrderInfoVo getOrderInfoById(Long id) {
        //id查询订单基本信息和详情信息
        OrderInfo orderInfo = baseMapper.selectById(id);
        List<OrderDetail> orderDetailByOderid = orderDetailService.getOrderDetailByOderid(orderInfo.getId());

        //封装OrderInfoVo
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        BeanUtils.copyProperties(orderInfo, orderInfoVo);
        orderInfoVo.setOrderDetails(orderDetailByOderid);
//        orderInfoVo.setCourseId(orderDetail.getCourseId());
//        orderInfoVo.setCourseName(orderDetail.getCourseName());
        return orderInfoVo;
    }

    //更新订单状态 ：已经支付
    @Override
    public void updateOrderStatus(String out_trade_no) {
        //根据订单号查询订单
        LambdaQueryWrapper<OrderInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderInfo::getOutTradeNo, out_trade_no);
        OrderInfo orderInfo = baseMapper.selectOne(wrapper);

        //设置订单状态
        orderInfo.setOrderStatus("1");

        //调用方法更新
        baseMapper.updateById(orderInfo);
    }

    @Override
    public Long SaveGoodsToOrder(Long orderid, Long goodsid) {
        if (orderid==null){
            return null;
        }
        //要先判断这个订单对于这个商品是否已经添加了。
        OrderDetail orderDetailByOderidAndgoodsid = orderDetailService.getOrderDetailByOderidAndgoodsid(orderid, goodsid);
        if (orderDetailByOderidAndgoodsid == null) {
            GoodsFormVo cargoods = GoodsFeignClient.getById(goodsid);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderid);
            orderDetail.setGoodsId(goodsid);
            orderDetail.setGoodsName(cargoods.getTitle());
            orderDetail.setCover(cargoods.getCover());
            orderDetail.setPrice(cargoods.getPrice());
            orderDetail.setBuyCount(1);
            orderDetailService.save(orderDetail);
        } else {
            //如果该订单已经添加该商品则往该数量上+1
            orderDetailByOderidAndgoodsid.setBuyCount(orderDetailByOderidAndgoodsid.getBuyCount() + 1);
            orderDetailService.updateById(orderDetailByOderidAndgoodsid);
        }

        //2、修改车辆上面的商品库存
        return orderid;
    }

    @Override
    public Long endOrder(OrderEndVo orderEndVo) throws IOException {
        //获取carid
        Long carid = orderEndVo.getCarId();
        Long orderid = carFeignClient.getOrderBycarId(carid);
        if (orderid==null)return null;
        //1、计算价格把价格计算好写入到订单表中
        List<OrderDetail> orderDetailByOderid = orderDetailService.getOrderDetailByOderid(orderid);
        BigDecimal sum = new BigDecimal(0);
        for (OrderDetail orderDetail:orderDetailByOderid){
            BigDecimal aa1 = new BigDecimal(orderDetail.getBuyCount());
            aa1=aa1.multiply(orderDetail.getPrice());
            sum=sum.add(aa1);
        }
        OrderInfo orderInfo = baseMapper.selectById(orderid);
        orderInfo.setOrderStatus("1");
        orderInfo.setOriginAmount(sum);
        //查询该订单是否有优惠券
        Long couponUseId = orderInfo.getCouponUseId();
        if (couponUseId==null){
            orderInfo.setFinalAmount(sum); //直接把原价填入
        }else{
            //先查询优惠券
            Long couponId = couponInfoFeignClient.getByCouponUserId(orderInfo.getCouponUseId());
            CouponInfo couponInfo = couponInfoFeignClient.getById(couponId);
            orderInfo.setFinalAmount(sum.subtract(couponInfo.getAmount()));
            //2、修改优惠券领取表==》更新优惠券领取表
            //获取优惠金额
            couponInfoFeignClient.updateCouponInfoUseStatus(couponUseId, orderInfo.getId());
        }

        orderInfo.setPayTime(new Date());


        //3、把车辆信息中的orderid改成null
        //解决办法：把carid存到订单id中，开始的时候存orderid，结束的时候取orderid
        OrderCaridVo orderCaridVo = new OrderCaridVo();
        orderCaridVo.setCarId(carid);
        carFeignClient.SetOrderIdByCarId(orderCaridVo);

        //4、上传位置信息
        BigDecimal longitude = orderEndVo.getLongitude();//经度
        BigDecimal latitude = orderEndVo.getLatitude();//纬度
        orderInfo.setLongitude(longitude);
        orderInfo.setLatitude(latitude);
        String province = baiduMapService.getProvinceByLocation(longitude, latitude);
        String city = baiduMapService.getCityByLocation(longitude, latitude);
        orderInfo.setProvince(province);
        orderInfo.setCity(city);
        baseMapper.updateById(orderInfo);
        //5、自动支付
        userPayFeignClient.sendMessageuser(orderid,orderInfo.getUserId());
        return orderid;
    }

    //根据userid获取订单
    @Override
    public List<OrderInfoVo> getOrderByUserid(Long id) {
        QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        List<OrderInfo> orderInfos = baseMapper.selectList(wrapper);
        List<OrderInfoVo> orderInfoVos=new ArrayList<>();
        for (OrderInfo orderInfo:orderInfos){
            List<OrderDetail> orderDetailByOderid = orderDetailService.getOrderDetailByOderid(orderInfo.getId());

            //封装OrderInfoVo
            OrderInfoVo orderInfoVo = new OrderInfoVo();
            BeanUtils.copyProperties(orderInfo,orderInfoVo);
            orderInfoVo.setOrderDetails(orderDetailByOderid);
            orderInfoVos.add(orderInfoVo);
        }
        return orderInfoVos;
    }

    @Override
    public List<OrderInfoVo> getOrderByCarId(Long id) {
        QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("car_id",id);
        List<OrderInfo> orderInfos = baseMapper.selectList(wrapper);
        List<OrderInfoVo> orderInfoVos=new ArrayList<>();
        for (OrderInfo orderInfo:orderInfos){
            List<OrderDetail> orderDetailByOderid = orderDetailService.getOrderDetailByOderid(orderInfo.getId());

            //封装OrderInfoVo
            OrderInfoVo orderInfoVo = new OrderInfoVo();
            BeanUtils.copyProperties(orderInfo,orderInfoVo);
            orderInfoVo.setOrderDetails(orderDetailByOderid);
            orderInfoVos.add(orderInfoVo);
        }
        return orderInfoVos;
    }

    @Override
    public Long SaveGoodsToOrderBycarid(Long carid, Long goodsid) {
        Long orderid = carFeignClient.getOrderBycarId(carid);
        Long aLong = this.SaveGoodsToOrder(orderid, goodsid);
        return aLong;
    }




    //订单列表
    @Override
    public Map<String, Object> selectOrderInfoPage(Page<OrderInfo> pageParam,
                                                   OrderInfoQueryVo orderInfoQueryVo) {
        //orderInfoQueryVo获取查询条件
        Long userId = orderInfoQueryVo.getUserId();
        Long carId = orderInfoQueryVo.getCarId();
        String outTradeNo = orderInfoQueryVo.getOutTradeNo();
        String phone = orderInfoQueryVo.getPhone();
        String createTimeEnd = orderInfoQueryVo.getCreateTimeEnd();
        String createTimeBegin = orderInfoQueryVo.getCreateTimeBegin();
        Integer orderStatus = orderInfoQueryVo.getOrderStatus();

        //判断条件值是否为空，不为空，进行条件封装
        QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(orderStatus)) {
            wrapper.eq("order_status", orderStatus);
        }
        if (!StringUtils.isEmpty(userId)) {
            wrapper.eq("user_id", userId);
        }
        if (!StringUtils.isEmpty(carId)) {
            wrapper.eq("car_id", carId);
        }
        if (!StringUtils.isEmpty(outTradeNo)) {
            wrapper.eq("out_trade_no", outTradeNo);
        }
        if (!StringUtils.isEmpty(phone)) {
            wrapper.eq("phone", phone);
        }
        if (!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge("create_time", createTimeBegin);
        }
        if (!StringUtils.isEmpty(createTimeEnd)) {
            wrapper.le("create_time", createTimeEnd);
        }
        //调用实现条件分页查询
        Page<OrderInfo> pages = baseMapper.selectPage(pageParam, wrapper);
        long totalCount = pages.getTotal();
        long pageCount = pages.getPages();
        List<OrderInfo> records = pages.getRecords();
        records.stream().forEach(item -> {
            this.getCouPonPrice(item);
        });
        //所有需要数据封装map集合，最终返回
        Map<String, Object> map = new HashMap<>();
        map.put("total", totalCount);
        map.put("pageCount", pageCount);
        map.put("records", records);
        return map;
    }

    //根据优惠券领取表id获取优惠券金额
    public OrderInfo getCouPonPrice(OrderInfo orderInfo){
        Long couponUseId = orderInfo.getCouponUseId();
        Long userId = orderInfo.getUserId();
        Long carId = orderInfo.getCarId();
        if (couponUseId!=null){

            Long couponId = couponInfoFeignClient.getByCouponUserId(couponUseId);
            CouponInfo couponInfo = couponInfoFeignClient.getById(couponId);
            orderInfo.getParam().put("CouPonName",couponInfo.getCouponName());
            orderInfo.getParam().put("CouPonPrice",couponInfo.getAmount());
        }
        if (userId!=null){
            UserInfo userInfo = userInfoFeignClient.getById(userId);
            if (userInfo!=null){
                orderInfo.getParam().put("userNickName",userInfo.getNickName());
            }
        }
        if (carId!=null){
            Car car = carFeignClient.GetCarById(carId);
            if (car!=null){
                orderInfo.getParam().put("carId",car.getCarId());
            }
        }
        return orderInfo;
    }
    @Override
    public Map<String, Integer> getProvinceOrderCount() throws IOException {
        QueryWrapper<OrderInfo> wrapper=new QueryWrapper<>();
        List<OrderInfo> orders = baseMapper.selectList(wrapper);
        orders.remove(orders.size()-1);
        Map<String, Integer> provinceOrderCount = new HashMap<>();
        for (OrderInfo order : orders) {
            String province =order.getProvince();
            Integer count = provinceOrderCount.get(province);
            if (count == null) {
                count = 0;
            }
            provinceOrderCount.put(province, count + 1);
        }
        return provinceOrderCount;
    }
    @Override
    public List<Map<String, Object>> getCityOrderCount() throws IOException {
        QueryWrapper<OrderInfo> wrapper=new QueryWrapper<>();
        List<OrderInfo> orders = baseMapper.selectList(wrapper);
        orders.remove(orders.size()-1);
        // 假设已经从数据库或其他数据源中获取了订单列表orderList
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Integer> provinceCityCountMap = new HashMap<>(); // 用于统计每个省份的城市数量
        for (OrderInfo order : orders) {
            String province = order.getProvince();
            String city = order.getCity();
            BigDecimal longitude = order.getLongitude();
            BigDecimal latitude = order.getLatitude();

            // 统计省份和城市数量
            String provinceCity = province + "-" + city;
            if (provinceCityCountMap.containsKey(provinceCity)) {
                provinceCityCountMap.put(provinceCity, provinceCityCountMap.get(provinceCity) + 1);
            } else {
                provinceCityCountMap.put(provinceCity, 1);
            }

            // 查找当前省份是否已经存在于dataList中
            Map<String, Object> provinceMap = null;
            for (Map<String, Object> map : dataList) {
                if (map.get("name").equals(province)) {
                    provinceMap = map;
                    break;
                }
            }

            // 如果不存在，则创建新的省份数据
            if (provinceMap == null) {
                provinceMap = new HashMap<>();
                provinceMap.put("name", province);
                provinceMap.put("value", provinceCityCountMap.get(provinceCity));
                List<Map<String, Object>> cityList = new ArrayList<>();
                Map<String, Object> cityMap = new HashMap<>();
                cityMap.put("name", city);
                cityMap.put("value", new BigDecimal[] {longitude, latitude, BigDecimal.valueOf(1)});
                cityList.add(cityMap);
                provinceMap.put("date", cityList);
                dataList.add(provinceMap);
            } else {
                // 如果已经存在，则在对应的省份数据中添加城市数据
                List<Map<String, Object>> cityList = (List<Map<String, Object>>) provinceMap.get("date");
                boolean found = false;
                for (Map<String, Object> map : cityList) {
                    if (map.get("name").equals(city)) {
                        BigDecimal[] value = (BigDecimal[]) map.get("value");
                        value[2] = BigDecimal.valueOf(provinceCityCountMap.get(provinceCity));
                        map.put("value", value);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    Map<String, Object> cityMap = new HashMap<>();
                    cityMap.put("name", city);
                    cityMap.put("value", new BigDecimal[] {longitude, latitude, BigDecimal.valueOf(provinceCityCountMap.get(provinceCity))});
                    cityList.add(cityMap);
                }
                provinceMap.put("value", provinceCityCountMap.get(provinceCity));
            }
        }
        return dataList;
    }

    @Override
    public List<OrderInfo> findByCreateTimeBetween(Date date2, Date date1) {
        QueryWrapper<OrderInfo> wrapper=new QueryWrapper<>();
        if (date2!=null){
            wrapper.ge("create_time",date2);
        }
        if (date2!=null) {
            wrapper.le("create_time", date1);
        }
        List<OrderInfo> orderInfos = baseMapper.selectList(wrapper);
        return orderInfos;
    }

    @Override
    public List<OrderInfoVo> getOrderByteacherId(Long id) {
        List<Car> carList = carFeignClient.GetCarListByteacherId(id);
        List<OrderInfoVo> orderInfoList=new ArrayList<>();
        for (Car car:carList){
            List<OrderInfoVo> orders = this.getOrderByCarId(car.getId());
            orderInfoList.addAll(orders);

        }
        return orderInfoList;
    }

    @Override
    public OrderInfoVo GetOrderInfoVoByorderId(Long orderId) {
        OrderInfo orderInfo = baseMapper.selectById(orderId);
        if (orderInfo==null)return null;
        List<OrderDetail> orderDetailByOderid = orderDetailService.getOrderDetailByOderid(orderInfo.getId());
        //封装OrderInfoVo
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        BeanUtils.copyProperties(orderInfo,orderInfoVo);
        orderInfoVo.setOrderDetails(orderDetailByOderid);
        return orderInfoVo;
    }

    @Override
    public List<OrderInfo> getAll() {
        QueryWrapper<OrderInfo> wrapper=new QueryWrapper<>();
        List<OrderInfo> orderInfoList = baseMapper.selectList(wrapper);
        return orderInfoList;
    }


}
