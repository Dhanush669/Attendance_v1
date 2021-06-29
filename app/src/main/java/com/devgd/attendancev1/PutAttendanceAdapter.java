package com.devgd.attendancev1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class PutAttendanceAdapter extends RecyclerView.Adapter<PutAttendanceAdapter.ViewHolder>{
    private List<AttendanceModelClass> nameList=new ArrayList<>();
    onClickListener listener;
    Context context;
    public PutAttendanceAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.put_attendance_layout,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        AttendanceModelClass modelClass=nameList.get(position);
        holder.name.setText(modelClass.getName());
        holder.absendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("clicked","absent btn");
                holder.attendanceStatus.setText("Absent");
               // if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.itemClick(position);
                //}
            }
        });
    }
    @Override
    public int getItemCount() {
        return nameList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,attendanceStatus;
        Button absendBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            attendanceStatus=itemView.findViewById(R.id.attendanceStatus);
            absendBtn=itemView.findViewById(R.id.absentBtn);
        }
    }
    public void setNameList(List<AttendanceModelClass> nameList){
        this.nameList=nameList;
    }

    public interface onClickListener{
        void itemClick(int position);
    }
    public void onItemClicked(onClickListener listener){
        this.listener=listener;
    }

}
