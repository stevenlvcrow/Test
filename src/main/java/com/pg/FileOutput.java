package com.pg;

import java.io.*;

/**
* @Author: lizhijie
* @Description:结果文件输出
* @Date: Created in 12:07 2018/9/18
*/
public class FileOutput {

   static File fileName;

    /**
     * 创建文件
     * @return
     */
    static boolean createFile() {
        try{
            fileName.delete();
            fileName.createNewFile();
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 写入TXT，追加写入
     * @param content
     */
    static void fileChaseFW(String content) {
        try {
            //构造函数中的第二个参数true表示以追加形式写文件
            FileWriter fw = new FileWriter(fileName,true);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
