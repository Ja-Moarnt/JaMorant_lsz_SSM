package com.JaMorant.SSM.thc.controller;


import com.JaMorant.SSM.model.thc.PurchaseDetail;
import com.JaMorant.SSM.model.thc.PurchaseInfo;
import com.JaMorant.SSM.result.Result;
import com.JaMorant.SSM.thc.service.PurchaseDetailService;
import com.JaMorant.SSM.thc.service.PurchaseInfoService;
import com.JaMorant.SSM.utils.OrderNoUtils;
import com.JaMorant.SSM.vo.thc.PurchaseInfoQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 补货单表 前端控制器
 * </p>
 *
 * @author JaMorant
 * @since 2023-03-01
 */
@RestController
@Api(tags = "补货单接口")
@RequestMapping("/admin/thc/purchase")
public class PurchaseInfoController {
    @Autowired
    private PurchaseInfoService purchaseInfoService;

    @Autowired
    private PurchaseDetailService purchaseDetailService;

    //补货单列表
    @ApiOperation("补货单列表")
    @GetMapping("{page}/{limit}")
    public Result listOrder(@PathVariable Long page,
                            @PathVariable Long limit,
                            PurchaseInfoQueryVo purchaseInfoQueryVo) {
        //创建page对象
        Page<PurchaseInfo> pageParam = new Page<>(page,limit);
        Map<String,Object> map =
                purchaseInfoService.selectPurchaseInfoPage(pageParam,purchaseInfoQueryVo);
        return Result.ok(map);
    }

    //根据补货单id获取补货单
    @ApiOperation("根据补货单id获取补货单")
    @GetMapping("GetPurchase")
    public Result GetPurchase(@RequestParam Long purchaseId) {
        if (purchaseId==null)return Result.fail(null).message("补货单id输入为空");
        PurchaseInfo purchaseInfo=purchaseInfoService.selectPurchaseInfoById(purchaseId);
        if (purchaseInfo==null)return Result.fail(null).message("未查到该补货单！");
        return Result.ok(purchaseInfo);
    }

    //根据补货单id删除补货单
    @ApiOperation("根据补货单id删除补货单")
    @GetMapping("Remove")
    public Result Remove(@RequestParam Long purchaseId) {
        if (purchaseId==null)return Result.fail(null).message("补货单id输入为空");
        boolean isremove = purchaseInfoService.removeById(purchaseId);
        if (!isremove)return Result.fail(null).message("未查到该补货单！删除失败");
        return Result.ok(purchaseId).message("删除成功");
    }

    //新增补货单
    @ApiOperation("新增补货单")
    @PostMapping ("save")
    public Result save(@RequestBody PurchaseInfo purchaseInfo) {
        purchaseInfo.setOutTradeNo(OrderNoUtils.getOrderNo());
        purchaseInfoService.save(purchaseInfo);
        return Result.ok(purchaseInfo.getId()).message("新增补货单成功！");
    }

    //根据补货单id删除补货单
    @ApiOperation("根据补货单id修改补货单")
    @PostMapping ("Update")
    public Result Update(@RequestBody PurchaseInfo purchaseInfo) {
        boolean isupdate = purchaseInfoService.updateById(purchaseInfo);
        if (!isupdate)Result.fail(null).message("更新失败");
        return Result.ok(purchaseInfo.getId()).message("修改补货单成功！");
    }

    //根据补货单号获取订单详情
    @ApiOperation("根据补货单号获取补货单详情")
    @GetMapping("getPurchaseInfoDetail/{id}")
    public Result getPurchaseInfoDetail(@PathVariable Long id) {
        List<PurchaseDetail> orderDetailByOderid = purchaseDetailService.getPurchaseDetailByPurchseid(id);
        return Result.ok(orderDetailByOderid);
    }
    //往补货单里面添加商品
    @ApiOperation(value = "往补货单里面添加商品")
    @GetMapping ("SaveGoodsToPurchase")
    public Result SaveGoodsToPurchase(@RequestParam Long purchaseInfoId,
                                   @RequestParam Long goodsid,
                                      @RequestParam Integer buyCount) {
        if (purchaseInfoId==null||goodsid==null||buyCount==null){
            return Result.fail(null).message("参数输入有误");
        }
        if (buyCount==0)return Result.fail(null).message("补货数量不可以为0");
        Long purchaseId = purchaseDetailService.SaveGoodsToPurchase(purchaseInfoId,goodsid,buyCount);
        return Result.ok(purchaseId);
    }
    //根据补货单号获取订单详情
    @ApiOperation("根据补货单详情id获取补货单详情")
    @GetMapping("GetPurchaseDetail")
    public Result GetPurchaseDetail(@RequestParam Long id) {
        PurchaseDetail purchaseDetail = purchaseDetailService.getById(id);
        return Result.ok(purchaseDetail);
    }
    //根据补货单详情id修改补货单详情
    @ApiOperation("根据补货单详情id修改补货单详情")
    @PostMapping ("UpdatePurchaseDetail")
    public Result UpdatePurchaseDetail(@RequestBody PurchaseDetail purchaseDetail) {
        boolean isUpdate = purchaseDetailService.updateById(purchaseDetail);
        if (isUpdate)
        return Result.ok(purchaseDetail.getId()).message("修改补货单详情单成功!");
        else return Result.fail(null).message("修改补货单详情单失败!");
    }
    //根据补货单详情id删除补货单详情
    @ApiOperation("根据补货单详情id删除补货单详情")
    @GetMapping("RemovePurchaseDetail")
    public Result RemovePurchaseDetail(@RequestParam Long id) {
        boolean isRemove = purchaseDetailService.removeById(id);
        if (isRemove)
            return Result.ok(id).message("删除补货单详情单成功!");
        else return Result.fail(null).message("删除补货单详情单失败!");
    }

    //往补货单里面添加商品
    @ApiOperation(value = "结束补货单，推送补货消息")
    @GetMapping ("EndPurchase")
    public Result EndPurchase(@RequestParam Long purchaseId) {
        if (purchaseId==null) return Result.fail(null).message("参数输入有误");
        return Result.ok(purchaseId).message("补货单成功结束，已发送至指定囤货场");
    }
}

