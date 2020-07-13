package teaching.action;

import java.util.List;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import teaching.service.TeachingService;
import teaching.entity.Teaching;

public class TeachingAction {
	
	private TeachingService teachingService;
	
	public TeachingAction() {
		this.teachingService = BeanFactory.getInstance(CommonUtils.TeachingInfo.TEACHINGSERVICE);
	}

	public List<Teaching> queryAll() {
		return this.teachingService.queryAll();
	}

	public int insertTeaching(Teaching teaching) {
		return this.teachingService.insertTeaching(teaching);
	}

	public int insertTeachingBatch(List<Teaching> teachings) {
		return this.teachingService.insertTeachingBatch(teachings);
	}

	public int deleteTeaching(Integer tcid) {
		return this.teachingService.deleteTeaching(tcid);
	}

	public int deleteTeachingsBatch(List<Teaching> teachings) {
		return this.teachingService.deleteTeachingsBatch(teachings);
	}

	public Teaching queryTeachingById(Integer tcid) {
		return this.teachingService.queryTeachingById(tcid);
	}

	public int updateTeaching(Teaching teaching) {
		return this.teachingService.updateTeaching(teaching);
	}

	public int allowTeaching(Teaching teaching) {
		return this.teachingService.allowTeaching(teaching);
	}

	public List<Teaching> searchByKeyword(String q) {
		return this.teachingService.searchByKeyword(q);
	}

	public int allowTeachingsBatch(List<Teaching> teachings) {
		return this.teachingService.allowTeachingsBatch(teachings);
	}

	public List<Teaching> queryTeachingByTid(int id) {
		return this.teachingService.queryTeachingByTid(id);
	}

	public int applyTeachingByTid(String tid, String cno) {
		return this.teachingService.applyTeachingByTid(tid, cno);
	}

}
