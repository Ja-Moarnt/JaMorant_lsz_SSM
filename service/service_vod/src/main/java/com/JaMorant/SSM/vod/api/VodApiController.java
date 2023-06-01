package com.JaMorant.SSM.vod.api;

import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "腾讯视频点播")
@RestController
@RequestMapping("/api/vod")
public class VodApiController {

    @Autowired
    private VodService vodService;

    //点播视频播放接口
    @GetMapping("getPlayAuth/{videoId}")
    public Result getPlayAuth(
            @ApiParam(value = "视频id", required = true)
            @PathVariable Long videoId) {
        Map<String,Object> map = vodService.getPlayAuth(videoId);
        return Result.ok(map);
    }
}
