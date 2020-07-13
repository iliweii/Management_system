$(function () {

    // 数据插入组
    var insertGroup = $(".insert-group");
    // +按钮
    var Addbtn = $(".Addbtn");
    // -按钮
    var Cutbtn = $(".Cutbtn");
    // 用于记录+按钮点击次数，确保性别单选按钮不相互影响
    var addBtnClickTime = 0;

    // +按钮点击事件
    Addbtn.click(function () {
        insertGroup.append('\
        	<div class="insert-item">\
                <div class="col-md-2 mb-3">\
                    <label>教职工号 <span class="text-danger">*</span></label>\
                    <input type="text" class="form-control Tno" required>\
                    <div class="valid-feedback SnoFeedback"></div>\
                </div>\
                <div class="col-md-2 mb-3">\
                    <label>姓名 <span class="text-danger">*</span></label>\
                    <input type="text" class="form-control Tname" required>\
                    <div class="valid-feedback SnameFeedback"></div>\
                </div>\
                <div class="col-md-2 mb-3">\
                    <label>密码 <span class="text-danger">*</span></label>\
                    <input type="password" class="form-control Tpwd" required>\
                    <div class="invalid-feedback SpwdFeedback"></div>\
                </div>\
                <div class="col-md-2 mb-3">\
                    <label>邮箱</label>\
                    <input type="text" class="form-control Email">\
                </div>\
                <div class="col-md-2 mb-3">\
                    <label>手机号</label>\
                    <input type="text" class="form-control Phone">\
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
    });

    // - 按钮点击事件
    $("body").on("click", ".Cutbtn", function () {
        var Cutbtn = $(".Cutbtn");
        var index = Cutbtn.index(this);
        var insertItem = $(".insert-item");
        insertItem.eq(index).remove();
    });

    // 教职工号输入事件
    $("body").on("keyup", ".Tno", function () {
        var Tno = $(".Tno");
        var TnoFeedback = $(".TnoFeedback");
        var index = Tno.index(this);
        Tno.eq(index).removeClass("is-valid");
        TnoFeedback.eq(index).removeClass("valid-feedback");
        Tno.eq(index).removeClass("is-invalid");
        TnoFeedback.eq(index).removeClass("invalid-feedback");
        TnoFeedback.eq(index).text("");
    });

    // 姓名输入事件
    $("body").on("keyup", ".Tname", function () {
        var Tname = $(".Tname");
        var TnameFeedback = $(".TnameFeedback");
        var index = Tname.index(this);
        Tname.eq(index).removeClass("is-valid");
        TnameFeedback.eq(index).removeClass("valid-feedback");
        Tname.eq(index).removeClass("is-invalid");
        TnameFeedback.eq(index).removeClass("invalid-feedback");
        TnameFeedback.eq(index).text("");
    });

    // 密码输入事件
    $("body").on("keyup", ".Tpwd", function () {
        var Tpwd = $(".Tpwd");
        var TpwdFeedback = $(".TpwdFeedback");
        var index = Tpwd.index(this);
        Tpwd.eq(index).removeClass("is-valid");
        TpwdFeedback.eq(index).removeClass("valid-feedback");
        Tpwd.eq(index).removeClass("is-invalid");
        TpwdFeedback.eq(index).removeClass("invalid-feedback");
        TpwdFeedback.eq(index).text("");
    });

    // 提交按钮点击事件
    $("#Submitbtn").click(function () {

        var insertItem = $(".insert-item");
        var Tno = $(".Tno");
        var Tname = $(".Tname");
        var Tpwd = $(".Tpwd");
        var Email = $(".Email");
        var Phone = $(".Phone");

        var TnoFeedback = $(".TnoFeedback");
        var TnameFeedback = $(".TnameFeedback");
        var TpwdFeedback = $(".TpwdFeedback");

        // 检查数据长度
        if (insertItem.length == 0) {
            swal("糟糕", "没有添加任何教师信息!", "warning");
            return;
        }

        // 检查所有的名，姓名，密码
        for (var i = 0; i < insertItem.length; i++) {
            // 获取教职工号
            var tno = Tno.eq(i).val();
            if (tno.length == 0) {
                Tno.eq(i).addClass("is-invalid");
                TnoFeedback.eq(i).addClass("invalid-feedback");
                TnoFeedback.eq(i).text("教职工号不能为空!");
                return;
            }
            // 获取姓名
            var tname = Tname.eq(i).val();
            if (tname.length == 0) {
                Tname.eq(i).addClass("is-invalid");
                TnameFeedback.eq(i).addClass("invalid-feedback");
                TnameFeedback.eq(i).text("姓名不能为空!");
                return;
            }
            // 获取密码
            var tpwd = Tpwd.eq(i).val();
            if (tpwd.length == 0) {
                Tpwd.eq(i).addClass("is-invalid");
                TpwdFeedback.eq(i).addClass("invalid-feedback");
                TpwdFeedback.eq(i).text("密码不能为空!");
                return;
            }            
        }

        var Student = new Array();
        // 将数据保存至数组
        for (var i = 0; i < insertItem.length; i++) {
            // 获取教职工号
            var tno = Tno.eq(i).val();
            // 获取姓名
            var tname = Tname.eq(i).val();
            // 获取密码
            var tpwd = Tpwd.eq(i).val();
            // 获取邮箱
            var email = Email.eq(i).val();
            // 获取手机号
            var phone = Phone.eq(i).val();
            // 将获取到的数据依次写入Teacher[i]中
            Teacher[i] = new Object();
            Teacher[i].sno = sno;
            Teacher[i].sname = sname;
            Teacher[i].spwd = spwd;
            Teacher[i].email = email;
            Teacher[i].phone = phone;
        }

        // 将数据转化为json数据
        var teacher = JSON.stringify(Teacher);

        // 发送ajax 提交批量插入请求
        $.ajax({
            type: "post",
            url: "../../TeacherManageInsertServlet",
            data: {
                json: teacher
            },
            success: function (e) {
                if (e == "success") {
                    // 插入数据成功
                    swal("成功", "新增教师信息成功!", "success");
                    // 延时1s跳转刷新界面
                    setTimeout(() => {
                        location.replace("./index.jsp");
                    }, 1000);
                } else if (e == "failed") {
                    // 插入数据失败
                    swal("错误", "批量新增教师信息失败!", "error");
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
