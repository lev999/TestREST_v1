package home.lev.lambdaExpression;

import java.util.function.Consumer;

//https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax
public class LambdaExpressionTry {

    interface Mathoperations{
        int operation(int a,int b);
    }
    public int doOperation(int a,int b,Mathoperations mathoperations){
        return mathoperations.operation(a,b);
    }

    public static void main(String[] args) {
        LambdaExpressionTry lambdaExpressionTry = new LambdaExpressionTry();
        Mathoperations sum = (a,b) -> a+b;
        Mathoperations sub = (a,b) -> a-b;
        System.out.println(lambdaExpressionTry.doOperation(10,12,sum));
        System.out.println(lambdaExpressionTry.doOperation(10,12,sub));
        lambdaExpressionTry.getName("str1-","str2-");

    }
    public void getName(final String  name1,String name2){
        name2="sdf";
        Consumer<String> out= (someName)-> {
            System.out.println(someName + 1);
            System.out.println(someName + 2+"-"+name1);
        };
        out.accept(name2);
    }
}
