package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    private final int[] images = new int[]{R.drawable.test,
            R.drawable.test, R.drawable.test, R.drawable.test, R.drawable.test};
    private final String[] name = new String[]{"姓名", "性别", "年龄", "居住地", "邮箱"};
    private final String[] des = new String[]{"陈家宁", "男", "20", "开封", "chenjianing087@Gmail.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //找到listview组件
        ListView list = findViewById(R.id.myListView2);
        //创建一个含有hashmap的列表集合
        List<HashMap<String, Object>> listitems = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            HashMap<String, Object> listitem = new HashMap<>();
            listitem.put("header", images[i]);
            listitem.put("name", name[i]);
            listitem.put("des", des[i]);
            listitems.add(listitem);
        }
        //创建适配器
        MySimpleAdapter simpleAdapter = new MySimpleAdapter(
                this,
                listitems,
                R.layout.myitem,
                new String[]{"name", "header", "des"},//key
                new int[]{R.id.name, R.id.header, R.id.des});//id


        list.setAdapter(simpleAdapter);

        list.setSelector(R.color.purple_200);

    }
}