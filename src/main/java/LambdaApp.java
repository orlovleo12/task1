package main.java;

import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaApp {

    public static void main(String[] args) {

        Operationable operation;
        operation = (x, y) -> x + y;

        int result = operation.calculate(10, 20);
        System.out.println(result); //30
    }
    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {

        return t -> {
            if (condition.test(t)) return ifTrue.apply(t);
            else return ifFalse.apply(t);
        }; // your implementation here

    }
}
interface Operationable{
    int calculate(int x, int y);
}