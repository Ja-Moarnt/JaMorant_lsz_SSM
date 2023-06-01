package com.JaMorant.SSM.activity.service;

import com.JaMorant.SSM.model.activity.CouponUse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 优惠券领用表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-04-29
 */
public interface CouponUseService extends IService<CouponUse> {

    List<CouponUse> getByuserId(Long userId);

    Long saveCouponUser(Long userId, Long couponid);

    //判定优惠券该用户是否领取过
    boolean isSave(Long userId, Long couponid);
}
