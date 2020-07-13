package choose.action;

import java.util.List;

import choose.entity.Choose;
import choose.service.ChooseService;
import comm.CommonUtils;
import comm.properties.factory.BeanFactory;

public class ChooseAction implements ChooseService {
	
	private ChooseService chooseService;
	
	public ChooseAction() {
		this.chooseService = BeanFactory.getInstance(CommonUtils.ChooseInfo.CHOOSESERVICE);
	}

	@Override
	public List<Choose> queryAll() {
		return this.chooseService.queryAll();
	}

	@Override
	public int insertChoose(Choose choose) {
		return this.chooseService.insertChoose(choose);
	}

	@Override
	public int insertChooseBatch(List<Choose> chooses) {
		return this.chooseService.insertChooseBatch(chooses);
	}

	@Override
	public int deleteChoose(Integer scid) {
		return this.chooseService.deleteChoose(scid);
	}

	@Override
	public int deleteChoosesBatch(List<Choose> chooses) {
		return this.chooseService.deleteChoosesBatch(chooses);
	}

	@Override
	public Choose queryChooseById(Integer scid) {
		return this.chooseService.queryChooseById(scid);
	}

	@Override
	public int updateChoose(Choose choose) {
		return this.chooseService.updateChoose(choose);
	}

	@Override
	public int allowChoose(Choose choose) {
		return this.chooseService.allowChoose(choose);
	}

	@Override
	public List<Choose> searchByKeyword(String q) {
		return this.chooseService.searchByKeyword(q);
	}

	public int allowChoosesBatch(List<Choose> chooses) {
		return this.chooseService.allowChoosesBatch(chooses);
	}

	public int publicChoose(String tno, String clno, String cono) {
		return this.chooseService.publicChoose(tno, clno, cono);
	}

	public List<Choose> queryAllGrade() {
		return this.chooseService.queryAllGrade();
	}

	public int updateChoosesBatch(List<Choose> chooses) {
		return this.chooseService.updateChoosesBatch(chooses);
	}

	public List<Choose> queryAllGradeByQ(String q) {
		return this.chooseService.queryAllGradeByQ(q);
	}

	public List<Choose> queryAllGradeByClno(String clno) {
		return this.chooseService.queryAllGradeByClno(clno);
	}

	public List<Choose> queryAllGradeByCono(String cono) {
		return this.chooseService.queryAllGradeByCono(cono);
	}

	public List<Choose> queryAllGradeBySid(String sid) {
		return this.chooseService.queryAllGradeBySid(sid);
	}

	public List<Choose> queryAllChooseBySid(String sid) {
		return this.chooseService.queryAllChooseBySid(sid);
	}

	public int applyChooseBySid(String sid, String cno) {
		return this.chooseService.applyChooseBySid(sid, cno);
	}

	public List<Choose> queryAllGradeByTid(String tid) {
		return this.chooseService.queryAllGradeByTid(tid);
	}

	public List<Choose> searchAllGradeByTid(String tid, String q) {
		return this.chooseService.searchAllGradeByTid(tid, q);
	}

}
