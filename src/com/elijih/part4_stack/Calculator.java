package com.elijih.part4_stack;

public class Calculator {
    public static void main(String[] args) {
        //创建数字栈
        CalculatorStack numStack = new CalculatorStack(10);
        //创建符号栈
        CalculatorStack operatorStack = new CalculatorStack(10);

        //传入表达式
        String line = "70*5+1";
        //创建遍历用的指针
        int index = 0;
        //定义相关的变量
        char ch = ' ';//每次扫描得到的字符
        int num1;//数值1
        int num2;//数值2
        int operator ;//操作符
        int result;
        String keepNum = "";//用于拼接多位数

        //扫描表达式
        while(true){
            ch = line.substring(index,index+1).charAt(0);
            //System.out.println(ch);
            if(!operatorStack.isOperator(ch)){
                //说明此时扫描到的是数值，进行处理
                keepNum+=ch;//处理多位数
                if(index == line.length()-1){
                    //说明此时已经扫描到最后一个位置，数值应该直接进栈并退出
                    numStack.push(Integer.parseInt(keepNum));
                    break;
                }else{
                    //说明没有扫描到最后一个位置，要往后看一个位置，判断是否为多位数
                    //判断下一个字符是不是数字
                    if (operatorStack.isOperator(line.substring(index+1,index+2).charAt(0))){
                        //如果是运算符，那么这个数值就入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //System.out.println("didi"+keepNum);
                        keepNum = "";//keepNum清空
                    }
                }
                //System.out.println(ch-48);
                /**
                 * numStack.push(ch-48);
                 * 只算个位数的时候用这个
                 *
                 * */
            }else{
                //说明此时扫描的是算符，进行处理
                if (operatorStack.isEmpty()){
                    //如果此时符号栈为空，直接进栈
                    //System.out.println("进去的是"+ch);
                    operatorStack.push(ch);
                    //System.out.println(ch+"进站成功");
                }else if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())){
                    //System.out.println("执行到我了"+ch);
                    //此时扫描到的符号位优先级比栈中的符号低，所以先对之前的数据进行运算，再将新得到的数值进入数字栈，继续扫描
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    operator = operatorStack.pop();
                    result = numStack.calculate(operator,num1,num2);
                    //System.out.println("这这这"+result);
                    numStack.push(result);
                    operatorStack.push(ch);
                }else {
                    //说明此时扫描到的符号优先级比栈中的高，直接进栈
                    operatorStack.push(ch);
                }
            }
            index++;
            if (index == line.length()){
                //说明已经扫描完
                break;
            };
        }

        //扫描完毕，进行最终的运算
        while (true){
            //如果符号栈为空，那么表示已经运算到最后的结果了
            if (operatorStack.isEmpty()){
                break;
            }
            //numStack.showStack();
            num1 = numStack.pop();
            //System.out.println("num1="+num1);
            num2 = numStack.pop();
            result = numStack.calculate(operatorStack.pop(),num1,num2);
            numStack.push(result);
        }
        System.out.printf("表达式%s = %d",line,numStack.pop());
    }

}
class CalculatorStack{
    private int maxSize;
    private int []stack;
    private int top = -1;

    //构造器
    public CalculatorStack(int maxSize){
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
        int value = stack[this.top];
        this.top--;
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



    //返回操作符的优先级
    public int priority(int ch){
        if(ch == '*' || ch =='/'){
            return 1;
        }else if (ch == '+' || ch == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    //添加计算方法
    public int calculate(int ch,int num1,int num2){
        int result = 0;
        switch(ch){
            case '*':
                result = num1*num2;
                break;
            case '/':
                result = num2/num1;
                break;
            case '+':
                result = num1+num2;
                break;
            case '-':
                result = num2-num1;
                break;
            default:
                System.out.println("程序出错");
                break;
        }
        return result;
    }
    //看看此时栈顶指针的值，但不把值取出栈
    public int peek(){
        return stack[top];
    }

    //判断是否为运算符
    public boolean isOperator(char ch){
        if(ch != '*' && ch != '/' && ch != '+' && ch != '-'){
            return false;
        }
        return true;
    }




}
