package com.egorlobach.firebasechat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.egorlobach.firebasechat.R;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(StartActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin: startActivity(new Intent(StartActivity.this, LoginActivity.class)); break;
            case R.id.btnRegister: startActivity(new Intent(StartActivity.this, RegisterActivity.class)); break;
            default: break;
        }
    }
}
