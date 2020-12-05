package com.kk.main;

import com.kk.function.User;

import java.util.Scanner;

/**
 *
 * @author 柯神_
 * @date 2020-12-03 19:53:16
*/
public class Lottery {

    //标记位
    public static boolean logined = false;

    /**
     * ===菜单栏===
     */
    public static void menu(User user){
        System.out.println("**********************************************");
        System.out.println("********************主菜单*********************");
        System.out.println("**********************************************");
        System.out.println("******          <1> 登   录             *******");
        System.out.println("******          <2> 注   册             *******");
        System.out.println("******          <3> 抽   奖             *******");
        System.out.println("******          <4> 退   出             *******");
        System.out.println("**********************************************");
        System.out.println("==============================================");
        System.out.println(logined ?
                "-[已登录]-  \n" +
                "（1）用户ID:" + user.getUserId() + "\t  （2）用户名:" + user.getUserName()
                :
                "-[未登录]- （1）用户名:NaN   （2）用户账号:NaN");
        System.out.println("==============================================");
        System.out.println(">>>>>>>>>>>>>>>>>");
        System.out.print("=====>>>>请输入您的选择：");

        int k = new Scanner(System.in).nextInt();

        boolean f = true;
        while(f){
            switch (k){
                case 1:
                    if ( (k = user.login(user)) == -1 ){
                        System.out.println("此用户不存在!\n===>请重新选择：\n");
                        menu(null);
                    }
                    else if ( (k == -2) ) {
                        System.out.println("===<<警告>>用户：[" + user.getUserName() + "]已处于登录状态---无需重复登录！\n");
                    }
                    break;
                case 2:
                    user.regist();
                    break;
                case 3:
                    user.getLuckly();
                    f = false;
                    break;
                default:
                    System.out.println("祝老板身体健康、万事如意!\n欢迎下次光临~\n再见!");
                    System.exit(0);
            }
        }
    }

    /**
     * 开始运行
     * @param args
     */
    public static void main(String[] args){
        menu(new User());
    }
}

