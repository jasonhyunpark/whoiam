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

public class LoginAcitivty extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_login);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.send_btn).setOnClickListener(onClickListener);
        findViewById(R.id.log_rg).setOnClickListener(onClickListener);
        findViewById(R.id.pwd_reset).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.send_btn:
                    LoginUp();
                    break;
                case R.id.log_rg:
                    startRegister();
                    break;
                case R.id.pwd_reset:
                    startActivity(PasswordReset.class);
                    break;
            }
        }
    };

    private void LoginUp() {
        String email = ((EditText) findViewById(R.id.send_id)).getText().toString();
        String password = ((EditText) findViewById(R.id.Log_pw)).getText().toString();

        if (email.length() > 0 && password.length() > 0) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("환영합니다. 우리 함께 발전해요!");
                                startActivity(MainActivity.class);
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
            startToast("이메일 또는 비밀번호를 입력하세요.");
        }
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void startRegister (){
        Intent intent = new Intent(this, RegisterActivty.class);
        startActivity(intent);
    }
    private void startActivity(Class c){
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
