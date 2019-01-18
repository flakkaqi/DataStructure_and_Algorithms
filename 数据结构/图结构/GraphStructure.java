package com.数据结构.图结构;

//----------------------------准备数据-------------------------------//

import java.util.Scanner;

/**
 * 因为图是一个复杂的数据结构,顶点之间存在很多多对多的关系
 * 所以通常采用结构数据的形式保存顶点之间的关系.
 * 这种保存顶点之间关系的数组成为邻接矩阵
 */
//定义邻接矩阵图结构
class GraphMatrix {
    static final int MAXNUM = 20;     //图的最大顶点数
    static final int MAXVALUE = 65535;     //最大值(用于保存特殊符号"Z"的最大值)

    char[] Vertex = new char[MAXNUM];     //保存顶点信息
    int GType;      //图的类型(0,无向图 1.有向图)
    int VertexNum;  //顶点的数量
    int EdgeNum;    //边的数量
    int[][] EdgeWeight = new int[MAXNUM][MAXNUM];     //保存边的权(邻接矩阵)
    int[] isTrav = new int[MAXNUM];       //遍历的标志数组

    //-------------------创建并初始化图---------------------------------------//
    void CreateGraph(GraphMatrix GM) {
        Scanner input = new Scanner(System.in);
        int i, j, k;
        int weight;             //权重
        char EstartV, EendV;     //起点和终点
        System.out.println("输入图中各结点的信息!");
        for (i = 0; i < GM.VertexNum; i++) {
            System.out.print("请输入第" + (i + 1) + "个结点:");
            GM.Vertex[i] = (input.next().toCharArray())[0];   //保存到各顶点元素中
        }
        System.out.println("输入构成各边的顶点和权值");
        for (k = 0; k < GM.EdgeNum; k++) {
            System.out.print("第" + (k + 1) + "条:");
            EstartV = input.next().charAt(0);
            EendV = input.next().charAt(0);
            weight = input.nextInt();
            for (i = 0; EstartV != GM.Vertex[i]; i++) ;    //在已有顶点中查找开始点
            for (j = 0; EendV != GM.Vertex[j]; j++) ;          //在已有顶点中查找结束点
            GM.EdgeWeight[i][j] = weight;
            if (GM.GType == 0) {                        //若是无向图
                GM.EdgeWeight[j][i] = weight;         //在对角位置保存权值
            }
        }
    }

    //-------------------------清空图----------------------------------//
    //清空图就是将图结构变成一个空图,这里只需要将各个元素设置为MAXVALUE即可
    void ClearGraph(GraphMatrix GM) {
        int i, j;
        for (i = 0; i < GM.VertexNum; i++) {
            for (j = 0; j < GM.VertexNum; j++) {
                GM.EdgeWeight[i][j] = GraphMatrix.MAXVALUE;   //设置矩阵中各元素的值为MAXVALUE
            }
        }
    }

    //-----------------------显示图的邻接矩阵------------------------------------//
    void OutGraph(GraphMatrix GM) {      //输出邻接矩阵
        int i, j;
        for (i = 0; i < GM.VertexNum; i++) {
            System.out.print("      " + GM.Vertex[i]);  //第一行打印顶点信息
        }
        System.out.println();
        for (j = 0; j < GM.VertexNum; j++) {
            System.out.print(GM.Vertex[j] + "     ");
            for (i = 0; i < GM.VertexNum; i++) {
                if (GM.EdgeWeight[j][i] == MAXVALUE) {      //若权值为最大值
                    System.out.print("Z" + "      ");      //如果不能存在边,则输出"Z"表示无穷大
                } else {
                    System.out.print(GM.EdgeWeight[j][i] + "      ");    //输出边的权值
                }
            }
            System.out.println();
        }
    }
    //----------------------------遍历图---------------------------------//

