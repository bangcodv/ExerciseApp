package com.product.bangcode.exerciseapp.roomsample.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.product.bangcode.exerciseapp.R;
import com.product.bangcode.exerciseapp.roomsample.activity.StudentActivity;
import com.product.bangcode.exerciseapp.roomsample.adapter.StudentAdapter;
import com.product.bangcode.exerciseapp.roomsample.modelDao.Student;
import com.product.bangcode.exerciseapp.roomsample.utils.Param;


public class ListStudentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private final String TAG = getClass().getSimpleName();

    RecyclerView recyclerView;
    StudentAdapter adapter;
    Context mContext;
    SwipeRefreshLayout refreshLayout;
    TextView noData;

    public ListStudentFragment() {
        // Required empty public constructor
    }


    public static ListStudentFragment newInstance() {
        ListStudentFragment fragment = new ListStudentFragment();
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
        return inflater.inflate(R.layout.fragment_list_student, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((StudentActivity) getActivity()).getSupportActionBar().setTitle("List Student");

        setHasOptionsMenu(true);

        mContext = getActivity();

        recyclerView = (RecyclerView) view.findViewById(R.id.studentRecycler);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.studentSwipe);
        noData = (TextView) view.findViewById(R.id.tvNoData);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        adapter = new StudentAdapter();
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(this);

        getDataDao();

        adapter.setOnAdapterClick(new StudentAdapter.OnAdapterClick() {
            @Override
            public void onClick(final Student student, int typeExe) {
                switch (typeExe) {
                    case Param.EDIT:
                        alertEdit(student);
                        break;
                    case Param.DELETE:
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("data will delete forever, are you sure ? ");
                        builder.setNegativeButton("No", null);
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                StudentActivity.appDatabase.studentDao().delete(student);
                                refreshItems();
                                Toast.makeText(mContext, "Data Deleted !", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.create().show();
                        break;
                }
            }
        });

    }

    private void getDataDao() {
        adapter.addAll(StudentActivity.appDatabase.studentDao().getAll());
        if (StudentActivity.appDatabase.studentDao().getAll().size() == 0)
            noData.setVisibility(View.VISIBLE);

    }

    @Override
    public void onRefresh() {
        refreshItems();
    }

    private void refreshItems() {
        getDataDao();
        onItemLoadComplete();
    }

    private void onItemLoadComplete() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.list_student_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all:
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("All data will be deleted , are you sure ?");
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        StudentActivity.appDatabase.studentDao().deleteAll(StudentActivity.appDatabase.studentDao().getAll());
                        StudentActivity.appDatabase.studentDao().deleteAll();
                        refreshItems();
                        Toast.makeText(mContext, "Data Deleted !", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.create().show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void alertEdit(final Student student){
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);

        View view = LayoutInflater.from(mContext).inflate(R.layout.snippet_alert_dialog_edit, null);

        final AppCompatEditText etName, etAge, etEmail;

        etName = (AppCompatEditText) view.findViewById(R.id.etName);
        etAge = (AppCompatEditText) view.findViewById(R.id.etAge);
        etEmail = (AppCompatEditText) view.findViewById(R.id.etEmail);

        etName.setText(student.getName());
        etAge.setText(String.valueOf(student.getAge()));
        etEmail.setText(student.getEmail());

        dialog.setCancelable(false);
        dialog.setView(view);
        dialog.setNegativeButton("Cancel", null);
        dialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String newName = etName.getText().toString();
                String newEmail = etEmail.getText().toString();
                int newAge = Integer.parseInt(etAge.getText().toString());

                student.setAge(newAge);
                student.setEmail(newEmail);
                student.setName(newName);

                StudentActivity.appDatabase.studentDao().update(student);
                refreshItems();
                Toast.makeText(mContext, "Data Updated!", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.create().show();

    }
}
