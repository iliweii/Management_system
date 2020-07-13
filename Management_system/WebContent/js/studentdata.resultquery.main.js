$(function () {

    // 表格数据展示区
    var tbody = $(".tbody");
    // 定义每页最大显示数
    var MAXSHOW = 7;
    
    var sid = getCookie("sid");
    if (!sid) {
    	swal("糟糕", "数据出错，请重新登录！", "error");
    }

    // 发送ajax 请求数据
    $.ajax({

        type: "post",
        url: "../../ResultQueryListServlet",
        data: {
        	sid: sid
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
            	if (!results[i]['teacher']) results[i]['teacher'] = "无";
            	if (!results[i]['grade'] || results[i]['grade']==0) results[i]['grade'] = "未公布";
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
                    </tr>\
            	');
            }

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
                    <td colspan="4">\
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