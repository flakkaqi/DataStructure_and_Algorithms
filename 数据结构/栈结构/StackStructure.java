package com.数据结构.栈结构;

import java.util.Scanner;

//------------------------------------准备数据----------------------------------------------//
class DATA {
    String name;
    int age;

}

class StackType {
    //java数组的下标是从0开始的,为方便演示我们从1开始
    static final int MAXLEN = 50;
    DATA[] data = new DATA[MAXLEN + 1];  //数据元素
    int top;    //栈顶    top=0 ：栈为空  top =SIZE 满栈

    //--------------------------------初始化栈结构--------------------------------------------//
    //1.按符号常量SIZE指定的大小申请内存空间，用来保存数据元素
    //2.设置栈顶引用值为0，表示是一个空栈
    StackType STInit() {

        StackType p;
        if ((p = new StackType()) != null) {
            p.top = 0;    //设置栈顶为0
            return p;   //返回初始化好的空栈
        }
        return null;    //申请地址失败,返回null;
    }

    //-----------------------------判断空栈-----------------------------------------------//
    //如果是空栈则可以进行入栈操作,不可以进行出栈操作
    boolean STIsEmptyStack(StackType s) {
        return s.top == 0 ? true : false;
    }

    //---------------------------判断满栈------------------------------------------------//
    //满栈只可以进行出栈操作,不能进行入栈操作
    boolean STisFullStack(StackType s) {
        return s.top == MAXLEN ? true : false;
    }

    //-------------------------清空栈---------------------------------------------------//
    void STClearStack(StackType s) {
        s.top = 0;
    }

    //---------------------------释放空间--------------------------------------------//
    //释放空间指释放栈结构所占用的内存单元
    //虽然清空栈已经将栈顶改为0了但实际占用的内存并没有释放
    void STFree(StackType s) {
        if (s != null) {
            s = null;
        }
    }

    //---------------------------入栈----------------------------------------------//
    //主要操作是将数据元素保存到栈结构中
    //步骤:
    //1.首先判断栈结构是否已满,如果top >= MAXLEN 则表示溢出,进行出错处理,否则进行入栈
    //2.设置top++,表示栈顶上移,所指向地址即为存入当前数据的位置
    //3.将入栈元素保存到top指向的位置
    int PushStack(StackType s, DATA data) {
        if ((s.top + 1) > MAXLEN) {
            System.out.println("栈溢出!");
            return 0;
        }
        s.data[++s.top] = data;     //栈顶引用+1后,将元素保存到栈顶指针所指向的地址
        return 1;
    }

    //-------------------------------出栈---------------------------------------//
    //主要操作将栈中元素取出来
    //1.判断栈顶是否是空栈top=1,如果是空栈则无法取出数据,进行出错处理,如果不是,执行出栈操作
    //2.返回栈顶所引用位置的数据,
    //3.将栈顶top-1,是栈顶指向前一个元素的地址,原来位置的元素被弹出.
    DATA PopStack(StackType s) {
        if (s.top == 0) {
            System.out.println("该栈还未存入数据,无法进行出栈");
            System.exit(0);
        }
        return (s.data[s.top--]);      //先返回数据,在修改栈顶位置
    }

    //-------------------------------读结点数据---------------------------------//
    //因为栈操作只能在一端进行操作,所以读结点实际就是对栈顶的操作
    //注意: 读结点数据只是对栈顶数据的展示,不删除数据.而出栈操作,栈顶数据就不存在了.
    DATA ReadStack(StackType s) {
        if (s.top == 0) {
            System.out.println("该栈中没有元素,读取失败!");
            return null;
        }
        return s.data[s.top];
    }
}

//----------------------------------栈结构的操作实例-------------------------------//
public class StackStructure {
    public static void main(String[] args) {
        StackType stackType = new StackType();      //创建栈
        DATA data1 = new DATA();

        StackType stack = stackType.STInit();        //初始化栈

        Scanner scanner = new Scanner(System.in);
        System.out.println("入栈操作!");
        System.out.println("请输入(姓名 年龄)");
        do {
            DATA data = new DATA();
            data.name = scanner.next();
            if (data.name.equals("0")) {
                break;      //若为0则退出循环
            } else {
                data.age = scanner.nextInt();
                stackType.PushStack(stack, data);
            }
        } while (true);

        String temp = "1";
        System.out.println("出栈操作!按任意非0键进行出栈操作");
        temp = scanner.next();
        while (!temp.equals("0")) {
            data1 = stackType.PopStack(stack);
            System.out.println("出栈的数据是:" + data1.name + " " + data1.age);
            temp = scanner.next();
        }
        System.out.println("测试结束!");
        stackType.STClearStack(stack);  //清空栈
        stackType.STFree(stack);    //释放空间
    }
}

//-----------------------------------------测试结果------------------------------------//
/*入栈操作!
        请输入(姓名 年龄)
        张三
        10
        里斯
        29
        图爱
        59
        0
        出栈操作!按任意非0键进行出栈操作
        4
        出栈的数据是:图爱 59
        5
        出栈的数据是:里斯 29
        6
        出栈的数据是:张三 10
        0
        测试结束!*/
