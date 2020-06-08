package com.tse.newhabit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    private EditText email ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    public void start(View v){
        email = (EditText) findViewById(R.id.userEmail);
        if(!email.getText().toString().equals("")){
            Intent it = new Intent(this,MainActivity.class);
            it.putExtra("USERID",email.getText().toString().trim());
            startActivity(it);
        }else{
            Toast.makeText(this,"請輸入信箱已取得資料",Toast.LENGTH_SHORT).show();
        }
    }
}