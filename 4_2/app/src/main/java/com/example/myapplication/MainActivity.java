package com.example.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button bind;
    private Button unbind;
    private Button speedup;
    private Button speeddown;
    //声明Service类的对象
    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bind = findViewById(R.id.bind);
        unbind = findViewById(R.id.unbind);
        speedup = findViewById(R.id.speedup);
        speeddown = findViewById(R.id.speeddown);
    }

    //MainActivity与Myservice之间的桥梁
    private ServiceConnection connect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((MyService.MyBinder) service).getService();//获取后台Service
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /*绑定服务*/
    public void BindService(View view) {
        bindService(new Intent(this, MyService.class), connect, Context.BIND_AUTO_CREATE);
    }

    /*解绑服务*/
    public void UnBindService(View view) {
        if (myService != null) {
            unbindService(connect);
            myService = null;
        } else {
            Toast.makeText(this, "还未绑定服务!", Toast.LENGTH_SHORT).show();
        }
    }

    /*加速*/
    public void speedUp(View view) {
        if (myService == null) {
            Toast.makeText(this, "未绑定服务!", Toast.LENGTH_SHORT).show();
        } else {
            myService.speedUp();
        }
    }

    /*减速*/
    public void speedDown(View view) {
        if (myService == null) {
            Toast.makeText(this, "未绑定服务!", Toast.LENGTH_SHORT).show();
        } else {
            myService.speedDown();
        }
    }
}