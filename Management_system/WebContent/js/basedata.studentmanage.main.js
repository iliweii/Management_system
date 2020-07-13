$(function () {

    // 表格数据展示区
    var tbody = $(".tbody");
    // 定义每页最大显示数
    var MAXSHOW = 7;

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

    // 发送ajax 请求数据
    $.ajax({

        type: "post",
        url: "../../StudentManageListServlet",

        success: function (e) {

            if (e == "empty") {
                return;
            }

            // 解析管理员信息
            var stus = JSON.parse(e);
            // 获取管理员总数
            var length = stus.length;
            // 获取分页总数
            var pagenum = Math.ceil(length * 1.0 / MAXSHOW);
            // 获取当前页数
            var pagenow = getQueryVariable("page");
            if (pagenow == false) {
                pagenow = 1; // 默认为第一页
            }

            // 输出本页表格数据
            var maxi = pagenow * MAXSHOW < length ? pagenow * MAXSHOW : length;
            // 先查询空数据，对未定义属性进行填充空值
            for (var i = (pagenow - 1) * MAXSHOW; i < maxi; i++) {
            	if (!stus[i]['sex']) stus[i]['sex'] = "";
            	else if (stus[i]['sex'] == 1) stus[i]['sex'] = "男";
            	else stus[i]['sex'] = "女";
            	if (!stus[i]['sage']) stus[i]['sage'] = "";
            	if (!stus[i]['email']) stus[i]['email'] = "";
            	if (!stus[i]['phone']) stus[i]['phone'] = "";
            }
            for (var i = (pagenow - 1) * MAXSHOW; i < maxi; i++) {
                tbody.append('\
            		<tr sid=' + stus[i]['sid'] + ' class="user-item">\
                        <td>\
                            <div class="form-check">\
                                <input class="form-check-input" type="checkbox" name="checkuser">\
                            </div>\
                        </td>\
                        <td>' + stus[i]['sno'] + '</td>\
                        <td>' + stus[i]['cname'] + '</td>\
                        <td>' + stus[i]['sname'] + '</td>\
                        <td>' + stus[i]['spwd'] + '</td>\
                        <td>' + stus[i]['sex'] + '</td>\
                        <td>' + stus[i]['sage'] + '</td>\
                        <td>' + stus[i]['email'] + '</td>\
                        <td>' + stus[i]['phone'] + '</td>\
                        <td>\
                            <button type="button" class="dropbtn btn btn-danger btn-sm">删除</button>\
                            <button type="button" class="checkbtn btn btn-success btn-sm" onclick="location.href=\'./detail.jsp?sid=' + stus[i]['sid'] + '\'">查看 修改</button>\
                        </td>\
                    </tr>\
            	');
            }

            // 表格底部批量操作栏
            tbody.append('\
                <!-- 表格底部批量操作栏 -->\
                <tr class="table-borderless">\
                    <td colspan="5">\
                        <div class="form-check">\
                            <input class="form-check-input" type="checkbox" id="checkAll">\
                            <label class="form-check-label" for="checkAll">全选</label>\
                        </div>\
                    </td>\
                    <td colspan="5">\
                        <button type="button" class="dropallbtn btn btn-danger btn-sm">全部删除</button>\
                        <button type="button" class="btn btn-primary btn-sm" onclick="location.href=\'./insert.jsp\'">批量添加</button>\
                    </td>\
                </tr>\
            ');

            // 定义上一页
            var lastpage = pagenow - 1 > 1 ? pagenow - 1 : 1;
            // 定义下一页
            var nextpage = pagenow + 1 < pagenum ? pagenow + 1 : pagenum;
            // 表格底部分页栏
            tbody.append('\
                <!-- 表格底部分页栏 -->\
                <tr class="table-borderless">\
                    <td colspan="4">\
                        <nav aria-label="Page navigation example">\
                            <ul class="pagination">\
                                <li class="page-item"><a class="page-link" href="./index.jsp?page=1">首页</a></li>\
                                <li class="page-item"><a class="page-link" href="./index.jsp?page=' + lastpage + '">上一页</a></li>\
                                <li class="page-item"><a class="page-link" href="./index.jsp?page=' + nextpage + '">下一页</a></li>\
                                <li class="page-item"><a class="page-link" href="./index.jsp?page=' + pagenum + '">尾页</a></li>\
                            </ul>\
                        </nav>\
                    </td>\
                    <td colspan="6">\
                        <span>第<span class="nowpage">' + pagenow + '</span>页/共<span class="allpage">' + pagenum + '</span>页</span>\
                    </td>\
                </tr>\
            ');

        },

        error: function (e) {

            // 网络或服务器错误
            swal("错误", "网络堵塞或服务器故障!", "error");

        }

    });

    // 表格栏单项数据删除按钮点击事件
    $("body").on("click", ".dropbtn", function() {
        var index = $(".dropbtn").index(this);
        // 获取学生编号 sid
        var userItem = $(".user-item");
        var sid = userItem.eq(index).attr("sid");

        // 发送ajax请求
        $.ajax({

            type: "post",
            url: "../../StudentManageDeleteServlet",
            data: {
                sid: sid
            },
    
            success: function (e) {
    
                if (e == "success") {
                    // 删除成功
                    swal("成功", "删除学生成功!", "success");
                    // 延时1s刷新界面
                    setTimeout(() => {
                        location.reload();
                    }, 1000);
                } else if (e == "failed") {
                    // 删除失败
                    swal("糟糕", "删除学生失败!", "error");
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
        // 获取选中的学生编号 sid
        var userItem = $(".user-item");
        // 定义管理员编号数组，数组下标
        var Sid = new Array(), sindex = 0;
        for (var i = 0; i < userItem.length; i++) {
            // 当前单选框
            var checkuser = $("input[name=checkuser]").eq(i);
            // 当前管理员编号
            var sid = userItem.eq(i).attr("sid");
            // 当前单选框选择状态
            var checked = checkuser.is(':checked');
            // 若为选中状态，添加至数组
            if (checked == true) {
                Sid[sindex ++] = sid;
            }
        }

        // 判断选中的管理员数量 
        if (Sid.length == 0) {
            swal("糟糕", "你好像没有选择任何学生!", "warning");
            return;
        }

        // 发送ajax数组提交要删除的管理员信息
        var json = JSON.stringify(Sid);
        $.ajax({

            type: "post",
            url: "../../StudentManageDeleteBatchServlet",
            data: {
                sid: json
            },
    
            success: function (e) {
    
                if (e == "success") {
                    // 删除成功
                    swal("成功", "删除所选学生成功!", "success");
                    // 延时1s刷新界面
                    setTimeout(() => {
                        location.reload();
                    }, 1000);
                } else if (e == "failed") {
                    // 删除失败
                    swal("糟糕", "删除所选学生失败!", "error");
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