<%--suppress ALL --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--&lt;%&ndash;不缓存&ndash;%&gt;--%>
    <%--<meta HTTP-EQUIV="pragma" CONTENT="no-cache">--%>
    <%--<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">--%>
    <%--<meta HTTP-EQUIV="expires" CONTENT="0">--%>

    <title>资源列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/easyui/easyui.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/easyui/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/easyui/jquery.easyui.min.js"></script>
    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">

    <style>
        table{
            width:100px;
            table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
        }
        td{
            width:100%;
            white-space:nowrap;/* 不换行 */
            overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
            /*text-overflow:ellipsis;!* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用*!*/
        }
        .table td:hover{
            overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
            overflow: scroll;   /* 滚动条*/
            overflow: auto;
            white-space: nowrap;
            height: 10px;
        }
        .itemPic_itemPic{
            width: 100px;
            height: 50px;
        }

    </style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>资源管理</h5>
                </div>
                <div class="ibox-content">
                    <div class="input-group col-md-3" style="float:left;margin-right: 50px;margin-top:10px;positon:relative">
                        <input type="text" name="itemName" id="itemName" class="form-control" placeholder="资源名搜索" />
                        <span class="input-group-btn">
                                 <button class="btn btn-info btn-search" onclick="eventquery();">🔍</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td>
                            </span>
                    </div>
                    <div class="input-group col-md-3" style="float:left;margin-right: 50px;margin-top:10px;positon:relative">
                        <input type="text" name="catalogueName" id="catalogueName" class="form-control" placeholder="目录名搜索" />
                        <span class="input-group-btn">
                                 <button class="btn btn-info btn-search" onclick="search();">🔍</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td>
                            </span>
                    </div>
                    <div id="toolbar" style="float:left;margin-left: 30px;margin-top:10px;positon:relative">
                        <div class="myBtn-content">
                            <button type="button" class="btn btn-primary" onclick="reset()">重置</button>
                        </div>
                    </div>
                    <div style="float:left;margin-left: 210px;margin-top:0px;">
                        <p>
                            <button class="btn btn-success" style="margin-top: 10px;margin-bottom: 10px" type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加</button>
                        </p>
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
<!-- 弹出框开始-->
<!-- 按钮触发模态框 -->
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" data-show="path" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:800px">
        <div class="modal-content">
            <div class="modal-header">
            </div>
            <div class="wrapper wrapper-content animated fadeInRight">

                <%--内容添加--%>
                <div class="ibox float-e-margins" id="itemContent" style="width: 770px">
                    <div class="ibox-title">
                        <h5>内容添加</h5>
                    </div>
                    <div class="ibox-content" >
                        <form class="form-horizontal m-t" id="content" method="post" action="" >
                            <input type="hidden" id="itemId1" name="itemId" value="">
                            <input class="userId" name="userId" value="userId" type="hidden">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">版本名称:</label>
                                <div class="col-sm-8">
                                    <input id="versionName" name="versionName" class="form-control" type="text" value="${resource.name}" placeholder="请输入版本名称 如：磕大头">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">文字内容:</label>
                                <div class="col-sm-8">
                                    <input id="text" name="text" class="form-control" type="text" value="${resource.text}" placeholder="请输入纯文字">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">年代：</label>
                                <div class="col-sm-8">
                                    <input class="form-control layer-date" name="yearsStr" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                                    <label class="laydate-icon"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>文档内容：</label>
                                <div class="col-sm-8">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" name="taskFileTxt" class="itemPic oas-filebutton-default" placeholder="请选择文档">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>图片内容：</label>
                                <div class="col-sm-8">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" name="taskFilePic" class="itemPic oas-filebutton-default" placeholder="请选择图片文件">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">来 源：</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="origin" id="origin" value="佛教文库" checked> 佛教文库
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="origin" id="origin1" value="佛教典籍"> 佛教典籍
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">资源版本：</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="version" id="version" value="0" checked> 藏版
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="version" value="1"> 汉版
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">视频版本号：</label>
                                <div class="col-sm-8">
                                    <input id="contentVersion" name="contentVersion" class="form-control" type="text" value="${resource.name}" placeholder="如:供佛（藏）">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>视频文件：</label>

                                <div class="col-sm-6">
                                    <input name="videoFile" class="form-control" type="text" value="${music.videoFile}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="addvideoFile" name="addvideoFile" class="itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-3 control-label">图片版本名称：</label>--%>
                                <%--<div class="col-sm-8">--%>
                                    <%--<input id="picVersionName" name="picVersionName" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入图片版本名称">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">出 处：</label>
                                <div class="col-sm-8">
                                    <input id="source" name="source" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入出处">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">作 者：</label>
                                <div class="col-sm-8">
                                    <input id="author" name="author" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入作者">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">译 者：</label>
                                <div class="col-sm-8">
                                    <input id="translator" name="translator" class="form-control" type="text" value="${resource.sourceKey}"placeholder="请输入译者">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">有无语音识别：</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="voiceCount" id="voiceCount" value="1"> 有
                                    </label>
                                    <label class="radio-inline">
                                        &nbsp;&nbsp;&nbsp;<input type="radio" name="voiceCount" id="voiceCount1" value="2" checked> 没有
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">语音识别名称：</label>
                                <div class="col-sm-8">
                                    <input id="voiceName" name="voiceName" class="form-control" type="text" value="${resource.voiceName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>语音识别字典文件：</label>
                                <div class="col-sm-6">
                                    <input name="voiceDic" class="form-control" type="text" value="${resource.voiceDic}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="fileEdit" name="taskItemPic" class="itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>语音识别模型文件：</label>
                                <div class="col-sm-6">
                                    <input name="voiceLm" class="form-control" type="text" value="${resource.voiceLm}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="fileEdit" name="taskItemPic" class="itemPic oas-filebutton-default">
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
                <button  class="btn btn-primary saveContent" id="btn1" type="button">保存&nbsp;&nbsp;
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body><!-- 弹出框结束-->


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
            url: "${pageContext.request.contextPath}/admin/findAllTaskItem",
            //表格显示条纹
            striped: true,
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
            showToggle:true, //是否显示详细视图和列表视图的切换按钮
            cardView: false, //是否显示详细视图
            //是否启用详细信息视图
            detailView:true,
            detailFormatter:detailFormatter,
            //表示服务端请求
            sidePagination: "server",
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            queryParamsType: "undefined",
            //json数据解析
            responseHandler: function(res) {
                return {
                    "rows": res.rows,
                    "total": res.total
                };
            },
            //数据列
            columns: [{
                title: "资源名称",
                field: "itemName",
                width:100

            },{
                title: "别名",
                field: "aliasName",
                width:130

            },{
                title: "资源介绍",
                field: "itemIntro",
                width:140
            },{
                title: "背景图片",
                field: "itemPic",
                align: "center",
                formatter:function (value,row,index) {
                    return '<img class="itemPic_itemPic" src='+value+' >';
                }
            },{
                title: "资源描述",
                field: "titleDesc",
                width:140

            },{
                title: "目录名称",
                field: "catalogueName",
                width:80

            },{
                title: "是否可供",
                field: "isSupport",
                width:80

            },{
                title: "操作",
                field: "empty",
                formatter: function (value, row, index) {
                    var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="editItem(\''+row.itemId+'\')"><i class="fa fa-edit"></i>&nbsp;修改</button> &nbsp;';
                    operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="del1(\''+row.itemId+'\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;';
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
            url: "${pageContext.request.contextPath}/admin/selectContentById?itemId="+parentid,
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
            columns: [ {
                title: "版本名称",
                field: "versionName"
            }, {
                title: "年代",
                field: "yearsStr",
                sortable: true
            }, {
                title: "出处",
                field: "source"
            }, {
                title: "作者",
                field: "author",
                width: 80
            }, {
                title: "译者",
                field: "translator"
            }, {
                title: "TXT文档",
                field: "fileTxt",
                sortable: true
            }, {
                title: "纯文字",
                field: "text",
                width: 80
            }, {
                title: "图片文件",
                field: "filePic",
                align: "center",
                width: 80,
                formatter:function (value,row,index) {
                    return '<img class="itemPic_itemPic" src='+value+'>';
                }
            },{
                title: "来源",
                field: "origin",
                width: 80
            },{
                title: "音频文件",
                field: "musicFile",
                width: 80
            },{
                title: "视频文件",
                field: "videoFile",
                width: 80
            },{
                title: "操作",
                field: "empty",
                witdth: 300,
                formatter: function (value, row, index) {
                    var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="edit(\'' + row.itemContentId + '\')"><i class="fa fa-edit"></i>修改</button>';
                    operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="del(\'' + row.itemContentId + '\')"><i class="fa fa-remove"></i>删除</button>';
                    return operateHtml;
                }
            }],
            //注册加载子表的事件。注意下这里的三个参数！
            onExpandRow: function (index, row, $detail) {
                InitSubTable(index, row, $detail);
            }
        });
    };


    <!-- 专栏下拉框 -->
    $(document).ready(function () {
        $.ajax({
            type: "POST",
            processData:false,
            contentType:false,
            url:"${pageContext.request.contextPath}/admin/getTree",
            success: function(msg){
                var columnNameList = msg.length;
                for (var i=0; i < columnNameList.length; i++){
                    var id = columnNameList[i].idtSpecialColumn;
                    $("#catalogueId").append("<option name='catalogueId' value="+columnNameList[i].catalogueId+">" + columnNameList[i].catalogueId + "</option>" );
                }

            }
        });
    });

    //        保存内容
    $(document).on("click",".saveContent",function(){
        var userId= window.parent.document.getElementById("userId").defaultValue;
        $(".userId").val(userId)
        var  form = new FormData($(content)[0]);
        layer.msg('正在提交中，请稍等。。。', {
            icon: 16,
            shade: 0.01,
            time:0
        });

        $.ajax({
            type: "POST",
            processData:false,
            contentType:false,
            url: "${pageContext.request.contextPath}/admin/insertTaskContent",
            data:form,
            success: function(msg){
                if (msg.code==1){
                    layer.msg('请先添加功课哦。。。');
                    return;
                }else if (msg.code==2){
                    layer.msg(msg.message, {time: 2000}, function () {
                        $('#table_list').bootstrapTable("refresh");
                        document.getElementById("btn1").disabled = true;
                        layer.close();
                    });
//                        //复制内容模版模块
//                        var html = template('musicTpl');
//                        $(".row").parent().append(html);
//                        document.getElementById("itemId1").value = msg.rows.itemId;
//                        document.getElementById("itemContentId").value = msg.rows.itemContentId;

                }else {
                    layer.msg(msg.message, {time: 2000}, function () {
                        layer.close();
                    });
                }
            }
        });

    });

    //结束添加

    function saveContent(id) {
        document.getElementById("itemId1").value=id;
    }

    //资源内容修改
    function edit(id) {
        window.location='${pageContext.request.contextPath}/admin/initEditContent?itemContentId='+id;

    }

    function editItem(id) {
        var userId = window.parent.document.getElementById("userId").defaultValue;
        layer.open({
            type: 2,
            title: '资源修改',
            shadeClose: true,
            shade: false,
            area: ['893px', '600px'],
            content: '${pageContext.request.contextPath}/admin/initEditItem?itemId='+id+'&userId='+userId,
            end: function (index) {
                $('#table_list').bootstrapTable("refresh");

            }
        });
    }
    //添加窗口打开哦
    function add() {
        window.location='${pageContext.request.contextPath}/view/templates/admin/resource/form.jsp';

    }

    //资源删除
    function del1(id){
        layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            var userId= window.parent.document.getElementById("userId").defaultValue;
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/deleteTaskItem/",
                data:{"itemId":id,"userId":userId},
                success: function(msg){
                    layer.msg(msg.message, {time: 2000},function(){
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            });
        });
    }


    //资源内容删除
    function del(id){
        layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            var userId= window.parent.document.getElementById("userId").defaultValue;
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/deleteTaskContent/",
                data:{"itemContentId":id,"userId":userId},
                success: function(msg){
                    layer.msg(msg.message, {time: 2000},function(){
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            });
        });
    }

    function detailFormatter(index, row) {

        var html = [];
        html.push('<p><b>描述:</b> ' + row.id + '</p>');
        return html.join('');
    }

    //	    目录名称检索
    function search() {
        var catalogueName = $('#catalogueName').val();
        $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchcatalogueName?catalogueName='+encodeURI(encodeURI(catalogueName,"UTF-8"))});
    }

    //        名称搜索
    function eventquery() {
        var itemName = $('#itemName').val();
        $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchItem?itemName='+encodeURI(encodeURI(itemName,"UTF-8"))});
    }

    //        重置搜索
    function reset() {
        var catalogueName = $('#catalogueName').val('');
        var itemName = $('#itemName').val('');
        $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchItem'});
    }

    //模态框关闭初始化事件
    $(function () { $('#myModal').on('hidden.bs.modal', function () {
        document.getElementById("content").reset();
        document.getElementById("btn1").disabled = false;
    })
    });
    /**
     * 功课内容音频文件上传
     */
    $(document).on("change",".addmusicFile",function(){

        var _this = $(this);
        var oMyForm = new FormData();
        if(!!!_this[0].files[0])
            return;
        oMyForm.append("file", _this[0].files[0]);
        layer.msg('正在提交中，请稍等。。。', {
            icon: 16,
            shade: 0.01,
            time:0
        });
        $.ajax({
            type: "POST",
            processData:false,
            contentType:false,
            url: "${pageContext.request.contextPath}/admin/uploadFile",
            data:oMyForm,
            success: function(msg){
                if (msg==''){
                    parent.layer.msg('上传失败，请重新上传。。。');
                    return;
                }
                layer.msg('上传成功。。。', {time: 2000},function(){

                    _this.closest(".form-group").find(".form-control").val(msg);
                    layer.close();
                });
            }
        });

    });
    /**
     * 功课内容视频文件上传
     */
    $(document).on("change",".addvideoFile",function(){

        var _this = $(this);
        var oMyForm = new FormData();
        if(!!!_this[0].files[0])
            return;
        oMyForm.append("file", _this[0].files[0]);
        layer.msg('正在提交中，请稍等。。。', {
            icon: 16,
            shade: 0.01,
            time:0
        });
        $.ajax({
            type: "POST",
            processData:false,
            contentType:false,
            url: "${pageContext.request.contextPath}/admin/uploadFile",
            data:oMyForm,
            success: function(msg){
                if (msg==''){
                    parent.layer.msg('上传失败，请重新上传。。。');
                    return;
                }
                layer.msg('上传成功。。。', {time: 2000},function(){

                    _this.closest(".form-group").find(".form-control").val(msg);
                    layer.close();
                });
            }
        });

    });
    //语音识别上传
    $(document).on("change",".fileEdit",function(){

        var _this = $(this);
        var oMyForm = new FormData();
        if(!!!_this[0].files[0])
            return;
        oMyForm.append("file", _this[0].files[0]);
        layer.msg('正在提交中，请稍等。。。', {
            icon: 16,
            shade: 0.01,
            time:0
        });
        $.ajax({
            type: "POST",
            processData:false,
            contentType:false,
            url: "${pageContext.request.contextPath}/admin/uploadFile",
            data:oMyForm,
            success: function(msg){
                if (msg==''){
                    parent.layer.msg('上传失败，请重新上传。。。');
                    return;
                }
                layer.msg('上传成功。。。', {time: 2000},function(){

                    _this.closest(".form-group").find(".form-control").val(msg);
                    layer.close();
                });
            }
        });
    });
</script>
</body>

</html>
