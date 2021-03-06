package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;

import com.example.model.StudentModel;
import com.example.model.CourseModel;

@Mapper
public interface CourseMapper
{
    @Select("select id_course, name, credits from course where id_course = #{id_course}")
	@Results(value = {
		@Result(property="id_course", column="id_course"),
		@Result(property="name", column="name"),
		@Result(property="credits", column="credits"),
		@Result(property="students", column="id_course", javaType=List.class, many=@Many(select="selectStudent"))
	})
    CourseModel selectCourse (@Param("id_course") String id_course);
	
	@Select("select student.npm, student.name " + 
			"from studentcourse join student " +
			"on studentcourse.npm = student.npm " +
			"where studentcourse.id_course = #{id_course}")
	List<StudentModel> selectStudent (@Param("id_course") String id_course);
}
