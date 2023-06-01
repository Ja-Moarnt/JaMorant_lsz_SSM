package com.JaMorant.SSM.client.user;

import com.JaMorant.SSM.model.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "service-user")
public interface UserInfoFeignClient {

    @GetMapping("/admin/user/userInfo/inner/getById/{id}")
    public UserInfo getById(@PathVariable Long id);

    @GetMapping("/admin/user/userInfo/inner/userAll")
    public List<UserInfo> userAll();
}
