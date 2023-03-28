package com.json.fastjson.analysis;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
  * JSON转MAP的配置对象
  * @author EinIce
  * @description TODO
  * @date 2022/7/8
  */
@Getter
@Setter
public class ConfigDto {
    /**
     * 用于解析数组数据，以数组为主的数组对象
     */
    private String mainList;
    /**
     * 主键
     */
    private String key;
    /**
     * 存储的字段
     */
    private ArrayList<String> fields;
    /**
     * 存储的List字段
     */
    private ArrayList<String> listFields;
    /**
     * 解析拼装结果
     */
    private Map<String,Object> result;

    public void addField(String field){
        fields.add(field);
    }
    public void addAllFields(String[] fields){
        this.fields.addAll(List.of(fields));
    }
    public void addAllFields(ArrayList<String> fields){
        this.fields.addAll(fields);
    }
    public void addListField(String field){
        listFields.add(field);
    }
    public void addAllListFields(String[] fields){
        listFields.addAll(List.of(fields));
    }
    public void addAllListFields(ArrayList<String> fields){
        listFields.addAll(fields);
    }
    public void cleanField(){
        fields.clear();
    }
    public void cleanListFields(){
        listFields.clear();
    }
}
