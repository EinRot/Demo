package com.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO Http案例
 * 　　* @date 2021/11/16
 *
 */
@Slf4j
public class HttpPostDemo {
    public static void main(String[] args) {
        //转发
        try {
            String data = "{\"data\":\"test\"}";
            HttpPost httpPost = new HttpPost("http://localhost:8989/szairportweb/modelArtsController/receiveData");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpPost.setConfig(requestConfig);
            StringEntity st = new StringEntity(data);
            httpPost.setEntity(st);
            CloseableHttpResponse toResponse = HttpClients.createDefault().execute(httpPost);
            HttpEntity entity = toResponse.getEntity();
            log.info(EntityUtils.toString(entity));
        }catch (Exception e){
            e.printStackTrace();
            log.error("转发失败:"+e.getClass().getSimpleName());
        }
    }
}
