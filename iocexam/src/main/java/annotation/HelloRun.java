package annotation;

import java.lang.reflect.Method;

public class HelloRun {
    public static void main(String[] args) throws NoSuchMethodException {
        Hello hello = new Hello();

        Method method = hello.getClass().getDeclaredMethod("print");
        if(method.isAnnotationPresent(Count100.class)){
            // 어노테이션 중에 Count100이 붙은 메소드가 있는지 체크
            for(int i = 0; i < 100; i++){ // @Count100 어노테이션이 붙었으면 100번 출력
                hello.print();
            }
        }else{ // @Count100 어노테이션이 없으면 한번 출력
            hello.print(); //
        }
    }
}
