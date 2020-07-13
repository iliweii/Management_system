package admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Admin {

	// 主码
	private Integer aid;
	// 登录名
	private String aname;
	// 登录密码
	private String apwd;

}
