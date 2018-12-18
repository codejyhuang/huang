<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/easyui/easyui.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/easyui/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/easyui/jquery.easyui.min.js"></script>

    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/plugins/zTree/zTreeStyle/zTreeStyle.css"
          rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>标签添加</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="typeId" method="post" action="" enctype="multipart/form-data">
                        <input id="tagId" name="tagId" value="" type="hidden">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">标签名称：<b style="color: red">*</b></label>
                            <div class="col-sm-8">
                                <input id="tagName" name="tagName" class="form-control" type="text" value="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">标签类型：<b style="color: red">*</b></label>
                            <div class="col-sm-8">
                                <input id="tagType1" type="radio" name="tagType" value="0" checked>类型
                                <input type="radio" id="tagType2" name="tagType" value="1">法门
                                <input type="radio" id="tagType3" name="tagType" value="2">功德
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">标签权重：</label>
                            <div class="col-sm-8">
                                <input id="tagWeight" name="tagWeight" class="form-control" type="number" value="0"
                                       placeholder="标签级别数字类型数字越大权重越高">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">标签排序：</label>
                            <div class="col-sm-8">
                                <input id="tagSort" name="tagSort" class="form-control" type="number" value="0"
                                       placeholder="标签排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" id="btn" type="button" onclick="save();">保存</button>&nbsp;&nbsp;
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

    // 保存与修改
    function save() {
        if (tagId.value.length>0){

            url="${pageContext.request.contextPath}/admin/updateResourceTag"
        }else {

            url="${pageContext.request.contextPath}/admin/insertResourceTag"
        }
        var form = new FormData($(typeId)[0]);
        layer.msg('正在提交中，请稍等。。。', {
            icon: 16,
            shade: 0.01,
            time: 0
        });
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: url,
            data: form,
            success: function (msg) {
                if (msg.code == 0) {
                    layer.msg(msg.message, {time: 2000}, function () {
                        document.getElementById("btn").disabled = true;
                        layer.close();
                        return;
                    });
                } else {
                    layer.msg(msg.message, {time: 2000}, function () {
                        layer.close();
                    });
                }
            }
        });
    }

    <!-- 功课标签展示-->
    $(document).ready(function typeId() {
        $("#tagId").empty();
        var args = new Object();
        var argsStr = location.search;  //获取URL参数字符串
        if (argsStr != "") {
            if (argsStr.length > 0) {
                argsStr = argsStr.substring(1);
            }
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/findResourceTagById?" + argsStr,
                success: function (msg) {

                    $("#tagId").val(msg.rows.tagId);
                    $("#tagName").val(msg.rows.tagName);
                    $("#tagWeight").val(msg.rows.tagWeight);
                    $("#tagSort").val(msg.rows.tagSort);
                    if (msg.rows.tagType == 0) {
                        $("#tagType1").attr("checked", "checked");

                    } else if (msg.rows.tagType == 1) {

                        $("#tagType2").attr("checked", "checked");
                    } else if (msg.rows.tagType == 2) {

                        $("#tagType3").attr("checked", "checked");
                    }

                }
            });
        }
    });

</script>

</body>

</html>
