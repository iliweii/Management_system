package choose.dao.impl;

import comm.base.dao.BaseDaoImpl;
import student.entity.Student;

import java.util.ArrayList;
import java.util.List;

import choose.dao.ChooseDao;
import choose.entity.Choose;

public class ChooseDaoImpl extends BaseDaoImpl<Choose> implements ChooseDao {

	@Override
	public List<Choose> queryAll() {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("SELECT `choose`.*, `student`.`sname`, `course`.`cname` ");
		sb.append("FROM `choose`, `student`, `course` ");
		sb.append("WHERE `choose`.`sno` = `student`.`sno` AND `choose`.`cno` = `course`.`cno`");
		String sql = sb.toString();
		return this.selectAll(sql, Choose.class, list.toArray());
	}
	
	@Override
	public int insertChoose(Choose choose) {
		String sql = "INSERT INTO `choose` (`scid`, `sno`, `cno`, `status`, `grade`) "
				+ "VALUES (NULL, ?, ?, '0', NULL)";
		List<Object> list = new ArrayList<>();
		list.add(choose.getSno());
		list.add(choose.getCno());
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public int insertChooseBatch(List<Choose> Choose) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();

		sb.append("INSERT INTO `choose` (`scid`, `sno`, `cno`, `status`, `grade`) VALUES ");
		// 遍历Choose List
		for (int i = 0; i < Choose.size(); i++) {
			Choose c = (Choose) Choose.get(i);
			list.add(c.getSno());
			list.add(c.getCno());
			sb.append("(NULL, ?, ?, '1', NULL),");
		}
		sb.deleteCharAt(sb.length() - 1);
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public int deleteChoose(Integer scid) {
		return this.saveOrUpdate("DELETE FROM choose WHERE scid=?", scid);
	}

	@Override
	public int deleteChoosesBatch(List<Choose> choose) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("DELETE FROM choose WHERE scid IN (");
		for (int i = 0; i < choose.size(); i++) {
			Choose c = (Choose) choose.get(i);
			int scid = c.getScid();

			list.add(scid);
			sb.append("? ,");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public Choose queryChooseById(Integer scid) {
		String sql = "SELECT * FROM choose WHERE scid=?";
		return this.selectOne(sql, Choose.class, scid);
	}

	@Override
	public int updateChoose(Choose c) {
		String sql = "UPDATE `choose` SET `grade` = ? WHERE `choose`.`scid` = ?";
		return this.saveOrUpdate(
			sql,
			new Object[] { c.getGrade(), c.getScid() });
	}
	
	@Override
	public int allowChoose(Choose choose) {
		String sql = "UPDATE `choose` SET `status` = ? WHERE `choose`.`scid` = ?";
		return this.saveOrUpdate(
			sql,
			new Object[] { 1, choose.getScid() });
	}

	@Override
	public List<Choose> searchByKeyword(String q) {
		String sql = "SELECT `choose`.*, `student`.`sname`, `course`.`cname` "
				+ "FROM `choose`, `student`, `course` "
				+ "WHERE `choose`.`sno` = `student`.`sno` AND `choose`.`cno` = `course`.`cno` "
				+ "AND (`choose`.`sno` LIKE ? OR `choose`.`cno` LIKE ?)";
		StringBuffer sb = new StringBuffer();
		sb.append("%" + q + "%");
		String key = sb.toString();
		return this.selectAll(sql, Choose.class, new Object[] { key, key });
	}

	@Override
	public int allowChooseBatch(List<Choose> chooses) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("UPDATE `choose` SET `status` = ? WHERE `choose`.`scid` = ? ");
		list.add(1);
		list.add(chooses.get(0).getScid());
		for (int i = 1; i < chooses.size(); i++) {
			Choose c = (Choose) chooses.get(i);
			int scid = c.getScid();

			list.add(scid);
			sb.append("OR `choose`.`scid` = ? ");
		}
		String sql = sb.toString();
		return this.saveOrUpdate(
			sql, list.toArray() );
	}

	@Override
	public int insertChooseBatchPublic(List<Student> student, String cno) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();

		sb.append("INSERT INTO `choose` (`scid`, `sno`, `cno`, `status`, `grade`) VALUES ");
		// 遍历Choose List
		for (int i = 0; i < student.size(); i++) {
			Student s = (Student) student.get(i);
			list.add(s.getSno());
			list.add(cno);
			sb.append("(NULL, ?, ?, '1', NULL),");
		}
		sb.deleteCharAt(sb.length() - 1);
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public List<Choose> queryAllGrade() {
		String sql = "SELECT DISTINCT `choose`.`scid`, `choose`.`grade`, `student`.`sid`, `student`.`sno`, `student`.`sname`, `course`.*, `class`.`cid` AS clid, `class`.`cno` AS clno, `class`.`cname` AS clname " + 
				"FROM `choose`, `teaching`, `student`, `teacher`, `class`, `course` " + 
				"WHERE `choose`.`status` = 1 " + 
				"AND `choose`.`sno` = `student`.`sno` AND `student`.`cno` = `class`.`cno` " + 
				"AND `choose`.`cno` = `course`.`cno` ";
		return this.selectAll(sql, Choose.class);
	}

	@Override
	public int updateChoosesBatch(List<Choose> chooses) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("UPDATE `choose` SET `grade` = ? WHERE `choose`.`scid` = ? ");
		list.add(0);
		list.add(chooses.get(0).getScid());
		for (int i = 1; i < chooses.size(); i++) {
			Choose c = (Choose) chooses.get(i);
			int scid = c.getScid();

			list.add(scid);
			sb.append("OR `choose`.`scid` = ? ");
		}
		String sql = sb.toString();
		return this.saveOrUpdate(
			sql, list.toArray() );
	}

