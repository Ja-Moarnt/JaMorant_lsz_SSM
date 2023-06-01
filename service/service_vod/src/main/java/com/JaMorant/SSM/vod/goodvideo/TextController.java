package com.JaMorant.SSM.vod.goodvideo;

import com.JaMorant.SSM.client.order.OrderFeignClient;
import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.model.vod.GoodsVideo;
import com.JaMorant.SSM.vo.order.OrderInfoVo;
import com.JaMorant.SSM.vod.service.CarService;
import com.JaMorant.SSM.vod.service.GoodsVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

/**
 * @author:JaMorant
 * @time:2023/3/5 22:46
 * @explain:
 */
@Controller
@RequestMapping("/admin/vod/text")
public class TextController {
    @Autowired
    private CarService carService;

    @Autowired
    private GoodsVideoService goodsVideoService;
    @Autowired
    private OrderFeignClient orderFeignClient;

    @GetMapping ("/send")
    @ResponseBody
    public String sendMessage(@RequestParam Long carId) throws IOException {
        List<WebSocketSession> sessions = MyHandler.getSessions();
        Car car = carService.getById(carId);
        Long videoId = car.getVideoId();
        if (videoId==null||car.getCarStatus().equals("0"))return null;
        GoodsVideo goodsVideo = goodsVideoService.getById(videoId);
        String videoSourceId = goodsVideo.getVideoSourceId();
        for (WebSocketSession session : sessions) {
            if (session.getUri().getPath().toString().equals("/admin/vod/myHandler"+carId.toString())) {
                session.sendMessage(new TextMessage(videoSourceId));
            }
        }
        return "Message sent";
    }

    @GetMapping ("/user")
    @ResponseBody
    public String sendMessageuser(@RequestParam Long orderId,@RequestParam Long userId) throws IOException {
        List<WebSocketSession> sessions = MyHandler.getSessions();
        OrderInfoVo orderInfoVo = orderFeignClient.GetOrderInfoVoByorderId(orderId);
        if (orderInfoVo==null)return "Message delivery failed";
//        ObjectMapper objectMapper = new ObjectMapper();
//        String orderInfoVoJson = objectMapper.writeValueAsString(orderInfoVo);
        for (WebSocketSession session : sessions) {
            if (session.getUri().getPath().toString().equals("/admin/vod/user"+userId.toString())) {
                System.out.println("发送消息：:" +orderId);
                session.sendMessage(new TextMessage(orderId.toString()));
            }
        }
        return "Message sent";
    }

    @GetMapping ("/sendpay")
    @ResponseBody
    public String sendMessagexx(@RequestParam Long orderId) throws IOException {
        List<WebSocketSession> sessions = MyHandler.getSessions();
        OrderInfoVo orderInfoVo = orderFeignClient.GetOrderInfoVoByorderId(orderId);
        if (orderInfoVo==null)return "Message delivery failed";
//        ObjectMapper objectMapper = new ObjectMapper();
//        String orderInfoVoJson = objectMapper.writeValueAsString(orderInfoVo);
        for (WebSocketSession session : sessions) {
            if (session.getUri().getPath().toString().equals("/admin/vod/pay")) {
                System.out.println("发送消息：:" +orderId);
                session.sendMessage(new TextMessage(orderId.toString()));
            }
        }
        return "Message sent";
    }


    @RequestMapping ("/video")
    public String boVideo(@RequestParam Long carId, Model model) {
        if (carId!=null) {
            Car car = carService.getById(carId);
            if (car!=null){
                model.addAttribute("car",car.getCarId());
            }
        }
        model.addAttribute("carId", carId);
        return "video3";
    }
}
