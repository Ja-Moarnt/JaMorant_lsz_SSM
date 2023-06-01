package com.JaMorant.SSM.thc.controller;


import com.JaMorant.SSM.model.thc.Tungy;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.thc.service.TungyService;
import com.JaMorant.SSM.thc.utils.HttpUtil;
import com.JaMorant.SSM.thc.utils.OpenIdJson;
import com.JaMorant.SSM.vo.thc.SaveTungyInfo;
import com.JaMorant.SSM.vo.user.Openid;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 囤管员 前端控制器
 * </p>
 *
 * @author JaMorant
 * @since 2023-02-20
 */
@RestController
@Slf4j
@Api(tags = "囤管员接口")
@RequestMapping("/admin/thc/tungy")
public class TungyController {

    @Value("${wechat.mpAppId}")
    private  String appID;
    @Value("${wechat.mpAppSecret}")
    private  String appSecret;

    @Autowired
    private TungyService tungyService;


    @ApiOperation(value = "根据userid获取用户信息")
    @GetMapping("inner/getById/{id}")
    public Tungy getById(@PathVariable Long id) {
        Tungy tungy = tungyService.getById(id);
        return tungy;
    }

    @ApiOperation(value = "根据openid获取用户信息")
    @GetMapping("inner/getByopenId/{openid}")
    public Tungy getByopenId(@PathVariable String openid) {
        Tungy tungy = tungyService.getTungyOpenid(openid);
        return tungy;
    }

    @ApiOperation(value = "根据code获取openid")
    @PostMapping("GetOpenid")
    public Result userLogin(@RequestParam("code") String code) throws IOException {
        String result = "";
        try{//请求微信服务器，用code换取openid。HttpUtil是工具类，后面会给出实现，Configure类是小程序配置信息，后面会给出代码
            result = HttpUtil.doGet(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="
                            + this.appID + "&secret="
                            + this.appSecret + "&js_code="
                            + code
                            + "&grant_type=authorization_code", null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        OpenIdJson openIdJson = mapper.readValue(result,OpenIdJson.class);
        System.out.println(result.toString());
        System.out.println(openIdJson.getOpenid());
        return Result.ok(result);
    }


    @ApiOperation(value = "保存openid到数据库返回userid")
    @PostMapping ("inner/saveopenid")
    public Result saveopenid(@RequestBody Openid opneid) {
        String openIdxx = opneid.getOpenId();
        if (!StringUtils.isEmpty(openIdxx)){
            if (tungyService.getTungyOpenid(openIdxx)!=null){
                return Result.fail(tungyService.getTungyOpenid(openIdxx).getId()).message("该openid已存在数据库中");
            }
            Tungy tungy = new Tungy(openIdxx);
            boolean save = tungyService.save(tungy);
            System.out.println(save);
            Tungy tungyOpenid = tungyService.getTungyOpenid(openIdxx);
            return Result.ok(tungyOpenid.getId());
        }
        return Result.fail(null).message("插入openid失败");
    }

    @ApiOperation(value = "根据openid保存用户表单")
    @PostMapping ("inner/savetungy")
    public Result saveopenid(@RequestBody SaveTungyInfo saveTungy) {
        String openId = saveTungy.getOpenId();
        String phone = saveTungy.getPhone();
        String name = saveTungy.getName();
        String nickName = saveTungy.getNickName();
        String password = saveTungy.getPassword();
        Long chengshi_id = saveTungy.getChengshi_id();
        String address = saveTungy.getAddress();
        String avatar = saveTungy.getAvatar();
        Integer sex = saveTungy.getSex();
        Time timeStart = saveTungy.getTimeStart();
        Time timeEnd = saveTungy.getTimeEnd();
        Tungy tungyOpenid = tungyService.getTungyOpenid(openId);
        if (tungyOpenid!=null){
            tungyOpenid.setAvatar(avatar);
            tungyOpenid.setNickName(nickName);
            tungyOpenid.setSex(sex);
            tungyOpenid.setChengshiId(chengshi_id);
            tungyOpenid.setAddress(address);
            tungyOpenid.setPhone(phone);
            tungyOpenid.setPassword(password);
            tungyOpenid.setName(name);
            tungyOpenid.setTimeStart(timeStart);
            tungyOpenid.setTimeEnd(timeEnd);
            boolean b = tungyService.updateById(tungyOpenid);
            System.out.println(b);
            return Result.ok(tungyOpenid.getId());
        }
        return Result.fail(null).message("插入用户信息失败");
    }

    @ApiOperation(value = "根据城市id获取该城市所有囤货场（囤管员）")
    @PostMapping ("GetTungyListByChengshiId")
    public Result GetTungyListByChengshiId(@RequestParam Long chenghsiId) {
        if (chenghsiId==null){
            return Result.fail(null).message("该城市id为空");
        }
        List<Tungy>  tungyList= tungyService.GetTungyListByChengshiId(chenghsiId);
        return Result.ok(tungyList);
    }

    @ApiOperation(value = "囤货场列表")
    @GetMapping  ("GetTungy/{page}/{limit}")
    public Result GetTungyListPage(@PathVariable Long page, @PathVariable Long limit) {
        Page<Tungy> pageParam = new Page<>(page,limit);
        Map<String,Object> map = tungyService.findPageTungy(pageParam);
        return Result.ok(map);
    }


}