	@Override
	public List<Choose> queryAllGradeByQ(String q) {
		String sql = "SELECT DISTINCT `choose`.`scid`, `choose`.`grade`, `student`.`sid`, `student`.`sno`, `student`.`sname`, `course`.*, `class`.`cid` AS clid, `class`.`cno` AS clno, `class`.`cname` AS clname " + 
				"FROM `choose`, `teaching`, `student`, `teacher`, `class`, `course` " + 
				"WHERE `choose`.`status` = 1 " + 
				"AND `choose`.`sno` = `student`.`sno` AND `student`.`cno` = `class`.`cno` " + 
				"AND `choose`.`cno` = `course`.`cno` " +
				"AND (`choose`.`cno` LIKE ? OR `choose`.`sno` LIKE ?)";
		StringBuffer sb = new StringBuffer();
		sb.append("%" + q + "%");
		String key = sb.toString();
		return this.selectAll(sql, Choose.class, new Object[] { key, key });
	}

	@Override
	public List<Choose> queryAllGradeByClno(String clno) {
		String sql = "SELECT DISTINCT `choose`.`scid`, `choose`.`grade`, `student`.`sid`, `student`.`sno`, `student`.`sname`, `course`.*, `class`.`cid` AS clid, `class`.`cno` AS clno, `class`.`cname` AS clname " + 
				"FROM `choose`, `teaching`, `student`, `teacher`, `class`, `course` " + 
				"WHERE `choose`.`status` = 1 " + 
				"AND `choose`.`sno` = `student`.`sno` AND `student`.`cno` = `class`.`cno` " + 
				"AND `choose`.`cno` = `course`.`cno` " +
				"AND (`class`.`cno` LIKE ?)";
		StringBuffer sb = new StringBuffer();
		sb.append(clno);
		String key = sb.toString();
		return this.selectAll(sql, Choose.class, new Object[] { key });
	}

	@Override
	public List<Choose> queryAllGradeByCono(String cono) {
		String sql = "SELECT DISTINCT `choose`.`scid`, `choose`.`grade`, `student`.`sid`, `student`.`sno`, `student`.`sname`, `course`.*, `class`.`cid` AS clid, `class`.`cno` AS clno, `class`.`cname` AS clname " + 
				"FROM `choose`, `teaching`, `student`, `teacher`, `class`, `course` " + 
				"WHERE `choose`.`status` = 1 " + 
				"AND `choose`.`sno` = `student`.`sno` AND `student`.`cno` = `class`.`cno` " + 
				"AND `choose`.`cno` = `course`.`cno` " +
				"AND (`choose`.`cno` LIKE ?)";
		StringBuffer sb = new StringBuffer();
		sb.append(cono);
		String key = sb.toString();
		return this.selectAll(sql, Choose.class, new Object[] { key });
	}

