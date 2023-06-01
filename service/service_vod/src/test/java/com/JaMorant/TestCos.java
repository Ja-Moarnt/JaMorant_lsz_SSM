package com.JaMorant;

import com.alibaba.fastjson.JSON;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.File;


//测试文件上传功能
public class TestCos {

    public static void main(String[] args) {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "AKIDzmNyTjma97wVr4YyTjKgneHi4C3jLxXv";
        String secretKey = "FG2ZuCKOHzc6MXVFjBYtERULyn9HByXs";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        Region region = new Region("ap-beijing");
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        // 指定要上传的文件
        File localFile = new File("F:\\LUO\\壁纸图\\22年2月26\\wallhaven-wqvkl7.jpg");
        // 指定文件将要存放的存储桶
        String bucketName = "jamorant-12-1309991848";
// 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = "/2022/01/01.jpg";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        System.out.println(JSON.toJSONString(putObjectResult));
    }
}
