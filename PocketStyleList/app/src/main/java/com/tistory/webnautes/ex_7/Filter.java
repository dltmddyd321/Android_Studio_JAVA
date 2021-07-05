package com.tistory.webnautes.ex_7;

import androidx.annotation.CheckResult;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Filter extends AppCompatActivity {

    CheckBox chk1, chk2, chk3, chk4, chk5, chk6, chk7;
    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        setTitle("선택 필터");

        chk1 = (CheckBox)findViewById(R.id.checkBox);
        chk2 = (CheckBox)findViewById(R.id.checkBox2);
        chk3 = (CheckBox)findViewById(R.id.checkBox3);
        chk4 = (CheckBox)findViewById(R.id.checkBox4);
        chk5 = (CheckBox)findViewById(R.id.checkBox5);
        chk6 = (CheckBox)findViewById(R.id.checkBox6);
        chk7 = (CheckBox)findViewById(R.id.checkBox7);
        btn1 = (Button)findViewById(R.id.classification);
        btn2 = (Button)findViewById(R.id.man);
        btn3 = (Button)findViewById(R.id.girl);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"이미 남성 검색창입니다.",Toast.LENGTH_SHORT).show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Girlhow.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"여성 검색창입니다.",Toast.LENGTH_SHORT).show();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk2.isChecked() == true && chk6.isChecked() == true){
                    Intent intent = new Intent(getApplicationContext(),Shops.class);
                    startActivity(intent);
                } else if (chk2.isChecked() == true && chk7.isChecked() == true){
                    Intent intent2 = new Intent(getApplicationContext(),Street.class);
                    startActivity(intent2);
                } else
                    Toast.makeText(getApplicationContext(),"다시 선택하십시오.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
