package com.JaMorant.SSM.vod.service.impl;

import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.model.vod.CarShoping;
import com.JaMorant.SSM.model.vod.Goods;
import com.JaMorant.SSM.model.vod.Subject;
import com.JaMorant.SSM.vo.vod.CarShopingFormVo;
import com.JaMorant.SSM.vo.vod.CarShopingQueryVo;
import com.JaMorant.SSM.vo.vod.CarShopingSaveVo;
import com.JaMorant.SSM.vod.service.CarService;
import com.JaMorant.SSM.vod.service.CarshopingService;
import com.JaMorant.SSM.vod.service.GoodsService;
import com.JaMorant.SSM.vod.service.SubjectService;
import com.JaMorant.SSM.vod.mapper.CarshopingMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆货物 服务实现类
 * </p>
 *
 * @author JaMorant
 * @since 2022-12-29
 */
@Service
public class CarshopingServiceImpl extends ServiceImpl<CarshopingMapper, CarShoping> implements CarshopingService {
    @Autowired
    CarService carService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SubjectService subjectService;

    @Override
    public Map<String, Object> findPageCarShoping(Page<CarShoping> pageParam, CarShopingQueryVo carShopingQueryVo) {
        Long carId = carShopingQueryVo.getCarId();
        //判断条件为空，封装条件
        QueryWrapper<CarShoping> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(carId)) {
            wrapper.eq("car_id",carId);
        }

        //调用方法实现条件查询分页
        Page<CarShoping> carShopingPage = baseMapper.selectPage(pageParam, wrapper);
        long totalCount = carShopingPage.getTotal();
        long totalPage = carShopingPage.getPages();
        List<CarShoping> records = carShopingPage.getRecords();
        //查询数据里面有几个id
        //讲师id  课程分类id（一层 和 二层）
        //获取这些id对应名称，进行封装，最终显示
        records.stream().forEach(item -> {
            this.getNameById(item);
        });
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);
        map.put("records",records);
        return map;
    }


    //获取这些id对应名称，进行封装，最终显示
    private CarShoping getNameById(CarShoping carShoping) {
        //根据carid获取车牌号
        Car car = carService.getById(carShoping.getCarId());
        if(car != null) {
            String carId = car.getCarId();
            carShoping.getParam().put("carId",carId);
        }

        //根据课程分类id获取课程分类名称
        Goods goods = goodsService.getById(carShoping.getGoodsId());
        if(goods != null) {
            carShoping.getParam().put("Title",goods.getTitle());
            carShoping.getParam().put("price",goods.getPrice());
            carShoping.getParam().put("Cover",goods.getCover());
        }
        Subject byId = subjectService.getById(goods.getSubjectId());
        if(byId != null) {
            carShoping.getParam().put("subjectTitle",byId.getTitle());
        }
        Subject byId2 = subjectService.getById(goods.getSubjectParentId());
        if(byId2 != null) {
            carShoping.getParam().put("subjectParentTitle",byId2.getTitle());
        }
        return carShoping;
    }

    @Override
    //添加
    public Long saveCarShopingInfo(CarShopingFormVo carShopingFormVo) {
        CarShoping carShoping = new CarShoping();
        BeanUtils.copyProperties(carShopingFormVo, carShoping);
        baseMapper.insert(carShoping);
        return carShoping.getId();
    }

    //根据id查询课程信息
    @Override
    public CarShopingFormVo getCarShopingInfoById(Long id) {
        //课程基本信息
        CarShoping carShoping = baseMapper.selectById(id);
        if (carShoping == null) {
            return null;
        }
        CarShopingFormVo carShopingFormVo = new CarShopingFormVo();
        BeanUtils.copyProperties(carShoping, carShopingFormVo);
        Goods goods = goodsService.getById(carShoping.getGoodsId());
        carShopingFormVo.setId(id);
        carShopingFormVo.setSubjectId(goods.getSubjectId());
        carShopingFormVo.setSubjectParentId(goods.getSubjectParentId());
        carShopingFormVo.setPrice(goods.getPrice());
        carShopingFormVo.setTitle(goods.getTitle());
        carShopingFormVo.setCover(goods.getCover());
        return carShopingFormVo;
    }

    //修改课程信息
    @Override
    public void updateCarShopingId(CarShopingFormVo carShopingFormVo) {
        //修改课程基本信息
        CarShoping carShoping = new CarShoping();
        BeanUtils.copyProperties(carShopingFormVo, carShoping);
        baseMapper.updateById(carShoping);
        //修改课程描述信息
    }

    //删除课程
    @Override
    public void removeCarShopingId(Long id) {
//        //根据课程id删除小节
//        videoService.removeVideoByCourseId(id);
//
//        //根据课程id删除章节
//        chapterService.removeChapterByCourseId(id);
//
//        //根据课程id删除课程描述
//        descriptionService.removeById(id);

        //根据课程id删除课程
        baseMapper.deleteById(id);
    }

    @Override
    public Long isSaveCarShoping(Long carId, Long goodsId) {
        QueryWrapper<CarShoping> wrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(carId)||StringUtils.isEmpty(goodsId)) {
            return null;
        }
        wrapper.eq("car_id", carId)
                .eq("goods_id",goodsId);
        CarShoping carShoping = baseMapper.selectOne(wrapper);
        if (carShoping==null)return null;
        else return carShoping.getId();
    }

    @Override
    public List<CarShoping> findCarShoping(CarShopingQueryVo carShopingQueryVo) {
        Long carId = carShopingQueryVo.getCarId();
        //判断条件为空，封装条件
        QueryWrapper<CarShoping> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(carId)) {
            wrapper.eq("car_id",carId);
        }

        //调用方法实现条件查询分页
        List<CarShoping> carShopingPage = baseMapper.selectList(wrapper);
        //查询数据里面有几个id
        //讲师id  课程分类id（一层 和 二层）
        //获取这些id对应名称，进行封装，最终显示
        carShopingPage.stream().forEach(item -> {
            this.getNameById(item);
        });
        return carShopingPage;
    }

    @Override
    public Long saveByYing(CarShopingSaveVo carShopingSaveVo) {
        Long carId = carShopingSaveVo.getCarId();
        Long goodsId = carShopingSaveVo.getGoodsId();
        //判断商品是否未新商品
        Long CarShopingid = this.isSaveCarShoping(carId, goodsId);
        if (CarShopingid!=null) {
            CarShoping carShoping = baseMapper.selectById(CarShopingid);
            carShoping.setShengCount(carShoping.getShengCount() + 1);
            baseMapper.updateById(carShoping);
            return CarShopingid;
        }else {
            CarShoping carShoping = new CarShoping();
            carShoping.setCarId(carId);
            carShoping.setShengCount(1);
            carShoping.setGoodsId(goodsId);
            baseMapper.insert(carShoping);
            return carShoping.getId();
        }

    }


    //根据carid遍历出该车辆中的所有商品
    @Override
    public List<CarShoping> findCarShopingByCarid(Long carId) {
        //判断条件为空，封装条件
        QueryWrapper<CarShoping> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(carId)) {
            wrapper.eq("car_id",carId);
        }

        List<CarShoping> carShopingList = baseMapper.selectList(wrapper);
        carShopingList.stream().forEach(item -> {
            this.getNameById(item);
        });

        return carShopingList;
    }
}
