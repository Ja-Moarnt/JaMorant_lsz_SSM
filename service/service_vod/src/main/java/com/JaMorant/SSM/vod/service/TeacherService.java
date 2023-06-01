package com.JaMorant.SSM.vod.service;

import com.JaMorant.SSM.model.vod.Teacher;
import com.JaMorant.SSM.vo.vod.TeacherLogin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-04-13
 */
public interface TeacherService extends IService<Teacher> {

    Teacher login(TeacherLogin teacherLogin);
}
