package com.example.download;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    EditText addr;
    Button go;
    WebView wv;
    View.OnClickListener cl;
    String page = "";
    String ustring;
    MyHandler mh;

    class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what ==1 ){
                tv.setText(page);
            }
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            HttpURLConnection con = null; //httpURL과의 접속을 위한 변수
            try{
                URL url = new URL(ustring); //문자열 변수를 통해 url 값을 저장한다.
                con = (HttpURLConnection) url.openConnection(); //url 접속을 위한 네트워크 연결 개통
                BufferedInputStream buf;
                buf = new BufferedInputStream((con.getInputStream()));
                BufferedReader reader;
                reader = new BufferedReader(new InputStreamReader(buf));
                String line = null; //받아온 데이터값을 한 줄씩 읽어들인다.
                while ((line = reader.readLine()) != null) {
                    page = page + line + "\n"; // 빈 페이지에 line이 읽어온 텍스트 값이 저장된다.
                }
                mh.sendEmptyMessage(1);
            } catch (Exception e) {
                page = e.getMessage();
                mh.sendEmptyMessage(1);
            }
        }
    }

    class Client extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
            //URL을 통해 사이트를 받아온다.
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mh = new MyHandler();
        tv = (TextView) findViewById(R.id.text);
        addr = (EditText) findViewById(R.id.address);
        go = (Button) findViewById(R.id.go);
        wv = (WebView) findViewById(R.id.web);
        wv.setWebViewClient(new Client());
        //접속한 사이트를 보여줄 WebView

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wv.loadUrl(addr.getText().toString());
                page = "";
                ustring = addr.getText().toString();
                MyThread mt = new MyThread();
                mt.start();
            }
        };
        go.setOnClickListener(cl);
    }
}