<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>活动礼品列表</title>
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

        /*设置input的内容*/
        .lW {
            width: 100%;
            margin-left: 0%;
        }

        /*模态框样式*/
        .modal-style {
            border: 0px solid #bbe1f1;
            background: #eefaff;

        }
    </style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>活动用户管理</h5>
                </div>
                <div class="ibox-content">
                    <div class="form-inline form-table">
                        <div class="form-group">
                            <%--<input type="text" class="form-control search-name" name="nickName" id="nickName"  placeholder="用户称呼搜索" />--%>
                            <%--<input class="btn btn-primary" type="button" onclick="eventquery();" value="搜索"/></n>--%>
                            <%--<input type="text" class="form-control search-name" id="startTimeis"name="startTimeis"--%>
                            <%--placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"style="width: 180px; margin-left: 16px" />&nbsp;---%>
                            <%--<input type="text" class="form-control search-name" id="endTimeis" name="endTimeis"--%>
                            <%--placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" style="width: 180px; margin-left: 16px"  />--%>
                            <%--<input class="btn btn-primary " type="button"onclick="timeBanner();" value="查询" />--%>
                            <%--<button type="button" class="btn btn-primary" onclick="reset()">重置</button>--%>
                        </div>
                    </div>
                    <div style="float:right;margin-top:0px;margin-left: 21px">
                        <p>
                            <button class="btn btn-success" style="margin-top: 10px;margin-bottom: 10px"
                                    data-toggle="modal" data-backdrop="static" data-target="#myModal" type="button"><i
                                    class="fa fa-plus"></i>&nbsp;添加
                            </button>
                        </p>
                    </div><!---->
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

<!-- 按钮触发模态框 -->
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>

            </div>
            <div class="wrapper wrapper-content animated fadeInRight">

                <div class="ibox float-e-margins" id="itemContent">
                    <div class="ibox-title">
                        <h5>活动礼品列表</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="bannerForm" method="post" action="">
                            <input type="hidden" id="activityId" name="activityId" value="">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">活动标题：</label>
                                <div class="col-sm-8">
                                    <input id="activityTitle" name="activityTitle" class="form-control" type="text"
                                           value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">活动类型：<b style="color: red">*</b></label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="activityType" id="activityType"
                                               value="1" ${map.content.voiceCount=='1'?'checked':''}> 放生活动
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="activityType" id="activityType1"
                                               value="0" ${map.content.voiceCount=='0'?'checked':''} > 其他
                                    </label>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button class="btn btn-primary save" id="btn" type="button">保存&nbsp;&nbsp;
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
            url: "${pageContext.request.contextPath}/admin/findAllInforMation",
            //表格显示条纹
            striped: false,
            //启动分页
            pagination: true,
            showColumns: true, //是否显示所有的列
            showToggle: true, //是否显示详细视图和列表视图的切换按钮
            cardView: false, //是否显示详细视图
            //每页显示的记录数
            pageSize: 10,
            //当前第几页
            pageNumber: 1,
            //记录数可选列表
            pageList: [5, 10, 15, 20, 25],
            //是否启用查询
            search: false,
            //是否显示父子表信息
            detailView: false,
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
                title: "活动标题",
                field: "activityTitle"
            }, {
                title: "活动类型",
                field: "activityType"
            }, {
                title: "创建时间",
                field: "createdTimeStr"
            }, {
                title: "更新时间",
                field: "updateTimeStr"
            }, {
                title: "操作",
                field: "empty",
                formatter: function (value, row, index) {
                    var operateHtml = '<button class="btn btn-primary btn-xs" data-toggle="modal" data-backdrop="static" data-target="#myModal" type="button" onclick="updateCom(\'' + row.activityId + '\')"><i class="fa fa-edit"></i>&nbsp;修改</button> &nbsp;';
                    operateHtml = operateHtml + '<button class="btn btn-primary btn-xs" type="button" onclick="delActivity(\'' + row.activityId + '\')"><i class="fa fa-delicious"></i>&nbsp;删除</button> &nbsp;';
                    return operateHtml;
                }
            }]
        });
    });

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

    <!--根据ID查找属性内容（编辑）-->
    function updateCom(id) {
        $("#activityId").empty();
        $.ajax({
            type: "get",
            dataType: "json",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/admin/findActivityInformationById/",
            data: {"activityId": id},
            success: function (msg) {
//                //下拉框默认选中事件
//                var option_all = $("#areaType option")
//                for (var i = 0; i < option_all.length; i++) {
////                        console.log(option_all[i].selected='selected');
//                    if (option_all[i].value == msg.rows.areaType) {
//                        option_all[i].selected = 'selected'
//                    }
//                }
                $("#activityId").val(msg.rows.activityId);
                $("#activityTitle").val(msg.rows.activityTitle);
                if (msg.rows.activityType == 1) {
                    document.getElementById("activityType").checked=true;
                } else {
                    document.getElementById("activityType1").checked=true;
                }
            }
        });
    }

    //        功课计划的添加与修改
    $(document).on("click", ".save", function () {
        if (activityId.value.length > 0) {
            url = "${pageContext.request.contextPath}/admin/updateActivityInformation"
        } else {
            url = "${pageContext.request.contextPath}/admin/insertActivityInformation"
        }
        var form = new FormData($(bannerForm)[0]);
        layer.msg('正在提交中，请稍等。。。', {
            icon: 16,
            shade: 0.01,
            time: 0
        });
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: url,
            data: form,
            success: function (msg) {

                layer.msg(msg.message, {time: 2000}, function () {
                    layer.close();
                    if (msg.code = 0) {
                        if (activityId.value.length = 0) {

                            document.getElementById("btn").disabled = true;
                        }
                    }
                    $('#table_list').bootstrapTable("refresh");
                });
            }
        });
    });
    function delActivity(id) {
        layer.confirm('确定删除吗?', {icon: 3, title: '提示'}, function (index) {
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time: 0
            });
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/deleteAcitivityInformation/",
                data: {"activityId": id},
                success: function (msg) {
                    layer.msg(msg.message, {time: 2000}, function () {
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            });
        });
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
        var nickName = $('#nickName').val('');
        $('#table_list').bootstrapTable('refresh', {url: '${pageContext.request.contextPath}/admin/searchNickName'});
    }
    function detailFormatter(index, row) {
        var html = [];
        html.push('<p><b>描述:</b> ' + row.description + '</p>');
        return html.join('');
    }

    //模态框关闭初始化事件
    $(function () {
        $('#myModal').on('hidden.bs.modal', function () {
            document.getElementById("bannerForm").reset();
//            document.getElementById("itemId").onblur;
            $("#activityId").val("");

        })
    });
</script>


</body>

</html>
