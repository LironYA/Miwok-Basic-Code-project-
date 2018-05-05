package com.example.android.astatic;


public class Person {
    public static int instanceCount;
    public int localCount;
    public Person(){
        instanceCount++;
        localCount++;
    }
}