    /**
     * 遍历图就时逐个访问图中的所有顶点.
     * 为避免遍历中又回到原点的情况发生,我们在图结构中设置了一个数组isTrav[n]
     * 该数组元素的初始值都为0,当某个顶点被遍历访问过,则设置对应的值为1,在访问
     * 顶点i时,先判断isTrav[i]中的值,如果为1,继续访问下一个顶点;
     * 如果为0,访问当前结点,依次继续..
     * 常用方法:广度优先法和深度优先法
     * 深度优先步骤:()
     * 1.从数组isTrav中选择一个未被访问的顶点Vi,将其标记为,表示已访问
     * 2.从Vi的一个从未被访问过的邻接点出发进行深度优先遍历
     * 3.重复步骤2,直到图中所有和Vi有路径相通的顶点都被访问过.
     * 4.重复步骤1至步骤3的操作,直到途中是所有顶点都被访问过.
     * 深度优先遍历算法是一个递归算法,具体过程如下:
     */
    //从第几个结点开始深度遍历图
    void DeepTraOne(GraphMatrix GM, int n) {
        int i;
        GM.isTrav[n] = 1;     //标记该点已被处理过
        System.out.print("-->" + GM.Vertex[n]);   //输出结点数据
        //添加处理结点的操作
        for (i = 0; i < GM.VertexNum; i++) {
           /* System.out.println(n +" "+ i+" "+GM.isTrav[n]);
            System.out.println(GM.EdgeWeight[n][i]);*/
            if (GM.EdgeWeight[n][i] != GraphMatrix.MAXVALUE && GM.isTrav[i] == 0) {
                DeepTraOne(GM, i);   //递归进行遍历，i是n结点的邻接结点
            }
        }
    }

    //完整的深度优先遍历
    void DeepTraGraph(GraphMatrix GM) {      //深度优先遍历
        int i;
        for (i = 0; i < GM.VertexNum; i++) {    //清除各顶点遍历标志
            GM.isTrav[i] = 0;
        }
        System.out.println("深度优先遍历结点:");
        for (i = 0; i < GM.VertexNum; i++) {
            if (GM.isTrav[i] == 0) {       //若该点未遍历
                DeepTraOne(GM, i);       //调用函数遍历
            }
        }
    }
}

//---------------------------------实例操作--------------------------//
public class GraphStructure {
    public static void main(String[] args) {
        GraphMatrix graphMatrix = new GraphMatrix();
        Scanner input = new Scanner(System.in);
        GraphMatrix GM = new GraphMatrix();     //定义保存邻接表结构的图
        System.out.println("输入生成图的类型:0无向图 1有向图");
        GM.GType = input.nextInt();
        System.out.println("输入图的顶点数<20");
        GM.VertexNum = input.nextInt();
        System.out.println("输入图的边数量<20");
        GM.EdgeNum = input.nextInt();
        graphMatrix.ClearGraph(GM);     //清空图
        graphMatrix.CreateGraph(GM);    //生成邻接表结构图
        System.out.println("该图的邻接矩证数据如下:");
        graphMatrix.OutGraph(GM);       //输出邻接矩正
        graphMatrix.DeepTraOne(GM, 3);   //指定位置遍历
        System.out.println();
        graphMatrix.DeepTraGraph(GM);    //深度优先搜索遍历图
    }
}
//-------------------------------结果展示------------------------------------//
/*
输入生成图的类型:0无向图 1有向图
0
输入图的顶点数<20
9
输入图的边数量<20
10
输入图中各结点的信息!
请输入第1个结点:0
请输入第2个结点:1
请输入第3个结点:2
请输入第4个结点:3
请输入第5个结点:4
请输入第6个结点:5
请输入第7个结点:6
请输入第8个结点:7
请输入第9个结点:8
输入构成各边的顶点和权值
第1条:0 1 1
第2条:0 2 1
第3条:1 3 1
第4条:1 4 1
第5条:3 7 1
第6条:4 7 1
第7条:7 8 1
第8条:2 5 1
第9条:2 6 1
第10条:5 6 1
Disconnected from the target VM, address: '127.0.0.1:58973', transport: 'socket'
该图的邻接矩证数据如下:
      0      1      2      3      4      5      6      7      8
0     Z      1      1      Z      Z      Z      Z      Z      Z
1     1      Z      Z      1      1      Z      Z      Z      Z
2     1      Z      Z      Z      Z      1      1      Z      Z
3     Z      1      Z      Z      Z      Z      Z      1      Z
4     Z      1      Z      Z      Z      Z      Z      1      Z
5     Z      Z      1      Z      Z      Z      1      Z      Z
6     Z      Z      1      Z      Z      1      Z      Z      Z
7     Z      Z      Z      1      1      Z      Z      Z      1
8     Z      Z      Z      Z      Z      Z      Z      1      Z
-->3-->1-->0-->2-->5-->6-->4-->7-->8
深度优先遍历结点:
-->0-->1-->3-->7-->4-->8-->2-->5-->6
 */

