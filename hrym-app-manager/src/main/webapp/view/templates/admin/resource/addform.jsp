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
<%--模版--%>
    <body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

            <%--内容添加--%>
    <div class="ibox float-e-margins" id="itemContent">
        <div class="ibox-title">
            <h5>内容添加</h5>
        </div>
        <div class="ibox-content" >
            <form class="form-horizontal m-t" id="content" method="post" action="" >
                <input type="hidden" id="itemId" name="itemId" value="${resource.itemId}">
                <div class="form-group">
                    <label class="col-sm-3 control-label">版本名称:</label>
                    <div class="col-sm-8">
                        <input id="versionName" name="versionName" class="form-control" type="text" value="${resource.name}" placeholder="如：释迦牟尼佛圣号">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">文字内容:</label>
                    <div class="col-sm-8">
                        <input id="text" name="text" class="form-control" type="text" value="${resource.name}" placeholder="请输入纯文字">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>文档内容：</label>
                    <div class="col-sm-8">
                        <label class="col-sm-3 control-label">
                            <input type="file" name="taskFileTxt" class="itemPic oas-filebutton-default" placeholder="请选择文档">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>图片内容：</label>
                    <div class="col-sm-8">
                        <label class="col-sm-3 control-label">
                            <input type="file" name="taskFilePic" class="itemPic oas-filebutton-default" placeholder="请选择图片文件">
                        </label>
                    </div>
                </div>
                <%--<div class="form-group">--%>
                    <%--<label class="col-sm-3 control-label">图片版本名称：</label>--%>
                    <%--<div class="col-sm-8">--%>
                        <%--<input id="picVersionName" name="picVersionName" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入图片版本名称">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <label class="col-sm-3 control-label">来 源：</label>
                    <div>
                        <label class="radio-inline">
                            <input type="radio" name="origin" id="origin" value="佛教文库" checked> 佛教文库
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="origin" value="佛教典籍"> 佛教典籍
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">出 处：</label>
                    <div class="col-sm-8">
                        <input id="source" name="source" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入出处">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">作 者：</label>
                    <div class="col-sm-8">
                        <input id="author" name="author" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入作者">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">译 者：</label>
                    <div class="col-sm-8">
                        <input id="translator" name="translator" class="form-control" type="text" value="${resource.sourceKey}"placeholder="请输入译者">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-8 col-sm-offset-3">
                        <button class="btn btn-primary saveContent" id="btn1" type="button">保存</button>&nbsp;&nbsp;
                        <button class="btn btn-primary addContent" type="button" >添加内容</button>&nbsp;&nbsp;
                        <%--<button class="btn btn-primary addMusic" type="button">添加音频</button>--%>
                    </div>
                </div>
            </form>
        </div>
    </div>
    </div>
