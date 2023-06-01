package com.JaMorant.SSM.live.service.impl;

import com.JaMorant.SSM.model.live.LiveCourseDescription;
import com.JaMorant.SSM.live.mapper.LiveCourseDescriptionMapper;
import com.JaMorant.SSM.live.service.LiveCourseDescriptionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-05-09
 */
@Service
public class LiveCourseDescriptionServiceImpl extends ServiceImpl<LiveCourseDescriptionMapper, LiveCourseDescription> implements LiveCourseDescriptionService {

    //获取直播课程描述信息
    @Override
    public LiveCourseDescription getLiveCourseById(Long id) {
        LambdaQueryWrapper<LiveCourseDescription> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LiveCourseDescription::getLiveCourseId,id);
        LiveCourseDescription liveCourseDescription = baseMapper.selectOne(wrapper);
        return liveCourseDescription;
    }
}
