package com.example.secondactivity.model;

public class Person {

    // Attributes
    private int age;
    private String name;

    // Methods
    public static void learnMultiplicationTable(int number) {
        System.out.println("Multiplication Table of: " + number);

        for (int i = 0; i < number; i++) {

        }
    }


    // Getters and Setters

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
