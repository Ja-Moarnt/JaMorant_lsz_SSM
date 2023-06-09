package com.JaMorant.SSM.activity.service.impl;

import com.JaMorant.SSM.client.user.UserInfoFeignClient;
import com.JaMorant.SSM.model.activity.CouponInfo;
import com.JaMorant.SSM.model.activity.CouponUse;
import com.JaMorant.SSM.model.user.UserInfo;
import com.JaMorant.SSM.vo.activity.CouponUseQueryVo;
import com.JaMorant.SSM.activity.service.CouponUseService;
import com.JaMorant.SSM.activity.mapper.CouponInfoMapper;
import com.JaMorant.SSM.activity.service.CouponInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-04-29
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

    @Autowired
    private CouponUseService couponUseService;

    @Autowired
    private UserInfoFeignClient userInfoFeignClient;

    //获取已经使用优惠券列表（条件查询分页）
    @Override
    public IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam,
                                                CouponUseQueryVo couponUseQueryVo) {
        //获取条件
        Long couponId = couponUseQueryVo.getCouponId();
        String couponStatus = couponUseQueryVo.getCouponStatus();
        String getTimeBegin = couponUseQueryVo.getGetTimeBegin();
        String getTimeEnd = couponUseQueryVo.getGetTimeEnd();
        //封装条件
        QueryWrapper<CouponUse> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(couponId)) {
            wrapper.eq("coupon_id",couponId);
        }
        if(!StringUtils.isEmpty(couponStatus)) {
            wrapper.eq("coupon_status",couponStatus);
        }
        if(!StringUtils.isEmpty(getTimeBegin)) {
            wrapper.ge("get_time",getTimeBegin);
        }
        if(!StringUtils.isEmpty(getTimeEnd)) {
            wrapper.le("get_time",getTimeEnd);
        }
        //调用方法条件分页查询
        IPage<CouponUse> pageModel = couponUseService.page(pageParam, wrapper);
        List<CouponUse> couponUseList = pageModel.getRecords();
        //遍历
        couponUseList.stream().forEach(item->{
            this.getUserInfoById(item);
        });
        return pageModel;
    }

    //更新优惠券
    @Override
    public void updateCouponInfoUseStatus(Long couponUseId, Long orderId) {
        CouponUse couponUse = new CouponUse();
        couponUse.setId(couponUseId);
        couponUse.setOrderId(orderId);
        couponUse.setCouponStatus("1");
        couponUse.setUsingTime(new Date());
        couponUse.setUsedTime(new Date());  //默认支付了
        couponUseService.updateById(couponUse);
    }

    @Override
    public List<CouponInfo> CouponListByEndTime() {
        QueryWrapper<CouponInfo> wrapper=new QueryWrapper<>();
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String format = sdf.format(date);
        Date date = new Date();
        wrapper.ge("end_time",date);
           wrapper.le("start_time",date);
        List<CouponInfo> couponInfos = baseMapper.selectList(wrapper);

        return couponInfos;
    }


    //根据用户id，通过远程调用得到用户信息
    private CouponUse getUserInfoById(CouponUse couponUse) {
        //获取用户id
        Long userId = couponUse.getUserId();
        if(!StringUtils.isEmpty(userId)) {
            //远程调用
            UserInfo userInfo = userInfoFeignClient.getById(userId);
            if(userInfo != null) {
                couponUse.getParam().put("nickName",userInfo.getNickName());
                couponUse.getParam().put("phone",userInfo.getPhone());
            }
        }
        return couponUse;
    }
}
