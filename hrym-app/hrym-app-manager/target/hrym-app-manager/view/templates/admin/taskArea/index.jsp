<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>功课计划</title>
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

        /*设置input的内容*/
        .lW {
            width: 75%;
            margin-left: 3%;
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
                    <h5>功课计划</h5>
                </div>
                <div class="ibox-content">
                    <div class="row row-lg">
                        <div class="col-sm-12">
                            <!-- Example Card View -->
                            <div>
                                <!-- 按钮触发模态框 -->
                                <button class="btn btn-primary" data-toggle="modal" data-backdrop="static"
                                        data-target="#myModal" id="">
                                    功课计划添加
                                </button>
                            </div>
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
                    功课计划
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="model_from" action="">
                    <input type="hidden" id="areaId" name="areaId" value="" placeholder="ID"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">专区类型:</label>
                        <div class="col-sm-10">
                            <select class="form-control lW" name="areaType" id="areaType" required="required">
                                <option name="areaType" value="1">常用功课</option>
                                <option name="areaType" value="2">慧修行功课专区</option>
                                <option name="areaType" value="3">修行专区</option>
                                <option name="areaType" value="4">热门功课</option>
                            </select>
                        </div>
                    </div>
                    <div style="color: #dddd00">
                        <label class="col-sm-2 control-label">温馨提示:</label>
                        <dl>
                            <dt style="padding-left: 110px;padding-top: 8px">除了修行专区，其他专区只能选择以下的功课类型：
                            <dd style="padding-left: 110px;padding-top: 5px">咒语、佛号、其他课诵、经书、禅修、顶礼</dd>
                            </dt>
                        </dl>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">功课类型:</label>
                        <div class="col-sm-10 div1" aria-required="true" id="radioType">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">功课标题:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="areaName" name="areaName" type="text" placeholder="专区标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">功课名称:</label>
                        <div class="col-sm-10 ">
                            <select class="form-control lW" name="itemId" id="itemId"
                                    onchange="return getItemContentID(value)" onclick="getItemContentID(value)">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">功课内容版本:</label>
                        <div class="col-sm-10 ">
                            <select class="form-control lW" name="version" id="version">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">功课期限:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="taskPeriod" name="taskPeriod" type="text"
                                   onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');"
                                   oninput="if(value.length>4)value=value.slice(0,4)">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">功课目标:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="taskTarget" name="taskTarget" value="" type="number"
                                   onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');"
                                   oninput="if(value.length>8)value=value.slice(0,8)">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary save" id="btn">提交更改
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
<%--<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/peity/jquery.peity.min.js"></script>--%>

<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<%--<script src="${pageContext.request.contextPath}/view/static/assets/js/content.js?v=1.0.0"></script>--%>

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
            url: "${pageContext.request.contextPath}/admin/findAllTaskArea",
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
                title: "专区ID",
                field: "areaId",
                sortable: true
            }, {
                title: "专区类型",
                field: "areaTypeStr"
            }, {
                title: "功课标题",
                field: "areaName",
                formatter: function (value) {
                    if (value == null) {
                        return "null";
                    } else {
                        return value;
                    }
                }
            }, {
                title: "版本名称",
                field: "versionName",
                formatter: function (value) {
                    if (value == null) {
                        return "null";
                    } else {
                        return value;
                    }
                }
            }, {
                title: "功课目标",
                field: "taskTarget",
                formatter: function (value) {
                    if (value == null) {
                        return "null";
                    } else {
                        return value;
                    }
                }
            }, {
                title: "功课期限",
                field: "taskPeriod",
                formatter: function (value) {
                    if (value == null) {
                        return "null";
                    } else {
                        return value;
                    }
                }
            }, {
                title: "创建时间",
                field: "createTime"
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
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/admin/findAllTaskTypeId/",
            success: function (msg) {
                $(function () {
                    var html = '';
                    var br = '<br>';
                    for (var i = 0; i < msg.rows.length; i++) {

                        if (i == 4) {
                            html += ' <label class="btn "><input type="radio" id="' + msg.rows[i].typeId + '" name="typeId" value="' + msg.rows[i].typeId + '" onclick="getTypeId(value);">' + msg.rows[i].typeName + '</label>' + br + '';
                        } else {
                            html += ' <label class="btn "><input type="radio" id="' + msg.rows[i].typeId + '" name="typeId" value="' + msg.rows[i].typeId + '" onclick="getTypeId(value);" >' + msg.rows[i].typeName + '</label>';
                        }
                    }
                    $(".div1").append(html);
                });
            }
        });
    });


    <!-- 资源专区内容下拉框 -->
    function getTypeId(value) {
        <!-- 清除专区下拉框的内容-->
        $("#version").empty();
        $("#itemId").empty();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/admin/findTaskItemById",
            data: {"Id": value},
            success: function (msg) {
                var columnNameList = msg.rows;
                var value = columnNameList[0].itemId;
                getItemContentID(value);
                for (var i = 0; i < columnNameList.length; i++) {
                    $("#itemId").append("<option name='itemId'  value=" + columnNameList[i].itemId + ">" + columnNameList[i].itemName + "</option>");
                }
            }
        });
    }
    function getItemContentID(value) {
        Apply($("#itemId").val(), value);
    }
    function Apply(a, value) {
        if (value == '') {

            alert("请先选择功课类型")
        } else {

            $("#version").empty();
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/selectContentById",
                data: {"itemId": value},
                success: function (msg) {

                    var columnNameList = msg.rows;
                    for (var i = 0; i < columnNameList.length; i++) {
//                        var id = columnNameList[i].;
                        $("#version").append("<option name='version'  value=" + columnNameList[i].itemContentId + ">" + columnNameList[i].versionName + "</option>");
                    }
                }
            });
        }

    }

    <!--根据ID查找属性内容（编辑）-->
    function updateCom(id) {
        $("#itemId").empty();
        $("#version").empty();
        $.ajax({
            type: "get",
            dataType: "json",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/admin/findTaskAreaById/",
            data: {"Id": id},
            success: function (msg) {
                //下拉框默认选中事件
                var option_all = $("#areaType option")
                for (var i = 0; i < option_all.length; i++) {
//                        console.log(option_all[i].selected='selected');
                    if (option_all[i].value == msg.rows.areaType) {
                        option_all[i].selected = 'selected'
                    }
                }
                $("#areaId").val(msg.rows.areaId);
                $("#areaName").val(msg.rows.areaName);
                $("#taskPeriod").val(msg.rows.taskPeriod);
                $("#taskTarget").val(msg.rows.taskTarget);
                if (msg.rows.typeId == 10001) {
                    $("#10001").attr("checked", "checked");
//                    $(".typeId").val(msg.rows.typeId);
                } else if (msg.rows.typeId == 10002) {
                    $("#10002").attr("checked", "checked");
                } else if (msg.rows.typeId == 10003) {
                    $("#10003").attr("checked", "checked");
                } else if (msg.rows.typeId == 10004) {
                    $("#10004").attr("checked", "checked");
                } else if (msg.rows.typeId == 10005) {
                    $("#10005").attr("checked", "checked");
                } else if (msg.rows.typeId == 10006) {
                    $("#10006").attr("checked", "checked");
                } else if (msg.rows.typeId == 10007) {
                    $("#10007").attr("checked", "checked");
                } else if (msg.rows.typeId == 10008) {
                    $("#10008").attr("checked", "checked");
                }

                $("#version").val(msg.rows.version);
                //下拉框默认选中事件
                $("#itemId").append("<option name='itemId'  value=" + msg.rows.itemId + ">" + msg.rows.itemName + "</option>");
                $("#version").append("<option name='version'  value=" + msg.rows.version + ">" + msg.rows.versionName + "</option>");
            }
        });
    }

    //        功课计划的添加与修改
    $(document).on("click", ".save", function () {
        if (areaId.value.length > 0) {
            url = "${pageContext.request.contextPath}/admin/updateTaskArea"
        } else {
            url = "${pageContext.request.contextPath}/admin/insertTaskArea"
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
                layer.msg(msg.message, {time: 2000}, function () {
                    layer.close();
                    if (msg.code = 0) {
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
                url: "${pageContext.request.contextPath}/admin/deleteTaskArea/",
                data: {"Id": id},
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
</script>
</body>

</html>
