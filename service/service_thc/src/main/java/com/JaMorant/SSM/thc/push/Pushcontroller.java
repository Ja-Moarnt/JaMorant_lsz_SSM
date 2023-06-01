package com.JaMorant.SSM.thc.push;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.JaMorant.SSM.client.car.CarFeignClient;
import com.JaMorant.SSM.client.teacher.TeacherFeignClient;
import com.JaMorant.SSM.model.thc.Invitation;
import com.JaMorant.SSM.model.thc.PurchaseInfo;
import com.JaMorant.SSM.model.thc.Tungy;
import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.model.vod.Teacher;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.thc.service.InvitationService;
import com.JaMorant.SSM.thc.service.PurchaseInfoService;
import com.JaMorant.SSM.thc.service.TungyService;
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
@RequestMapping(value = "/admin/thc/Push")
public class Pushcontroller {
    @Value("${wechat.mpAppId}")
    private  String appId;
    @Value("${wechat.mpAppSecret}")
    private  String appSecret;

    @Autowired
    private PurchaseInfoService purchaseInfoService;

    @Autowired
    private TungyService tungyService;

    @Autowired
    private CarFeignClient carFeignClient;
    @Autowired
    private TeacherFeignClient teacherFeignClient;

    @Autowired
    private InvitationService invitationService;
    //获取Access_Token
    public String getAccessToken() {
        String result = cn.hutool.http.HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        return jsonObject.getStr("access_token");
    }
    //发送订阅消息
    @GetMapping ("/SendPurchase")
    @ApiOperation("发送补货通知（参数为补货单id）")
    public Result SendPurchase(@RequestParam Long purchaseId){
        if (purchaseId==null)return Result.fail(null).message("补货单id输入为空");
        PurchaseInfo purchaseInfo=purchaseInfoService.selectPurchaseInfoById(purchaseId);
        if (purchaseInfo==null)return Result.fail(null).message("未查到该补货单！");
        Tungy tungy = tungyService.getById(purchaseInfo.getTungyId());
        JSONObject body=new JSONObject();
        body.set("touser",tungy.getOpenId());
        body.set("template_id","qAobJbIxgD3Lmjgh8lEOMxKez9XcNxO5sttYY_CpaUE");
        JSONObject json=new JSONObject();
        //补货单号
        json.set("character_string1",new JSONObject().set("value", purchaseInfo.getOutTradeNo()));
        //补货地址
        json.set("thing5",new JSONObject().set("value",tungy.getAddress()));
        //车牌号
        Car car = carFeignClient.GetCarById(purchaseInfo.getCarId());
        json.set("thing3",new JSONObject().set("value", car.getCarId()));
        //司机电话
        Teacher teacher = teacherFeignClient.GetTeacherById(car.getTeacherId());
        json.set("phone_number4",new JSONObject().set("value",teacher.getPhone()));
        //补货时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String PurchaseTime = simpleDateFormat.format(purchaseInfo.getStartTime());
        json.set("time8",new JSONObject().set("value", PurchaseTime));

        body.set("data",json);
        //发送
        String accessToken= getAccessToken();
        String post =  cn.hutool.http.HttpUtil.post("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken, body.toString());
        return Result.ok(post);
    }
    //发送订阅消息
    @GetMapping ("/SendInvitation")
    @ApiOperation("发送邀约通知（参数为邀约单id）")
    public Result SendInvitation(@RequestParam Long InvitationId){
        if (InvitationId==null)return Result.fail(null).message("邀约单id输入为空");
        Invitation invitation = invitationService.getById(InvitationId);
        if (invitation==null)return Result.fail(null).message("未查到该邀约单！");
        Tungy tungy = tungyService.getById(invitation.getTungyId());
        Car car = carFeignClient.GetCarById(invitation.getCarId());
        Teacher teacher = teacherFeignClient.GetTeacherById(car.getTeacherId());
        JSONObject body=new JSONObject();
        body.set("touser",teacher.getOpenId());
        body.set("template_id","DquM8EVcz3i_0OC-7dqUnb9y9z_eoR1PMxC5G7lwBZ4");
        JSONObject json=new JSONObject();
        //车牌号
        json.set("name1",new JSONObject().set("value", "车牌号"));
        //囤货场名称
        json.set("thing2",new JSONObject().set("value", tungy.getName()));
        //囤货场地址
        json.set("thing7",new JSONObject().set("value",tungy.getAddress()));
        //囤货场电话
        json.set("phone_number3",new JSONObject().set("value",tungy.getPhone()));
        //邀约福利
        json.set("thing8",new JSONObject().set("value", invitation.getWelfare()));

        body.set("data",json);
        //发送
        String accessToken= getAccessToken();
        String post =  cn.hutool.http.HttpUtil.post("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken, body.toString());
        System.out.println(post);
        return Result.ok(post);
    }

}
