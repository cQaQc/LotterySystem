package com.kk.function;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author 柯神_
 * @date 2020-12-03 20:10:38
*/
@Data
@NoArgsConstructor
public class OpenFile {
    private String user;
    public OpenFile(String user){
        this.user = user;
    }

    /**
     *  获取文件信息，以集合形式返回
     */
    public ArrayList<String> getUserMsg(int index){
        String line = "";
        ArrayList<String> temp = new ArrayList<String>();
        File file = new File("user.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            //读取一行数据
            while ((line = br.readLine()) != null && line != "\n"){

                //可以理解为清理缓存吧
                temp.clear();

                //构造一个用来解析 str 的 StringTokenizer 对象, java默认的分隔符是空格("")、制表符(\t)、换行符(\n)、回车符(\r)
                StringTokenizer sk = new StringTokenizer(line);

                //是否还有分隔符
                while (sk.hasMoreTokens()){

                    //返回从当前位置到下一个分隔符的字符串
                    temp.add(sk.nextToken());

                }

                //获取指定索引
                if (temp.get(index).equals(this.user)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (line == null? new ArrayList<String>() : temp);
    }


    /**
     * 注册信息写入txt文件
     */
    public void writeUserMsg(User user){
        File file = new File("user.txt");
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(user.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
