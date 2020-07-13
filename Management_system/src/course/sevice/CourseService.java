package course.sevice;

import java.util.List;

import course.entity.Course;

public interface CourseService {
	
	// 查询所有课程信息
	List<Course> queryAll();

	// 批量添加课程信息
	int insertCourseBatch(List<Course> Coursees);

	// 删除课程信息
	int deleteCourse(Integer cid);

	// 批量删除课程信息
	int deleteCoursesBatch(List<Course> Coursees);

	// 根据课程id查询课程信息
	Course queryCourseById(Integer cid);
	
	// 根据课程编号查询课程信息
	Course queryCourseByCno(String cno);

	// 修改课程信息
	int updateCourse(Course aCourse);

	// 根据课程号、课程名称、学院查询课程信息
	List<Course> searchByKeyword(String q);

}
