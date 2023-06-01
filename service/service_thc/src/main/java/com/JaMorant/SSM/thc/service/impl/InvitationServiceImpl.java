package com.JaMorant.SSM.thc.service.impl;

import com.JaMorant.SSM.client.car.CarFeignClient;
import com.JaMorant.SSM.model.thc.Invitation;
import com.JaMorant.SSM.model.thc.Tungy;
import com.JaMorant.SSM.model.vod.Car;
import com.JaMorant.SSM.thc.mapper.InvitationMapper;
import com.JaMorant.SSM.thc.service.InvitationService;
import com.JaMorant.SSM.thc.service.TungyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 补货邀约 服务实现类
 * </p>
 *
 * @author JaMorant
 * @since 2023-03-02
 */
@Service
public class InvitationServiceImpl extends ServiceImpl<InvitationMapper, Invitation> implements InvitationService {

    @Autowired
    private CarFeignClient carFeignClient;

    @Autowired
    TungyService tungyService;
    @Override
    public List<Invitation> GetInvitationListByCarid(Long carId) {
        QueryWrapper<Invitation> wrapper=new QueryWrapper<>();
        wrapper.eq("car_id",carId);
        List<Invitation> invitationList = baseMapper.selectList(wrapper);
        for (Invitation invitation:invitationList){
            this.JiaGong(invitation);
        }
        return invitationList;
    }

    @Override
    public List<Invitation> GetInvitationListByTungyId(Long tungyId) {
        QueryWrapper<Invitation> wrapper=new QueryWrapper<>();
        wrapper.eq("tungy_id",tungyId);
        List<Invitation> invitationList = baseMapper.selectList(wrapper);
        for (Invitation invitation:invitationList){
            this.JiaGong(invitation);
        }
        return invitationList;
    }

    @Override
    public List<Invitation> GetInvitationListByteacherId(Long teacherId) {
        List<Car> carList = carFeignClient.GetCarListByteacherId(teacherId);
        List<Invitation> invitationLists = new ArrayList<>();
        for (Car car:carList){
            List<Invitation> invitationList = this.GetInvitationListByCarid(car.getId());
            invitationLists.addAll(invitationList);
        }
        return invitationLists;
    }

    @Override
    public Invitation GetInvitationById(Long id) {
        Invitation invitation = baseMapper.selectById(id);
        if (invitation==null)
        return null;
        Invitation invitation1 = this.JiaGong(invitation);
        return invitation1;
    }

    public Invitation JiaGong(Invitation invitation){
        Long carId = invitation.getCarId();
        Long tungyId = invitation.getTungyId();
        Car car = carFeignClient.GetCarById(carId);
        Tungy tungy = tungyService.getById(tungyId);
        invitation.getParam().put("carID",car.getCarId());
        invitation.getParam().put("tungyName",tungy.getName());
        invitation.getParam().put("tungyPhone",tungy.getPhone());
        invitation.getParam().put("tungyAddress",tungy.getAddress());
        return invitation;
    }
}
