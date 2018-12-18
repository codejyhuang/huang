<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/easyui/easyui.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/easyui/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/easyui/jquery.easyui.min.js"></script>

    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/plugins/zTree/zTreeStyle/zTreeStyle.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>类型添加</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="typeId" method="post" action="" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">类型名称：</label>
                                <div class="col-sm-8">
                                    <input id="typeName" name="typeName" class="form-control" type="text" value="${user.catalogueName}" placeholder="如：藏经,歌曲……">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">类型描述：</label>
                                <div class="col-sm-8">
                                    <input id="catalogueDesc" name="typeDesc" class="form-control" type="text" value="" placeholder="类目简单的一句话描述">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">类型简介：</label>
                                <div class="col-sm-8">
                                    <input id="introduceInfo" name="introduceInfo" class="form-control" type="textarea" cols="30" rows="10" value="" placeholder="一个简单的类目介绍">
                                </div>
                            </div>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-3 control-label">序号：</label>--%>
                                <%--<div>--%>
                                    <%--<label class="radio-inline">--%>
                                        <%--<input type="radio" name="sort" id="order1" value="1" > 1--%>
                                    <%--</label>--%>
                                    <%--<label class="radio-inline">--%>
                                        <%--<input type="radio" name="sort" id="order2" value="2" > 2--%>
                                    <%--</label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>图 片：</label>
                                <div class="col-sm-6">
                                    <input name="img" class="form-control" type="text" value="" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="fileEdit" name="taskItemPic" class="itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary"id="btn"type="button" onclick="save();">保存</button>&nbsp;&nbsp;
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>


    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/view/static/assets/js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/laydate/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/zTree/jquery.ztree.all.min.js"></script>
    <script type="text/javascript">

            function save(){
                var form = new FormData($(typeId)[0]);
                layer.msg('正在提交中，请稍等。。。', {
                    icon: 16,
                    shade: 0.01,
                    time:0
                });
                $.ajax({
                    type: "POST",
                    processData:false,
                    contentType:false,
                    url: "${pageContext.request.contextPath}/admin/insertByIdTaskType",
                    data:form,
                    success: function(msg){
                        debugger
                        if (msg.code==0){
                            layer.msg(msg.message, {time: 2000},function(){
                                document.getElementById("btn").disabled=true;
                                layer.close();
                                return;
                            });
                        }else {
                            layer.msg(msg.message, {time: 2000},function(){
                                layer.close();
                            });
                        }

                    }
                });
            }
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

    </script>

</body>

</html>
