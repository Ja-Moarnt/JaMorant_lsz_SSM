package com.JaMorant.SSM.user.api;


import com.JaMorant.SSM.user.utils.ConstantPropertiesUtil;
import com.JaMorant.SSM.user.utils.HttpUtil;
import com.JaMorant.SSM.user.utils.OpenIdJson;
import com.JaMorant.SSM.result.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
/**
 * @author:JaMorant
 * @time:2023/2/18 11:36
 * @explain:
 */
@RestController
@Slf4j
@RequestMapping("/api/user/login")
public class LoginHandler {
    private String appID= ConstantPropertiesUtil.ACCESS_KEY_ID ;
    private String appSecret =ConstantPropertiesUtil.ACCESS_KEY_SECRET;

    @Autowired
    private WxMpService wxMpService;

    @PostMapping("GetOpenid")
    public Result userLogin(@RequestParam("code") String code) throws IOException {
        String result = "";
        try{//请求微信服务器，用code换取openid。HttpUtil是工具类，后面会给出实现，Configure类是小程序配置信息，后面会给出代码
            result = HttpUtil.doGet(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="
                            + this.appID + "&secret="
                            + this.appSecret + "&js_code="
                            + code
                            + "&grant_type=authorization_code", null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        OpenIdJson openIdJson = mapper.readValue(result,OpenIdJson.class);
        System.out.println(result.toString());
        System.out.println(openIdJson.getOpenid());
        return Result.ok(result);
    }


}
