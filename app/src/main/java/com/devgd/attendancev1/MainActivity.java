package com.devgd.attendancev1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText batch,date,dept,sec,hour,acayear;
    RadioGroup radioGroup;
    String year,semind;
    TextView oddind,evenind;
    RadioButton b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        batch=findViewById(R.id.batch);
        date=findViewById(R.id.date);
        dept=findViewById(R.id.dep);
        sec=findViewById(R.id.sec);
        hour=findViewById(R.id.hour);
        acayear=findViewById(R.id.acayear);
        radioGroup=findViewById(R.id.radioGroup);
        b1=findViewById(R.id.oddSem);
        b2=findViewById(R.id.evenSem);
//        oddind=findViewById(R.id.oddSemInd);
//        evenind=findViewById(R.id.evenSemInd);
        acayear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(batch.getText().toString().equals(acayear.getText().toString())){
                    year="1";
                    oddind.setText("1");
                    evenind.setText("2");
                }
                else {
                    int y;
                    y = Integer.parseInt(acayear.getText().toString())-Integer.parseInt(batch.getText().toString());
//                    oddind.setText(String.valueOf((y*2)-1));
//                    evenind.setText(String.valueOf(y*2));
                    b1.setText("Odd Sem "+String.valueOf((y*2)-1));
                    b2.setText("Even Sem "+String.valueOf(y*2));
                    year=String.valueOf(y);
                }
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.oddSem:
                        semind= String.valueOf(b1.getText().toString().charAt(b1.getText().toString().length()-1));
                        //semind=oddind.getText().toString();
                        Log.i("clicked","oddbtn");
                        break;
                    case R.id.evenSem:
                        semind= String.valueOf(b2.getText().toString().charAt(b2.getText().toString().length()-1));
                        //semind=evenind.getText().toString();
                        Log.i("clicked","oddbtn");
                        break;
                }
            }
        });

    }

    public void fetch(View view) {
        Intent intent=new Intent(getApplicationContext(),NameListActivity.class);
        intent.putExtra("batch",batch.getText().toString());
        intent.putExtra("date",date.getText().toString());
        intent.putExtra("dep",dept.getText().toString());
        intent.putExtra("sec",sec.getText().toString());
        intent.putExtra("hour",hour.getText().toString());
        intent.putExtra("year",year);
        intent.putExtra("sem",semind);
        startActivity(intent);

    }
}