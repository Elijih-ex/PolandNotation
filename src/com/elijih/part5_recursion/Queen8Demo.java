package com.elijih.part5_recursion;

public class Queen8Demo {
    public static void main(String[] args) {
        Queen8 queen = new Queen8();
        queen.paly(0);
        System.out.printf("有%d种解法\n",queen.count);
        System.out.printf("冲突发生了%d次\n",queen.conflictTimes);
    }


}
class Queen8{
    //创建一个max，表示共有多少皇后
    int max = 8;

    //创建一个count，表示有几种解法
    int count = 0;

    //创建一个confilctTimes，表示冲突发生的次数
    int conflictTimes = 0;

    //定义一个一维数组，表示皇后位置放置的结果
    int array[] = new int [max];

    //创建一个方法，将皇后摆放的位置输出
    public void show (){
        for (int data:array
             ) {
            System.out.print(data+" ");
        }
        System.out.println();

    }

    //创建一个方法，检测摆放的第n+1个皇后和前边的皇后是否冲突
    public boolean isConflict(int n){
        for (int i=0;i<n;i++){
            if (array[n] == array[i] || Math.abs(n-i)==Math.abs(array[n]-array[i])){
                //判断是不是在同一列，以及是不是在同一斜线上
                //如果是，说明冲突
                conflictTimes++;
                return true;
            }
        }
        return false;
    }

    //创建方法，摆放皇后
    public void paly(int n){
        if (n == max){
            //说明已经摆放好最后一个皇后了，摆完了，因为索引最大是7，这里max=8
            count++;
            show();
            //print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i=0;i<max;i++){
            //先把当前的这个皇后n，放到该行的第一列
            array[n]=i;
            //借着判断当皇后到第i列时是否冲突
            if (!isConflict(n)){
                //不冲突，则借着放第n+1个皇后，开始递归
                paly(n+1);
            }
            //如果冲突，就会继续执行array[n]=i;
        }
    }
}
