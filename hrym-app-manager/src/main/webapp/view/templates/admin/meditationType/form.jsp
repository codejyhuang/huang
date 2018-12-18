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
<style>
    .form-group{
        margin-top: 20px;
        line-height: 20px;
        width: 700px;
    }
</style>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="form-group">
                            <h5  class="col-sm-10"></h5>
                            <button type="button" class="btn btn-default btn-xs save">
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 添加
                            </button>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t contentForm"  method="post" action="" >
                            <input  type="hidden" id="meditationTypeId" name="meditationTypeId" value="${type.meditationTypeId}">
                            <div class="form-group divname">
                                <label class="col-sm-3 control-label">共修类型名称：</label>
                                <div class="col-sm-8">
                                    <input  name="meditationTypeName" class="form-control" type="text" value="${type.meditationTypeName}">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label class="col-sm-3 control-label">组织者：</label>
                                <div class="col-sm-8">
                                    <input id="author" name="author" class="form-control" type="text" value="${type.author}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">类型简介：</label>
                                <div class="col-sm-8">
                                    <textarea id="meditationTypeIntro" name="meditationTypeIntro" class="form-control" type="text" value="${type.meditationTypeIntro}"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">类型版本：</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="meditationTypeVersion" id="meditationTypeVersion" value="0" checked> 单版本
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="meditationTypeVersion" id="meditationTypeVersion1" value="1"> 多版本
                                    </label>
                                </div>
                            </div>
                            <div class="form-group startTimes">
                                <label class="col-sm-3 control-label">开始共修时间：</label>
                                <div class="col-sm-8">
                                    <input class="form-control layer-date " id="startTimes" name="startTimes" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                                    <label class="laydate-icon"></label>
                                </div>
                            </div>
                            <div class="form-group expectTimes">
                                <label class="col-sm-3 control-label">共修结束时间：</label>
                                <div class="col-sm-8">
                                    <input class="form-control layer-date " id="expectTimes" name="expectTimes" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                                    <label class="laydate-icon"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>头像url：</label>

                                <div class="col-sm-6">
                                    <input name="activeHeadUrl" class="form-control" type="text" value="${type.activeHeadUrl}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="fileEdit" name="takeImg" class="itemPic oas-filebutton-default">
                                    </label>
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

        $(document).on("click", ".save", function () {
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
                url: "${pageContext.request.contextPath}/admin/insertMeditation",
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

//        // 单多版本属性选择
//        $(function(){
//            $(":radio").click(function(){
//
//                var divcss = {
//                    display:'none'
//                };
//                var divcss1 = {
//                    display:''
//                };
//                if ($(this).val() ==0) {
//
//                    $(".startTimes").css(divcss1);
//                    $(".expectTimes").css(divcss1);
//
//                }else {
//                    $("#startTimes").val("");
//                    $("#expectTimes").val("");
//                    $(".startTimes").css(divcss);
//                    $(".expectTimes").css(divcss);
//                }
//            });
//        });
    </script>

</body>

</html>
