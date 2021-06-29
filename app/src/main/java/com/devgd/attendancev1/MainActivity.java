package com.devgd.attendancev1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText batch,date,dept,sec,hour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        batch=findViewById(R.id.batch);
        date=findViewById(R.id.date);
        dept=findViewById(R.id.dep);
        sec=findViewById(R.id.sec);
        hour=findViewById(R.id.hour);
    }

    public void fetch(View view) {
        Intent intent=new Intent(getApplicationContext(),NameListActivity.class);
        intent.putExtra("batch",batch.getText().toString());
        intent.putExtra("date",date.getText().toString());
        intent.putExtra("dep",dept.getText().toString());
        intent.putExtra("sec",sec.getText().toString());
        intent.putExtra("hour",hour.getText().toString());
        startActivity(intent);

    }
}