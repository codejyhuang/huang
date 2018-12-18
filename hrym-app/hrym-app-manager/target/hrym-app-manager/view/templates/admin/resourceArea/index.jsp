<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>用户列表</title>
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
        }

        .table td:hover {
            overflow: scroll;
            white-space: nowrap;
            height: 20px;
        }

        .width {
            width: 100px;
            height: 50px;
        }

        .layui-layer-page .layui-layer-content {
            overflow-x: hidden;
        }
    </style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>功课计划</h5>
                </div>
                <div class="ibox-content">

                    <div class="input-group col-md-3"
                         style="float:left;margin-right: 50px;margin-top:10px;positon:relative">
                        <input type="text" name="itemName" id="itemName" class="form-control" placeholder="功课名称搜索"/>
                        <span class="input-group-btn">
                                 <button class="btn btn-info btn-search" onclick="eventquery();">🔍</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td>
                            </span>
                    </div>
                    <div class="input-group col-md-3"
                         style="float:left;margin-right: 50px;margin-top:10px;positon:relative">
                        <input type="text" name="titleNames" id="titleNames" class="form-control"
                               placeholder="专区名称搜索"/>
                        <span class="input-group-btn">
                                 <button class="btn btn-info btn-search" onclick="search();">🔍</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td>
                            </span>
                    </div>
                    <div id="toolbar" style="float:left;margin-left: 30px;margin-top:10px;positon:relative">
                        <div class="myBtn-content">
                            <button type="button" class="btn btn-primary" onclick="reset()">重置&nbsp;&nbsp;&nbsp;&nbsp;
                            </button>
                        </div>&nbsp;&nbsp;
                    </div>
                    <div class="input-group col-md-3"
                         style="float:left;margin-right: 50px;margin-top:10px;positon:relative">
                        <!-- 按钮触发模态框 -->&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button class="btn btn-primary" data-toggle="modal" data-backdrop="static"
                                data-target="#myModal" id="" onclick="empty()">
                            功课专区添加
                        </button>
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


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content modal-style">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">
                    功课专区
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="model_from" action="">
                    <input type="hidden" id="areaId" name="areaId" value="" placeholder="ID"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">专区类型:</label>
                        <div class="col-sm-10">
                            <select class="form-control lW" name="areaType" id="areaType" required="required"
                                    onchange="btnchange(value)" onclick="btnchange(value)">
                                <option name="areaType" value="1">常用功课专区</option>
                                <option name="areaType" value="4">热门功课</option>
                                <option name="areaType" value="5">功课推荐</option>
                                <option name="areaType" value="6">慧修行能帮你做什么</option>
                                <option name="areaType" value="7">共修活动</option>
                                <option name="areaType" value="8">用户服务</option>
                                <option name="areaType" value="9">寺庙服务</option>
                            </select>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">专区标题:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="titleName" name="titleName" type="text"
                                   placeholder="专区标题">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">功课名称:</label>
                        <div class="col-sm-10 ">
                            <select class="form-control lW" name="itemId" id="itemId" onchange="" onclick="">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">功课描述:</label>
                        <div class="col-sm-10">
                            <textarea class="form-control lW" name="titleDesc" id="titleDesc" placeholder="请填写功课简介的信息……"
                                      style="height: 90px;"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">分享标题：</label>
                        <div class="col-sm-8">
                            <input id="shareTitle" name="shareTitle" class="form-control" type="text" value="">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">分享描述：</label>
                        <div class="col-sm-8">
                            <input id="shareDesc" name="shareDesc" class="form-control" type="text" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">分享URL：</label>
                        <div class="col-sm-8">
                            <input id="shareUrl" name="shareUrl" class="form-control" type="text" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">需要登录：</label>
                        <div>
                            <label class="radio-inline">
                                <input type="radio" id="needLogin" name="needLogin" value="1"> YES
                            </label>
                            <label class="radio-inline">
                                &nbsp;&nbsp;&nbsp;<input type="radio" id="needLogin1" name="needLogin" value="0"
                                                         checked> NO
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">需要分享：</label>
                        <div>
                            <label class="radio-inline">
                                <input type="radio" id="needShare" name="needShare" value="1"> YES
                            </label>
                            <label class="radio-inline">
                                &nbsp;&nbsp;&nbsp;<input type="radio" id="needShare1" name="needShare" value="0"
                                                         checked> NO
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">需要分享结果：</label>
                        <div>
                            <label class="radio-inline">
                                <input type="radio" id="needResult" name="needResult" value="1"> YES
                            </label>
                            <label class="radio-inline">
                                &nbsp;&nbsp;&nbsp;<input type="radio" id="needResult1" name="needResult" value="0"
                                                         checked> NO
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">分享渠道：</label>
                        <div>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="shareChannelA" name="shareChannelA" value="1"> 微信朋友圈
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="shareChannelB" name="shareChannelB" value="1"> 微信好友
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="shareChannelC" name="shareChannelC" value="1"> QQ空间
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="shareChannelD" name="shareChannelD" value="1"> QQ好友
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="shareChannelE" name="shareChannelE" value="1"> 微博
                            </label>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary save" id="btn">提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>

