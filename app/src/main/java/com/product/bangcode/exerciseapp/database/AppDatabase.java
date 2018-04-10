package com.product.bangcode.exerciseapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.product.bangcode.exerciseapp.dao.StudentDao;
import com.product.bangcode.exerciseapp.modelDao.Student;

/**
 * Created by wahyufirmansyah on 4/10/18.
 */


@Database(entities = {Student.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract StudentDao studentDao();

}
