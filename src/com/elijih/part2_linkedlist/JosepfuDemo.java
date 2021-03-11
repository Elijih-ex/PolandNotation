package com.elijih.part2_linkedlist;

public class JosepfuDemo {
    public static void main(String[] args) {
        //测
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.add(125);
        circleLinkedList.showLinkedList();
        circleLinkedList.Josepfu(10,20,125);
    }
}

/**
 * 创建一个唤醒链表类，管理节点
 * */
class CircleLinkedList{
    /**
     * 先创建一个first节点，不赋值
     * */
    private Boy first = null;
    /**
     * 添加小孩节点，构建环形链表
     * nums为小孩数量
     * */
    public void add(int nums){
        //对nums需要做一个校验,因为至少一个节点才能构成环形链表
        if (nums<1){
            System.out.println("nums的数值不对，无法完成环形链表");
            return;
        }

        //使用辅助指针来帮助构建环形链表
        Boy curBoy = null;
        for (int i=1;i<=nums;i++){
            //先处理第一个节点的情况
            if(i == 1){
                Boy boy = new Boy(1);
                first = boy;
                first.setNext(first);
                curBoy=first;
                continue;
            }
            //开始插入节点
            Boy boy = new Boy(i);
            curBoy.setNext(boy);
            boy.setNext(first);
            curBoy = boy;
        }
        //System.out.println(first.getId());
    }
    /**
     * 解决约瑟夫问题
     * startId:开始报数的孩子
     * countNum:报数次数
     * nums:孩子总数
     * */
    public void Josepfu(int startId,int countNum,int nums){
        //先校验数据
        if(startId < 1 || startId > nums || first == null){
            System.out.println("参数有误");
            return;
        }


        //两个指针

        /*Boy helper = new Boy(nums);
        * 不能这样做，原因是链表已经用add方法建立，这样做是新建一个节点，和原先的链表没有关系，所以会出现空指针异常
        * */

        Boy helper = first;
        /*while (true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }*/

        for (int i=0;i<nums-1;i++){
            helper=helper.getNext();
        }
        System.out.println(helper.getId());

        //移动指针到准备开始报数小孩的位置
        for (int j=0;j<startId-1;j++){
            System.out.println("-----");
            first = first.getNext();
            helper = helper.getNext();
        }
        //开始处理
        while (true){
            if (helper == first){
                //说明遍历完毕
                System.out.println("*********");
                break;
            }
            for (int j=0;j<countNum-1;j++){
                first=first.getNext();
                helper=helper.getNext();
            }
            System.out.printf("第%d个小孩出列\n",first.getId());
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后一个出圈的小孩id是%d",first.getId());
    }

    /**
     * 展示类，用来展示创建的链表，即遍历链表
     * */
    public void showLinkedList(){
        //先判读链表是否为空
        if(first == null){
            System.out.println("链表为空");
            return;
        }
        //辅助指针帮助遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("第%d个小孩 \n",curBoy.getId());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

}


/**
 * 创建一个节点类，模拟小孩/
 */

class Boy{
    private int id;
    private Boy next;

    public Boy(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
