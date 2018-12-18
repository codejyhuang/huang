<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
    <title>二维码制作</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/qrcodejs-master/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/qrcodejs-master/qrcode.js"></script>
    <%----%>
    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">

    <style>
        .div{text-align: center }
        
        .down_img{
            margin-left: 12px;
            padding-top: 0px;
            text-align: center;
            width: 100px;
            height: 30px;
            border-top:1px solid #00a0e9;
            border-bottom:1px solid #229922;
            border-left:1px solid #ffff00;
            border-right:1px solid #000;
            border-radius:15px;
        }
    </style>
</head>
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

<body>
<div class="">
    <form class="form-horizontal m-t" id="catalogue"  method="post" action="" enctype="multipart/form-data">
        <input id="text" type="hidden" name="text" style="width: 280px;height: 70px;margin-left: 25%" value="${qrcode.channelName}"/>
        <input id="text1" type="hidden" name="text" style="width: 280px;height: 70px;margin-left: 25%"value="${qrcode.url}"/>
        <input id="channelId" type="hidden" value="${qrcode.channelId}"/>
       <p style="margin-left: 30%">渠道名称：${qrcode.channelName}</p>
        <div class="div" id="qrcode" style="margin-left: 25%" ></div><br><br>
        <a  id="a" href="" download="${qrcode.channelName}">
            <DIV id="new_img" class="down_img"  alt="下载图片" style="margin-left: 43%" onclick="download();"><p style="font-size: 20px;">点击下载</p></DIV>
        </a>
    </form>
</div>

<script type="text/javascript">

//图片下载
    function download() {
        var qrcode=document.getElementById("qrcode");
        var Qrurl=qrcode.getElementsByTagName('img')[0].src;
        a.href=Qrurl;

    }
    //二维码
    var qrcode = new QRCode(document.getElementById("qrcode"), {
        width : 283,
        height : 283,
        colorLight : '#FFFFFF',
        correctLevel : QRCode.CorrectLevel.H
    });
        //二维码生成
    function makeCode () {
//        var channel = document.getElementById("text").value;
        var url = document.getElementById("text1").value;
        var urlStr = '<%=basePath%>/admin/redirectView?channelId='+$("#channelId").val()+"&url="+encodeURI(url);

        qrcode.makeCode(urlStr);
    }

    makeCode();

    $("#text").
    on("blur", function () {
        makeCode();
    }).
    on("keydown", function (e) {
        if (e.keyCode == 13) {
            makeCode();
        }
    });

    //将URL传入后台
    function QrcodeURL(){
        debugger
        var form = new FormData($(catalogue)[0]);
        var qrcode=document.getElementById("qrcode");
        var Qrurl=qrcode.getElementsByTagName('img')[0].src;
    $.ajax({
        type: "POST",
        processData:false,
        contentType:false,
        url: "${pageContext.request.contextPath}/admin/inserAllqrurl",
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