$(function () {

    // 获取get传值 tid
    var cid = getQueryVariable("cid");
    if (cid == false) {
        swal("糟糕", "页面获取失败，无效的班级信息，请返回重试!", "error");
        return;
    }

    var Cno = $("input#Cno");
    var Cname = $("input#Cname");
    var College = $("input#College");
    var CnoFeedback = $("#CnoFeedback");
    var CnameFeedback = $("#CnameFeedback");
    var CollegeFeedback = $("#CollegeFeedback");

    // 发送ajax 请求获取数据
    $.ajax({
        type: "post",
        url: "../../ClassManageQueryServlet",
        data: {
            cid: cid
        },
        success: function (e) {
        	
            if (e == "failed") {
                // 查询失败
                swal("糟糕", "班级信息获取失败，请返回重试!", "error");
            } else {

                var classes = JSON.parse(e);

                if (classes == null) {
                    // 查询失败
                    swal("糟糕", "班级信息获取失败，请返回重试!", "error");
                    return;
                }
                // 将数据输出到表单
                Cno.val(classes['cno']);
                Cname.val(classes['cname']);
                College.val(classes['college']);
            }
        },
        error: function (e) {
            // 网络或服务器错误
            swal("错误", "网络堵塞或服务器故障!", "error");
        }
    });

    // 班级编号输入事件
    Cno.keyup(function() {
        Cno.removeClass("is-valid");
        CnoFeedback.removeClass("valid-feedback");
        Cno.removeClass("is-invalid");
        CnoFeedback.removeClass("invalid-feedback");
        CnoFeedback.text("");
    });

    // 班级名称输入事件
    Cname.keyup(function() {
        Cname.removeClass("is-valid");
        CnameFeedback.removeClass("valid-feedback");
        Cname.removeClass("is-invalid");
        CnameFeedback.removeClass("invalid-feedback");
        CnameFeedback.text("");
    });

    // 所属学院输入事件
    College.keyup(function() {
    	College.removeClass("is-valid");
    	CollegeFeedback.removeClass("valid-feedback");
    	College.removeClass("is-invalid");
    	CollegeFeedback.removeClass("invalid-feedback");
    	CollegeFeedback.text("");
    });

    // 提交按钮点击事件
    $("#Submitbtn").click(function () {
        // 获取班级编号
        var cno = Cno.val();
        if (cno.length == 0) {
            Cno.addClass("is-invalid");
            CnoFeedback.addClass("invalid-feedback");
            CnoFeedback.text("班级编号不能为空!");
            return;
        }
        // 获取姓名
        var cname = Cname.val();
        if (cname.length == 0) {
            Cname.addClass("is-invalid");
            CnameFeedback.addClass("invalid-feedback");
            CnameFeedback.text("姓名不能为空!");
            return;
        }
        // 获取所属学院
        var college = College.val();
        if (college.length == 0) {
            College.addClass("is-invalid");
            CollegeFeedback.addClass("invalid-feedback");
            CollegeFeedback.text("所属学院不能为空!");
            return;
        }
        // 将获取到的数据依次写入Class中
        var Class = new Object();
        Class.cid = cid;
        Class.cno = cno;
        Class.cname = cname;
        Class.college = college;
        
        // 将数据转化为json数据
        var json = JSON.stringify(Class);
        
        // 发送ajax 请求提交数据
        $.ajax({
            type: "post",
            url: "../../ClassManageUpdateServlet",
            data: {
            	json: json
            },
            success: function (e) {
                if (e == "success") {
                    // 修改成功
                    swal("成功", "修改班级信息成功!", "success");
                    // 延时1s跳转刷新界面
                    setTimeout(() => {
                        location.reload();
                    }, 1000);
                } else if (e == "failed") {
                    // 修改失败
                    swal("错误", "修改班级信息失败!", "error");
                } else {
                    // 未知错误
                    swal("错误", "出现未知错误!", "error");
                }
            }
        });
    });


    // 重置按钮点击事件
    $("#Resetbtn").click(function () {
        Cno.val("");
        Cname.val("");
        College.val("");
    });

});

// 获取get数据
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return (false);
}