package student.dao.impl;

import java.util.ArrayList;
import java.util.List;

import comm.base.dao.BaseDaoImpl;
import student.dao.StudentDao;
import student.entity.Student;

public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {

	@Override
	public Student login(Student student) {
		String sql = "SELECT * FROM student WHERE sno=? AND spwd=?";
		Object params[] = new Object[] { student.getSno(), student.getSpwd() };

		return this.selectOne(sql, Student.class, params);
	}

	@Override
	public List<Student> queryAll() {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("SELECT `student`.*, `class`.`cname` ");
		sb.append("FROM `student`, `class` ");
		sb.append("WHERE `student`.`cno` = `class`.`cno` ");
		sb.append("ORDER BY sid");
		String sql = sb.toString();
		return this.selectAll(sql, Student.class, list.toArray());
	}

	@Override
	public int insertStudentBatch(List<Student> students) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();

		sb.append("INSERT INTO `student` (`sid`, `sno`, `cno`, `sname`, `spwd`, `sex`, `sage`, `email`, `phone`) VALUES ");
		// 遍历student List
		for (int i = 0; i < students.size(); i++) {
			Student s = (Student) students.get(i);
			list.add(s.getSno());
			list.add(s.getCno());
			list.add(s.getSname());
			list.add(s.getSpwd());
			list.add(s.getSex());
			list.add(s.getSage());
			list.add(s.getEmail());
			list.add(s.getPhone());
			sb.append("(NULL, ?, ?, ?, ?, ?, ?, ?, ?),");
		}
		sb.deleteCharAt(sb.length() - 1);
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public int deleteStudent(Integer sid) {
		return this.saveOrUpdate("DELETE FROM student WHERE sid=?", sid);
	}

	@Override
	public int deleteStudentsBatch(List<Student> students) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("DELETE FROM student WHERE sid IN (");
		for (int i = 0; i < students.size(); i++) {
			Student s = (Student) students.get(i);
			int sid = s.getSid();

			list.add(sid);
			sb.append("? ,");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public Student queryStudentById(Integer sid) {
		String sql = "SELECT * FROM student WHERE sid=?";
		return this.selectOne(sql, Student.class, sid);
	}

	@Override
	public int updateStudent(Student student) {
		String sql = "UPDATE `student` SET `sno` = ?, `cno` = ?, `sname` = ?, `spwd` = ?, `sex` = ?, `sage` = ?, `email` = ?, `phone` = ? WHERE `student`.`sid` = ?";
		return this.saveOrUpdate(
			sql,
			new Object[] { student.getSno(), student.getCno(), student.getSname(), student.getSpwd(), student.getSex(), student.getSage(), student.getEmail(), student.getPhone(), student.getSid() });
	}

	@Override
	public List<Student> searchByKeyword(String q) {
		String sql = "SELECT * "
				+ "FROM (SELECT `student`.*, `class`.`cname` FROM `class`, `student` WHERE `class`.`cno`=`student`.`cno`) AS stu "
				+ "WHERE `sno` LIKE ? OR `sname` LIKE ?";
		StringBuffer sb = new StringBuffer();
		sb.append("%" + q + "%");
		String key = sb.toString();
		return this.selectAll(sql, Student.class, new Object[] { key, key });
	}

	@Override
	public int deleteStudentByCid(Integer cid) {
		String sql = "DELETE FROM `student` "
				+ "WHERE `cno` IN ( "
				+ "SELECT `cno` FROM `class` WHERE `cid` = ? )";
		return this.saveOrUpdate(sql, cid);
	}

	@Override
	public List<Student> queryStudentByCno(String cno) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT `student`.*");
		sb.append("FROM `student`");
		sb.append("WHERE `student`.`cno` = ? ");
		String sql = sb.toString();
		return this.selectAll(sql, Student.class, cno);
	}

}
