package teacher.dao.impl;

import java.util.ArrayList;
import java.util.List;

import comm.base.dao.BaseDaoImpl;
import teacher.dao.TeacherDao;
import teacher.entity.Teacher;

public class TeacherDaoImpl extends BaseDaoImpl<Teacher> implements TeacherDao {

	@Override
	public Teacher login(Teacher teacher) {
		String sql = "SELECT * FROM teacher WHERE tno=? AND tpwd=?";
		Object params[] = new Object[] { teacher.getTno(), teacher.getTpwd() };

		return this.selectOne(sql, Teacher.class, params);
	}

	@Override
	public List<Teacher> queryAll() {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("SELECT * FROM teacher");

		sb.append(" ORDER BY tid");
		String sql = sb.toString().replaceFirst("and", "where");
		return this.selectAll(sql, Teacher.class, list.toArray());
	}

	@Override
	public int insertTeacherBatch(List<Teacher> Teachers) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();

		sb.append("INSERT INTO `teacher` (`tid`, `tno`, `tname`, `tpwd`, `email`, `phone`) VALUES ");
		// 遍历Teacher List
		for (int i = 0; i < Teachers.size(); i++) {
			Teacher t = (Teacher) Teachers.get(i);
			list.add(t.getTno());
			list.add(t.getTname());
			list.add(t.getTpwd());
			list.add(t.getEmail());
			list.add(t.getPhone());
			sb.append("(NULL, ?, ?, ?, ?, ?),");
		}
		sb.deleteCharAt(sb.length() - 1);
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public int deleteTeacher(Integer tid) {
		return this.saveOrUpdate("DELETE FROM teacher WHERE tid=?", tid);
	}

	@Override
	public int deleteTeachersBatch(List<Teacher> teachers) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("DELETE FROM teacher WHERE tid IN (");
		for (int i = 0; i < teachers.size(); i++) {
			Teacher t = (Teacher) teachers.get(i);
			int tid = t.getTid();

			list.add(tid);
			sb.append("? ,");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public Teacher queryTeacherById(Integer tid) {
		String sql = "SELECT * FROM teacher WHERE tid=?";
		return this.selectOne(sql, Teacher.class, tid);
	}

	@Override
	public int updateTeacher(Teacher teacher) {
		String sql = "UPDATE `teacher` SET `tno` = ?, `tname` = ?, `tpwd` = ?, `email` = ?, `phone` = ? WHERE `teacher`.`tid` = ?";
		return this.saveOrUpdate(
			sql,
			new Object[] { teacher.getTno(), teacher.getTname(), teacher.getTpwd(), teacher.getEmail(), teacher.getPhone(), teacher.getTid() });
	}

	@Override
	public List<Teacher> searchByKeyword(String q) {
		String sql = "SELECT * FROM `teacher` WHERE `tno` LIKE ? OR `tname` LIKE ?";
		StringBuffer sb = new StringBuffer();
		sb.append("%" + q + "%");
		String key = sb.toString();
		return this.selectAll(sql, Teacher.class, new Object[] { key, key });
	}

}
