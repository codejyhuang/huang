<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 资源编辑</title>
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

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12 total">

                <%--功课编辑--%>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="form-group">
                            <h5  class="col-sm-10" >编辑资源(id:${resource.itemId})内容</h5>

                            <button type="button" class="btn btn-default btn-xs saveItem">
                                <span class="glyphicon glyphicon-edit saveItem" aria-hidden="true"></span> 编辑
                            </button>

                        </div>
                    </div>
                    <div class="ibox-content" >
                        <form class="form-horizontal m-t itemForm" method="post" action="">

                            <input id="typeId" type="hidden" name="typeId" value="${resource.typeId} ">
                            <input  type="hidden" id="itemId" name="itemId" value="${resource.itemId}">
                            <input  type="hidden" name="userId" value="${resource.userId}">
                            <div class="form-group">
                                <label  class="col-sm-3 control-label">选择父目录：</label>
                                <div class="col-sm-8">
                                    <select name="catalogueId" url="${pageContext.request.contextPath}/admin/getTree" class="easyui-combotree" style="width:500px;height: 30px">
                                        <option name="catalogueId"value="${resource.catalogueId}">${resource.catalogueId}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">资源名称：</label>
                                <div class="col-sm-8">
                                    <input id="itemName" name="itemName" class="form-control" type="text" value="${resource.itemName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">资源描述：</label>
                                <div class="col-sm-8">
                                    <input id="titleDesc" name="titleDesc"  class="form-control" type="text" value="${resource.titleDesc}"  placeholder="如:南无文殊菩萨……">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">别 名：</label>
                                <div class="col-sm-8">
                                    <input id="aliasName" name="aliasName" class="form-control" type="text" value="${resource.aliasName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">禅修集数：</label>
                                <div class="col-sm-8">
                                    <input id="versionNumber" name="versionNumber"  class="form-control" type="text" value="${resource.versionNumber}"  placeholder="中文数字....">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">经书排序：</label>
                                <div class="col-sm-8">
                                    <input id="sort" name="sort" class="form-control" type="text" value="${resource.sort}" placeholder="如:数值越大，越靠前……">
                                </div>
                            </div>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-3 control-label">是否可供：</label>--%>
                                <%--<div>--%>
                                    <%--<label class="radio-inline">--%>
                                        <%--<input type="radio" name="isSupport" id="isSupport1" value="1" ${resource.isSupport=='1'?'checked':''}> 可供--%>
                                    <%--</label>--%>
                                    <%--<label class="radio-inline">--%>
                                        <%--<input type="radio" name="isSupport" id="isSupport2" value="0" ${resource.isSupport=='0'?'checked':''}> 不可供--%>
                                    <%--</label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">经书类型：</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="bookType" id="bookType1" value="1"${resource.bookType=='1'?'checked':''} > 经文精选
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="bookType" id="bookType2" value="2" ${resource.bookType=='2'?'checked':''} > 论释精选
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="bookType" id="bookType3" value="3" ${resource.bookType=='3'?'checked':''} > 著作精选
                                    </label>
                                </div>
                            </div>

                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-3 control-label">资源类型：</label>--%>
                                <%--<div>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="typeId" value="10001" ${resource.typeIdA=='10001'?'checked':''}> 磕大头--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="typeId" value="10002" ${resource.typeIdB=='10002'?'checked':''}> 拜忏--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="typeId" value="10003" ${resource.typeIdC=='10003'?'checked':''} > 供佛--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="typeId" value="10004" ${resource.typeIdD=='10004'?'checked':''}> 念佛--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="typeId" value="10005" ${resource.typeIdE=='10005'?'checked':''}> 念咒--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="typeId" value="10006" ${resource.typeIdF=='10006'?'checked':''}> 诵经--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="typeId" value="10007" ${resource.typeIdH=='10007'?'checked':''}> 禅修--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="typeId" value="10008" ${resource.typeIdG=='10008'?'checked':''}> 歌曲--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="typeId" value="10011" ${resource.typeIdK=='10011'?'checked':''}> 其他课诵--%>
                                    <%--</label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>经书类型图片《小》：</label>
                                <div class="col-sm-6">
                                    <input name="smallPic" class="form-control" type="text" value="${resource.smallPic}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="fileEdit"  class="smallPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>功课图片：</label>
                                <div class="col-sm-6">
                                    <input name="itemPic" class="form-control" type="text" value="${resource.itemPic}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="fileEdit" name="taskItemPic" class="itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>做功课图片：</label>
                                <div class="col-sm-6">
                                    <input name="backgroundPic" class="form-control" type="text" value="${resource.backgroundPic}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="fileEdit" class="itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>资源简介：</label>
                                <div class="col-sm-8">
                                    <textarea name="itemIntro" placeholder="" style="width: 530px;height: 100px">${resource.itemIntro}</textarea>
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

//        保存功课
        $(document).on("click",".saveItem",function(){

            var con = $(this).closest(".ibox.float-e-margins").find(".itemForm");

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
                url: "${pageContext.request.contextPath}/admin/editTaskItem",
                data:form,
                success: function(msg){
                    if (msg.code==1){
                        layer.msg('编辑内容失败，请联系管理员。。。');
                        return;
                    } else {
                        layer.close();
                        layer.msg("编辑成功", {time: 2000},function(){
                            layer.close();

                    });
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
