<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>功课念诵管理列表</title>
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
                    <h5>类型管理</h5>
                </div>
                <div class="ibox-content">
                    <div class="input-group col-md-3"
                         style="float:left;margin-left:40px;margin-top:10px;positon:relative">
                        <input type="text" class="form-control" name="itemName" id="itemNames" placeholder="功课名称"/>
                        <span class="input-group-btn">
                                 <button class="btn btn-info btn-search" onclick="itemNames();">🔍</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </span>
                    </div>
                    <div id="toolbar" style="float:left;margin-left: 30px;margin-top:10px;positon:relative">
                        <div class="myBtn-content">
                            <button type="button" class="btn btn-primary" onclick="reset()">重置</button>
                        </div>
                    </div>

                    <div style="float:right;margin-top:0px;margin-left: 21px">
                        <p>
                            <button class="btn btn-success" style="margin-top: 10px;margin-bottom: 10px" type="button"
                                    onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加
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
<%--功课添加模态框--%>
<script type="text/template" id="itemTap">
    <div class="row">
        <%--<div class="col-sm-12 total">--%>
        <%--功课添加--%>
        <%--<div class="ibox float-e-margins">--%>
        <%--<div class="ibox-title">--%>
        <%--<h5>功课添加</h5><tr>&emsp;&emsp;&emsp;<a id="callBack" href="${pageContext.request.contextPath}/view/templates/admin/itemLesson/index.jsp">返回</a>--%>
        <%--</div>--%>
        <div class="ibox-content">

            <input id="itemContentId" type="hidden" name="itemContentId" value="">
            <form class="form-horizontal m-t" id="item">
                <input class="userId" value="" name="userId" type="hidden">
                <input id="itemId" type="hidden" name="itemId" value="">
                <div class="form-group">
                    <label class="col-sm-3 control-label">功课名称：<b style="color: red">*</b></label>
                    <div class="col-sm-8">
                        <input id="itemName" name="itemName" class="form-control" type="text" value="" placeholder="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">功课别名：<b style="color: red">*</b></label>
                    <div class="col-sm-8">
                        <input id="aliasName" name="aliasName" class="form-control" type="text" value="" placeholder="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">功课描述：<b style="color: red">*</b></label>
                    <div class="col-sm-8">
                        <input id="titleDesc" name="titleDesc" class="form-control" type="text" value="" placeholder="">
                    </div>
                </div>
                <%--<div class="form-group">--%>
                    <%--<label class="col-sm-3 control-label">是否可供：<b style="color: red">*</b></label>--%>
                    <%--<div>--%>
                        <%--<label class="radio-inline" style="margin-left: 17px">--%>
                            <%--<input type="radio" name="isSupport" id="isSupport1" value="1"> 可供--%>
                        <%--</label>--%>
                        <%--<label class="radio-inline">--%>
                            <%--<input type="radio" name="isSupport" id="isSupport2" value="0" checked> 不可供--%>
                        <%--</label>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <label class="col-sm-3 control-label">流派传承：</label>
                    <div class="label-bq1">

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">所属类别：</label>
                    <div class="label-bq2">

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">功德利益：</label>
                    <div class="label-bq3">

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>功课简介：<b
                            style="color: red">*</b></label>
                    <div class="col-sm-8">
                        <textarea name="itemIntro" id="itemIntro" placeholder="请填写功课简介的信息……"
                                  style="width: 500px;height: 100px"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>功德利益简介：<b style="color: red">*</b></label>
                    <div class="col-sm-8">
                        <textarea name="meritBenefitIntro" id="meritBenefitIntro" placeholder="请填写功德利益简介的信息……"
                                  style="width: 500px;height: 100px"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>做功课背景图片：<b
                            style="color: red">*</b></label>
                    <div class="col-sm-6">
                        <input name="backgroundPic" id="backgroundPic" class="form-control" type="text" value=""
                               readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>功课计划背景图片：<b
                            style="color: red">*</b></label>
                    <div class="col-sm-6">
                        <input name="bgBannerPic" id="bgBannerPic" class="form-control" type="text" value="" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>功课列表图片：<b
                            style="color: red">*</b></label>
                    <div class="col-sm-6">
                        <input name="itemPic" id="itemPic" class="form-control" type="text" value="" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>功德利益图片：<b style="color: red">*</b></label>
                    <div class="col-sm-6">
                        <input name="meritBenefitPic" id="meritBenefitPic" class="form-control" type="text" value=""
                               readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-8 col-sm-offset-3">
                        <button class="btn btn-primary saveItem" id="btn" type="button">保存</button>&nbsp;&nbsp;
                    </div>
                </div>
            </form>
        </div>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>
        <input id="typeId" type="hidden" name="typeId" value="">
        <%--</div>--%>
    </div>


