$(function () {

    // 获取get传值 tbid
    var tbid = getQueryVariable("tbid");
    if (tbid == false) {
        swal("糟糕", "页面获取失败，无效的管理员信息，请返回重试!", "error");
        return;
    }

    var Tbuser = $("input#Tbuser");
    var Tbname = $("input#Tbname");
    var Tbpwd = $("input#Tbpwd");
    var tbuserFeedback = $("#tbuserFeedback");
    var tbnameFeedback = $("#tbnameFeedback");
    var tbpwdFeedback = $("#tbpwdFeedback");

    var oldtbuser = null;

    // 发送ajax 请求获取数据 
    $.ajax({
        type: "post",
        url: "../../AdminManageQueryServlet",
        data: {
            tbid: tbid
        },
        success: function (e) {
        	
            if (e == "failed") {
                // 查询失败
                swal("糟糕", "管理员信息获取失败，请返回重试!", "error");
            } else {

                var user = JSON.parse(e);

                if (user == null) {
                    // 查询失败
                    swal("糟糕", "管理员信息获取失败，请返回重试!", "error");
                    return;
                }
                // 将数据输出到表单
                Tbuser.val(user['tbuser']);
                Tbname.val(user['tbname']);
                Tbpwd.val(user['tbpwd']);
                oldtbuser = user['tbuser'];
            }
        },
        error: function (e) {
            // 网络或服务器错误
            swal("错误", "网络堵塞或服务器故障!", "error");
        }
    });

    // 管理员登录名输入事件
    Tbuser.keyup(function() {
        Tbuser.removeClass("is-valid");
        tbuserFeedback.removeClass("valid-feedback");
        Tbuser.removeClass("is-invalid");
        tbuserFeedback.removeClass("invalid-feedback");
        tbuserFeedback.text("");
    });

    // 管理员姓名输入事件
    Tbname.keyup(function() {
        Tbname.removeClass("is-valid");
        tbnameFeedback.removeClass("valid-feedback");
        Tbname.removeClass("is-invalid");
        tbnameFeedback.removeClass("invalid-feedback");
        tbnameFeedback.text("");
    });

    // 密码输入事件
    Tbpwd.keyup(function() {
        Tbpwd.removeClass("is-valid");
        tbpwdFeedback.removeClass("valid-feedback");
        Tbpwd.removeClass("is-invalid");
        tbpwdFeedback.removeClass("invalid-feedback");
        tbpwdFeedback.text("");
    });

    // 提交按钮点击事件
    $("#Submitbtn").click(function () {
        // 获取管理员登录名
        var tbuser = Tbuser.val();
        if (tbuser.length == 0) {
            Tbuser.addClass("is-invalid");
            tbuserFeedback.addClass("invalid-feedback");
            tbuserFeedback.text("管理员登录名不能为空!");
            return;
        }
        // 获取姓名
        var tbname = Tbname.val();
        if (tbname.length == 0) {
            Tbname.addClass("is-invalid");
            tbnameFeedback.addClass("invalid-feedback");
            tbnameFeedback.text("管理员姓名不能为空!");
            return;
        }
        // 获取密码
        var tbpwd = Tbpwd.val();
        if (tbpwd.length == 0) {
            Tbpwd.addClass("is-invalid");
            tbpwdFeedback.addClass("invalid-feedback");
            tbpwdFeedback.text("管理员密码不能为空!");
            return;
        }

        // 发送ajax 请求查询用户名是否重复
        $.ajax({
            type: "post",
            url: "../../AdminManageCheckuserServlet",
            data: {
                tbuser: tbuser
            },
            success: function (e) {

                if ((e == "repeat" && e == oldtbuser) || e == "norepeat") {

                    // 发送ajax 请求提交数据
                    $.ajax({
                        type: "post",
                        url: "../../AdminManageUpdateServlet",
                        data: {
                            tbuser: tbuser,
                            tbname: tbname,
                            tbpwd: tbpwd,
                            tbid: tbid
                        },
                        success: function (e) {
                            if (e == "success") {
                                // 修改成功
                                swal("成功", "修改管理员信息成功!", "success");
                                // 延时1s跳转刷新界面
                                setTimeout(() => {
                                    location.reload();
                                }, 1000);
                            } else if (e == "failed") {
                                // 修改失败
                                swal("错误", "修改管理员信息失败!", "error");
                            } else {
                                // 未知错误
                                swal("错误", "出现未知错误!", "error");
                            }
                        }
                    });

                } else {
                    // 登录名重复
                    Tbuser.addClass("is-invalid");
                    tbuserFeedback.addClass("invalid-feedback");
                    tbuserFeedback.text("管理员登录名重复，请更换!");
                }
            },

            error: function (e) {
                // 网络或服务器错误
                swal("错误", "网络堵塞或服务器故障!", "error");
            }

        });

    });



    // 重置按钮点击事件
    $("#Resetbtn").click(function () {
        Tbuser.val("");
        Tbname.val("");
        Tbpwd.val("");
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