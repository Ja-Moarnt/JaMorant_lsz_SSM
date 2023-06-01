package com.JaMorant.SSM.vod.service;

import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.vo.order.OrderCaridVo;
import com.JaMorant.SSM.vo.vod.CarFormVo;
import com.JaMorant.SSM.vo.vod.CarQueryVo;
import com.JaMorant.SSM.vo.vod.CarSaveVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆 服务类
 * </p>
 *
 * @author JaMorant
 * @since 2023-01-13
 */
public interface CarService extends IService<Car> {


    Long SetorderidBycarId(OrderCaridVo orderCaridVo);

    //管理员端的车列表
    Map<String, Object> findPageCar(Page<Car> pageParam, CarQueryVo carQueryVo);


    //审核状态未完成的
    Map<String, Object> findPageCarNot(Page<Car> pageParam, CarQueryVo carQueryVo);

    List<Car> findPageCarYesAll();
    //添加
    Long saveCarInfo(CarFormVo carFormVo);

    //根据id查询课程信息
    CarFormVo getCarInfoById(Long id);

    //修改课程信息
    void updateCarId(CarFormVo carFormVo);

    List<Car> getcarListByteacherId(Long teacherid);

    Long saveCar(CarSaveVo carSaveVo);

    Map<String, Object> findPageCarYes(Page<Car> pageParam, CarQueryVo carQueryVo);

    List<Car> GetCarBychengshiId(Long chengshiId);

    Integer getCarCountAll();

    int UploadLocation(Long carId, BigDecimal log, BigDecimal lat);

    List<Map<String, Object>> getCityCarCount() throws IOException;
}
