package com.pg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
* @Author: lizhijie
* @Description: 处理计算相关
* @Date: Created in 12:07 2018/9/18
*/
class Calculate {

    /**
     * 计算不同矿种的单价分数
     *
     * @param arr
     * @param unitArr
     * @return
     */
    void resolver(String[] arr, ArrayList<String> unitArr) {

        ArrayList<String> romeArr1 = new ArrayList<>(Arrays.asList(arr));
        ArrayList<String> romeArr2 = new ArrayList<>(Arrays.asList(arr));
        //System.out.println("-----------------------------");
        romeArr2.retainAll(unitArr);
        //System.out.println(romeArr2);
        StringBuilder ss = new StringBuilder();
        for (String str : romeArr2) {
            ss.append(Constant.corresMap.get(str));
        }
        //得到了总数量
        romeArr1.removeAll(unitArr);
        //System.out.println(romeArr1);
        String ore = romeArr1.get(0);
        Integer score = Integer.valueOf(romeArr1.get(2));

        int count = RomeToArabic.r2a(ss.toString());

        BigDecimal b1 = new BigDecimal(Double.toString(score));
        BigDecimal b2 = new BigDecimal(Double.toString(count));
        //保留两位小数
        Double rslt = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
        Constant.oreScoreMap.put(ore,  rslt);
        //System.out.println(Constant.oreScoreMap);
    }

    /**
     * 数字加法
     * @param arr
     * @return
     */
    static Integer add(String [] arr){
        StringBuilder tr = new StringBuilder();
        for(String str : arr){
            tr.append(Constant.corresMap.get(str));
        }
        return RomeToArabic.r2a(tr.toString());
    }

    /**
     * 乘法
     * @param arr
     * @return
     */
    static Double mult(String [] arr){
        ArrayList<String> romeArr1 = new ArrayList<>(Arrays.asList(arr));
        // 矿种
        String ore = romeArr1.get(romeArr1.size()-1);
        romeArr1.remove(romeArr1.size()-1);

        StringBuilder tr = new StringBuilder();
        for(String str : romeArr1){
            tr.append(Constant.corresMap.get(str));
        }
        return RomeToArabic.r2a(tr.toString()) * Constant.oreScoreMap.get(ore);
    }
}
