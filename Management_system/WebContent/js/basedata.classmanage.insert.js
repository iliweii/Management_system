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
                <div class="col-md-3 mb-3">\
                    <label>班级编号 <span class="text-danger">*</span></label>\
                    <input type="text" class="form-control Cno" required>\
                    <div class="valid-feedback CnoFeedback"></div>\
                </div>\
                <div class="col-md-3 mb-3">\
                    <label>班级名称 <span class="text-danger">*</span></label>\
                    <input type="text" class="form-control Cname" required>\
                    <div class="valid-feedback CnameFeedback"></div>\
                </div>\
                <div class="col-md-4 mb-3">\
                    <label>所属学院<span class="text-danger">*</span></label>\
                    <input type="text" class="form-control College" required>\
                    <div class="invalid-feedback CollegeFeedback"></div>\
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

    // 班级编号输入事件
    $("body").on("keyup", ".Cno", function () {
        var Cno = $(".Cno");
        var CnoFeedback = $(".CnoFeedback");
        var index = Cno.index(this);
        Cno.eq(index).removeClass("is-valid");
        CnoFeedback.eq(index).removeClass("valid-feedback");
        Cno.eq(index).removeClass("is-invalid");
        CnoFeedback.eq(index).removeClass("invalid-feedback");
        CnoFeedback.eq(index).text("");
    });

    // 班级名称输入事件
    $("body").on("keyup", ".Cname", function () {
        var Cname = $(".Cname");
        var CnameFeedback = $(".CnameFeedback");
        var index = Cname.index(this);
        Cname.eq(index).removeClass("is-valid");
        CnameFeedback.eq(index).removeClass("valid-feedback");
        Cname.eq(index).removeClass("is-invalid");
        CnameFeedback.eq(index).removeClass("invalid-feedback");
        CnameFeedback.eq(index).text("");
    });

    // 密码输入事件
    $("body").on("keyup", ".College", function () {
        var College = $(".College");
        var CollegeFeedback = $(".CollegeFeedback");
        var index = College.index(this);
        College.eq(index).removeClass("is-valid");
        CollegeFeedback.eq(index).removeClass("valid-feedback");
        College.eq(index).removeClass("is-invalid");
        CollegeFeedback.eq(index).removeClass("invalid-feedback");
        CollegeFeedback.eq(index).text("");
    });

    // 提交按钮点击事件
    $("#Submitbtn").click(function () {

        var insertItem = $(".insert-item");
        var Cno = $(".Cno");
        var Cname = $(".Cname");
        var College = $(".College");

        var CnoFeedback = $(".CnoFeedback");
        var CnameFeedback = $(".CnameFeedback");
        var CollegeFeedback = $(".CollegeFeedback");

        // 检查数据长度
        if (insertItem.length == 0) {
            swal("糟糕", "没有添加任何班级信息!", "warning");
            return;
        }

        // 检查所有的班级号，名称，所属学院
        for (var i = 0; i < insertItem.length; i++) {
            // 获取班级编号
            var cno = Cno.eq(i).val();
            if (cno.length == 0) {
                Cno.eq(i).addClass("is-invalid");
                CnoFeedback.eq(i).addClass("invalid-feedback");
                CnoFeedback.eq(i).text("班级编号不能为空!");
                return;
            }
            // 获取姓名
            var cname = Cname.eq(i).val();
            if (cname.length == 0) {
                Cname.eq(i).addClass("is-invalid");
                CnameFeedback.eq(i).addClass("invalid-feedback");
                CnameFeedback.eq(i).text("班级名称不能为空!");
                return;
            }
            // 获取所属学院
            var college = College.eq(i).val();
            if (college.length == 0) {
                College.eq(i).addClass("is-invalid");
                CollegeFeedback.eq(i).addClass("invalid-feedback");
                CollegeFeedback.eq(i).text("所属学院不能为空!");
                return;
            }            
        }

        var Class = new Array();
        // 将数据保存至数组
        for (var i = 0; i < insertItem.length; i++) {
            // 获取班级编号
            var cno = Cno.eq(i).val();
            // 获取班级名称
            var cname = Cname.eq(i).val();
            // 获取所属学院
            var college = College.eq(i).val();
            // 将获取到的数据依次写入Class[i]中
            Class[i] = new Object();
            Class[i].cno = cno;
            Class[i].cname = cname;
            Class[i].college = college;
        }

        // 将数据转化为json数据
        var classes = JSON.stringify(Class);

        // 发送ajax 提交批量插入请求
        $.ajax({
            type: "post",
            url: "../../ClassManageInsertServlet",
            data: {
                json: classes
            },
            success: function (e) {
                if (e == "success") {
                    // 插入数据成功
                    swal("成功", "新增班级信息成功!", "success");
                    // 延时1s跳转刷新界面
                    setTimeout(() => {
                        location.replace("./index.jsp");
                    }, 1000);
                } else if (e == "failed") {
                    // 插入数据失败
                    swal("错误", "批量新增班级信息失败!", "error");
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
