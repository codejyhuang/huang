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

        .fileInput {
            margin-top: 5px;
            margin-left: 15px;
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
                        <input type="text" name="audioName" id="audioNames" class="form-control"
                               placeholder="大德音频名称搜索"/>
                        <span class="input-group-btn">
                                 <button class="btn btn-info btn-search" onclick="search();">🔍</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td>
                            </span>
                    </div>
                    <div id="toolbar" style="float:left;margin-left: 30px;margin-top:10px;positon:relative">
                        <div class="myBtn-content">
                            <button type="button" class="btn btn-primary" onclick="reset()">重置&nbsp;&nbsp;&nbsp;&nbsp;
                            </button>
                        </div>
                    </div>
                    <div class="input-group col-md-3"
                         style="float:left;margin-right: 50px;margin-top:10px;positon:relative">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <!-- 按钮触发模态框 -->
                        <button class="btn btn-primary" data-toggle="modal" data-backdrop="static"
                                data-target="#myModal" id="" onclick="empty()">
                            大德念诵添加
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
                    大德念诵管理
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="model_from" action="">
                    <input type="hidden" id="audioId" name="audioId" value="" placeholder="ID"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">音频名称:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="audioName" name="audioName" type="text" placeholder="">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">音频作者:</label>
                        <div class="col-sm-10 ">
                            <input class="form-control lW" id="audioSinger" name="audioSinger" type="text"
                                   placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">功课名称:</label>
                        <div class="col-sm-10 ">
                            <select class="form-control lW" name="itemId" id="itemId" value="">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频文件：<b
                                style="color: red">*</b></label>
                        <div class="col-sm-6">
                            <input name="audioFile" id="audioFile" class="form-control" type="text"
                                   value="" readonly>
                        </div>
                        <div class="col-sm-3">
                            <label class="col-sm-3 control-label">
                                <input type="file" class="uploadFile audioFile oas-filebutton-default">
                            </label>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频字幕：<b
                                style="color: red">*</b></label>
                        <div class="col-sm-6">
                            <input name="audioSubtitle" id="audioSubtitle" class="form-control" type="text"
                                   value="" readonly>
                        </div>
                        <div class="col-sm-3">
                            <label class="col-sm-3 control-label">
                                <input type="file" class="uploadFile audioSubtitle oas-filebutton-default">
                            </label>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频图片：<b
                                style="color: red">*</b></label>
                        <div class="col-sm-6">
                            <input name="audioPic" id="audioPic" class="form-control" type="text"
                                   value="" readonly>
                        </div>
                        <div class="col-sm-3">
                            <label class="col-sm-3 control-label">
                                <input type="file" class="uploadFile audioPic oas-filebutton-default">
                            </label>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">音频时间:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="audioTime" name="audioTime" type="text" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">音频排序:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="audioSort" name="audioSort" type="text" value="">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary save" id="btn">提交</button>
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
            url: "${pageContext.request.contextPath}/admin/findAllResourceAudio",
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
                title: "ID",
                field: "audioId",
                sortable: true
            }, {
                title: "音频文件",
                field: "audioFile"
            }, {
                title: "音频名称",
                field: "audioName",
                formatter: function (value) {
                    if (value == null) {
                        return "null";
                    } else {
                        return value;
                    }
                }
            }, {
                title: "音频时间",
                field: "audioTime",
                formatter: function (value) {
                    if (value == null) {
                        return "null";
                    } else {
                        return value;
                    }
                }
            }, {
                title: "音频图片",
                field: "audioPic",
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
                title: "音频作者",
                field: "audioSinger",
                formatter: function (value) {
                    if (value == null) {
                        return "null";
                    } else {
                        return value;
                    }
                }
            }, {
                title: "音频字幕",
                field: "audioSubtitle"
            }, {
                title: "操作",
                field: "empty",
                formatter: function (value, row) {
                    var operateHtml = '<button class="btn btn-primary btn-xs" type="button" data-toggle="modal" data-backdrop="static" data-target="#myModal" onclick="updateCom(\'' + row.audioId + '\')"><i class="fa fa-edit"></i>&nbsp;编辑</button> &nbsp;';
                    operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="del(\'' + row.audioId + '\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;';
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

    <!-- 功课名称展示-->
    $(document).ready(function typeId() {
        getTypeId();
    })

    //获取功课名称封装
    function getTypeId() {
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/admin/findArticleAndItem?type=" + 0,
            success: function (msg) {
                console.log(msg);
                var columnNameList = msg.rows;
                for (var i = 0; i < columnNameList.length; i++) {
                    $("#itemId").append("<option name='itemId'  value=" + columnNameList[i].itemId + ">" + columnNameList[i].itemName + "</option>");
                }
            }
        });
    }

    //添加弹框
    function empty() {
        $("#audioId").val("");
        getTypeId();
    }

    <!--修改根据id查找内容-->
    function updateCom(id) {
        $("#itemId").empty();
        $.ajax({
            type: "get",
            dataType: "json",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/admin/findResourceAudioById",
            data: {"audioId": id},
            success: function (msg) {
                console.log(msg);
                var data = msg.rows;
                $("#audioId").val(data.audioId);
                $("#audioName").val(data.audioName);
                getTypeId();
                console.log($("#itemId").val());
                $("#audioSinger").val(data.audioSinger);
//                $("#itemId").val(data.itemId);
                $("#itemId").append("<option name='itemId'  value=" + data.itemId + ">" + data.itemName + "</option>");
                console.log($("#itemId").val());
                $("#audioPic").val(data.audioPic);
                $("#audioFile").val(data.audioFile);
                $("#audioTime").val(data.audioTime);
                $("#audioSubtitle").val(data.audioSubtitle);
                $("#audioSort").val(data.audioSort)
            }
        });
    }

    // 大德念诵的添加与修改
    $(document).on("click", ".save", function () {
        console.log($("#itemId").val());
        if ($("#audioId").val()) {
            url = "${pageContext.request.contextPath}/admin/updateResourceAudio"
        } else {
            url = "${pageContext.request.contextPath}/admin/insertResourceAudio"
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
                url: "${pageContext.request.contextPath}/admin/deleteResourceAudio",
                data: {"audioId": id},
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
                _this.closest(".form-group").find(".form-control").val(msg);
                layer.msg('上传成功。。。', {time: 2000}, function () {

                    layer.close();
                });
            }
        });

    });

    //	    目录名称检索
    function search() {
        var audioName = $('#audioNames').val();
        $('#table_list').bootstrapTable('refresh', {url: '${pageContext.request.contextPath}/admin/findAllResourceAudioByName?audioName=' + encodeURI(encodeURI(audioName, "UTF-8"))});
    }

    //        名称搜索
    function eventquery() {
        var itemName = $('#itemName').val();
        $('#table_list').bootstrapTable('refresh', {url: '${pageContext.request.contextPath}/admin/findAllResourceAudioByName?itemName=' + encodeURI(encodeURI(itemName, "UTF-8"))});
    }

    //        重置搜索
    function reset() {
        var audioName = $('#audioNames').val('');
        var itemName = $('#itemName').val('');
        $('#table_list').bootstrapTable('refresh', {url: '${pageContext.request.contextPath}/admin/findAllResourceAudioByName'});
    }


</script>
</body>

</html>
