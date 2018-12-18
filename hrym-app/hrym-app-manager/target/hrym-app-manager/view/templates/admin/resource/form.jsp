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
<script id="contentTpl" type="text/html">
    <%--内容添加--%>
    <div class="ibox float-e-margins" id="itemContent">
        <div class="ibox-title">
            <h5>内容添加</h5>
        </div>
        <div class="ibox-content" >
            <form class="form-horizontal m-t" id="content" method="post" action="" >
                <%--//用户ID--%>
                <input class="userId"  value="" name="userId" type="hidden">
                <input type="hidden" id="itemId" name="itemId" class="itemId" value="">
                <div class="form-group">
                    <label class="col-sm-3 control-label">版本名称：</label>
                    <div class="col-sm-8">
                        <input id="versionName" name="versionName" class="form-control" type="text" value="${resource.name}" placeholder="如:释迦牟尼佛圣号（藏）">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">文字内容：</label>
                    <div class="col-sm-8">
                        <input id="text" name="text" class="form-control" type="text" value="${resource.name}" placeholder="请输入纯文字内容">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>文档内容：</label>
                    <div class="col-sm-8">
                        <label class="col-sm-3 control-label">
                            <input type="file" name="taskFileTxt" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>图片内容：</label>
                    <div class="col-sm-8">
                        <label class="col-sm-3 control-label">
                            <input type="file" name="taskFilePic" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
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
                    <label class="col-sm-3 control-label">资源版本：</label>
                    <div>
                        <label class="radio-inline">
                            <input type="radio" name="version" id="version" value="0" checked> 藏版
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="version" value="1"> 汉版
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">视频版本号：</label>
                    <div class="col-sm-8">
                        <input id="contentVersion" name="contentVersion" class="form-control" type="text" value="${resource.name}" placeholder="如:供佛（藏）">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>视频文件：</label>

                    <div class="col-sm-6">
                        <input name="videoFile" class="form-control" type="text" value="${music.videoFile}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="addvideoFile" name="addvideoFile" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">出 处：</label>
                    <div class="col-sm-8">
                        <input id="source" name="source" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入出处名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">作 者：</label>
                    <div class="col-sm-8">
                        <input id="author" name="author" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入作者名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">译 者：</label>
                    <div class="col-sm-8">
                        <input id="translator" name="translator" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入译者名称">
                    </div>
                </div>
                    <%--语音识别--%>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">有无语音识别：</label>
                        <div>
                            <label class="radio-inline">
                                <input type="radio" name="voiceCount" id="voiceCount1" value="1" > 有
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="voiceCount" id="voiceCount2" value="2"checked > 没有
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">语音识别名称：</label>
                        <div class="col-sm-8">
                            <input id="voiceName" name="voiceName" class="form-control" type="text" value="${resource.voiceName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>语音识别字典文件：</label>

                        <div class="col-sm-6">
                            <input name="voiceDic" class="form-control" type="text" value="" readonly>
                        </div>
                        <div class="col-sm-3">
                            <label class="col-sm-3 control-label">
                                <input type="file" class="addvideoFile" name="addvideoFile" class="itemPic oas-filebutton-default">
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>语音识别模型文件：</label>

                        <div class="col-sm-6">
                            <input name="voiceLm" class="form-control" type="text" value="" readonly>
                        </div>
                        <div class="col-sm-3">
                            <label class="col-sm-3 control-label">
                                <input type="file" class="addvideoFile" name="addvideoFile" class="itemPic oas-filebutton-default">
                            </label>
                        </div>
                    </div>
                <div class="form-group">
                    <div class="col-sm-8 col-sm-offset-3">
                        <button class="btn btn-primary saveContent" id="btn1" type="button">保存</button>&nbsp;&nbsp;
                        <button class="btn btn-primary addContent" type="button" >添加内容</button>&nbsp;&nbsp;
                    </div>
                </div>
            </form>
        </div>
    </div>
