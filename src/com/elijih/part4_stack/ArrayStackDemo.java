package com.elijih.part4_stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        /*普通测试

        ArrayStack arrayStack = new ArrayStack(5);
        //arrayStack.pop();

        arrayStack.push(10);
        arrayStack.push(20);
        arrayStack.push(30);
        arrayStack.push(40);
        arrayStack.push(50);
        arrayStack.showStack();

        arrayStack.push(60);*/

        /**
         * 搞一个菜单来模拟更直观
         * */
        ArrayStack arrayStack = new ArrayStack(2);
        Scanner scanner = new Scanner(System.in);
        String key = "";
        boolean loop = true;//控制是否退出菜单

        while (loop){
            System.out.println("show:表示显示栈");
            System.out.println("push:表示添加数据到栈（进栈）");
            System.out.println("pop:表示从栈中取出数据（出栈）");
            System.out.println("exit:表示退出程序");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key){
                case "show":
                    arrayStack.showStack();
                    break;
                case "push":
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.println("出栈的数据为："+arrayStack.pop());
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getMessage();
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop=false;
                    break;
                default:
                    System.out.println("操作有误，请重新输入");
                    break;


            }


        }
        System.out.println("程序退出");


    }
}

class ArrayStack{
    private int maxSize;
    private int []stack;
    private int top = -1;

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        this.stack = new int [this.maxSize];
    }

    //判断栈空的方法
    public boolean isEmpty(){
        return top == -1;
    }

    //判断栈满的方法
    public boolean isFull(){
        return top+1 == maxSize;
    }

    //进栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈已满，无法存入数据");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的数据
    public void showStack(){
        if (isEmpty()){
            System.out.println("栈为空");
        }
        for (int i=top;i>=0;i--){
            System.out.printf("索引为%d的数据为%d\n",i,stack[i]);
        }
    }


}
