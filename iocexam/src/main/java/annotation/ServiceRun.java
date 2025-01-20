package annotation;

import java.lang.reflect.Method;

public class ServiceRun {
    public static void main(String[] args) {
        Service service = new Service();
        Method[] declaredMethods = Service.class.getDeclaredMethods();

        for(Method method : declaredMethods){
            if(method.isAnnotationPresent(PrintAnnotation.class)){
                PrintAnnotation printAnnotation = method.getAnnotation(PrintAnnotation.class);
                for(int i = 0; i < printAnnotation.number(); i++){
                    System.out.print(printAnnotation.value());
                }
                System.out.println();
            }
        }
    }
}
