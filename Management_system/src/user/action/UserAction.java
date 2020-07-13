package user.action;

import java.util.List;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import user.entity.Tbuser;
import user.service.UserService;

public class UserAction {

	private UserService userService;

	public UserAction() {
		this.userService = BeanFactory.getInstance(CommonUtils.UserInfo.USERSERVICE);
	}

	public Tbuser login(Tbuser user) {
		return this.userService.login(user);
	}

	public Tbuser queryUserById(Integer tbid) {
		return this.userService.queryUserById(tbid);
	}

	public List<Tbuser> queryAll() {
		return this.userService.queryAll();
	}

	public Tbuser queryUserByUser(String tbuser) {
		return this.userService.queryUserByUser(tbuser);
	}

	public int updateUser(Tbuser user) {
		return this.userService.updateUser(user);
	}

	public int deleteUser(Integer tbid) {
		return this.userService.deleteUser(tbid);
	}

	public int deleteUsersBatch(List<Tbuser> user) {
		return this.userService.deleteUsersBatch(user);
	}

	public int insertUser(Tbuser tbuser) {
		return this.userService.insertUser(tbuser);
	}

	public int insertUsersBatch(List<Tbuser> user) {
		return this.userService.insertUsersBatch(user);
	}

	public List<Tbuser> searchByKeyword(String q) {
		return this.userService.searchByKeyword(q);
	}
}
