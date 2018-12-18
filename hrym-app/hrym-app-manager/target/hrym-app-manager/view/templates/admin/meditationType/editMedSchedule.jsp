<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> 共修类型修改</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/easyui/easyui.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/easyui/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/easyui/jquery.easyui.min.js"></script>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>
<body>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12 total">
            <%--目录添加编辑--%>
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <div class="form-group">
                        <h5  class="col-sm-10">编辑共修类型(id:${med.scheduleId})内容</h5>
                        <button type="button" class="btn btn-default btn-xs saveContent">
                            <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 编辑
                        </button>
                    </div>
                </div>
                <div class="ibox-content" >
                    <form class="form-horizontal m-t contentForm" id="content" method="post" action="" >
                        <input type="hidden" name="scheduleId" value="${med.scheduleId}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">共修内容名称:</label>
                            <div class="col-sm-8">
                                <input id="activeTitle" name="activeTitle" class="form-control" type="text" value="${med.activeTitle}" placeholder="请输入共修内容名称 ">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">完成目标数:</label>
                            <div class="col-sm-8">
                                <input id="targetNumber" name="targetNumber" class="form-control" type="number" value="${med.targetNumber}" placeholder="请输入数字。。">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">开始共修时间：</label>
                            <div class="col-sm-8">
                                <input class="form-control layer-date" value="${med.startTimes}" name="startTimes" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                                <label class="laydate-icon"></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">共修结束时间：</label>
                            <div class="col-sm-8">
                                <input class="form-control layer-date" value="${med.expectTimes}" name="expectTimes" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                                <label class="laydate-icon"></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>共修头像：</label>

                            <div class="col-sm-6">
                                <input name="activeHeadUrl" class="form-control" type="text" value="${med.activeHeadUrl}" readonly>
                            </div>
                            <div class="col-sm-3">
                                <label class="col-sm-3 control-label">
                                    <input type="file" class="fileEdit" name="addvideoFile" class="itemPic oas-filebutton-default">
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">功课名称:</label>
                            <div class="col-sm-10 ">
                                <select class="form-control lW" value="${med.meditationTypeId}" name="meditationTypeId" id="meditationTypeId"  >
                                </select>
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
//        保存资源类型
        $(document).on("click", ".saveContent", function () {
            var con = $(this).closest(".ibox.float-e-margins").find(".contentForm");

            var form = new FormData(con[0]);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            $.ajax({
                type: "POST",
                processData: false,
                contentType: false,
                url: "${pageContext.request.contextPath}/admin/updateMedSchedule",
                data: form,
                success: function (msg) {
                    if (msg.code == 1) {
                        layer.msg('编辑内容失败，请联系管理员。。。');
                        layer.close();
                        return;
                    } else {
                      layer.msg("操作成功");
                        layer.close();
                    }
                },
                complete: function(XMLHttpRequest, textStatus) {
                    $("#load").remove();
                }
            });

        });

        <!-- 共修类别内容下拉框 -->
        $(document).ready(function getTypeId(value) {
            <!-- 清除专区下拉框的内容-->
            $("#version").empty();
            $("#meditationTypeId").empty();
            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                url: "${pageContext.request.contextPath}/admin/findAllMeditationType",
                data:{"Id":value},
                success: function(msg){
                    var columnNameList = msg.rows;
                    var value = columnNameList[0].meditationTypeId;
                    for (var i=0; i < columnNameList.length; i++){
                        $("#meditationTypeId").append("<option name='meditationTypeId'  value="+columnNameList[i].meditationTypeId+">" + columnNameList[i].meditationTypeName + "</option>" );
                    }
                }
            });
        });
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
                        layer.close();
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

</script>
</body>
</html>
