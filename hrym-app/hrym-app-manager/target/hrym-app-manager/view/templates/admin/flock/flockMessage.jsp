<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>共修群信息列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/plugins/bootstrap-table/bootstrap-table.min.css"
          rel="stylesheet">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">

    <style>
        table {
            width: 100px;
            table-layout: fixed; /* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
        }

        td {
            width: 100%;
            word-break: keep-all; /* 不换行 */
            white-space: nowrap; /* 不换行 */
            overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
            /*text-overflow:ellipsis;!* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用*!*/
        }

        .table td:hover {
            overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
            overflow: scroll; /* 滚动条*/
            white-space: nowrap;
            height: 10px;
        }

        .head_image {
            width: 100px;
            height: 100px;
        }
    </style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>回答列表</h5>
                </div>
                <div class="ibox-content">
                    <div class="form-inline form-table">
                        <div class="form-group">
                            <input type="text" class="form-control search-name" id="startTimeis" name="startTimeis"
                                   placeholder="YYYY-MM-DD hh:mm:ss"
                                   onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"
                                   style="width: 180px; margin-left: 16px"/>&nbsp;-
                            <input type="text" class="form-control search-name" id="endTimeis" name="endTimeis"
                                   placeholder="YYYY-MM-DD hh:mm:ss"
                                   onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"
                                   style="width: 180px; margin-left: 16px"/>
                            <input class="btn btn-primary " type="button" onclick="timeBanner();" value="查询"/>
                            <button type="button" class="btn btn-primary" onclick="reset()">重置</button>
                        </div>
                    </div>
                    <div class="row row-lg">
                        <div class="col-sm-12">
                            <!-- Example Card View -->
                            <div class="example-wrap">
                                <div class="example">
                                    <table id="table_list"></table>
                                </div>
                            </div>
                            <!-- End Example Card View -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/bootstrap.min.js?v=3.3.6"></script>

<!-- Bootstrap table -->
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<!-- Peity -->
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/peity/jquery.peity.min.js"></script>

<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/view/static/assets/js/content.js?v=1.0.0"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/laydate/laydate.js"></script>

