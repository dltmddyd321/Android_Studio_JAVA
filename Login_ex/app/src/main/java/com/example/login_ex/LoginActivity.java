package com.example.login_ex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private int loginCnt;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        tv1 = (TextView) findViewById(R.id.cntView);
        findViewById(R.id.singUpButton).setOnClickListener(onClickListener);
        findViewById(R.id.loginButton).setOnClickListener(onClickListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String text = String.valueOf(loginCnt);
        editor.putString("text",text);
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String text = String.valueOf(loginCnt);
        editor.putString("text",text);
        editor.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.singUpButton:
                    goSignUp();
                    break;

                case R.id.loginButton:
                    login();
                    break;
            }
        }
    };

    private void goSignUp() {
        loginCnt = 0;
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }


    private void login(){
        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
        DatabaseReference userDB = FirebaseDatabase.getInstance().getReference();
        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);
        // 로그인 정보 미입력시
        if (email.length() > 0 && password.length() > 0){
            // Firebase Auth 정보에 사용자가 입력한 이메일과 패스워드가 일치하는지 확인
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String cntText = sharedPreferences.getString("text","");
                                ToastMessageUp("로그인에 성공했습니다!");
                                if(loginCnt == 1) {
                                    Intent intent = new Intent(LoginActivity.this, InfoMenu.class);
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }else {
                                ToastMessageNoUp("아이디 또는 비밀번호가 일치하지 않습니다!!");
                            }
                        }
                    });
        } else {
            ToastMessageNoUp("아이디 또는 비밀번호를 입력해 주세요!!");
        }
    }

    private void ToastMessageUp(String message){
        loginCnt += 1;
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }

    private void ToastMessageNoUp(String message){
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }
}

