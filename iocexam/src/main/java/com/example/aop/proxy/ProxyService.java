package com.example.aop.proxy;

public class ProxyService implements Service{
    private RealService realService;
    @Override
    public void perform() {
        if(realService == null){
            realService = new RealService();
        }

        System.out.println("추가로 해야하는 작업을 수행..");
        realService.perform();

        System.out.println("AOP가 구현한 코드 or 추가로 수행해야하는 작업들을 수행..");
    }
}
