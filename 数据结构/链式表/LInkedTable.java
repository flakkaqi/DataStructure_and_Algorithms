package com.数据结构.链式表;

import java.util.Scanner;

/**
 * 1.准备数据
 */

//链式表数据元素的类
class DATA {
    String key;                                 //结点的关键字
    String name;
    int age;
}                                               //数据结点类型

//链式表的类
class CLType {                                  //定义链式表结构
    DATA nodeData = new DATA();                 //保存具体数据
    CLType nextNode;                            //指向下一个结点

    /**
     * 追加结点
     * 典型追加步骤:
     * 1.分配内存地址保存新增结点
     * 2.从头引用head逐个检查,找到尾结点
     * 3.将表尾结点的地址设置为新增结点的地址
     * 4.新增结点的地址设置为null
     */
    CLType CLAddEnd(CLType head, DATA nodeData) {
        CLType node, htemp;
        if ((node = new CLType()) == null) { //通过new关键字申请保存结点的内存空间
            System.out.println("申请内存失败!");
            return null;
        } else {
            node.nodeData = nodeData; //保存数据
            node.nextNode = null; //设置新增结点引用为空,因为新增节点将是尾节点,所以引用地址为null
            if (head == null) {     //头引用
                head = node;
                return head;
            }
            htemp = head;
            while (htemp.nextNode != null) {   //查找链表的末尾
                htemp = htemp.nextNode;   //逐个查找,直到找到尾结点
            }
            htemp.nextNode = node;    //将新增结点设置为尾节点的引用节点,即产生了新的尾节点
            return head;
        }
    }

    /**
     * 插入头结点
     * 1.分配内存空间,保存新增的结点
     * 2.使新增结点指向头引用head所指向的结点
     * 3.使头引用head指向新增结点
     */
    CLType CLAddFirst(CLType head, DATA nodeData) {
        CLType node;
        if ((node = new CLType()) == null) {
            System.out.println("申请内存失败!");
            return null;
        } else {
            node.nodeData = nodeData; //保存新增结点数据
            node.nextNode = head;     //指向头引用所指向的结点
            head = node;               //头引用指向新增结点,即新增结点变为头结点
            return head;
        }
    }

    /**
     * 查找结点
     * 在链表中查找需要的元素
     * 通过关键字查找需要元素
     */
    CLType CLFindNode(CLType head, String key) {
        CLType htemp;
        htemp = head;
        while (htemp != null) {
            //如果当前结点的关键字和传入的关键字相同
            if (htemp.nodeData.key.compareTo(key) == 0) {
                return htemp;
            }
            htemp = htemp.nextNode;    //处理下一个结点
        }
        return null;     //返回空引用
    }

    /**
     * 插入结点(在链表中间指定位置插入数据)
     * 1.分配内存地址,保存新增的结点
     * 2.从head开始找到要插入的逻辑位置,也就是位于那两个结点之间
     * 3.修改要插入位置前结点的指向为新插入的结点,修改新插入结点的为后结点
     */
    CLType CLInsert(CLType head, String findKey, DATA nodeData) {

        CLType node, nodeTemp;
        if ((node = new CLType()) == null) {
            System.out.println("申请内存失败!");
            return null;
        }
        node.nodeData = nodeData; //保存新增的结点
        nodeTemp = CLFindNode(head, findKey);   //找出要插入的位置的关键结点

        if (nodeTemp != null) {
            node.nextNode = nodeTemp.nextNode;   //新插入结点指向关键结点的下一个结点
            nodeTemp.nextNode = node;              //关键结点的下一个结点指向新增结点
        } else {
            System.out.println("未找到要正确插入的位置!");
        }
        return head;
    }

    /**
     * 删除结点
     * 1.查找需要删除的结点
     * 2.使前一个结点指向要删除节点的下一个结点
     * 3.删除结点
     */

    int CLDelete(CLType head, String key) {
        CLType node, hTemp;      //node保存删除结点的前一结点
        hTemp = head;
        node = head;
        while (hTemp != null) {
            if (hTemp.nodeData.key.compareTo(key) == 0) {  //找到关键字,执行删除
                node.nextNode = hTemp.nextNode;   //使前一结点指向当前结点的下一结点
                hTemp = null;                       //释放内存
                return 1;
            } else {
                node = hTemp;                       //指向当前结点
                hTemp = hTemp.nextNode;             //指向下一节点
            }
        }
        return 0;                                   //删除失败
    }

    /**
     * 计算链表长度
     */
    int CLLength(CLType head) {
        CLType htemp;
        int Len = 0;
        htemp = head;
        while (htemp != null) {        //遍历整个列表
            Len++;
            htemp = htemp.nextNode;
        }
        return Len;
    }

    /**
     * 显示所有结点
     */
    void CLAllNode(CLType head) {
        CLType htemp;
        DATA nodeData;
        htemp = head;
        System.out.println("当前链表中共存放了" + CLLength(head) + "条数据,其结构如下:");
        while (htemp != null) {
            nodeData = htemp.nodeData;  //循环每个结点的值
            System.out.println(nodeData.key + " " + nodeData.name + " " + nodeData.age);
            htemp = htemp.nextNode;
        }
    }
}
//-------------------------------------------------------------------------

/**
 * 2.对链式表的操作
 */
public class LInkedTable {

    public static void main(String[] args) {
        //初始化
        CLType node, head = null;
        CLType CL = new CLType();
        String key, findKey;


        Scanner input = new Scanner(System.in);
        System.out.println("链表测试.先输入链表中的数据,格式为(关键字 姓名 年龄)");
        //添加数据
        do {
            DATA nodeData = new DATA();
            nodeData.key = input.next();
            if (nodeData.key.equals("0")) {
                break;                   //若输入0 则退出,为了不一直循环
            } else {
                nodeData.name = input.next();
                nodeData.age = input.nextInt();
                head = CL.CLAddEnd(head, nodeData);  //在链表尾追加数据
            }
        } while (true);
        CL.CLAllNode(head);         //显示所有结点

        System.out.println("演示插入结点,输入插入位置的关键字:");
        findKey = input.next();
        System.out.println("请输入要插入的结点数据:(关键字(非0) 姓名 年龄)");
        DATA nodeData = new DATA();
        nodeData.key = input.next();
        nodeData.name = input.next();
        nodeData.age = input.nextInt();
        CL.CLInsert(head, findKey, nodeData);
        CL.CLAllNode(head);         //显示所有结点

        System.out.println("演示删除结点,输入要删除结点的关键字:");
        key=input.next();
        CL.CLDelete(head,key);
        CL.CLAllNode(head);         //显示所有结点

        System.out.println("演示在链表中查找,输入要查找结点的关键字:");
        key=input.next();
        node = CL.CLFindNode(head, key);
        if(node==null){
            System.out.println("查询的结点"+key+"不存在!");
        }else{
            nodeData = node.nodeData;
            System.out.println("关键字对应的节点为:"+nodeData.key+" "+nodeData.name+" "+nodeData.age);
        }

    }
}
