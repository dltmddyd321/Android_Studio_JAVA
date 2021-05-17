package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GridView gv;
    int[] pic = {R.drawable.blue, R.drawable.green, R.drawable.magenta, R.drawable.red, R.drawable.wood,R.drawable.yellow,
    R.drawable.purple, R.drawable.orange, R.drawable.gray, R.drawable.cyan};

    MyAdapter adap;
    AdapterView.OnItemClickListener icl;
    ImageView img;

    class MyAdapter extends BaseAdapter {
        Context con;
        MyAdapter(Context c) {
            con = c;
        }

        @Override
        public int getCount() {
            return pic.length; //배열의 개수 반환
        }

        @Override
        public Object getItem(int position) {
            ImageView iv;
            iv = new ImageView(con);
            iv.setImageResource(pic[position]);
            return iv;
        }

        @Override
        public long getItemId(int position) {
            return position; //배열 아이템의 ID값 반환
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView== null){
                convertView = new ImageView(con);
            }
            ((ImageView)convertView).setImageResource(pic[position]); // %4
            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adap = new MyAdapter(this);

        img = (ImageView)findViewById(R.id.img);
        gv = (GridView) findViewById(R.id.pic);
        gv.setAdapter(adap);

        icl = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"index=" + position, Toast.LENGTH_LONG).show();
                img.setImageResource(pic[position]);
            }
        };
        gv.setOnItemClickListener(icl);
    }
}