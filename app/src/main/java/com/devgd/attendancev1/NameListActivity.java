package com.devgd.attendancev1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NameListActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    String batch,dept,sec,year,date;
    List<AttendanceModelClass> nameList;
    TextView dep,sect,hour;
    PutAttendanceAdapter adapter;
    List<Boolean> attendanceStatus;
    int firsttime=0;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);
        firestore=FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.namelistrecyclerview);
        nameList=new ArrayList<>();
        attendanceStatus=new ArrayList<>();
        dep=findViewById(R.id.namelistdepartment);
        sect=findViewById(R.id.namelistsec);
        hour=findViewById(R.id.namelisthour);
        sharedPreferences=this.getPreferences(MODE_PRIVATE);
        Intent intent=getIntent();
        batch=intent.getStringExtra("batch");
        dept=intent.getStringExtra("dep");
        sec=intent.getStringExtra("sec");
        year=intent.getStringExtra("year");
        date=intent.getStringExtra("date");
        dep.setText(dept);
        sect.setText(sec);
        hour.setText(intent.getStringExtra("hour"));
        adapter=new PutAttendanceAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(NameListActivity.this));
        adapter.onItemClicked(new PutAttendanceAdapter.onClickListener() {
            @Override
            public void itemClick(int position) {
                attendanceStatus.set(position,false);
                Log.i("changed to absent", ""+attendanceStatus.get(position));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firestore.collection(batch)
                .whereEqualTo("dep",dept)
                .whereEqualTo("sec",sec)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot querySnapshot) {
                nameList=new ArrayList<>();
                for(QueryDocumentSnapshot documentSnapshot:querySnapshot){
                    AttendanceModelClass modelClass=documentSnapshot.toObject(AttendanceModelClass.class);
                    modelClass.setRegNo(documentSnapshot.getId());
                    nameList.add(modelClass);
                    attendanceStatus.add(true);
                }
                adapter.setNameList(nameList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(NameListActivity.this));

            }
        });
    }

    public void upload(View view) {
        if(sharedPreferences.getString("first",null)==null) {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("first","1");
            editor.apply();
            firsttime++;
            for (int i = 0; i <nameList.size();i++){
                AttendanceModelClass modelClass=nameList.get(i);
                Map<String ,String > attendance=new HashMap<>();
                attendance.put("h"+hour.getText().toString(), String.valueOf(attendanceStatus.get(i)));
                attendance.put("h2", " ");
                attendance.put("h3", " ");
                attendance.put("h4", " ");
                attendance.put("h5", " ");
                attendance.put("h6", " ");
                attendance.put("h7", " ");
                firestore.collection(date).document(modelClass.getRegNo()).set(attendance).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("addwd sucessfully","doneee");
                    }
                });
            }
        }
        else{
            for (int i = 0; i <nameList.size();i++) {
                Map<String,Object> attendance=new HashMap<>();
                attendance.put("h"+hour.getText().toString(), String.valueOf(attendanceStatus.get(i)));
                firestore.collection(date).document().update(attendance);
            }
        }
    }
}