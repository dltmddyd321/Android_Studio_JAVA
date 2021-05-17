package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    MyItem mi;
    ArrayList<MyItem> mydata;
    MyAdater adap;

    class MyItem{
        int icon;
        String comment;
        int price;
        float star;
        MyItem(int a, String b, int c, float d) {
            icon = a;
            comment = b;
            price = c;
            star = d;
        }
    }

    class MyAdater extends BaseAdapter{
        Context con;
        MyAdater(Context c) {
            con = c;
        }

        @Override
        public int getCount() {
            return mydata.size();
        }

        @Override
        public Object getItem(int position) {
            return mydata.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = ((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item,
                        parent, false);
            }
            ((ImageView)convertView.findViewById(R.id.image)).setImageResource(mydata.get(position).icon);
            ((TextView)convertView.findViewById(R.id.text)).setText(mydata.get(position).comment);
            ((EditText)convertView.findViewById(R.id.price)).setText("가격" + mydata.get(position).price);
            ((RatingBar)convertView.findViewById(R.id.rating)).setRating(mydata.get(position).star);
            View.OnClickListener cl;
            cl = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),mydata.get(position).comment + "\n" +
                            mydata.get(position).price, Toast.LENGTH_LONG).show();
                }
            };
            ((Button)convertView.findViewById(R.id.buy)).setOnClickListener(cl);

            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydata = new ArrayList<MyItem>();
        mi = new MyItem(R.drawable.rif, "삼성 냉장고\n 양문형 850L 10년 무상 보증", 3500000, 4);
        mydata.add(mi);
        mi = new MyItem(R.drawable.se, ":LG 세탁기\n 18KG 수용 10년 무상 보증", 2000000, 3);
        mydata.add(mi);
        mi = new MyItem(R.drawable.tv, ":삼성 TV\n 80인치 UHD 올해의 TV 선정!", 2600000,5);
        mydata.add(mi);
        mi = new MyItem(R.drawable.air, ":LG 에어프라이어\n 에어후라이기 5.5L 한정판 특가 세일", 120000,4);
        mydata.add(mi);
        mi = new MyItem(R.drawable.gong, ":LG 공기청정기\n 360도 지원 상품권 추가 증정", 1000000,3.5f);
        mydata.add(mi);
        mi = new MyItem(R.drawable.chung, ":차이슨 청소기\n 무선형 5년 무상 보증", 118000,3);
        mydata.add(mi);

        list = (ListView) findViewById(R.id.mylist);
        adap = new MyAdater(this);
        list.setAdapter(adap);
    }
}