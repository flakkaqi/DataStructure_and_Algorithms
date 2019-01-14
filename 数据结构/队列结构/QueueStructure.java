package com.数据结构.队列结构;

import java.util.Scanner;

//------------------------准备数据-------------------------------//
class DATA {
    String name;
    int age;
}

class SQType {
    static final int QUEUELEN = 15;
    DATA[] data = new DATA[QUEUELEN];   //队列数组
    int head;  //队头,为0时表示空队列
    int tail;   //队尾,为QUEUELEN时表示队列已满

    //-------------------初始化队列------------------------------//
    /*
     *在使用顺序队列之前需要先创建一个空队列
     * 1.按照QUEUELEN指定的大小申请一片内存空间,用来保存数据
     * 2.设置head=0,tail=0,表示是一个空队列
     */
    SQType SQTypeInit() {
        SQType sq;
        if ((sq = new SQType()) != null) {
            sq.head = 0;
            sq.tail = 0;
            return sq;
        } else {
            return null;
        }
    }

    //------------------------判断空队列-----------------------//
    //如果是空队列，则可以入队
    boolean SQTypeIsNull(SQType sq) {
        return sq.tail == sq.head;
    }

    //------------------------判断满队列----------------------//
    //如果是满队列则不能再入队
    boolean SQtypeIsFull(SQType sq) {
        return sq.tail == QUEUELEN;
    }

    //-----------------------清空对列------------------------//
    void SQTypeClear(SQType sq) {
        sq.head = 0;
        sq.tail = 0;
    }

    //-----------------------释放空间,从内存中删除-----------------------//
    void SQTypeFree(SQType sq) {
        if (sq != null) {
            sq = null;
        }
    }
    //----------------------入队列------------------------------------//

    /**
     * 入队列主要是对数据的存入，操作如下：
     * 1.判断对列是否已满，如果已满则表示溢出，进行出错处理，否则继续
     * 2.设置tail=tail+1(队尾后移)
     * 3.将入队的数据存入tail所指的位置
     */
    boolean InsertSQType(SQType sq, DATA data) {
        if (sq.tail >= QUEUELEN) {
            System.out.println("队列已满！操作失败");
            return false;
        }
        sq.data[sq.tail++] = data;        //队尾后移
        return true;
    }
    //----------------------出队列-------------------------------//

    /**
     * 出队列与如对了刚好相反，从队列中弹出头数据，让后一位数据作为新的头
     * 1.判断队列是否为空，为空出错处理，否则继续
     * 2.从队列首部取出对头元素（实际是返回队头元素的引用）
     * 3.设修改对头元素head的序号，使其指向后一个元素
     * 队首和队尾只增不减，会慢慢耗光申请的空间
     */
    DATA OutSQType(SQType sq) {
        if (sq.head == sq.tail) {
            System.out.println("当前队列为空!操作失败");
            System.exit(0);
        } else {
            return sq.data[sq.head++];      //队首后移
        }
        return null;
    }

    //----------------------------读结点数据---------------------//
    //对列中只能读队头数据，只是对队头数据的展示
    //出队则是直接将数据删除
    DATA PeekSQType(SQType sq) {
        if (sq.head == sq.tail) {
            System.out.println("空队列！");
            return null;
        }
        return sq.data[sq.head];
    }

    //---------------------------计算队列长度-------------------//
    int SQTypeLen(SQType sq) {
        return sq.tail - sq.head;
    }
}

//------------------------------队列的实例操作----------------------//
public class QueueStructure {
    public static void main(String[] args) {
        SQType sqType = new SQType();
        DATA data;
        Scanner input = new Scanner(System.in);

        SQType queue = sqType.SQTypeInit();//初始化队列
        System.out.println("入队操作的演示！");
        do {
            DATA data1 = new DATA();
            System.out.println("请输入要入队的数据（姓名 年龄）：");
            data1.name = input.next();
            data1.age = input.nextInt();
            if (data1.name.equals("0")) {
                break;  //若输入0则退出
            } else {
                sqType.InsertSQType(queue, data1);
            }
        } while (true);

        String temp = "1";
        System.out.println("出队操作：按任意非零键进行操作");
        temp = input.next();
        while (!temp.equals("0")) {
            data = sqType.OutSQType(queue);
            System.out.println("出栈元素为" + data.name + "" + data.age);
            temp = input.next();
        }
        System.out.println("测试结束！");
        sqType.SQTypeClear(queue);
        sqType.SQTypeFree(queue);       //释放空间
    }
}

//----------------------------------------结果展示------------------------------//
/*
入队操作的演示！
        请输入要入队的数据（姓名 年龄）：
        Tom
        28
        请输入要入队的数据（姓名 年龄）：
        Jack
        25
        请输入要入队的数据（姓名 年龄）：
        0
        0
        出队操作：按任意非零键进行操作
        4
        出栈元素为Tom28
        2
        出栈元素为Jack25
        0
        测试结束！*/
