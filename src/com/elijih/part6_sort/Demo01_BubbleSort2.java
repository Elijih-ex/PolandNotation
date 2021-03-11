package com.elijih.part6_sort;

import java.util.Arrays;

public class Demo01_BubbleSort2 {
    public static void main(String[] args) {
        //int[] arr = new int[]{3,9,-1,10,-2};
        int[] arr = new int[]{1,2,3,4,5,6};

        //定义一个中间变量
        int temp=0;
        //定义一个表示符，来判断在某次循环中，是否有元素发生交换
        boolean flag = false;
        //外层for循环表示总共排序的次数
        for (int i=0;i<arr.length-1;i++){
            //内层for循环是每次排序的具体运作，每一次排序都会把当前最大的那个数据挪到最后，所以每次都比上一次少管一个数
            for(int j=0;j<arr.length-1-i;j++){
                if (arr[j]>arr[j+1]){
                    flag = true;//表示进入了这次循环，下次继续循环
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.printf("第%d次排序的结果是%s\n",i+1, Arrays.toString(arr));
            if (!flag){
                //此时表示这次循环里，没有元素发生移动，已经排序好了
                break;
            }else {
                flag = false;//这里需要将flag重置，否则flag就一直是true了，判断就会失效
            }

        }
    }
}
