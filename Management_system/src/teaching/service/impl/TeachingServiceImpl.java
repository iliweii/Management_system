package teaching.service.impl;

import java.util.List;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import teacher.dao.TeacherDao;
import teacher.entity.Teacher;
import teaching.dao.TeachingDao;
import teaching.entity.Teaching;
import teaching.service.TeachingService;

public class TeachingServiceImpl implements TeachingService {
	
	private TeachingDao teachingDao;
	private TeacherDao teacherDao;
	
	public TeachingServiceImpl() {
		this.teachingDao = BeanFactory.getInstance(CommonUtils.TeachingInfo.TEACHINGDAO);
		this.teacherDao = BeanFactory.getInstance(CommonUtils.TeacherInfo.TEACHERDAO);
	}

	@Override
	public List<Teaching> queryAll() {
		return this.teachingDao.queryAll();
	}

	@Override
	public int insertTeaching(Teaching teaching) {
		return this.teachingDao.insertTeaching(teaching);
	}

	@Override
	public int insertTeachingBatch(List<Teaching> teachings) {
		return this.teachingDao.insertTeachingBatch(teachings);
	}

	@Override
	public int deleteTeaching(Integer tcid) {
		return this.teachingDao.deleteTeaching(tcid);
	}

	@Override
	public int deleteTeachingsBatch(List<Teaching> teachings) {
		return this.teachingDao.deleteTeachingsBatch(teachings);
	}

	@Override
	public Teaching queryTeachingById(Integer tcid) {
		return this.teachingDao.queryTeachingById(tcid);
	}

	@Override
	public int updateTeaching(Teaching teaching) {
		return this.teachingDao.updateTeaching(teaching);
	}

	@Override
	public int allowTeaching(Teaching teaching) {
		return this.teachingDao.allowTeaching(teaching);
	}

	@Override
	public List<Teaching> searchByKeyword(String q) {
		return this.teachingDao.searchByKeyword(q);
	}

	@Override
	public int allowTeachingsBatch(List<Teaching> teachings) {
		return this.teachingDao.allowTeachingsBatch(teachings);
	}

	@Override
	public List<Teaching> queryTeachingByTid(int id) {
		return this.teachingDao.queryTeachingByTid(id);
	}

	@Override
	public int applyTeachingByTid(String tid, String cno) {
		int id = Integer.parseInt(tid);
		Teacher teacher = this.teacherDao.queryTeacherById(id);
		Teaching teaching = new Teaching();
		teaching.setTno(teacher.getTno());
		teaching.setCno(cno);
		teaching.setStatus(0);
		return this.teachingDao.insertTeaching(teaching);
	}

}
