package com.JaMorant.SSM.order.AI;

import com.JaMorant.SSM.vo.order.AICarGoods;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:JaMorant
 * @time:2023/4/13 12:19
 * @explain:
 */
@Component
public class LinkPy {
//    @Autowired
//    private GoodsFeignClient goodsFeignClient;
//    @Autowired
//    private CarFeignClient carFeignClient;
    //获取训练数据集数据集
    @Test
    public  void getTrain() throws Exception {
        String scriptPath = "service/service_order/src/main/java/com/atguigu/ggkt/order/AI/getTrainData.py";
        ProcessBuilder pb = new ProcessBuilder("python", "-W", "ignore", scriptPath);
        pb.redirectErrorStream(true);
        Process p = pb.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        p.waitFor();
        in.close();
    }


    //获取数据集
    @Test
    public  void getData(Long chengshiId) throws Exception {
        String scriptPath = "service/service_order/src/main/java/com/atguigu/ggkt/order/AI/getTestData.py";
        ProcessBuilder pb = new ProcessBuilder("python", "-W", "ignore", scriptPath, chengshiId.toString());
        pb.redirectErrorStream(true);
        Process p = pb.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        p.waitFor();
        in.close();
    }

    //预测goodsId
    @Test
    public  void forecast() {
        String scriptPath = "service/service_order/src/main/java/com/atguigu/ggkt/order/AI/JavaLink.py";
        ProcessBuilder pb = new ProcessBuilder("python", "-W", "ignore", scriptPath);
        pb.redirectErrorStream(true);
        try {
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取predictions.csv文件
    public List<AICarGoods> GetPredictions() throws IOException, CsvValidationException {
        List<AICarGoods> aiCarGoods=new ArrayList<>();
        String csvFile = "service/service_order/src/main/java/com/atguigu/ggkt/order/AI/predictions.csv";
        CSVReader reader = new CSVReader(new FileReader(csvFile));
        String[] headers = reader.readNext(); // 读取表头
        int goodsIdIndex = indexOf(headers, "goodsId");
        int carIdIndex = indexOf(headers, "carId");

        String[] line;
        while ((line = reader.readNext()) != null) { // 逐行读取数据
            AICarGoods aigoods = new AICarGoods();
            Long goodsId = Long.valueOf(line[goodsIdIndex]);
            Long carId = Long.valueOf(line[carIdIndex]);
            aigoods.setCarId(carId);
            aigoods.setGoodsId(goodsId);
//            String carName = carFeignClient.GetCarById(carId).getCarId();
//            String goodsName = goodsFeignClient.getById(goodsId).getTitle();
//            aigoods.setCarName(carName);
//            aigoods.setGoodsName(goodsName);
            aiCarGoods.add(aigoods);
            System.out.println("Goods ID: " + goodsId + ", Car ID: " + carId);
        }
        return aiCarGoods;
    }
    //用于GetPredictions()函数索引表头下标
    private static int indexOf(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (value.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

}
