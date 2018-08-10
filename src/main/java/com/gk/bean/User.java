package com.gk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by akun on 2017/5/26.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer age;
    private String name;
    private String gender;

    abstract class Animal {
        abstract void play();
    }

    class Dog extends Animal{
        void play(){
            System.out.println("play with human");
        }
    }

    public  void main(String[] args){
        Animal d = new Dog();
        d.play();

        A a = new A();
    }


    static class A{}

    private class B{}

    public class CC {}

    protected class Cc{}

    abstract class D{}

    final class E{}



}
