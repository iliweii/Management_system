$(function () {

    // 数据插入组
    var insertGroup = $(".insert-group");
    
    var tid = getCookie("tid");
    if (!tid) {
    	swal("糟糕", "数据出错，请重新登录！", "error");
    }
    
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

        var Cono = $(".Cono");
        var ConoFeedback = $(".ConoFeedback");

        var i = 0;
        // 检查课程
        var cono = Cono.eq(i).val();
        if (cono == "") {
            Cono.eq(i).addClass("is-invalid");
            ConoFeedback.eq(i).addClass("invalid-feedback");
            ConoFeedback.eq(i).text("请选择课程!");
            return;
        }

        // 发送ajax 提交批量插入请求
        $.ajax({
            type: "post",
            url: "../../ApplyTeachingTeacherServlet",
            data: {
                tid: tid,
                cono: cono
            },
            success: function (e) {
                if (e == "success") {
                    // 插入数据成功
                    swal("成功", "申请课程成功!请等待管理员审核", "success");
                    // 延时1s跳转刷新界面
                    setTimeout(() => {
                        location.replace("./index.jsp");
                    }, 1000);
                } else if (e == "failed") {
                    // 插入数据失败
                    swal("错误", "申请课程失败!", "error");
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
