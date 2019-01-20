package com.基本算法思想.分治算法;

import java.util.Scanner;

public class Fenzhi {
    static final int MAXNUM = 30;

    public static void main(String[] args) {
        int num, i;
        int position;
        int[] coins = new int[MAXNUM];  //  存储硬币的集合
        Scanner input = new Scanner(System.in);
        System.out.println("分治法求解假硬币问题");
        System.out.println("请输入硬币的总个数:");
        num = input.nextInt();
        System.out.println("请输入硬币的质量");
        for (i = 0; i < num; i++) {
            coins[i] = input.nextInt();
        }
        position = FalseCoin(coins, 0, num - 1);
        System.out.println("在上述" + num + "个硬币中,假硬币是第" + position + "个.");
    }

    //参数一:硬币集合  参数二:起始硬币编号  参数二:结束硬币编号  返回值:假硬币的位置
    private static int FalseCoin(int[] coins, int start, int end) {

        int i, sum1, sum2;
        int position = 0;             //假币的位置
        sum1 = sum2 = 0;       //初始化

        if (start + 1 == end) {         //最后剩两枚硬币时
            if (coins[start] > coins[end]) {
                position = end + 1;
                return position;
            } else if (coins[start] == coins[end]) {
                return 0;
            } else {
                position = start + 1;
                return position;
            }
        }
        if ((end - start + 1) % 2 == 0) {       //偶数个
            for (i = start; i <= start + (end - start) / 2; i++) {
                sum1 = sum1 + coins[i];     //  前半段和
            }
            for (i = start + (end - start) / 2 + 1; i <= end; i++) {
                sum2 = sum2 + coins[i];     //  后半段和
            }
            if (sum1 > sum2) {
                position = FalseCoin(coins, start + (end - start) / 2 + 1, end);
                return position;
            } else if (sum1 < sum2) {
                position = FalseCoin(coins, start, start + (end - start) / 2);
                return position;
            } else {
            }
        } else {                         //奇数个
            for (i = start; i <= start + (end - start) / 2 - 1; i++) {
                sum1 = sum1 + coins[i];     //  前半段和
            }
            for (i = start + (end - start) / 2 + 1; i <= end; i++) {
                sum2 = sum2 + coins[i];     //  后半段和
            }
            if (sum1 > sum2) {
                position = FalseCoin(coins, start + (end - start) / 2, end);
                return position;
            } else if (sum1 < sum2) {
                position = FalseCoin(coins, start, start + (end - start) / 2);
                return position;
            } else {
            }
            if (sum1 == sum2) {         //当奇数个时两边数字和相等,中间数字肯定就是假币.
                position = start + (end - start) / 2 + 1;
                return position;
            }
        }
        return position;
    }
}
