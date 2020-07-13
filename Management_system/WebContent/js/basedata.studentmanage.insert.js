$(function () {

    // 数据插入组
    var insertGroup = $(".insert-group");
    // +按钮
    var Addbtn = $(".Addbtn");
    // -按钮
    var Cutbtn = $(".Cutbtn");
    // 用于记录+按钮点击次数，确保性别单选按钮不相互影响
    var addBtnClickTime = 0;
    
    // 定义班级信息数组
    var classes = null;
    // 获取班级信息，存储到数组，以便输出班级下拉框
    $.ajax({
        type: "post",
        url: "../../ClassManageListServlet",
        success: function(e) {
        	classes = JSON.parse(e);
        	// 首先将数据输出到默认首行中
        	for (var i = 0; i < classes.length; i++) {
        		$("select[name=Cno]").append('\
                	<option value="' + classes[i]["cno"] + '">' + classes[i]["cname"] + '</option>\
                ');
        	}
        }
    });

    // +按钮点击事件
    Addbtn.click(function () {
        insertGroup.append('\
        	<div class="insert-item">\
                <div class="col-md-2 mb-3">\
                    <label>学号 <span class="text-danger">*</span></label>\
                    <input type="text" class="form-control Sno" required>\
                    <div class="valid-feedback tbuserFeedback"></div>\
                </div>\
        		<div class="col-md-2 mb-3">\
	                <label>班级 <span class="text-danger">*</span></label>\
	                <select class="form-control Cno" name="Cno' + addBtnClickTime + '">\
				    	<option>--请选择班级--</option>\
				    </select>\
	            </div>\
                <div class="col-md-1 mb-3">\
                    <label>姓名 <span class="text-danger">*</span></label>\
                    <input type="text" class="form-control Sname" required>\
                    <div class="valid-feedback tbnameFeedback"></div>\
                </div>\
                <div class="col-md-2 mb-3">\
                    <label>密码 <span class="text-danger">*</span></label>\
                    <input type="password" class="form-control Spwd" required>\
                    <div class="invalid-feedback tbpwdFeedback"></div>\
                </div>\
                <div class="col-md-1 mb-3">\
                    <label>性别</label>\
                    <div style="display:flex; align-item: center;">\
                    	<span>男</span>\
                    	<input type="radio" class="form-control Sex" name="sex' + addBtnClickTime + '" value="1">\
                    	<span class="ml-1">女</span>\
                    	<input type="radio" class="form-control Sex" name="sex' + addBtnClickTime++ + '" value="2">\
                    </div>\
                </div>\
                <div class="col-md-1 mb-3">\
                    <label>年龄</label>\
                    <input type="text" class="form-control Sage">\
                </div>\
                <div class="col-md-1 mb-3">\
                    <label>邮箱</label>\
                    <input type="text" class="form-control Email">\
                </div>\
                <div class="col-md-1 mb-3">\
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
        // 将班级数据输出到新增的该行中
        var thisSelectClass = $("select[name=Cno" + (addBtnClickTime - 1).toString() + "]");
    	for (var i = 0; i < classes.length; i++) {
    		thisSelectClass.append('\
            	<option value="' + classes[i]["cno"] + '">' + classes[i]["cname"] + '</option>\
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

    // 学号名输入事件
    $("body").on("keyup", ".Sno", function () {
        var Sno = $(".Sno");
        var SnoFeedback = $(".SnoFeedback");
        var index = Sno.index(this);
        Sno.eq(index).removeClass("is-valid");
        SnoFeedback.eq(index).removeClass("valid-feedback");
        Sno.eq(index).removeClass("is-invalid");
        SnoFeedback.eq(index).removeClass("invalid-feedback");
        SnoFeedback.eq(index).text("");
    });

    // 学生姓名输入事件
    $("body").on("keyup", ".Sname", function () {
        var Sname = $(".Sname");
        var SnameFeedback = $(".SnameFeedback");
        var index = Sname.index(this);
        Sname.eq(index).removeClass("is-valid");
        SnameFeedback.eq(index).removeClass("valid-feedback");
        Sname.eq(index).removeClass("is-invalid");
        SnameFeedback.eq(index).removeClass("invalid-feedback");
        SnameFeedback.eq(index).text("");
    });

    // 密码输入事件
    $("body").on("keyup", ".Spwd", function () {
        var Spwd = $(".Spwd");
        var SpwdFeedback = $(".SpwdFeedback");
        var index = Spwd.index(this);
        Spwd.eq(index).removeClass("is-valid");
        SpwdFeedback.eq(index).removeClass("valid-feedback");
        Spwd.eq(index).removeClass("is-invalid");
        SpwdFeedback.eq(index).removeClass("invalid-feedback");
        SpwdFeedback.eq(index).text("");
    });

    // 提交按钮点击事件
    $("#Submitbtn").click(function () {

        var insertItem = $(".insert-item");
        var Sno = $(".Sno");
        var Cno = $(".Cno");
        var Sname = $(".Sname");
        var Spwd = $(".Spwd");
        var Sex = $(".Sex:checked");
        var Sage = $(".Sage");
        var Email = $(".Email");
        var Phone = $(".Phone");

        var SnoFeedback = $(".SnoFeedback");
        var SnameFeedback = $(".SnameFeedback");
        var SpwdFeedback = $(".SpwdFeedback");

        // 检查数据长度
        if (insertItem.length == 0) {
            swal("糟糕", "没有添加任何学生信息!", "warning");
            return;
        }

        // 检查所有的管理员登录名，姓名，密码
        for (var i = 0; i < insertItem.length; i++) {
            // 获取学号
            var sno = Sno.eq(i).val();
            if (sno.length == 0) {
                Sno.eq(i).addClass("is-invalid");
                SnoFeedback.eq(i).addClass("invalid-feedback");
                SnoFeedback.eq(i).text("学号不能为空!");
                return;
            }
            // 获取姓名
            var sname = Sname.eq(i).val();
            if (sname.length == 0) {
                Sname.eq(i).addClass("is-invalid");
                SnameFeedback.eq(i).addClass("invalid-feedback");
                SnameFeedback.eq(i).text("姓名不能为空!");
                return;
            }
            // 获取密码
            var spwd = Spwd.eq(i).val();
            if (spwd.length == 0) {
                Spwd.eq(i).addClass("is-invalid");
                SpwdFeedback.eq(i).addClass("invalid-feedback");
                SpwdFeedback.eq(i).text("密码不能为空!");
                return;
            }            
        }

        var Student = new Array();
        // 将数据保存至数组
        for (var i = 0; i < insertItem.length; i++) {
            // 获取学号
            var sno = Sno.eq(i).val();
            // 获取班级
            var cno = Cno.eq(i).val();
            // 获取姓名
            var sname = Sname.eq(i).val();
            // 获取密码
            var spwd = Spwd.eq(i).val();
            // 获取性别
            var sex = Sex.eq(i).val();
            // 获取年龄
            var sage = Sage.eq(i).val();
            // 获取邮箱
            var email = Email.eq(i).val();
            // 获取手机号
            var phone = Phone.eq(i).val();
            // 将获取到的数据依次写入Student[i]中
            Student[i] = new Object();
            Student[i].sno = sno;
            Student[i].cno = cno;
            Student[i].sname = sname;
            Student[i].spwd = spwd;
            if (sex) Student[i].sex = sex;
            if (sage) Student[i].sage = sage;
            Student[i].email = email;
            Student[i].phone = phone;
        }

        // 将数据转化为json数据
        var student = JSON.stringify(Student);

        // 发送ajax 提交批量插入请求
        $.ajax({
            type: "post",
            url: "../../StudentManageInsertServlet",
            data: {
                json: student
            },
            success: function (e) {
                if (e == "success") {
                    // 插入数据成功
                    swal("成功", "新增学生信息成功!", "success");
                    // 延时1s跳转刷新界面
                    setTimeout(() => {
                        location.replace("./index.jsp");
                    }, 1000);
                } else if (e == "failed") {
                    // 插入数据失败
                    swal("错误", "批量新增学生信息失败!", "error");
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
