<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> -目录编辑</title>
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
                        <h5  class="col-sm-10">编辑目录(id:${bean.catalogueId})内容</h5>
                        <button type="button" class="btn btn-default btn-xs saveContent">
                            <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 编辑
                        </button>
                    </div>
                </div>
                <div class="ibox-content" >
                    <form class="form-horizontal m-t contentForm"  method="post" action="" >
                        <input  type="hidden" id="catalogueId" name="catalogueId" value="${bean.catalogueId}">
                        <input class="userId" name="userId" type="hidden" value="${userId}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">目录名称：</label>
                            <div class="col-sm-8">
                                <input  name="catalogueName" class="form-control" type="text" value="${bean.catalogueName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label">选择父目录：</label>
                            <div class="col-sm-8">
                                <select name="parentTypeId" url="${pageContext.request.contextPath}/admin/getTree" class="easyui-combotree" style="width:500px;height: 30px">
                                    <option name="${bean.parentTypeId}">${bean.parentTypeId}</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">目录描述：</label>
                            <div class="col-sm-8">
                                <input id="catalogueDesc" name="catalogueDesc" class="form-control" type="text" value="${bean.catalogueDesc}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">目录简介：</label>
                            <div class="col-sm-8">
                                <input id="introduceInfo" name="introduceInfo" class="form-control" type="text" value="${bean.introduceInfo}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">创建者：</label>
                            <div class="col-sm-8">
                                <input id="creator" name="creator" class="form-control" type="text" value="${bean.creator}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>背景图片：</label>

                            <div class="col-sm-6">
                                <input name="img" class="form-control" type="text" value="${bean.img}" readonly>
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
                url: "${pageContext.request.contextPath}/admin/updateResourceCatalogue",
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
    });

</script>
</body>
</html>
