package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.CourseModel;
import com.example.model.StudentModel;

@Mapper
public interface StudentMapper
{
	@Select("select npm, name, gpa from student where npm = #{npm}")
	@Results(value = {
		@Result(property="npm", column="npm"),
		@Result(property="name", column="name"),
		@Result(property="gpa", column="gpa"),
		@Result(property="courses", column="npm", javaType=List.class, many=@Many(select="selectCourses"))
	})
    StudentModel selectStudent (@Param("npm") String npm);

    @Select("select npm, name, gpa from student")
	@Results(value = {
		@Result(property="npm", column="npm"),
		@Result(property="name", column="name"),
		@Result(property="gpa", column="gpa"),
		@Result(property="courses", column="npm", javaType=List.class, many=@Many(select="selectCourses"))
	})
    List<StudentModel> selectAllStudents ();

    @Insert("INSERT INTO student (npm, name, gpa) VALUES (#{npm}, #{name}, #{gpa})")
    void addStudent (StudentModel student);
    
    //Latihan delete
    @Delete("delete from student where npm = #{npm}")
	void deleteStudent (@Param("npm") String npm);
   
    //Latihan update
    @Update("update student set name = #{student.name}, gpa = #{student.gpa} where npm = #{student.npm}")
    void updateStudent (@Param("student") StudentModel student);
    
    
    @Select("select course.id_course, name, credits " + 
			"from studentcourse join course " +
			"on studentcourse.id_course = course.id_course " +
			"where studentcourse.npm = #{npm}")
	List<CourseModel> selectCourses (@Param("npm") String npm);
}