</body>
<%--音频添加--%>
<script id="musicTpl" type="text/html">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>音频添加</h5>
        </div>
        <div class="ibox-content" >
            <form class="form-horizontal m-t" id="music" method="post" action="/static1/static/admin/resource/edit" >
                <input id="itemContentId" type="hidden" name="itemContentId" value="">
                <input id="itemId1" type="hidden" name="itemId" value="">
                <div class="form-group">
                    <label class="col-sm-3 control-label">音频名称：</label>
                    <div class="col-sm-8">
                        <input id="musicName" name="musicName" class="form-control" type="text" value="${resource.name}" placeholder="请输入音频名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频文件：</label>
                    <div class="col-sm-8">
                        <label class="col-sm-3 control-label">
                            <input type="file" name="taskMusicFile" class="itemPic oas-filebutton-default" placeholder="选择音频文件">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频字幕：</label>
                    <div class="col-sm-8">
                        <label class="col-sm-3 control-label">
                            <input type="file" name="taskMusicSubtitle" class="itemPic oas-filebutton-default" placeholder="请输入音频字幕">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">起腔结束时间：</label>
                    <div class="col-sm-8">
                        <input id="endTime" name="endTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}"placeholder="请输入起腔结束时间">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">开始计数时间：</label>
                    <div class="col-sm-8">
                        <input id="startTime" name="startTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}"placeholder="请输入开始计数时间">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">初始计数数量：</label>
                    <div class="col-sm-8">
                        <input id="startNum" name="startNum" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入初始数量">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">步 长：</label>
                    <div class="col-sm-8">
                        <input id="step" name="step" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入步长">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">单曲循环切入时间：</label>
                    <div class="col-sm-8">
                        <input id="settingTime" name="settingTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入单曲循环切入时间：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">歌 手：</label>
                    <div class="col-sm-8">
                        <input id="singer" name="singer" class="form-control" type="text" value="${resource.sourceKey}"placeholder="请输入歌手名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">专辑名称：</label>
                    <div class="col-sm-8">
                        <input id="albumName" name="albumName" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入专辑名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">磁盘号：</label>
                    <div class="col-sm-8">
                        <input id="diskNumber" name="diskNumber" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.sourceKey}" placeholder="请输入磁盘号">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">作 曲：</label>
                    <div class="col-sm-8">
                        <input id="composer" name="composer" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入作曲名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">发行时间：</label>
                    <div class="col-sm-8">
                        <input class="form-control layer-date" name="taskPushTime" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                        <label class="laydate-icon"></label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">轨道号：</label>
                    <div class="col-sm-8">
                        <input id="orbitalNumber" name="orbitalNumber" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入数字轨道号">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">风 格：</label>
                    <div class="col-sm-8">
                        <input id="style" name="style" class="form-control" type="text" value="${resource.sourceKey}"placeholder="请输入分格">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">版本号：</label>
                    <div class="col-sm-8">
                        <input id="versionId" name="versionId" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入版本号">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">需要设定时间：</label>
                    <div class="col-sm-8">
                        <input id="needSetTime" name="needSetTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入需要设定时间：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">开示结束时间：</label>
                    <div class="col-sm-8">
                        <input id="shouEndTime" name="shouEndTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入开示结束时间：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">循环时间段：</label>
                    <div class="col-sm-8">
                        <input id="roundTime" name="roundTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入循环时间段：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">结束时间：</label>
                    <div class="col-sm-8">
                        <input id="downTime" name="downTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入结束时间：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">止静时间开始：</label>
                    <div class="col-sm-8">
                        <input id="voiceStart" name="voiceStart" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入止静时间开始：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">止静时间结束：</label>
                    <div class="col-sm-8">
                        <input id="voiceDown" name="voiceDown" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入止静时间结束：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频图片：</label>

                    <div class="col-sm-6">
                        <input name="musicPic" class="form-control" type="text" readonly>
                    </div><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="addFile" name="c" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-8 col-sm-offset-3">
                        <button class="btn btn-primary saveMusic" id="btn2" type="button">保存</button>&nbsp;&nbsp;
                        <button class="btn btn-primary addMusic" type="button">添加音频</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</script>


    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/bootstrap.min.js?v=3.3.6"></script>

    <%--引入复制js--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/oasis/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/oasis/js/oasis.js"></script>
    <!-- 自定义js -->
    <script src="/static1/static/assets/js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/laydate/laydate.js"></script>
    <script type="text/javascript">

    $(document).ready(function () {

//        保存功课
        $(document).on("click",".saveItem",function(){
            var form = new FormData($(item)[0]);
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/insertTaskItem",
                data:form,
                beforeSend: function(XMLHttpRequest) {
                    layer.msg('提交中，请稍等。。。', {
                        icon: 16
                        ,shade: 0.01
                    });
                },
                success: function(msg){
                    //复制内容模版模块
                    var html = template('contentTpl');
                    $(".row").parent().append(html);
                    document.getElementById("itemId").value=msg.rows.itemId;
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
            debugger
            document.getElementById("itemId").value=msg.rows.itemId;
            document.getElementById("btn").disabled=true;
            var form = new FormData($(content)[0]);
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/insertTaskContent",
                data:form,
                beforeSend: function(XMLHttpRequest) {
                    layer.msg('提交中，请稍等。。。', {
                        icon: 16
                        ,shade: 0.01
                    });
                },
                success: function(msg){
                    if (msg.code==1){
                        parent.layer.msg('请先添加功课哦。。。');
                        return;
                    }
                    //复制内容模版模块
                    var html = template('musicTpl');
                    $(".row").parent().append(html);
                    document.getElementById("itemId1").value=msg.rows.itemId;
                    document.getElementById("itemContentId").value=msg.rows.itemContentId;
                    document.getElementById("btn1").disabled=true;
                    layer.msg(msg.message, {time: 2000},function(){
                        layer.close();
                    });
                }
            });

        });

//      保存音频
        $(document).on("click",".saveMusic",function(){
            var form = new FormData($(music)[0]);
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/insertTaskMusic",
                data:form,
                beforeSend: function(XMLHttpRequest) {
                    layer.msg('提交中，请稍等。。。', {
                        icon: 16
                        ,shade: 0.01
                    });
                },
                success: function(msg){
                    if (msg.code==1){
                        parent.layer.msg('请先添加功课和功课内容哦。。。');
                        return;
                    }
                    document.getElementById("btn2").disabled=true;
                    layer.msg(msg.message, {time: 2000},function(){
                        layer.close();
                    });
                }
            });

        });

        $(document).on("click",".addContent",function(){
            //复制内容模版模块
            var html = template('contentTpl');
            $(".row").parent().append(html);
        });
        //复制音频模版模块
        $(document).on("click",".addMusic",function(){

            var html = template('musicTpl');
            $(".row").parent().append(html);
        });
    });
    //资源删除
    $(document).on("click",".del",function(){

        var ibox = $(this).closest(".ibox.float-e-margins");
        var itemId = ibox.find(".itemId").val();
        if(itemId==''){
            ibox.remove();
        }else{
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            $.ajax({

                type: "post",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/deleteTaskItem?itemId="+itemId,
                success: function(msg){
                    layer.msg(msg.message, {time: 2000},function(){
                        layer.close();
                        if(msg.code=='0'){
                            ibox.remove();
                        }
                    });
                }
            });
        }
    });

    /**
     * 文件上传
     */
    $(document).on("change",".addFile",function(){

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
