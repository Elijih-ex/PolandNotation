package com.elijih.part6_sort;

import java.util.Arrays;
/**
 * 冒泡排序
 * 时间复杂度：o(n^2)
 * */
public class Demo01_BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3,9,-1,10,-2};

        //定义一个中间变量
        int temp=0;
        //外层for循环表示总共排序的次数
        for (int i=0;i<arr.length-1;i++){
            //内层for循环是每次排序的具体运作，每一次排序都会把当前最大的那个数据挪到最后，所以每次都比上一次少管一个数
            for(int j=0;j<arr.length-1-i;j++){
                if (arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.printf("第%d次排序的结果是%s\n",i+1, Arrays.toString(arr));
        }
    }
}
