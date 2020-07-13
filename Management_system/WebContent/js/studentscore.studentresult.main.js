$(function () {

    // 表格数据展示区
    var tbody = $(".tbody");
    // 定义每页最大显示数
    var MAXSHOW = 7;
    
    var q = getQueryVariable("q");
    var qclno = getQueryVariable("clno");
    var qcono = getQueryVariable("cono");
    
    // 定义班级信息数组
    var classes = null;
    // 获取班级信息，存储到数组
    $.ajax({
        type: "post",
        url: "../../ClassManageListServlet",
        success: function(e) {
        	classes = JSON.parse(e);
        	for (var i = 0; i < classes.length; i++) {
        		if (qclno == classes[i]["cno"]) {
        			$("select[name=Clno]").append('\
        					<option value="' + classes[i]["cno"] + '" selected="selected">' + classes[i]["cno"] + "  " + classes[i]["cname"] + '</option>\
                    ');
        		} else {
        			$("select[name=Clno]").append('\
        					<option value="' + classes[i]["cno"] + '">' + classes[i]["cno"] + "  " + classes[i]["cname"] + '</option>\
                    ');
        		}
        	}
        }
    });
    // 班级下拉框选择事件
    $("select[name=Clno]").change(function() {
    	var clno = $("select[name=Clno]").val();
    	location.replace("./index.jsp?clno=" + clno);
    });
    // 定义课程信息数组
    var courses = null;
    // 获取课程信息，存储到数组
    $.ajax({
        type: "post",
        url: "../../CourseManageListServlet",
        success: function(e) {
        	courses = JSON.parse(e);
        	for (var i = 0; i < courses.length; i++) {
        		if (qcono == courses[i]["cno"]) {
        			$("select[name=Cono]").append('\
                        <option value="' + courses[i]["cno"] + '" selected="selected">' + courses[i]["cno"] + "  " + courses[i]["cname"] + '</option>\
                    ');
        		} else {
        			$("select[name=Cono]").append('\
                        <option value="' + courses[i]["cno"] + '">' + courses[i]["cno"] + "  " + courses[i]["cname"] + '</option>\
                    ');
        		}
        	}
        }
    });
    // 课程下拉框选择事件
    $("select[name=Cono]").change(function() {
    	var cono = $("select[name=Cono]").val();
    	location.replace("./index.jsp?cono=" + cono);
    });

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
        location.href = "./index.jsp?q=" + q;
    });
    // 清空按钮点击事件
    $(".Clearbtn").click(function() {
        location.replace("./index.jsp");
    });

    if (q != "" && q != false){
    	q = decodeURIComponent(q);
        $(".Searchtext").val(q);
    	// 发送ajax 请求数据
        $.ajax({

            type: "post",
            url: "../../StudentResultSearchServlet",
            data: {
            	q: q
            },
            success: function (e) {

                if (e == "empty") {
                    return;
                }

                // 解析学生成绩信息
                var results = JSON.parse(e);
                // 获取授课总数
                var length = results.length;
                // 获取分页总数
                var pagenum = Math.ceil(length * 1.0 / MAXSHOW);
                // 获取当前页数
                var pagenow = getQueryVariable("page");
                if (pagenow == false) {
                    pagenow = 1; // 默认为第一页
                }

                // 输出本页表格数据
                var maxi = pagenow * MAXSHOW < length ? pagenow * MAXSHOW : length;
                // 对未定义属性值进行补充
                for (var i = (pagenow - 1) * MAXSHOW; i < maxi; i++) {
                	if (!results[i]['teacher']) results[i]['teacher'] = "/";
                	if (!results[i]['grade'] || results[i]['grade']==0) results[i]['grade'] = "/";
                }
                for (var i = (pagenow - 1) * MAXSHOW; i < maxi; i++) {
                    tbody.append('\
                		<tr scid=' + results[i]['scid'] + ' class="user-item">\
                            <td>\
                                <div class="form-check">\
                                    <input class="form-check-input" type="checkbox" name="checkuser">\
                                </div>\
                            </td>\
                            <td>' + results[i]['cno'] + '</td>\
                            <td>' + results[i]['cname'] + '</td>\
                            <td>' + results[i]['sno'] + '</td>\
                            <td>' + results[i]['sname'] + '</td>\
                            <td>' + results[i]['clname'] + '</td>\
                            <td>' + results[i]['teacher'] + '</td>\
                            <td>' + results[i]['grade'] + '</td>\
                            <td>\
                                <button type="button" class="dropbtn btn btn-danger btn-sm">删除成绩</button>\
                            	<button type="button" class="checkbtn btn btn-success btn-sm">修改成绩</button>\
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
                        <td colspan="5">\
                            <button type="button" class="dropallbtn btn btn-danger btn-sm">批量删除成绩</button>\
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
                                    <li class="page-item"><a class="page-link" href="./index.jsp?q=' + q + '&page=1">首页</a></li>\
                                    <li class="page-item"><a class="page-link" href="./index.jsp?q=' + q + '&page=' + lastpage + '">上一页</a></li>\
                                    <li class="page-item"><a class="page-link" href="./index.jsp?q=' + q + '&page=' + nextpage + '">下一页</a></li>\
                                    <li class="page-item"><a class="page-link" href="./index.jsp?q=' + q + '&page=' + pagenum + '">尾页</a></li>\
                                </ul>\
                            </nav>\
                        </td>\
                        <td colspan="5">\
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
    } else if (qclno != "" && qclno != false) {
    	qclno = decodeURIComponent(qclno);
    	// 发送ajax 请求数据
        $.ajax({

            type: "post",
            url: "../../StudentResultSearchByClnoServlet",
            data: {
            	cno: qclno
            },
            success: function (e) {

                if (e == "empty") {
                    return;
                }

                // 解析学生成绩信息
                var results = JSON.parse(e);
                // 获取授课总数
                var length = results.length;
                // 获取分页总数
                var pagenum = Math.ceil(length * 1.0 / MAXSHOW);
                // 获取当前页数
                var pagenow = getQueryVariable("page");
                if (pagenow == false) {
                    pagenow = 1; // 默认为第一页
                }

                // 输出本页表格数据
                var maxi = pagenow * MAXSHOW < length ? pagenow * MAXSHOW : length;
                // 对未定义属性值进行补充
                for (var i = (pagenow - 1) * MAXSHOW; i < maxi; i++) {
                	if (!results[i]['teacher']) results[i]['teacher'] = "/";
                	if (!results[i]['grade'] || results[i]['grade']==0) results[i]['grade'] = "/";
                }
                for (var i = (pagenow - 1) * MAXSHOW; i < maxi; i++) {
                    tbody.append('\
                		<tr scid=' + results[i]['scid'] + ' class="user-item">\
                            <td>\
                                <div class="form-check">\
                                    <input class="form-check-input" type="checkbox" name="checkuser">\
                                </div>\
                            </td>\
                            <td>' + results[i]['cno'] + '</td>\
                            <td>' + results[i]['cname'] + '</td>\
                            <td>' + results[i]['sno'] + '</td>\
                            <td>' + results[i]['sname'] + '</td>\
                            <td>' + results[i]['clname'] + '</td>\
                            <td>' + results[i]['teacher'] + '</td>\
                            <td>' + results[i]['grade'] + '</td>\
                            <td>\
                                <button type="button" class="dropbtn btn btn-danger btn-sm">删除成绩</button>\
                            	<button type="button" class="checkbtn btn btn-success btn-sm">修改成绩</button>\
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
                        <td colspan="5">\
                            <button type="button" class="dropallbtn btn btn-danger btn-sm">批量删除成绩</button>\
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
                                    <li class="page-item"><a class="page-link" href="./index.jsp?clno=' + qclno + '&page=1">首页</a></li>\
                                    <li class="page-item"><a class="page-link" href="./index.jsp?clno=' + qclno + '&page=' + lastpage + '">上一页</a></li>\
                                    <li class="page-item"><a class="page-link" href="./index.jsp?clno=' + qclno + '&page=' + nextpage + '">下一页</a></li>\
                                    <li class="page-item"><a class="page-link" href="./index.jsp?clno=' + qclno + '&page=' + pagenum + '">尾页</a></li>\
                                </ul>\
                            </nav>\
                        </td>\
                        <td colspan="5">\
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
    } else if (qcono != "" && qcono != false) {
    	qcono = decodeURIComponent(qcono);
    	// 发送ajax 请求数据
        $.ajax({

            type: "post",
            url: "../../StudentResultSearchByConoServlet",
            data: {
            	cno: qcono
            },
            success: function (e) {

                if (e == "empty") {
                    return;
                }

                // 解析学生成绩信息
                var results = JSON.parse(e);
                // 获取授课总数
                var length = results.length;
                // 获取分页总数
                var pagenum = Math.ceil(length * 1.0 / MAXSHOW);
                // 获取当前页数
                var pagenow = getQueryVariable("page");
                if (pagenow == false) {
                    pagenow = 1; // 默认为第一页
                }

                // 输出本页表格数据
                var maxi = pagenow * MAXSHOW < length ? pagenow * MAXSHOW : length;
                // 对未定义属性值进行补充
                for (var i = (pagenow - 1) * MAXSHOW; i < maxi; i++) {
                	if (!results[i]['teacher']) results[i]['teacher'] = "/";
                	if (!results[i]['grade'] || results[i]['grade']==0) results[i]['grade'] = "/";
                }
                for (var i = (pagenow - 1) * MAXSHOW; i < maxi; i++) {
                    tbody.append('\
                		<tr scid=' + results[i]['scid'] + ' class="user-item">\
                            <td>\
                                <div class="form-check">\
                                    <input class="form-check-input" type="checkbox" name="checkuser">\
                                </div>\
                            </td>\
                            <td>' + results[i]['cno'] + '</td>\
                            <td>' + results[i]['cname'] + '</td>\
                            <td>' + results[i]['sno'] + '</td>\
                            <td>' + results[i]['sname'] + '</td>\
                            <td>' + results[i]['clname'] + '</td>\
                            <td>' + results[i]['teacher'] + '</td>\
                            <td>' + results[i]['grade'] + '</td>\
                            <td>\
                                <button type="button" class="dropbtn btn btn-danger btn-sm">删除成绩</button>\
                            	<button type="button" class="checkbtn btn btn-success btn-sm">修改成绩</button>\
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
                        <td colspan="5">\
                            <button type="button" class="dropallbtn btn btn-danger btn-sm">批量删除成绩</button>\
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
                                    <li class="page-item"><a class="page-link" href="./index.jsp?cono=' + qcono + '&page=1">首页</a></li>\
                                    <li class="page-item"><a class="page-link" href="./index.jsp?cono=' + qcono + '&page=' + lastpage + '">上一页</a></li>\
                                    <li class="page-item"><a class="page-link" href="./index.jsp?cono=' + qcono + '&page=' + nextpage + '">下一页</a></li>\
                                    <li class="page-item"><a class="page-link" href="./index.jsp?cono=' + qcono + '&page=' + pagenum + '">尾页</a></li>\
                                </ul>\
                            </nav>\
                        </td>\
                        <td colspan="5">\
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
    } else {
    	// 发送ajax 请求数据
        $.ajax({

            type: "post",
            url: "../../StudentResultListServlet",

            success: function (e) {

                if (e == "empty") {
                    return;
                }

                // 解析学生成绩信息
                var results = JSON.parse(e);
                // 获取授课总数
                var length = results.length;
                // 获取分页总数
                var pagenum = Math.ceil(length * 1.0 / MAXSHOW);
                // 获取当前页数
                var pagenow = getQueryVariable("page");
                if (pagenow == false) {
                    pagenow = 1; // 默认为第一页
                }

                // 输出本页表格数据
                var maxi = pagenow * MAXSHOW < length ? pagenow * MAXSHOW : length;
                // 对未定义属性值进行补充
                for (var i = (pagenow - 1) * MAXSHOW; i < maxi; i++) {
                	if (!results[i]['teacher']) results[i]['teacher'] = "/";
                	if (!results[i]['grade'] || results[i]['grade']==0) results[i]['grade'] = "/";
                }
                for (var i = (pagenow - 1) * MAXSHOW; i < maxi; i++) {
                    tbody.append('\
                		<tr scid=' + results[i]['scid'] + ' class="user-item">\
                            <td>\
                                <div class="form-check">\
                                    <input class="form-check-input" type="checkbox" name="checkuser">\
                                </div>\
                            </td>\
                            <td>' + results[i]['cno'] + '</td>\
                            <td>' + results[i]['cname'] + '</td>\
                            <td>' + results[i]['sno'] + '</td>\
                            <td>' + results[i]['sname'] + '</td>\
                            <td>' + results[i]['clname'] + '</td>\
                            <td>' + results[i]['teacher'] + '</td>\
                            <td>' + results[i]['grade'] + '</td>\
                            <td>\
                                <button type="button" class="dropbtn btn btn-danger btn-sm">删除成绩</button>\
                            	<button type="button" class="checkbtn btn btn-success btn-sm">修改成绩</button>\
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
                        <td colspan="5">\
                            <button type="button" class="dropallbtn btn btn-danger btn-sm">批量删除成绩</button>\
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
                        <td colspan="5">\
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
    }
    
    
    // 修改成绩点击事件
    $("body").on("click", ".checkbtn", function() {
    	var index = $(".checkbtn").index(this);
        // 获取选课编号 scid
        var userItem = $(".user-item");
        var scid = userItem.eq(index).attr("scid");
        swal({
        	  text: '请输入成绩 数值范围:0.0-100.0',
        	  content: "input",
        	  button: {
        		  text: "保存",
        		  closeModal: false,
        	  },
        	})
        	.then(name => {
        		if (!name) throw null;
        		// 发送ajax请求
                $.ajax({

                    type: "post",
                    url: "../../StudentResultUpdateServlet",
                    data: {
                        scid: scid,
                        grade: name
                    },
            
                    success: function (e) {
            
                        if (e == "success") {
                            // 修改成功
                            swal("成功", "修改成绩成功!", "success");
                            // 延时1s刷新界面
                            setTimeout(() => {
                                location.reload();
                            }, 1000);
                        } else if (e == "failed") {
                            // 修改失败
                            swal("糟糕", "修改成绩失败!", "error");
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

    // 表格栏单项数据删除成绩按钮点击事件
    $("body").on("click", ".dropbtn", function() {
        var index = $(".dropbtn").index(this);
        // 获取选课编号 scid
        var userItem = $(".user-item");
        var scid = userItem.eq(index).attr("scid");

        // 发送ajax请求
        $.ajax({

            type: "post",
            url: "../../StudentResultDeleteServlet",
            data: {
                scid: scid
            },
    
            success: function (e) {
    
                if (e == "success") {
                    // 删除成功
                    swal("成功", "删除成绩成功!", "success");
                    // 延时1s刷新界面
                    setTimeout(() => {
                        location.reload();
                    }, 1000);
                } else if (e == "failed") {
                    // 删除失败
                    swal("糟糕", "删除成绩失败!", "error");
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

    // 批量删除成绩按钮点击事件
    $("body").on("click", ".dropallbtn", function() {
        // 获取选中的选课编号 scid
        var userItem = $(".user-item");
        // 定义选课编号数组，数组下标
        var SCid = new Array(), scindex = 0;
        for (var i = 0; i < userItem.length; i++) {
            // 当前单选框
            var checkuser = $("input[name=checkuser]").eq(i);
            // 当前授课编号
            var scid = userItem.eq(i).attr("scid");
            // 当前单选框选择状态
            var checked = checkuser.is(':checked');
            // 若为选中状态，添加至数组
            if (checked == true) {
                SCid[scindex ++] = scid;
            }
        }

        // 判断选中的授课数量
        if (SCid.length == 0) {
            swal("糟糕", "你好像没有选择任何授课!", "warning");
            return;
        }

        // 发送ajax数组提交要删除的授课信息
        var json = JSON.stringify(SCid);
        $.ajax({

            type: "post",
            url: "../../StudentResultDeleteBatchServlet",
            data: {
                scid: json
            },
    
            success: function (e) {
    
                if (e == "success") {
                    // 删除成功
                    swal("成功", "删除所选选课成功!", "success");
                    // 延时1s刷新界面
                    setTimeout(() => {
                        location.reload();
                    }, 1000);
                } else if (e == "failed") {
                    // 删除失败
                    swal("糟糕", "删除所选选课失败!", "error");
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