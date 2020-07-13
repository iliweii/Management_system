package teaching.dao.impl;

import java.util.ArrayList;
import java.util.List;

import comm.base.dao.BaseDaoImpl;
import teaching.dao.TeachingDao;
import teaching.entity.Teaching;

public class TeachingDaoImpl extends BaseDaoImpl<Teaching> implements TeachingDao {

	@Override
	public List<Teaching> queryAll() {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("SELECT `teaching`.*, `teacher`.`tname`, `course`.`cname` ");
		sb.append("FROM `teaching`, `teacher`, `course` ");
		sb.append("WHERE `teaching`.`tno` = `teacher`.`tno` AND `teaching`.`cno` = `course`.`cno`");
		String sql = sb.toString();
		return this.selectAll(sql, Teaching.class, list.toArray());
	}
	
	@Override
	public int insertTeaching(Teaching teaching) {
		String sql = "INSERT INTO `teaching` (`tcid`, `cno`, `tno`, `book`, `status`) "
				+ "VALUES (NULL, ?, ?, ?, ?)";
		List<Object> list = new ArrayList<>();
		list.add(teaching.getCno());
		list.add(teaching.getTno());
		list.add(teaching.getBook());
		list.add(teaching.getStatus());
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public int insertTeachingBatch(List<Teaching> teaching) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();

		sb.append("INSERT INTO `teaching` (`tcid`, `cno`, `tno`, `book`, `status`) VALUES ");
		// 遍历Teaching List
		for (int i = 0; i < teaching.size(); i++) {
			Teaching t = (Teaching) teaching.get(i);
			list.add(t.getCno());
			list.add(t.getTno());
			list.add(t.getBook());
			sb.append("(NULL, ?, ? , ?, '1'),"); // 默认通过
		}
		sb.deleteCharAt(sb.length() - 1);
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public int deleteTeaching(Integer tcid) {
		return this.saveOrUpdate("DELETE FROM teaching WHERE tcid=?", tcid);
	}

	@Override
	public int deleteTeachingsBatch(List<Teaching> teaching) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("DELETE FROM teaching WHERE tcid IN (");
		for (int i = 0; i < teaching.size(); i++) {
			Teaching t = (Teaching) teaching.get(i);
			int tcid = t.getTcid();

			list.add(tcid);
			sb.append("? ,");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public Teaching queryTeachingById(Integer tcid) {
		String sql = "SELECT * FROM teaching WHERE tcid=?";
		return this.selectOne(sql, Teaching.class, tcid);
	}

	@Override
	public int updateTeaching(Teaching t) {
		String sql = "UPDATE `Teaching` SET `book` = ? WHERE `teaching`.`tcid` = ?";
		return this.saveOrUpdate(
			sql,
			new Object[] { t.getBook(), t.getTcid() });
	}
	
	@Override
	public int allowTeaching(Teaching teaching) {
		String sql = "UPDATE `teaching` SET `status` = ? WHERE `teaching`.`tcid` = ?";
		return this.saveOrUpdate(
			sql,
			new Object[] { 1, teaching.getTcid() });
	}

	@Override
	public List<Teaching> searchByKeyword(String q) {
		String sql = "SELECT `teaching`.*, `teacher`.`tname`, `course`.`cname` "
				+ "FROM `teaching`, `teacher`, `course` "
				+ "WHERE `teaching`.`tno` = `teacher`.`tno` AND `teaching`.`cno` = `course`.`cno` "
				+ "AND (`teaching`.`tno` LIKE ? OR `teaching`.`cno` LIKE ?)";
		StringBuffer sb = new StringBuffer();
		sb.append("%" + q + "%");
		String key = sb.toString();
		return this.selectAll(sql, Teaching.class, new Object[] { key, key });
	}

	@Override
	public int allowTeachingsBatch(List<Teaching> teachings) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("UPDATE `teaching` SET `status` = ? WHERE `teaching`.`tcid` = ? ");
		list.add(1);
		list.add(teachings.get(0).getTcid());
		for (int i = 1; i < teachings.size(); i++) {
			Teaching c = (Teaching) teachings.get(i);
			int scid = c.getTcid();

			list.add(scid);
			sb.append("OR `choose`.`tcid` = ? ");
		}
		String sql = sb.toString();
		return this.saveOrUpdate(
			sql, list.toArray() );
	}

	@Override
	public List<Teaching> queryAllGroupTeacher() {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("SELECT `teaching`.`tcid`, `teaching`.`cno`, GROUP_CONCAT(`teacher`.`tname` SEPARATOR ',') AS teacher ");
		sb.append("FROM `teaching`, `teacher` ");
		sb.append("WHERE `teacher`.`tno` = `teaching`.`tno` GROUP BY `teaching`.`cno`");
		String sql = sb.toString();
		return this.selectAll(sql, Teaching.class, list.toArray());
	}

	@Override
	public List<Teaching> queryTeachingByTid(int id) {
		String sql = "SELECT `teaching`.*, `teacher`.`tname`, `course`.`cname` "
				+ "FROM `teaching`, `teacher`, `course` "
				+ "WHERE `teaching`.`tno` = `teacher`.`tno` AND `teaching`.`cno` = `course`.`cno` "
				+ "AND `teacher`.`tid` = ?";
		return this.selectAll(sql, Teaching.class, id);
	}

}
