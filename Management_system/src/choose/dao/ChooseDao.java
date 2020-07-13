package choose.dao;

import java.util.List;

import choose.entity.Choose;
import student.entity.Student;

public interface ChooseDao {
	
	// 查询所有选课信息
	List<Choose> queryAll();
	
	// 学生申请选课
	int insertChoose(Choose choose);

	// 批量添加选课信息
	int insertChooseBatch(List<Choose> chooses);

	// 删除选课信息
	int deleteChoose(Integer scid);

	// 批量删除选课信息
	int deleteChoosesBatch(List<Choose> chooses);

	// 根据选课id查询选课信息
	Choose queryChooseById(Integer scid);

	// 修改选课信息
	int updateChoose(Choose choose);
	
	// 通过学生选课
	int allowChoose(Choose choose);

	// 根据学号、课程号查询选课信息
	List<Choose> searchByKeyword(String q);

	int allowChooseBatch(List<Choose> chooses);

	int insertChooseBatchPublic(List<Student> student, String cno);

	List<Choose> queryAllGrade();

	int updateChoosesBatch(List<Choose> chooses);

	List<Choose> queryAllGradeByQ(String q);

	List<Choose> queryAllGradeByClno(String clno);

	List<Choose> queryAllGradeByCono(String cono);

	List<Choose> queryAllGradeBySid(String sid);

	List<Choose> queryAllChooseBySid(String sid);

	List<Choose> queryAllGradeByTid(String tid);

	List<Choose> searchAllGradeByTid(String tid, String q);

}
