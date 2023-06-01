package com.JaMorant.SSM.order.count;

import com.JaMorant.SSM.order.service.OrderInfoService;
import com.JaMorant.SSM.model.order.OrderInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author:JaMorant
 * @time:2023/3/10 16:37
 * @explain:
 */
@RestController
@Api(tags = "订单统计（数据可视化接口）")
@RequestMapping("/admin/order/count")
public class CityController {

    @Autowired
    private BaiduMapService baiduMapService;

    @Autowired
    private OrderInfoService orderInfoService;

    @GetMapping("/city")
    @ApiOperation("根据目前经纬度获取城市")
    public String getCityByLocation(@RequestParam BigDecimal lng, @RequestParam BigDecimal lat) throws IOException {
        return baiduMapService.getCityByLocation(lng, lat);
    }
    @GetMapping("/province")
    @ApiOperation("根据目前经纬度获取省份")
    public String getProvinceByLocation(@RequestParam BigDecimal lng, @RequestParam BigDecimal lat) throws IOException {
        return baiduMapService.getProvinceByLocation(lng, lat);
    }

    @GetMapping("/ProvinceAll")
    @ApiOperation("根据目前经纬度统计省份的订单数")
    public Map<String, Integer> getProvinceOrderCount() throws IOException {
        return orderInfoService.getProvinceOrderCount();
    }

    @GetMapping("/CityAll")
    @ApiOperation("根据目前经纬度统计城市的订单数")
    public List<Map<String, Object>> getCityOrderCount() throws IOException {
        return orderInfoService.getCityOrderCount();
    }

    @GetMapping("/WeekDay")
    @ApiOperation("统计近一周的的订单数（按天数时间）")
    public List<Map<String, Object>> countOrdersByDay() {
        // 创建List对象来存储数据
        List<Map<String, Object>> dataList = new ArrayList<>();
        SimpleDateFormat newFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date WeekAgo = calendar.getTime();
        Date now=new Date();
        List<OrderInfo> orders = orderInfoService.findByCreateTimeBetween(WeekAgo, now);
        orders.remove(orders.size()-1);
        Map<String, Integer> orderCountByDate = new HashMap<>();
        for (OrderInfo order : orders) {
            String day = newFormatter.format(order.getCreateTime()); // 假设订单类中日期字段为date
            if (orderCountByDate.containsKey(day)) {
                orderCountByDate.put(day, orderCountByDate.get(day) + 1);
            } else {
                orderCountByDate.put(day, 1);
            }
        }
        for (String date : orderCountByDate.keySet()) {
            Map<String, Object> result = new HashMap<>();
            result.put("name", date);
            result.put("value", orderCountByDate.get(date));
            dataList.add(result);
        }
        return dataList;
    }

    @GetMapping("/WeekSales")
    @ApiOperation("统计近一周的的销售额（按天数时间）")
    public List<Map<String, Object>> WeekSalesOrdersByDay() {
        // 创建List对象来存储数据
        List<Map<String, Object>> dataList = new ArrayList<>();
        SimpleDateFormat newFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date WeekAgo = calendar.getTime();
        Date now=new Date();
        List<OrderInfo> orders = orderInfoService.findByCreateTimeBetween(WeekAgo, now);
        orders.remove(orders.size()-1);
        Map<String, BigDecimal> orderCountByDate = new HashMap<>();
        for (OrderInfo order : orders) {
            String day = newFormatter.format(order.getCreateTime()); // 假设订单类中日期字段为date
            if (orderCountByDate.containsKey(day)) {
                orderCountByDate.put(day, orderCountByDate.get(day).add(order.getFinalAmount()));
            } else {
                orderCountByDate.put(day, order.getFinalAmount());
            }
        }
        for (String date : orderCountByDate.keySet()) {
            Map<String, Object> result = new HashMap<>();
            result.put("name", date);
            result.put("value", orderCountByDate.get(date));
            dataList.add(result);
        }
        return dataList;
    }

    @GetMapping("/Turnover")
    @ApiOperation("订单总成交额")
    public Map<String, BigDecimal> Turnover() {
        Map<String,BigDecimal> Turnover=new HashMap<>();
        List<OrderInfo> orderInfoList=orderInfoService.getAll();
        BigDecimal bigDecimal = new BigDecimal("0.00");
        orderInfoList.remove(orderInfoList.size()-1);
        for (OrderInfo orderInfo:orderInfoList){
            bigDecimal= bigDecimal.add(orderInfo.getFinalAmount());
        }
        Turnover.put("Turnover",bigDecimal);
        return Turnover;
    }

}
//[
//    {
//    name:"江西省",
//    value:3,
//    date:{
//            { name:"赣州" ,value: [114.7635968,25.7933916,2] },
//            { name:"南昌" ,value: [115.7635968,28.7933916,1] }
//         }
//    },
//    {
//        name:"广东省",
//        value:1,
//        date:{
//        { name:"广州" ,value: [114.7635968,25.7933916,2] }
//        }
//    }
//]
//[
//        {
//            name:"2023-3-10",
//            value:5
//        },
//        {
//        name:"2023-3-7",
//        value:6
//        }
//        ]
