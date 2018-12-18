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
		.size{
			width: 70px;
			height: 10px;
			font-size: 8px;
			padding-top: -10px;
		}
	</style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox ">
				<div class="ibox-title">
					<h5>后台用户列表</h5>
				</div>
				<div class="ibox-content">
					<div class="input-group col-md-3" style="float:left;margin-right:0px;margin-top:10px;margin-bottom: 10px; positon:relative">
						<input type="text" class="form-control" name="name" id="name" placeholder="用户名搜索" />
						<span class="input-group-btn">
                                 <button class="btn btn-info btn-search" onclick="eventquery();">🔍</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </span>
					</div>
					<div id="toolbar" style="float:left;margin-left: 30px;margin-top:10px;">
						<div class="myBtn-content">
							<button type="button" class="btn btn-primary" onclick="reset()">重置</button>
						</div>
					</div>
					<div style="float:left;margin-left: 150px;margin-top:0px;">
						<p>
							<button class="btn btn-success" style="margin-top: 10px;margin-bottom: 10px" data-toggle="modal" data-backdrop="static"  data-target="#myModal1" type="button" ><i class="fa fa-plus"></i>&nbsp;添加</button>
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

<!-- 模态框（Modal）  添加用户 -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="ibox float-e-margins" id="userItem">
					<div class="ibox-title">
						<h5>用户添加</h5>
					</div>
					<div class="ibox-content" >
						<form class="form-horizontal m-t" id="userForm" method="post" action="">
							<input type="hidden" id="user_id" name="user_id" value="">
							<input type="hidden"  name="status" value="0">
							<div class="form-group">
								<label class="col-sm-3 control-label">用户名：</label>
								<div class="col-sm-8">
									<input id="username" name="username" class="form-control" onblur="checkUsername()" type="text" value="${list.username}" >
									<span id="usernameSpan"></span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">真实姓名：</label>
								<div class="col-sm-8">
									<input id="realname" name="realname" class="form-control" onblur="checkRealname()" type="text" value="${list.realname}" >
									<span id="realnameSpan"></span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">密码：</label>
								<div class="col-sm-8">
									<input id="password" name="password" class="form-control" onblur="checkPassword()" type="password" value="${list.password}" >
									<span id="pwdSpan"></span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">性别：</label>
								<div class="col-sm-8">
									<input id="gender" name="gender"  type="radio" value="男" >&nbsp;&nbsp;男
									<input id="gendera" name="gender" type="radio" value="女" >&nbsp;&nbsp;女
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">电话：</label>
								<div class="col-sm-8">
									<input id="telephone" name="telephone" class="form-control" onblur="checkTelephone()" type="text" value="${list.telephone}">
									<span id="telSpan"></span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">角色：</label>
								<div class="col-sm-8">
									<div id="roleList"></div>
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭&nbsp;&nbsp;</button>
				<button  class="btn btn-primary" id="btn1" type="button" onclick="saveUser();" >保存</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<div>
	<!-- 模态框（Modal） 修改权限-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						角色列表
					</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="model_from" action="">
						<input type="hidden" id="id" name="id" value="" placeholder="ID"/>
						<div class="form-group">
							<label class="col-sm-2 control-label">角色类型:</label>
							<div class="col-sm-10 ">
								<select class="form-control lW" name="roleId" id="roleId"  >
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary save">
						提交更改
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
            url: "${pageContext.request.contextPath}/admin/user_list",
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
                title: "用户🆔",
                field: "userId",
                sortable: true
            },{
                title: "用户名称",
                field: "username"
            }, {
				title: "真实姓名",
				field: "realname"
			},{
				title: "用户角色",
				field: "userRole"
			},{
                title: "用户性别",
                field: "gender"
            },{
                title: "用户电话",
                field: "telephone"
			},{
				title: "用户状态",
				field: "status"
			},{
                title: "创建时间",
                field: "createTime"
            }, {
                title: "操作",
                field: "roleId",
                formatter: function (value, row, index) {
                    var operateHtml = '<button class="btn btn-success btn-xs" type="button" onclick="change(\'' + row.userId + '\')"><i class="fa fa-save"></i>&nbsp;禁用/恢复</button> &nbsp;';
                    operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="del(\'' + row.userId + '\')"><i class="fa fa-remove"></i>删除</button>';
                    operateHtml = operateHtml + '<button class="btn btn-primary btn-lg size" data-toggle="modal" data-target="#myModal" onclick="getTypeId(\'' + row.userId + '\')">修改角色</button>';
                    return operateHtml;
                }
            }]
        });
    });

    //模态框点击关闭或保存，清空表单内容
    $("#myModal2").on("hidden.bs.modal", function() {
        document.getElementById("userForm1").reset();

    });
    $("#myModal1").on("hidden.bs.modal", function() {
        document.getElementById("userForm").reset();

    });

    //  获取所有角色，生成checkbox列表
    $.get("${pageContext.request.contextPath}/admin/role_box", function(data){
        // 生成checkbox列表
        $(data).each(function(){
            var checkbox = $("<input type='radio' name='roleId' />");
            checkbox.val(this.roleId);
            $("#roleList").append(checkbox);
            $("#roleList").append(this.roleName);
            $("#roleList").append("&nbsp;&nbsp;");

        });
    });

    function detailFormatter(index, row) {
        var html = [];
        html.push('<p><b>描述:</b> ' + row.description + '</p>');
        return html.join('');
    }

    //用户名搜索
    function eventquery() {
        var username = $('#name').val();
        $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/user_search?username='+encodeURI(encodeURI(username,"UTF-8"))});
    }

    //重置
    function reset() {
        $('#table_list').bootstrapTable('refresh', { url: '${pageContext.request.contextPath}/admin/user_list'});
    }

    //保存用户
	function saveUser() {
            var form = new FormData($(userForm)[0]);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/insertUser",
                data:form,
                success: function(msg){
                    if (msg.code==0){
                        layer.msg(msg.message, {time: 2000},function(){
                            document.getElementById("btn1").disabled=true;
                            $('#table_list').bootstrapTable("refresh");
                            layer.close();
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

    //修改用户状态
    function change(id){
        layer.confirm('确定禁用/恢复吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url:"${pageContext.request.contextPath}/admin/user_change/",
                data:{"userId":id},
                success: function(msg){
                    layer.msg(msg.message, {time: 2000},function(){
                        $('#table_list').bootstrapTable("refresh");
                        layer.close(index);
                    });
                }
            });
        });
	}

    // 用户角色修改
    $(document).on("click",".save",function(){

            url="${pageContext.request.contextPath}/admin/updateRoleId";

        var form = new FormData($(model_from)[0]);
        layer.msg('正在提交中，请稍等。。。', {
            icon: 16,
            shade: 0.01,
            time:0
        });
        $.ajax({
            type: "POST",
            processData:false,
            contentType:false,
            url: url,
            data:form,
            success: function(msg){
                layer.msg(msg.message, {time: 2000},function(){
                    layer.close();
                    if (msg.code=0){
                        document.getElementById("btn").disabled = true;
                    }
                    $('#table_list').bootstrapTable("refresh");
                });
            }
        });
    });

        //保存用户和对应的菜单
        function updateUser() {
            var form = new FormData($(editForm)[0]);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });

            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/role_update",
                data:form,
                success: function(msg){
                    if (msg.code==0){
                        layer.msg(msg.message, {time: 2000},function(){
                            document.getElementById("btn1").disabled=true;
                            layer.close();
                        });
                    }else {
                        layer.msg(msg.message, {time: 2000},function(){
                            layer.close();
                        });
                    }

                }
            });
        }

        //添加用户时的校验
        function checkUsername(){
            var reg = /^[a-zA-Z][a-zA-Z0-9]{5,15}$/ ;
            var userName = document.getElementById("username");
            var nameSpan = document.getElementById("usernameSpan");
			if(!reg.test(userName.value)){
                nameSpan.innerHTML = "必须由英文字母和数字组成的5-15位字符,并以字母开头";
                nameSpan.style.color = "red";
			}else{
                nameSpan.innerHTML = "用户名可用";
                nameSpan.style.color = "green";
            }
        }

    function checkRealname(){
        var reg = /^[\u4E00-\u9FA5]{2,8}$/ ;
        var realName = document.getElementById("realname");
        var nameSpan = document.getElementById("realnameSpan");
        if(!reg.test(realName.value)){
            nameSpan.innerHTML = "只能由2-8位中文组成";
            nameSpan.style.color = "red";
        }else{
            nameSpan.innerHTML = "真实姓名正确";
            nameSpan.style.color = "green";
        }
    }

    function checkPassword(){
        var reg = /^[a-zA-Z0-9]{5,15}$/ ;
        var pwd = document.getElementById("password");
        var pwdSpan = document.getElementById("pwdSpan");
        if(!reg.test(pwd.value)){
            pwdSpan.innerHTML = "密码长度必须在5到15位之间,只能包含大写、小写、数字";
            pwdSpan.style.color = "red";
        }else{
            pwdSpan.innerHTML = "密码有效";
            pwdSpan.style.color = "green";
        }
    }

    function checkTelephone(){
        var reg = /^1(3[0-9]|47|5((?!4)[0-9])|7(0|1|[6-8])|8[0-9])\d{8,8}$/;
        var tel = document.getElementById("telephone");
        var telSpan = document.getElementById("telSpan");
        if(!reg.test(tel.value)){
            telSpan.innerHTML = "请输入正确的11位手机号";
            telSpan.style.color = "red";
        }else{
            telSpan.innerHTML = "手机号正确";
            telSpan.style.color = "green";
        }
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
                url: "${pageContext.request.contextPath}/admin/deleteBackUserByUserId/",
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

    //角色列表显示
    function getTypeId(value) {
        $("#roleId").empty();
		$("#id").val(value);
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            url: "${pageContext.request.contextPath}/admin/findALLRole",
            data:{"Id":value},
            success: function(msg){
                var columnNameList = msg.rows;
//                var value = columnNameList[0].itemId;
                for (var i=0; i < columnNameList.length; i++){
                    $("#roleId").append("<option name='roleId'  value="+columnNameList[i].roleId+">" + columnNameList[i].roleName + "</option>" );
                }
            }
        });
    }
</script>

</body>

</html>
