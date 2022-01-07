package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class MainActivity2 extends MainActivity {
    private TextView message;
    private Button ok, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        message = findViewById(R.id.message);
        ok = findViewById(R.id.ok);
        cancel = findViewById(R.id.cancel);
        //获取意图对象
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        //获取传递的值
        String name = intent.getStringExtra("name");
        String tmp = "删除" + name + "的记录吗？";
        //textview中显示信息
        message.setText(tmp);
        //点击确定监听事件
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder Dialog = new AlertDialog.Builder(MainActivity2.this)
                        .setTitle("提示")
                        .setMessage("确认要删除该条记录吗?")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                bundle.putString("deleteName", name);
                                intent.putExtras(bundle);
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity2.this, "你没有删除该记录", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setCancelable(false);
                Dialog.create().show();
            }
        });
        //点击取消监听事件
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "你没有删除该记录", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}