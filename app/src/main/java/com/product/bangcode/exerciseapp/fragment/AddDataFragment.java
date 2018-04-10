package com.product.bangcode.exerciseapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.product.bangcode.exerciseapp.R;
import com.product.bangcode.exerciseapp.activity.StudentActivity;
import com.product.bangcode.exerciseapp.modelDao.Student;

public class AddDataFragment extends Fragment implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    private AppCompatEditText etName, etEmail, etAge;
    private AppCompatButton addButton;

    Context mContext;

    public AddDataFragment() {
        // Required empty public constructor
    }


    public static AddDataFragment newInstance() {
        AddDataFragment fragment = new AddDataFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_data, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((StudentActivity) getActivity()).getSupportActionBar().setTitle("Add Data");

        mContext = getActivity();

        etName = (AppCompatEditText) view.findViewById(R.id.etName);
        etAge = (AppCompatEditText) view.findViewById(R.id.etAge);
        etEmail = (AppCompatEditText) view.findViewById(R.id.etEmail);
        addButton = (AppCompatButton) view.findViewById(R.id.addButton);

        addButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addButton:
                setStudent();
                break;
        }
    }

    private void setStudent() {
        Student student = new Student();
        student.setName(etName.getText().toString());
        student.setEmail(etEmail.getText().toString());
        student.setAge(Integer.parseInt(etAge.getText().toString()));

        StudentActivity.appDatabase.studentDao().insertAll(student);

        Toast.makeText(mContext, "User Added !", Toast.LENGTH_SHORT).show();

        etName.setText("");
        etEmail.setText("");
        etAge.setText("");
    }
}
