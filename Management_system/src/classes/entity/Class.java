package classes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Class {
	
	// 编号
	private Integer cid;
	// 班级编码，名称，所属学院
	private String cno, cname, college;
	// 班级人数
	private Integer num;
	
}
