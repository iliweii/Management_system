$(function () {

	// 管理员信息组
    var Tbuser = $("input[aria-describedby=Tbuser]");
    var Tbname = $("input[aria-describedby=Tbname]");
    var Tbpwd = $("input[aria-describedby=Tbpwd]");
    
    // 学生信息组
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
    
    // 教师信息组
    var Tno = $("input#Tno");
    var Tname = $("input#Tname");
    var Tpwd = $("input#Tpwd");
    var Email = $("input#Email");
    var Phone = $("input#Phone");
    var TnoFeedback = $("#TnoFeedback");
    var TnameFeedback = $("#TnameFeedback");
    var TpwdFeedback = $("#TpwdFeedback");
    
    // 退出登录按钮
    var logoutBtn = $("#logoutBtn");
    // 提交按钮
    var Loginbtn = $("#Loginbtn");
    // 重置按钮
    var Resetbtn = $("#Resetbtn");
    // cookie 中保存的数据
    var uid = getCookie("uid");
    var sid = getCookie("sid");
    var tid = getCookie("tid");

    if (uid) {
    	// 发送ajax 请求
        $.ajax({

            type: "post",
            url: "../../AdminManageQueryServlet",
            data: {
                tbid: uid
            },
            success: function (e) {
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
                // 保存tbid
                Loginbtn.attr("tbid", user['tbid']);
            },

            error: function (e) {
                // 网络或服务器错误
                swal("错误", "网络堵塞或服务器故障!", "error");
            }

        });
        
        // 提交按钮点击事件
        Loginbtn.click(function () {
            // 获取管理员登录名
            var tbuser = Tbuser.val();
            if (tbuser.length == 0) {
                swal("糟糕", "管理员登录名不能为空!", "warning");
                return;
            }
            // 获取姓名
            var tbname = Tbname.val();
            if (tbname.length == 0) {
                swal("糟糕", "管理员姓名不能为空!", "warning");
                return;
            }
            // 获取密码
            var tbpwd = Tbpwd.val();
            if (tbpwd.length == 0) {
                swal("糟糕", "管理员密码不能为空!", "warning");
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

                    if ((e == "repeat" && e == tbuser) || e == "norepeat") {

                        // 发送ajax 请求提交数据
                        $.ajax({
                            type: "post",
                            url: "../../AdminManageUpdateServlet",
                            data: {
                                tbuser: tbuser,
                                tbname: tbname,
                                tbpwd: tbpwd,
                                tbid: Loginbtn.attr("tbid")
                            },
                            success: function (e) {
                                if (e == "success") {
                                    // 修改成功
                                    swal("成功", "修改管理员信息成功! 3s后重新登录", "success");
                                    // 延时1s跳转退出登录
                                    setTimeout(() => {
                                        logoutBtn.trigger("click");
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
                        swal("错误", "管理员登录名重复，请更换!", "error");
                    }
                },

                error: function (e) {
                    // 网络或服务器错误
                    swal("错误", "网络堵塞或服务器故障!", "error");
                }

            });
        });
    } else if (sid) {
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
    } else if (tid) {
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
    }
    

    // 退出登录按钮点击事件
    logoutBtn.click(function () {

        // 发送ajax 请求
        $.ajax({

            type: "post",
            url: "../../LogoutServlet",

            success: function (e) {

                if (e == "success") {
                    // 登录成功
                    swal("成功", "退出登录成功!", "success");
                    // 延时1s跳转到index.jsp主页面
                    setTimeout(() => {
                        location.href = "../../login.jsp"
                    }, 1000);
                } else {
                    // 未知错误 
                    swal("错误", "出现未知错误!" + e, "error");
                }
            },

            error: function (e) {
                // 网络或服务器错误
                swal("错误", "网络堵塞或服务器故障!", "error");
            }
        });

    });

});

//读取cookies
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
 
    if(arr=document.cookie.match(reg))
 
        return unescape(arr[2]);
    else
        return null;
}