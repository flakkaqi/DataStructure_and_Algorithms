package com.算法概述.数字查找;

import java.util.Random;
import java.util.Scanner;

public class SearchNum {
    //生成一个长度为20的随机数组(0-100)，输入一个数字，存在打印位置，不存在打印不存在
    static int N = 20;

    public static void main(String[] args) {
        //1.生成一个长度为20的随机数组(0-100)
        int[] ints = new int[N];
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            ints[i] = random.nextInt(101);
            System.out.print(ints[i] + " ");
        }

        //输入一个数字，存在打印位置，不存在打印不存在
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入要查找的数字");
        int num = scanner.nextInt();
        int i = 0;
        for (int anInt : ints) {
            i++;
            if (num == anInt) {
                System.out.println("第" + i + "个");
                break;
            }
            if (i == ints.length) {
                System.out.println("未找到");
            }
        }

    }
}
