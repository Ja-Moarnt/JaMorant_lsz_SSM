package com.JaMorant.SSM.thc.service;

import com.JaMorant.SSM.model.thc.PurchaseDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 补货单明细 服务类
 * </p>
 *
 * @author JaMorant
 * @since 2023-03-01
 */
public interface PurchaseDetailService extends IService<PurchaseDetail> {

    List<PurchaseDetail> getPurchaseDetailByPurchseid(Long id);

    PurchaseDetail getPurchaseDetailByPurchaseidAndgoodsid(Long purchaseId, Long goodsid);

    Long SaveGoodsToPurchase(Long purchaseInfoId, Long goodsid,Integer buyCount);
}
