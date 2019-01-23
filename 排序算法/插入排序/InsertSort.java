package com.排序算法.插入排序;

import java.util.Scanner;

public class InsertSort {
    static final int MAXLEN = 20;   //指定数组的最大长度

    public static void main(String[] args) {
        int[] waitSort = new int[MAXLEN];
        Scanner input = new Scanner(System.in);
        System.out.println("插入排序算法演示");
        System.out.println("请输入待排序数据的个数（<20）：");
        int num = input.nextInt();
        System.out.println("请输入待排序数据：");
        for (int i = 0; i < num; i++) {
            waitSort[i] = input.nextInt();
        }
        sortArr(waitSort, num);     //调用插入排序算法进行排序
        System.out.print("最终排序结果为：");
        for (int i = 0; i < num; i++) {      //打印排序后的结果
            System.out.print(+waitSort[i] + " ");
        }
    }

    //插入排序  参数一：待排序数组      参数二：元素的个数
    private static void sortArr(int[] waitSort, int num) {
        int start = 0;
        int end = 1;
        int temp, newTemp;
        if (waitSort[start] > waitSort[end]) { //1.对前两个数据进行处理，如果前一个数>后一个数 则交换位置
            temp = waitSort[end];
            waitSort[end] = waitSort[start];
            waitSort[start] = temp;
        }
        do {
            //2.下一个值去和头尾进行比较
            if (waitSort[end + 1] <= waitSort[start]) {        //如果比最小的数都小，则换位置到最前面
                temp = waitSort[end + 1];
                for (int i = end + 1; i > 0; i--) {
                    waitSort[i] = waitSort[i - 1];
                }
                waitSort[0] = temp;
                end++;
            } else if (waitSort[end + 1] >= waitSort[end]) {          //如果比当前最大的都大,将end加1
                end++;
            } else {    //其他情况的处理
                int i = 1;
                while (waitSort[end + 1] > waitSort[start + i]) {   //计算出要插入的位置索引
                    i++;
                }
                newTemp = waitSort[end + 1];     //保存最新的数据
                for (int j = end + 1; j > i; j--) {       //从插入位置整体后移
                    temp = waitSort[start + j]; //保存当前状态
                    waitSort[j] = waitSort[j - 1];
                }
                waitSort[start + i] = newTemp;      //插入数据
                end++;
            }
            System.out.print("该轮排序后结果为：");
            for (int j = 0; j < num; j++) {
                System.out.print(waitSort[j] + " ");
            }
            System.out.println();
        } while (end < num - 1);
    }
}