</script>
<%--音频添加--%>
<script id="musicTpl" type="text/html">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>音频添加</h5>
        </div>

        <div class="ibox-content" >
            <form class="form-horizontal m-t" method="post" action="" >
                <input class="itemContentId" type="hidden" name="itemContentId" value="">
                <input class="itemId" type="hidden" name="itemId" value="">
                <input class="userId"  value="" name="userId" type="hidden">
                <div class="form-group">
                    <label class="col-sm-3 control-label">音频名称：</label>
                    <div class="col-sm-8">
                        <input id="musicName" name="musicName" class="form-control" type="text" value="${resource.name}" placeholder="请输入音频名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">音频版本：</label>
                    <div>
                        <label class="radio-inline">
                            <input type="checkbox" name="musicVersion" id="musicVersion" value="1" ${bean.musicVersion=='1'?'checked':''}> 标准版
                        </label>
                        <%--<label class="radio-inline">--%>
                            <%--<input type="radio" name="musicVersion" value="2" ${bean.musicVersion=='2'?'checked':''}> 快速版--%>
                        <%--</label>--%>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>android版音频文件：</label>

                    <div class="col-sm-6">
                        <input name="musicFileAndroid" class="form-control" type="text" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file"  name="taskandroid" class="itemPic oas-filebutton-default addmusicfileA">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频文件：</label>
                    <div class="col-sm-8">
                        <label class="col-sm-3 control-label">
                            <input type="file" name="taskMusicFile" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频字幕：</label>
                    <div class="col-sm-8">
                        <label class="col-sm-3 control-label">
                            <input type="file" name="taskMusicSubtitle" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">起腔结束时间：</label>
                    <div class="col-sm-8">
                        <input id="endTime" name="endTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入起腔结束时间：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">开始计数时间：</label>
                    <div class="col-sm-8">
                        <input id="startTime" name="startTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入开始计数时间：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">初始计数数量：</label>
                    <div class="col-sm-8">
                        <input id="startNum" name="startNum" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入初始计数数量：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">步 长：</label>
                    <div class="col-sm-8">
                        <input id="step" name="step" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入步 长：：number">
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
                        <input id="singer" name="singer" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入歌手名称">
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
                        <input id="diskNumber" name="diskNumber" class="form-control" type="text" value="${resource.sourceKey}" placeholder="请输入磁盘号">
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
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>子音频URL：</label>

                    <div class="col-sm-6">
                        <input name="value" class="form-control" type="text" value="${music.value}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="addmusicvalue" name="taskvalue" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">子音频描述：</label>
                    <div class="col-sm-8">
                        <input id="des" name="des" class="form-control" type="text" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">子音频类型：</label>
                    <div class="col-sm-8">
                        <input id="type" name="type" class="form-control" type="text" value="">
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
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12 total">
                <%--功课添加--%>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>功课添加</h5><tr>&emsp;&emsp;&emsp;<a href="${pageContext.request.contextPath}/view/templates/admin/resource/index.jsp">返回</a>
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
                                <label class="col-sm-3 control-label">是否可供：</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="isSupport" id="isSupport1" value="1" > 可供
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="isSupport" id="isSupport2" value="0" checked> 不可供
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">资源类型：</label>
                                <div>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="taskTypeId" value="10001" checked> 磕大头
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="taskTypeId" value="10002"> 拜忏
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="taskTypeId" value="10003"> 供佛
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="taskTypeId" value="10004"> 念佛
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="taskTypeId" value="10005"> 念咒
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="taskTypeId" value="10006"> 诵经
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="taskTypeId" value="10007"> 禅修
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="taskTypeId" value="10008"> 音乐
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="taskTypeId" value="10011"> 其他课诵
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>图片：</label>
                                <div class="col-sm-8">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" name="taskItemPic" class="itemPic oas-filebutton-default">
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
        var userId = window.parent.document.getElementById("userId").defaultValue;
        $(".userId").val(userId);

        var itemID='';
        function addItemId() {
           var itemId_arr = document.querySelectorAll('.itemId');
            console.log(itemId_arr.length);
            console.log(itemId_arr);
            for(var i =0 ;i<itemId_arr.length;i++) {
                itemId_arr[i].value=itemID;
                console.log(itemID);
                console.log(itemId_arr[0].value);
//            alert(itemId_arr[0].value)
            }
            }
//
//        保存功课
        $(document).on("click",".saveItem",function(){

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
                url: "${pageContext.request.contextPath}/admin/insertTaskItem",
                data:form,
                success: function(msg){
                    //复制内容模版模块
                    var html = template('contentTpl');
                    $(".row").parent().append(html);

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
                url: "${pageContext.request.contextPath}/admin/insertTaskContent",
                data:form,
                success: function(msg){
                    if (msg.code==1){
                        layer.msg(msg.message, {time: 2000},function(){
                            layer.close();
                        });
                    }else if (msg.code==2){
                    //打开音频模版模块
                    var html = template('musicTpl');
                    $(".row").parent().append(html);
                    $("#itemContentId").val(msg.rows.itemContentId);

//                    document.getElementById("itemId1").value=msg.rows.itemId;
//                    document.getElementById("itemContentId").value=msg.rows.itemContentId;
//                    document.getElementById("btn1").disabled=true;

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

//      保存音频
        $(document).on("click",".saveMusic",function(){

            var _this = $(this);

//            $(".itemId").val($("#itemId").val());
            addItemId()
            //用户ID
            var userId = window.parent.document.getElementById("userId").defaultValue;
            $(".userId").val(userId);
            $(".itemContentId").val($("#itemContentId").val());

            var con = _this.closest(".form-horizontal");

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
                url: "${pageContext.request.contextPath}/admin/insertTaskMusic",
                data:form,
                success: function(msg){
                    if (msg.code==1){
                        parent.layer.msg('请先添加功课和功课内容哦。。。');
                        return;
                    }
                    _this.attr('disabled',"true");

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
        var userId = window.parent.document.getElementById("userId").defaultValue;
        $(".userId").val(userId);
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
                url: "${pageContext.request.contextPath}/admin/deleteTaskItem?itemId="+itemId+'&userId='+userId,
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
    /**
     * 子音频文件上传
     */
    $(document).on("change",".addmusicvalue",function(){

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
    /**
     * 功课内容音频文件上传
     */
    $(document).on("change",".addmusicFile",function(){

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
    /**
     * 功课内容视频文件上传
     */
    $(document).on("change",".addvideoFile",function(){

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
    /**
     * android音频文件上传
     */
    $(document).on("change",".addmusicfileA",function(){

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
