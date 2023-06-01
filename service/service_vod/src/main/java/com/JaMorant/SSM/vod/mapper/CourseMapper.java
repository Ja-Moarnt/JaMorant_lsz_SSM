package com.JaMorant.SSM.vod.mapper;

import com.JaMorant.SSM.model.vod.Course;
import com.JaMorant.SSM.vo.vod.CoursePublishVo;
import com.JaMorant.SSM.vo.vod.CourseVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-04-22
 */
public interface CourseMapper extends BaseMapper<Course> {

    //根据课程id查询发布课程信息
    CoursePublishVo selectCoursePublishVoById(Long id);

    //根据课程id查询课程详情
    CourseVo selectCourseVoById(Long courseId);
}
