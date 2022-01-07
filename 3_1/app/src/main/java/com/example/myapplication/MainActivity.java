package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String[] name = new String[]{"1802090181-陈家宁", "张三", "李四", "王五"};
    private ArrayList<String> nam = new ArrayList<String>();
    private MyAdapter myAdapter;
    private ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylistview = findViewById(R.id.listview);
        //初始化适配器
        for (int i = 0; i < name.length; i++) {
            nam.add(name[i]);
        }
        myAdapter = new MyAdapter(nam);
        //给ListView设置Adapter
        mylistview.setAdapter(myAdapter);
        //添加点击事件，点击跳转到新的activity
        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView mytextview = view.findViewById(R.id.name);
                String name = mytextview.getText().toString();
                //创建意图对象
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                //获取传递数据的值
                intent.putExtra("name", name);
                //激活意图
                startActivityForResult(intent, 1);
            }
        });
    }


    @Override
    //处理返回数据
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            //获取返回数据
            String deleteName = bundle.getString("deleteName");
            nam.remove(deleteName);
            System.out.println(nam);
            /*删除数据后重新加载适配器*/
            myAdapter = new MyAdapter(nam);
            //给ListView设置Adapter
            mylistview.setAdapter(myAdapter);

        }
    }


    /*ListView适配器*/
    public class MyAdapter extends BaseAdapter {
        private ArrayList<String> name;

        public MyAdapter(ArrayList<String> name) {
            this.name = name;
        }

        @Override
        public int getCount() {
            return name.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = View.inflate(MainActivity.this, R.layout.list_item, null);
                TextView myText = (TextView) view.findViewById(R.id.name);
                myText.setText(name.get(position).toString());
            } else {
                view = convertView;
            }
            return view;
        }
    }
}