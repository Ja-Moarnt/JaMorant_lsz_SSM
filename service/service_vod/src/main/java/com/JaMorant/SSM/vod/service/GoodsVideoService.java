package com.JaMorant.SSM.vod.service;

import com.JaMorant.SSM.model.vod.GoodsVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品宣传视频 服务类
 * </p>
 *
 * @author JaMorant
 * @since 2022-12-31
 */
public interface GoodsVideoService extends IService<GoodsVideo> {
    public void removeGoodsVideoById(Long id);
    public void removeGoodsVideoByGoodsId(Long id);

    //根据商品id获取商品的全部视频
    public List<GoodsVideo> getGoodsVideoByGoodsId(Long id);

    List<GoodsVideo> getVideoAll();
}
