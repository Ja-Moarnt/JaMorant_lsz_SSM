package com.JaMorant.SSM.thc.service;

import com.JaMorant.SSM.model.thc.PurchaseInfo;
import com.JaMorant.SSM.vo.thc.PurchaseInfoQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 补货单表 服务类
 * </p>
 *
 * @author JaMorant
 * @since 2023-03-01
 */
public interface PurchaseInfoService extends IService<PurchaseInfo> {

    Map<String, Object> selectPurchaseInfoPage(Page<PurchaseInfo> pageParam, PurchaseInfoQueryVo purchaseInfoQueryVo);

    PurchaseInfo selectPurchaseInfoById(Long purchaseId);
}
