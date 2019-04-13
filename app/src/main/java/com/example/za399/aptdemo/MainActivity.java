package com.example.za399.aptdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.process_lib.annotaion.BindView;
import com.example.process_lib.annotaion.TestAnnotation;
import com.example.process_tool.AptTool;

@TestAnnotation
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_test)
    TextView mTextView;
    @BindView(R.id.btn_test)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AptTool.bind(this);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AptTool.showToast(getApplicationContext(), "我是文本");
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AptTool.showToast(getApplicationContext(), "我是测试按钮");
            }
        });
    }
}
