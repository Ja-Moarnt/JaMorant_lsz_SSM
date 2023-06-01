package com.JaMorant.SSM.wechat.controller;

import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.utils.AuthContextHolder;
import com.JaMorant.SSM.utils.Base64Util;
import com.JaMorant.SSM.vo.wechat.WxJsapiSignatureVo;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wechat/share")
public class ShareController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/getSignature")
    public Result getSignature(@RequestParam("url") String url) throws WxErrorException {
        String currentUrl = url.replace("guiguketan", "#");
        WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(currentUrl);

        WxJsapiSignatureVo wxJsapiSignatureVo = new WxJsapiSignatureVo();
        BeanUtils.copyProperties(jsapiSignature, wxJsapiSignatureVo);
        wxJsapiSignatureVo.setUserEedId(Base64Util.base64Encode(AuthContextHolder.getUserId()+""));
        return Result.ok(wxJsapiSignatureVo);
    }

}
