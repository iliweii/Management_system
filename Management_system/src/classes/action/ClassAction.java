package classes.action;

import java.util.List;

import classes.entity.Class;
import classes.service.ClassService;
import comm.CommonUtils;
import comm.properties.factory.BeanFactory;

public class ClassAction {

	private ClassService classService;
	
	public ClassAction() {
		this.classService = BeanFactory.getInstance(CommonUtils.ClassInfo.CLASSSERVICE);
	}

	public List<Class> queryAll() {
		return this.classService.queryAll();
	}

	public int insertClassBatch(List<Class> classes) {
		return this.classService.insertClassBatch(classes);
	}

	public int deleteClass(Integer cid) {
		return this.classService.deleteClass(cid);
	}

	public int deleteClasssBatch(List<Class> classes) {
		return this.classService.deleteClasssBatch(classes);
	}

	public Class queryClassById(Integer cid) {
		return this.classService.queryClassById(cid);
	}
	 
	public int updateClass(Class aclass) {
		return this.classService.updateClass(aclass);
	}
	 
	public List<Class> searchByKeyword(String q) {
		return this.classService.searchByKeyword(q);
	}
	
	public Class queryClassByCno(String cno) {
		return this.classService.queryClassByCno(cno);
	}

}
