package student.action;

import java.util.List;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import student.entity.Student;
import student.service.StudentService;

public class StudentAction {

	private StudentService studentService;
	
	public StudentAction() {
		this.studentService = BeanFactory.getInstance(CommonUtils.StudentInfo.STUDENTSERVICE);
	}
	
	public Student login(Student student) {
		return this.studentService.login(student);
	}

	public List<Student> queryAll() {
		return this.studentService.queryAll();
	}

	public int deleteStudent(Integer sid) {
		return this.studentService.deleteStudent(sid);
	}

	public int deleteStudentsBatch(List<Student> students) {
		return this.studentService.deleteStudentsBatch(students);
	}

	public Student queryStudentById(Integer sid) {
		return this.studentService.queryStudentById(sid);
	}

	public int updateStudent(Student student) {
		return this.studentService.updateStudent(student);
	}

	public List<Student> searchByKeyword(String q) {
		return this.studentService.searchByKeyword(q);
	}

	public int deleteStudentByCid(Integer cid) {
		return this.studentService.deleteStudentByCid(cid);
	}

	public int insertStudentBatch(List<Student> students) {
		return this.studentService.insertStudentBatch(students);
	}

}
