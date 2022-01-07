package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String[] listdata = {"移动应用开发技术", "计算机组成原理", "数据结构", "1802090181—陈家宁", "操作系统", "逻辑设计"};
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview = super.findViewById(R.id.myListView);
        this.textView = super.findViewById(R.id.mytext);
        listview.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, this.listdata));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(listdata[position]);
            }
        });
    }
}