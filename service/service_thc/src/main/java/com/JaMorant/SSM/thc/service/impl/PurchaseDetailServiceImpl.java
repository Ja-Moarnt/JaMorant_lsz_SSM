package com.JaMorant.SSM.thc.service.impl;

import com.JaMorant.SSM.client.goods.GoodsFeignClient;
import com.JaMorant.SSM.model.thc.PurchaseDetail;
import com.JaMorant.SSM.thc.mapper.PurchaseDetailMapper;
import com.JaMorant.SSM.thc.service.PurchaseDetailService;
import com.JaMorant.SSM.vo.vod.GoodsFormVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 补货单明细 服务实现类
 * </p>
 *
 * @author JaMorant
 * @since 2023-03-01
 */
@Service
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailMapper, PurchaseDetail> implements PurchaseDetailService {
    @Autowired
    private GoodsFeignClient goodsFeignClient;
    @Override
    public List<PurchaseDetail> getPurchaseDetailByPurchseid(Long id) {

        QueryWrapper<PurchaseDetail> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(id)) {
            wrapper.eq("purchase_id", id);
        }
//        }else{
//            wrapper.eq("order_id",-404);
//        }
        List<PurchaseDetail> purchaseDetails = baseMapper.selectList(wrapper);
        return purchaseDetails;
    }

    @Override
    public PurchaseDetail getPurchaseDetailByPurchaseidAndgoodsid(Long purchaseId, Long goodsid) {
        QueryWrapper<PurchaseDetail> wrapper=new QueryWrapper<>();
        if (StringUtils.isEmpty(purchaseId)||StringUtils.isEmpty(goodsid)) {
            return null;
        }
        wrapper.eq("goods_id", goodsid)
                .eq("purchase_id",purchaseId);

        PurchaseDetail purchaseDetail = baseMapper.selectOne(wrapper);
        return purchaseDetail;
    }

    @Override
    public Long SaveGoodsToPurchase(Long purchaseInfoId, Long goodsid,Integer buyCount) {
        //要先判断这个补货单对于这个商品是否已经添加了。
        PurchaseDetail purchaseDetailByPurchaseidAndgoodsid = this.getPurchaseDetailByPurchaseidAndgoodsid(purchaseInfoId, goodsid);
        if (purchaseDetailByPurchaseidAndgoodsid == null) {
            GoodsFormVo cargoods = goodsFeignClient.getById(goodsid);
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setPurchaseId(purchaseInfoId);
            purchaseDetail.setGoodsId(goodsid);
            purchaseDetail.setGoodsName(cargoods.getTitle());
            purchaseDetail.setCover(cargoods.getCover());
            purchaseDetail.setPrice(cargoods.getPrice());
            purchaseDetail.setBuyCount(buyCount);
            baseMapper.insert(purchaseDetail);
        } else {
            //如果该订单已经添加该商品则往该数量上+1
            purchaseDetailByPurchaseidAndgoodsid.setBuyCount(purchaseDetailByPurchaseidAndgoodsid.getBuyCount() + buyCount);
            baseMapper.updateById(purchaseDetailByPurchaseidAndgoodsid);
        }

        //2、修改车辆上面的商品库存
        return purchaseInfoId;
    }

}
