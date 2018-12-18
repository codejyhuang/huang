<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/plugins/zTree/zTreeStyle/zTreeStyle.css" rel="stylesheet">

</head>
<body>
<div class="wrapper wrapper-content animated fadeInRight">

    <%--内容添加--%>
    <div class="ibox float-e-margins" id="itemContent">
        <div class="ibox-title">
            <h5>文章预览</h5>
            <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1);">返回</a></h5>
            <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预览地址：${url}</h5>
        </div>
        <div id="testdiv">
            <span>${content}</span>
        </div>
    </div>
</div>
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
//    var loc = location.href;
//    var n1 = loc.length;//地址的总长度
//    var n2 = loc.indexOf("=");//取得=号的位置
//    var text = decodeURI(loc.substr(n2+1, n1-n2));//从=号后面的内容
//    var testdiv = document.getElementById("testdiv");
//    testdiv.innerHTML=text;


</script>

</html>
