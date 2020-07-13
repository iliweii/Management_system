package user.service.impl;

import java.util.List;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import user.dao.UserDao;
import user.entity.Tbuser;
import user.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public UserServiceImpl() {
		this.userDao = BeanFactory.getInstance(CommonUtils.UserInfo.USERDAO);
	}

	@Override
	public Tbuser login(Tbuser user) {
		return this.userDao.login(user);
	}

	@Override
	public Tbuser queryUserById(Integer tbid) {
		return this.userDao.queryUserById(tbid);
	}

	@Override
	public List<Tbuser> queryAll() {
		return this.userDao.queryAll();
	}

	@Override
	public Tbuser queryUserByUser(String tbuser) {
		return this.userDao.queryUserByUser(tbuser);
	}

	@Override
	public int updateUser(Tbuser user) {
		return this.userDao.updateUser(user);
	}

	@Override
	public int deleteUser(Integer tbid) {
		return this.userDao.deleteUser(tbid);
	}

	@Override
	public int deleteUsersBatch(List<Tbuser> user) {
		return this.userDao.deleteUsersBatch(user);
	}

	@Override
	public int insertUser(Tbuser tbuser) {
		return this.userDao.insertUser(tbuser);
	}

	@Override
	public int insertUsersBatch(List<Tbuser> user) {
		return this.userDao.insertUsersBatch(user);
	}

	@Override
	public List<Tbuser> searchByKeyword(String q) {
		return this.userDao.searchByKeyword(q);
	}
}
