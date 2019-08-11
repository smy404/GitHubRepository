package com.example.lenovo.calculator_chapter4;

import java.util.HashMap;
import java.util.Map;
//定义了一个类，用来存放加减乘除
public class ConstantInfo {
    public static final String addition = "加法";
    public static final String minus = "减法";
    public static final String multiply = "乘法";
    public static final String divider = "除法";

    public static final Map<Integer,String> typeMap = new HashMap<Integer,String>();
    public static final Map<Integer,String> infoMap = new HashMap<Integer,String>();//数组

    static {
        typeMap.put(1,addition);//键，值
        typeMap.put(2,minus);
        typeMap.put(3,multiply);
        typeMap.put(4,divider);

        infoMap.put(1,"+");
        infoMap.put(2,"_");
        infoMap.put(3,"*");
        infoMap.put(4,"/");
    }



}
