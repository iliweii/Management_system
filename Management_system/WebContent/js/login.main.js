$(function(){

    // 变量声明区域

    // 用户名，密码和验证码
    var Tbuser = $("input#Tbuser");
    var Tbpwd = $("input#Tbpwd")
    var Tbverify = $("input#Tbverify");
    // 登录按钮，重置按钮和更换身份按钮
    var Loginbtn = $("#Loginbtn");
    var Resetbtn = $("#Resetbtn");
    var ChangeIdbtn = $("#ChangeIdbtn");
    // 用户名、密码、验证码信息反馈
    var tbuserFeedback = $("#tbuserFeedback");
    var tbpwdFeedback = $("#tbpwdFeedback");
    var tbverifyFeedback = $("#tbverifyFeedback");
    // 用户选择的身份
    var Identity = null;
    // 表单区域
    var Form = $("#Form");
    // 用户选择身份按钮组
    var StudentBtn = $("#StudentBtn");
    var TeacherBtn = $("#TeacherBtn");
    var UserBtn = $("#UserBtn");
    var AdminBtn = $("#AdminBtn");

    // 身份选择事件
    // “我是学生”按钮点击事件
    StudentBtn.click(function() {
        Identity = "student";
        Form.css({
            "z-index": "100",
            "opacity": "1"
        });
        $("label[for=Tbuser]").text("学号");
        $("label[for=Tbpwd]").text("密码");
    });
    // “我是老师”按钮点击事件
    TeacherBtn.click(function() {
        Identity = "teacher";
        Form.css({
            "z-index": "100",
            "opacity": "1"
        });
        $("label[for=Tbuser]").text("教师号");
        $("label[for=Tbpwd]").text("密码");
    });
    // 我是管理员 按钮点击事件
    UserBtn.click(function() {
        Identity = "user";
        Form.css({
            "z-index": "100",
            "opacity": "1"
        });
        $("label[for=Tbuser]").text("管理员登录名");
        $("label[for=Tbpwd]").text("管理员密码");
    });
    // "高级管理员"按钮点击事件
    AdminBtn.click(function() {
        Identity = "admin";
        Form.css({
            "z-index": "100",
            "opacity": "1"
        });
        $("label[for=Tbuser]").text("高级管理员登录名");
        $("label[for=Tbpwd]").text("高级管理员密码");
    });
    
    //初始化验证码
    var verifyCode = new GVerify({
        id:"verify-img",    //容器的ID
        type:"blend"    //图形验证码的类型：blend-数字字母混合类型（默认）、number-纯数字、letter-纯字母
    });

    // 管理员登录名输入事件
    Tbuser.keyup(function() {
        Tbuser.removeClass("is-valid");
        tbuserFeedback.removeClass("valid-feedback");
        Tbuser.removeClass("is-invalid");
        tbuserFeedback.removeClass("invalid-feedback");
        tbuserFeedback.text("");
    });

    // 密码输入事件
    Tbpwd.keyup(function() {
        Tbpwd.removeClass("is-valid");
        tbpwdFeedback.removeClass("valid-feedback");
        Tbpwd.removeClass("is-invalid");
        tbpwdFeedback.removeClass("invalid-feedback");
        tbpwdFeedback.text("");
    });

    // 验证码输入事件
    Tbverify.keyup(function() {
        Tbverify.removeClass("is-valid");
        tbverifyFeedback.removeClass("valid-feedback");
        Tbverify.removeClass("is-invalid");
        tbverifyFeedback.removeClass("invalid-feedback");
        tbverifyFeedback.text("");
    });

    // 登录按钮点击事件
    Loginbtn.click(function() {

        // 获取管理员登录名
        var tbuser = Tbuser.val();
        if (tbuser.length == 0) {
            Tbuser.addClass("is-invalid");
            tbuserFeedback.addClass("invalid-feedback");
            tbuserFeedback.text("您的登录名不能为空!");
        }

        // 获取密码
        var tbpwd = Tbpwd.val();
        if (tbpwd.length == 0) {
            Tbpwd.addClass("is-invalid");
            tbpwdFeedback.addClass("invalid-feedback");
            tbpwdFeedback.text("您的密码不能为空!");
            return;
        }

        // 获取验证码
        var verify = Tbverify.val();
        if (verify.length == 0) {
            Tbverify.addClass("is-invalid");
            tbverifyFeedback.addClass("invalid-feedback");
            tbverifyFeedback.text("请输入验证码!");
            return;
        }
        // 验证验证码
        else if (verifyCode.validate(verify) == false) {
            Tbverify.addClass("is-invalid");
            tbverifyFeedback.addClass("invalid-feedback");
            tbverifyFeedback.text("验证码错误!");
            //刷新验证码
            verifyCode.refresh();
            return;
        }

        // 发送ajax 请求
        $.ajax({

            type: "post",
            url: "./LoginServlet",
            data: {
                tbuser: tbuser,
                tbpwd: tbpwd,
                identity: Identity
            },

            success: function(e) {

                if (e == "success") {

                    // 登录成功
                    swal("成功", "登录成功!", "success");
                    // 延时1s跳转到index.jsp主页面
                    setTimeout(() => {
                        location.href = "./index.jsp"
                    }, 1000);

                } else if (e == "failed") {

                    // 登录失败
                    Tbpwd.addClass("is-invalid");
                    tbpwdFeedback.addClass("invalid-feedback");
                    tbpwdFeedback.text("您的登录名或密码错误!");
                    swal("错误", "登录失败，您的登录名或密码错误!", "error");

                } else {

                    // 未知错误
                    swal("错误", "出现未知错误!" + e, "error");

                }

            },

            error: function(e) {

                // 网络或服务器错误
                swal("错误", "网络堵塞或服务器故障!", "error");
                console.log(e);

            }

        });
        
    });

    // 重置按钮点击事件
    Resetbtn.click(function() {
        Tbuser.val("");
        Tbpwd.val("");
        Tbverify.val("");
        //刷新验证码
        verifyCode.refresh();
    });

    // 更换身份按钮点击事件
    ChangeIdbtn.click(function() {
        // 隐藏表单区域
        Form.css({
            "z-index": "-1",
            "opacity": "0"
        });
        // 置空用户选择的身份
        Identity = null;
        // 延时刷新验证码
        setTimeout(() => {
            verifyCode.refresh();
        }, 400);
    });

});