package comm;

public class CommonUtils {

	// 匿名内部类的语法，接口
	public interface AdminInfo {
		// 定义一个常量
		String ADMINDAO = "managesys.admindao";
		String ADMINSERVICE = "managesys.adminservice";
		String ADMINACTION = "managesys.adminaction";
	}
	
	public interface UserInfo {
		String USERDAO = "managesys.userdao";
		String USERSERVICE = "managesys.userservice";
		String USERACTION = "managesys.useraction";
	}
	
	public interface StudentInfo {
		String STUDENTDAO = "managesys.studentdao";
		String STUDENTSERVICE = "managesys.studentservice";
		String STUDENTACTION = "managesys.studentaction";
	}
	
	public interface TeacherInfo {
		String TEACHERDAO = "managesys.teacherdao";
		String TEACHERSERVICE = "managesys.teacherservice";
		String TEACHERACTION = "managesys.teacheraction";
	}
	
	public interface ClassInfo {
		String CLASSDAO = "managesys.classdao";
		String CLASSSERVICE = "managesys.classservice";
		String CLASSACTION = "managesys.classaction";
	}
	
	public interface CourseInfo {
		String COURSEDAO = "managesys.coursedao";
		String COURSESERVICE = "managesys.courseservice";
		String COURSEACTION = "managesys.courseaction";
	}
	
	public interface ChooseInfo {
		String CHOOSEDAO = "managesys.choosedao";
		String CHOOSESERVICE = "managesys.chooseservice";
		String CHOOSEACTION = "managesys.chooseaction";
	}
	
	public interface TeachingInfo {
		String TEACHINGDAO = "managesys.teachingdao";
		String TEACHINGSERVICE = "managesys.teachingservice";
		String TEACHINGACTION = "managesys.teachingaction";
	}
	
}