</script>
<%--功课内容添加模态框--%>
<script type="text/template" id="contentTap">
    <div class="row">
        <div class="ibox-content">
            <form class="form-horizontal m-t" id="content" method="post" action="">
                <%--//用户ID--%>
                <input class="userId" value="" name="userId" type="hidden">
                <input type="hidden" name="itemId" id="itemId1" class="itemId" value="">
                <div class="form-group">
                    <label class="col-sm-3 control-label">版本名称：<b style="color: red">*</b></label>
                    <div class="col-sm-8">
                        <input id="versionName" name="versionName" class="form-control" type="text"
                               value="${resource.versionName}" placeholder="如:释迦牟尼佛圣号（藏）">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">纯文字：</label>
                    <div class="col-sm-8">
                        <input id="text" name="text" class="form-control" type="text" value="${resource.text}"
                               placeholder="请输入纯文字内容">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>图片内容：<b
                            style="color: red">*</b></label>
                    <div class="col-sm-6">
                        <input name="contentPic" class="form-control" type="text" value="${resource.contentPic}"
                               readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile itemPic oas-filebutton-default">
                        </label>
                    </div>

                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">视频版本号：<b style="color: red">*</b></label>
                    <div class="col-sm-8">
                        <input id="contentVersion" name="contentVersion" class="form-control" type="text"
                               value="${resource.contentVersion}" placeholder="如:供佛（藏）">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>视频文件：</label>

                    <div class="col-sm-6">
                        <input name="videoFile" class="form-control" type="text" value="${resource.videoFile}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" name="" class="uploadFile itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">有无语音识别：<b style="color: red">*</b></label>
                    <div>
                        <label class="radio-inline">
                            <input type="radio" name="voiceCount" id="voiceCount1" value="1"> 有
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="voiceCount" id="voiceCount2" value="0" checked> 没有
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">android语音识别名称：</label>
                    <div class="col-sm-8">
                        <input id="voiceName" name="voiceName" class="form-control" type="text"
                               value="${resource.voiceName}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>语音识别字典文件：</label>

                    <div class="col-sm-6">
                        <input name="voiceDic" class="form-control" type="text" value="${resource.voiceDic}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile itemPic oas-file button-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>android语音识别文件：</label>

                    <div class="col-sm-6">
                        <input name="voiceLm" class="form-control" type="text" value="${resource.voiceLm}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile itemPic oas-file button-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">有无语速识别：<b style="color: red">*</b></label>
                    <div>
                        <label class="radio-inline">
                            <input type="radio" name="speedCount" id="speedCount1" value="1"> 有
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="speedCount" id="speedCount2" value="0" checked> 没有
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>ios语速计数内容：</label>
                    <div class="col-sm-8">
                        <input name="voiceText" class="form-control" type="text" value="${resource.voiceText}"
                               placeholder="请输入ios语速技术字段">
                    </div>

                </div>
                <div class="form-group">
                    <div class="col-sm-8 col-sm-offset-3">
                        <%--<button class="btn btn-primary saveContent" id="btn1" type="button">保存</button>&nbsp;&nbsp;--%>
                        <%--<button class="btn btn-primary addContent" type="button" >添加内容</button>&nbsp;&nbsp;--%>
                    </div>
                </div>
                <input type="hidden" name="typeId" value="10000">
            </form>
        </div>
    </div>
</script>
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
            contentType: "application/json",
            //获取数据的Servlet地址
            url: "${pageContext.request.contextPath}/admin/findResourceItemLessonAll",
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
                title: "功课名称",
                field: "itemName"
            }, {
                title: "功课别名",
                field: "aliasName"
            }, {
                title: "功课简介",
                field: "itemIntro"
            }
//            , {
//                title: "功课描述",
//                field: "titleDesc"
//            }
            , {
                field: "itemPic",
                title: "背景图片",
                align: "center",
                formatter: function (value) {
                    return '<img class="width"  src=' + value + '>';
                }
            }, {
                title: "在做人数",
                field: "onlineNum"
            }, {
                field: "orderNum",
                title: "定制数"
            }, {
                field: "tags",
                title: "功课标签"
            }
