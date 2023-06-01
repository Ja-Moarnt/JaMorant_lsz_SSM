package com.JaMorant.SSM.vod.service.impl;

import com.JaMorant.SSM.client.chengshi.ChengshiFeignClient;
import com.JaMorant.SSM.client.order.OrderFeignClient;
import com.JaMorant.SSM.model.thc.Chengshi;
import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.model.vod.Goods;
import com.JaMorant.SSM.model.vod.GoodsVideo;
import com.JaMorant.SSM.model.vod.Teacher;
import com.JaMorant.SSM.vo.order.OrderCaridVo;
import com.JaMorant.SSM.vo.vod.CarFormVo;
import com.JaMorant.SSM.vo.vod.CarQueryVo;
import com.JaMorant.SSM.vo.vod.CarSaveVo;
import com.JaMorant.SSM.vod.count.BaiduMapService;
import com.JaMorant.SSM.vod.mapper.CarMapper;
import com.JaMorant.SSM.vod.service.CarService;
import com.JaMorant.SSM.vod.service.GoodsService;
import com.JaMorant.SSM.vod.service.GoodsVideoService;
import com.JaMorant.SSM.vod.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆 服务实现类
 * </p>
 *
 * @author JaMorant
 * @since 2023-01-13
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private GoodsVideoService goodsVideoService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderFeignClient orderFeignClient;

    @Autowired
    private ChengshiFeignClient chengshiFeignClient;

    @Autowired
    private BaiduMapService baiduMapService;

    @Override
    public Long SetorderidBycarId(OrderCaridVo orderCaridVo) {
        Car car = baseMapper.selectById(orderCaridVo.getCarId());
        Long orderId = orderCaridVo.getOrderId();
        car.setOrderId(orderId);
        Long i = Long.valueOf(baseMapper.updateById(car));
        return i;

    }


    @Override
    public Map<String, Object> findPageCar(Page<Car> pageParam, CarQueryVo carQueryVo) {
        Long teacherid = carQueryVo.getTeacherId();
        Long chengshiId = carQueryVo.getChengshiId();
        //判断条件为空，封装条件
        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(teacherid)) {
            wrapper.eq("teacher_id", teacherid);
        }
        if (!StringUtils.isEmpty(chengshiId)) {
            wrapper.eq("chengshi_id", chengshiId);
        }
        //调用方法实现条件查询分页
        Page<Car> carShopingPage = baseMapper.selectPage(pageParam, wrapper);
        long totalCount = carShopingPage.getTotal();
        long totalPage = carShopingPage.getPages();
        List<Car> records = carShopingPage.getRecords();
        //查询数据里面有几个id
        //讲师id  课程分类id（一层 和 二层）
        //获取这些id对应名称，进行封装，最终显示
        records.stream().forEach(item -> {
            this.getNameById(item);
        });
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);
        map.put("records", records);
        return map;
    }

    //审核状态未完成的
    @Override
    public Map<String, Object> findPageCarNot(Page<Car> pageParam, CarQueryVo carQueryVo) {
        Long teacherid = carQueryVo.getTeacherId();
        //判断条件为空，封装条件
        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(teacherid)) {
            wrapper.eq("teacher_id", teacherid);
        }
        //查询审核完成的列表
        wrapper.eq("car_status","0");
        //调用方法实现条件查询分页
        Page<Car> carShopingPage = baseMapper.selectPage(pageParam, wrapper);
        long totalCount = carShopingPage.getTotal();
        long totalPage = carShopingPage.getPages();
        List<Car> records = carShopingPage.getRecords();
        //查询数据里面有几个id
        //讲师id  课程分类id（一层 和 二层）
        //获取这些id对应名称，进行封装，最终显示
        records.stream().forEach(item -> {
            this.getNameById(item);
        });
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);
        map.put("records", records);
        return map;
    }

    @Override
    public List<Car> findPageCarYesAll() {
        QueryWrapper<Car> wrapper=new QueryWrapper<>();
        //查询审核完成的列表
        wrapper.eq("car_status","1");
        List<Car> carList = baseMapper.selectList(wrapper);
        return carList;
    }

    //审核状态完成的
    @Override
    public Map<String, Object> findPageCarYes(Page<Car> pageParam, CarQueryVo carQueryVo) {
        Long teacherid = carQueryVo.getTeacherId();
        //判断条件为空，封装条件
        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(teacherid)) {
            wrapper.eq("teacher_id", teacherid);
        }
        //查询审核完成的列表
        wrapper.eq("car_status","1");
        //调用方法实现条件查询分页
        Page<Car> carShopingPage = baseMapper.selectPage(pageParam, wrapper);
        long totalCount = carShopingPage.getTotal();
        long totalPage = carShopingPage.getPages();
        List<Car> records = carShopingPage.getRecords();
        //查询数据里面有几个id
        //讲师id  课程分类id（一层 和 二层）
        //获取这些id对应名称，进行封装，最终显示
        records.stream().forEach(item -> {
            this.getNameById(item);
        });
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);
        map.put("records", records);
        return map;
    }

    @Override
    public List<Car> GetCarBychengshiId(Long chengshiId) {
        QueryWrapper<Car> wrapper=new QueryWrapper<>();
        wrapper.eq("chengshi_id",chengshiId);
        wrapper.like("car_status","1");
        List<Car> carList = baseMapper.selectList(wrapper);
        return carList;
    }



    //获取这些id对应名称，进行封装，最终显示
    private Car getNameById(Car car) {
        if (car.getTeacherId() != null) {
            //根据讲师id获取讲师名称
            Teacher teacher = teacherService.getById(car.getTeacherId());
            if (teacher != null) {
                String name = teacher.getName();
                car.getParam().put("teacherName", name);
            }
        }
        if (car.getVideoId() != null) {
            //根据视频id获取视频名称
            GoodsVideo byId1 = goodsVideoService.getById(car.getVideoId());
            if (byId1 != null) {
                car.getParam().put("videoTitle", byId1.getTitle());
            }
        }
        if (car.getOrderId() != null) {
            //根据orderid获取订单流水号
            String byId = orderFeignClient.getById(car.getOrderId());
            if (byId != null) {
                car.getParam().put("OrderLiu", byId);
            }
        }
        if (car.getChengshiId() != null) {
            //根据orderid获取订单流水号
            Chengshi chengshi = chengshiFeignClient.GetChengshi(car.getChengshiId());

            if (chengshi != null) {
                car.getParam().put("Chengshi", chengshi.getTitle());
            }
        }
        return car;
    }



    @Override
    //添加
    public Long saveCarInfo(CarFormVo carFormVo) {
        Car car = new Car();
        BeanUtils.copyProperties(carFormVo, car);
        baseMapper.insert(car);
        return car.getId();
    }
    //根据id查询课程信息
    @Override
    public CarFormVo getCarInfoById(Long id) {
        //课程基本信息
        Car car = baseMapper.selectById(id);
        if (car == null) {
            return null;
        }
        CarFormVo carFormVo = new CarFormVo();
        BeanUtils.copyProperties(car, carFormVo);
        if(car.getVideoId()!=null) {
            GoodsVideo goodsVideo = goodsVideoService.getById(car.getVideoId());
            carFormVo.setTitle(goodsVideo.getTitle());
            Goods goods = goodsService.getById(goodsVideo.getGoodsId());
            carFormVo.setGoodsId(goods.getId());
            carFormVo.setSubjectId(goods.getSubjectId());
            carFormVo.setSubjectParentId(goods.getSubjectParentId());
        }
        if(car.getChengshiId()!=null) {
            Chengshi chengshi = chengshiFeignClient.GetChengshi(car.getChengshiId());
            carFormVo.setChengShi(chengshi.getTitle());
            carFormVo.setChengshiParentId(chengshi.getParentId());
        }
        carFormVo.setId(id);
        carFormVo.setCarId(car.getCarId());
        carFormVo.setTeacherId(car.getTeacherId());
        carFormVo.setVideoId(car.getVideoId());
        carFormVo.setChengshiId(car.getChengshiId());
        carFormVo.setOrderId(car.getOrderId());
        if (car.getOrderId()!=null){
            String OrderLiu = orderFeignClient.getById(car.getOrderId());
            carFormVo.setOrderLiu(OrderLiu);
        }
        carFormVo.setTimeStart(car.getTimeStart());
        carFormVo.setTimeEnd(car.getTimeEnd());
        carFormVo.setYingli(car.getYingli());
        carFormVo.setJifeng(car.getJifeng());
        carFormVo.setFengcheng(car.getFengcheng());


        return carFormVo;
    }

    //修改课程信息
    @Override
    public void updateCarId(CarFormVo carFormVo) {
        //修改课程基本信息
        Car car = new Car();
        BeanUtils.copyProperties(carFormVo, car);
        baseMapper.updateById(car);
        //修改课程描述信息
    }

    @Override
    public List<Car> getcarListByteacherId(Long teacherid) {
        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        if (teacherid!=null){
            wrapper.eq("teacher_id",teacherid);
        }
        List<Car> carList = baseMapper.selectList(wrapper);
        return carList;
    }


    @Override
    public Long saveCar(CarSaveVo carSaveVo) {
        Car car = new Car();
        BeanUtils.copyProperties(carSaveVo,car);
        //添加一些没有的属性
        car.setJifeng(new BigDecimal("0.0"));
        car.setFengcheng(new BigDecimal("0.0"));
        car.setYingli(new BigDecimal("0.0"));
        baseMapper.insert(car);


        return car.getId();
    }

    @Override
    public Integer getCarCountAll() {
        QueryWrapper<Car> wrapper=new QueryWrapper<>();
        wrapper.eq("car_status","1");
        List<Car> carList = baseMapper.selectList(wrapper);
        return carList.size();
    }

    @Override
    public int UploadLocation(Long carId, BigDecimal log, BigDecimal lat) {
        Car car = baseMapper.selectById(carId);
        car.setLongitude(log);
        car.setLatitude(lat);
        int i = baseMapper.updateById(car);
        return i;
    }

    @Override
    public List<Map<String, Object>> getCityCarCount() throws IOException {
        QueryWrapper<Car> wrapper=new QueryWrapper<>();
        List<Car> cars = baseMapper.selectList(wrapper);
        // 假设已经从数据库或其他数据源中获取了订单列表orderList
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Integer> provinceCityCountMap = new HashMap<>(); // 用于统计每个省份的城市数量
        for (Car car : cars) {

            BigDecimal longitude = car.getLongitude();
            BigDecimal latitude = car.getLatitude();
            String province = baiduMapService.getProvinceByLocation(longitude,latitude);
            String city = baiduMapService.getCityByLocation(longitude,latitude);
            // 统计省份和城市数量
            String provinceCity = province + "-" + city;
            if (provinceCityCountMap.containsKey(provinceCity)) {
                provinceCityCountMap.put(provinceCity, provinceCityCountMap.get(provinceCity) + 1);
            } else {
                provinceCityCountMap.put(provinceCity, 1);
            }

            // 查找当前省份是否已经存在于dataList中
            Map<String, Object> provinceMap = null;
            for (Map<String, Object> map : dataList) {
                if (map.get("name").equals(province)) {
                    provinceMap = map;
                    break;
                }
            }

            // 如果不存在，则创建新的省份数据
            if (provinceMap == null) {
                provinceMap = new HashMap<>();
                provinceMap.put("name", province);
                provinceMap.put("value", provinceCityCountMap.get(provinceCity));
                List<Map<String, Object>> cityList = new ArrayList<>();
                Map<String, Object> cityMap = new HashMap<>();
                cityMap.put("name", city);
                cityMap.put("value", new BigDecimal[] {longitude, latitude, BigDecimal.valueOf(1)});
                cityList.add(cityMap);
                provinceMap.put("date", cityList);
                dataList.add(provinceMap);
            } else {
                // 如果已经存在，则在对应的省份数据中添加城市数据
                List<Map<String, Object>> cityList = (List<Map<String, Object>>) provinceMap.get("date");
                boolean found = false;
                for (Map<String, Object> map : cityList) {
                    if (map.get("name").equals(city)) {
                        BigDecimal[] value = (BigDecimal[]) map.get("value");
                        value[2] = BigDecimal.valueOf(provinceCityCountMap.get(provinceCity));
                        map.put("value", value);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    Map<String, Object> cityMap = new HashMap<>();
                    cityMap.put("name", city);
                    cityMap.put("value", new BigDecimal[] {longitude, latitude, BigDecimal.valueOf(provinceCityCountMap.get(provinceCity))});
                    cityList.add(cityMap);
                }
                provinceMap.put("value", provinceCityCountMap.get(provinceCity));
            }
        }
        return dataList;
    }

}

