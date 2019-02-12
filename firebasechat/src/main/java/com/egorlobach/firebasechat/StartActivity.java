package com.egorlobach.firebasechat;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class StartActivity extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE = 1;

    private FirebaseListAdapter<Message> adapter;
    ConstraintLayout mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mainActivity = findViewById(R.id.mainActivity);

        Button btnSendMessage = findViewById(R.id.btnSendMessage);
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textMessage = findViewById(R.id.textMessage);
                FirebaseDatabase.getInstance().getReference().push().setValue(
                        new Message(textMessage.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                textMessage.setText("");
            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_REQUEST_CODE);
        } else {
            displayChat();
        }
    }

    private void displayChat() {
        ListView listMessages = findViewById(R.id.listMessages);
        adapter = new FirebaseListAdapter<Message>(this, Message.class, R.layout.item, FirebaseDatabase.getInstance().getReference()) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected void populateView(View v, Message model, int position) {
                TextView tvMessage = v.findViewById(R.id.tvMessage);
                TextView tvAuthor = v.findViewById(R.id.tvAuthor);
                TextView tvTime = v.findViewById(R.id.tvTime);

                tvMessage.setText(model.getMessage());
                tvAuthor.setText(model.getAuthor());
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy (HH:mm:ss)");
                tvTime.setText(df.format(model.getTime()));
            }
        };
        listMessages.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_REQUEST_CODE){
            if(requestCode == RESULT_OK){
                Snackbar.make(mainActivity, "Вход выполнен", Snackbar.LENGTH_SHORT).show();
                displayChat();
            }else {
                Snackbar.make(mainActivity, "Вход не выполнен", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_singout){
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar.make(mainActivity, "Выход выполнен", Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
        return true;
    }
}
