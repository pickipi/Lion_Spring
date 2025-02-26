package com.example.restexam.builderexam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Builder
public class Pizza {
    private String size;
    private boolean cheese;
    private boolean onion;
    private boolean potato;
}

//public class Pizza{
//    private String size;
//    private boolean cheese;
//    private boolean onion;
//    private boolean potato;
//
//    public static class Builder{
//        private String size;
//        private boolean cheese = true;
//        private boolean onion = false;
//        private boolean potato = true;
//
//        // build()를 쓰기위한 기본 생성자
//        public Builder(){};
//
//        // 만약 size는 기본적으로 갖고싶다면 이처럼 구현 가능
//        //public Builder(String size){};
//
//        // 메소드 체이닝
//        public Builder size(String size){
//            this.size = size;
//            return this;
//        }
//
//        public Builder cheese(boolean cheese){
//            this.cheese = cheese;
//            return this;
//        }
//
//        public Builder onion(boolean onion){
//            this.onion = onion;
//            return this;
//        }
//
//        public Builder potato(boolean potato){
//            this.potato = potato;
//            return this;
//        }
//
//        public Pizza build(){
//            return new Pizza(this);
//        }
//
//    }
//    private Pizza(Builder builder){
//        this.size = builder.size;
//        this.cheese = builder.cheese;
//        this.onion = builder.onion;
//        this.potato = builder.potato;
//    }
//
//    public static Builder builder(){
//        return new Builder();
//    }
//}
