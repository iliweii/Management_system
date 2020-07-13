package course.dao.impl;

import java.util.ArrayList;
import java.util.List;

import comm.base.dao.BaseDaoImpl;
import course.dao.CourseDao;
import course.entity.Course;

public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {

	@Override
	public List<Course> queryAll() {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("SELECT `course`.*, `cournum`.`num` ");
		sb.append("FROM `course` LEFT JOIN (SELECT `cno`, COUNT(*) AS `num` FROM `choose` GROUP BY `cno`) AS `cournum` ");
		sb.append("ON `course`.`cno`=`cournum`.`cno`");
		String sql = sb.toString().replaceFirst("and", "where");
		return this.selectAll(sql, Course.class, list.toArray());
	}

	@Override
	public int insertCourseBatch(List<Course> course) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();

		sb.append("INSERT INTO `course` (`cid`, `cno`, `cname`) VALUES ");
		// 遍历Course List
		for (int i = 0; i < course.size(); i++) {
			Course c = (Course) course.get(i);
			list.add(c.getCno());
			list.add(c.getCname());
			sb.append("(NULL, ?, ?),");
		}
		sb.deleteCharAt(sb.length() - 1);
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public int deleteCourse(Integer cid) {
		return this.saveOrUpdate("DELETE FROM course WHERE cid=?", cid);
	}

	@Override
	public int deleteCoursesBatch(List<Course> course) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("DELETE FROM course WHERE cid IN (");
		for (int i = 0; i < course.size(); i++) {
			Course c = (Course) course.get(i);
			int cid = c.getCid();

			list.add(cid);
			sb.append("? ,");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public Course queryCourseById(Integer cid) {
		String sql = "SELECT * FROM course WHERE cid=?";
		return this.selectOne(sql, Course.class, cid);
	}

	@Override
	public int updateCourse(Course c) {
		String sql = "UPDATE `course` SET `cno` = ?, `cname` = ? WHERE `course`.`cid` = ?";
		return this.saveOrUpdate(
			sql,
			new Object[] { c.getCno(), c.getCname(), c.getCid() });
	}

	@Override
	public List<Course> searchByKeyword(String q) {
		String sql = "SELECT * FROM `Course` WHERE `cno` LIKE ? OR `cname` LIKE ?";
		StringBuffer sb = new StringBuffer();
		sb.append("%" + q + "%");
		String key = sb.toString();
		return this.selectAll(sql, Course.class, new Object[] { key, key });
	}

	@Override
	public Course queryCourseByCno(String cno) {
		String sql = "SELECT * FROM course WHERE cno=?";
		return this.selectOne(sql, Course.class, cno);
	}

}
