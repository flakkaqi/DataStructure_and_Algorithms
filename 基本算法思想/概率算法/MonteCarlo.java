package com.基本算法思想.概率算法;

import java.util.Scanner;

/**
 * 蒙特卡罗算法实现计算Π
 */
public class MonteCarlo {
    public static void main(String[] args) {
        //输入参数n为撒点的次数,返回值为圆周率的近似值
        int n;
        double PI;
        System.out.println("蒙特卡罗概率算法计算Π");
        Scanner input = new Scanner(System.in);
        System.out.println("请输入点的数量");
        n = input.nextInt();
        PI = MontePI(n);
        System.out.println("Π约等于" + PI);
    }

    //蒙特卡罗算法
    private static double MontePI(int n) {
        double PI;
        double x, y;    //点的坐标
        int i, sum;
        sum = 0;
        for (i = 0; i < n; i++) {
            x = Math.random();    //产生0-1之间的随机数
            y = Math.random();    //产生0-1之间的随机数
            if ((x * x + y * y) <= 1)     //若在阴影面积里
                sum++;          //计数
        }
        PI = 4.0 * sum / n;       //计算Π
        return PI;
    }
}
