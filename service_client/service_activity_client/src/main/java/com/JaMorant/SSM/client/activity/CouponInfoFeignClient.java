package com.JaMorant.SSM.client.activity;

import com.JaMorant.SSM.model.activity.CouponInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-activity")
public interface CouponInfoFeignClient {

    @ApiOperation(value = "获取优惠券")
    @GetMapping(value = "/api/activity/couponInfo/inner/getById/{couponId}")
    CouponInfo getById(@PathVariable("couponId") Long couponId);

    /**
     * 更新优惠券使用状态
     */
    @GetMapping(value = "/api/activity/couponInfo/inner/updateCouponInfoUseStatus/{couponUseId}/{orderId}")
    Boolean updateCouponInfoUseStatus(@PathVariable("couponUseId") Long couponUseId, @PathVariable("orderId") Long orderId);

    @ApiOperation(value = "根据领取表id获取优惠券id")
    @GetMapping(value = "/api/activity/couponInfo/inner/getByCouponUserId/{couponuserId}")
    Long getByCouponUserId(@PathVariable Long couponuserId);
}
