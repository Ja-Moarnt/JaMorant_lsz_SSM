package com.JaMorant.SSM.client.teacher;

import com.JaMorant.SSM.model.vod.Teacher;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-vod")
public interface TeacherFeignClient {

    @ApiOperation("根据teacherid获取司机信息")
    @GetMapping("/admin/vod/teacher/inner/getTeacher/{id}")
    public Teacher GetTeacherById(@PathVariable Long id) ;



}
