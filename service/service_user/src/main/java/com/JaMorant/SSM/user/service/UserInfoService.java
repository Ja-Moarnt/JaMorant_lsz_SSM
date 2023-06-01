package com.JaMorant.SSM.user.service;

import com.JaMorant.SSM.model.user.UserInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-04-29
 */
public interface UserInfoService extends IService<UserInfo> {

    //openid查询
    UserInfo getUserInfoOpenid(String openId);
    //id查询
    UserInfo getUserInfoById(Long id);

    List<UserInfo> getAll();

    Map<String, Object> findPageUser(Page<UserInfo> pageParam);
}
