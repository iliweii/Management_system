package classes.service.impl;

import java.util.List;

import classes.dao.ClassDao;
import classes.entity.Class;
import classes.service.ClassService;
import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import student.dao.StudentDao;

public class ClassServiceImpl implements ClassService {

	private ClassDao classDao;
	private StudentDao studentDao;
	
	public ClassServiceImpl() {
		this.studentDao = BeanFactory.getInstance(CommonUtils.StudentInfo.STUDENTDAO);
		this.classDao = BeanFactory.getInstance(CommonUtils.ClassInfo.CLASSDAO);
	}

	@Override
	public List<Class> queryAll() {
		return this.classDao.queryAll();
	}

	@Override
	public int insertClassBatch(List<Class> classes) {
		return this.classDao.insertClassBatch(classes);
	}

	@Override
	public int deleteClass(Integer cid) {
		// 删除班级，先删除班级里的学生
		this.studentDao.deleteStudentByCid(cid);
		// 再删除班级信息
		return this.classDao.deleteClass(cid);
	}

	@Override
	public int deleteClasssBatch(List<Class> classes) {
		return this.classDao.deleteClasssBatch(classes);
	}

	@Override
	public Class queryClassById(Integer cid) {
		return this.classDao.queryClassById(cid);
	}

	@Override
	public int updateClass(Class aclass) {
		return this.classDao.updateClass(aclass);
	}

	@Override
	public List<Class> searchByKeyword(String q) {
		return this.classDao.searchByKeyword(q);
	}

	@Override
	public Class queryClassByCno(String cno) {
		return this.classDao.queryClassByCno(cno);
	}

}
