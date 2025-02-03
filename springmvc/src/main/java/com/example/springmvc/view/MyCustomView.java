package com.example.springmvc.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.View;

import java.io.PrintWriter;
import java.util.Map;

public class MyCustomView implements View {
    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());

        response.setCharacterEncoding("UTF-8"); // 명시적으로 UTF-8 설정

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><meta charset='UTF-8'></head>"); // HTML 메타 태그 추가
        out.println("<body>");
        out.println("<h1>사용자 정의 뷰!!</h1>");
        out.println("<p>나는 사용자 정의 뷰입니다.^^</p>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}