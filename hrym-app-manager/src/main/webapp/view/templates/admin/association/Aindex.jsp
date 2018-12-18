<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>文章列表</title>
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
        /*text-overflow:ellipsis;!* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用*!*/
    }
    .table td:hover{
        overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
        overflow: scroll;   /* 滚动条*/
        white-space: nowrap;
        height: 10px;
    }
</style>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>文章管理</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="input-group col-md-3" style="float:left;margin-right:0px;margin-top:10px;positon:relative">
                        <input type="text" class="form-control" name="articleTitle" id="articleTitle" placeholder="文章名称搜索" />
                            <span class="input-group-btn">
                                 <button class="btn btn-info btn-search" onclick="eventquery();">🔍</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </span>
                        </div>&nbsp;&nbsp;

                        <div class="input-group col-md-3" style="float:left;margin-left:30px;margin-bottom: 15px;margin-top:10px;mpositon:relative">
                            <select class="input-group col-md-3" id="columnName" name="columnName" style="width: 260px;height: 31px">
                                <option name="colu" value="">专栏名称搜索</option>
                            </select>
                            <span class="input-group-btn">
                                 <button class="btn btn-info btn-search" onclick="columnName();">🔍</button>&nbsp;&nbsp;
                            </span>
                        </div>

                        <div id="toolbar" style="float:left;margin-left: 60px;margin-top:10px;positon:relative">
                            <div class="myBtn-content">
                                <button type="button" class="btn btn-primary" onclick="reset()">重置</button>
                            </div>
                        </div>
                        <div style="float:left;margin-left: 180px;margin-top:10px;positon:relative">
                            <div class="myBtn-content">
                                <button class="btn btn-success"  type="button" onclick="window.location='${pageContext.request.contextPath}/view/templates/admin/association/form.jsp'";><i class="fa fa-plus"></i>&nbsp;发表</button>
                            </div>
                        </div><br><br>
                        <div class="ibox-content">
                            <div class="input-group col-md-3" style="float:left;margin-right: 50px;margin-top:10px;positon:relative">
                                <input type="hidden" class="form-control" placeholder="" />

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
			    url: "${pageContext.request.contextPath}/admin/findAllArticle",
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
			    //是否显示父子表信息
			    detailView:false,
                showColumns: true, //是否显示所有的列
                showToggle:true, //是否显示详细视图和列表视图的切换按钮
                cardView: false, //是否显示详细视图
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
                    title: "发表状态",
                    field: "statusArticle",
                    width:90,
                    formatter: function (value, row, index) {
                        if (value == "未发表") {
                            return '<button class="btn btn-success btn-xs" type="button" onclick="pass(\'' + row.idtArticle + '\')"> 未发表</button>';
                        }
                        else if (value == "已发表") {
                            return '<button class="btn btn-primary btn-xs" type="button" disabled="disabled" onclick="pass(\'' + row.idtFeedBack + '\')"> 已发表</button>';
                        }
                    }
                },{
			        title: "专栏名称",
			        field: "columnName",
                    width:90
			    },{
			        title: "文章名称",
			        field: "articleTitle",
                    width:200
			    },{
			        title: "发表时间",
			        field: "createTime",
                    width:130
			    },{
                    title: "文章摘要",
                    field: "articleAbstract",
                    width:200
                },{
                    title: "点击URL预览文章",
                    field: "articleUrl",
                        width:210,
                    formatter:function (value) {
                        if (value==null){
                            return' '
                        }
                        return '<a  href='+value+' width=100 height=50>'+value+'</a>'
                        }
                },{
                    title: "操作",
                    field: "empty",
                        width:110,
                formatter: function (value, row, index) {
                    var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="editArticle(\''+row.idtArticle+'\')"><i class="fa fa-edit"></i>&nbsp;修改</button> &nbsp;';
                    operateHtml=operateHtml +'<button class="btn btn-danger btn-xs" type="button" onclick="del(\''+row.idtArticle+'\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;';
                     return operateHtml;
                    }
                }]
            });
        });

// 分页查询参数，是以键值对的形式设置的
function queryParams(params) {
    return {
    catalogueId : $('#eventqueryform input[name=\'catalogueId\']').val(),	// 请求时向服务端传递的参数
}
}
//修改文章
<%--function editArticle(id) {--%>
<%--window.location='${pageContext.request.contextPath}/admin/editArticleById?idtArticle='+id;--%>

<%--}--%>
        function editArticle(id) {
            layer.open({
                type: 2,
                title: '目录修改',
                shadeClose: true,
                shade: false,
                area: ['793px', '400px'],
                content: '${pageContext.request.contextPath}/admin/editArticleById?idtArticle='+id,
                end: function (index) {
                    $('#table_list').bootstrapTable("refresh");
                }
            });
        }
// 文章发表
function pass(id){
    layer.confirm('此功能不能扯回，确定发表吗?', {icon: 3, title:'提示'}, function(index){
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            url:"${pageContext.request.contextPath}/admin/updateArticle/",
            data:{"idtArticle":id,"statusArticle":"1"},
            success: function(msg){
                layer.msg(msg.message, {time: 2000},function(){
                    $('#table_list').bootstrapTable("refresh");
                    layer.close(index);
                });
            }
        });
    });
}
//文章删除
function del(id){
    layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/admin/deleteAllArticle/",
            data:{"idtArticle":id},
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
    <!-- 专栏下拉框 -->
    $.ajax({
        type: "POST",
        processData:false,
        contentType:false,
        url: "${pageContext.request.contextPath}/admin/findColumnName",
        success: function(msg){
            var columnNameList = msg.rows;
            for (var i=0; i < columnNameList.length; i++){
                var id = columnNameList[i].idtSpecialColumn;
                $("#columnName").append("<option name='columnName'  value="+columnNameList[i].columnName+">" + columnNameList[i].columnName + "</option>" );
            }
        }
    });
});

function detailFormatter(index, row) {
    var html = [];
    html.push('<p><b>描述:</b> ' + row.description + '</p>');
    return html.join('');
}
//文章名称搜索
function eventquery() {
    var articleTitle = $('#articleTitle').val();
    $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchAllArticle?articleTitle='+encodeURI(encodeURI(articleTitle,"UTF-8"))});
    }
//专栏名称搜索
function columnName() {
    var columnName = $('#columnName').val();
    $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchAllSpecialName?columnName='+encodeURI(encodeURI(columnName,"UTF-8"))});
    }
//重置
function reset() {
    var columnName = $('#columnName').val('');
    var articleTitle = $('#articleTitle').val('');
    $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchAllArticle'});
    }

</script>




</body>

</html>
