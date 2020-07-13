package user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Tbuser {

	// 主码
	private Integer tbid;
	// 管理员姓名
	private String tbname;
	// 管理员登录密码
	private String tbuser, tbpwd;

}
