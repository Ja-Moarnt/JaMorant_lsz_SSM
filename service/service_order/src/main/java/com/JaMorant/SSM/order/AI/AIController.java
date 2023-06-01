package com.JaMorant.SSM.order.AI;


import com.JaMorant.SSM.client.car.CarFeignClient;
import com.JaMorant.SSM.client.goods.GoodsFeignClient;
import com.JaMorant.SSM.order.service.OrderDetailService;
import com.JaMorant.SSM.order.service.OrderInfoService;
import com.JaMorant.SSM.client.user.UserInfoFeignClient;
import com.JaMorant.SSM.model.order.OrderDetail;
import com.JaMorant.SSM.model.order.OrderInfo;
import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vo.order.AICarGoods;
import com.JaMorant.SSM.vo.order.AIOrderGoods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 订单表 订单表 前端控制器
 * </p>
 *
 * @author JaMorant
 * @since 2022-04-28
 */
@Api(tags = "AI智能商品推荐")
@RestController
@RequestMapping(value="/admin/order/AI")
public class AIController {

    @Autowired
    private LinkPy linkPy;

    @Autowired
    private CarFeignClient carFeignClient;
    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private GoodsFeignClient goodsFeignClient;

    @Autowired
    private UserInfoFeignClient userInfoFeignClient;
    //根据订单号获取订单详情
    @GetMapping("WeekGoods")
    @ApiOperation("获取近一周每个订单的订单详情外加经纬度（AI算法需要）")
    public Result WeekGoods() {
        List<OrderDetail> weekGoods = orderDetailService.GetWeekGoods();
        List<AIOrderGoods> aiOrderGoods=new ArrayList<>();
        for (OrderDetail orderDetail:weekGoods){
            //如果经纬度不为空
            if (!StringUtils.isEmpty(orderDetail.getParam().get("longitude"))&&!StringUtils.isEmpty(orderDetail.getParam().get("latitude"))){
                Long userId = (Long) orderDetail.getParam().get("userId");
                Long carId = (Long) orderDetail.getParam().get("carId");
                BigDecimal longitude = (BigDecimal) orderDetail.getParam().get("longitude");
                BigDecimal latitude = (BigDecimal) orderDetail.getParam().get("latitude");
                AIOrderGoods orderGoods = new AIOrderGoods(userId, orderDetail.getGoodsId(), carId, longitude, latitude);
                aiOrderGoods.add(orderGoods);
            }
        }
        return Result.ok(aiOrderGoods);
    }


    //根据订单号获取订单详情
    @GetMapping("GetWeekGoods")
    @ApiOperation("获取该城市的车辆信息，查询近一周订单，查询在该车上消费最多的用户id(AI算法需要)")
    public Result GetWeekGoods(@RequestParam Long chengshiId) {
        //定义结果返回
        List<AIOrderGoods> aiOrderGoods=new ArrayList<>();
        //获取该城市的所有车辆信息
        List<Car> carList = carFeignClient.GetCarListBychengshiId(chengshiId);
        //近一周的商品详情表中查询在该车消费最多的用户
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date WeekAgo = calendar.getTime();
        Date now=new Date();
        List<OrderInfo> Weekorders = orderInfoService.findByCreateTimeBetween(WeekAgo, now);

        for (Car car : carList) {
            Long maxOrderCount = Long.valueOf(0);
            Long maxUserId = Long.valueOf(0);
            Map<Long, Long> userOrderCounts = new HashMap<>();

            for (OrderInfo orderInfo : Weekorders) {
                if (orderInfo.getCarId() == car.getId()) {
                    Long userId = orderInfo.getUserId();

                    // 统计每个用户在该车辆上的订单数
                    Long orderCount = userOrderCounts.getOrDefault(userId, Long.valueOf(0)) + 1;
                    userOrderCounts.put(userId, orderCount);

                    // 找到订单数最多的用户
                    if (orderCount > maxOrderCount) {
                        maxOrderCount = orderCount;
                        maxUserId = userId;
                    }
                }
            }
            AIOrderGoods carGoods = new AIOrderGoods(maxUserId, car.getId(), car.getLongitude(), car.getLatitude());
            aiOrderGoods.add(carGoods);
//            // 输出用户 ID 和车辆 ID
//            System.out.println("User ID: " + maxUserId + ", Car ID: " + car.getId());
        }
        return Result.ok(aiOrderGoods);
    }

    //更新训练模型数据，供平台管理端调用
    @GetMapping("UpdataTrain")
    @ApiOperation("更新训练模型数据，供平台管理端调用")
    public Result UpdataTrain() {
        //获取模型数据
        try {
            linkPy.getTrain();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("更新失败："+e);
        }
        System.out.println("已更新");
        return Result.ok("已更新");
    }

    //获取预测数据，供囤货场调用
    @GetMapping("GetAIGoods")
    @ApiOperation("获取预测数据，供囤货场调用")
    public Result GetAIGoods(@RequestParam Long chengshiId) {
        List<AICarGoods> aiCarGoods;
        //获取模型数据
        try {
            //获取要预测的数据
            linkPy.getData(chengshiId);
            //进行预测，得出predictions.csv文件
            linkPy.forecast();
            //读取predictions.csv文件
            aiCarGoods= linkPy.GetPredictions();
            for (AICarGoods aigoods:aiCarGoods){
                String carName = carFeignClient.GetCarById(aigoods.getCarId()).getCarId();
                String goodsName = goodsFeignClient.getById(aigoods.getGoodsId()).getTitle();
                String cover = goodsFeignClient.getById(aigoods.getGoodsId()).getCover();
                aigoods.setCarName(carName);
                aigoods.setGoodsName(goodsName);
                aigoods.setGoodsImg(cover);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("获取失败："+e);
        }
        return Result.ok(aiCarGoods);
    }
}

