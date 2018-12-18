<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 资源添加</title>
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

</head>
<c:import url="bookContent.jsp"></c:import>
<c:import url="meditationContent.jsp"></c:import>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12 total">
                <%--功课添加--%>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>功课添加</h5><tr>&emsp;&emsp;&emsp;<a id="callBack" href="${pageContext.request.contextPath}/view/templates/admin/newResource/index.jsp">返回</a>
                    </div>
                    <div class="ibox-content" >

                        <input id="itemContentId" type="hidden" name="itemContentId" value="">
                        <input id="itemId1" type="hidden" name="itemId" value="">

                        <form class="form-horizontal m-t" id="item" method="post" action="${pageContext.request.contextPath}/admin/insertTaskItem" enctype="multipart/form-data">
                            <input class="userId"  value="" name="userId" type="hidden">
                            <div class="form-group">
                                <label  class="col-sm-3 control-label">选择父目录：</label>
                                <div class="col-sm-8">
                                    <select name="catalogueId" url="${pageContext.request.contextPath}/admin/getTree" class="easyui-combotree" style="width:500px;height: 30px"></select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">资源名称：</label>
                                <div class="col-sm-8">
                                    <input id="itemName" name="itemName" class="form-control" type="text" value="${resource.name}" placeholder="如:诵经,观音心咒……&nbsp;注:资源类型为音乐时，此处添加音乐名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">别 名：</label>
                                <div class="col-sm-8">
                                    <input id="aliasName" name="aliasName" class="form-control" type="text" value="${resource.sourceKey}" placeholder="如:六字大明咒 ,六字真言……">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">资源描述：</label>
                                <div class="col-sm-8">
                                    <input id="titleDesc" name="titleDesc"  class="form-control" type="text" value="${resource.sourceKey}"  placeholder="如:南无文殊菩萨……">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">禅修集数：</label>
                                <div class="col-sm-8">
                                    <input id="versionNumber" name="versionNumber"  class="form-control" type="text" value="${resource.sourceKey}"  placeholder="中文数字....">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">经书排序：</label>
                                <div class="col-sm-8">
                                    <input id="sort" name="sort" class="form-control" type="text" value="${resource.sourceKey}" placeholder="如:数值越大，越靠前……">
                                </div>
                            </div>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-3 control-label">是否可供：</label>--%>
                                <%--<div>--%>
                                    <%--<label class="radio-inline">--%>
                                        <%--<input type="radio" name="isSupport" id="isSupport1" value="1" > 可供--%>
                                    <%--</label>--%>
                                    <%--<label class="radio-inline">--%>
                                        <%--<input type="radio" name="isSupport" id="isSupport2" value="0" checked> 不可供--%>
                                    <%--</label>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">经书类型：</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="bookType" id="bookType1" value="1" checked > 经文精选
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="bookType" id="bookType2" value="2" > 论释精选
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="bookType" id="bookType3" value="3" > 著作精选
                                    </label>
                                </div>
                            </div>

                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-3 control-label">资源类型：</label>--%>
                                <%--<div>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="taskTypeId" value="10001" checked> 磕大头--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="taskTypeId" value="10002"> 拜忏--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="taskTypeId" value="10003"> 供佛--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="taskTypeId" value="10004"> 念佛--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="taskTypeId" value="10005"> 念咒--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="taskTypeId" value="10006"> 诵经--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="taskTypeId" value="10007"> 禅修--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="taskTypeId" value="10008"> 音乐--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input type="checkbox" name="taskTypeId" value="10011"> 其他课诵--%>
                                    <%--</label>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>功课图片：</label>
                                <div class="col-sm-6">
                                    <input name="itemPic" class="form-control" type="text" value="${resource.itemPic}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="uploadFile"  class="itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>经书类型图片《小》：</label>
                                <div class="col-sm-6">
                                    <input name="smallPic" class="form-control" type="text" value="${resource.smallPic}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="uploadFile"  class="itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>做功课背景图片：</label>
                                <div class="col-sm-6">
                                    <input name="backgroundPic" class="form-control" type="text" value="${resource.backgroundPic}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="uploadFile"  class="itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>资源简介：</label>
                                <div class="col-sm-8">
                                    <textarea name="itemIntro" placeholder="请填写资源简介的信息……" style="width: 500px;height: 100px"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary saveItem" id="btn" type="button">保存</button>&nbsp;&nbsp;
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input id="typeId" type="hidden" name="typeId" value="">
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
        //获取地址栏的信息
        function GetQueryString(name){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)return  unescape(r[2]); return null;
        }

        var userId = window.parent.document.getElementById("userId").defaultValue;
        $(".userId").val(userId);
        var typeId = GetQueryString('typeId');
        $("#typeId").val(typeId);
        document.getElementById("callBack").href='${pageContext.request.contextPath}/view/templates/admin/newResource/index.jsp?typeId='+typeId;

        var itemID='';
        function addItemId() {
           var itemId_arr = document.querySelectorAll('.itemId');
            console.log(itemId_arr.length);
            console.log(itemId_arr);
            for(var i =0 ;i<itemId_arr.length;i++) {
                itemId_arr[i].value=itemID;
                console.log(itemID);
                console.log(itemId_arr[0].value);
            }
        }

//        保存功课
        $(document).on("click",".saveItem",function(){

            var typeId = document.getElementById("typeId").value;
            var form = new FormData($(item)[0]);
            console.log(form);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/saveTaskItem?typeId="+typeId,
                data:form,
                success: function(msg){
                    if (typeId == 10006){
                        //复制内容模版模块
                        var html = template('bookContentTpl');
                        $(".row").parent().append(html);
                        document.getElementById('btn3').style.display="none";
                    }
                    if (typeId == 10007){
                        //复制内容模版模块
                        var html = template('meditationContentTpl');
                        $(".row").parent().append(html);
                        document.getElementById('btn3').style.display="none";
                    }

                    itemID = msg.rows.itemId;
                    addItemId();
                    document.getElementById("btn").disabled=true;
                    //$("#itemId").val(msg.rows.itemId);
                    layer.msg(msg.message, {time: 2000},function(){
                        layer.close();
                    });
                }
            });

        });

//        保存内容
        $(document).on("click",".saveContent",function(){
            var typeId = document.getElementById("typeId").value;
            var _this = $(this);
            addItemId();
            var userId = window.parent.document.getElementById("userId").defaultValue;
            $(".userId").val(userId);
            var con = _this.closest(".form-horizontal");
            var form = new FormData(con[0]);
            console.log(form);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/saveTaskContent?typeId="+typeId,
                data:form,
                success: function(msg){
                    if (msg.code==1){
                        layer.msg(msg.message, {time: 2000},function(){
                            layer.close();
                        });
                    }else if (msg.code==2){

                    _this.attr('disabled',"true");

                    layer.msg(msg.message, {time: 2000},function(){
                        layer.close();
                    });
                }else {
                        layer.msg(msg.message, {time: 2000},function(){
                            layer.close();
                        });
                    }
                }
            });

        });


        $(document).on("click",".addContent",function(){
            var typeId = document.getElementById("typeId").value;
            if (typeId == 10006){
                //复制内容模版模块
                var html = template('bookContentTpl');
                $(".row").parent().append(html);
                document.getElementsByClassName('btn3').style.display="none";
            }
            if (typeId == 10007){
                //复制内容模版模块
                var html = template('meditationContentTpl');
                $(".row").parent().append(html);
                document.getElementsByClassName('btn3')[3].style.display="none";
            }
        });
    });

    /**
     * 文件上传
     */
    $(document).on("change",".uploadFile",function(){

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
