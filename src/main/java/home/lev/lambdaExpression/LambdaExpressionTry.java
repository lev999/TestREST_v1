package home.lev.lambdaExpression;

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
        Mathoperations sub = (a,b) ->a-b;
        System.out.println(lambdaExpressionTry.doOperation(10,12,sum));
        System.out.println(lambdaExpressionTry.doOperation(10,12,sub));
    }
}
