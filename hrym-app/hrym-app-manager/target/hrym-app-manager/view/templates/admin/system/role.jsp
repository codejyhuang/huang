<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>角色列表</title>
	<meta name="keywords" content="">
	<meta name="description" content="">

	<link rel="shortcut icon" href="favicon.ico">
	<link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

	<link href="${pageContext.request.contextPath}/view/static/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

	<link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">

	<!-- 导入ztree类库 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view/static/assets/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/static/assets/ztree/jquery.ztree.all-3.5.js"></script>

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
					<h5>角色列表</h5>
				</div>
				<div class="ibox-content">
					<div style="float:left;margin-left: 10px;margin-top:0px;">
						<p>
							<button class="btn btn-success" style="margin-top: 10px;margin-bottom: 10px" data-toggle="modal" data-target="#myModal1"type="button" ><i class="fa fa-plus"></i>&nbsp;添加</button>
						</p>
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

<!-- 模态框（Modal） 角色添加 -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="wrapper wrapper-content animated fadeInRight">

				<div class="ibox float-e-margins" id="roleItem">
					<div class="ibox-title">
						<h5>角色添加</h5>
					</div>
					<div class="ibox-content" >
						<form class="form-horizontal m-t" id="roleForm" method="post" action="" >
							<input type="hidden" id="role_id" name="roleId" value="">
							<div class="form-group">
								<label class="col-sm-3 control-label">角色名：</label>
								<div class="col-sm-8">
									<input id="roleName" name="roleName" class="form-control" type="text" value="${list.roleName}" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">描述：</label>
								<div class="col-sm-8">
									<input id="roleDesc" name="roleDesc" class="form-control" type="text" value="${list.roleDesc}" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">菜单授权：</label>
								<div class="col-sm-8">
										<input type="hidden" name="roleMenuIds">
										<div id="menuTree1" class="ztree" ></div>
										<%--<div class="right">--%>
											<%--<input type="hidden" id="py" class="checkbox first" checked />--%>
											<%--<input type="hidden" id="sy" class="checkbox first" checked />--%>
											<%--<input type="hidden" id="pn" class="checkbox first" checked />--%>
											<%--<input type="hidden" id="sn" class="checkbox first" checked />--%>
										<%--</div>--%>
								</div>
							</div>
							<div>每个角色必须选"系统主页"</div>

						</form>
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭&nbsp;&nbsp;</button>
				<button  class="btn btn-primary" id="btn1" type="button" onclick="saveRole();" >保存</button>
			</div>

		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<!-- 模态框（Modal） 菜单展示 -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="wrapper wrapper-content animated fadeInRight">

				<div class="ibox float-e-margins" id="menuItem">
					<div class="ibox-title">
						<h5>角色名称 ${bean.roleName}</h5>
					</div>
					<div class="ibox-content" >
						<div class="form-horizontal m-t">
							<div class="form-group">
								<label class="col-sm-3 control-label">我的菜单：</label>
								<div class="col-sm-8">
									<input type="hidden" name="roleMenuIds">
									<div id="menuTree2" class="ztree" ></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">确定&nbsp;&nbsp;</button>
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
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/peity/jquery.peity.min.js"></script>

<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/view/static/assets/js/content.js?v=1.0.0"></script>
<!-- ztree -->
<script src="${pageContext.request.contextPath}/view/static/assets/ztree/jquery.ztree.all-3.5.js" ></script>

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
            url: "${pageContext.request.contextPath}/admin/role_list",
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
                title: "角色编号",
                field: "roleId",
                sortable: true
            },{
                title: "角色名称",
                field: "roleName"
            },{
				title: "角色描述",
				field: "roleDesc"
			},{
                title: "操作",
                field: "empty",
                formatter: function (value, row, index) {
                    var operateHtml = '<button class="btn btn-success btn-xs" data-toggle="modal" data-backdrop="static"  data-target="#myModal2" type="button" onclick="showMenu(\'' + row.roleId + '\')"><i class="fa fa-save"></i>&nbsp;菜单展示</button> &nbsp;';
                    operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="del(\'' + row.roleId + '\')"><i class="fa fa-remove"></i>删除</button>';
                    return operateHtml;
                }
            }
            ]
        });


    });

    //模态框点击关闭或保存，清空表单内容
    $("#myModal1").on("hidden.bs.modal", function() {
        document.getElementById("roleForm").reset();

    });

    function detailFormatter(index, row) {
        var html = [];
        html.push('<p><b>描述:</b> ' + row.description + '</p>');
        return html.join('');
    }

    //生成菜单树(添加角色)
    $(function() {
        // 授权树初始化
        var setting = {
            data: {
                key: {
                    title: "t"
                },
                simpleData: {
                    enable: true
                }
            },
            check: {
                enable: true,
            }
        };

        var zNodes
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/node_list',
            type: 'POST',
            dataType: 'text',
            success: function (data) {
                zNodes = eval("(" + data + ")");
                $.fn.zTree.init($("#menuTree1"), setting, zNodes);
            },
            error: function (msg) {
                alert('树加载异常!');
            }
        });
    });

    //生成菜单树（菜单展示）
    function showMenu(id) {
        debugger
        // 授权树初始化
        var setting = {
            data: {
                key: {
                    title: "t"
                },
                simpleData: {
                    enable: true
                }
            }
        };

        var zNodes;
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/showMenuById?roleId='+id,
            type: 'POST',
            dataType: 'text',
            success: function (data) {
                zNodes = eval("(" + data + ")");
                $.fn.zTree.init($("#menuTree2"), setting, zNodes);
            }
        });
    };


    //保存用户和对应的菜单
    function saveRole() {
        // 获取ztree勾选节点集合
        var treeObj = $.fn.zTree.getZTreeObj("menuTree1");
        var nodes = treeObj.getCheckedNodes(true);
        // 获取所有节点id
        var array = new Array();
        for(var i=0 ; i< nodes.length ; i++){
            array.push(nodes[i].id);
        }
        var roleMenuIds = array.join(",");
        $("input[name='roleMenuIds']").val(roleMenuIds);


        var form = new FormData($(roleForm)[0]);
        layer.msg('正在提交中，请稍等。。。', {
            icon: 16,
            shade: 0.01,
            time:0
        });

        $.ajax({
            type: "POST",
            processData:false,
            contentType:false,
            url: "${pageContext.request.contextPath}/admin/role_save",
            data:form,
            success: function(msg){
                debugger
                if (msg.code==0){
                    layer.msg(msg.message, {time: 2000},function(){
                        document.getElementById("btn1").disabled=true;
                        layer.close();
                        $('#table_list').bootstrapTable("refresh");
                        return;
                    });
                }else {
                    layer.msg(msg.message, {time: 2000},function(){
                        layer.close();
                        $('#table_list').bootstrapTable("refresh");
                    });
                }

            }
        });
    }

    //角色删除
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
                url: "${pageContext.request.contextPath}/admin/deleteRoleByRoleId/",
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
</script>




</body>

</html>
