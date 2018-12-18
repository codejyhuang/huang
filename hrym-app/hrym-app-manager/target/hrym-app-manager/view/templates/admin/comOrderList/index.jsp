<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>商品类目</title>
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
    /*设置input的内容*/
    .lW{
        width: 60%;
        margin-left: 3%;
    }
    /*模态框样式*/
    .modal-style{
        border:0px solid #bbe1f1;
        background:#eefaff;

    }
</style>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>商品类目</h5>
                    </div>
                    <div class="ibox-content">
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
    <div>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content modal-style">
                    <div class="modal-body">

                        <h2 class="text-center text-info">
                            <strong>商品信息详情</strong>
                        </h2>
                        <table class="table table-hover">
                            <thead class="lW">
                            <tr>
                                <th>大小</th>
                                <th>颜色</th>
                                <th>款式</th>
                                <th>版本</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>1</td>
                                <td>TB - Monthly</td>
                                <td>01/04/2012</td>
                                <td>Default</td>
                            </tr>
                            <tr class="success">
                                <td>2</td>
                                <td>TB - Monthly</td>
                                <td>02/04/2012</td>
                                <td>Approved</td>
                            </tr>
                            <tr class="error">
                                <td>3</td>
                                <td>TB - Monthly</td>
                                <td>03/04/2012</td>
                                <td>Declined</td>
                            </tr>
                            <tr class="warning">
                                <td>4</td>
                                <td>TB - Monthly</td>
                                <td>04/04/2012</td>
                                <td>Pending</td>
                            </tr>
                            <tr class="info">
                                <td>5</td>
                                <td>TB - Monthly</td>
                                <td>05/04/2012</td>
                                <td>Call in to confirm</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
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
			    url: "${pageContext.request.contextPath}/admin/category",
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
			        title: "订单号",
			        field: "catalogueId",
			        sortable: true
			    },{
			        title: "创建时间",
			        field: "catalogueName"
			    },{
			        title: "最近更新时间",
			        field: "level"
			    },{
                    title: "订单状态",
                    field: "level"
                },{
                    title: "收货人电话",
                    field: "level"
                },{
                    title: "收货人地址",
                    field: "level"
                },{
                    title: "收货人",
                    field: "level"
                },{
                    title: "物流公司",
                    field: "level"
                },{
                    title: "物流单号",
                    field: "level"
                },{
			        title: "商品信息",
			        field: "catalogueDesc",
                    formatter: function (value) {
                        var operateHtml = '<button class="btn btn-primary btn-xs" type="button" data-toggle="modal" data-backdrop="static" data-target="#myModal" onclick="edit(\''+value+'\')">' +
                            '<i class="fa"></i>&nbsp;商品属性详情</button> &nbsp;';
                        return operateHtml;
                    }
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
        function edit() {
            debugger
            $("#tt2").hide();
            $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            url:"${pageContext.request.contextPath}/admin/categoryq",
            data:{"catalogueId":id},
            success: function(msg){
            layer.msg(msg.message, {time: 2000},function(){
            $('#table_list').bootstrapTable("refresh");
            layer.close(index);
            });
            }
            });

        }
        <%--function del(id){--%>
        	<%--layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){--%>
                <%--layer.msg('正在提交中，请稍等。。。', {--%>
                    <%--icon: 16,--%>
                    <%--shade: 0.01,--%>
                    <%--time:0--%>
                <%--});--%>
        		<%--$.ajax({--%>
                    <%--type: "GET",--%>
                    <%--dataType: "json",--%>
                    <%--contentType: "application/json",--%>
                    <%--url:"${pageContext.request.contextPath}/admin/de/",--%>
                    <%--data:{"catalogueId":id},--%>
                    <%--success: function(msg){--%>
	 	   	    			<%--layer.msg(msg.message, {time: 2000},function(){--%>
	 	   	    				<%--$('#table_list').bootstrapTable("refresh");--%>
	 	   	    				<%--layer.close(index);--%>
	 	   					<%--});--%>
    	    		   <%--}--%>
    	    	<%--});--%>
       		<%--});--%>
        <%--}--%>

        function detailFormatter(index, row) {
	        var html = [];
	        html.push('<p><b>描述:</b> ' + row.description + '</p>');
	        return html.join('');
	    }
        //模态框关闭初始化事件
        $(function () { $('#myModal').on('hidden.bs.modal', function () {
            document.getElementById("model_from").reset();
            document.getElementById("btn").disabled = false;
        })
        });
    </script>
</body>

</html>
