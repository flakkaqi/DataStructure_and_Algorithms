package com.排序算法.选择排序;

import java.util.Scanner;

public class SelectSort {
    static final int MAXLEN = 20;   //指定数组的最大长度

    public static void main(String[] args) {
        int[] waitSort = new int[MAXLEN];
        Scanner input = new Scanner(System.in);
        System.out.println("选择排序算法演示");
        System.out.println("请输入待排序数据的个数（<20）：");
        int num = input.nextInt();
        System.out.println("请输入待排序数据：");
        for (int i = 0; i < num; i++) {
            waitSort[i] = input.nextInt();
        }
        sortArr(waitSort, num);     //调用选择排序算法进行排序
        System.out.print("最终排序结果为：");
        for (int i = 0; i < num; i++) {      //打印排序后的结果
            System.out.print(+waitSort[i] + " ");
        }
    }
    //选择排序 参数一：待排序的数据   参数二：数据的个数
    private static void sortArr(int[] waitSort, int num) {
        int min;    //每轮最小数据
        for (int i = 0; i < num - 1; i++) {      //找出每一轮中最小的数据
            for (int j = 1; j < num - i; j++) {     //找出当前轮中最小的数
                if (waitSort[i] > waitSort[i + j]) {  //如果后面的数据中比当前比较的数字小，则交换
                    min = waitSort[i + j];
                    waitSort[i + j] = waitSort[i];
                    waitSort[i] = min;
                }
                System.out.print("内" + j + "轮数据为");
                for (int k = 0; k < num; k++) {
                    System.out.print(waitSort[k] + " ");
                }
                System.out.println();
            }
        }
    }
}