</html>
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

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {
        //初始化表格,动态从服务器加载数据
        $("#table_list").bootstrapTable({
            //使用get请求到服务器获取数据
            type: "POST",
            //必须设置，不然request.getParameter获取不到请求参数
            contentType: "application/json  ",
            //获取数据的Servlet地址
            url: "${pageContext.request.contextPath}/admin/findAllTaskAreaLesson",
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
            detailView: false,
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
                title: "专区标题",
                field: "titleName",
                sortable: true
            }, {
                title: "专区类型",
                field: "areaTypeName"
            }
// , {
//                title: "功课目标",
//                field: "taskTarget",
//                formatter: function (value) {
//                    if (value == null) {
//                        return "null";
//                    } else {
//                        return value;
//                    }
//                }
//            }, {
//                title: "功课期限",
//                field: "taskPeriod",
//                formatter: function (value) {
//                    if (value == null) {
//                        return "null";
//                    } else {
//                        return value;
//                    }
//                }
//            }
                , {
                    title: "专区功课描述",
                    field: "titleDesc",
                    formatter: function (value) {
                        if (value == null) {
                            return "null";
                        } else {
                            return value;
                        }
                    }
                }, {
                    title: "功课名称",
                    field: "itemName",
                    formatter: function (value) {
                        if (value == null) {
                            return "null";
                        } else {
                            return value;
                        }
                    }
                }, {
                    title: "操作",
                    field: "empty",
                    formatter: function (value, row) {
                        var operateHtml = '<button class="btn btn-primary btn-xs" type="button" data-toggle="modal" data-backdrop="static" data-target="#myModal" onclick="updateCom(\'' + row.areaId + '\')"><i class="fa fa-edit"></i>&nbsp;编辑</button> &nbsp;';
                        operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="del(\'' + row.areaId + '\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;';
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
    <!-- 功课类型展示-->
    $(document).ready(function typeId() {
        getTypeId($("#areaType").val());
    });
    //添加弹框
    function empty() {
        $("#areaId").val("");
        getTypeId($("#areaType").val());
    }
    //关联的功课名称
    function btnchange(value) {
        $("#itemId").empty();
        getTypeId(value);
    }

    //封装函数
    function getTypeId(value) {
        console.log(value);
        var typemsg = value;
        var type = "";
        if (typemsg == 1 || typemsg == 4) {
            type = 0;
        } else {
            type = 1;
        }
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/admin/findArticleAndItem?type=" + type,
            success: function (msg) {
                console.log(msg);
                var columnNameList = msg.rows;
                for (var i = 0; i < columnNameList.length; i++) {
                    $("#itemId").append("<option name='itemId'  value=" + columnNameList[i].itemId + ">" + columnNameList[i].itemName + "</option>");
                }
            }
        });

    }

    <!--修改根据id查找内容-->
    function updateCom(id) {
        $("#itemId").empty();
//        $("#version").empty();
        $.ajax({
            type: "get",
            dataType: "json",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/admin/findTaskAreaByIdLesson",
            data: {"areaId": id},
            success: function (msg) {
                console.log(msg);
                var data = msg.rows;
                $("#areaId").val(data.areaId);
                $("#areaType").val(data.areaType);
//                $("#areaType").append("<option name='areaType'  value="+data.areaType+">"+data.areaTypeName+"</option>" );
                $("#itemId").append("<option name='itemId'  value=" + data.itemId + ">" + data.itemName + "</option>");
                getTypeId(data.areaType);
                $("#titleName").val(data.titleName);
                $("#taskPeriod").val(data.taskPeriod);
                $("#taskTarget").val(data.taskTarget);
                $("#titleDesc").val(data.titleDesc);
                $("#shareTitle").val(data.shareTitle);
                $("#shareDesc").val(data.shareDesc);
                $("#shareUrl").val(data.shareUrl);
                if (data.needLogin == 1) {
                    document.getElementById("needLogin").checked = true;
                } else {
                    document.getElementById("needLogin1").checked = true;
                }
                if (data.needShare == 1) {
                    document.getElementById("needShare").checked = true;
                } else {
                    document.getElementById("needShare1").checked = true;
                }
                if (data.needResult == 1) {
                    document.getElementById("needResult").checked = true;
                } else {
                    document.getElementById("needResult1").checked = true;
                }
                // 复选框选中事件
                if (data.shareChannelA == 1) {
                    $("#shareChannelA").attr("checked", "checked");
                }
                if (data.shareChannelB == 1) {
                    $("#shareChannelB").attr("checked", "checked");
                }
                if (data.shareChannelC == 1) {
                    $("#shareChannelC").attr("checked", "checked");
                }
                if (data.shareChannelD == 1) {
                    $("#shareChannelD").attr("checked", "checked");
                }
                if (data.shareChannelE == 1) {
                    $("#shareChannelE").attr("checked", "checked");
                }


            }
        });
    }

    // 功课专区的添加与修改
    $(document).on("click", ".save", function () {
        if ($("#areaId").val()) {
            url = "${pageContext.request.contextPath}/admin/updateResourceAreaLesson"
        } else {
            url = "${pageContext.request.contextPath}/admin/insertResourceAreaLesson"
        }
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
            url: url,
            data: form,
            success: function (msg) {
                console.log(msg);
                layer.msg(msg.message, {time: 2000}, function () {
                    layer.close();
                    if (msg.code = "0") {
                        document.getElementById("btn").disabled = true;
                    }
                    $('#table_list').bootstrapTable("refresh");
                });
            }
        });
    });


    function del(id) {
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
                url: "${pageContext.request.contextPath}/admin/deleteResourceAreaLesson",
                data: {"areaId": id},
                success: function (msg) {
                    layer.msg(msg.message, {time: 2000}, function () {
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            });
        });
    }


    function detailFormatter(index, row) {
        var html = [];
        html.push('<p><b>描述:</b> ' + row.description + '</p>');
        return html.join('');
    }

    //模态框关闭初始化事件
    $(function () {
        $('#myModal').on('hidden.bs.modal', function () {
            document.getElementById("model_from").reset();
            document.getElementById("btn").disabled = false;
//            document.getElementById("itemId").onblur;
            $("#itemId").empty();


        })
    });

    //	    目录名称检索
    function search() {
        var titleName = $('#titleNames').val();
        $('#table_list').bootstrapTable('refresh', {url: '${pageContext.request.contextPath}/admin/findAllTaskAreaLessonByName?titleName=' + encodeURI(encodeURI(titleName, "UTF-8"))});
    }

    //        名称搜索
    function eventquery() {
        var itemName = $('#itemName').val();
        $('#table_list').bootstrapTable('refresh', {url: '${pageContext.request.contextPath}/admin/findAllTaskAreaLessonByName?itemName=' + encodeURI(encodeURI(itemName, "UTF-8"))});
    }

    //        重置搜索
    function reset() {
        var titleName = $('#titleNames').val('');
        var itemName = $('#itemName').val('');
        $('#table_list').bootstrapTable('refresh', {url: '${pageContext.request.contextPath}/admin/findAllTaskAreaLessonByName'});
    }


</script>

