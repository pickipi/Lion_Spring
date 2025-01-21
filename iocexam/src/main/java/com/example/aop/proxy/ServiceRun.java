package com.example.aop.proxy;

public class ServiceRun {
    public static void main(String[] args) {
        Service service = new ProxyService();

        service.perform();
    }
}
