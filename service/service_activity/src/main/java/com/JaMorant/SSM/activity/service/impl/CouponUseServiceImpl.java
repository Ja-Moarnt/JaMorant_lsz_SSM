package com.JaMorant.SSM.activity.service.impl;

import com.JaMorant.SSM.model.activity.CouponInfo;
import com.JaMorant.SSM.model.activity.CouponUse;
import com.JaMorant.SSM.activity.service.CouponInfoService;
import com.JaMorant.SSM.activity.mapper.CouponUseMapper;
import com.JaMorant.SSM.activity.service.CouponUseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 优惠券领用表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-04-29
 */
@Service
public class CouponUseServiceImpl extends ServiceImpl<CouponUseMapper, CouponUse> implements CouponUseService {
    @Autowired
    private CouponInfoService couponInfoService;
    @Override
    public List<CouponUse> getByuserId(Long userId) {
        QueryWrapper<CouponUse> wrapper=new QueryWrapper<>();
        if (!StringUtils.isEmpty(userId)){
            wrapper.eq("user_id",userId);
            wrapper.ge("expire_time",new Date());
            wrapper.eq("coupon_status","0");
        }
        List<CouponUse> couponUses = baseMapper.selectList(wrapper);
        for (CouponUse co:couponUses){
            TianCouponInfo(co);
        }
        return couponUses;
    }
    //判定优惠券该用户是否领取过
    @Override
    public boolean isSave(Long userId, Long couponid) {
        QueryWrapper<CouponUse> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId)
                .eq("coupon_id",couponid);
        CouponUse couponUse = baseMapper.selectOne(wrapper);
        if (couponUse!=null)return true;
        return false;
    }
    @Override
    public Long saveCouponUser(Long userId, Long couponid) {
        boolean save = this.isSave(userId, couponid);
        if (save)return null;
        CouponUse couponUse = new CouponUse();
        couponUse.setCouponId(couponid);
        couponUse.setUserId(userId);
        Date date = new Date();
        couponUse.setGetTime(date);
        couponUse.setCouponStatus("0");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 添加10天
        calendar.add(Calendar.DATE,7);
        Date time = calendar.getTime();
        couponUse.setExpireTime(time);
        int insert = baseMapper.insert(couponUse);
        return Long.valueOf(insert);
    }



    public void TianCouponInfo(CouponUse couponUse){
        CouponInfo couponInfo = couponInfoService.getById(couponUse.getCouponId());
        if(couponInfo != null) {
            String name = couponInfo.getCouponName();
            BigDecimal amount = couponInfo.getAmount();
            couponUse.getParam().put("CouponName",name);
            couponUse.getParam().put("amount",amount);
        }
    }
}
