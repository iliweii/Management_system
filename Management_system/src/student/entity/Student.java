package student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Student {

	// 主码
	private Integer sid;
	// 学号
	private String sno;
	// 班级编号, 名称
	private String cno, cname;
	// 姓名
	private String sname;
	// 密码
	private String spwd;
	// 性别
	private Integer sex;
	// 年龄
	private Integer sage;
	// 邮箱
	private String email;
	// 手机号
	private String phone;

}
