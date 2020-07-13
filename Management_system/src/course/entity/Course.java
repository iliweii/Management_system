package course.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Course {
	
	// 课程主码
	private Integer cid;
	// 课程编码，课程名称
	private String cno, cname;
	// 课程人数
	private Integer num;
	
}
