package com.基本算法思想.递归算法;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("递推算法求解兔子产仔问题");
        System.out.println("输入要计算的月份:");
        int date = input.nextInt();
        int num = compute(date);
        System.out.println("第"+date+"个月时总共有兔子"+num+"只");
    }
    //递归计算斐波那契兔子只数
    private static int compute(int month) {
        if (month <2){
            return 1;
        }else {
            return compute(month-1)+compute(month-2);
        }
    }
}
