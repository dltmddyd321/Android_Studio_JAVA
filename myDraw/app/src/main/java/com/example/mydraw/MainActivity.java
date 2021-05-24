package com.example.mydraw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyView mv; //View
    int color = Color.BLACK;
    int type = 1; //1:직선 2: 사각형 3:원 4:타원

    class MyView extends View {
        Paint p;
        MyView(Context c) {
            super(c);
            p = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            p.setStyle(Paint.Style.FILL);
            p.setStrokeWidth(10);
            p.setColor(color);

            if(type == 1)
                canvas.drawLine(300,40,500,370,p);
            else if (type ==2)
                canvas.drawRect(20,300,150,520,p);
            else if(type==3)
                canvas.drawCircle(200,400,100,p);
            else if(type ==4)
                canvas.drawOval(20,40,300,370,p);

     /*       canvas.drawLine(80,480,130,530,p);
            canvas.drawLine(330,530,380,480,p);
            canvas.drawLine(130,530,330,530,p);
            p.setColor(Color.BLACK);
            canvas.drawLine(100,100,200,150,p);
            canvas.drawLine(380,100,260,150,p);

            p.setColor(Color.YELLOW);
            canvas.drawRect(220,440,260,300,p);
            p.setColor(Color.GREEN);
            canvas.drawCircle(130,200,50,p);
            p.setColor(Color.MAGENTA);
            canvas.drawCircle(330,200,50,p);
            p.setColor(Color.LTGRAY);
            canvas.drawOval(10,10,470,700,p);
            */
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mv = new MyView(this);
        setContentView(mv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,101,0,"빨강색");
        menu.add(0,102,0,"파랑색");
        menu.add(0,103,0,"녹색");
        SubMenu sm;
        sm = menu.addSubMenu("기타색");
        sm.add(0,201,0,"노랑색");
        sm.add(0,202,0,"하늘색");
        sm.add(0,203,0,"분홍색");
        sm = menu.addSubMenu("선호하는 색");
        sm.add(0,301,0,"민트색");
        sm.add(0,302,0,"보라색");
        sm.add(0,303,0,"갈색");
        sm = menu.addSubMenu("도형 선택");
        sm.add(0,401,0,"직선");
        sm.add(0,402,0,"사각형");
        sm.add(0,403,0,"원");
        sm.add(0,404,0,"타원");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 101:
                color = Color.RED;
                mv.invalidate();
                break;
            case 102:
                color = Color.BLUE;
                mv.invalidate();
                break;
            case 103:
                color = Color.GREEN;
                mv.invalidate();
                break;
            case 201:
                color = Color.YELLOW;
                mv.invalidate();
                break;
            case 202:
                color = Color.CYAN;
                mv.invalidate();
                break;
            case 203:
                color = Color.MAGENTA;
                mv.invalidate();
                break;
            case 301:
                color = Color.rgb(170,240,209);
                mv.invalidate();
                break;
            case 302:
                color = Color.rgb(139,0,255);
                mv.invalidate();
                break;
            case 303:
                color = Color.rgb(150,75,0);
                mv.invalidate();
                break;
            case 401:
                type =1;
                mv.invalidate();
                break;
            case 402:
                type =2;
                mv.invalidate();
                break;
            case 403:
                type =3;
                mv.invalidate();
                break;
            case 404:
                type =4;
                mv.invalidate();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}