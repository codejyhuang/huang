
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>活动管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
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
            word-break:keep-all;/* 不换行 */
            white-space:nowrap;/* 不换行 */
            overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
        }
        .table td:hover{
            overflow: scroll;
            white-space: nowrap;
            height: 20px;
        }
    </style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>愿望管理</h5>
                </div>

                <div class="ibox-content">

                    <%--<div id="main" style="margin-left: 32px;margin-top: 45px">--%>
                        <%--<div id="toolbar">--%>
                            <%--<div  class="form-inline form-table">--%>
                                <%--<div class="form-group">--%>
                                    <%--<input type="text" class="form-control search-name" name="bannerTitle" id="bTitle"  placeholder="活动标题" />--%>
                                    <%--<input class="btn btn-primary" type="button" onclick="bannerTe();" value="搜索"/></n>--%>
                                    <%--<input type="text" class="form-control search-name" id="startTimeis"name="startTimeis"--%>
                                           <%--placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"style="width: 180px; margin-left: 16px" />&nbsp;---%>
                                    <%--<input type="text" class="form-control search-name" id="endTimeis" name="endTimeis"--%>
                                           <%--placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" style="width: 180px; margin-left: 16px"  />--%>
                                    <%--<input class="btn btn-primary " type="button"onclick="timeBanner();" value="查询" />--%>
                                    <%--<button type="button" class="btn btn-primary" onclick="reset()">重置</button>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                        <%--</div>--%>
                        <%--<div style="float:right;margin-top:0px;margin-left: 21px">--%>
                            <%--<p>--%>
                                <%--<button class="btn btn-success" style="margin-top: 10px;margin-bottom: 10px" data-toggle="modal" data-backdrop="static" data-target="#myModal"type="button" ><i class="fa fa-plus"></i>&nbsp;添加</button>--%>
                            <%--</p>--%>
                        <%--</div><!---->--%>
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
            contentType: "application/json  ",
            //获取数据的Servlet地址
            url: "${pageContext.request.contextPath}/admin/findAllDesire",
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
            showToggle:true, //是否显示详细视图和列表视图的切换按钮
            cardView: false, //是否显示详细视图
            clickToSelect: true, //是否启用点击选中行
            //是否显示父子表信息
            detailView:false,
            // height: tableHeight(),
            detailFormatter:detailFormatter,
            //分页方式：client客户端分页，server服务端分页（*）
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
                title: "ID",
                field: "id"
            },{
                title: "愿望",
                field: "desire"
            },{
                title: "许愿时间",
                field: "createdTimes"
            }]
        });
    });
    //请求服务数据时所传参数
    function queryParams(params){
        return{
            //每页多少条数据
            pageSize: params.limit,
            //请求第几页
            pageIndex:params.pageNumber,
            Name:$('#search_name').val(),
            Tel:$('#search_tel').val()
        }
    }

    function editBanner(id) {
        layer.open({
            type: 2,
            title: '目录修改',
            shadeClose: true,
            shade: false,
            area: ['893px', '500px'],
            content: '${pageContext.request.contextPath}/admin/findAllBannerById?bannerId='+id,
            end: function (index) {
                $('#table_list').bootstrapTable("refresh");
            }
        });
    }

    //活动删除
    function del(id){
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
                url:"${pageContext.request.contextPath}/admin/deleteAllBanner/",
                data:{"bannerId":id},
                success: function(msg){
                    layer.msg(msg.message, {time: 2000},function(){
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            });
        });
    }
    $(document).ready(function () {
        //活动保存
        $(document).on("click",".insertAllBanner",function(){
            var form = new FormData($(bannerForm)[0]);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/insertAllBanner",
                data:form,
                success: function(msg){
                    if (msg.code==1){
                        layer.msg('编辑内容失败，请联系管理员。。。');
                        return;
                    } else {
                   layer.msg('操作成功');
                        $('#table_list').bootstrapTable("refresh")
                        document.getElementById("btn1").disabled = true;

                    }
                }
            });

        });
    });
//上传文件
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
    //模态框关闭初始化事件
    $(function () { $('#myModal').on('hidden.bs.modal', function () {
//            window.location.reload();
        document.getElementById("bannerForm").reset();
        document.getElementById("btn1").disabled = false;
    })
    });

    function detailFormatter(index, row) {
        var html = [];
        html.push('<p><b>描述:</b> ' + row.description + '</p>');
        return html.join('');
    }
    //活动标题
    function bannerTe() {
        var bannerTitle = $('#bTitle').val();
        $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/findAllBannerByTitle?bannerTitle='+encodeURI(encodeURI(bannerTitle,"UTF-8"))});
    }
    //活动开始时间
    function timeBanner() {
        var startTimeis = $('#startTimeis').val();
        var endTimeis = $('#endTimeis').val();
        $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/findAllBannerByTime?startTimeis=' + startTimeis +'&endTimeis='+endTimeis});

    }
    //重置
    function reset() {
        var startTimeis = $('#startTimeis').val('');
        var endTimeis = $('#endTimeis').val('');
        var bannerTitle = $('#bTitle').val('');
        $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/findAllBannerByTitle'});
    }


</script>


</body>

</html>
