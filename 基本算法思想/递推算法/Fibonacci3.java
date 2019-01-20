package com.基本算法思想.递推算法;

import java.util.Scanner;
public class Fibonacci3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("递推算法求解兔子产仔问题");
        System.out.println("输入要计算的月份:");
        int month = input.nextInt();
        int num = rabbit(month);
        System.out.println("第"+month+"个月时总共有兔子"+num+"只");
    }
    //顺递推算法求解斐波那契数列之兔子问题
    private static int rabbit(int month) {
        if (month<=2) return 1;
        int fP=1, fL=1, f;
        for (int i=3; i<=month; i++) {
            f = fP + fL;        //前两月兔子之和等于后一个月兔子个数
            fP = fL;
            fL = f;
        }
        return fL;
    }

}
