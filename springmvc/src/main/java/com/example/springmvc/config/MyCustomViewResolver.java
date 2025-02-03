package com.example.springmvc.config;

import com.example.springmvc.view.MyCustomView;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

// 사용자 정의 뷰 사용을 위한 설정 클래스
public class MyCustomViewResolver implements ViewResolver, Ordered {
    private int order;

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        //  my-prefix로 시작되는 viewName을 가지면 사용자 정의 뷰를 사용하도록 할 것
        if(viewName.startsWith("my-prefix")){
            return new MyCustomView(); // 사용자 정의 뷰 사용
        }
        return null; // 다음의 뷰 리졸버가 처리함
    }
}
