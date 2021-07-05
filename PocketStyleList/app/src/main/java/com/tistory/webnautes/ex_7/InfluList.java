package com.tistory.webnautes.ex_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class InfluList extends AppCompatActivity {

    ImageButton ibtn1, ibtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_influ_list);
        setTitle("인플루언서");

        ibtn1 = (ImageButton)findViewById(R.id.ibtn1);
        ibtn2 = (ImageButton)findViewById(R.id.ibtn2);

        ibtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InfluInfor.class);
                startActivity(intent);
            }
        });

        ibtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InfluInfor2.class);
                startActivity(intent);
            }
        });
    }
}
