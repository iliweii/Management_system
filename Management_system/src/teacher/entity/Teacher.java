package teacher.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Teacher {
	
	// 主码
	private Integer tid;
	// 教师号，姓名，密码，邮箱，手机号
	private String tno, tname, tpwd, email, phone;

}
