<%@ page contentType="text/html; charset=utf-8"%>
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
                        <h5>社群管理</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="input-group col-md-3" style="float:left;margin-right:0px;margin-top:10px;margin-bottom: 10px; positon:relative">
                            <input type="text" class="form-control" name="idtAssociation" id="idtAssociation" placeholder="社群ID搜索" />
                            <span class="input-group-btn">
                                 <button class="btn btn-info btn-search" onclick="eventquery();">🔍</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </span>
                        </div>
                            <div class="input-group col-md-3" style="float:left;margin-left:30px;margin-top:10px;margin-bottom: 10px; positon:relative">
                                <input type="text" class="form-control" name="name" id="name" placeholder="社群名称搜索" />
                                <span class="input-group-btn">
                                 <button class="btn btn-info btn-search" onclick="searchName();">🔍</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </span>
                            </div>
                            <div id="toolbar" style="float:left;margin-left: 30px;margin-top:10px;positon:relative">
                                <div class="myBtn-content">
                                    <button type="button" class="btn btn-primary" onclick="reset()">重置</button>
                                </div>
                            </div>
                        <div style="float:left;margin-left: 715px;margin-top:0px;">

                        </div><!---->
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
			    url: "${pageContext.request.contextPath}/admin/findAllAssociation",
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
			        field: "idtAssociation",
			        sortable: true
			    },{
			        title: "社群名称",
			        field: "name"
			    },{
			        title: "社群类型",
			        field: "type"
			    },{
			        title: "建群时间",
			        field: "createTime"
			    },{
			        title: "社群简介",
			        field: "intro"
			    },{
                    title: "创建者名称",
                    field: "userName"
                },{
                    title: "社群人数",
                    field: "count"
                }]
			});
        });

        function detailFormatter(index, row) {
	        var html = [];
	        html.push('<p><b>描述:</b> ' + row.description + '</p>');
	        return html.join('');
	    }

        //ID搜索
        function eventquery() {
            var idtAssociation = $('#idtAssociation').val();
            $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchAllAssociation?idtAssociation=' +idtAssociation });
        }

        //目录名称
        function searchName() {
            var name = $('#name').val();
            debugger
            $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchAllAssociation?name='+encodeURI(encodeURI(name,"UTF-8"))});
        }
        //重置
        function reset() {
            var name = $('#name').val('');
            var idtAssociation = $('#idtAssociation').val('');
            $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchAllAssociation'});
        }
    </script>


    

</body>

</html>
