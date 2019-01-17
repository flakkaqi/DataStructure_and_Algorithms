package com.数据结构.树结构;

import java.util.Scanner;

/**
 * 完全二叉树的性质
 * 1.对于任意一个结点m，如果m != 1 ,那他的父节点编号为m/2
 * 2.如果2m <= n,则结点m的左子树根结点编号为2m；若2m > n 则无左子树，更没有右子树
 * 3.若果2m+1 <= n,则结点m的右子树根节点编号为2m+1；若2m+1>n,则无右子树。
 * 4.对于该完全二叉树来说，深度为[log2n]+1.
 */

//-------------------------------------准备数据-----------------------------//
class CBTType {  //定义二叉树结点类型，采用链式存储
    String data;    //元素数据
    CBTType left;   //左子树结点的引用
    CBTType right;   //右子树结点的引用
}

public class TreeStructrue {
    static final int MAXLEN = 20;    //最大长度
    static Scanner input = new Scanner(System.in);

    //-----------------------------------初始化二叉树----------------------------//
    CBTType InitTree() {     //初始化二叉树的根
        CBTType node;
        if ((node = new CBTType()) != null) {    //申请内存
            System.out.println("请先输入一个根结点数据：");
            node.data = input.next();
            node.left = null;
            node.right = null;
            if (node != null) {         //如果二叉树根节点不为空
                return node;
            }
            return null;
        }
        return null;
    }

    //---------------------------添加结点-----------------------------//
    //添加结点除了要输入结点数据外，还要指定其父节点和左子树和右子树。
    void AddTreeNode(CBTType treeNode) {
        CBTType pnode, parent;
        String data;
        int menusel;

        if ((pnode = new CBTType()) != null) {    //分配内存
            System.out.println("输入二叉树结点数据：");
            pnode.data = input.next();       //添加结点数据
            pnode.left = null;               //设置左子树为null
            pnode.right = null;              //设置右子树为null
            System.out.println("输入该结点的父结点的数据：");
            data = input.next();
            parent = TreeFindNode(treeNode, data);     //查找指定数据的结点
            if (parent == null) {
                System.out.println("未找到要插入的结点");
                pnode = null;
                return;
            }
            System.out.println("1.添加该结点到左子树\n2.添加该结点到右子树");
            do {
                menusel = input.nextInt();    //输入选择项
                if (menusel == 1 || menusel == 2) {
                    if (parent == null) {
                        System.out.println("父节点不存在，请先设置父节点！");
                    } else {
                        switch (menusel) {
                            case 1:         //添加到左结点
                                if (parent.left != null) {
                                    System.out.println("左子树不为空");
                                } else {
                                    parent.left = pnode;
                                }
                                break;
                            case 2:         //添加到右结点
                                if (parent.right != null) {
                                    System.out.println("左子树不为空");
                                } else {
                                    parent.right = pnode;
                                }
                                break;
                            default:
                                System.out.println("无效参数");
                        }
                    }
                }
            } while (menusel != 1 && menusel != 2);
        }
    }

    //--------------------------查找结点---------------------------------//
    //查找结点就是在遍历二叉树中的每一个结点，逐个比较数据，找到目标时将
    //返回数据所在结点的引用。
    //参数一：待查找的二叉树的根结点 参数二：待查找结点的数据
    CBTType TreeFindNode(CBTType treeNode, String data) {
        CBTType ptr;
        if (treeNode == null) {    //判断根节点是否为空
            return null;
        } else {
            if (treeNode.data.equals(data)) {
                return treeNode;
            } else {      //分别向左右子树递归查找
                if ((ptr = TreeFindNode(treeNode.left, data)) != null) {
                    return ptr;
                } else if ((ptr = TreeFindNode(treeNode.right, data)) != null) {
                    return ptr;
                } else {
                    return null;
                }
            }
        }
    }

    //---------------------------获取左子树-----------------------------//
    //获取左子树就是返回当前结点的左子树结点的值。
    //参数：二叉树的任意一个要查的结点
    CBTType TreeLeftNode(CBTType treeNode) { //获取左子树的值
        if (treeNode != null) {
            return treeNode.left;   //返回当前结点左子树的值
        }
        return null;
    }

    //---------------------------获取右子树-----------------------------//
    //获取右子树就是返回当前结点的右子树结点的值。
    //参数：二叉树的任意一个要查的结点
    CBTType TreeRightNode(CBTType treeNode) { //获取右子树的值
        if (treeNode != null) {
            return treeNode.right;   //返回当前结点右子树的值
        }
        return null;
    }

    //--------------------------判断空树--------------------------------//
    //判断空树就是判断一个二叉树结构是否为空。空树则表示没有数据。
    Boolean TreeIsEmpty(CBTType treeNode) {
        return treeNode == null;
    }

    //--------------------------计算二叉树的深度----------------------//
    //计算二叉树的深度就是计算二叉树中结点的最大层数，往往需要利用递归算法实现。
    int TreeDepth(CBTType treeNode) {
        int depleft, depright;
        if (treeNode == null) {
            return 0;   //空树，深度为0
        }
        depleft = TreeDepth(treeNode.left); //左子树深度（递归）
        depright = TreeDepth(treeNode.right); //右子树深度（递归）
        if (depleft > depright) {
            return depleft + 1;                 //每次递归计数加一
        } else {
            return depright + 1;
        }
    }

    //------------------------清空二叉树-----------------------------//
    //清空二叉树就是使二叉树变为空树，需要递归实现
    void ClearTree(CBTType treeNode) {
        if (treeNode != null) {
            ClearTree(treeNode.left);   //递归清空左子树
            ClearTree(treeNode.right);  //递归清空右边子树
            treeNode = null;              //释放当前结点所占用的内存
        }
    }

