$(function () {

    // 获取get传值 tbid
    var sid = getQueryVariable("sid");
    if (sid == false) {
        swal("糟糕", "页面获取失败，无效的学生信息，请返回重试!", "error");
        return;
    }

    var Sno = $("input#Sno");
    var Cno = $("#Cno");
    var Sname = $("input#Sname");
    var Spwd = $("input#Spwd");
    var Sex = $("input[name=Sex]");
    var SexChecked = $("input[name=Sex]:checked");
    var SexMale = $("input[name='Sex'][value='1']");
    var SexFemale = $("input[name='Sex'][value='2']");
    var Sage = $("input#Sage");
    var Email = $("input#Email");
    var Phone = $("input#Phone");
    var SnoFeedback = $("#SnoFeedback");
    var SnameFeedback = $("#SnameFeedback");
    var SpwdFeedback = $("#SpwdFeedback");

    // 发送ajax 请求获取数据
    $.ajax({
        type: "post",
        url: "../../StudentManageQueryServlet",
        data: {
            sid: sid
        },
        success: function (e) {
        	
            if (e == "failed") {
                // 查询失败
                swal("糟糕", "学生信息获取失败，请返回重试!", "error");
            } else {

                var student = JSON.parse(e);

                if (student == null) {
                    // 查询失败
                    swal("糟糕", "学生信息获取失败，请返回重试!", "error");
                    return;
                }
                // 将数据输出到表单
                var cno = student['cno']; // 
                // 先获取班级信息，输出班级下拉框
                $.ajax({
                    type: "post",
                    url: "../../ClassManageListServlet",
                    success: function(e) {
                    	var classes = JSON.parse(e);
                    	for (var i = 0; i < classes.length; i++) {
                    		var appendstr = "";
                    		if (cno == classes[i]["cno"]) {
                    			appendstr = '<option value="' + classes[i]["cno"] + '" selected="selected">' + classes[i]["cname"] + '</option>';
                    		} else {
                    			appendstr = '<option value="' + classes[i]["cno"] + '">' + classes[i]["cname"] + '</option>';
                    		}
                    		Cno.append(appendstr);
                    	}
                    }
                });
                Sno.val(student['sno']);
                Sname.val(student['sname']);
                Spwd.val(student['spwd']);
                Sage.val(student['sage']);
                if (student['sex'] == 1) SexMale.attr("checked",true);
                else if (student['sex'] == 2) SexFemale.attr("checked",true);
                Email.val(student['email']);
                Phone.val(student['phone']);
                oldsno = student['sno'];
            }
        },
        error: function (e) {
            // 网络或服务器错误
            swal("错误", "网络堵塞或服务器故障!", "error");
        }
    });

    // 学号输入事件
    Sno.keyup(function() {
        Sno.removeClass("is-valid");
        SnoFeedback.removeClass("valid-feedback");
        Sno.removeClass("is-invalid");
        SnoFeedback.removeClass("invalid-feedback");
        SnoFeedback.text("");
    });

    // 姓名输入事件
    Sname.keyup(function() {
        Sname.removeClass("is-valid");
        SnameFeedback.removeClass("valid-feedback");
        Sname.removeClass("is-invalid");
        SnameFeedback.removeClass("invalid-feedback");
        SnameFeedback.text("");
    });

    // 密码输入事件
    Spwd.keyup(function() {
        Spwd.removeClass("is-valid");
        SpwdFeedback.removeClass("valid-feedback");
        Spwd.removeClass("is-invalid");
        SpwdFeedback.removeClass("invalid-feedback");
        SpwdFeedback.text("");
    });

    // 提交按钮点击事件
    $("#Submitbtn").click(function () {
        // 获取管理员登录名
        var sno = Sno.val();
        if (sno.length == 0) {
            Sno.addClass("is-invalid");
            SnoFeedback.addClass("invalid-feedback");
            SnoFeedback.text("学号不能为空!");
            return;
        }
        // 获取姓名
        var sname = Sname.val();
        if (sname.length == 0) {
            Sname.addClass("is-invalid");
            SnameFeedback.addClass("invalid-feedback");
            SnameFeedback.text("姓名不能为空!");
            return;
        }
        // 获取密码
        var spwd = Spwd.val();
        if (spwd.length == 0) {
            Spwd.addClass("is-invalid");
            SpwdFeedback.addClass("invalid-feedback");
            SpwdFeedback.text("密码不能为空!");
            return;
        }
        // 获取班级
        var cno = Cno.val();
        // 获取性别
        SexChecked = $("input[name=Sex]:checked");
        var sex = SexChecked.val();
        // 获取年龄
        var sage = Sage.val();
        // 获取邮箱
        var email = Email.val();
        // 获取手机号
        var phone = Phone.val();
        // 将获取到的数据依次写入Student中
        var Student = new Object();
        Student.sid = sid;
        Student.sno = sno;
        Student.cno = cno;
        Student.sname = sname;
        Student.spwd = spwd;
        if (sex) Student.sex = sex;
        if (sage) Student.sage = sage;
        Student.email = email;
        Student.phone = phone;
        
        // 将数据转化为json数据
        var json = JSON.stringify(Student);
        
        // 发送ajax 请求提交数据
        $.ajax({
            type: "post",
            url: "../../StudentManageUpdateServlet",
            data: {
            	json: json
            },
            success: function (e) {
                if (e == "success") {
                    // 修改成功
                    swal("成功", "修改学生信息成功!", "success");
                    // 延时1s跳转刷新界面
                    setTimeout(() => {
                        location.reload();
                    }, 1000);
                } else if (e == "failed") {
                    // 修改失败
                    swal("错误", "修改学生信息失败!", "error");
                } else {
                    // 未知错误
                    swal("错误", "出现未知错误!", "error");
                }
            }
        });
    });


    // 重置按钮点击事件
    $("#Resetbtn").click(function () {
        Sno.val("");
        Sname.val("");
        Spwd.val("");
        Sex.attr("checked", false);
        Sage.val("");
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