<!-- Page-Level Scripts -->
<script>



    $(document).ready(function () {
        //初始化表格,动态从服务器加载数据
        $("#table_list").bootstrapTable({
            //使用get请求到服务器获取数据
            type: "POST",
            //必须设置，不然request.getParameter获取不到请求参数
            contentType: "application/json",
            //获取数据的Servlet地址
            url: "${pageContext.request.contextPath}/admin/listFlock",
            //表格显示条纹
            striped: false,
            //启动分页
            pagination: true,
            //每页显示的记录数
            pageSize: 10,
            //当前第几页
            pageNumber: 1,
            //记录数可选列表
            pageList: [5, 10, 15, 20, 25],
            //是否启用查询
            search: false,
            showColumns: true, //是否显示所有的列
            showToggle: true, //是否显示详细视图和列表视图的切换按钮
            cardView: false, //是否显示详细视图
            clickToSelect: true, //是否启用点击选中行
            //是否显示父子表信息
            detailView: true,
            // height: tableHeight(),
            detailFormatter: detailFormatter,
            //分页方式：client客户端分页，server服务端分页（*）
            sidePagination: "server",
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            queryParamsType: "undefined",
            //json数据解析
            responseHandler: function (res) {
                return {
                    "rows": res.rows,
                    "total": res.total
                };
            },
            //数据列
            columns: [{
                title: "群名称",
                field: "name",
                sortable: true
            }, {
                title: "共修人数",
                field: "itemJoinNum",
                align: "center",
            }, {
                title: "功课数量",
                field: "itemNum"
            }, {
                title: "创建时间",
                field: "createTime",
                formatter: function (v, r, i) {
                    var time = new Date(v * 1000);
                    var y = time.getFullYear();
                    var m = time.getMonth() + 1;
                    var d = time.getDate();
                    var h = time.getHours();
                    var mm = time.getMinutes();
                    var s = time.getSeconds();
                    return y + '-' + m + '-' + d + ' ' + h + ':' + mm + ':' + s;
                }
            }, {
                title: "群状态",
                field: "state",
                formatter: function (value, row, index) {
                    return value == 1 ? "已解散":"正常";
                }
            }, {
                title: "今日报数量",
                field: "dayDoneNum"
            },{
                title: "累计总报数量",
                field: "totalDoneNum"
            }],
            //注册加载子表的事件。注意下这里的三个参数！
            onExpandRow: function (index, row, $detail) {
                InitSubTable(index, row, $detail);
            }
        });
    });

    //初始化子表格(无线循环)
    InitSubTable = function (index, row, $detail) {
        var parentid = row.id;
        var cur_table = $detail.html('<table></table>').find('table');
        var startTimeis = $("#startTimeis").val();
        var endTimeis = $("#endTimeis").val();
        $(cur_table).bootstrapTable({
            //使用get请求到服务器获取数据
            type: "POST",
            //必须设置，不然request.getParameter获取不到请求参数
            contentType: "application/json",
            //获取数据的Servlet地址
            url: "${pageContext.request.contextPath}/admin/listLessonMessage?id=" + parentid +'&startTimeis=' + startTimeis +'&endTimeis='+endTimeis,
            //表格显示条纹
            striped: false,
            //启动分页
            pagination: false,
            //每页显示的记录数
            pageSize: 5,
            //当前第几页
            pageNumber: 1,
            //记录数可选列表
            pageList: [5, 10, 15, 20, 25],
            //是否启用查询
            search: false,
            //showToggle:true, //是否显示详细视图和列表视图的切换按钮
            cardView: true, //是否显示详细视图
            detailFormatter: detailFormatter,
            //表示服务端请求
            sidePagination: "server",
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            queryParamsType: "undefined",
            //json数据解析
            responseHandler: function (res) {
                return {
                    "rows": res.rows,
                    "total": res.total
                };
            },
            //数据列
            columns: [{
                title: "功课名称",
                field: "versionName"
            },{
                title: "功课报数量",
                field: "totalDoneNum",
//            },{
//                title: "启用状态",
//                field: "isEnabled",
//                formatter: function (value, row, index) {
//                    if(value == 1)
//                        return '<button class="btn btn-primary btn-xs"  type="button" onclick="updateIsNotEnable(\'' + row.itemContentId  + '\')">未启用 </button>';
//                    else if(value == 2)
//                        return '<button class="btn btn-sucess btn-xs"  type="button"  onclick="updateIsEnable(\'' + row.itemContentId + '\')"> 已启用</button>';
//                }
            }],
            //注册加载子表的事件。注意下这里的三个参数！
            onExpandRow: function (index, row, $detail) {
                InitSubTable(index, row, $detail);
            }
        });
    };



    //请求服务数据时所传参数
    function queryParams(params) {
        return {
            //每页多少条数据
            pageSize: params.limit,
            //请求第几页
            pageIndex: params.pageNumber,
            Name: $('#search_name').val(),
            Tel: $('#search_tel').val()
        }
    }


    //搜索
    function eventquery() {
        var nickName = $('#nickName').val();
        $('#table_list').bootstrapTable('refresh', {url: '${pageContext.request.contextPath}/admin/searchNickName?nickName=' + encodeURI(encodeURI(nickName, "UTF-8"))});
    }
    //活动开始时间
    function timeBanner() {
        var startTimeis = $('#startTimeis').val();
        var endTimeis = $('#endTimeis').val();
        $('#table_list').bootstrapTable('refresh', {url: '${pageContext.request.contextPath}/admin/searchUserInfoByReTime?startTimeis=' + startTimeis + '&endTimeis=' + endTimeis});

    }
    //重置
    function reset() {
        var startTimeis = $('#startTimeis').val('');
        var endTimeis = $('#endTimeis').val('');
    }
    function detailFormatter(index, row) {
        var html = [];
        html.push('<p><b>描述:</b> ' + row.description + '</p>');
        return html.join('');
    }

    function editAnswer(id) {
        $.ajax({
            type: "get",
            dataType: "json",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/admin/findAnswerById/",
            data: {"answerId": id},
            success: function (msg) {
                $("#answerId").val(msg.rows.id);
                $("#answerName").val(msg.rows.userName);
                $("#answerContent").val(msg.rows.answerContent);
                $("#headImage").val(msg.rows.headImage);
                $("#questionId").val(msg.rows.questionId);
            }
        });

        console.log($("#headImage").val());
    }
    //保存用户
    function answerUpdate() {
        var form = new FormData($(model_from)[0]);
        layer.msg('正在提交中，请稍等。。。', {
            icon: 16,
            shade: 0.01,
            time: 0
        });
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "${pageContext.request.contextPath}/admin/answerUpdate",
            data: form,
            success: function (msg) {
                if (msg.code == 0) {
                    layer.msg(msg.message, {time: 2000}, function () {
                        layer.close();
                        $('#table_list').bootstrapTable("refresh");
                    });
                } else {
                    layer.msg(msg.message, {time: 2000}, function () {
                        layer.close();
                        $('#table_list').bootstrapTable("refresh");
                    });
                }

            }
        });
    }


    /**
     * 文件上传
     */
    $(document).on("change", ".uploadFile", function () {

        var _this = $(this);
        var oMyForm = new FormData();
        if (!!!_this[0].files[0])
            return;
        oMyForm.append("file", _this[0].files[0]);
        layer.msg('正在提交中，请稍等。。。', {
            icon: 16,
            shade: 0.01,
            time: 0
        });

        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "${pageContext.request.contextPath}/admin/uploadFile",
            data: oMyForm,
            success: function (msg) {
                if (msg == '') {
                    parent.layer.msg('上传失败，请重新上传。。。');
                    return;
                }
                layer.msg('上传成功。。。', {time: 2000}, function () {
                    $("#headImage").val(msg);
                    console.log($("#headImage").val());
                    layer.close();
                });
            }
        });

    });
</script>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content modal-style">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">
                    回答修改编辑
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="model_from" action="">
                    <input type="hidden" id="answerId" name="id" value="" placeholder="ID"/>
                    <input type="hidden" id="uuid" name="uuid" value="-1"/>
                    <input type="hidden" id="questionId" name="questionId" value=""/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">选择头像:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="headImage" name="headImage" type="text" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label class="col-sm-3 control-label">
                                <input type="file" class="uploadFile" class="itemPic oas-filebutton-default">
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">回答显示用户名:</label>
                        <div class="col-sm-10 ">
                            <input class="form-control lW" id="answerName" name="userName" type="text"
                                   placeholder="xxx法师">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">回答内容:</label>
                        <div class="col-sm-10">
                            <textarea class="form-control lW" id="answerContent" name="answerContent"
                                      style="height: 100px" placeholder="写下你的见解......"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary save" data-dismiss="modal" id="btn"
                        onclick="answerUpdate()">提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


</body>

</html>
