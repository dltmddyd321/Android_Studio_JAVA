package com.example.multi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button he;
    EditText ed;
    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.text);
        he = (Button)findViewById(R.id.hello);
        ed = (EditText)findViewById(R.id.edit);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed.setText(R.string.mytext);
                tv.setText(R.string.myName);
            }
        };
        he.setOnClickListener(cl);
    }
}