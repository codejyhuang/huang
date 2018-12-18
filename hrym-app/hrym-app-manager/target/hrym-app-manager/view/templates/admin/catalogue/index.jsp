<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    }
    .table td:hover{
        overflow: scroll;
        white-space: nowrap;
        height: 20px;
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
                        <div class="input-group col-md-3" style="float:left;margin-right:0px;margin-top:10px;positon:relative">
                            <select class="input-group col-md-3" id="level"name="level" style="width: 250px;height: 30px">
                                <option value="">请选择</option>
                                <option name="level" value="1">一级目录</option>
                                <option name="level" value="2">二级目录</option>
                                <option name="level" value="3">三级目录</option>
                            </select>
                            <%--<input type="text" class="form-control" name="level" id="level" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" placeholder="类目层级搜索" />--%>
                            <span class="input-group-btn">
                                 <button class="btn btn-info btn-search" onclick="searchlevel();">🔍</button>&nbsp;&nbsp;
                            </span>
                        </div>
                        <div class="input-group col-md-3" style="float:left;margin-left:40px;margin-top:10px;positon:relative">
                            <input type="text" class="form-control" name="catalogueName" id="catalogueName"  placeholder="目录名称" />
                            <span class="input-group-btn">
                                 <button class="btn btn-info btn-search" onclick="catalogueName();">🔍</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </span>
                        </div>
                        <div id="toolbar" style="float:left;margin-left: 30px;margin-top:10px;positon:relative">
                            <div class="myBtn-content">
                                <button type="button" class="btn btn-primary" onclick="reset()">重置</button>
                            </div>
                        </div>

                        <div style="float:right;margin-top:0px;margin-left: 21px">
                            <p>
                                <button class="btn btn-success" style="margin-top: 10px;margin-bottom: 10px" type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加</button>
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
			        field: "catalogueId",
			        sortable: true
			    },{
			        title: "目录名称",
			        field: "catalogueName"
			    },{
			        title: "目录层级",
			        field: "level"
			    },{
			        title: "目录描述",
			        field: "catalogueDesc"
			    },{
                    title: "目录简介",
                    field: "introduceInfo"
                },{
			        title: "创建时间",
			        field: "creatTime"
			    },{
			        title: "创建者",
			        field: "creator"
			    },{
                    field: "img",
                    title: "背景图",
                    align: "center",
                    formatter:function (value) {
                        return '<img class="itemPic_itemPic"  src='+value+' >';
                    }
                },{
			        title: "操作",
			        field: "empty",
                    formatter: function (value, row, index) {
                    	var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="edit(\''+row.catalogueId+'\')"><i class="fa fa-edit"></i>&nbsp;编辑</button> &nbsp;';
                    	operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="del(\''+row.catalogueId+'\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;';
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

            function edit(id) {
                var userId= window.parent.document.getElementById("userId").defaultValue;
                layer.open({
                    type: 2,
                    title: '目录修改',
                    shadeClose: false,
                    isOutAnim: true,
                    fixed: false,
//                    shade: false,
                    shade: [0.1, '#393D12'],
                    anim: 4,
                    maxmin: true,
                    scrollbar:true,
                    area: ['893px', '500px'],
                    content: '${pageContext.request.contextPath}/admin/initEditCatalogue?catalogueId='+id+'&userId='+userId,
                    end: function (index) {
                        $('#table_list').bootstrapTable("refresh");
                    }
                });
        }
        function add(){
            window.location='${pageContext.request.contextPath}/view/templates/admin/catalogue/form.jsp';

        }
        function del(id){
        	layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){

                //子iframe页面获取父iframe页面值
                var userId= window.parent.document.getElementById("userId").defaultValue;
        		$.ajax({
                    type: "GET",
                    dataType: "json",
                    contentType: "application/json",
                    url:"${pageContext.request.contextPath}/admin/deleteTaskType/",
                    data:{"catalogueId":id,"userId":userId},
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
	        html.push('<p><b>描述:</b> ' + row.description + '</p>');
	        return html.join('');
	    }
	    //ID搜索
	    function eventquery() {
            var catalogueId = $('#catalogueId').val();
            $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchCategory?catalogueId=' +catalogueId });
        }

        //目录名称
        function catalogueName() {
            var catalogueName = $('#catalogueName').val();
            $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchCategory?catalogueName='+encodeURI(encodeURI(catalogueName,"UTF-8"))});
        }
        //类目层级
        function searchlevel() {
            var level = $('#level').val();
            $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchCategory?level='+level });
        }
        //重置
        function reset() {
            var catalogueName = $('#catalogueName').val('');
            var catalogueId = $('#catalogueId').val('');
            var level = $('#level').val('');
            $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/searchCategory'});
        }
    </script>
</body>

</html>
