package com.JaMorant.SSM.user.controller;


import com.JaMorant.SSM.model.user.UserInfo;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.user.service.UserInfoService;
import com.JaMorant.SSM.vo.user.SaveUserInfo;
import com.JaMorant.SSM.vo.user.Openid;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/admin/user/userInfo")
@Api(tags = "用户信息管理")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "根据userid获取用户信息")
    @GetMapping("inner/getById/{id}")
    public UserInfo getById(@PathVariable Long id) {
        UserInfo userInfo = userInfoService.getUserInfoById(id);
        return userInfo;
    }

    @ApiOperation(value = "根据openid获取用户信息")
    @GetMapping("inner/getByopenId/{openid}")
    public UserInfo getByopenId(@PathVariable String openid) {
        UserInfo userInfo = userInfoService.getUserInfoOpenid(openid);
        return userInfo;
    }

    @ApiOperation(value = "保存openid到数据库返回userid")
    @PostMapping ("inner/saveopenid")
    public Result saveopenid(@RequestBody Openid opneid) {
        String openIdxx = opneid.getOpenId();
        if (!StringUtils.isEmpty(openIdxx)){
            if (userInfoService.getUserInfoOpenid(openIdxx)!=null){
                return Result.fail(userInfoService.getUserInfoOpenid(openIdxx).getId()).message("该openid已存在数据库中");
            }
            UserInfo userInfo = new UserInfo(openIdxx);
            boolean save = userInfoService.save(userInfo);
            System.out.println(save);
            UserInfo userInfoOpenid = userInfoService.getUserInfoOpenid(openIdxx);
            return Result.ok(userInfoOpenid.getId());
        }
        return Result.fail(null).message("插入openid失败");
    }

    @ApiOperation(value = "根据openid保存用户表单")
    @PostMapping ("inner/saveuserInfo")
    public Result saveopenid(@RequestBody SaveUserInfo saveUserInfo) {
        String openId = saveUserInfo.getOpenId();
        String phone = saveUserInfo.getPhone();
        String name = saveUserInfo.getName();
        String nickName = saveUserInfo.getNickName();
        String password = saveUserInfo.getPassword();
        Long chengshiId = saveUserInfo.getChengshiId();
        String avatar = saveUserInfo.getAvatar();
        Integer sex = saveUserInfo.getSex();
        UserInfo userInfoOpenid = userInfoService.getUserInfoOpenid(openId);
        if (userInfoOpenid!=null){
            userInfoOpenid.setAvatar(avatar);
            userInfoOpenid.setNickName(nickName);
            userInfoOpenid.setSex(sex);
            userInfoOpenid.setChengshiId(chengshiId);
            userInfoOpenid.setPhone(phone);
            userInfoOpenid.setPassword(password);
            userInfoOpenid.setName(name);
            boolean b = userInfoService.updateById(userInfoOpenid);
            System.out.println(b);
            return Result.ok(userInfoOpenid.getId());
        }
        return Result.fail(null).message("插入用户信息失败");
    }
    @GetMapping("/inner/userAll")
    @ApiOperation("获取全部用户信息")
    public List<UserInfo> userAll(){
        List<UserInfo> userInfoList=userInfoService.getAll();
        return userInfoList;
    }

    @ApiOperation(value = "乘客列表")
    @GetMapping  ("GetUser/{page}/{limit}")
    public Result GetUserListPage(@PathVariable Long page, @PathVariable Long limit) {
        Page<UserInfo> pageParam = new Page<>(page,limit);
        Map<String,Object> map = userInfoService.findPageUser(pageParam);
        return Result.ok(map);
    }
}

