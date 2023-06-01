package com.JaMorant.SSM.order.push;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.JaMorant.SSM.client.car.CarFeignClient;
import com.JaMorant.SSM.order.service.OrderInfoService;
import com.JaMorant.SSM.client.teacher.TeacherFeignClient;
import com.JaMorant.SSM.client.user.UserInfoFeignClient;
import com.JaMorant.SSM.model.order.OrderInfo;
import com.JaMorant.SSM.model.user.UserInfo;
import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.model.vod.Teacher;
import com.JaMorant.SSM.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * @author:JaMorant
 * @time:2023/3/20 22:17
 * @explain:
 */
@RestController
@Api(tags = "消息推送管理")
@RequestMapping(value = "/admin/order/Push")
public class Pushcontroller {
    @Value("${wechat.mpAppId}")
    private  String appId;
    @Value("${wechat.mpAppSecret}")
    private  String appSecret;

    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private CarFeignClient carFeignClient;
    @Autowired
    private TeacherFeignClient teacherFeignClient;
    @Autowired
    private UserInfoFeignClient userInfoFeignClient;
    //获取Access_Token
    public String getAccessToken() {
        String result = cn.hutool.http.HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        return jsonObject.getStr("access_token");
    }
    //发送订阅消息
    @GetMapping ("/SendOrder")
    @ApiOperation("同时发送订单通知给司机和乘客（参数为订单id）")
    public Result SendOrder(@RequestParam Long orderId){
        if (orderId==null)return Result.fail(null).message("订单id输入为空");
        OrderInfo orderInfo = orderInfoService.getById(orderId);
        if (orderInfo==null)return Result.fail(null).message("未查到该订单！");
        UserInfo userInfo = userInfoFeignClient.getById(orderInfo.getUserId());
        Car car = carFeignClient.GetCarById(orderInfo.getCarId());
        Teacher teacher = teacherFeignClient.GetTeacherById(car.getTeacherId());
        //先给乘客发
        JSONObject chengke=new JSONObject();
        chengke.set("touser",userInfo.getOpenId());
        chengke.set("template_id","fZjuxiGdZqtuNy4zjNBZJoYjLnoy_m4fpFJyWy7gDmE");
        JSONObject json=new JSONObject();
        //订单流水号
        json.set("character_string5",new JSONObject().set("value", orderInfo.getOutTradeNo()));
        //订单原价
        json.set("amount6",new JSONObject().set("value",orderInfo.getOriginAmount()));
        //优惠券价格
        json.set("amount7",new JSONObject().set("value", orderInfo.getOriginAmount().subtract(orderInfo.getFinalAmount())));
        //支付价格
        json.set("amount8",new JSONObject().set("value",orderInfo.getFinalAmount()));
        //支付时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String PayTime = simpleDateFormat.format(orderInfo.getPayTime());
        json.set("time9",new JSONObject().set("value", PayTime));
        chengke.set("data",json);
        //发送
        String accessToken= getAccessToken();
        String post =  cn.hutool.http.HttpUtil.post("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken, chengke.toString());

        //给司机发送
        JSONObject siji=new JSONObject();
        siji.set("touser",teacher.getOpenId());
        siji.set("template_id","fZjuxiGdZqtuNy4zjNBZJoYjLnoy_m4fpFJyWy7gDmE");
        siji.set("data",json);
        //发送
        String post1 =  cn.hutool.http.HttpUtil.post("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken, siji.toString());
        String str=post+"\n"+post1;
        System.out.println(str);
        return Result.ok(str);
    }
}
