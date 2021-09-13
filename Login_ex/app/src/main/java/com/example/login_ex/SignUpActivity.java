package com.example.login_ex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference userDB;

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
        String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
        String nickname = ((EditText) findViewById(R.id.nicknameEditText)).getText().toString();
        String birth = ((EditText) findViewById(R.id.dateOfBirthEditText)).getText().toString();
        String phoneNumber = ((EditText) findViewById(R.id.phoneNumberEditText)).getText().toString();

        // 회원가입 요구 정보를 미 입력 했을때
        if (email.length() > 0 && password.length() > 0 && checkPassword.length() > 0  && name.length() > 1 && nickname.length() > 2 && birth.length() > 7 && phoneNumber.length() > 10) {
            // 비밀번호와 비밀번호 확인이 동일하지

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            userDB = FirebaseDatabase.getInstance().getReference();
            SignUp_Info signUp_info = new SignUp_Info(name, nickname, birth, phoneNumber);
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

            if(user != null) {
                userDB.child("users").child(user.getUid()).setValue(signUp_info)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                ToastMessage("정보 등록 완료");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                ToastMessage("정보 등록 실패");
                            }
                        });
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
