package com.JaMorant.SSM.activity.service;

import com.JaMorant.SSM.model.activity.CouponInfo;
import com.JaMorant.SSM.model.activity.CouponUse;
import com.JaMorant.SSM.vo.activity.CouponUseQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-04-29
 */
public interface CouponInfoService extends IService<CouponInfo> {

    //获取已经使用优惠券列表（条件查询分页）
    IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo);

    //更新优惠券
    void updateCouponInfoUseStatus(Long couponUseId, Long orderId);

    //优惠券列表
    List<CouponInfo> CouponListByEndTime();
}
