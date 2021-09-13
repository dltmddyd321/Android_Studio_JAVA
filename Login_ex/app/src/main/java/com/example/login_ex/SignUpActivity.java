package com.example.login_ex;

import android.content.Intent;
import android.media.MediaCas;
import android.os.Bundle;
import android.se.omapi.Session;
import android.service.textservice.SpellCheckerService;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.singUpButton).setOnClickListener(onClickListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.singUpButton:
                    signUp();
                    break;

            }
        }
    };

    private void signUp() {
        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
        String checkPassword = ((EditText) findViewById(R.id.checkPasswordEditText)).getText().toString();

        // 회원가입 요구 정보를 미 입력 했을때
        if (email.length() > 0 && password.length() > 0 && checkPassword.length() > 0 ) {
            // 비밀번호와 비밀번호 확인이 동일하지
            if (password.equals(checkPassword)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // TODO 성공 UI
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    ToastMessage("회원가입에 성공했습니다!");
                                    goLogin();
                                } else {
                                    // TODO 실패 UI
                                    ToastMessage("비밀번호는 6자리 이상만 가능합니다!!");
                                }
                            }
                        });
            } else {
                ToastMessage("비밀번호가 일치하지 않습니다!!");
            }
        } else {
            ToastMessage("모든 정보를 입력해 주세요!!");
        }
    }
    private void ToastMessage(String message){
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }

    private void goLogin(){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
