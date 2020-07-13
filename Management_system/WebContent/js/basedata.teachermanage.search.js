$(function () {

    // 获取get传值 q
    var q = getQueryVariable("q");
    if (q == false) {
        swal("糟糕", "页面获取失败，无效的查询信息，请返回重试!", "error");
        return;
    }
    q = decodeURIComponent(q);
    $(".Searchtext").val(q);
    
    // 表格数据展示区
    var tbody = $(".tbody");
    
    // 全选复选框点击事件
    $("body").on("change", "#checkAll", function () {
        var checkuser = $("input[name=checkuser]");
        var checkAll = $("#checkAll");

        // 全选复选框选择状态
        var checked = checkAll.is(':checked');

        if (checked == true) {
            checkuser.prop("checked", true);
        } else {
            checkuser.prop("checked", false);
        }
    });

    // 查询按钮点击事件
    $(".Searchbtn").click(function() {
        var q = $(".Searchtext").val();
        if (q.length == 0) {
            return;
        }
        location.href = "./search.jsp?q=" + q;
    });

    // 发送ajax 请求获取数据 
    $.ajax({
        type: "post",
        url: "../../TeacherManageSearchServlet",
        data: {
            q: q
        },
        success: function (e) {

            if (e == "failed") {
                // 查询失败
                swal("糟糕", "教师信息获取失败，请返回重试!", "error");
            } else {

            	// 解析教师信息
                var teachers = JSON.parse(e);
                // 获取教师总数
                var length = teachers.length;

                // 先查询空数据，对未定义属性进行填充空值
                for (var i = 0; i < length; i++) {
                	if (!teachers[i]['email']) teachers[i]['email'] = "";
                	if (!teachers[i]['phone']) teachers[i]['phone'] = "";
                }
                for (var i = 0; i < length; i++) {
                    tbody.append('\
                		<tr tid=' + teachers[i]['tid'] + ' class="user-item">\
                            <td>\
                                <div class="form-check">\
                                    <input class="form-check-input" type="checkbox" name="checkuser">\
                                </div>\
                            </td>\
                            <td>' + teachers[i]['tno'] + '</td>\
                            <td>' + teachers[i]['tname'] + '</td>\
                            <td>' + teachers[i]['tpwd'] + '</td>\
                            <td>' + teachers[i]['email'] + '</td>\
                            <td>' + teachers[i]['phone'] + '</td>\
                            <td>\
                                <button type="button" class="dropbtn btn btn-danger btn-sm">删除</button>\
                                <button type="button" class="checkbtn btn btn-success btn-sm" onclick="location.href=\'./detail.jsp?tid=' + teachers[i]['tid'] + '\'">查看 修改</button>\
                            </td>\
                        </tr>\
                	');
                }

                // 表格底部批量操作栏
                tbody.append('\
                    <!-- 表格底部批量操作栏 -->\
                    <tr class="table-borderless">\
                        <td colspan="4">\
                            <div class="form-check">\
                                <input class="form-check-input" type="checkbox" id="checkAll">\
                                <label class="form-check-label" for="checkAll">全选</label>\
                            </div>\
                        </td>\
                        <td colspan="3">\
                            <button type="button" class="dropallbtn btn btn-danger btn-sm">全部删除</button>\
                            <button type="button" class="btn btn-primary btn-sm" onclick="location.href=\'./insert.jsp\'">批量添加</button>\
                        </td>\
                    </tr>\
                ');
            }
        },
        error: function (e) {
            // 网络或服务器错误
            swal("错误", "网络堵塞或服务器故障!", "error");
        }
    });

    // 表格栏单项数据删除按钮点击事件
    $("body").on("click", ".dropbtn", function() {
        var index = $(".dropbtn").index(this);
        // 获取教师编号 tid
        var userItem = $(".user-item");
        var tid = userItem.eq(index).attr("tid");

        // 发送ajax请求
        $.ajax({

            type: "post",
            url: "../../TeacherManageDeleteServlet",
            data: {
                tid: tid
            },
    
            success: function (e) {
    
                if (e == "success") {
                    // 删除成功
                    swal("成功", "删除教师成功!", "success");
                    // 延时1s刷新界面
                    setTimeout(() => {
                        location.reload();
                    }, 1000);
                } else if (e == "failed") {
                    // 删除失败
                    swal("糟糕", "删除教师失败!", "error");
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

    // 批量删除按钮点击事件
    $("body").on("click", ".dropallbtn", function() {
        // 获取选中的教师编号 tid
        var userItem = $(".user-item");
        // 定义教师编号数组，数组下标
        var Tid = new Array(), tindex = 0;
        for (var i = 0; i < userItem.length; i++) {
            // 当前单选框
            var checkuser = $("input[name=checkuser]").eq(i);
            // 当前教师编号
            var tid = userItem.eq(i).attr("tid");
            // 当前单选框选择状态
            var checked = checkuser.is(':checked');
            // 若为选中状态，添加至数组
            if (checked == true) {
                Tid[sindex ++] = tid;
            }
        }

        // 判断选中的管理员数量 
        if (Tid.length == 0) {
            swal("糟糕", "你好像没有选择任何教师!", "warning");
            return;
        }

        // 发送ajax数组提交要删除的管理员信息
        var json = JSON.stringify(Tid);
        $.ajax({

            type: "post",
            url: "../../TeacherManageDeleteBatchServlet",
            data: {
                tid: json
            },
    
            success: function (e) {
    
                if (e == "success") {
                    // 删除成功
                    swal("成功", "删除所选教师成功!", "success");
                    // 延时1s刷新界面
                    setTimeout(() => {
                        location.reload();
                    }, 1000);
                } else if (e == "failed") {
                    // 删除失败
                    swal("糟糕", "删除所选教师失败!", "error");
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