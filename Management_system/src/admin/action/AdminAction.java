package admin.action;

import admin.service.AdminService;
import admin.entity.Admin;
import comm.CommonUtils;
import comm.properties.factory.BeanFactory;

public class AdminAction {
		
	private AdminService adminService;
	
	public AdminAction() {
		this.adminService = BeanFactory.getInstance(CommonUtils.AdminInfo.ADMINSERVICE);
	}
	
	public Admin login(Admin admin) {
		return this.adminService.login(admin);
	}

}
