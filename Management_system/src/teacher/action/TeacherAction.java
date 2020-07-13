package teacher.action;

import java.util.List;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import teacher.entity.Teacher;
import teacher.service.TeacherService;

public class TeacherAction {
	
	private TeacherService teacherService;
	
	public TeacherAction() {
		this.teacherService = BeanFactory.getInstance(CommonUtils.TeacherInfo.TEACHERSERVICE);
	}

	public Teacher login(Teacher teacher) {
		return this.teacherService.login(teacher);
	}

	public List<Teacher> queryAll() {
		return this.teacherService.queryAll();
	}

	public int insertTeacherBatch(List<Teacher> teachers) {
		return this.teacherService.insertTeacherBatch(teachers);
	}

	public int deleteTeacher(Integer tid) {
		return this.teacherService.deleteTeacher(tid);
	}

	public int deleteTeachersBatch(List<Teacher> teachers) {
		return this.teacherService.deleteTeachersBatch(teachers);
	}

	public Teacher queryTeacherById(Integer tid) {
		return this.teacherService.queryTeacherById(tid);
	}

	public int updateTeacher(Teacher teacher) {
		return this.teacherService.updateTeacher(teacher);
	}

	public List<Teacher> searchByKeyword(String q) {
		return this.teacherService.searchByKeyword(q);
	}

}
