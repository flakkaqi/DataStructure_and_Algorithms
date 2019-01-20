package com.基本算法思想.递归算法;

import java.util.Scanner;
public class JieCheng {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入要计算阶乘的整数:");
        int num = input.nextInt();
        long result = fact(num);
        System.out.println(num+"的阶乘为:"+result);
    }

    private static long fact(int num) {
        if(num==1){
            return 1;
        }
        //通式 n!= n*(n-1)!
        return num*fact(num-1);
    }
}
