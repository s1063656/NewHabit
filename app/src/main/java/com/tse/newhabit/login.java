package com.tse.newhabit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class login extends AppCompatActivity {
    private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    public void start(View v){

        email = (EditText) findViewById(R.id.userEmail);
        if(!email.getText().toString().equals("")){

                MainActivity.HabitList.clear();
                Intent it = new Intent(this, MainActivity.class);
                it.putExtra("USERID", email.getText().toString().trim());
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection(it.getStringExtra("USERID"))
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    int i = 0;
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        MainActivity.HabitList.add(new Habit(document.getString("habitName"), document.get("beginDate")));
                                        Log.d("TAG", document.getId() + " => " + MainActivity.HabitList.get(i).getTitle());
                                        Log.d("TAG", document.getId() + " => " + document.getData());
                                        i++;
                                    }
                                } else {
                                    Log.w("TAG", "Error getting documents.", task.getException());
                                }
                            }
                        });
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                }
                startActivity(it);

        }else{
            Toast.makeText(this,"請輸入信箱已取得資料",Toast.LENGTH_SHORT).show();
        }
    }
}