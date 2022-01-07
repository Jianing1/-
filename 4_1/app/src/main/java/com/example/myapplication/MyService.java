package com.example.myapplication;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class MyService extends Service {
    //定义通知管理器
    private NotificationManager manager;
    private Notification notification1;
    private Notification notification2;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //从系统服务中获取通知管理器
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //判断Android版本是否大于8.0，引入通知渠道
        NotificationChannel channel = new NotificationChannel("wyx", "测试通知"
                , NotificationManager.IMPORTANCE_LOW);
        //创建通知渠道
        manager.createNotificationChannel(channel);
        //创建通知意图
        Intent intent1 = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        notification1 = new NotificationCompat.Builder(this, "wyx")
                .setContentTitle("通知")
                .setSmallIcon(R.drawable.left)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                .setContentText("服务正在运行中")
                .build();
        notification2 = new NotificationCompat.Builder(this, "wyx")
                .setContentTitle("通知")
                .setSmallIcon(R.drawable.right)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
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
                        //休眠1秒
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!isRunning("com.example.myapplication.MyService")) {
                    manager.cancel(1);
                }
            }
        }).start();//开启线程
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
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