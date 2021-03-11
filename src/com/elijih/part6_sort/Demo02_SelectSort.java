package com.elijih.part6_sort;

import java.util.Arrays;

/**
 * 选择排序
 * */
public class Demo02_SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[]{19,-1,6,37,2};
        selectSort(arr);
    }

    public static void selectSort(int[] arr){

        //外层循环表示循环的次数
        for (int i=0;i<arr.length-1;i++){
            //内层循环是每次对数组进行的操作
            int min = arr[i];
            int minIndex = i;
            for (int j=i+1;j<arr.length;j++){

                if (min > arr[j]){
                    //说明此时的最小值不是最小的
                    min = arr[j];
                    minIndex = j;
                }
            }
            //操作一轮后，已经找到了最小值和最小值的索引，此事交换两个值的位置
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        //循环结束后，排序成功，此时输出结果
        System.out.println(Arrays.toString(arr));
    }
}
