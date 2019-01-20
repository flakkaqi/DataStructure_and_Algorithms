package com.基本算法思想.递推算法;

/**
 * 题目要求：
 * 如果一对两个月大的兔子以后每个月都可以生一对小兔子，而一对新生的兔子出生两个月后才可以生小兔子。
 * 也就是说,1月份出生，3月份才可以产仔。那么假定一年内没有发生兔子死亡事件，那么一年后共有多少对兔子呢？）
 */
public class Fibonacci {
    public static void main(String[] args) {

        //参数1：开始计算的月份 参数2：前前一月兔子对数 参数3：前一月兔子对数
        int num = born(3, 1, 1);
        System.out.println("一年后兔子总只数为"+num+"只");
    }

    private static int born(int mon, int preNum, int newNum) {
        int num;
        if( mon<=12){
            num = preNum + newNum;  //算法规律：前两项之和等于第三项
            mon = mon + 1;   //月份增加
            newNum = born(mon, newNum, num);   //递归计算只数
        }
        return newNum;
    }
}