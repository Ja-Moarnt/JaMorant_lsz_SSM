package com.JaMorant.SSM.thc.controller;


import com.JaMorant.SSM.model.thc.Invitation;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.thc.service.InvitationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 补货邀约 前端控制器
 * </p>
 *
 * @author JaMorant
 * @since 2023-03-02
 */
@RestController
@Api(tags = "补货邀约接口")
@RequestMapping("/admin/thc/invitation")
public class InvitationController {
    @Autowired
    private InvitationService invitationService;


    //根据carid获取所有囤货场对该车邀约列表
    @ApiOperation("根据carid获取所有囤货场对该车邀约列表")
    @GetMapping("GetListByCarId")
    public Result GetListByCarId(@RequestParam Long carId) {
        if (carId==null)return Result.fail(null).message("carid输入为空");
        List<Invitation> invitationList=invitationService.GetInvitationListByCarid(carId);
        return Result.ok(invitationList);
    }

    //根据carid获取所有囤货场对该车邀约列表
    @ApiOperation("根据司机id获取所有囤货场对该司机邀约列表")
    @GetMapping("GetListByteacherId")
    public Result GetListByteacherId(@RequestParam Long teacherId) {
        if (teacherId==null)return Result.fail(null).message("teacherId输入为空");
        List<Invitation> invitationList=invitationService.GetInvitationListByteacherId(teacherId);
        return Result.ok(invitationList);
    }

    //根据tungyId获取改囤货场对该城市车邀约列表
    @ApiOperation("根据tungyId获取改囤货场对该城市车邀约列表")
    @GetMapping("GetListByTungyId")
    public Result GetListByTungyId(@RequestParam Long tungyId) {
        if (tungyId==null)return Result.fail(null).message("tungyId输入为空");
        List<Invitation> invitationList=invitationService.GetInvitationListByTungyId(tungyId);
        return Result.ok(invitationList);
    }

    //根据Id获取邀约
    @ApiOperation("根据Id获取邀约")
    @GetMapping("GetInvitation")
    public Result GetInvitation(@RequestParam Long Id) {
        if (Id==null)return Result.fail(null).message("Id输入为空");
        Invitation invitation = invitationService.GetInvitationById(Id);
        return Result.ok(invitation);
    }

    //根据Id删除邀约（拒绝补货邀约）
    @ApiOperation("根据Id删除邀约（拒绝补货邀约）")
    @GetMapping("RemoveInvitation")
    public Result RemoveInvitation(@RequestParam Long Id) {
        if (Id==null)return Result.fail(null).message("Id输入为空");
        boolean isRemove = invitationService.removeById(Id);
        if (isRemove)
        return Result.ok(Id).message("补货邀约删除成功（拒绝邀约）");
        else
            return Result.fail(Id).message("补货邀约删除失败！") ;
    }

    //根据Id修改补货邀约
    @ApiOperation("根据Id修改补货邀约")
    @PostMapping ("UpdateInvitation")
    public Result UpdateInvitation(@RequestBody Invitation invitation) {
        boolean isUpdate = invitationService.updateById(invitation);
        if (isUpdate)
            return Result.ok(invitation.getId()).message("补货邀约修改成功");
        else
            return Result.fail(invitation.getId()).message("补货邀约修改失败！") ;
    }

    //根据Id修改补货邀约
    @ApiOperation("囤货场对司机（车）创建补货邀约")
    @PostMapping ("SaveInvitation")
    public Result SaveInvitation(@RequestBody Invitation invitation) {
        boolean isSave = invitationService.save(invitation);
        if (isSave)
            return Result.ok(invitation.getId()).message("补货邀约创建成功");
        else
            return Result.fail(invitation.getId()).message("补货邀约创建失败！") ;
    }


}

