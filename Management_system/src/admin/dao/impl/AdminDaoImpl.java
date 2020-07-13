package admin.dao.impl;

import comm.base.dao.BaseDaoImpl;
import admin.dao.AdminDao;
import admin.entity.Admin;

public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {

	@Override
	public Admin login(Admin admin) {
		String sql = "SELECT * FROM admin WHERE aname=? AND apwd=?";
		Object params[] = new Object[] { admin.getAname(), admin.getApwd() };

		return this.selectOne(sql, Admin.class, params);
	}

}
