package teaching.service;

import java.util.List;

import teaching.entity.Teaching;

public interface TeachingService {
	
	// 查询所有授课信息
	List<Teaching> queryAll();
	
	// 教师申请授课
	int insertTeaching(Teaching teaching);

	// 批量添加授课信息
	int insertTeachingBatch(List<Teaching> teachings);

	// 删除授课信息
	int deleteTeaching(Integer tcid);

	// 批量删除授课信息
	int deleteTeachingsBatch(List<Teaching> teachings);

	// 根据授课id查询授课信息
	Teaching queryTeachingById(Integer tcid);

	// 修改授课信息
	int updateTeaching(Teaching teaching);
	
	// 通过教师授课
	int allowTeaching(Teaching teaching);

	// 根据教职工号、课程号查询授课信息
	List<Teaching> searchByKeyword(String q);

	int allowTeachingsBatch(List<Teaching> teachings);

	List<Teaching> queryTeachingByTid(int id);

	int applyTeachingByTid(String tid, String cno);

}
