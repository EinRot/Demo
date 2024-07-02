package com.regular;

public class Demo1 {
    public String formatNumber(String number){
        //split()方法支持正则表达式，‘.’表示匹配任意字符，所以这里需要转义，表示匹配‘.’
        String[] subArr = number.split("\\.");
        //只对整数部分进行千分位制,整数部分转换成StringBuffer
        StringBuffer sb = new StringBuffer(subArr[0]);
        //整数部分小于三位，不需要进行分位操作
        if(sb.length()<=3){
            return number;
        }
        //大于三位
        //开始位置
        int start = sb.length()%3;
        if(start==0){
            start = 3;
        }
        for (; start <sb.length() ; start+=4) {
            sb.insert(start,",");
        }
        //加上小数部分
        StringBuffer doubleStr = new StringBuffer(".");
        try {
            //如果try正常执行，说明有小数部分，doubleStr拼接小数部分
            doubleStr.append(subArr[1]);
        }catch (ArrayIndexOutOfBoundsException e){
            //进入这里表示没有小数部分，所以报数组越界的异常
            //没有小数部分 doubleStr置为“”
            doubleStr.deleteCharAt(doubleStr.length()-1);
        }
        //整数后面加上doubleStr的内容
        sb.append(doubleStr);
        return sb.toString();
    }
    public String myFormatNumber(String number){
        //split()方法支持正则表达式，‘.’表示匹配任意字符，所以这里需要转义，表示匹配‘.’
        String strArr[] = number.split("\\.");
        //将整数部分分离出来
        StringBuffer sb = new StringBuffer(strArr[0]);
        //小于等于三位，不需要该操作，返回原数字
        if(sb.length()<=3){
            return number;
        }
        //大于三位
        int last = sb.length();
        //从后往前，每三位前插入一个逗号
        for (int i = last-3; i >0 ; i-=3) {
            sb.insert(i,",");
        }
        StringBuffer doubleStr = new StringBuffer(".");
        try {
            //如果有小数部分，那就小数点加上小数部分
            doubleStr.append(strArr[1]);
        }catch (ArrayIndexOutOfBoundsException e){
            //进入这里表示没有小数部分，那就将doubleStr置为空字符
            doubleStr.deleteCharAt(doubleStr.length()-1);
        }
        //整数和doubleStr的结果拼接
        sb.append(doubleStr);
        return sb.toString();
    }

    //测试方法
    public static void main(String[] args) {
        System.out.println(new Demo1().formatNumber("31213123.1212"));
        System.out.println(new Demo1().myFormatNumber("31213123"));
        System.out.println(new Demo1().myFormatNumber("31213123.1122"));
    }
}