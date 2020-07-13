package choose.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Choose {
	
	// 选课主码
	private Integer scid;
	// 学号，课程编号, 学生姓名，课程名称
	private String sno, cno, sname, cname;
	// 班级，任课教师
	private String clid, clno, clname, teacher;
	// 选课状态
	private Integer status;
	// 成绩
	private double grade;
}
