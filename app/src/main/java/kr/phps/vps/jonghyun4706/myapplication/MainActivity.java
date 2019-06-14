package kr.phps.vps.jonghyun4706.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            mystartActivity(RegisterActivty.class);
        }else{
            if (user == null) {
                for (UserInfo profile : user.getProviderData()) {
                    String name = profile.getDisplayName();
                    if(user == null){
                        mystartActivity(RGinformation.class);
                    }
                 }
            }
        }
        findViewById(R.id.button).setOnClickListener(onClickListener);
        findViewById(R.id.button2).setOnClickListener(onClickListener);
        findViewById(R.id.logoutbutton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button:
                    mystartActivity(LoginAcitivty.class);
                    break;
                case R.id.button2:
                    FirebaseAuth.getInstance().signOut();
                    mystartActivity(RegisterActivty.class);
                    break;
                case R.id.logoutbutton:
                    mystartActivity(RegisterActivty.class);
                    break;
            }
        }
    };


    private void mystartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
