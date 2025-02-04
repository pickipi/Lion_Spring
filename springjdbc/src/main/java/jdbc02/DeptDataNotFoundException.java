package jdbc02;

public class DeptDataNotFoundException extends RuntimeException{
    public DeptDataNotFoundException(String message){
        super(message);
    }
}
