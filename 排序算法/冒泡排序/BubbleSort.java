package com.排序算法.冒泡排序;

import java.util.Scanner;

public class BubbleSort {
    static final int MAXLEN = 20;   //指定数组的最大长度

    public static void main(String[] args) {
        int[] waitSort = new int[MAXLEN];
        Scanner input = new Scanner(System.in);
        System.out.println("冒泡排序算法演示");
        System.out.println("请输入待排序数据的个数（<20）：");
        int num = input.nextInt();
        System.out.println("请输入待排序数据：");
        for (int i = 0; i < num; i++) {
            waitSort[i] = input.nextInt();
        }
        sortArr(waitSort, num);     //调用冒泡排序算法进行排序
        System.out.print("最终排序结果为：");
        for (int i = 0; i < num; i++) {      //打印排序后的结果
            System.out.print(+waitSort[i] + " ");
        }
    }

    //冒泡排序  参数一：待排序数组      参数二：元素的个数
    private static void sortArr(int[] waitSort, int num) {
        int temp;
        int start;      //起始位置
        int end = num - 1;    //结束的位置
        for (int i = 0; i < end; i++) {     //总遍历轮数 = num - 1 次
            start = -1;
            for (int j = 0; j < end - i; j++) {     //每轮比较次数 = 比前一轮少一次
                start = start + 1;
                if (waitSort[start] > waitSort[start + 1]) {      //如果前一个数大于后一个数
                    temp = waitSort[start + 1];
                    waitSort[start + 1] = waitSort[start];   //交换他们的位置
                    waitSort[start] = temp;
                }
            }
            System.out.print("第" + (i + 1) + "轮排序的结果：");
            for (int k = 0; k < num; k++) {
                System.out.print(waitSort[k] + " ");
            }
            System.out.println();
        }
    }
}
