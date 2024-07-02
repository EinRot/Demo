package com.mqs;

import com.fasterxml.jackson.databind.JsonNode;
import com.json.JacksonMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Base64;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO json读取、图片读取
 * 　　* @date 2021/11/11
 *
 */
@Slf4j
public class JsonTest {
    public static void main(String[] args) throws Exception {
        Reader reader = new FileReader("D:\\Projects\\ideaProjects\\szairport-node-guarantee\\resource\\json\\1636597715482.json");
//        Reader reader = new FileReader("D:\\迅雷下载\\DwnlData\\截图\\test1.json");
        int ch = reader.read();
        StringBuffer buffer = new StringBuffer();
        while (ch != -1) { // 读取成功
            buffer.append((char) ch);
            ch = reader.read();
        }
        reader.close();
        String data = buffer.toString();

        JsonNode json = JacksonMapper.instance.objectMapper.readTree(data);
        System.out.println(json.get("data").get("bounding_box").toString());
        CameraDynamic dtoObject = JacksonMapper.instance.objectMapper.readValue(json.toString(), CameraDynamic.class);
        JsonNode detailData = json.get("data");
        if(json!=null){
            dtoObject.setDetectBox(detailData.get("bounding_box").toString());
            dtoObject.setDataType(detailData.get("status").textValue());
        }
        String imageUrl = "D:\\Projects\\ideaProjects\\szairport-node-guarantee\\resource\\image\\";
        String imageName = json.get("message_id").textValue() + ".jpg";
        byte [] img = Base64.getMimeDecoder().decode(json.get("image_base64").textValue());
        String url = writeImage(img,imageUrl+imageName);
        dtoObject.setImageUrl(imageName);
        System.out.println(dtoObject);
        System.out.println(json.get("image_base64").toString().replaceAll("\\r|\\n", ""));
    }
    /**
     *
     * @param img base64字节流
     * @param url 图片路径+图片名称
     */
    public static String writeImage(byte [] img, String url){
        try {
            File file = new File(url);
            if (!file.getParentFile().exists() && !file.getParentFile().isDirectory()) {
                file.getParentFile().mkdir();
            }
            //使用缓冲流
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(img);
            //缓冲写出
            outputStream.close();
            return file.getAbsolutePath();
        }catch (FileNotFoundException e){
            log.error("图片路径不存在："+url);
            log.error(e.getMessage());
            return null;
        }catch (IOException e){
            log.error("图片流写入失败："+url);
            log.error(e.getMessage());
            return null;
        }
    }
}
