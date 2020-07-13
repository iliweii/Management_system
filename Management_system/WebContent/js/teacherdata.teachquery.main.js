$(function () {

    // 表格数据展示区
    var tbody = $(".tbody");
    // 定义每页最大显示数
    var MAXSHOW = 7;
    
    var tid = getCookie("tid");
    if (!tid) {
    	swal("糟糕", "数据出错，请重新登录！", "error");
    }

    // 发送ajax 请求数据
    $.ajax({

        type: "post",
        url: "../../TeachQueryListByTidServlet",
        data: {
        	tid: tid
        },
        success: function (e) {

            if (e == "empty") {
                return;
            }

            // 解析授课信息
            var teaching = JSON.parse(e);
            // 获取授课总数
            var length = teaching.length;
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
            	if (teaching[i]['status'] == 0) teaching[i]['status'] = '等待通过';
            	else teaching[i]['status'] = '已通过';
            	if (!teaching[i]['book']) teaching[i]['book'] = '暂无';
            }
            for (var i = (pagenow - 1) * MAXSHOW; i < maxi; i++) {
                tbody.append('\
            		<tr tcid=' + teaching[i]['tcid'] + ' class="user-item">\
                        <td>\
                            <div class="form-check">\
                                <input class="form-check-input" type="checkbox" name="checkuser">\
                            </div>\
                        </td>\
                        <td>' + teaching[i]['tno'] + '</td>\
                        <td>' + teaching[i]['tname'] + '</td>\
                        <td>' + teaching[i]['cno'] + '</td>\
                        <td>' + teaching[i]['cname'] + '</td>\
                        <td>' + teaching[i]['book'] + '</td>\
                        <td>' + teaching[i]['status'] + '</td>\
                        <td>\
                            <button type="button" class="checkbtn btn btn-success btn-sm">修改课程用书</button>\
                        </td>\
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
    
    // 修改课程用书点击事件
    $("body").on("click", ".checkbtn", function() {
    	var index = $(".checkbtn").index(this);
        // 获取授课编号 tcid
        var userItem = $(".user-item");
        var tcid = userItem.eq(index).attr("tcid");
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
                url: "../../TeachQueryUpdateBookServlet",
                data: {
                    tcid: tcid,
                    book: name
                },
        
                success: function (e) {
        
                    if (e == "success") {
                        // 修改成功
                        swal("成功", "修改课程用书成功!", "success");
                        // 延时1s刷新界面
                        setTimeout(() => {
                            location.reload();
                        }, 1000);
                    } else if (e == "failed") {
                        // 修改失败
                        swal("糟糕", "修改课程用书失败!", "error");
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