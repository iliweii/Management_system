package teaching.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Teaching {
	
	// 授课主码
	private Integer tcid;
	// 教职工号，课程编号
	private String tno, cno, tname, cname, teacher;
	// 选课状态
	private Integer status;
	// 授课用书
	private String book;
}
