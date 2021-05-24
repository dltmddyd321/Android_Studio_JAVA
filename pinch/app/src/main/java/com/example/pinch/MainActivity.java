package com.example.pinch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    String s;
    Matrix m;
    ImageView iv;

    float d1, d2, r;
    float getDistance(MotionEvent me){
        float dx = me.getX(1) - me.getX(0);
        float dy = me.getY(1) - me.getY(0);
        return (float) Math.sqrt(dx*dx + dy*dy); //Math.sqrt가 double 타입이라 float로 캐스팅
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { //Up, Down, Move
        int act;
        int index;
        act = event.getAction() & 0xff; // 하위 8비트
        index = (event.getAction() & 0xff00) >> 8; // 상위 8비트를 8자리 시프트 연산
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN : //입력키가 push
                s = "Down index=" + index + "x=" + event.getX() + "y=" + event.getY();
                //X 좌표값과  Y 좌표값을 가져온다.
                tv1.setText(s);
                break;
            case MotionEvent.ACTION_MOVE: //드래그 -> 피타고라스 정리에 이용
                s = "Move index=" + index + "x=" + event.getX() + "y=" + event.getY();
                tv1.setText(s);
                break;
            case MotionEvent.ACTION_UP: //입력키가 no push
                s = "Up index=" + index + "x=" + event.getX() + "y=" + event.getY();
                tv1.setText(s);
                break;
            case MotionEvent.ACTION_POINTER_DOWN: //좌클릭 누른 상태로 우클릭
                s = "Pointer Down index=" + index + "x=" + event.getX() + "y=" + event.getY();
                tv1.setText(s);
                break;
            case MotionEvent.ACTION_POINTER_UP : //좌클릭 우클릭 누른 상태에서 둘 중 하나만 up
                s = "Pointer Up index=" + index + "x=" + event.getX() + "y=" + event.getY();
                tv1.setText(s);
                break;
        }
        if(event.getPointerCount() == 2) {
            switch (act) {
                case MotionEvent.ACTION_POINTER_DOWN :
                    d1 = getDistance(event);
                    s = "Pointer Down d1=" + d1;
                    tv1.setText(s);
                    break;
                case MotionEvent.ACTION_MOVE:
                    d2 = getDistance(event);
                    s = "Move d1=" + d1 + "d2=" + d2;
                    m = new Matrix();
                    r = d2 / d1;
                    m.postScale(r,r);
                    iv.setImageMatrix(m);
                    tv1.setText(s);
                    break;
                case MotionEvent.ACTION_POINTER_UP :
                    d2 = getDistance(event);
                    s = "Pointer Up d1=" + d1 + "d2=" + d2;
                    m = new Matrix();
                    r = d2 / d1;
                    m.postScale(r,r);
                    iv.setImageMatrix(m);
                    tv1.setText(s);
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.txt1);
        iv = (ImageView)findViewById(R.id.img);
    }
}