package com.JaMorant.SSM.vod.service.impl;

import com.JaMorant.SSM.model.vod.GoodsVideo;
import com.JaMorant.SSM.vod.mapper.GoodsVideoMapper;
import com.JaMorant.SSM.vod.service.GoodsVideoService;
import com.JaMorant.SSM.vod.service.VodService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 商品宣传视频 服务实现类
 * </p>
 *
 * @author JaMorant
 * @since 2022-12-31
 */
@Service
public class GoodsVideoServiceImpl extends ServiceImpl<GoodsVideoMapper, GoodsVideo> implements GoodsVideoService {
    //注入
    @Autowired
    private VodService vodService;

    //删除小节 同时删除小节里面视频
    @Override
    public void removeGoodsVideoById(Long id) {
        //id查询小节
        GoodsVideo goodsVideo = baseMapper.selectById(id);
        //获取video里面视频id
        String videoSourceId = goodsVideo.getVideoSourceId();
        //判断视频id是否为空
        if(!StringUtils.isEmpty(videoSourceId)) {
            //视频id不为空，调用方法根据视频id删除腾讯云视频
            vodService.removeVideo(videoSourceId);
        }
        //根据id删除小节
        baseMapper.deleteById(id);
    }

    //根据课程id删除小节   删除所有小节视频
    @Override
    public void removeGoodsVideoByGoodsId(Long id) {
        //根据课程id查询课程所有小节
        QueryWrapper<GoodsVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id",id);
        List<GoodsVideo> goodsVideos = baseMapper.selectList(wrapper);
        //遍历所有小节集合得到每个小节，获取每个小节视频id
        for (GoodsVideo goodsvideo:goodsVideos) {
            //获取每个小节视频id
            String videoSourceId = goodsvideo.getVideoSourceId();
            //判断视频id是否为空，不为空，删除腾讯云视频
            if(!StringUtils.isEmpty(videoSourceId)) {
                //删除腾讯云视频
                vodService.removeVideo(videoSourceId);
            }
        }
        //根据课程id删除课程所有小节
        baseMapper.delete(wrapper);
    }

    //根据商品id获取商品的全部视频
    @Override
    public List<GoodsVideo> getGoodsVideoByGoodsId(Long id) {
        //根据课程id查询课程所有小节
        QueryWrapper<GoodsVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id",id);
        List<GoodsVideo> goodsVideos = baseMapper.selectList(wrapper);
        return goodsVideos;
    }

    @Override
    public List<GoodsVideo> getVideoAll() {
        QueryWrapper<GoodsVideo> wrapper=new QueryWrapper<>();
        List<GoodsVideo> goodsVideos = baseMapper.selectList(wrapper);
        return goodsVideos;
    }
}
