package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
    //定义通知管理器
    private NotificationManager manager;
    private Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //从系统服务中获取通知管理器
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //判断Android版本是否大于8.0，引入通知渠道
        NotificationChannel channel = new NotificationChannel("111", "测试通知"
                , NotificationManager.IMPORTANCE_HIGH);
        //创建通知渠道
        manager.createNotificationChannel(channel);

        Intent intent = new Intent(this, MainActivity2.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this
                , 0, intent, 0);

        notification = new NotificationCompat.Builder(this, "111")
                .setContentTitle("通知")
                .setContentText("1802090181-陈家宁")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setColor(Color.parseColor("#ff0000"))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)    //设置点击后自动消失，必须和setContentIntent一起使用，否则无效
                .build(); //channelid是渠道id
    }

    /*发送通知*/
    public void sendNotification(View view) {
        manager.notify(1, notification);
    }

    /*关闭通知*/
    public void cancelNotification(View view) {
        manager.cancel(1);
    }
}