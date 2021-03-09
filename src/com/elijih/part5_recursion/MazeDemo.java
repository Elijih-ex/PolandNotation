package com.elijih.part5_recursion;
/**
 * 迷宫回溯问题
 * 小球找路
 *
 * 待实现：1.策略改为上右下左
 *       2.找到最短路径
 * 思路：实现不同的策略，将每个策略所走的“2”保存在不同的集合里，哪个集合最小，谁就是最短路径
 * */
public class MazeDemo {
    public static void main(String[] args) {
        //创建地图
        int[][] map = new int[8][7];

        //设置周围一圈是墙
        //先设置上下两边
        for (int i = 0; i < map[i].length; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //再设置左右两边
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置里边的挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //打印设置好的地图
        System.out.println("原始的地图是这样的");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }


        findPath(map,1,1);

        //输出走过的路
        System.out.println("走完的地图是这样的");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }
    //使用递归回溯来给小球找路
    public static boolean findPath(int[][] map,int i, int j){
        //传入的参数是地图和小球的起点
        if (map[6][5] == 2){
            //说明出口找到了
            return true;
        }else{
            if (map[i][j]==0){
                //说明这个点还没走过
                map[i][j]=2;//假定该点可以走通
                //策略（方法）： 下 -> 右 -> 上 -> 左
                if (findPath(map,i+1,j)){
                    return true;
                }else if (findPath(map,i,j+1)){
                    return true;
                }else if (findPath(map,i-1,j)){
                    return true;
                }else if (findPath(map,i-1,j)){
                    return true;
                }else {
                    //说明这个点走不通
                    map[i][j]=3;
                    return false;
                }
            }
            else{
                //如果mao[i][j] != 0,那么可能是1，2，3
                return false;
            }
        }
    }


}



