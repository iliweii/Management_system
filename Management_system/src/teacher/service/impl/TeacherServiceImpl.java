package teacher.service.impl;

import java.util.List;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import teacher.dao.TeacherDao;
import teacher.entity.Teacher;
import teacher.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {
	
	private TeacherDao teacherDao;
	
	public TeacherServiceImpl() {
		this.teacherDao = BeanFactory.getInstance(CommonUtils.TeacherInfo.TEACHERDAO);
	}

	@Override
	public Teacher login(Teacher teacher) {
		return this.teacherDao.login(teacher);
	}

	@Override
	public List<Teacher> queryAll() {
		return this.teacherDao.queryAll();
	}

	@Override
	public int insertTeacherBatch(List<Teacher> teachers) {
		return this.teacherDao.insertTeacherBatch(teachers);
	}

	@Override
	public int deleteTeacher(Integer tid) {
		return this.teacherDao.deleteTeacher(tid);
	}

	@Override
	public int deleteTeachersBatch(List<Teacher> teachers) {
		return this.teacherDao.deleteTeachersBatch(teachers);
	}

	@Override
	public Teacher queryTeacherById(Integer tid) {
		return this.teacherDao.queryTeacherById(tid);
	}

	@Override
	public int updateTeacher(Teacher teacher) {
		return this.teacherDao.updateTeacher(teacher);
	}

	@Override
	public List<Teacher> searchByKeyword(String q) {
		return this.teacherDao.searchByKeyword(q);
	}

}
