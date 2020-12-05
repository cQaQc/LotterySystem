package com.kk.function;

import com.kk.main.Lottery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 *
 * @author 柯神_
 * @date 2020-12-03 20:10:10
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userId;
    private String userName;
    private String userPwd;

    //定义一个全局静态变量, 用来存储中奖者的信息
    public static HashMap<String,String> lucklyUsers = new HashMap();

    /**
     * 设置用户命名规则
     * @param userName
     */

    /**
     * 设置ID
     */
    public String setId() {

        //随即产生以一个【60000-100000】的ID
        String userId = String.valueOf((int)(100 + Math.random()*(100 + 1)));
        return this.userId = userId;
    }

    /**
     * 1.登录模块
     */
    public int login(User u) {

        int inputTimes = 3;

        Scanner scanner = new Scanner(System.in);
        System.out.print("======>请输入您的用户名：");
        String uName = scanner.nextLine();

        OpenFile Msg = new OpenFile();
        Msg.setUser(uName);

        //集合中的存储顺序：0-->ID; 1-->Name; 2-->pwd
        //检查是否存在该用户
        ArrayList<String> MsgName = Msg.getUserMsg(1);
        if(MsgName.size() < 1){
            //返回-1表示用户不存在
            return -1;
        }
        if (uName.equals(userName)){
            //返回-2表示用户重复登录
            return -2;
        }

        System.out.print("======>请输入您的登录密码：");
        while( !( u.userPwd = scanner.next() ).equals(MsgName.get(2)) && inputTimes > 0) {
            System.out.print( "===>密码输入错误！" +
                    ( (--inputTimes) > 0 ?
                            "您还剩" + inputTimes + "次机会！"
                            :
                            "三次机会已经用完了！\n输入任意退出!") );
        }

        Lottery.logined = ( inputTimes > 0 ) ? true : false;

        if( inputTimes > 0 ){
            System.out.println("==>登录成功！您本次输入密码" + (4 - inputTimes) + "次！");
            u.userName = uName;
            u.userId = MsgName.get(0);
        }
        else System.out.println("==>登录失败!");
        Lottery.menu(u);

        return 0;
    }

    /**
     * 2.注册模块
     */
    public void regist() {

        String name;
        User u = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.print("===>请输入新的用户名：");

        name = scanner.nextLine();
        while (new OpenFile(name).getUserMsg(1).size() > 0){
            System.out.print("已存在此用户,注册失败！\n===>请重新输入新的用户名：");
            name = scanner.nextLine();
        }

        System.out.print("======>请设置您的(六位数字)登录密码：");
        String regex = "[0-9]{6}";
        String pwd = scanner.nextLine();

        while ( !pwd.matches(regex) ){
            System.out.print("==>密码格式不正确，请重新设置您的(六位数字)登录密码：");
            pwd = scanner.nextLine();
        }

        System.out.println( "已为用户：" + ( u.userName = name ) + " 生成唯一ID: " + (u.setId()) );

        u.userPwd = pwd;
        ( new OpenFile() ).writeUserMsg(u);
        System.out.println(u);
        System.out.println("=======>注册成功！");
        Lottery.menu(new User());
    }

    /**
     * 3.抽奖模块
     * @return
     */
    public void getLuckly() {

        //未登录不能抽奖
        if ( !Lottery.logined )   {
            System.out.println("==============>温馨提示：没有用户登录，无法抽奖！<=========================");
            System.out.println("===>请重新选择<===");
            Lottery.menu(new User());
        }

        //规定中奖的人数
        while( lucklyUsers.size() < 5) {
            String id="";
            ArrayList<String> uList;

            //随机一个id，直到随机到文件中有对应的id
            while( (uList = (new OpenFile(
                    id = String.valueOf ((int) (Math.random()*(100+1) + 100))))
                    .getUserMsg(0))
                    .size() < 1){}

            //将这个id对应的用户信息填到幸运集合中
            lucklyUsers.put(uList.get(1),uList.get(0));
        }
        System.out.println(lucklyUsers.size());

        int num = 1;
        boolean LUCKLY = false;
        System.out.println("====>恭喜以下用户获得幸运称号：");
        Iterator iterator = lucklyUsers.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println("幸运用户[" + (num++) + "]  " +
                    "用户名：" + entry.getKey() +
                    "   " +
                    "用户编号："+entry.getValue() );
            if (this.userId.equals(entry.getValue())){
                LUCKLY = true;
            }
        }

        System.out.println("===================================================");
        System.out.println("|                                                 |");
        System.out.println("|                                                 |");
        System.out.println( LUCKLY ?
                "=========>恭喜您在本次抽奖中获得幸运称号！"
                :
                "=========>很遗憾，今日您未获奖 ！-_-！");
        System.out.println("|                                                 |");
        System.out.println("|                                                 |");
        System.out.println("===================================================");
    }

    public String toString(){
        return this.userId + "\t" + this.userName + "\t" + this.userPwd + "\n";
    }

}
