package course.action;

import java.util.List;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import course.entity.Course;
import course.sevice.CourseService;

public class CourseAction {
	
	private CourseService courseService;
	
	public CourseAction() {
		this.courseService = BeanFactory.getInstance(CommonUtils.CourseInfo.COURSESERVICE);
	}

	public List<Course> queryAll() {
		return this.courseService.queryAll();
	}

	public int insertCourseBatch(List<Course> course) {
		return this.courseService.insertCourseBatch(course);
	}

	public int deleteCourse(Integer cid) {
		return this.courseService.deleteCourse(cid);
	}

	public int deleteCoursesBatch(List<Course> courses) {
		return this.courseService.deleteCoursesBatch(courses);
	}

	public Course queryCourseById(Integer cid) {
		return this.courseService.queryCourseById(cid);
	}

	public Course queryCourseByCno(String cno) {
		return this.courseService.queryCourseByCno(cno);
	}

	public int updateCourse(Course course) {
		return this.courseService.updateCourse(course);
	}

	public List<Course> searchByKeyword(String q) {
		return this.courseService.searchByKeyword(q);
	}

}
