package com.example.executorservicemodel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    Handler handler;

    boolean isTrue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(Looper.getMainLooper());

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        clickBtnThread(button);
    }

    private void clickBtnThread(Button button) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        //doInBackground
                        Log.d("doInBackground", "Now!");

                        //1번 방법
                        runOnUiThread(new Runnable() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run() {
                                //postExecute
                                Log.d("postExecute", "Now!");
                                isTrue = true;

                                checkBoolean();
                            }
                        });

                        //2번 방법
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("postExecute", "Now!");
                                isTrue = true;

                                checkBoolean();
                            }
                        });
                    }
                });
                Log.d("Normal", "Now!");
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkBoolean() {
        if(isTrue) {
            textView.setText("It's True!!");
        }
    }
}