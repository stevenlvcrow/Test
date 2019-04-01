package com.pg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


/**
 * @Author: lizhijie
 * @Description: 文件输入
 * @Date: Created in 18:45 2018/9/17
 */
public class FileInput {


    private ArrayList<String> unitArr = new ArrayList<>();

    void txt2String(File file) {
        String fileName = file.getName();
        if (!fileName.endsWith(".txt")) {
            System.out.println("错误的文本格式");
            return;
        }
        try {
            FileOutput.fileName = new File(file.getParentFile().getPath()+"/"+getFileNameWithoutSuffix(file)+"_answer.txt");
            FileOutput.createFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            while ((s = br.readLine()) != null) {
                checkIsDefindCredits(s);
                checkIsQuestions(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkIsDefindCredits(String linetext) {
        String[] arr = linetext.split("\\s+");
        if (arr.length == 3 && Constant.romeArr.contains(arr[2])) {
            // 定义句式 xxx is [ "I", "V", "X", "L", "C", "D", "M"]
            Constant.corresMap.put(arr[0], arr[2]);
            unitArr.add(arr[0]);
        } else if (linetext.endsWith(" Credits")) {
            Calculate calculate = new Calculate();
            calculate.resolver(arr, unitArr);
        }
    }

    private void checkIsQuestions(String linetext) {
        String msg = "";
        if (linetext.startsWith("how much is ")) {
            linetext = linetext.replaceAll("how much is ", "");
            linetext = linetext.replaceAll(" \\?","");
            //[pish tegj glob glob ?]
            String[] arr = linetext.split("\\s+");
            Integer result = Calculate.add(arr);
            msg = linetext+" is "+result;
            System.out.println(msg);
            FileOutput.fileChaseFW(msg+"\n");
        } else if (linetext.startsWith("how many Credits is ")) {
            linetext = linetext.replaceAll("how many Credits is ", "");
            linetext = linetext.replaceAll(" \\?","");
            //[glob prok Silver]
            String[] arr = linetext.split("\\s+");
            Double result = Calculate.mult(arr);
            msg = linetext+" is "+result+" Credits";
            System.out.println(msg);
            FileOutput.fileChaseFW(msg+"\n");
        } else if (linetext.startsWith("how ")){
            msg = "I have no idea what you are talking about";
            System.out.println(msg);
            FileOutput.fileChaseFW(msg+"\n");
        }

    }


    //获取不带后缀名的文件名
    private static String getFileNameWithoutSuffix(File file){
        String file_name = file.getName();
        return file_name.substring(0, file_name.lastIndexOf("."));
    }
}
