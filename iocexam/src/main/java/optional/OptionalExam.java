package optional;

public class OptionalExam {
    public static void main(String[] args) {
        String msg = null;
        // System.out.println(msg.charAt(0)); // NullPointerException 발생

        if (msg != null) { // Null이 아닐때만 처리할 수 있게 만듦
            System.out.println(msg.charAt(0));
        }
    }
}
