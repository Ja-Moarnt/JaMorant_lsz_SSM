package com.JaMorant.SSM.thc.service.impl;

import com.JaMorant.SSM.client.car.CarFeignClient;
import com.JaMorant.SSM.client.teacher.TeacherFeignClient;
import com.JaMorant.SSM.model.thc.Chengshi;
import com.JaMorant.SSM.model.thc.PurchaseInfo;
import com.JaMorant.SSM.model.thc.Tungy;
import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.model.vod.Teacher;
import com.JaMorant.SSM.thc.mapper.PurchaseInfoMapper;
import com.JaMorant.SSM.thc.service.ChengshiService;
import com.JaMorant.SSM.thc.service.PurchaseInfoService;
import com.JaMorant.SSM.thc.service.TungyService;
import com.JaMorant.SSM.vo.thc.PurchaseInfoQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 补货单表 服务实现类
 * </p>
 *
 * @author JaMorant
 * @since 2023-03-01
 */
@Service
public class PurchaseInfoServiceImpl extends ServiceImpl<PurchaseInfoMapper, PurchaseInfo> implements PurchaseInfoService {
    @Autowired
    private TungyService tungyService;

    @Autowired
    private CarFeignClient carFeignClient;

    @Autowired
    private ChengshiService chengshiService;

    @Autowired
    private TeacherFeignClient teacherFeignClient;

    //补货单列表
    @Override
    public Map<String, Object> selectPurchaseInfoPage(Page<PurchaseInfo> pageParam, PurchaseInfoQueryVo purchaseInfoQueryVo) {
        //purchaseInfoQueryVo获取查询条件
        Long carId = purchaseInfoQueryVo.getCarId();
        Long tungyId = purchaseInfoQueryVo.getTungyId();
        String purchaseStatus = purchaseInfoQueryVo.getPurchaseStatus();
        String createTimeBegin = purchaseInfoQueryVo.getCreateTimeBegin();
        String createTimeEnd = purchaseInfoQueryVo.getCreateTimeEnd();
            //判断条件值是否为空，不为空，进行条件封装
            QueryWrapper<PurchaseInfo> wrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(carId)) {
                wrapper.eq("car_id", carId);
            }
            if (!StringUtils.isEmpty(tungyId)) {
                wrapper.eq("tungy_id", tungyId);
            }
            if (!StringUtils.isEmpty(purchaseStatus)) {
                wrapper.eq("purchase_status", purchaseStatus);
            }
            if (!StringUtils.isEmpty(createTimeBegin)) {
                wrapper.ge("create_time", createTimeBegin);
            }
            if (!StringUtils.isEmpty(createTimeEnd)) {
                wrapper.le("create_time", createTimeEnd);
            }
            //调用实现条件分页查询
            Page<PurchaseInfo> pages = baseMapper.selectPage(pageParam, wrapper);
            long totalCount = pages.getTotal();
            long pageCount = pages.getPages();
            List<PurchaseInfo> records = pages.getRecords();
            records.stream().forEach(item -> {
                this.getCouPonPrice(item);
            });
            //所有需要数据封装map集合，最终返回
            Map<String, Object> map = new HashMap<>();
            map.put("total", totalCount);
            map.put("pageCount", pageCount);
            map.put("records", records);
            return map;
        }

    @Override
    public PurchaseInfo selectPurchaseInfoById(Long purchaseId) {
        PurchaseInfo purchaseInfo = baseMapper.selectById(purchaseId);
        if (purchaseInfo==null)return null;
        this.getCouPonPrice(purchaseInfo);
        return purchaseInfo;
    }

    //根据添加补货单的其他信息
    public PurchaseInfo getCouPonPrice(PurchaseInfo purchaseInfo){
        Long carId = purchaseInfo.getCarId();
        Long tungyId = purchaseInfo.getTungyId();
        if (tungyId!=null){
            Tungy tungy = tungyService.getById(tungyId);
            Chengshi chengshi = chengshiService.getById(tungy.getChengshiId());
            if (tungy!=null){
                purchaseInfo.getParam().put("tungyName",tungy.getName());
                purchaseInfo.getParam().put("tungyPhone",tungy.getPhone());
                purchaseInfo.getParam().put("tungyChenshi",chengshi.getTitle());
                purchaseInfo.getParam().put("tungyAddress",tungy.getAddress());
            }
        }
        if (carId!=null){
            Car car = carFeignClient.GetCarById(carId);
            Chengshi chengshi = chengshiService.getById(car.getChengshiId());
            Teacher teacher = teacherFeignClient.GetTeacherById(car.getTeacherId());
            if (car!=null){
                purchaseInfo.getParam().put("carId",car.getCarId());
                purchaseInfo.getParam().put("carChengshi",chengshi.getTitle());
                purchaseInfo.getParam().put("teacherPhone",teacher.getPhone());
                purchaseInfo.getParam().put("teacherName",teacher.getName());
            }
        }
        return purchaseInfo;
    }
}
