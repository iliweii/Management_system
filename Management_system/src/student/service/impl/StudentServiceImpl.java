package student.service.impl;

import java.util.List;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import student.dao.StudentDao;
import student.entity.Student;
import student.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;
	
	public StudentServiceImpl() {
		this.studentDao = BeanFactory.getInstance(CommonUtils.StudentInfo.STUDENTDAO);
	}
	
	@Override
	public Student login(Student student) {
		return this.studentDao.login(student);
	}

	@Override
	public List<Student> queryAll() {
		return this.studentDao.queryAll();
	}

	@Override
	public int deleteStudent(Integer sid) {
		return this.studentDao.deleteStudent(sid);
	}

	@Override
	public int deleteStudentsBatch(List<Student> students) {
		return this.studentDao.deleteStudentsBatch(students);
	}

	@Override
	public Student queryStudentById(Integer sid) {
		return this.studentDao.queryStudentById(sid);
	}

	@Override
	public int updateStudent(Student student) {
		return this.studentDao.updateStudent(student);
	}

	@Override
	public List<Student> searchByKeyword(String q) {
		return this.studentDao.searchByKeyword(q);
	}

	@Override
	public int deleteStudentByCid(Integer cid) {
		return this.studentDao.deleteStudentByCid(cid);
	}

	@Override
	public int insertStudentBatch(List<Student> students) {
		return this.studentDao.insertStudentBatch(students);
	}

}
