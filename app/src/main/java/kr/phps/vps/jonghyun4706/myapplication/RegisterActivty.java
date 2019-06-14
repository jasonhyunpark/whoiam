package kr.phps.vps.jonghyun4706.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivty extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.rg_btn).setOnClickListener(onClickListener);
        findViewById(R.id.rg_lg).setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rg_btn:
                    signUp();
                    break;
                case R.id.rg_lg:
                    mystartActivity(LoginAcitivty.class);
                    break;
            }
        }
    };

    private void signUp() {
        String email = ((EditText) findViewById(R.id.rg_id)).getText().toString();
        String password = ((EditText) findViewById(R.id.rg_pw)).getText().toString();
        String PW2 = ((EditText) findViewById(R.id.rg_pw2)).getText().toString();
        if (email.length() > 0 && password.length() > 0 && PW2.length() > 0) {
            if (password.equals(PW2)) {
                if (password.length() > 5 && PW2.length() > 5) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        startToast("자신을 찾아 떠날준비가 되셨습니다.");
                                        finish();
                                        //success ui
                                    } else {
                                        if (task.getException() != null) {
                                            // If sign in fails, display a message to the user.
                                            startToast(task.getException().toString());
                                            //fail ui
                                        }
                                    }

                                    // ...
                                }
                            });
                } else {
                    startToast("비밀번호는 6자리 이상입니다.");
                }
            } else {
                startToast("비밀번호가 일치하지 않습니다.");
            }
        } else {
            startToast("이메일 또는 비밀번호를 입력하세요.");
        }
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void mystartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}


