package com.mengdee.manager.entity;

/**
 * @author LiuZX liuzhixiang
 * DATE   2018/8/16
 */

public class Dog {
    private long id;
    private String name;
    private int age;

    public Dog() {
    }

    public Dog(long id, String name, int age) {

        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
