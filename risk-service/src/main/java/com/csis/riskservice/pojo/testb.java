package com.csis.riskservice.pojo;

public class testb implements testa{
    @Override
    public int testa(int a, int b) {
        return a+b;
    }

    public void system(){
        System.out.println(testa(2,1));
    }
}
