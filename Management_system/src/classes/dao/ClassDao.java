package classes.dao;

import java.util.List;

import classes.entity.Class;

public interface ClassDao {

	// 查询所有班级信息
	List<Class> queryAll();

	// 批量添加班级信息
	int insertClassBatch(List<Class> classes);

	// 删除班级信息
	int deleteClass(Integer cid);

	// 批量删除班级信息
	int deleteClasssBatch(List<Class> classes);

	// 根据班级id查询班级信息
	Class queryClassById(Integer cid);
	
	// 根据班级编号查询班级信息
	Class queryClassByCno(String cno);

	// 修改班级信息
	int updateClass(Class aclass);

	// 根据班级号、班级名称、学院查询班级信息
	List<Class> searchByKeyword(String q);

}
