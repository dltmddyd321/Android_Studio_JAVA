package com.example.contexttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button he;
    View.OnClickListener cl;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        he = (Button) findViewById(R.id.hello);
        registerForContextMenu(et);
        registerForContextMenu(he);
        cl = new View.OnClickListener() {
            @Override public void onClick(View v) {
                switch (v.getId()){
                    case R.id.hello :
                        et.setText("안녕하세요"); break;
                }
            }
        };
        he.setOnClickListener(cl);
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 101, 0, "빨강색 ");
        menu.add(0, 102, 0, "녹색");
        menu.add(0, 103, 0, "파랑색");
        SubMenu sm; sm = menu.addSubMenu("회색계통");
        sm.add(0, 201, 0, "밝은 회색");
        sm.add(0, 202, 0, "그냥 회색");
        sm.add(0, 203, 0, "어두운 회색");
        return super.onCreateOptionsMenu(menu); }
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId() ){
            case 101 :
                et.setBackgroundColor(Color.RED);
                break;
            case 102 :
                et.setBackgroundColor(Color.GREEN);
                break;
            case 103 :
                et.setBackgroundColor(Color.BLUE);
                break;
            case 201 :
                et.setBackgroundColor(Color.LTGRAY);
                break;
            case 202 :
                et.setBackgroundColor(Color.GRAY);
                break;
            case 203 :
                et.setBackgroundColor(Color.DKGRAY);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v == et)
        { menu.add(0, 301, 0, "노랑색");
            menu.add(0, 302, 0, "하늘색");
        } else if ( v == he) {
            menu.add(0, 401, 0, "강감찬");
            menu.add(0, 402, 0, "이순신");
            menu.add(0, 403, 0, "세종대왕");
            SubMenu s;
            s = menu.addSubMenu("여자 인물");
            s.add(0, 501, 0, "유관순");
            s.add(0, 502, 0, "논개");
            s.add(0, 503, 0, "신사임당");
        }
    }
    @Override public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 301 :
                et.setBackgroundColor(Color.YELLOW);
                break;
            case 302 :
                et.setBackgroundColor(Color.CYAN);
                break;
            case 401 :
                et.setText("강감찬");
                break;
            case 402 :
                et.setText("이순신");
                break;
            case 403:
                et.setText("세종대왕");
                break;
            case 501 :
                et.setText("유관순");
                break;
            case 502 :
                et.setText("논개");
                break;
            case 503 :
                et.setText("신사임당");
                break;
        }
        return super.onContextItemSelected(item);
    }
}