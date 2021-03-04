package com.elijih.part1_sparseArray;

import java.io.*;

public class chess {
    public static void main(String[] args) throws IOException {
        //创建一个原始的二维数组11*11
        //0：没有棋子；1：黑色棋子；2：白色棋子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        System.out.println("输出原始的二维数组");
        for (int[] row :chessArr1) {
            for (int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组转稀疏数组
        //1.先遍历二维数组，找到非0数据的个数
        int sum=0;
        for(int i=0;i<chessArr1.length;i++){
            for(int j=0;j<chessArr1[i].length;j++){
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("sum="+sum);

        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //遍历二维数组，将非0的数据存放到稀疏数组
        int count=0;//用于记录是第几个非0数据
        for(int i=0;i<chessArr1.length;i++){
            for(int j=0;j<chessArr1[i].length;j++){
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }
            }
        }

        /**
         *
         * 将稀疏数组存入到文件中
         * */

        //创建需要存入的文件
        File file = new File("D:\\idea\\DataStructures\\src\\Files\\sparse.txt");
        System.out.println("创建成功");

        //读取数组中的数据，存入文件中
        FileWriter fw = new FileWriter(file);

        for(int i=0;i<sparseArr.length;i++){
            for(int j=0;j<sparseArr[i].length;j++){
                fw.write(sparseArr[i][j]+"\t");
            }
            fw.write("\r\n");
        }

        fw.close();


        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为：");
        for (int i=0;i<sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        /**
         * 将稀疏数组恢复为原始二维数组
         */

        /*
        * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        * 2.再读取稀疏数组的后几行数据，赋值给二维数组
        *
        * */
        //1
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2 遍历的是后几行，所以从第二行开始遍历，i=1
        for(int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        
        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组为：");
        for (int[] row :chessArr2) {
            for (int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        /**
         * 读取存档的稀疏数组
         * */
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int row = 0;
        while((line = br.readLine()) != null){
            String temp[] = line.split("\t");
            for(int j=0;j<temp.length;j++){
                sparseArr[row][j] = Integer.parseInt(temp[j]);

            }
            row++;
        }
        br.close();

        /**
         * 显示读取出的稀疏数组
         * */
        System.out.println("读取出的稀疏数组为：");
        for(int i=0;i<sparseArr.length;i++){
            for(int j=0;j<sparseArr[i].length;j++){
                System.out.printf(sparseArr[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
