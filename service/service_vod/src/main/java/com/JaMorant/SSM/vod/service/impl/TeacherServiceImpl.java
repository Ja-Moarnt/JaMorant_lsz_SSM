package com.JaMorant.SSM.vod.service.impl;

import com.JaMorant.SSM.model.vod.Teacher;
import com.JaMorant.SSM.vo.vod.TeacherLogin;
import com.JaMorant.SSM.vod.mapper.TeacherMapper;
import com.JaMorant.SSM.vod.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-04-13
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public Teacher login(TeacherLogin teacherLogin) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            wrapper.eq("phone",teacherLogin.getPhone())
            .eq("password",teacherLogin.getPassword());
        Teacher teacher = baseMapper.selectOne(wrapper);
        return teacher;
    }
}
