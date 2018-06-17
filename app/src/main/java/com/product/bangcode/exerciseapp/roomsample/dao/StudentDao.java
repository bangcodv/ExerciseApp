package com.product.bangcode.exerciseapp.roomsample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.product.bangcode.exerciseapp.roomsample.modelDao.Student;

import java.util.List;

/**
 * Created by wahyufirmansyah on 4/10/18.
 */

@Dao
public interface StudentDao {

    /*make query to get all data from student table*/
    @Query("SELECT * FROM student ORDER BY uid DESC")
    List<Student> getAll();

    /*make query to get count of student data*/
    @Query("SELECT COUNT(*) from student")
    int countStudents();

    /*method to insert student data*/
    @Insert
    void insertAll(Student... students);

    /*method to delete student column*/
    @Delete
    void delete(Student student);

    //    @Delete
//    void deleteAll(List<Student> students);
    @Query("DELETE FROM student")
    void deleteAll();

    @Update
    void update(Student... students);
}
