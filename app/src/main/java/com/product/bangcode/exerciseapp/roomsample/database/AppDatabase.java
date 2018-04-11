package com.product.bangcode.exerciseapp.roomsample.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.product.bangcode.exerciseapp.roomsample.dao.StudentDao;
import com.product.bangcode.exerciseapp.roomsample.modelDao.Student;

/**
 * Created by wahyufirmansyah on 4/10/18.
 */


@Database(entities = {Student.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract StudentDao studentDao();

}
