package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    String[] ver = {"Jellybean", "Kitkat", "Lollipop", "Marshmellow", "Nouga", "Oreo", "Pie", "Q", "R"};
    ArrayList<String> General;
    ArrayAdapter<String> adap;
    AdapterView.OnItemClickListener icl;
    Button plus, del;
    EditText edt1;
    View.OnClickListener cl;
    int Choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        General = new ArrayList<String>();
        General.add("강감찬");
        General.add("이순신");
        General.add("김유신");
        plus = (Button) findViewById(R.id.plus);
        del = (Button) findViewById(R.id.del);
        lv = (ListView) findViewById(R.id.lv1);
        edt1 = (EditText)findViewById(R.id.edt1);
        adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, General);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setAdapter(adap);

        icl = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Choice = position;
                Toast.makeText(getApplicationContext(), "index=" + position + "장군=" + General.get(position),Toast.LENGTH_LONG).show();
            }
        };
        lv.setOnItemClickListener(icl);
        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.plus :
                        General.add(edt1.getText().toString());
                        adap.notifyDataSetChanged(); //배열에 추가한 뒤 화면에 바뀐 내용을 보여주도록 하는 것
                        break;
                    case R.id.del :
                        General.remove(Choice);
                        adap.notifyDataSetChanged();
                        lv.clearChoices();
                        break;
                }
            }
        };
        plus.setOnClickListener(cl);
        del.setOnClickListener(cl);
    }
}