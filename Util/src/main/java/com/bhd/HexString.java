package com.bhd;

import org.testng.annotations.Test;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 16进制和字符串互转
 * 　　* @date 2022/6/1
 *
 */
public class HexString {
    /**
      * @description TODO 十六进制转字符串，中文乱码
      */
    @Test
    public void hexToString(){
        String s = "616263";
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(s);
    }
    /**
     * @description TODO 十六进制转字符串
     */
    @Test
    public void hexToStringZn(){
        String hexStr="07F06C2C4C6C8CACCCED16F3005DD6D04438A944C397A18A0A038E401178A2FFBF700FA1FA101FFFEFF7C0096A342850005A0007FFF00401C000";
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        System.out.println(new String(bytes));
    }

    /**
      * @description TODO 字符串转十六进制，中文乱码
      */
    @Test
    public void stringToHex(){
        String s1 = "abc";
        String str = "";
        for (int i = 0; i < s1.length(); i++) {
            int ch = s1.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        System.out.println(str);
    }
    /**
     * @description TODO 字符串转十六进制
     */
    @Test
    public void stringToHexZn(){
        String str = "测试";
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        System.out.println(sb.toString().trim());
    }
}
