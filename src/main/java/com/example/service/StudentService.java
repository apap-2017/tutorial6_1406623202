package com.example.service;

import java.util.List;

import com.example.model.StudentModel;

public interface StudentService
{
    StudentModel selectStudent (String npm);


    List<StudentModel> selectAllStudents ();


    void addStudent (StudentModel student);

    //Latihan delete
    void deleteStudent (String npm);
    
    //Latihan update
    void updateStudent (StudentModel student);
}
