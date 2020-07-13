package admin.service.impl;

import admin.dao.AdminDao;
import admin.entity.Admin;
import admin.service.AdminService;
import comm.CommonUtils;
import comm.properties.factory.BeanFactory;

public class AdminServiceImpl implements AdminService {
	
	private AdminDao adminDao;
	
	public AdminServiceImpl() {
		this.adminDao = BeanFactory.getInstance(CommonUtils.AdminInfo.ADMINDAO);
	}

	@Override
	public Admin login(Admin admin) {
		return this.adminDao.login(admin);
	}

}
