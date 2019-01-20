package com.基本算法思想.穷举算法;
import java.util.Scanner;
/**
 * 今有鸡兔同笼，上有35头，下有94足，问鸡兔各几何？
 */
public class QiongJu {
    public static void main(String[] args) {
        int heads,foots;
        Scanner input = new Scanner(System.in);
        System.out.println("请输入头的个数");
        heads = input.nextInt();
        System.out.println("请输入足的个数");
        foots = input.nextInt();

        int i,j;
        for (i = 1;  i<=heads ; i++) {
            j=heads-i;
            if((2*i+4*j)==foots){
                System.out.println("鸡娃子有"+i+"只");
                System.out.println("小兔子有"+j+"只");
            }
        }
    }
}
/**
 * 结果:
 *
 *请输入头的个数
 *35
 *请输入足的个数
 *94
 *鸡娃子有23只
 *小兔子有12只
 */
