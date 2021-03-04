package com.elijih.part4_stack;
/**
 * 逆波兰计算机：
 * 实现用户输入一个中缀表达式，计算机将他转化为后缀表达式，并进行输出，支持小数，多位数，括号，并且可以过滤空格
 *
 * 目前实现了支持单个数值，尚未实现多位数，小数，括号，以及过滤
 * */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {

        /**
         * 完成一个中缀表达式转后缀表达式的功能
         * 思路：
         * 1.获取一个中缀表达式，例如1+（（2+3）*4）-5转化为 1 2 3 + 4 * + 5 -
         * 2.因为直接对String操作不方便，所以先将中缀表达式的每一个字符存放在集合里
         * 3.创建一个方法，用于把中缀表达式存放进集合
         * 4.创建一个方法，实现将中缀表达式对应的List转换成后缀表达式对应的List
         * 5.转换的方法需要创建两个栈，一个用来存放运算符，一个用来存放中间结果，但由于存放中间结果的栈全程没有
         * pop操作，而且最后还需要逆序输出，所以没有必要创建为栈，不如创建为集合
         * 6.创建一个类Operation，用来比较运算符的优先级
         *
         * */

        String infixExpression = "1+((2+3)*4)-5";
        List<String> infixList = toInfixExpressionList(infixExpression);
        List<String> suffixList = toSufixExpressionList(infixList);
        System.out.println("给出的中缀表达式为："+infixExpression+"\n转换后的后缀表达式的List为："+suffixList);
        System.out.printf("计算结果=%d",calculate(suffixList));




        //先定义一个逆波兰表达式，为了方便，该表达式的数值和字符用空格隔开
        /*String suffixExpression = "3 4 + 5 * 6 -";*/
        /**
         * 思路：
         * 1.先将逆波兰表达式放进ArrayList中,这是为了省去设立指针index来一次次遍历表达式的麻烦，这样就可以直接使用集合的索引
         * 2.将ArrayList传递给一个方法，遍历ArrayList，配合栈，完成计算
         * */

        /*List<String> List = getListString(suffixExpression);
        System.out.println("List="+List);
        int result = calculate(List);
        System.out.printf("计算的结果是%d",result);
*/
    }

    //将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s){
        int index=0;//创建一个指针，用来遍历字符串
        List<String> list = new ArrayList<>();//创建一个集合，用来存储结果
        while(index<s.length()){
            list.add(s.substring(index,index+1));
            index++;
        }
        return list;
    }

    //把中缀表达式对应的List转换成后缀表达式对应的list
    //1+((2+3)*4)-5 -> 1 2 3 + 4 * + 5 -
    public static List<String> toSufixExpressionList(List<String> infixList){
        //创建一个栈，用于存放运算符，创建一个集合，用来存放结果
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        //遍历中缀表达式的集合
        for(String item:infixList){
            if(item.matches("\\d+")){
                //如果是操作数，直接加入s2
                s2.add(item);
            }else if (isOperator(item.charAt(0))){
                //如果是运算符，处理
                while (true){
                    //如果s1为空，或s1栈顶运算符为“（”，则直接将此运算符入栈
                    if(s1.empty() || s1.peek().equals("(")){
                        s1.push(item);
                        break;
                    }else if(priority(item.charAt(0)) > priority(s1.peek().charAt(0))){
                        //如果优先级比栈顶优先级的高，也将运算符入栈
                        s1.push(item);
                        break;
                    }else{
                        //否则，将s1栈顶的运算符弹出并加入到s2，再次与新的栈顶运算符作比较
                        s2.add(s1.pop());
                    }

                }

            }else if (item.equals("(") || item.equals(")")){
                //遇到括号时，处理
                //如果是左括号，直接压入s1
                if(item.equals("(")){
                    s1.push(item);
                }else if (item.equals(")")){
                    //如果是右括号，则依次弹出s1中的运算符，并加入s2，直到遇到左括号为止，此时丢弃这一对括号
                    while (!s1.peek().equals("(")){
                        s2.add(s1.pop());
                    }
                    s1.pop();
                }
            }
        }

        //最后把s1中剩余的运算符依次弹栈加入到s2中
        while (!s1.empty()){
            s2.add(s1.pop());
        }
        //返回结果
        return s2;
    }

    //判断是否为运算符的方法
    public static boolean isOperator(char ch){
        return ch == '*' || ch == '/' || ch == '+' || ch == '-';
    }

    //比较运算符优先级的算法
    public static int priority(int ch){
        if(ch == '*' || ch =='/'){
            return 1;
        }else if (ch == '+' || ch == '-'){
            return 0;
        }else {
            throw new RuntimeException("操作符有问题啊小伙子");
        }
    }


    //依次把逆波兰表达式的数据放进ArrayList中
    public  static List<String> getListString(String suffixExpression){//没有这个static的话，方法只能用类对象来调用
        //将suffixExpression分隔
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String element:split) {
            list.add(element);
        }
        return list;
    }


    /**
     * 完成对逆波兰表达式的运算
     * 1.从左往右扫描，将3和4压入栈
     * 2.遇到+运算符，取出4和3，运算得到7，将7压入栈
     * 3.将5入栈
     * 4.遇到运算符*，5和7出栈，运算得到35，入栈
     * 5.将6入栈
     * 6.遇到运算符-，将6和35出栈，计算出35-6，得结果29
     */
    public static int calculate(List<String> list){
        //创建一个栈
        Stack<String> stack = new Stack<String>();
        //遍历集合，进行操作
        for (String item:list) {
            if(item.matches("\\d+")){
                //匹配的是多位数,这道题不用考虑拼接字符串，是数字直接入栈即可
                stack.push(item);
                continue;
            }else{
                //匹配到了运算符，此时需要pop出两个数，运算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                if(item.equals("*")){
                    result = num1*num2;
                }else if (item.equals("/")){
                    result = num1/num2;
                }else if (item.equals("+")){
                    result = num1+num2;
                }else if (item.equals("-")){
                    result = num1-num2;
                }else{
                    throw new RuntimeException("运算符有误，程序出错");
                }
                //把结果入栈
                stack.push(Integer.toString(result));

                /* int类型转换为String类型的三种方法：
                *  " " +result
                * Integer.toString(result)
                * String.valueOf(result)
                * */
            }
        }
        //最后返回运算结果
        return Integer.parseInt(stack.pop());
    }

}
