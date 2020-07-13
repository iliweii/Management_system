package user.dao;

import java.util.List;

import user.entity.Tbuser;

public interface UserDao {

	// 登录方法
	Tbuser login(Tbuser tbuser);

	// 通过用户id查询用户信息
	Tbuser queryUserById(Integer tbid);

	// 查询所有用户信息
	List<Tbuser> queryAll();

	// 通过用户登录名查询用户信息
	Tbuser queryUserByUser(String tbuser);

	// 添加用户
	int insertUser(Tbuser tbuser);

	// 批量添加用户
	int insertUsersBatch(List<Tbuser> user);

	// 修改用户信息
	int updateUser(Tbuser tbuser);

	// 删除用户
	int deleteUser(Integer tbid);

	// 批量删除用户
	int deleteUsersBatch(List<Tbuser> user);

	// 通过关键词模糊搜索用户登录名
	List<Tbuser> searchByKeyword(String q);

}
