package com.JaMorant.SSM.client.userpay;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@FeignClient(value = "service-vod")
public interface UserPayFeignClient {

    @GetMapping ("/admin/vod/text/user")
    @ResponseBody
    public String sendMessageuser(@RequestParam Long orderId,@RequestParam Long userId) throws IOException;




}
