<%--
  Created by IntelliJ IDEA.
  User: hrym13
  Date: 2017/12/4
  Time: 上午10:51
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>商品信息</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/easyui/easyui.css" />--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/view/easyui/jquery-1.8.0.min.js"></script>--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/view/easyui/jquery.easyui.min.js"></script>--%>


    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">

    <style>
        table{
            table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
        }
        td{
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
<body>
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>商品信息</h5>
                </div>
                <div class="ibox-content">
                    <div class="row row-lg">
                        <div class="col-sm-12">
                            <!-- Example Card View -->
                            <div class="example-wrap">
                                <div>
                                    <!-- 按钮触发模态框 -->
                                    <button class="btn btn-primary"  data-toggle="modal" data-backdrop="static" data-target="#myModal" id="">
                                        商品添加
                                    </button>
                                </div>
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
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content modal-style">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">
                    商品信息添加
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="model_from" action="">
                    <input type="hidden" id="as" name="##" value="" placeholder="商品ID"/>
                    <input type="hidden" name="" value="0" placeholder="在架状态默认是下架"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品名称:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="focusedInput1" type="text"  value="" placeholder="商品名称十五字以内...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品类目:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="focusedInput2" type="text"  value="商品类目...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">品牌:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="focusedInput3" type="text"  value="该输入框获得焦点...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">单位:</label>
                        <select class="bg-success" style="width: 47%;height: 31px;margin-left: 5%">
                            <option class="bg-success" id="focusednpu" value="该输入框获得焦点..." placeholder="单位：台、个、盒等等">请选择</option>
                            <option class="bg-success" id="focu" value="该输入框获得焦点..." placeholder="单位：台、个、盒等等">台</option>
                            <option class="form-control" id="fonpu" value="该输入框获得焦点..." placeholder="单位：台、个、盒等等">盒</option>
                            <option class="form-control" id="fpu" value="该输入框获得焦点..." placeholder="单位：台、个、盒等等">个</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">包装内容:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="focusedInp" type="text"  value="该输入框获得焦点...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品图片:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="focusedIn" type="text"  value="该输入框获得焦点...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">价格:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="focusedI" type="text"  value="该输入框获得焦点...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品库存:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="fcusedInput" type="text"  value="该输入框获得焦点...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品属性:</label>
                        <div class="col-sm-10">
                            <input class="form-control lW" id="focsedInput" type="text"  value="该输入框获得焦点...">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-backdrop="static" data-target="#myModal1" id="btn">提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
    <!-- 模态框（Modal） -->
    <div>
    <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content modal-style">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel1">
                        商品属性添加
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form" id="odel_from1" action="">
                        <input type="hidden" id="as1a" name="##" value="" placeholder="商品信息ID"/>
                        <input type="hidden" id="12" name="" value="" placeholder="商品属性ID">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品大小:</label>
                            <div class="col-sm-10">
                                <input class="form-control lW" id="focuedshInpu" type="text"  value="" placeholder="商品...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品版本:</label>
                            <div class="col-sm-10">
                                <input class="form-control lW" id="focuedcdsInpu" type="text"  value="" placeholder="商品...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品款式:</label>
                            <div class="col-sm-10">
                                <input class="form-control lW" id="fou1edInpu" type="text"  value="" placeholder="商品...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品颜色:</label>
                            <div class="col-sm-10">
                                <input class="form-control lW" id="foycunpu" type="text"  value="" placeholder="商品...">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="btn11">提交更改
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
        <!-- 模态框（Modal） -->
        <div>
            <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content modal-style">
                        <div class="modal-body">

                                        <h2 class="text-center text-info">
                                            <strong>商品属性详情</strong>
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
            url: "${pageContext.request.contextPath}/admin/findAllTaskItem",
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
            detailView:false,
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
                title: "商品名称",
                field: "itemName"
//                width:100
            },{
                title: "商品类目",
                field: "aliasName"
//                width:100
            },{
                title: "品牌",
                field: "itemIntro"
            },{
                title: "单位",
                field: "titleDesc",
                width:80
            },{
                title: "包装内容",
                field: "catalogueName",
                width:80
            },{
                title: "商品图片",
                field: "isSupport"

            },{
                title: "价格",
                field: "isSupport"
            },{
                title: "商品库存",
                field: "isSupport"
            },{
                title: "在架状态",
                field: "isSupport"
            },{
                title: "商品属性",
                field: "isSupport",
                formatter: function (value) {
                    var operateHtml = '<button class="btn btn-primary btn-xs" type="button" data-toggle="modal" data-backdrop="static" data-target="#myModal2" onclick="edit(\''+value+'\')">' +
                        '<i class="fa"></i>&nbsp;商品属性详情</button> &nbsp;';
                    return operateHtml;
                }
                <%--formatter:function (value,row,index) {--%>
<%--//                    if (value==null){--%>
<%--//                        return' '--%>
<%--//                    }--%>
                    <%--return "<a  href='${pageContext.request.contextPath}/view/templates/admin/comInformation/ComProperty.jsp' width=100 height=50>萨达</a>"--%>
                <%--}--%>
            },{
                title: "操作",
                field: "catalogueId",
                formatter: function (value, row,index) {
                    var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="refuse(\''+value+'\')">' +
                        '<i class="fa"></i>&nbsp;上架/下架</button> &nbsp;';
                    return operateHtml;
                }
            }]
        });

    });

    function detailFormatter(index, row) {

        var html = [];
        html.push('<p><b>描述:</b> ' + row.id + '</p>');
        return html.join('');
    }
    function edit() {

    }

    //模态框关闭初始化事件
    $(function () { $('#myModal').on('hidden.bs.modal', function () {
        document.getElementById("model_from").reset();
        document.getElementById("btn").disabled = false;
    })
    });
//        商品信息保存
        $(document).on("click",".saveItem",function(){

            var form = new FormData($(item)[0]);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/？",
                data:form,
                success: function(msg){

                    $("#itemId").val(msg.rows.itemId);
                    document.getElementById("btn").disabled=true;
                    layer.msg(msg.message, {time: 2000},function(){
                        layer.close();

                    });
                }
            });
        });
//商品上下架
    function refuse(id){
        if(id<=10005){sa = "确定要下架吗？"}
            else if(id>10005) {
            sa = "确定要上架吗？"}
            debugger
        layer.confirm(sa, {icon: 3, title:'提示'}, function(index){
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url:"${pageContext.request.contextPath}/admin/update",
                data:{"uuid":id,"identityAuthState":"2"},
                success: function(msg){
                    layer.msg(msg.message, {time: 2000},function(){

                        layer.close(index);
                    });
                }
            });
            $('#table_list').bootstrapTable("refresh");
            layer.close(index);
        });
    }
    </script>

</body>
</html>