	@Override
	public List<Choose> queryAllGradeBySid(String sid) {
		String sql = "SELECT DISTINCT `choose`.`scid`, `choose`.`grade`, `student`.`sid`, `student`.`sno`, `student`.`sname`, `course`.*, `class`.`cid` AS clid, `class`.`cno` AS clno, `class`.`cname` AS clname " + 
				"FROM `choose`, `teaching`, `student`, `teacher`, `class`, `course` " + 
				"WHERE `choose`.`status` = 1 " + 
				"AND `choose`.`sno` = `student`.`sno` AND `student`.`cno` = `class`.`cno` " + 
				"AND `choose`.`cno` = `course`.`cno` " +
				"AND (`student`.`sid` LIKE ?)";
		StringBuffer sb = new StringBuffer();
		sb.append(sid);
		String key = sb.toString();
		return this.selectAll(sql, Choose.class, new Object[] { key });
	}

	@Override
	public List<Choose> queryAllChooseBySid(String sid) {
		String sql = "SELECT `choose`.*, `student`.`sname`, `course`.`cname` "
				+ "FROM `choose`, `student`, `course` "
				+ "WHERE `choose`.`sno` = `student`.`sno` AND `choose`.`cno` = `course`.`cno` "
				+ "AND (`student`.`sid` = ?)";
		StringBuffer sb = new StringBuffer();
		sb.append(sid);
		String key = sb.toString();
		return this.selectAll(sql, Choose.class, new Object[] { key });
	}

	@Override
	public List<Choose> queryAllGradeByTid(String tid) {
		Integer id = Integer.parseInt(tid);
		String sql = "SELECT DISTINCT `choose`.`scid`, `choose`.`grade`, `student`.`sid`, `student`.`sno`, `student`.`sname`, `course`.*, `class`.`cid` AS clid, `class`.`cno` AS clno, `class`.`cname` AS clname " + 
				"FROM `choose`, `teaching`, `student`, `teacher`, `class`, `course` " + 
				"WHERE `choose`.`status` = 1 " + 
				"AND `choose`.`sno` = `student`.`sno` AND `student`.`cno` = `class`.`cno` " + 
				"AND `choose`.`cno` = `course`.`cno` " + 
				"AND `choose`.`cno` = `teaching`.`cno` AND `teaching`.`tno` = `teacher`.`tno` AND `teacher`.`tid` = ?";
		return this.selectAll(sql, Choose.class, id);
	}

	@Override
	public List<Choose> searchAllGradeByTid(String tid, String q) {
		Integer id = Integer.parseInt(tid);
		String sql = "SELECT DISTINCT `choose`.`scid`, `choose`.`grade`, `student`.`sid`, `student`.`sno`, `student`.`sname`, `course`.*, `class`.`cid` AS clid, `class`.`cno` AS clno, `class`.`cname` AS clname " + 
				"FROM `choose`, `teaching`, `student`, `teacher`, `class`, `course` " + 
				"WHERE `choose`.`status` = 1 " + 
				"AND `choose`.`sno` = `student`.`sno` AND `student`.`cno` = `class`.`cno` " + 
				"AND `choose`.`cno` = `course`.`cno` " + 
				"AND `choose`.`cno` = `teaching`.`cno` AND `teaching`.`tno` = `teacher`.`tno` AND `teacher`.`tid` = ? " +
				"AND (`choose`.`cno` LIKE ? OR `choose`.`sno` LIKE ?)";
		StringBuffer sb = new StringBuffer();
		sb.append("%" + q + "%");
		String key = sb.toString();
		return this.selectAll(sql, Choose.class, new Object[] { id, key, key });
	}
	
}
