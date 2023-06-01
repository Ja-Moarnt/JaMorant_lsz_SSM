package com.JaMorant.SSM.activity.api;

import com.JaMorant.SSM.activity.service.CouponInfoService;
import com.JaMorant.SSM.activity.service.CouponUseService;
import com.JaMorant.SSM.model.activity.CouponInfo;
import com.JaMorant.SSM.model.activity.CouponUse;
import com.JaMorant.SSM.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "优惠券接口")
@RestController
@RequestMapping("/api/activity/couponInfo")
public class CouponInfoApiController {

    @Autowired
    private CouponInfoService couponInfoService;

    @Autowired
    private CouponUseService couponUseService;

    //根据优惠券id查询
    @ApiOperation(value = "查看可领取的优惠券列表（领取截止时间内）")
    @GetMapping(value = "inner/CouponListByEndTime")
    public Result CouponListByEndTime() {
        List<CouponInfo> couponInfos = couponInfoService.CouponListByEndTime();
        return Result.ok(couponInfos);
    }

    //根据优惠券id查询
    @ApiOperation(value = "获取优惠券")
    @GetMapping(value = "inner/getById/{couponId}")
    public CouponInfo getById(@PathVariable("couponId") Long couponId) {
        CouponInfo couponInfo = couponInfoService.getById(couponId);
        return couponInfo;
    }

    //更新优惠券
    @ApiOperation(value = "更新优惠券使用状态")
    @GetMapping(value = "inner/updateCouponInfoUseStatus/{couponUseId}/{orderId}")
    public Boolean updateCouponInfoUseStatus(@PathVariable("couponUseId") Long couponUseId, @PathVariable("orderId") Long orderId) {
        couponInfoService.updateCouponInfoUseStatus(couponUseId, orderId);
        return true;
    }

    //根据优惠券id查询
    @ApiOperation(value = "根据领取表id获取优惠券")
    @GetMapping(value = "inner/getByCouponUserId/{couponuserId}")
    public Long getByCouponUserId(@PathVariable Long couponuserId) {
        Long couponid = couponUseService.getById(couponuserId).getCouponId();
        return couponid;
    }

    //根据用户id查询用户所领取的优惠券
    @ApiOperation(value = "根据用户id查询用户所领取的优惠券(使用截止日期内的未使用过的优惠券)")
    @GetMapping(value = "inner/getByUserId/{userId}")
    public Result getByUserId(@PathVariable Long userId) {
        List<CouponUse>  couponUseList=couponUseService.getByuserId(userId);
        return Result.ok(couponUseList);
    }

    //用户领取优惠券
    @ApiOperation(value = "用户领取优惠券")
    @GetMapping(value = "inner/saveCouponUser/{userId}/{couponid}")
    public Result saveCouponUser(@PathVariable Long userId,
                              @PathVariable Long couponid) {
        Long aLong = couponUseService.saveCouponUser(userId, couponid);
        if (aLong==null)return Result.fail(null).message("该优惠券你已经领取过了，不可重复领取");
        return Result.ok(null).message("领取成功");
    }

}
