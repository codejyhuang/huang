<%--suppress ALL --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

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
    <link href="${pageContext.request.contextPath}/view/templates/admin/newResource/bookContent.jsp" rel="stylesheet">


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
<c:import url="bookContent.jsp"></c:import>
<c:import url="meditationContent.jsp"></c:import>
<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>资源管理</h5>
                </div>
                <div class="ibox-content">
                    <div id="toolbar" style="float:left;margin-top:10px;positon:relative;">
                        <div class="myBtn-content">
                            <button type="button" id="a" class="btn btn-primary" value="10000" disabled="true" onclick="chose(value)">功课</button>
                        </div>
                    </div>
                    <div id="toolbar" style="float:left;margin-left: 20px;margin-top:10px;positon:relative">
                        <div class="myBtn-content">
                            <button type="button" id="b" class="btn btn-success" value="10006" onclick="chose(value)">诵经</button>
                        </div>
                    </div>
                    <div id="toolbar" style="float:left;margin-left: 20px;margin-top:10px;positon:relative">
                        <div class="myBtn-content">
                            <button type="button" id="c" class="btn btn-primary" value="10007" onclick="chose(value)">禅修</button>
                        </div>
                    </div>
                    <div class="input-group col-md-3" style="float:left;margin-left:50px;margin-top:10px;positon:relative">
                        <input type="text" name="itemName" id="itemName" class="form-control" placeholder="资源名搜索" />
                        <span class="input-group-btn">
                                 <button id="e" class="btn btn-info btn-search" value="10006" onclick="eventquery(value);">🔍</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td>
                            </span>
                    </div>
                    <div id="toolbar" style="float:left;margin-left: 30px;margin-top:10px;positon:relative">
                        <div class="myBtn-content">
                            <button type="button" id="d" class="btn btn-primary" value="10006" onclick="reset(value)">重置</button>
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
<%--内容添加--%>
<div class="modal fade" id="myModal" data-show="path" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:800px">
        <div class="modal-content">
            <div class="modal-header">
            </div>
            <div class="wrapper wrapper-content animated fadeInRight">

                <%--内容添加--%>
                <div class="content"></div>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


</body><!-- 弹出框结束-->

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/bootstrap.min.js?v=3.3.6"></script>

