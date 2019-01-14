package com.数据结构.顺序表;

import java.util.Scanner;

/**
 * 准备数据
 * 顺序表本身就是一个数组
 */
//顺序表数据元素的类
class DATA {
    String key;                                 //结点的关键字
    String name;
    int age;
}                                               //定义结点

//顺序表的类
class SLType {                                  //定义顺序表结构
    static final int MAXLEN = 2;              //定义顺序表的最大长度（常量名称全大写）
    DATA[] ListData = new DATA[MAXLEN + 1];    //保存结构表的结构数组
    int ListLen;                                //顺序表已存结点的数量

    //初始化数据
    void SLInit(SLType SL) { //初始化顺序表
        SL.ListLen = 0;       //初始化为空表
    }

    //计算顺序表长度（计算线性表L中结点的个数）
    int SLLength(SLType SL) {
        return SL.ListLen;
    }

    //插入结点
    int SLInsert(SLType SL, int n, DATA data) {
        int i;
        if (SL.ListLen >= MAXLEN) {
            System.out.println("顺序表已满，无法继续插入");
            return 0;       //表示插入失败
        }
        if (n < 1 || n > SL.ListLen - 1) {
            System.out.println("插入元素序号错误，不能插入！");
            return 0;
        }
        //将顺序表中的数据向后移动
        for (i = 0; i < SL.ListLen; i++) {
            SL.ListData[i + 1] = SL.ListData[i];
        }
        SL.ListData[n] = data;
        SL.ListLen++;
        return 1;   //插入成功
    }

    //追加结点
    int SLAdd(SLType SL, DATA data) {
        if (SL.ListLen >= MAXLEN) {
            System.out.println("顺序表已满，无法继续插入数据");
            return 0;
        }
        SL.ListData[++SL.ListLen] = data;
        return 1;
    }

    // 删除结点
    int SLDelete(SLType SL, int n) {
        int i;
        if (n < 1 || n > SL.ListLen + 1) {
            System.out.println("删除结点序号错误，无法删除！");
            return 0;
        }
        for (i = 0; i < SL.ListLen; i++) {
            SL.ListData[i] = SL.ListData[i + 1];
        }
        SL.ListLen--;
        return 1;
    }

    /**
     * 查找结点(根据序号返回查询结点)
     */

    //按照序号查找结点
    DATA SLFindByNum(SLType SL, int n) {
        if (n < 1 || n > SL.ListLen) {
            System.out.println("结点序号错误,无法返回结点!");
            return null;
        }
        return SL.ListData[n];
    }

    //按照关键字查找结点
    int SLFindByCont(SLType SL, String key) {
        int i;
        for (i = 1; i <= SL.ListLen; i++) {
            if (SL.ListData[i].key.compareTo(key) == 0) {
                return i;       //返回结点序号
            }
        }
        return 0;
    }

    //显示所有结点
    int SLAll(SLType SL) {
        int i;
        for (i = 1; i <= SL.ListLen; i++) {
            System.out.println(SL.ListData[i].key + " " + SL.ListData[i].name + " " + SL.ListData[i].age);
        }
        return 0;
    }
}
//-------------------------------------------------------------------//
/**
 * 利用前面顺序表的基本运算完成对数据表的操作
 */
public class SortTable {
    public static void main(String[] args) {

        int i;
        SLType SL = new SLType();
        DATA pdata;
        String key;

        System.out.println("顺序表操作演示!");

        SL.SLInit(SL);  //初始化顺序表
        System.out.println("顺序表初始化完成");
        Scanner scanner = new Scanner(System.in);

        do {    //循环添加结点数据
            System.out.println("请输入添加结点的(学号 姓名 年龄)");
            DATA data = new DATA();
            data.key = scanner.next();
            data.name = scanner.next();
            data.age = scanner.nextInt();
            if (data.age != 0) {    //若年龄不等于0,开始添加
                if (SL.SLAdd(SL, data) == 0) {   //若添加结点失败
                    break;
                }
            } else {  //若年龄为0
                break;
            }

        } while (true);
        System.out.println("顺序表中的结点顺序为:");
        SL.SLAll(SL);   //显示所有结点顺序

        System.out.println("要取出结点的序号:");
        i = scanner.nextInt();
        pdata = SL.SLFindByNum(SL, i);
        if (pdata != null) {
            System.out.println("第" + i + "个结点为:" + pdata.key + " " + pdata.name + " " + pdata.age);
        }

        System.out.println("要查找结点的关键字:");
        key = scanner.next(); //输入要查找结点的关键字
        i = SL.SLFindByCont(SL, key);
        pdata = SL.SLFindByNum(SL, i);
        if (pdata != null) {
            System.out.println("关键字为" + key + "的结点为:" + pdata.key + " " + pdata.name + " " + pdata.age);
        }
    }
}

