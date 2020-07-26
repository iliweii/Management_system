package user.entity;

public class Tbuser {

	public Integer getTbid() {
		return tbid;
	}
	public void setTbid(Integer tbid) {
		this.tbid = tbid;
	}
	public String getTbname() {
		return tbname;
	}
	public void setTbname(String tbname) {
		this.tbname = tbname;
	}
	public String getTbuser() {
		return tbuser;
	}
	public void setTbuser(String tbuser) {
		this.tbuser = tbuser;
	}
	public String getTbpwd() {
		return tbpwd;
	}
	public void setTbpwd(String tbpwd) {
		this.tbpwd = tbpwd;
	}
	// 主码
	private Integer tbid;
	// 管理员姓名
	private String tbname;
	// 管理员登录密码
	private String tbuser, tbpwd;

}
