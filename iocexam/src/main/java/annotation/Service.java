package annotation;

public class Service {
    @PrintAnnotation
    public void methodA(){
        System.out.println("methodA 실행!");
    }

    @PrintAnnotation("BBB")
    public void methodB(){
        System.out.println("methodB 실행!");
    }

    @PrintAnnotation(number = 10)
    public void methodC(){
        System.out.println("methodC 실행!");
    }

    @PrintAnnotation(value = "#", number = 20)
    public void methodD(){
        System.out.println("methodD 실행!");
    }
}
