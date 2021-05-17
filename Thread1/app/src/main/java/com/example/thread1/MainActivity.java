package com.example.thread1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText cnt1, cnt2, cnt3;
    Button add, add1, add2, stop, stop1, stop2;
    View.OnClickListener cl;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    boolean b1 = true, b2 = true, b3= true;
    MyHandler mh;

    class MyHandler extends Handler { //스레드를 분할할 핸들러 선언
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what ==1)
                cnt1.setText("count1=" + i1);
            else if(msg.what ==2)
                cnt2.setText("count2=" + i2);
            else if(msg.what ==3)
                cnt3.setText("count3=" + i3);
        }
    }

    class MyThread1 extends Thread { //무한대로 카운트 증가
        @Override
        public void run() {
            super.run(); // Thread 동작을 위한 기본 함수
            while (b1 == true) {
                mh.sendEmptyMessage(1);
                SystemClock.sleep(1300);
                i1 ++;
            }
        }
    }

    class MyThread2 extends Thread {
        @Override
        public void run() {
            super.run(); // Thread 동작을 위한 기본 함수
            while( b2 == true) {
                mh.sendEmptyMessage(2);
                SystemClock.sleep(700);
                i2++;
            }
        }
    }

    class MyThread3 extends Thread {
        @Override
        public void run() {
            super.run(); // Thread 동작을 위한 기본 함수
            while(b3 == true) {
                mh.sendEmptyMessage(3);
                SystemClock.sleep(200);
                i3++;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mh = new MyHandler();

        cnt1 = (EditText)findViewById(R.id.count1);
        cnt2 = (EditText) findViewById(R.id.count2);
        cnt3 = (EditText) findViewById(R.id.count3);
        stop = (Button) findViewById(R.id.stop1);
        stop1 = (Button) findViewById(R.id.stop2);
        stop2 = (Button) findViewById(R.id.stop3);
        add = (Button)findViewById(R.id.add);
        add1 = (Button)findViewById(R.id.add1);
        add2 = (Button) findViewById(R.id.add2);

        //main Thread

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add :
                        b1 = true;
                        i1 = 0;
                        MyThread1 mt1;
                        mt1 = new MyThread1();
                        mt1.start();
                       break;
                    case R.id.add1 :
                        b2 = true;
                        i2 = 0;
                        MyThread2 mt2;
                        mt2 = new MyThread2();
                        mt2.start();
                        break;
                    case R.id.add2 :
                        b3 = true;
                        i3 = 0;
                        MyThread3 mt3;
                        mt3 = new MyThread3();
                        mt3.start();
                        break;
                    case R.id.stop1: //상태 변수를 통한 중지 구현
                        b1 = false;
                        break;
                    case R.id.stop2:
                        b2 = false;
                        break;
                    case  R.id.stop3:
                        b3 = false;
                        break;
                }
            }
        };
        add.setOnClickListener(cl);
        add1.setOnClickListener(cl);
        add2.setOnClickListener(cl);
        stop.setOnClickListener(cl);
        stop1.setOnClickListener(cl);
        stop2.setOnClickListener(cl);
    }
}