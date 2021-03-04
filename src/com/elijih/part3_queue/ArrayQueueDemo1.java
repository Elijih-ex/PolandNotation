package com.elijih.part3_queue;

import java.util.Scanner;

public class ArrayQueueDemo1 {
/**
 * 使用数组模拟队列
 *
 * */
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key=scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                case 'g':
                    try{
                        int result = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int result = arrayQueue.headQueue();
                        System.out.printf("队列的头数据为%d\n",result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
                    }
            }
        System.out.println("程序退出");
    }
}

class ArrayQueue{
    private int maxSize;//表示数组的最大容量
    private int front ;//队列头
    private int rear;//队列尾
    private int arr[];//该数组用于存放数据，模拟队列

    /**
     * 创建队列的构造器
     * */
    public ArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr = new int[maxSize];
        front=-1;//指向队列头部，定义为-1则指向的是队列头部的前一个位置，定义为0则指向队列头部
        rear=-1;//指向队列尾部，即队列的最后一个数据

    }

    /**
     * 判断队列是否已满
     * */
    public boolean isFull(){
        return rear == maxSize-1;
    }

    /**
     * 判断队列是否为空
     * */
    public boolean isEmpty(){
        return  rear == front;
    }

    /**
     * 添加数据
     * */
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    /**
     * 获取队列的数据，出队列
     * */
    public int getQueue(){
        //先判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }

        front++;
        return arr[front];

    }

    /**
     * 显示队列的所有数据
     * */
    public void showQueue(){
        //先判断队列是否为空
        if(isEmpty()){
            System.out.println("队列为空");
            return;
        }

        for(int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    /**
     * 显示队列的头部数据，不取出
     * */
    public int headQueue(){
        //判断
        if(isEmpty()){
            throw new RuntimeException("队列是空的，无头数据");
        }

        return arr[front+1];
    }
}


