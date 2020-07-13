package user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import comm.base.dao.BaseDaoImpl;
import user.dao.UserDao;
import user.entity.Tbuser;

public class UserDaoImpl extends BaseDaoImpl<Tbuser> implements UserDao {

	@Override
	public Tbuser login(Tbuser user) {
		String sql = "SELECT * FROM tbuser WHERE tbuser=? AND tbpwd=?";
		Object params[] = new Object[] { user.getTbuser(), user.getTbpwd() };

		return this.selectOne(sql, Tbuser.class, params);
	}

	@Override
	public Tbuser queryUserById(Integer tbid) {
		String sql = "SELECT * FROM tbuser WHERE tbid=?";
		return this.selectOne(sql, Tbuser.class, tbid);
	}

	@Override
	public List<Tbuser> queryAll() {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();
		sb.append("SELECT * FROM tbuser");

		sb.append(" ORDER BY tbid");
		String sql = sb.toString().replaceFirst("and", "where");
		return this.selectAll(sql, Tbuser.class, list.toArray());
	}

	@Override
	public Tbuser queryUserByUser(String tbuser) {
		String sql = "SELECT * FROM tbuser WHERE tbuser=?";
		return this.selectOne(sql, Tbuser.class, tbuser);
	}

	@Override
	public int updateUser(Tbuser user) {
		return this.saveOrUpdate(
				"UPDATE `tbuser` SET `tbuser` = ?, `tbname` = ?, `tbpwd` = ? WHERE `tbuser`.`tbid` = ?",
				new Object[] { user.getTbuser(), user.getTbname(), user.getTbpwd(), user.getTbid() });
	}

	@Override
	public int deleteUser(Integer tbid) {
		return this.saveOrUpdate("DELETE FROM tbuser WHERE tbid=?", tbid);
	}

	@Override
	public int deleteUsersBatch(List<Tbuser> user) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();

		sb.append("DELETE FROM tbuser WHERE tbid IN (");
		// 遍历user List
		for (int i = 0; i < user.size(); i++) {
			Tbuser u = (Tbuser) user.get(i);
			int tbid = u.getTbid();

			list.add(tbid);
			sb.append("? ,");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public int insertUser(Tbuser tbuser) {
		return 0;
	}

	@Override
	public int insertUsersBatch(List<Tbuser> user) {
		StringBuffer sb = new StringBuffer();
		List<Object> list = new ArrayList<>();

		sb.append("INSERT INTO `tbuser` (`tbid`, `tbuser`, `tbname`, `tbpwd`) VALUES ");
		// 遍历user List
		for (int i = 0; i < user.size(); i++) {
			Tbuser u = (Tbuser) user.get(i);
			list.add(u.getTbuser());
			list.add(u.getTbname());
			list.add(u.getTbpwd());
			sb.append("(NULL, ?, ?, ?),");
		}
		sb.deleteCharAt(sb.length() - 1);
		String sql = sb.toString();
		return this.saveOrUpdate(sql, list.toArray());
	}

	@Override
	public List<Tbuser> searchByKeyword(String q) {
		String sql = "SELECT * FROM `tbuser` WHERE `tbuser` LIKE ?";
		StringBuffer sb = new StringBuffer();
		sb.append("%" + q + "%");
		String key = sb.toString();
		return this.selectAll(sql, Tbuser.class, key);
	}

}
