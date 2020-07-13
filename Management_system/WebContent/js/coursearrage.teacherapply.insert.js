$(function () {

    // 数据插入组
    var insertGroup = $(".insert-group");
    // +按钮
    var Addbtn = $(".Addbtn");
    // -按钮
    var Cutbtn = $(".Cutbtn");
    // 用于记录+按钮点击次数，确保性别单选按钮不相互影响
    var addBtnClickTime = 0;
    
    // 定义教师信息数组
    var teachers = null;
    // 获取教师信息，存储到数组
    $.ajax({
        type: "post",
        url: "../../TeacherManageListServlet",
        success: function(e) {
        	teachers = JSON.parse(e);
        	// 首先将数据输出到默认首行中
        	for (var i = 0; i < teachers.length; i++) {
        		$("select[name=Tno]").append('\
                	<option value="' + teachers[i]["tno"] + '">' + teachers[i]["tno"] + "  " + teachers[i]["tname"] + '</option>\
                ');
        	}
        }
    });
    // 定义课程信息数组
    var courses = null;
    // 获取课程信息，存储到数组
    $.ajax({
        type: "post",
        url: "../../CourseManageListServlet",
        success: function(e) {
        	courses = JSON.parse(e);
        	// 首先将数据输出到默认首行中
        	for (var i = 0; i < courses.length; i++) {
        		$("select[name=Cno]").append('\
                	<option value="' + courses[i]["cno"] + '">' + courses[i]["cno"] + "  " + courses[i]["cname"] + '</option>\
                ');
        	}
        }
    });

    // +按钮点击事件
    Addbtn.click(function () {
        insertGroup.append('\
        	<div class="insert-item">\
        		<div class="col-md-5 mb-3">\
	                <label>教师 <span class="text-danger">*</span></label>\
	                <select class="form-control Tno" name="Tno' + addBtnClickTime + '">\
				    	<option>--请选择教师--</option>\
				    </select>\
				    <div class="valid-feedback TnoFeedback"></div>\
	            </div>\
	            <div class="col-md-5 mb-3">\
	                <label>课程 <span class="text-danger">*</span></label>\
	                <select class="form-control Cno" name="Cno' + addBtnClickTime++ + '">\
				    	<option>--请选择课程--</option>\
				    </select>\
				    <div class="valid-feedback CnoFeedback"></div>\
	            </div>\
                <svg t="1592634353459" class="icon mx-3 Cutbtn" viewBox="0 0 1024 1024" version="1.1"\
                    xmlns="http://www.w3.org/2000/svg" p-id="2123" width="30" height="30" style="cursor: pointer;">\
                    <path\
                        d="M512 1009.6C236.8 1009.6 14.4 785.6 14.4 512S236.8 14.4 512 14.4s497.6 224 497.6 497.6S787.2 1009.6 512 1009.6z m0-932.8C272 76.8 76.8 272 76.8 512S272 947.2 512 947.2 947.2 752 947.2 512 752 76.8 512 76.8z"\
                        fill="#43484D" p-id="2124"></path>\
                    <path\
                        d="M705.6 556.8H313.6c-17.6 0-32-14.4-32-32s14.4-32 32-32h393.6c17.6 0 32 14.4 32 32s-14.4 32-33.6 32z"\
                        fill="#229BFF" p-id="2125"></path>\
                </svg>\
            </div>\
        ');
        var thisSelectTeacher = $("select[name=Tno" + (addBtnClickTime - 1).toString() + "]");
        for (var i = 0; i < teachers.length; i++) {
        	thisSelectTeacher.append('\
            	<option value="' + teachers[i]["tno"] + '">' + teachers[i]["tno"] + "  " + teachers[i]["tname"] + '</option>\
            ');
    	}
    	var thisSelectCourse = $("select[name=Cno" + (addBtnClickTime - 1).toString() + "]");
    	for (var i = 0; i < courses.length; i++) {
    		thisSelectCourse.append('\
            	<option value="' + courses[i]["cno"] + '">' + courses[i]["cno"] + "  " + courses[i]["cname"] + '</option>\
            ');
    	}
    });

    // - 按钮点击事件
    $("body").on("click", ".Cutbtn", function () {
        var Cutbtn = $(".Cutbtn");
        var index = Cutbtn.index(this);
        var insertItem = $(".insert-item");
        insertItem.eq(index).remove();
    });

    // 提交按钮点击事件
    $("#Submitbtn").click(function () {

        var insertItem = $(".insert-item");
        var Tno = $(".Tno");
        var Cno = $(".Cno");
        var TnoFeedback = $(".TnoFeedback");
        var CnoFeedback = $(".CnoFeedback");

        // 检查数据长度
        if (insertItem.length == 0) {
            swal("糟糕", "没有添加任何课程信息!", "warning");
            return;
        }

        // 检查所有的课程号，名称
        for (var i = 0; i < insertItem.length; i++) {
        	var tno = Tno.eq(i).val();
            if (tno.length == 0) {
                Tno.eq(i).addClass("is-invalid");
                TnoFeedback.eq(i).addClass("invalid-feedback");
                TnoFeedback.eq(i).text("请选择教师");
                return;
            }
            var cno = Cno.eq(i).val();
            if (cno.length == 0) {
                Cno.eq(i).addClass("is-invalid");
                CnoFeedback.eq(i).addClass("invalid-feedback");
                CnoFeedback.eq(i).text("请选择课程!");
                return;
            }        
        }

        var Teaching = new Array();
        // 将数据保存至数组
        for (var i = 0; i < insertItem.length; i++) {
            // 获取教师编号
            var tno = Tno.eq(i).val();
            // 获取课程编号
            var cno = Cno.eq(i).val();
            // 将获取到的数据依次写入Teaching[i]中
            Teaching[i] = new Object();
            Teaching[i].tno = tno;
            Teaching[i].cno = cno;
        }

        // 将数据转化为json数据
        var teaching = JSON.stringify(Teaching);

        // 发送ajax 提交批量插入请求
        $.ajax({
            type: "post",
            url: "../../TeacherApplyInsertServlet",
            data: {
                json: teaching
            },
            success: function (e) {
                if (e == "success") {
                    // 插入数据成功
                    swal("成功", "新增授课信息成功!", "success");
                    // 延时1s跳转刷新界面
                    setTimeout(() => {
                        location.replace("./index.jsp");
                    }, 1000);
                } else if (e == "failed") {
                    // 插入数据失败
                    swal("错误", "批量新增授课信息失败!", "error");
                } else {
                    // 未知错误
                    swal("错误", "出现未知错误!", "error");
                }
            },

            error: function (e) {
                // 网络或服务器错误
                swal("错误", "网络堵塞或服务器故障!", "error");
            }

        });

    });


});
