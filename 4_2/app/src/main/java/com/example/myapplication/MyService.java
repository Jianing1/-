package com.example.myapplication;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class MyService extends Service {
    //定义通知管理器
    private NotificationManager manager;
    private Notification notification1;
    private Notification notification2;
    private Intent Myintent;
    //定义通知切换的原始速度
    private Integer speed = 1000;

    public MyService() {
    }

    //创建MyBinder内部类
    public class MyBinder extends Binder {
        public MyService getService() { //创建获取Service的方法
            return MyService.this;//返回当前的Service类
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Myintent = intent;
        sendNotification();
        Toast.makeText(this, "服务启动成功!", Toast.LENGTH_SHORT).show();
        return new MyBinder();//返回MyBinder Service对象
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "服务解绑成功!", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    //自定义方法，用于显示通知
    public void sendNotification() {
        //从系统服务中获取通知管理器
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //判断Android版本是否大于8.0，引入通知渠道
        NotificationChannel channel = new NotificationChannel("wyx", "测试通知"
                , NotificationManager.IMPORTANCE_LOW);
        //创建通知渠道
        manager.createNotificationChannel(channel);
        //创建通知意图
        Intent intent1 = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, Myintent, 0);
        notification1 = new NotificationCompat.Builder(this, "wyx")
                .setContentTitle("通知")
                .setSmallIcon(R.drawable.left)
                .setContentText("服务正在运行中")
                .build();
        notification2 = new NotificationCompat.Builder(this, "wyx")
                .setContentTitle("通知")
                .setSmallIcon(R.drawable.right)
                .setContentText("服务正在运行中")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (isRunning("com.example.myapplication.MyService")) {//如果Service正在运行
                    ++i;
                    if (i % 2 == 0) {
                        manager.notify(1, notification1);
                    } else {
                        manager.notify(1, notification2);
                    }
                    try {
                        //休眠
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!isRunning("com.example.myapplication.MyService")) {
                    manager.cancel(1);
                }
            }
        }).start();//开启线程
    }

    //加速
    public void speedUp() {
        speed = speed / 2;//休眠的时间减半
    }

    //减速
    public void speedDown() {
        speed = speed * 2;//休眠的时间加倍
    }

    @Override
    public void onDestroy() {//销毁Service
        super.onDestroy();
    }

    //判断service是否正在运行
    public boolean isRunning(String ServiceName) {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        //获取正在运行的Service
        ArrayList<ActivityManager.RunningServiceInfo> runningServiceInfos = (ArrayList<ActivityManager.RunningServiceInfo>)
                activityManager.getRunningServices(1000);

        for (int i = 0; i < runningServiceInfos.size(); i++) {
            Log.i("服务运行1：", "" + runningServiceInfos.get(i).service.getClassName().toString());
            if (runningServiceInfos.get(i).service.getClassName().toString().equals(ServiceName)) {
                return true;
            }
        }
        return false;
    }
}