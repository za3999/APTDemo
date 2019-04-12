package com.example.za399.aptdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.example.process_lib.annotaion.BindView;
import com.example.process_lib.annotaion.TestAnnotation;
import com.example.process_lib.util.AptTool;

@TestAnnotation
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_test)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AptTool.bind(this);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AptTool.test();
            }
        });
    }
}