    //---------------------显示结点数据----------------------------------//
    //显示结点数据就是显示当前结点的数据内容
    void TreeNodeData(CBTType treeNode) {
        System.out.println(treeNode.data);  //打印结点数据
    }
    //--------------------------遍历二叉树-----------------------------//
    //遍历二叉树就是逐个查找二叉树中的所有元素，这是二叉树的基本操作
    //常用遍历算法：
    //1.分层遍历：因为树结构本身是一种层次结构，所以可以使用循环队列逐层循环遍历，但较为复杂
    //2.先序遍历：按照先遍历根结点，再遍历左子树，最后遍历右子树的顺序
    //3.中序遍历：按照先遍历左子树，再遍历根结点，最后遍历右子树的顺序
    //4.后序遍历：按照先遍历左子树，再遍历右子树，最后遍历根结点的顺序
    //先序遍历、中序遍历、后序遍历最大的好处是可以利用递归的思想实现遍历算法

    /**
     * 1.按层遍历算法
     * 程序从根节点开始,逐个将每层的结点加如队列,最终队列就是结果
     */
    void LevelTree(CBTType treeNode) {
        CBTType p;
        CBTType[] q = new CBTType[MAXLEN];    //定义一个顺序栈
        int head = 0, tail = 0;

        if (treeNode != null) {         //如果队首引用不为空
            tail = (tail + 1) % MAXLEN;   //计算循环队列队尾的序号
            q[tail] = treeNode;       //将二叉树根引入进队
        }
        while (head != tail) {
            head = (head + 1) % MAXLEN;   //计算循环队列队首的序号
            p = q[head];              //获取队首元素
            TreeNodeData(p);        //处理队首元素
            if (p.left != null) {          //如果结点存在于左子树
                tail = (tail + 1) % MAXLEN;   //计算循环队列队尾的序号
                q[tail] = p.left;        //将左子树引入进队
            }
            if (p.right != null) {         //如果结点存在于右子树
                tail = (tail + 1) % MAXLEN;   //计算循环队列队尾的序号
                q[tail] = p.right;        //将右子树引入进队
            }
        }
    }

    /**
     * 2.先序遍历算法
     */
    void DLRTree(CBTType treeNode) {
        if (treeNode != null) {
            TreeNodeData(treeNode);     //显示结点的数据
            DLRTree(treeNode.left);
            DLRTree(treeNode.right);
        }
    }

    /**
     * 3.中序遍历算法
     */
    void LDRTree(CBTType treeNode) {
        if (treeNode != null) {
            LDRTree(treeNode.left);
            TreeNodeData(treeNode);
            LDRTree(treeNode.right);
        }
    }

    /**
     * 4.后序遍历算法
     */
    void LRDTree(CBTType treeNode) {
        if (treeNode != null) {
            LDRTree(treeNode.left);
            LDRTree(treeNode.right);
            TreeNodeData(treeNode);
        }
    }

    //------------------------------树结构的实例操作-----------------------//
    public static void main(String[] args) {
        CBTType root = null;     //root为指向二叉树根节点的引用
        int menusel;
        TreeStructrue t = new TreeStructrue();
        root = t.InitTree();  //设置根元素
        //添加结点
        do {
            System.out.println("请选择菜单添加二叉树的结点");
            System.out.println("0.退出\t");     //显示菜单
            System.out.println("1.添加二叉树的结点");
            menusel = input.nextInt();
            switch (menusel) {
                case 0:
                    break;
                case 1:
                    t.AddTreeNode(root);
                default:
                    ;
            }
        }while (menusel!=0);

        //遍历
        do {
            System.out.println("请选择菜单遍历二叉树,输入0表示退出:");
            //显示菜单
            System.out.print("1.先序遍历DLR\t");
            System.out.println("2.中序遍历LDR");
            System.out.print("3.后序遍历LRD\t");
            System.out.println("4.按层遍历");
            menusel=input.nextInt();
            switch (menusel){
                case 0:
                    break;
                case 1:
                    System.out.println("先序遍历结果为:");
                    t.DLRTree(root);
                    System.out.println();
                    break;
                case 2:
                    System.out.println("中序遍历结果为:");
                    t.LDRTree(root);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("后序遍历结果为:");
                    t.LRDTree(root);
                    System.out.println();
                    break;
                case 4:
                    System.out.println("按层遍历结果为:");
                    t.LevelTree(root);
                    System.out.println();
                    break;
                    default:
                        ;
            }
        }while (menusel!=0);
        //深度
        System.out.println("二叉树的深度为:"+t.TreeDepth(root));
        t.ClearTree(root);  //清空二叉树
        root=null;  //释放内存空间
    }
}
//--------------------------------------结果展示----------------------------------------//
/*
请先输入一个根结点数据：
tom
请选择菜单添加二叉树的结点
0.退出
1.添加二叉树的结点
1
输入二叉树结点数据：
tson1
输入该结点的父结点的数据：
tom
1.添加该结点到左子树
2.添加该结点到右子树
1
请选择菜单添加二叉树的结点
0.退出
1.添加二叉树的结点
1
输入二叉树结点数据：
tson2
输入该结点的父结点的数据：
tom
1.添加该结点到左子树
2.添加该结点到右子树
2
请选择菜单添加二叉树的结点
0.退出
1.添加二叉树的结点
0
请选择菜单遍历二叉树,输入0表示退出:
1.先序遍历DLR	2.中序遍历LDR
3.后序遍历LRD	4.按层遍历
1
先序遍历结果为:
tom
tson1
tson2

请选择菜单遍历二叉树,输入0表示退出:
1.先序遍历DLR	2.中序遍历LDR
3.后序遍历LRD	4.按层遍历
0
二叉树的深度为:2
 */

