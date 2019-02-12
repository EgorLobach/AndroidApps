package com.egorlobach.firebasechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

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
