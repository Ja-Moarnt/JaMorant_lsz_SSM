package com.JaMorant.SSM.order.count;


import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author:JaMorant
 * @time:2023/3/10 16:36
 * @explain:
 */
@Service
public class BaiduMapService {

    private static final String BAIDU_MAP_API_URL = "http://api.map.baidu.com/reverse_geocoding/v3/";

    private final OkHttpClient client = new OkHttpClient();

    public String getCityByLocation(BigDecimal lng, BigDecimal lat) throws IOException {
        String url = BAIDU_MAP_API_URL + "?ak=AGmMEUezksee79AQAxRax9mk9kGZknlV&output=json&coordtype=wgs84ll&location=" + lat + "," + lng;
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONObject addressComponent = result.getJSONObject("addressComponent");
            return addressComponent.getString("city");
        }
    }

    public String getProvinceByLocation(BigDecimal lng, BigDecimal lat) throws IOException {
        String url = BAIDU_MAP_API_URL + "?ak=AGmMEUezksee79AQAxRax9mk9kGZknlV&output=json&coordtype=wgs84ll&location=" + lat + "," + lng;
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONObject addressComponent = result.getJSONObject("addressComponent");
            return addressComponent.getString("province");
        }
    }
}

