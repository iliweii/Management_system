package course.sevice.impl;

import java.util.List;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import course.dao.CourseDao;
import course.entity.Course;
import course.sevice.CourseService;

public class CourseServiceImpl implements CourseService {
	
	private CourseDao courseDao;
	
	public CourseServiceImpl() {
		this.courseDao = BeanFactory.getInstance(CommonUtils.CourseInfo.COURSEDAO);
	}

	@Override
	public List<Course> queryAll() {
		return this.courseDao.queryAll();
	}

	@Override
	public int insertCourseBatch(List<Course> course) {
		return this.courseDao.insertCourseBatch(course);
	}

	@Override
	public int deleteCourse(Integer cid) {
		return this.courseDao.deleteCourse(cid);
	}

	@Override
	public int deleteCoursesBatch(List<Course> courses) {
		return this.courseDao.deleteCoursesBatch(courses);
	}

	@Override
	public Course queryCourseById(Integer cid) {
		return this.courseDao.queryCourseById(cid);
	}

	@Override
	public Course queryCourseByCno(String cno) {
		return this.courseDao.queryCourseByCno(cno);
	}

	@Override
	public int updateCourse(Course course) {
		return this.courseDao.updateCourse(course);
	}

	@Override
	public List<Course> searchByKeyword(String q) {
		return this.courseDao.searchByKeyword(q);
	}

}
