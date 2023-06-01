package com.JaMorant.SSM.vod.service;

import com.JaMorant.SSM.model.vod.CarShoping;
import com.JaMorant.SSM.vo.vod.CarShopingFormVo;
import com.JaMorant.SSM.vo.vod.CarShopingQueryVo;
import com.JaMorant.SSM.vo.vod.CarShopingSaveVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆货物 服务类
 * </p>
 *
 * @author JaMorant
 * @since 2022-12-29
 */
public interface CarshopingService extends IService<CarShoping> {
    //点播课程列表
    Map<String, Object> findPageCarShoping(Page<CarShoping> pageParam, CarShopingQueryVo carShopingQueryVo);

    //添加课程基本信息
    Long saveCarShopingInfo(CarShopingFormVo carShopingFormVo);

    public CarShopingFormVo getCarShopingInfoById(Long id);

    public void updateCarShopingId(CarShopingFormVo carShopingFormVo);

    public void removeCarShopingId(Long id);

    //判断该carshoping种car_id和goods_id是否有同一个
    Long isSaveCarShoping(Long carId, Long goodsId);

    List<CarShoping> findCarShoping(CarShopingQueryVo carShopingQueryVo);

    Long saveByYing(CarShopingSaveVo carShopingSaveVo);

    List<CarShoping> findCarShopingByCarid(Long carId);
}
