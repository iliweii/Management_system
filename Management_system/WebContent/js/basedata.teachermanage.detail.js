$(function () {

    // 获取get传值 tid
    var tid = getQueryVariable("tid");
    if (tid == false) {
        swal("糟糕", "页面获取失败，无效的教师信息，请返回重试!", "error");
        return;
    }

    var Tno = $("input#Tno");
    var Tname = $("input#Tname");
    var Tpwd = $("input#Tpwd");
    var Email = $("input#Email");
    var Phone = $("input#Phone");
    var TnoFeedback = $("#TnoFeedback");
    var TnameFeedback = $("#TnameFeedback");
    var TpwdFeedback = $("#TpwdFeedback");

    // 发送ajax 请求获取数据
    $.ajax({
        type: "post",
        url: "../../TeacherManageQueryServlet",
        data: {
            tid: tid
        },
        success: function (e) {
        	
            if (e == "failed") {
                // 查询失败
                swal("糟糕", "教师信息获取失败，请返回重试!", "error");
            } else {

                var teacher = JSON.parse(e);

                if (teacher == null) {
                    // 查询失败
                    swal("糟糕", "教师信息获取失败，请返回重试!", "error");
                    return;
                }
                // 将数据输出到表单
                Tno.val(teacher['tno']);
                Tname.val(teacher['tname']);
                Tpwd.val(teacher['tpwd']);
                Email.val(teacher['email']);
                Phone.val(teacher['phone']);
            }
        },
        error: function (e) {
            // 网络或服务器错误
            swal("错误", "网络堵塞或服务器故障!", "error");
        }
    });

    // 教职工号输入事件
    Tno.keyup(function() {
        Tno.removeClass("is-valid");
        TnoFeedback.removeClass("valid-feedback");
        Tno.removeClass("is-invalid");
        TnoFeedback.removeClass("invalid-feedback");
        TnoFeedback.text("");
    });

    // 姓名输入事件
    Tname.keyup(function() {
        Tname.removeClass("is-valid");
        TnameFeedback.removeClass("valid-feedback");
        Tname.removeClass("is-invalid");
        TnameFeedback.removeClass("invalid-feedback");
        TnameFeedback.text("");
    });

    // 密码输入事件
    Tpwd.keyup(function() {
        Tpwd.removeClass("is-valid");
        TpwdFeedback.removeClass("valid-feedback");
        Tpwd.removeClass("is-invalid");
        TpwdFeedback.removeClass("invalid-feedback");
        TpwdFeedback.text("");
    });

    // 提交按钮点击事件
    $("#Submitbtn").click(function () {
        // 获取教职工号
        var tno = Tno.val();
        if (tno.length == 0) {
            Tno.addClass("is-invalid");
            TnoFeedback.addClass("invalid-feedback");
            TnoFeedback.text("教职工号不能为空!");
            return;
        }
        // 获取姓名
        var tname = Tname.val();
        if (tname.length == 0) {
            Tname.addClass("is-invalid");
            TnameFeedback.addClass("invalid-feedback");
            TnameFeedback.text("姓名不能为空!");
            return;
        }
        // 获取密码
        var tpwd = Tpwd.val();
        if (tpwd.length == 0) {
            Tpwd.addClass("is-invalid");
            TpwdFeedback.addClass("invalid-feedback");
            TpwdFeedback.text("密码不能为空!");
            return;
        }
        // 获取邮箱
        var email = Email.val();
        // 获取手机号
        var phone = Phone.val();
        // 将获取到的数据依次写入Teacher中
        var Teacher = new Object();
        Teacher.tid = tid;
        Teacher.tno = tno;
        Teacher.tname = tname;
        Teacher.tpwd = tpwd;
        Teacher.email = email;
        Teacher.phone = phone;
        
        // 将数据转化为json数据
        var json = JSON.stringify(Teacher);
        
        // 发送ajax 请求提交数据
        $.ajax({
            type: "post",
            url: "../../TeacherManageUpdateServlet",
            data: {
            	json: json
            },
            success: function (e) {
                if (e == "success") {
                    // 修改成功
                    swal("成功", "修改教师信息成功!", "success");
                    // 延时1s跳转刷新界面
                    setTimeout(() => {
                        location.reload();
                    }, 1000);
                } else if (e == "failed") {
                    // 修改失败
                    swal("错误", "修改教师信息失败!", "error");
                } else {
                    // 未知错误
                    swal("错误", "出现未知错误!", "error");
                }
            }
        });
    });


    // 重置按钮点击事件
    $("#Resetbtn").click(function () {
        Tno.val("");
        Tname.val("");
        Tpwd.val("");
        Email.val("");
        Phone.val("");
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