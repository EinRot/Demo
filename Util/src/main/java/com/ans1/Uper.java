package com.ans1;

import java.nio.charset.Charset;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO
 * 　　* @date 2022/6/1
 */
public class Uper {
    public static void main(String[] args) {
        String s = "07f06c2c4c6c8caccced16f3005dd6d04438a944c397a18a0a038e401178a2ffbf700fa1fa101fffeff7c0096a342850005a0007fff00401c000";
        byte[] bytes = s.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for (int i = 0; i < Byte.SIZE * bytes.length; i++) {
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        }
        System.out.println(sb);
        Uper uper = new Uper();
        uper.test(sb.toString());
    }
    public void test(String binaryStr){
        String [] binaryArr = new String [binaryStr.length()/8];
        for (int i=0; i<binaryStr.length(); i = i + 8) {
            binaryArr[i/8] = binaryStr.substring(i, i+8);
            System.out.println(binaryArr[i/8]);
        }
        byte [] mm3 = new byte[binaryArr.length];
        for(int i=0; i< binaryArr.length; i++) {
            String m = binaryArr[i];
            int nn4 = Integer.parseInt(m, 2);
            byte mm = (byte)(nn4);
            mm3[i] = mm;
        }
        System.out.print(new String(mm3, Charset.forName("UTF-8")));
    }
}
