package com.elijih.part5_recursion;
/**
 * 递归
 * 阶乘的实现
 * */
public class FactorialDemo {
    public static void main(String[] args) {
        Factorial f = new Factorial();
        System.out.println(f.factorial(3));
    }
}

class Factorial{
    public int factorial(int n){
        if(n == 1){
            return 1;
        }else {
            return factorial(n-1)*n;
        }
    }
}
