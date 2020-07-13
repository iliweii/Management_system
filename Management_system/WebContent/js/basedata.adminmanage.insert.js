$(function () {

    // 数据插入组
    var insertGroup = $(".insert-group");
    // +按钮
    var Addbtn = $(".Addbtn");
    // -按钮
    var Cutbtn = $(".Cutbtn");

    // +按钮点击事件
    Addbtn.click(function () {
        insertGroup.append('\
            <div class="insert-item">\
                <div class="col-md-3 mb-3">\
                    <label for="Tbuser">管理员登录名 <span class="text-danger">*</span></label>\
                    <input type="text" class="form-control Tbuser" required>\
                    <div class="valid-feedback tbuserFeedback"></div>\
                </div>\
                <div class="col-md-3 mb-3">\
                    <label for="Tbname">管理员姓名 <span class="text-danger">*</span></label>\
                    <input type="text" class="form-control Tbname" required>\
                    <div class="valid-feedback tbnameFeedback"></div>\
                </div>\
                <div class="col-md-3 mb-3">\
                    <label for="Tbpwd">管理员密码 <span class="text-danger">*</span></label>\
                    <input type="password" class="form-control Tbpwd" required>\
                    <div class="invalid-feedback tbpwdFeedback"></div>\
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

    // 管理员登录名输入事件
    $("body").on("keyup", ".Tbuser", function () {
        var Tbuser = $(".Tbuser");
        var tbuserFeedback = $(".tbuserFeedback");
        var index = Tbuser.index(this);
        Tbuser.eq(index).removeClass("is-valid");
        tbuserFeedback.eq(index).removeClass("valid-feedback");
        Tbuser.eq(index).removeClass("is-invalid");
        tbuserFeedback.eq(index).removeClass("invalid-feedback");
        tbuserFeedback.eq(index).text("");
    });

    // 管理员姓名输入事件
    $("body").on("keyup", ".Tbname", function () {
        var Tbname = $(".Tbname");
        var tbnameFeedback = $(".tbnameFeedback");
        var index = Tbname.index(this);
        Tbname.eq(index).removeClass("is-valid");
        tbnameFeedback.eq(index).removeClass("valid-feedback");
        Tbname.eq(index).removeClass("is-invalid");
        tbnameFeedback.eq(index).removeClass("invalid-feedback");
        tbnameFeedback.eq(index).text("");
    });

    // 密码输入事件
    $("body").on("keyup", ".Tbpwd", function () {
        var Tbpwd = $(".Tbpwd");
        var tbpwdFeedback = $(".tbpwdFeedback");
        var index = Tbpwd.index(this);
        Tbpwd.eq(index).removeClass("is-valid");
        tbpwdFeedback.eq(index).removeClass("valid-feedback");
        Tbpwd.eq(index).removeClass("is-invalid");
        tbpwdFeedback.eq(index).removeClass("invalid-feedback");
        tbpwdFeedback.eq(index).text("");
    });

    // 提交按钮点击事件
    $("#Submitbtn").click(function () {

        var insertItem = $(".insert-item");
        var Tbuser = $(".Tbuser");
        var Tbname = $(".Tbname");
        var Tbpwd = $(".Tbpwd");

        var tbuserFeedback = $(".tbuserFeedback");
        var tbnameFeedback = $(".tbnameFeedback");
        var tbpwdFeedback = $(".tbpwdFeedback");

        // 检查数据长度
        if (insertItem.length == 0) {
            swal("糟糕", "没有添加任何管理员信息!", "warning");
            return;
        }

        // 检查所有的管理员登录名，姓名，密码
        for (var i = 0; i < insertItem.length; i++) {
            // 获取登录名
            var tbuser = Tbuser.eq(i).val();
            if (tbuser.length == 0) {
                Tbuser.eq(i).addClass("is-invalid");
                tbuserFeedback.eq(i).addClass("invalid-feedback");
                tbuserFeedback.eq(i).text("管理员登录名不能为空!");
                return;
            }
            // 获取姓名
            var tbname = Tbname.eq(i).val();
            if (tbname.length == 0) {
                Tbname.eq(i).addClass("is-invalid");
                tbnameFeedback.eq(i).addClass("invalid-feedback");
                tbnameFeedback.eq(i).text("管理员姓名不能为空!");
                return;
            }
            // 获取密码
            var tbpwd = Tbpwd.eq(i).val();
            if (tbpwd.length == 0) {
                Tbpwd.eq(i).addClass("is-invalid");
                tbpwdFeedback.eq(i).addClass("invalid-feedback");
                tbpwdFeedback.eq(i).text("管理员密码不能为空!");
                return;
            }
        }

        // 检查所有用户名是否重复
        // TODO 

        var User = new Array();
        // 将数据保存至数组
        for (var i = 0; i < insertItem.length; i++) {
            // 获取登录名
            var tbuser = Tbuser.eq(i).val();
            // 获取姓名
            var tbname = Tbname.eq(i).val();
            // 获取密码
            var tbpwd = Tbpwd.eq(i).val();
            User[i] = new Object();
            User[i].tbuser = tbuser;
            User[i].tbname = tbname;
            User[i].tbpwd = tbpwd;
        }

        // 将数据转化为json数据
        var user = JSON.stringify(User);

        // 发送ajax 提交批量插入请求
        $.ajax({
            type: "post",
            url: "../../AdminManageInsertServlet",
            data: {
                json: user
            },
            success: function (e) {
                if (e == "success") {
                    // 插入数据成功
                    swal("成功", "新增管理员信息成功!", "success");
                    // 延时1s跳转刷新界面
                    setTimeout(() => {
                        location.replace("./index.jsp");
                    }, 1000);
                } else if (e == "failed") {
                    // 插入数据失败
                    swal("错误", "批量新增管理员信息失败!", "error");
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
