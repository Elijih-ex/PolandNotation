package com.elijih.part2_linkedlist;

public class SingleLinkedListDemo {
    /**
     * 用漫威英雄的案例来模拟单链表的增删改查
     * */
    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode node1 = new HeroNode(1,"Tony","IronMan");
        HeroNode node2 = new HeroNode(2,"Steve","Captain America");
        HeroNode node3 = new HeroNode(3,"Javis","Vison");
        HeroNode node4 = new HeroNode(4,"Romannove","BlackWidow");

        //创建链表
        //测试添加方法1
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(node1);
        singleLinkedList.add(node2);
        singleLinkedList.add(node3);
        singleLinkedList.add(node4);

        //测试添加方法2
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.insert(node1);
        singleLinkedList2.insert(node4);
        singleLinkedList2.insert(node3);
        singleLinkedList2.insert(node2);


        //测试修改方法
        HeroNode newHeroNode = new HeroNode(4,"Bruce","Batman");
        singleLinkedList2.update(newHeroNode);

        //测试删除方法
        singleLinkedList2.delete(5);
        singleLinkedList2.delete(1);
        singleLinkedList2.delete(4);
        singleLinkedList2.delete(2);
        singleLinkedList2.delete(3);
        singleLinkedList2.delete(3);

        //显示链表
//        singleLinkedList.showLinkedList();
        singleLinkedList2.showLinkedList();
    }
}
/**
 * 创建一个SingleLinkedList类来管理英雄
 * */
class SingleLinkedList{
    //先初始化一个头节点，这个头节点不能动，不存放数据
    private HeroNode head = new HeroNode(0,"","");

    /**
     * 添加节点的方法，此时是不考虑顺序的添加，直接添加在链表末尾
     *     思路：1、找到当前链表的最后一个节点 2、将这个节点的next指向要添加的节点
     * */
    public void add(HeroNode node){
        //头节点不能动，所以创建一个辅助变量来遍历链表
        HeroNode temp = head;

        while (true){
            //当节点的next为空时，则说明找到了最后一个节点
            if(temp.next == null){
                break;
            }
            //不为空时，则temp后移
            temp = temp.next;
        }
        //当退出while循环时，说明已经找到了最后一个节点，现在把这个节点的next指向要添加的节点
        temp.next = node;
    }

    /**
     * 添加节点，该方法为有序添加，按照id的大小顺序进行添加
     * */
    public void insert(HeroNode node){
        //先创建辅助节点
        HeroNode temp = head;
        //创建一个标志，用来表示是否要添加的节点id是否在链表中已经存在
        boolean flag = false;
        //从头节点开始进行遍历
        while (true){
            //先判断是否temp节点已经到了链表的最后，如果到了就直接添加在末尾
            if(temp.next == null){
                break;
            }
            //当辅助节点的下一个节点的id大于要插入的节点，则说明位置找到
            if(temp.next.id > node.id){
                break;
            }else if (temp.next.id == node.id){
                //当辅助节点的下一个节点的id等于要插入的节点，则说明元素重复
                flag = true;
                break;
            }else {
                temp = temp.next;
            }
        }
        //插入操作
        if (flag){
            System.out.println("要插入的节点和链表中已有节点的id重复，不可插入");
        }else {
            node.next = temp.next;
            temp.next = node;
        }

    }

    /**
     * 修改节点信息，根据id来进行查找，但不能修改id
     * */
    public void update(HeroNode newHeroNode){
        //先判断链表是否为空，空的话则不能修改
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //不为空，则进行遍历
        //1.先创建辅助节点,从头节点的下一个节点开始
        HeroNode temp = head.next;
        //2.标志，是否找到
        boolean flag = false;
        //3.开始遍历
        while (true){
            //判断是否已经遍历完
            if (temp == null){
                break;
            }

            if (temp.id == newHeroNode.id){
                //找到
                flag=true;
                break;
            }else{
                temp = temp.next;
            }


        }
        //4.修改节点
        if(flag){
            temp.name=newHeroNode.name;
            temp.nickName=newHeroNode.nickName;
        }else {
            System.out.println("没有找到id为"+newHeroNode.id+"的节点，无法修改");
        }
    }

    /**
     * 删除节点
     * 思路：
     * 1.使用temp找到待删除节点的前一个节点
     * 2.比较的时候，比较的是temp.next.id和待删除节点的id
     * 3.修改待删除节点的前一个节点的next指向为待删除节点的后一个节点，即temp.next=temp.next.next
     * */
    public void delete(int id){
        //先判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //创建辅助节点和标志
        HeroNode temp = head;
        boolean flag = false;
        //开始遍历
        while (true){
            //判断是否已经遍历完
            if(temp.next == null){
                break;
            }
            //判断是否找到了待删除节点
            if(temp.next.id == id){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //删除操作
        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.println("未找到id为"+id+"的待删除节点");
        }

    }

    /**
     * 显示链表，需要遍历链表，因为头节点不能动，所以仍旧需要一个辅助变量来进行遍历
     * */
    public void showLinkedList(){
        //先判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //如果不为空，则进行遍历
        HeroNode temp = head.next;
        while (true){
            //先判断是否到了链表的最后
            if(temp == null){
                break;
            }
            //不是，则输出,同时指针要后移
            System.out.println(temp);
            temp = temp.next;
        }


    }
}




/**
 * 创建一个类HeroNode用来创建英雄节点，每一个英雄就是一个节点
 * */
class HeroNode{
    public int id;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int id,String name,String nickName){
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方便，重写toString

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
