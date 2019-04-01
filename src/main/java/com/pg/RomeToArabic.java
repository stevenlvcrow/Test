package com.pg;

import java.util.Scanner;

/**
* @Author: lizhijie
* @Description: 罗马数字转换为十进制数字
* @Date: Created in 18:14 2018/9/17
*/
public class RomeToArabic {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner mScanner = new Scanner(System.in);
        //System.out.println(r2a(mScanner.nextLine()));
        System.out.println(a2r(mScanner.nextInt()));
    }

    /**
     * 把罗马数字转换为阿拉伯数字
     *
     * @param m
     * @return
     */
    public static int r2a(String m) {
        int graph[] = new int[400];
        graph['I'] = 1;
        graph['V'] = 5;
        graph['X'] = 10;
        graph['L'] = 50;
        graph['C'] = 100;
        graph['D'] = 500;
        graph['M'] = 1000;
        char[] num = m.toCharArray();
        int sum = graph[num[0]];
        for (int i = 0; i < num.length - 1; i++) {
            if (graph[num[i]] >= graph[num[i + 1]]) {
                sum += graph[num[i + 1]];
            } else {
                sum = sum + graph[num[i + 1]] - 2 * graph[num[i]];
            }
        }
        return sum;
    }

    /**
     * 把阿拉伯数字转换为罗马数字
     *
     * @param number
     * @return
     */
    public static String a2r(int number) {
        StringBuilder rNumber = new StringBuilder();
        int[] aArray = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] rArray = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        if (number < 1 || number > 3999) {
            rNumber = new StringBuilder("-1");
        } else {
            for (int i = 0; i < aArray.length; i++) {
                while (number >= aArray[i]) {
                    rNumber.append(rArray[i]);
                    number -= aArray[i];
                }
            }
        }
        return rNumber.toString();
    }
}