//            , {
//                title: "是否可供",
//                field: "isSupport",
//                formatter: function (value) {
//                    if (value == 0) {
//                        return "不可供";
//                    } else {
//                        return "可供";
//                    }
//
//                }
//
//            }
            , {
                title: "操作",
                field: "empty",
                formatter: function (value, row, index) {

                    var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="edit(\'' + row.itemId + '\')"><i class="fa fa-edit"></i>&nbsp;编辑</button> &nbsp;';
                    if(row.flag=='0'){
                        operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="publish(\''+row.itemId+'\')"><i class="fa fa-remove"></i>&nbsp;发布</button> &nbsp;';
                    }

                    operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="delItem(\'' + row.itemId + '\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;';
                    operateHtml = operateHtml + '<button  class="btn btn-sucess btn-xs" data-toggle="modal" data-backdrop="static" data-target="#myModal"  type="button" onclick="saveContent(\'' + row.itemId + '\')">添加内容</button>';
                    return operateHtml;
                }
            }],
            //注册加载子表的事件。注意下这里的三个参数！
            onExpandRow: function (index, row, $detail) {
                InitSubTable(index, row, $detail);
            }
        });
    });


    //初始化子表格(无线循环)
    InitSubTable = function (index, row, $detail) {
        var parentid = row.itemId;
        var cur_table = $detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            //使用get请求到服务器获取数据
            type: "POST",
            //必须设置，不然request.getParameter获取不到请求参数
            contentType: "application/json",
            //获取数据的Servlet地址
            url: "${pageContext.request.contextPath}/admin/findAllContentById?id=" + parentid,
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
                title: "功课内容版本名称",
                field: "versionName"
            }, {
                title: "视频版本",
                field: "contentVersion"
            }, {
                title: "正在做的人数",
                field: "onlineNum",
                sortable: true
            }, {
                title: "定制数量",
                field: "orderNum"
            }, {
                title: "来源",
                field: "origin"
//                    width: 80
            }, {
                title: "纯文字",
                field: "text"
            }, {
                title: "版本",
                field: "version",
                sortable: true
            }, {
                title: "语音识别名称",
                field: "voiceName"
//                    width: 80
            }, {
                title: "背景图片",
                field: "contentPic",
                align: "center",
                width: 80,
                formatter: function (value, row, index) {
                    return '<img class="width" src=' + value + '>';
                }
            }, {
                title: "语音识别名称",
                field: "voiceName"
//                    width: 80
            }, {
                title: "ios语音计数字段",
                field: "voiceText"
//                    width: 80
            }, {
                title: "语音识别字典文件",
                field: "voiceDic"
//                    width: 80
            }, {
                title: "android语音识别文件",
                field: "voiceLm"
//                    width: 80
            }, {
                title: "视频文件",
                field: "videoFile",
                width: 80
//            },{
//                title: "启用状态",
//                field: "isEnabled",
//                formatter: function (value, row, index) {
//                    if(value == 1)
//                        return '<button class="btn btn-primary btn-xs"  type="button" onclick="updateIsNotEnable(\'' + row.itemContentId  + '\')">未启用 </button>';
//                    else if(value == 2)
//                        return '<button class="btn btn-sucess btn-xs"  type="button"  onclick="updateIsEnable(\'' + row.itemContentId + '\')"> 已启用</button>';
//                }
            }, {
                title: "操作",
                field: "empty",
                witdth: 300,
                formatter: function (value, row, index) {
                    var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="editcon(\'' + row.itemContentId + '\')"><i class="fa fa-edit"></i>修改</button>';
                    operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="delCont(\'' + row.itemContentId + '\')"><i class="fa fa-remove"></i>删除</button>';
                    return operateHtml;
                }
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

    function edit(id) { //修改
        layer.open({
            type: 1,
            title: '功课修改',
            shadeClose: false,
            shade: false,
            area: ['1000px', '550px'],
            content: $("#itemTap").html(),
            success: function () {
                console.log(id);
                $("#itemId").val(id);
                $.ajax({
                    type: "post",
                    processData: false,
                    contentType: false,
                    url: "${pageContext.request.contextPath}/admin/findTagsList",
                    data: "",
                    success: function (data) {
                        console.log(data);
                        for (var i = 0; i < data.rows.length; i++) {
                            if (data.rows[i].tagType == 0) {  //流派
                                var str = "<label class='radio-inline'><input type='checkbox' name='tags3' value=" + data.rows[i].tagId + ">" + data.rows[i].tagName + "</label>"
                                $(".label-bq1").append(str);
                            }
                            if (data.rows[i].tagType == 1) {  //类别
                                var str = "<label class='radio-inline'><input type='checkbox' name='tags1' value=" + data.rows[i].tagId + ">" + data.rows[i].tagName + "</label>"
                                $(".label-bq2").append(str);
                            }
                            if (data.rows[i].tagType == 2) {  // 功效
                                var str = "<label class='radio-inline'><input type='checkbox' name='tags2' value=" + data.rows[i].tagId + ">" + data.rows[i].tagName + "</label>"
                                $(".label-bq3").append(str);
                            }

                        }
//                        $(".label-bq1").append("<label class='radio-inline'><input type='radio' name='tags3' value='-1'>不选择</label>");
//                        $(".label-bq2").append("<label class='radio-inline'><input type='radio' name='tags1' value='-1'>不选择</label>");
//                        $(".label-bq3").append("<label class='radio-inline'><input type='radio' name='tags2' value='-1'>不选择</label>");
//                                $(".label-bq1").children(".radio-inline").eq(0).children().attr("checked","checked");
//                                $(".label-bq2").children(".radio-inline").eq(0).children().attr("checked","checked");
//                                $(".label-bq3").children(".radio-inline").eq(0).children().attr("checked","checked");
                    }
                });
                $.ajax({
                    type: "post",
                    dataType: "json",
                    contentType: "application/json",
                    url: "${pageContext.request.contextPath}/admin/findItemLessonByItemId?itemId=" + id,
                    success: function (data) {
                        console.log(data);
                        $("#itemName").val(data.rows.itemName);
                        $("#aliasName").val(data.rows.aliasName);
                        $("#titleDesc").val(data.rows.titleDesc);
                        $("#backgroundPic").val(data.rows.backgroundPic);
                        $("#bgBannerPic").val(data.rows.bgBannerPic);
                        $("#itemPic").val(data.rows.itemPic);
                        $("#meritBenefitPic").val(data.rows.meritBenefitPic);
                        $("#itemIntro").val(data.rows.itemIntro);
                        $("#meritBenefitIntro").val(data.rows.meritBenefitIntro);
                        //是否可供
                        if (data.rows.isSupport == 0) { //不可供
                            $("#isSupport2").attr("checked", "checked");
                        } else (
                            $("#isSupport1").attr("checked", "checked")
                        );

                        if (data.rows.tags1) {   //类别
                            console.log("tages1有值");
                            var tags1arr = data.rows.tags1.split(",");
                            var length2 = $(".label-bq2").children(".radio-inline").length;
                            //所属类别
                            for (var i = 0; i < length2; i++) {
                                for (var j = 0; j < tags1arr.length; j++) {
                                    var val = $(".label-bq2").children(".radio-inline").eq(i).children().val();
                                    if (tags1arr[j] == val) {
                                        $(".label-bq2").children(".radio-inline").eq(i).children().attr("checked", "checked")
                                    }
                                }
                            }
                        }
                        if (data.rows.tags2) {  //功德利益
                            console.log("tages2有值");
                            var tags2arr = data.rows.tags2.split(",");
                            var length3 = $(".label-bq3").children(".radio-inline").length;
                            //功德利益
                            for (var i = 0; i < length3; i++) {
                                for (var j = 0; j < tags2arr.length; j++) {
                                    var val = $(".label-bq3").children(".radio-inline").eq(i).children().val();
                                    if (tags2arr[j] == val) {
                                        $(".label-bq3").children(".radio-inline").eq(i).children().attr("checked", "checked")
                                    }
                                }
                            }
                        }
                        if (data.rows.tags3) {   //流派传承
                            console.log("tages3有值");
                            var tags3arr = data.rows.tags3.split(",");
                            var length1 = $(".label-bq1").children(".radio-inline").length;
                            //流派传承
                            for (var i = 0; i < length1; i++) {
                                for (var j = 0; j < tags3arr.length; j++) {
                                    var val = $(".label-bq1").children(".radio-inline").eq(i).children().val();
                                    if (tags3arr[j] == val) {
                                        $(".label-bq1").children(".radio-inline").eq(i).children().attr("checked", "checked")
                                    }
                                }
                            }

                        }
                    }
                })

            },
            end: function (index) {
                $('#table_list').bootstrapTable("refresh");
            }
        });
    }
    //修改的保存
    $(document).on("click", "#btn", function () {
        var typeId = document.getElementById("typeId").value;
        var userId = window.parent.document.getElementById("userId").defaultValue;
        $(".userId").val(userId);
        var form = new FormData($(item)[0]);
        console.log(form);
        layer.msg('正在提交中，请稍等。。。', {
            icon: 16,
            shade: 0.01,
            time: 0
        });
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "${pageContext.request.contextPath}/admin/updateResourceItemLesson",
            data: form,
            success: function (msg) {
                console.log(msg);
                document.getElementById("btn").disabled = true;
                //$("#itemId").val(msg.rows.itemId);
                layer.msg(msg.message, {time: 2000}, function () {
                    layer.close();
                });
            }
        });

    });

    //    功课内容启用
    function updateIsNotEnable(itemContentId) {

        layer.confirm('确定启用吗?', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/updateIsEnabledById",
                data: {"isEnabled":2,"itemContentId":itemContentId},
                success: function (msg) {
                    layer.msg(msg.message, {time: 2000}, function () {
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            });
        });
    }
    //    功课内容下架
    function updateIsEnable(itemContentId) {

        layer.confirm('确定不启用吗?', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/updateIsEnabledById",
                data: {"isEnabled":1,"itemContentId":itemContentId},
                success: function (msg) {
                    layer.msg(msg.message, {time: 2000}, function () {
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            });
        });
    }

    function add() {  //添加
        window.location = '${pageContext.request.contextPath}/view/templates/admin/itemLesson/form.jsp';
    }
    //  功课删除
    function delItem(id) {

        var userId= window.parent.document.getElementById("userId").defaultValue;
        layer.confirm('确定删除吗?', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/deleteResourceItemLesson",
                <%--url: "${pageContext.request.contextPath}/admin/updateIsExitByItemLessonId",--%>
                data: {"itemId": id,"userId":userId},
                success: function (msg) {
                    layer.msg(msg.message, {time: 2000}, function () {
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            });
        });
    }

    function publish(id) {

        var userId= window.parent.document.getElementById("userId").defaultValue;
        layer.confirm('确定发布吗?', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/publishResourceItemLesson",
                data: {"itemId": id,"userId":userId},
                success: function (msg) {
                    layer.msg(msg.message, {time: 2000}, function () {
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            });
        });
    }


    //    功课内容删除
    function delCont(id) {
        var userId= window.parent.document.getElementById("userId").defaultValue;
        layer.confirm('确定删除吗?', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/deleteContentLesson",
                <%--url: "${pageContext.request.contextPath}/admin/updateIsExitByContentLessonId",--%>
                data: {"id": id,"userId":userId},
                success: function (msg) {
                    layer.msg(msg.message, {time: 2000}, function () {
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            });
        });
    }
    //功课内容修改
    function editcon(id) {
        window.location = '${pageContext.request.contextPath}/admin/findContentLessonByContentId?id=' + id;

    }
    //功课内容添加
    function saveContent(id) {
        var userId = window.parent.document.getElementById("userId").defaultValue;
        $(".userId").val(userId);
        layer.open({
            type: 1,
            title: '功课内容添加',
            shadeClose: false,
            shade: false,
            btn: ["保存"],
            area: ['1000px', '550px'],
            content: $("#contentTap").html(),
            success: function () {
                $("#itemId1").val(id);
                $(".layui-layer-page .layui-layer-content").css("overflowX", "hidden");
            },
            yes: function (index) {
                var con = $("#content");
                var form = new FormData(con[0]);
                console.log(form);
                layer.msg('正在提交中，请稍等。。。', {
                    icon: 16,
                    shade: 0.01,
                    time: 0
                });
                $.ajax({
                    type: "POST",
                    processData: false,
                    contentType: false,
                    url: "${pageContext.request.contextPath}/admin/insertContentLesson",
                    data: form,
                    success: function (msg) {
                        console.log(msg);
                        $('#table_list').bootstrapTable("refresh");
                        layer.msg(msg.message, {time: 2000}, function () {
                            layer.close();
                            layer.close(index);
                        });
                    }
                });
            }

        })
    }

    function detailFormatter(index, row) {
        var html = [];
        html.push('<p><b>描述:</b> ' + row.description + '</p>');
        return html.join('');
    }

    //目录名称
    function itemNames() {
        var itemName = $('#itemNames').val();
        $('#table_list').bootstrapTable('refresh', {url: '${pageContext.request.contextPath}/admin/findItemLessonByName?itemName=' + encodeURI(encodeURI(itemName, "UTF-8"))});
    }

    //重置
    function reset() {
        var typeName = $('#itemNames').val('');
        $('#table_list').bootstrapTable('refresh', {url: '${pageContext.request.contextPath}/admin/findItemLessonByName'});
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
                _this.closest(".form-group").find(".form-control").val(msg);

                layer.msg('上传成功。。。', {time: 2000}, function () {

                    layer.close();
                });
            }
        });

    });

</script>

