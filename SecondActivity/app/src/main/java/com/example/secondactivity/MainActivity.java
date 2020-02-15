package com.example.secondactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.secondactivity.model.Person;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person professor = new Person();
        professor.setAge(52);
        professor.setName("Teacher");

        Person student = new Person();
        student.setAge(30);
        student.setName("Students");

        Log.i("Object", "Professor`s name: " + professor.getName());
        Log.i("Object", "Professor`s age: " + professor.getAge());
        Log.i("Object", "Student`s name: " + student.getName());
        Log.i("Object", "Student`s age: " + student.getAge());
    }
}
