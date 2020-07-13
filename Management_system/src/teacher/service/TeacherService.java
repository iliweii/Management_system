package teacher.service;

import java.util.List;

import teacher.entity.Teacher;

public interface TeacherService {

	// 登录方法
	Teacher login(Teacher teacher);
	
	// 查询所有教师信息
	List<Teacher> queryAll();
	
	// 批量添加教师信息
	int insertTeacherBatch(List<Teacher> teachers);
	
	// 删除教师信息
	int deleteTeacher(Integer tid);
	
	// 批量删除教师信息
	int deleteTeachersBatch(List<Teacher> teachers);

	// 根据教师编号查询教师信息
	Teacher queryTeacherById(Integer tid);

	// 修改教师信息
	int updateTeacher(Teacher teacher);

	// 根据教师号、姓名查询教师信息
	List<Teacher> searchByKeyword(String q);

	
}
