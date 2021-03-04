package com.elijih.part3_queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    /**
     * 用数组来模拟环形队列
     * */
    public static void main(String[] args) {
        /**
         * 测试数组
         * */
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            System.out.println("n(effectiveData):查看队列的有效数据个数");
            key=scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    circleArrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输一个数");
                    int value = scanner.nextInt();
                    circleArrayQueue.addQueue(value);
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                case 'g':
                    try{
                        int result = circleArrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'n':
                    int res = circleArrayQueue.effctiveData();
                    System.out.println("有效数据个数为"+res);
                    break;
                case 'h':
                    try {
                        int result = circleArrayQueue.headQueue();
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

class CircleArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int []arr;

    /**
     * 构造器
     * */
    public CircleArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr = new int[maxSize];
    }
    /**
     * 判断是否为空
     * */
    public boolean isEmpty(){
        return rear == front;
    }
    /**
     * 判断是否已满
     * */
    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }
    /**
     * 添加数据
     * */
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满");
            return;
        }

        arr[rear] = n;
        rear=(rear+1)%maxSize;
    }
    /**
     * 获取队列的第一个数据，出队列
     * */
    public int getQueue(){
        //先判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        //因为需要把front后移，所以不能直接返回当前front指向的值，而是需要借助一个临时变量，先把front后移，然后再返回临时变量
        int value = arr[front];
        front = (front+1)%maxSize;
        return value;
    }
    /**
     * 显示队列有几个有效数据
     * */
    public int effctiveData(){
        return (rear+maxSize-front)%maxSize;
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

        for(int i=front;i<front+(rear+maxSize-front)%maxSize;i++){
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
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

        return arr[front];
    }
}
