
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
    </style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>渠道管理</h5>
                </div>

                <div class="ibox-content">
                    <div id="main" style="margin-left: 32px;margin-top: 45px">
                        <div style="float:right;margin-top:0px;margin-left: 21px">
                            <p>
                                <button class="btn btn-success" style="margin-top: 10px;margin-bottom: 10px" data-toggle="modal" data-backdrop="static" data-target="#myModal"type="button" ><i class="fa fa-plus"></i>&nbsp;添加</button>
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
</div>

<!-- 按钮触发模态框 -->
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>

            </div>
            <div class="wrapper wrapper-content animated fadeInRight">

                <div class="ibox float-e-margins" id="itemContent">
                    <div class="ibox-title">
                        <h5>渠道生成</h5>
                    </div>
                    <div class="ibox-content" >
                        <form class="form-horizontal m-t" id="channelForm" method="post" action="" >
                            <input type="hidden" id="bannerId" name="bannerId" value=""/>
                            <input type="hidden" id="accessTimes" name="accessTimes" value="0"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">渠道名称：</label>
                                <div class="col-sm-8">
                                    <input id="channelName" name="channelName" class="form-control" type="text" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')"
                                           onpaste="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">原URL：</label>
                                <div class="col-sm-8">
                                    <input id="url" name="url" class="form-control" type="text" value="" oninput="myFunction()"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button  class="btn btn-primary insertAllBanner" id="btn1" type="button">保存&nbsp;&nbsp;
                </button>
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
            url: "${pageContext.request.contextPath}/admin/findAllQrCode",
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
                title: "渠道ID",
                field: "channelId",
                witdth: 10
            },{
                title: "渠道名称",
                field: "channelName"
            },{
                title: "渠道URL",
                field: "url",
                formatter:function (value,row,index) {
                    if (value==null){
                        return' '
                    }
                    return '<a  href='+value+' width=100 height=50>'+value+'</a>'
                }
            },{
                title: "点击次数",
                field: "accessTimes"
            },{
                title: "创建时间",
                field: "createTimes"
            },
//                {
//                title: "活动用途",
//                field: "bannerType",
//                formatter: function (value) {
//                    if(value == 0)
//                        return '<p> 社群主页</p>';
//                    else
//                        return '<p>正在设计</p>'
//                }
//            },
                {
                title: "操作",
                field: "empty",
                formatter: function (value, row, index) {
                    var operateHtml = '<button class="btn btn-primary btn-xs" type="button"  onclick="edit(\''+row.channelId+'\')"><i class="fa fa-edit"></i>&nbsp;点击查看</button> &nbsp;';
                    operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="del(\''+row.channelId+'\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;';
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

    //渠道删除
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
                url:"${pageContext.request.contextPath}/admin/deteleurl/",
                data:{"channelId":id},
                success: function(msg){
                    layer.msg(msg.message, {time: 2000},function(){
                        $('#table_list').bootstrapTable("refresh");
                        layer.close();
                    });
                }
            });
        });
    }
    $(document).ready(function () {
        //渠道生成
        $(document).on("click",".insertAllBanner",function(){
            var form = new FormData($(channelForm)[0]);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/inserqrurl",
                data:form,
                success: function(msg){
                     if(msg.code==0){
                         layer.msg(msg.message, {time: 2000},function() {
                             $('#table_list').bootstrapTable("refresh");
                             document.getElementById("btn1").disabled = true;
                             layer.close();
                             return;
                         });
                     }else if(msg.code==1) {
                         layer.msg(msg.message, {time: 2000},function(){
                             $('#table_list').bootstrapTable("refresh");
                             layer.close();
                         });
                        layer.close();
                    }
                }
            });

        });
    });
    //模态框关闭初始化事件
    $(function () { $('#myModal').on('hidden.bs.modal', function () {
//            window.location.reload();
        document.getElementById("channelForm").reset();
        document.getElementById("btn1").disabled = false;
    })
    });
//    渠道查看
    function edit(id) {
        layer.open({
            type: 2,
            title: '渠道查看',
            shadeClose: true,
            shade: false,
            area: ['43%', '80%'],
            content: '${pageContext.request.contextPath}/admin/findByCannelId?channelId='+id+'&select=0',
            end: function (index) {
                $('#table_list').bootstrapTable("refresh");
            }
        });
    }



    function detailFormatter(index, row) {
        var html = [];
        html.push('<p><b>描述:</b> ' + row.description + '</p>');
        return html.join('');
    }

    //输入框限制事件
    function myFunction(){
        var x=document.getElementById("url");
        if(!x.value.match(/[\u4e00-\u9fa5]/g)){
        }else{
        x.value=x.value.replace(/[\u4e00-\u9fa5]/g,'');
//        if ()
            layer.msg('禁止输入汉字！')
        }



    }

</script>


</body>

</html>
