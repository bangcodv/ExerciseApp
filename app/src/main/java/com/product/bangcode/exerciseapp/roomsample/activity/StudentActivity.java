package com.product.bangcode.exerciseapp.roomsample.activity;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.product.bangcode.exerciseapp.R;
import com.product.bangcode.exerciseapp.roomsample.database.AppDatabase;
import com.product.bangcode.exerciseapp.roomsample.fragment.AddDataFragment;
import com.product.bangcode.exerciseapp.roomsample.fragment.ListStudentFragment;
import com.product.bangcode.exerciseapp.roomsample.fragment.NotificationFragment;

public class StudentActivity extends AppCompatActivity {
    private final String TAG =getClass().getSimpleName();
    private final int LIST_STUDENT = 0;
    private final int ADD_STUDENT = 1;
    private final int NOTIFICATION = 2;

    public static AppDatabase appDatabase;

    private TextView mTextMessage;

    private Fragment currentFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_view:
                    setFragment(LIST_STUDENT);
                    return true;
                case R.id.add_data:
                    setFragment(ADD_STUDENT);
                    return true;
                case R.id.navigation_notifications:
                    setFragment(NOTIFICATION);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mydb").allowMainThreadQueries().build();

        setFragment(LIST_STUDENT);
    }


    private void setFragment(int page){
        switch (page){
            case LIST_STUDENT:
                currentFragment = ListStudentFragment.newInstance();
                break;
            case ADD_STUDENT:
                currentFragment = AddDataFragment.newInstance();
                break;
            case NOTIFICATION:
                currentFragment = NotificationFragment.newInstance();
                break;

        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.studentFrame, currentFragment)
                .commit();

    }

}