<%--引入复制js--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/view/oasis/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/view/oasis/js/oasis.js"></script>

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
        //获取地址栏的信息
        function GetQueryString(name){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)return  unescape(r[2]); return null;
        }
        var typeId = GetQueryString('typeId');
        if (typeId != null){
            chose(typeId)
        }else {
            //复制内容模版模块
            var html = template('bookContentTpl');
            $(".content").parent().append(html);
            document.getElementById('btn2').style.display="none";
        }

        typeId = document.getElementById("d").value;
        //初始化表格,动态从服务器加载数据
        $("#table_list").bootstrapTable({
            //使用get请求到服务器获取数据
            type: "POST",
            //必须设置，不然request.getParameter获取不到请求参数
            contentType: "application/json",
            //获取数据的Servlet地址
            url: "${pageContext.request.contextPath}/admin/findAllByType?typeId="+typeId,
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
                var typeId = document.getElementById("d").value;
                if (typeId == 10006){
                    InitSubTable(index, row, $detail,typeId);
                }else if (typeId == 10007){
                    InitSubTable2(index, row, $detail,typeId);
                }
            }
        });

    });

    //初始化子表格(无线循环)
    InitSubTable = function (index, row, $detail,typeId) {
        var parentid = row.itemId;
        var cur_table = $detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            //使用get请求到服务器获取数据
            type: "POST",
            //必须设置，不然request.getParameter获取不到请求参数
            contentType: "application/json",
            //获取数据的Servlet地址
            url: "${pageContext.request.contextPath}/admin/findContentById?itemId="+parentid+"&typeId="+typeId,
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
                    "rows": res.rows.content,
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
        });
    };

    //初始化子表格(无线循环)
    InitSubTable2 = function (index, row, $detail,typeId) {
        var parentid = row.itemId;
        var cur_table = $detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            //使用get请求到服务器获取数据
            type: "POST",
            //必须设置，不然request.getParameter获取不到请求参数
            contentType: "application/json",
            //获取数据的Servlet地址
            url: "${pageContext.request.contextPath}/admin/findContentById?itemId="+parentid+"&typeId="+typeId,
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
                    "rows": res.rows.content,
                    "total": res.total
                };
            },
            //数据列
            columns: [ {
                title: "版本名称",
                field: "contentName"
            },{
                title: "音频文件",
                field: "musicFile",
                width: 80
            },{
                title: "音频字幕",
                field: "musicSubtitle",
                width: 80
            },{
                title: "结束音频文件",
                field: "endMusicFile",
                width: 80
            },{
                title: "视频文件",
                field: "videoFile",
                width: 80
            },{
                title: "单曲循环切入时间",
                field: "settingTime",
            },{
                title: "固定音频时长",
                field: "fixedMusicTime",
                formatter:function (value,row,index) {
                    return value+'分钟';
                }
            },{
                title: "固定视频时长",
                field: "fixedVideoTime",
                formatter:function (value,row,index) {
                    return value+'分钟';
                }
            },{
                title: "音频时间是否可编辑",
                field: "isMusicEdit",
                formatter:function (value,row,index) {
                    if (value == 0){
                        return "不可编辑";
                    }
                    if (value == 1){
                        return "可编辑";
                    }
                }
            },{
                title: "版本号",
                field: "versionId",
            },{
                title: "止静声音开始",
                field: "voiceStart",
            },{
                title: "止静声音结束",
                field: "voiceDown",
            },{
                title: "操作",
                field: "empty",
                witdth: 300,
                formatter: function (value, row, index) {
                    var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="edit(\'' + row.contentId + '\')"><i class="fa fa-edit"></i>修改</button>';
                    operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="del(\'' + row.contentId + '\')"><i class="fa fa-remove"></i>删除</button>';
                    return operateHtml;
                }
            }],
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
        var typeId = document.getElementById("d").value;
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
            url: "${pageContext.request.contextPath}/admin/saveTaskContent?typeId="+typeId,
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
        document.getElementById("itemId").value=id;
    }

    //资源内容修改
    function edit(id) {
        var typeId = document.getElementById("d").value;
        window.location='${pageContext.request.contextPath}/admin/getEditContent?itemContentId='+id+'&typeId='+typeId;

    }

    function editItem(id) {
        var userId = window.parent.document.getElementById("userId").defaultValue;
        var typeId = document.getElementById("d").value;
        layer.open({
            type: 2,
            title: '资源修改',
            shadeClose: true,
            shade: false,
            area: ['893px', '600px'],
            content: '${pageContext.request.contextPath}/admin/getEditItem?itemId='+id+'&userId='+userId+'&typeId='+typeId,
            end: function (index) {
                $('#table_list').bootstrapTable("refresh");

            }
        });
    }
    //添加窗口打开哦
    function add() {
        var typeId = document.getElementById("d").value;
        window.location='${pageContext.request.contextPath}/view/templates/admin/newResource/form.jsp?typeId='+typeId;

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
            var typeId = document.getElementById("d").value;
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/removeTaskItem/",
                data:{"itemId":id,"userId":userId,"typeId":typeId},
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
            var typeId = document.getElementById("d").value;
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/removeTaskContent/",
                data:{"itemContentId":id,"userId":userId,"typeId":typeId},
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
    <%--function search() {--%>
        <%--var catalogueName = $('#catalogueName').val();--%>
        <%--$('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchcatalogueName?catalogueName='+encodeURI(encodeURI(catalogueName,"UTF-8"))});--%>
    <%--}--%>

    //        名称搜索
    function eventquery(typeId) {
        var itemName = $('#itemName').val();
        $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchResource?itemName='+encodeURI(encodeURI(itemName,"UTF-8"))+'&'+'typeId='+typeId});
    }

    //        重置搜索
    function reset(typeId) {
        var catalogueName = $('#catalogueName').val('');
        var itemName = $('#itemName').val('');
        $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/findAllByType?typeId='+typeId});
    }
    function chose(typeId) {
        document.getElementById("d").value = typeId;
        document.getElementById("e").value = typeId;
        document.getElementById("a").className = "btn btn-primary";
        document.getElementById("c").className = "btn btn-primary";
        document.getElementById("b").className = "btn btn-primary";
        $("#tag").remove();
        if (typeId == 10000){
            document.getElementById("a").className = "btn btn-success";
        }
        if (typeId == 10006){
            document.getElementById("b").className = "btn btn-success";
            //复制内容模版模块
            var html = template('bookContentTpl');
            $(".content").parent().append(html);
            document.getElementById('btn2').style.display="none";
        }
        if (typeId == 10007){
            document.getElementById("c").className = "btn btn-success";
            //复制内容模版模块
            var html = template('meditationContentTpl');
            $(".content").parent().append(html);
            document.getElementById('btn2').style.display="none";
        }
        $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/findAllByType?typeId='+typeId});
    }

    //模态框关闭初始化事件
    $(function () { $('#myModal').on('hidden.bs.modal', function () {
        document.getElementById("content").reset();
        document.getElementById("btn1").disabled = false;
        })
    });


    /**
     * 文件上传
     */
    $(document).on("change",".uploadFile",function(){

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
