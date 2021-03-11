package com.elijih.part2_linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1,"Peter","SpiderMan");
        HeroNode2 hero2 = new HeroNode2(3,"Mathe","DareDevil");
        HeroNode2 hero3 = new HeroNode2(5,"Bk","WhiteWolf");
        HeroNode2 hero4 = new HeroNode2(7,"j","HawkEye");
        HeroNode2 hero5 = new HeroNode2(1,"strange","doctor");
        HeroNode2 hero6 = new HeroNode2(6,"banner","Halk");

        //创建双链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        //添加节点
        doubleLinkedList.addToEnd(hero1);
        doubleLinkedList.addToEnd(hero2);
        doubleLinkedList.addToEnd(hero3);
        doubleLinkedList.addToEnd(hero4);
        doubleLinkedList.insert(hero5);
        doubleLinkedList.insert(hero6);
        doubleLinkedList.showLinkedList();

        //修改节点
        /*HeroNode2 newHeroNode = new HeroNode2(5,"Jhon","Spada");
        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.showLinkedList();*/

        //删除节点
        /*doubleLinkedList.delete(1);
        doubleLinkedList.delete(2);
        doubleLinkedList.delete(3);
        doubleLinkedList.delete(4);
        doubleLinkedList.showLinkedList();*/



    }
}

/**
 * 双向链表类，用来管理链表
 * */
class DoubleLinkedList{
    //初始化一个头节点
    HeroNode2 head = new HeroNode2(0,"","");

    /**
     *增加方法，增加到最后
     * */
    public void addToEnd(HeroNode2 node){
        //头节点不能动，所以创建一个辅助变量来遍历链表
        HeroNode2 temp = head;

        while (true){
            //当节点的next为空时，则说明找到了最后一个节点
            if(temp.next == null){
                break;
            }
            //不为空时，则temp后移
            temp = temp.next;
        }
        //当退出while循环时，说明已经找到了最后一个节点，现在把这个节点的next指向要添加的节点,要添加节点的pre指向这个节点
        temp.next = node;
        node.pre=temp;
    }

    /**
     *添加方法，根据id大小添加到合适的位置
     * */
    public void insert(HeroNode2 node){
        //创建辅助节点
        HeroNode2 temp = head;
        //创建一个标志，用来表示要添加的节点id是否在链表中已经存在
        boolean flag = false;
        //从头节点开始进行遍历
        while (true){
            //判断是否temp节点已经到了链表的最后，如果到了就直接添加在末尾
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
            }
                temp = temp.next;
        }

        //插入操作
        if (flag){
            System.out.println("要插入的id为"+node.id+"的节点和链表中已有节点的id重复，不可插入");
        }else {
            //node节点指向temp的下一个节点
            node.next=temp.next;
            if(temp.next != null){
                temp.next.pre = node;
            }

            //temp节点指向node节点
            temp.next=node;
            node.pre=temp;



        }

    }


    /**
     * 删除方法
     * */
    public void delete(int id){
        //先判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //创建辅助节点和标志,因为可以自我删除，所以直接从第一个节点开始找即可
        HeroNode2 temp = head.next;
        boolean flag = false;
        //开始遍历
        while (true){
            //判断是否已经遍历完
            if(temp == null){
                break;
            }
            //判断是否找到了待删除节点
            if(temp.id == id){
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //删除操作
        if(flag){
            temp.pre.next=temp.next;
            if(temp.next != null){
                temp.next.pre=temp.pre;
            }

        }else{
            System.out.println("未找到id为"+id+"的待删除节点");
        }
    }

    /**
     * 修改节点
     * */
    public void update(HeroNode2 newHeroNode){
        //先判断链表是否为空，空的话则不能修改
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //不为空，则进行遍历
        //1.先创建辅助节点,从头节点的下一个节点开始
        HeroNode2 temp = head.next;
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
     * 遍历双向链表的方法
     * */
    public void showLinkedList(){
        //先判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //如果不为空，则进行遍历，从第一个节点开始
        HeroNode2 temp = head.next;
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
 * 生成节点的类
 * */
class HeroNode2 {
    public int id;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向上一个节点


    //构造器
    public HeroNode2(int id, String name, String nickName) {
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
