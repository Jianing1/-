package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String[] edu = {"计算机科学与技术", "信息安全", "数据科学与大数据", "人工智能"};
    private EditText editText1;
    private CheckBox checkbox1, checkbox2, checkbox3, checkbox4;
    private String sInfo;
    String gender = "";
    String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        SpinnerAdapter adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.edu, R.layout.activity_main);
        spinner.setAdapter(adapter);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, edu);
        spinner.setAdapter(adapter);
        SpinnerAdapter finalAdapter = adapter;

        Button submit = findViewById(R.id.submit);
        this.editText1 = findViewById(R.id.myInput1);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        this.checkbox1 = findViewById(R.id.mybox1);
        this.checkbox2 = findViewById(R.id.mybox2);
        this.checkbox3 = findViewById(R.id.mybox3);
        this.checkbox4 = findViewById(R.id.mybox4);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.man) gender = "男";
                else gender = "女";
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sInfo = finalAdapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder hobby = new StringBuilder();
                if (checkbox1.isChecked()) {
                    hobby.append("音乐 ");
                }
                if (checkbox2.isChecked()) {
                    hobby.append("阅读 ");
                }
                if (checkbox3.isChecked()) {
                    hobby.append("游泳 ");
                }
                if (checkbox4.isChecked()) {
                    hobby.append("运动 ");
                }
                if (TextUtils.isEmpty(editText1.getText())) {
                    info = "请输入您的姓名";
                } else {
                    info = "你好：" + editText1.getText().toString() + '\n' + "你的性别是：" + gender +
                            '\n' + "你的专业是:" + sInfo + '\n' + "你的个人爱好有:" + hobby + "!";
                }
                Toast.makeText(getApplicationContext(), info, Toast.LENGTH_LONG).show();
            }
        });

    }
}