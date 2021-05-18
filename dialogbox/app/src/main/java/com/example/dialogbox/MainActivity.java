package com.example.dialogbox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button basic, hb, sex, tel, ani, mult;
    View.OnClickListener cl;
    AlertDialog.Builder dlg;
    DialogInterface.OnClickListener yes, no, yes1, no1, tcom, san,yes3, no3;
    DialogInterface.OnMultiChoiceClickListener mcl;
    String[] tele,animal;
    int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tele = new String[4];
        tele[0] = "KT";
        tele[1] = "LG";
        tele[2] = "SKT";
        tele[3] = "ETC";

        basic = (Button)findViewById(R.id.basicbox);
        hb = (Button) findViewById(R.id.hobby);
        sex = (Button)findViewById(R.id.sexdlg);
        tel = (Button)findViewById(R.id.telecom);
        ani = (Button)findViewById(R.id.animal);
        mult = (Button)findViewById(R.id.multiple);

        animal = new String[5];
        animal[0] = "강아지";
        animal[1] = "고양이";
        animal[2] = "햄스터";
        animal[3] = "도마뱀";
        animal[4] = "앵무새";

        yes1 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "축구를 좋아하시네요!", Toast.LENGTH_LONG).show();
            }
        };

        tcom = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { // which : 인덱스
                Toast.makeText(getApplicationContext(), "번호"+which+"당신의 통신사는??"+tele[which], Toast.LENGTH_LONG).show();
            }
        };

        yes = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "남성 확인", Toast.LENGTH_LONG).show();
            }
        };

        san = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                choice = which;
                Toast.makeText(getApplicationContext(), "번호"+which+"당신의 반려동물은??"+animal[which], Toast.LENGTH_LONG).show();
            }
        };

        yes3 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "당신의 최종 선택은"+animal[choice], Toast.LENGTH_LONG).show();
            }
        };

        no = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "선택 없음", Toast.LENGTH_LONG).show();
            }
        };

        mcl = new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "당신의 선택은??"+animal[which]+" 최종"+ isChecked, Toast.LENGTH_LONG).show();
            }
        };

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.basicbox :
                        dlg = new AlertDialog.Builder(MainActivity.this);
                        dlg.setTitle("나의미");
                        dlg.setMessage("나의 취미는 축구와니다.");
                        dlg.setIcon(R.mipmap.ic_launcher);
                        dlg.show();
                        break;
                    case R.id.hobby :
                        dlg = new AlertDialog.Builder(MainActivity.this);
                        dlg.setTitle("나의 취미");
                        dlg.setMessage("나의 취미는 축구와 영화 감상입니다.");
                        dlg.setIcon(R.mipmap.ic_launcher);
                        dlg.show();
                        break;
                    case R.id.sexdlg :
                        dlg = new AlertDialog.Builder(MainActivity.this);
                        dlg.setTitle("성별 판별");
                        dlg.setMessage("당신은 남성입니까??");
                        dlg.setIcon(R.mipmap.ic_launcher);
                        dlg.setPositiveButton("예",yes);
                        dlg.setNegativeButton("아니오",no);
                        dlg.show();
                        break;
                    case R.id.telecom :
                        dlg = new AlertDialog.Builder(MainActivity.this);
                        dlg.setTitle("당신의 통신사는?");
                        dlg.setItems(tele, tcom);
                        dlg.setIcon(R.mipmap.ic_launcher);
                        dlg.show();
                        break;
                    case R.id.animal :
                        dlg = new AlertDialog.Builder(MainActivity.this);
                        dlg.setTitle("선호하는 반려동물은?");
                        dlg.setSingleChoiceItems(animal,0,san);
                        dlg.setIcon(R.mipmap.ic_launcher);
                        dlg.setPositiveButton("예",yes3);
                        dlg.setNegativeButton("아니오",no);
                        dlg.show();
                        break;
                    case R.id.multiple :
                        dlg = new AlertDialog.Builder(MainActivity.this);
                        dlg.setTitle("선호하는 반려동물은?");
                        boolean[] init;
                        init = new boolean[5];
                        init[0] = true;
                        init[1] = true;
                        init[2] = false;
                        init[3] = false;
                        init[4] = true;

                        dlg.setMultiChoiceItems(animal,init,null);
                        dlg.setIcon(R.mipmap.ic_launcher);
                        dlg.setPositiveButton("예",yes3);
                        dlg.setNegativeButton("아니오",no);
                        dlg.show();
                        break;
                }
            }
        };
        basic.setOnClickListener(cl);
        hb.setOnClickListener(cl);
        sex.setOnClickListener(cl);
        tel.setOnClickListener(cl);
        ani.setOnClickListener(cl);
        mult.setOnClickListener(cl);
    }
}