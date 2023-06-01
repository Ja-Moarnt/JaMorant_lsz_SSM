package com.JaMorant.SSM.user.service.impl;

import com.JaMorant.SSM.client.chengshi.ChengshiFeignClient;
import com.JaMorant.SSM.model.thc.Chengshi;
import com.JaMorant.SSM.model.user.UserInfo;
import com.JaMorant.SSM.user.mapper.UserInfoMapper;
import com.JaMorant.SSM.user.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-04-29
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private ChengshiFeignClient chengshiFeignClient;

    //openid查询
    @Override
    public UserInfo getUserInfoOpenid(String openId) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", openId);
        UserInfo userInfo = baseMapper.selectOne(wrapper);
        this.getUserInfoOrder(userInfo);
        return userInfo;
    }


    //信息完善
    public void getUserInfoOrder(UserInfo userInfo) {
        Long chengshiId = userInfo.getChengshiId();
        if (chengshiId == null) return;
        Chengshi chengshi = chengshiFeignClient.GetChengshi(chengshiId);
        userInfo.getParam().put("chengshi", chengshi.getTitle());
    }

    //id查询
    @Override
    public UserInfo getUserInfoById(Long id) {
        UserInfo userInfo = baseMapper.selectById(id);
        this.getUserInfoOrder(userInfo);
        return userInfo;
    }

    @Override
    public List<UserInfo> getAll() {
        QueryWrapper<UserInfo> wrapper=new QueryWrapper<>();
        List<UserInfo> userInfoList = baseMapper.selectList(wrapper);
        return userInfoList;
    }

    @Override
    public Map<String, Object> findPageUser(Page<UserInfo> pageParam) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        //调用方法实现条件查询分页
        Page<UserInfo> pages = baseMapper.selectPage(pageParam, wrapper);
        long totalCount = pages.getTotal();
        long totalPage = pages.getPages();
        List<UserInfo> list = pages.getRecords();
        //查询数据里面有几个id
        //讲师id  课程分类id（一层 和 二层）
        //获取这些id对应名称，进行封装，最终显示
        list.stream().forEach(item -> {
            Chengshi chengshi = chengshiFeignClient.GetChengshi(item.getChengshiId());
            item.getParam().put("chengshi",chengshi.getTitle());
        });
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);
        map.put("records", list);
        return map;
    }
}

