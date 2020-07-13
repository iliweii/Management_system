$(function(){

    var logoutBtn = $("#logoutBtn");

    // 退出登录按钮点击事件
    logoutBtn.click(function() {

        // 发送ajax 请求
        $.ajax({

            type: "post",
            url: "../LogoutServlet",

            success: function(e) {

                if (e == "success") {

                    // 登录成功
                    swal("成功", "退出登录成功!", "success");
                    // 延时1s跳转到index.html主页面
                    setTimeout(() => {
                        location.href = "../login.html"
                    }, 1000);

                } else {

                    // 未知错误
                    swal("错误", "出现未知错误!" + e, "error");

                }
            },

            error: function(e) {

                // 网络或服务器错误
                swal("错误", "网络堵塞或服务器故障!", "error");

            }

        });
        
    });

});