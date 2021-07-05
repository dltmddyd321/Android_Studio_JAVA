package com.tistory.webnautes.ex_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Shopmall extends AppCompatActivity {
    Button btn2,btn3;
    ImageButton shbtn1,shbtn2, shbtn3, shbtn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopmall);
        setTitle("쇼핑몰");

        shbtn1 = (ImageButton) findViewById(R.id.shop1);
        shbtn2 = (ImageButton) findViewById(R.id.shop2);
        shbtn3 = (ImageButton) findViewById(R.id.shop3);
        shbtn4 = (ImageButton) findViewById(R.id.shop4);

        shbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.musinsa.com/"));
                startActivity(intent);
            }
        });

        shbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.asclo.com/index.html"));
                startActivity(intent);
            }
        });

        shbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gopeople.co.kr/"));
                startActivity(intent);
            }
        });

        shbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.musinsa.com/"));
                startActivity(intent);
            }
        });

        btn2 = (Button)findViewById(R.id.man);
        btn3 = (Button)findViewById(R.id.girl);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"이미 남성 쇼핑몰입니다.",Toast.LENGTH_SHORT).show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Girlmall.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"여성 쇼핑몰입니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
