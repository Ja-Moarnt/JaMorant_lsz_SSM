package com.JaMorant.SSM.thc.service;

import com.JaMorant.SSM.model.thc.Tungy;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 囤管员 服务类
 * </p>
 *
 * @author JaMorant
 * @since 2023-02-20
 */
public interface TungyService extends IService<Tungy> {
    //根据openid获取囤管员
    Tungy getTungyOpenid(String openid);
    //根据城市id遍历出该城市所有囤管员
    List<Tungy> GetTungyListByChengshiId(Long chenghsiId);
    //囤管员列表
    Map<String, Object> findPageTungy(Page<Tungy> pageParam);
}
