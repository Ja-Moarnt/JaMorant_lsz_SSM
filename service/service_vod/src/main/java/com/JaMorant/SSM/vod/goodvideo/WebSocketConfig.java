package com.JaMorant.SSM.vod.goodvideo;

import com.JaMorant.SSM.client.user.UserInfoFeignClient;
import com.JaMorant.SSM.model.user.UserInfo;
import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.vod.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.List;

/**
 * @author:JaMorant
 * @time:2023/3/6 21:13
 * @explain:
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private CarService carService;
    @Autowired
    private UserInfoFeignClient userInfoFeignClient;
    //通过DevTools对修改了 pageCarYesAll 的数据进行热部署
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        List<Car> pageCarYesAll = carService.findPageCarYesAll();
        List<UserInfo> userInfos = userInfoFeignClient.userAll();
        for (Car car:pageCarYesAll) {
            registry.addHandler(myHandler(), "/admin/vod/myHandler"+car.getId().toString()).setAllowedOrigins("*");
        }
        for (UserInfo userInfo:userInfos){
            registry.addHandler(myHandler(), "/admin/vod/user"+userInfo.getId().toString()).setAllowedOrigins("*");
        }
        registry.addHandler(myHandler(), "/admin/vod/pay").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new MyHandler();
    }
}
