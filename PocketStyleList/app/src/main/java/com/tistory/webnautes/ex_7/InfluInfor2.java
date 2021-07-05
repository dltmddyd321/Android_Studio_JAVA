package com.tistory.webnautes.ex_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class InfluInfor2 extends AppCompatActivity {

    ImageButton btnj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_influ_infor2);
        setTitle("인플루언서");

        btnj = (ImageButton)findViewById(R.id.btnj);
        btnj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCcSepD5GRKpskptBW2TSKGg"));
                startActivity(intent);
            }
        });
    }
}
