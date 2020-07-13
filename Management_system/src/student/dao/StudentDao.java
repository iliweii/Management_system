package student.dao;

import java.util.List;

import student.entity.Student;

public interface StudentDao {
	
	// 登录方法
	Student login(Student student);
	
	// 查询所有学生信息
	List<Student> queryAll();
	
	// 批量添加学生信息
	int insertStudentBatch(List<Student> students);
	
	// 删除学生信息
	int deleteStudent(Integer sid);
	
	// 根据班级cid删除学生信息
	int deleteStudentByCid(Integer cid);
	
	// 批量删除学生信息
	int deleteStudentsBatch(List<Student> students);

	// 根据学生编号查询学生信息
	Student queryStudentById(Integer sid);

	// 修改学生信息
	int updateStudent(Student student);

	// 根据学号、姓名查询学生信息
	List<Student> searchByKeyword(String q);

	List<Student> queryStudentByCno(String cno);

}
