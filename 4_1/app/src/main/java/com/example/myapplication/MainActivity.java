package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //启动服务
    public void startService(View view) {
        startService(new Intent(this, MyService.class));
        System.out.println("启动了服务");
    }

    //停止服务
    public void stopService(View view) {
        stopService(new Intent(this, MyService.class));
        System.out.println("停止了服务");
    }
}