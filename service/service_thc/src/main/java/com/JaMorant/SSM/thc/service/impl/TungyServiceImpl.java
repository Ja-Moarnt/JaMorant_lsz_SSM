package com.JaMorant.SSM.thc.service.impl;

import com.JaMorant.SSM.model.thc.Chengshi;
import com.JaMorant.SSM.model.thc.Tungy;
import com.JaMorant.SSM.thc.mapper.TungyMapper;
import com.JaMorant.SSM.thc.service.ChengshiService;
import com.JaMorant.SSM.thc.service.TungyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 囤管员 服务实现类
 * </p>
 *
 * @author JaMorant
 * @since 2023-02-20
 */
@Service
public class TungyServiceImpl extends ServiceImpl<TungyMapper, Tungy> implements TungyService {

    @Autowired
    private ChengshiService chengshiService;
    @Override
    public Tungy getTungyOpenid(String openid) {
        QueryWrapper<Tungy> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id",openid);
        Tungy tungy = baseMapper.selectOne(wrapper);
        return tungy;
    }

    @Override
    public List<Tungy> GetTungyListByChengshiId(Long chenghsiId) {
        QueryWrapper<Tungy> wrapper = new QueryWrapper<>();
        wrapper.eq("chengshi_id",chenghsiId);
        List<Tungy> tungyList = baseMapper.selectList(wrapper);
        return tungyList;
    }

    @Override
    public Map<String, Object> findPageTungy(Page<Tungy> pageParam) {
        QueryWrapper<Tungy> wrapper = new QueryWrapper<>();
        //调用方法实现条件查询分页
        Page<Tungy> pages = baseMapper.selectPage(pageParam, wrapper);
        long totalCount = pages.getTotal();
        long totalPage = pages.getPages();
        List<Tungy> list = pages.getRecords();
        //查询数据里面有几个id
        //讲师id  课程分类id（一层 和 二层）
        //获取这些id对应名称，进行封装，最终显示
        list.stream().forEach(item -> {
            Chengshi chengshi = chengshiService.getById(item.getChengshiId());
            item.getParam().put("chengshi",chengshi.getTitle());
        });
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);
        map.put("records", list);
        return map;
    }
}
