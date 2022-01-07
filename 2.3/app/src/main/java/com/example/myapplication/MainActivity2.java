package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private Button cirle;
    private Button line;
    ProgressDialog prodialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        cirle = findViewById(R.id.circle);
        line = findViewById(R.id.line);
        cirle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prodialog = new ProgressDialog(MainActivity2.this);
                prodialog.setTitle("提示");
                prodialog.setMessage("这是一个圆形进度条对话框");
                prodialog.setIndeterminate(false);
                prodialog.setCancelable(true);
                prodialog.show();
            }
        });
        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] count = {0};
                prodialog = new ProgressDialog(MainActivity2.this);
                prodialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                prodialog.setTitle("提示");
                prodialog.setMessage("这是一个长方形进度条对话框");
                prodialog.setIndeterminate(false);
                prodialog.setCancelable(true);
                prodialog.show();
                new Thread() {
                    @Override
                    public void run() {
                        while (count[0] <= 100) {
                            prodialog.setProgress(count[0]++);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        prodialog.cancel();
                    }
                }.start();
            }
        });
    }
}