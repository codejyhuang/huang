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
                        <h5>目录添加</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="catalogue" method="post" action="" enctype="multipart/form-data">
                            <input class="userId" name="userId" type="hidden" value="">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">目录名称：</label>
                                <div class="col-sm-8">
                                    <input id="catalogueName" name="catalogueName" class="form-control" type="text" value="${user.catalogueName}" placeholder="如：藏经,歌曲……">
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-3 control-label">选择父目录：</label>
                                <div class="col-sm-8">
                                    <select name="parentTypeId" url="${pageContext.request.contextPath}/admin/getTree" class="easyui-combotree" style="width:500px;height: 30px"></select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">目录描述：</label>
                                <div class="col-sm-8">
                                    <input id="catalogueDesc" name="catalogueDesc" class="form-control" type="text" value="${user.catalogueDesc}" placeholder="类目简单的一句话描述">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">目录简介：</label>
                                <div class="col-sm-8">
                                    <input id="introduceInfo" name="introduceInfo" class="form-control" type="textarea" cols="30" rows="10" value="${user.introduceInfo}" placeholder="一个简单的类目介绍">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">创建者：</label>
                                <div class="col-sm-8">
                                    <input id="creator" name="creator" class="form-control" type="text" value="${user.creator}" placeholder="此处是创建者名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>背景图片：</label>
                                <div class="col-sm-8">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" name="taskImg" id="img" class="itemPic oas-filebutton-default">
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
                var userId= window.parent.document.getElementById("userId").defaultValue;
                $(".userId").val(userId);
                var form = new FormData($(catalogue)[0]);
                layer.msg('正在提交中，请稍等。。。', {
                    icon: 16,
                    shade: 0.01,
                    time:0
                });
                $.ajax({
                    type: "POST",
                    processData:false,
                    contentType:false,
                    url: "${pageContext.request.contextPath}/admin/insertCatalogue",
                    data:form,
                    success: function(msg){
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
    </script>

</body>

</html>
