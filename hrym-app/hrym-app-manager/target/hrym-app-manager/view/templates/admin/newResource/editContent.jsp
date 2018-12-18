<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 资源添加</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">
    <style>

        </style>
</head>
<c:import url="bookContent.jsp"></c:import>
<c:import url="meditationContent.jsp"></c:import>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12 total">

            </div>
        </div>
    </div>
    <input id="typeId" type="hidden" name="typeId" value="${resource.typeId}">
</body>
    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/bootstrap.min.js?v=3.3.6"></script>

    <%--引入复制js--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/oasis/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/oasis/js/oasis.js"></script>
    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/view/static/assets/js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/laydate/laydate.js"></script>
    <script type="text/javascript">

    $(document).ready(function () {

        var typeId = document.getElementById("typeId").value;
        if (typeId == 10006){
            //复制内容模版模块
            var html = template('bookContentTpl');
            $(".row").parent().append(html);
        }
        if (typeId == 10007){
            //复制内容模版模块
            var html = template('meditationContentTpl');
            $(".row").parent().append(html);
        }
//       更新内容
        $(document).on("click",".saveContent",function(){
            var userId = window.parent.document.getElementById("userId").defaultValue;

            $(".userId").val(userId)
            var con = $(this).closest(".ibox.float-e-margins").find(".form-horizontal");

            var form = new FormData(con[0]);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/editTaskContent?typeId="+typeId,
                data:form,
                success: function(msg){
                    if (msg.code==1){
                        parent.layer.msg('编辑内容失败，请联系管理员。。。');
                        return;
                    } else {
                        layer.msg('操作成功。。。');
                        layer.close();
                    }
                }
            });
        });


        $(document).on("click",".btn3",function(){
            window.location.href="${pageContext.request.contextPath}/view/templates/admin/newResource/index.jsp?typeId="+typeId;
        });

        /**
         * 文件上传
         */
        $(document).on("change",".uploadFile",function(){

            debugger
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
    });

    var userId = window.parent.document.getElementById("userId").defaultValue;
    </script>

</html>
