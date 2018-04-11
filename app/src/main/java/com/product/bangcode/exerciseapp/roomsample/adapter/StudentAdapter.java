package com.product.bangcode.exerciseapp.roomsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.product.bangcode.exerciseapp.R;
import com.product.bangcode.exerciseapp.roomsample.modelDao.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wahyufirmansyah on 4/11/18.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {
    private final String TAG = getClass().getSimpleName();

    Context mContext;
    List<Student> students = new ArrayList<>();
    public OnAdapterClick onAdapterClick;

    public void setOnAdapterClick(OnAdapterClick onAdapterClick){
        this.onAdapterClick = onAdapterClick;
    }


    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(mContext);
        View itemView =  inflater.inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, final int position) {
        final Student student = students.get(position);
        holder.setStudent(student);

        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdapterClick.onClick(student, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void addAll(List<Student> students){
        this.students = students;
        notifyDataSetChanged();
    }

    public interface OnAdapterClick{
        void onClick(Student student, int position);
    }
}
