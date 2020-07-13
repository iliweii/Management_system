$(function () {

    // 数据插入组
    var insertGroup = $(".insert-group");
    
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
    // 定义班级信息数组
    var classes = null;
    // 获取班级信息，存储到数组
    $.ajax({
        type: "post",
        url: "../../ClassManageListServlet",
        success: function(e) {
        	classes = JSON.parse(e);
        	// 首先将数据输出到默认首行中
        	for (var i = 0; i < classes.length; i++) {
        		$("select[name=Clno]").append('\
                	<option value="' + classes[i]["cno"] + '">' + classes[i]["cno"] + "  " + classes[i]["cname"] + '</option>\
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
        		$("select[name=Cono]").append('\
                	<option value="' + courses[i]["cno"] + '">' + courses[i]["cno"] + "  " + courses[i]["cname"] + '</option>\
                ');
        	}
        }
    });

    // 提交按钮点击事件
    $("#Submitbtn").click(function () {

        var insertItem = $(".insert-item");
        var Tno = $(".Tno");
        var Clno = $(".Clno");
        var Cono = $(".Cono");
        var TnoFeedback = $(".TnoFeedback");
        var ClnoFeedback = $(".ClnoFeedback");
        var ConoFeedback = $(".ConoFeedback");

        // 检查数据长度
        if (insertItem.length == 0) {
            swal("糟糕", "没有添加任何公选课信息!", "warning");
            return;
        }

        var i = 0;
        // 检查教师、班级、课程
    	var tno = Tno.eq(i).val();
        if (tno.length == 0) {
            Tno.eq(i).addClass("is-invalid");
            TnoFeedback.eq(i).addClass("invalid-feedback");
            TnoFeedback.eq(i).text("请选择教师");
            return;
        }
        var clno = Clno.eq(i).val();
        if (clno.length == 0) {
            Clno.eq(i).addClass("is-invalid");
            ClnoFeedback.eq(i).addClass("invalid-feedback");
            ClnoFeedback.eq(i).text("请选择班级!");
            return;
        }
        var cono = Cono.eq(i).val();
        if (cono.length == 0) {
            Cono.eq(i).addClass("is-invalid");
            ConoFeedback.eq(i).addClass("invalid-feedback");
            ConoFeedback.eq(i).text("请选择课程!");
            return;
        }

        // 发送ajax 提交批量插入请求
        $.ajax({
            type: "post",
            url: "../../PublicApplyInsertServlet",
            data: {
                tno: tno,
                clno: clno,
                cono: cono
            },
            success: function (e) {
                if (e == "success") {
                    // 插入数据成功
                    swal("成功", "新增公选课信息成功!", "success");
                    // 延时1s跳转刷新界面
                    setTimeout(() => {
                        location.replace("./index.jsp");
                    }, 1000);
                } else if (e == "failed") {
                    // 插入数据失败
                    swal("错误", "批量新增公选课信息失败!", "error");
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
