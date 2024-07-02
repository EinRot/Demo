package com.bhd;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 十六进制数转十进制数
 * 　　* @date 2022/5/12
 *
 */
public class HexDec {
    public static void main(String[] args) {
        //十六进制数转十进制数
        String s = Integer.toString(0xfe);
        System.out.println(s);
        //去0x十六进制
        System.out.println(Integer.toHexString(0xfe));

        //十六进制转十进制，返回int
        int out = Integer.parseInt("fe", 16);
        System.out.println(out);
        //字符串打印
        System.out.println((char)out);

        //测试
        String data = "0xfe 0xdc 0x01 0x13 0xc3 0xac 0x2c 0x9c 0x6d 0x00 0x00 0x00 0x15 0x03 0x00 0x18 0x00 0x00 0x01 0x37 0x00 0x00 0x00 0x60 0x00 0x00 0x07 0xd0 0x00 0x00 0x00 0x17 0x00 0x00 0x00 0x00 0x00 0x00 0x00 0x1e 0x00";
        data = data.replaceAll("0x","");
        String[] list = data.split(" ");
        for (String l : list) {
            long val = Integer.parseInt(l, 16);
            System.out.println(val);
        }

        //拼接测试
        System.out.println(joint(list,0,1));

        StringBuilder str = new StringBuilder();
        str.append(1);
        str.append(2);
        System.out.println(str);

        String b = "asd";
        System.out.println(BinaryToHexString(b.getBytes(),b.length()));
    }

    /**
     * @description TODO 拼接结果
     */
    private static String joint(String[]list,int start,int end){
        if(list.length<end){
            return null;
        }
        StringBuilder str = new StringBuilder();
        for(int i=start;i<=end;i++){
            str.append(list[i]);
        }
        return str.toString();
    }

    /**
     * @description TODO 将字节数组转换成十六进制的字符串
     */
    public static String BinaryToHexString(byte[] bytes,int len) {
        String hexStr = "0123456789ABCDEF";
        StringBuilder result = new StringBuilder();
        for (int i = 0;i<len;i++) {
            result.append(hexStr.charAt((bytes[i] & 0xF0) >> 4));
            result.append(hexStr.charAt(bytes[i] & 0x0F));
            if(i!=(len-1))result.append(" ");
        }
        return result.toString();
    }
}
