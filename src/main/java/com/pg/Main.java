package com.pg;

import java.io.File;
import java.util.Scanner;

/**
* @Author: lizhijie
* @Description: 主执行程序
* @Date: Created in 18:53 2018/9/17
*/
public class Main {


    public static void main(String[] args) {
        Scanner mScanner = new Scanner(System.in);
        // 输入的文件目录
        String path = mScanner.next();
        File file = new File(path);
        if(file.isFile()){
            FileInput fileInput = new FileInput();
            fileInput.txt2String(file);
            return;
        }
        System.out.println("错误的路径");
    }
}
