package classes.dao.impl;

import java.util.ArrayList;
import java.util.List;

import classes.dao.ClassDao;
import classes.entity.Class;
import comm.base.dao.BaseDaoImpl;

public class ClassDaoImpl extends BaseDaoImpl<Class> implements ClassDao {

	@Override
	public List<Class> queryAll() {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("SELECT `class`.*, `stunum`.`num` ");
		sb.append("FROM `class` LEFT JOIN (SELECT `cno`, COUNT(*) AS `num` FROM `student` GROUP BY `cno`) AS `stunum` ");
		sb.append("ON `class`.`cno`=`stunum`.`cno`");
		String sql = sb.toString().replaceFirst("and", "where");
		return this.selectAll(sql, Class.class, list.toArray());
	}

	@Override
	public int insertClassBatch(List<Class> Classes) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();

		sb.append("INSERT INTO `class` (`cid`, `cno`, `cname`, `college`) VALUES ");
		// 遍历Class List
		for (int i = 0; i < Classes.size(); i++) {
			Class c = (Class) Classes.get(i);
			list.add(c.getCno());
			list.add(c.getCname());
			list.add(c.getCollege());
			sb.append("(NULL, ?, ?, ?),");
		}
		sb.deleteCharAt(sb.length() - 1);
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public int deleteClass(Integer cid) {
		return this.saveOrUpdate("DELETE FROM class WHERE cid=?", cid);
	}

	@Override
	public int deleteClasssBatch(List<Class> Classes) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("DELETE FROM class WHERE cid IN (");
		for (int i = 0; i < Classes.size(); i++) {
			Class c = (Class) Classes.get(i);
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
	public Class queryClassById(Integer cid) {
		String sql = "SELECT * FROM class WHERE cid=?";
		return this.selectOne(sql, Class.class, cid);
	}

	@Override
	public int updateClass(Class c) {
		String sql = "UPDATE `class` SET `cno` = ?, `cname` = ?, `college` = ? WHERE `class`.`cid` = ?";
		return this.saveOrUpdate(
			sql,
			new Object[] { c.getCno(), c.getCname(), c.getCollege(), c.getCid() });
	}

	@Override
	public List<Class> searchByKeyword(String q) {
		String sql = "SELECT * FROM `class` WHERE `cno` LIKE ? OR `cname` LIKE ? OR `college` LIKE ?";
		StringBuffer sb = new StringBuffer();
		sb.append("%" + q + "%");
		String key = sb.toString();
		return this.selectAll(sql, Class.class, new Object[] { key, key, key });
	}

	@Override
	public Class queryClassByCno(String cno) {
		String sql = "SELECT * FROM class WHERE cno=?";
		return this.selectOne(sql, Class.class, cno);
	}

}
