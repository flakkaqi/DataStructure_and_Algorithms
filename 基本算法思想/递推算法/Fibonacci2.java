package com.基本算法思想.递推算法;

import java.util.Scanner;
public class Fibonacci2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("递推算法求解兔子产仔问题");
        System.out.println("输入要计算的月份:");
        int date = input.nextInt();
        int num = rabbit(date);
        System.out.println("第"+date+"个月时总共有兔子"+num+"只");
    }

    private static int rabbit(int date) {
        int t1,t2;
        if(date==1 || date ==2){
            return 1;
        }else {
            t1=rabbit(date-1);  //递归地调用方法
            t2=rabbit(date-2);
            return t1+t2;   //递推求结果
        }
    }
}
