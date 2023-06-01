package com.JaMorant.SSM.vod.controller;

import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "腾讯云点播")
@RestController
@RequestMapping("/admin/vod")
//@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

//    //返回客户端上传视频签名
//    @ApiOperation("返回客户端上传视频签名")
//    @GetMapping("sign")
//    public Result sign() {
//        Signature sign = new Signature();
//        // 设置 App 的云 API 密钥
//        sign.setSecretId(ConstantPropertiesUtil.ACCESS_KEY_ID);
//        sign.setSecretKey(ConstantPropertiesUtil.ACCESS_KEY_SECRET);
//        sign.setCurrentTime(System.currentTimeMillis() / 1000);
//        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
//        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
//        try {
//            String signature = sign.getUploadSignature();
//            System.out.println("signature : " + signature);
//            return Result.ok(signature);
//        } catch (Exception e) {
//            System.out.print("获取签名失败");
//            e.printStackTrace();
//            throw new GgktException(20001,"获取签名失败");
//        }
//    }

    //上传视频接口
    @PostMapping("upload")
    @ApiOperation("上传视频接口")
    public Result upload() {
        String fileId = vodService.updateVideo();
        return Result.ok(fileId);
    }

    //删除腾讯云视频
    @ApiOperation("删除腾讯云视频")
    @DeleteMapping("remove/{fileId}")
    public Result remove(@PathVariable String fileId) {
        vodService.removeVideo(fileId);
        return Result.ok(null);
    }
}
