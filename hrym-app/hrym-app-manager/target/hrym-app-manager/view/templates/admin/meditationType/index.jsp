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
        .lw{
            width: 400px;
        }

    </style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>共修活动管理</h5>
                </div>
                <div class="ibox-content">
                    <div  class="form-inline form-table">
                        <div class="form-group">

                            <input type="text" class="form-control search-name" name="meditationTypeName" id="meditationTypeName"  placeholder="共修类型名称搜索" />&nbsp;&nbsp;
                            <input class="btn btn-primary" type="button" onclick="eventquery();" value="搜索"/></n>&nbsp;&nbsp;
                            <button type="button" class="btn btn-primary" onclick="reset()" >重置</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button class="btn btn-success" style="margin-top: 10px;margin-bottom: 10px" type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;共修类型添加</button>
                        </div>
                    </div>
                    <div style="float:left;margin-left: 210px;margin-top:0px;">
                        <p>
                        </p>
                    </div>
                    <div class="row row-lg">
                        <div class="col-sm-12">
                            <!-- Example Card View -->
                            <div class="example-wrap">
                                <div class="example">
                                    <table id="table_list">
                                        <div id="userCount" style="float: left">当前总用户数：</div>
                                        <div id="medCount" style="margin-left: 150px" >参加共修人数：</div>
                                    </table>
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
                        <h5>共修内容添加</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="content" method="post" action="" >
                            <input type="hidden" name="scheduleId" value="" id="scheduleId">
                            <input id="meditationTypeId" name="meditationTypeId" type="hidden" value="">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">共修内容名称:</label>
                                <div class="col-sm-8">
                                    <input id="activeTitle" name="activeTitle" class="form-control" type="text" value="" placeholder="请输入共修内容名称 ">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">内容类型版本：</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="scheduleType" id="scheduleType"  value="0" checked> 共修报数
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="scheduleType" id="scheduleType1" value="1"> 其他
                                    </label>
                                </div>
                            </div>
                            <div class="form-group targetNumber">
                                <label class="col-sm-3 control-label">完成目标数:</label>
                                <div class="col-sm-8">
                                    <input id="targetNumber" name="targetNumber" class="form-control" type="number" value="" placeholder="请输入数字。。">
                                </div>
                            </div>
                            <div class="form-group refreshTime">
                                <label class="col-sm-3 control-label">刷新时间:</label>
                                <div class="col-sm-8">
                                    <input id="refreshTime" name="refreshTime" class="form-control" type="number" value="" placeholder="请输入数字。。">
                                </div>
                            </div>
                            <div class="form-group refreshTime">
                                <label class="col-sm-3 control-label">法本预习开始时间：</label>
                                <div class="col-sm-8">
                                    <input class="form-control layer-date" id="lawTimes" name="lawTimes" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                                    <label class="laydate-icon"></label>
                                </div>
                            </div>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-3 control-label">开始共修时间：</label>--%>
                                <%--<div class="col-sm-8">--%>
                                    <%--<input class="form-control layer-date" id="startTimes" name="startTimes" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">--%>
                                    <%--<label class="laydate-icon"></label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-3 control-label">共修结束时间：</label>--%>
                                <%--<div class="col-sm-8">--%>
                                    <%--<input class="form-control layer-date" id="expectTimes" name="expectTimes" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">--%>
                                    <%--<label class="laydate-icon"></label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        </form>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="hiee();">关闭
                </button>
                <button  class="btn btn-primary saveMeduSched" id="btn1" type="button">保存&nbsp;&nbsp;
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
            url: "${pageContext.request.contextPath}/admin/findAllMeditation",
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
                title: "共修活动类别ID",
                field: "meditationTypeId",
                width:100

            },{
                title: "共修活动类型名称",
                field: "meditationTypeName",
                width:130

            },{
                title: "共修活动类别简介",
                field: "meditationTypeIntro",
                width:140
            },{
                title: "组织者",
                field: "author",
                width:140

            },{
                title: "参加人数",
                field: "count",
                width:140

            },{
                title: "创建时间",
                field: "createdTimes",
                width:80

            },{
                title: "更新时间",
                field: "updateTimes",
                width:80

            },{
                title: "操作",
                field: "empty",
                formatter: function (value, row, index) {
                    var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="editType(\''+row.meditationTypeId+'\')"><i class="fa fa-edit"></i>&nbsp;修改</button> &nbsp;';
                    operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="delType(\''+row.meditationTypeId+'\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;';
                    operateHtml = operateHtml + '<button  class="btn btn-sucess btn-xs" data-toggle="modal" data-backdrop="static" data-target="#myModal"  type="button" onclick="saveMSchedule(\'' + row.meditationTypeId + '\')">添加内容</button>';
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
        var parentid = row.meditationTypeId;
        var cur_table = $detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            //使用get请求到服务器获取数据
            type: "POST",
            //必须设置，不然request.getParameter获取不到请求参数
            contentType: "application/json",
            //获取数据的Servlet地址
            url: "${pageContext.request.contextPath}/admin/findAllMedSchedule?meditationTypeId="+parentid,
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
                title: "共修内容ID",
                field: "scheduleId"
            }, {
                title: "共修内容名称",
                field: "activeTitle",
                sortable: true
            }, {
                title: "目标数",
                field: "targetNumber"
            }, {
                title: "创建时间",
                field: "createdTimes",
                width: 80
            }, {
                title: "更新时间",
                field: "updateTimes"
            }, {
                title: "共修开始时间",
                field: "startTimes",
                sortable: true
            }, {
                title: "共修结束时间 ",
                field: "expectTimes",
                width: 80
            },{
                title: "预习开始时间",
                field: "lawTimes",
                width:140

            },{
                title: "共修活动类型",
                field: "meditationTypeName",
                width: 80
            },{
                title: "共修活动简介",
                field: "scheduleIntro",
                width: 80
            },{
                title: "已完成数",
                field: "realNumber",
                width: 80
            },{
                title: "操作",
                field: "empty",
                witdth: 300,
                formatter: function (value, row, index) {
                    var operateHtml = '<button class="btn btn-primary btn-xs" data-toggle="modal" data-backdrop="static" data-target="#myModal"  type="button" onclick="editSchedule(\'' + row.scheduleId + '\')"><i class="fa fa-edit"></i>修改</button>';
                    operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="delSchedule(\'' + row.scheduleId+ '\')"><i class="fa fa-remove"></i>删除</button>';
//                    operateHtml = operateHtml + '<button  class="btn btn-sucess btn-xs" data-toggle="modal" data-backdrop="static" data-target="#myModal"  type="button" onclick="saveMSchedule(\'' + row.scheduleId + '\')">添加内容</button>';
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


    //打开模态框前操作事件
    function saveMSchedule(id) {

        $("#meditationTypeId").val(id);
    }

    //        保存内容
    $(document).on("click",".saveMeduSched",function(){
        if (scheduleId.value.length>0){
            url="${pageContext.request.contextPath}/admin/updateMedSchedule"
        }else {
            url="${pageContext.request.contextPath}/admin/insertMedSchedule"
        }
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
            url:url,
            data:form,
            success: function(msg){
                    layer.msg(msg.message, {time: 2000}, function () {
                        $('#table_list').bootstrapTable("refresh");
                        document.getElementById("btn1").disabled = true;
                        window.location.reload();
                        layer.close();
                    });
            }
        });

    });

    function editType(id) {
        layer.open({
            type: 2,
            title: '资源修改',
            shadeClose: true,
            shade: false,
            area: ['893px', '600px'],
            content: '${pageContext.request.contextPath}/admin/findMeditationById?id='+id,
            end: function (index) {
                $('#table_list').bootstrapTable("refresh");

            }
        });
    }
    function add() {
        layer.open({
            type: 2,
            title: '共修类别添加',
            shadeClose: true,
            shade: false,
            area: ['893px', '550px'],
            content: '${pageContext.request.contextPath}/view/templates/admin/meditationType/form.jsp',
            end: function (index) {
                $('#table_list').bootstrapTable("refresh");

            }
        });
    }

    //共修类型删除
    function delType(id){
        layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/deleteMeditation/",
                data:{"id":id},
                success: function(msg){
                    layer.msg(msg.message, {time: 2000},function(){
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            });
        });
    }


    //共修内容内容删除
    function delSchedule(id){
        layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/deleteMedSchedule/",
                data:{"id":id},
                success: function(msg){
                    layer.msg(msg.message, {time: 2000},function(){
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            });
        });
    }
    <!-- 资源专区内容下拉框 -->
    $(document).ready(function getTypeId(value) {
        <!-- 清除专区下拉框的内容-->
        $("#version").empty();
        $("#itemId").empty();
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/admin/findAllMeditationType",
            data:{"Id":value},
            success: function(msg){
                var columnNameList = msg.rows;
                var value = columnNameList[0].meditationTypeId;
                for (var i=0; i < columnNameList.length; i++){
                    $("#meditationTypeId").append("<option name='meditationTypeId'  value="+columnNameList[i].meditationTypeId+">" + columnNameList[i].meditationTypeName + "</option>" );
                }
            }
        });
    });


    function detailFormatter(index, row) {

        var html = [];
        html.push('<p><b>描述:</b> ' + row.id + '</p>');
        return html.join('');
    }
    //模态框关闭初始化事件
    $(function () { $('#myModal').on('hidden.bs.modal', function () {

        $("#scheduleType").attr("checked","checked");
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
    <!--根据ID查找属性内容（编辑）-->
    function editSchedule(id) {

        $.ajax({
            type:"get",
            dataType:"json",
            contentType:"application/json",
            url :"${pageContext.request.contextPath}/admin/MedAcheduleById/",
            data:{"id":id},
            success:function (msg) {
                var divcss = {
                    display:'none'
                };
                var divcss1 = {
                    display:''
                };
                $(".refreshTime").css(divcss);
                $("#meditationTypeId").val(msg.meditationTypeId);
                $("#activeHeadUrl").val(msg.activeHeadUrl);
                $("#scheduleIntro").val(msg.scheduleIntro);
                $("#targetNumber").val(msg.targetNumber);
                $("#activeTitle").val(msg.activeTitle);
                $("#expectTimes").val(msg.expectTimes);
                $("#scheduleId").val(msg.scheduleId);
                $("#startTimes").val(msg.startTimes);
                $("#targetNumber").val(msg.targetNumber);
                $("#scheduleId").val(msg.scheduleId);
                $("#refreshTime").val(msg.refreshTime);
                $("#lawTimes").val(msg.lawTimes);
                if(msg.scheduleType == 1){
                    $("#scheduleType1").attr("checked","checked");
                    $(".targetNumber").css(divcss);
                    $(".refreshTime").css(divcss1);

                }else{
                    $("#scheduleType").attr("checked","checked");
                    $(".targetNumber").css(divcss1);
                    $(".refreshTime").css(divcss);
                }
                //下拉框默认选中事件
//                $("#meditationTypeId").append("<option name='meditationTypeId' aria-checked='true'  value="+msg.meditationTypeId+">"+msg.meditationTypeName+"</option>" );
                var option_all = $("#meditationTypeId option")
                for(var i=0;i<option_all.length;i++){
//                        console.log(option_all[i].selected='selected');
                    if(option_all[i].value==msg.meditationTypeId){
                        option_all[i].selected='selected'
                    }
                }
            }
        });
    }
    $(document).ready(function () {
            var divcss = {
                display:'none'
            };
            $(".refreshTime").css(divcss);

        });
    $(document).ready(function () {
        $.ajax({
            type:"get",
            dataType:"json",
            contentType:"application/json",
            url :"${pageContext.request.contextPath}/admin/findAllWechatUserCount/",
            data:{"id":"w"},
            success:function (msg) {
                $("#userCount").val(msg.rows.count);
                document.getElementById('userCount').innerHTML='注册总用户数：'+msg.rows.count,
                document.getElementById('medCount').innerHTML='参加总人数：'+msg.rows.medCount
            }
        })
    });

    // 单多版本属性选择
    $(function(){

        $(":radio").click(function(){

            var divcss = {
                display:'none'
            };
            var divcss1 = {
                display:''
            };
            if ($(this).val() ==0){
                $(".refreshTime").css(divcss);
                $(".targetNumber").css(divcss1);
                $("#refreshTime").val("");
                $("#lawTimes").val("");

            }else {
                $("#targetNumber").val("");
                $(".targetNumber").css(divcss);
                $(".refreshTime").css(divcss1);
            }
        });
    });

    //搜索
    function eventquery() {

        var Name = $('#meditationTypeName').val();
        $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/findMeditationByName?meditationTypeName='+encodeURI(encodeURI(Name,"UTF-8"))});
    }
    //重置
    function reset() {

        var nickName = $('#meditationTypeName').val('');
        $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/findMeditationByName'});
    }

    function hiee() {
        $('#table_list').bootstrapTable("refresh");
    }
</script>

</body>

</html>
