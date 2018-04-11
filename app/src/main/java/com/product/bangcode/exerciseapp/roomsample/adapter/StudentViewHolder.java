package com.product.bangcode.exerciseapp.roomsample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.product.bangcode.exerciseapp.R;
import com.product.bangcode.exerciseapp.roomsample.modelDao.Student;

/**
 * Created by wahyufirmansyah on 4/11/18.
 */

public class StudentViewHolder extends RecyclerView.ViewHolder{

    public TextView tvName, tvEmail, tvAge;
    public ImageView deleteIcon;

    public StudentViewHolder(View itemView) {
        super(itemView);

        tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
        tvAge = (TextView) itemView.findViewById(R.id.tvAge);
        deleteIcon= (ImageView) itemView.findViewById(R.id.deleteIcon);
    }

    public void setStudent(Student student){
        tvName.setText(student.getName());
        tvEmail.setText(student.getEmail());
        tvAge.setText(String.valueOf(student.getAge()));
    }
}
