<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 资源添加</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">
    <style>

        </style>
</head>
<%--模版--%>
<%--音频添加--%>
<script id="musicTpl" type="text/html">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5 class="col-sm-10" >添加音频版本</h5>

            <button type="button" class="btn btn-default btn-xs saveMusic">
                <span class="glyphicon glyphicon-plus install" aria-hidden="true"></span> 保存音频
            </button>

            <button type="button" class="btn btn-default btn-xs del">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 删除
            </button>
        </div>
        <div class="ibox-content" >
            <form class="form-horizontal m-t musicForm" method="post" action="" >
                <input  type="hidden" class="itemContentId" name="itemContentId" value="">
                <input  type="hidden" class="itemId" name="itemId" value="">
                <input  type="hidden" class="musicId" name="musicId" value="">
                <input  type="text" class="userId" name="userId" value="">
                <div class="form-group">
                    <label class="col-sm-3 control-label">音频名称：</label>
                    <div class="col-sm-8">
                        <input id="musicName" name="musicName" class="form-control" type="text" value="">
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
                    <label class="col-sm-3 control-label">音频版本：</label>
                    <div>
                        <label class="radio-inline">
                            <input type="checkbox" name="musicVersion" id="musicVersion" value="1"> 标准版
                        </label>
                        <%--<label class="radio-inline">--%>
                            <%--<input type="radio" name="musicVersion" value="2" > 快速版--%>
                        <%--</label>--%>
                    </div>
                </div>
                <div class="form-group"><br><br>
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
                        <input id="endTime" name="endTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">开始计数时间：</label>
                    <div class="col-sm-8">
                        <input id="startTime" name="startTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">初始计数数量：</label>
                    <div class="col-sm-8">
                        <input id="startNum" name="startNum" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">步 长：</label>
                    <div class="col-sm-8">
                        <input id="step" name="step" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">单曲循环切入时间：</label>
                    <div class="col-sm-8">
                        <input id="settingTime" name="settingTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">歌 手：</label>
                    <div class="col-sm-8">
                        <input id="singer" name="singer" class="form-control" type="text" value="${resource.sourceKey}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">专辑名称：</label>
                    <div class="col-sm-8">
                        <input id="albumName" name="albumName" class="form-control" type="text" value="${resource.sourceKey}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">磁盘号：</label>
                    <div class="col-sm-8">
                        <input id="diskNumber" name="diskNumber" class="form-control" type="text" value="${resource.sourceKey}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">作 曲：</label>
                    <div class="col-sm-8">
                        <input id="composer" name="composer" class="form-control" type="text" value="${resource.sourceKey}">
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
                        <input id="orbitalNumber" name="orbitalNumber" class="form-control" type="text" value="${resource.sourceKey}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">风 格：</label>
                    <div class="col-sm-8">
                        <input id="style" name="style" class="form-control" type="text" value="${resource.sourceKey}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">版本号：</label>
                    <div class="col-sm-8">
                        <input id="versionId" name="versionId" class="form-control" type="text" value="${resource.sourceKey}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">需要设定时间：</label>
                    <div class="col-sm-8">
                        <input  name="needSetTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入需要设定时间：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">开示结束时间：</label>
                    <div class="col-sm-8">
                        <input  name="shouEndTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入开示结束时间：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">循环时间段：</label>
                    <div class="col-sm-8">
                        <input  name="roundTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入循环时间段：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">结束时间：</label>
                    <div class="col-sm-8">
                        <input  name="downTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入结束时间：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">止静时间开始：</label>
                    <div class="col-sm-8">
                        <input  name="voiceStart" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入止静时间开始：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">止静时间结束：</label>
                    <div class="col-sm-8">
                        <input  name="voiceDown" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.name}" placeholder="请输入止静时间结束：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频图片：</label>

                    <div class="col-sm-6">
                        <input name="musicPic" class="form-control" type="text" value="${music.musicPic}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="addFile" name="taskMusicFile" class="itemPic oas-filebutton-default">
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
                        <input id="type" name="type" class="form-control" type="text" value="1">
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
                <%--编辑资源--%>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="form-group">
                            <h5  class="col-sm-10" >编辑资源(id:${bean.itemContentId})内容</h5>
                            <a  href="${pageContext.request.contextPath}/view/templates/admin/resource/index.jsp">返回</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-default btn-xs saveContent">
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 编辑
                            </button>

                            <button type="button" class="btn btn-default btn-xs addMusic">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加音频
                            </button>

                        </div>
                    </div>
                    <div class="ibox-content" >
                        <form class="form-horizontal m-t contentForm"  method="post" action="" >

                            <input  type="hidden" id="itemContentId" name="itemContentId" value="${bean.itemContentId}">
                            <input  type="hidden" id="itemId" name="itemId" value="${bean.itemId}">
                            <input  type="hidden" class="userId" name="userId" value="">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">版本名称：</label>
                                <div class="col-sm-8">
                                    <input  name="versionName" class="form-control" type="text" value="${bean.versionName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">文字内容：</label>
                                <div class="col-sm-8">
                                    <input name="text" class="form-control" type="text" value="${bean.text}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>文档内容：</label>
                                <div class="col-sm-6">
                                        <input name="fileTxt" class="form-control" type="text" value="${bean.fileTxt}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file"  class="fileEdit" name="taskFileTxt" class="itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>图片内容：</label>

                                <div class="col-sm-6">
                                    <input name="filePic" class="form-control" type="text" value="${bean.filePic}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="fileEdit" name="taskFilePic" class="itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">来 源：</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="origin" value="佛教文库" ${bean.origin=='佛教文库'?'checked':''}> 佛教文库
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="origin" value="佛教典籍" ${bean.origin=='佛教典籍'?'checked':''}> 佛教典籍
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">资源版本：</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="version" id="version" value="0" ${bean.version=='0'?'checked':''}> 藏版
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="version" value="1" ${bean.version=='1'?'checked':''}> 汉版
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">视频版本号：</label>
                                <div class="col-sm-8">
                                    <input id="contentVersion" name="contentVersion" class="form-control" type="text" value="${bean.contentVersion}" placeholder="如：供佛。。。">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>视频文件：</label>

                                <div class="col-sm-6">
                                    <input name="videoFile" class="form-control" type="text" value="${bean.videoFile}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="updatevideoFile" name="addvideoFile" class="itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">出 处：</label>
                                <div class="col-sm-8">
                                    <input id="source" name="source" class="form-control" type="text" value="${bean.source}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">作 者：</label>
                                <div class="col-sm-8">
                                    <input id="author" name="author" class="form-control" type="text" value="${bean.author}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">译 者：</label>
                                <div class="col-sm-8">
                                    <input id="translator" name="translator" class="form-control" type="text" value="${bean.translator}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">有无语音识别：</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="voiceCount" id="voiceCount" value="1" ${bean.voiceCount=='1'?'checked':''}> 有
                                    </label>
                                    <label class="radio-inline">
                                        &nbsp;&nbsp;&nbsp;<input type="radio" name="voiceCount" id="voiceCount1" value="2" ${bean.voiceCount=='2'?'checked':''}> 没有
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">语音识别名称：</label>
                                <div class="col-sm-8">
                                    <input id="voiceName" name="voiceName" class="form-control" type="text" value="${bean.voiceName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>语音识别字典文件：</label>
                                <div class="col-sm-6">
                                    <input name="voiceDic" class="form-control" type="text" value="${bean.voiceDic}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="fileEdit" name="taskItemPic" class="itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>语音识别模型文件：</label>
                                <div class="col-sm-6">
                                    <input name="voiceLm" class="form-control" type="text" value="${bean.voiceLm}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="fileEdit" name="taskItemPic" class="itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <c:forEach var="music" items="${bean.musicVos}">

                    <div class="ibox float-e-margins" id="i">
                        <div class="ibox-title">
                            <div class="form-group">
                                <h5  class="col-sm-10" >编辑音频(id:${music.musicId})内容</h5>
                                <button type="button" class="btn btn-default btn-xs editMusic">
                                    <span class="glyphicon glyphicon-edit" aria-hidden="true" value="编辑">修改</span>
                                </button>
                                <button type="button" class="btn btn-default btn-xs del">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 删除
                                </button>

                            </div>
                        </div>
                        <div class="ibox-content" >
                            <form class="form-horizontal m-t editMusicForm" method="post" action="" >
                                <input  type="hidden" name="musicId" class="musicId" value="${music.musicId}">
                                <input  type="hidden" class="userId" name="userId" value="">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">音频名称：</label>
                                    <div class="col-sm-8">
                                        <input  name="musicName" class="form-control" type="text" value="${music.musicName}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">音频版本：</label>
                                    <div>
                                        <label class="radio-inline">
                                            <input type="checkbox" name="musicVersion"  value="1" ${music.musicVersion=='1'?'checked':''}> 标准版
                                        </label>
                                        <%--<label class="radio-inline">--%>
                                            <%--<input type="radio" name="musicVersion" value="2" ${music.musicVersion=='2'?'checked':''}> 快速版--%>
                                        <%--</label>--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>android版音频文件：</label>
                                    <div class="col-sm-6">
                                        <input name="musicFileAndroid" class="form-control" type="text" value="${music.musicFileAndroid}" readonly>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="col-sm-3 control-label">
                                            <input type="file"  name="musicandroid" class="itemPic oas-filebutton-default updatemusicfileA">
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频文件：</label>

                                    <div class="col-sm-6">
                                        <input name="musicFile" class="form-control" type="text" value="${music.musicFile}" readonly>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="col-sm-3 control-label">
                                            <input type="file" class="fileEdit" name="taskMusicFile" class="itemPic oas-filebutton-default">
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频字幕：</label>

                                    <div class="col-sm-6">
                                        <input name="musicSubtitle" class="form-control" type="text" value="${music.musicSubtitle}" readonly>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="col-sm-3 control-label">
                                            <input type="file" class="fileEdit" name="taskMusicSubtitle" class="itemPic oas-filebutton-default">
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">起腔结束时间：</label>
                                    <div class="col-sm-8">
                                        <input  name="endTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${music.endTime}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">开始计数时间：</label>
                                    <div class="col-sm-8">
                                        <input  name="startTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${music.startTime}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">初始计数数量：</label>
                                    <div class="col-sm-8">
                                        <input  name="startNum" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${music.startNum}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">步 长：</label>
                                    <div class="col-sm-8">
                                        <input  name="step" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${music.step}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">单曲循环切入时间：</label>
                                    <div class="col-sm-8">
                                        <input  name="settingTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${music.settingTime}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">歌 手：</label>
                                    <div class="col-sm-8">
                                        <input  name="singer" class="form-control" type="text" value="${music.singer}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">专辑名称：</label>
                                    <div class="col-sm-8">
                                        <input  name="albumName" class="form-control" type="text" value="${music.albumName}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">磁盘号：</label>
                                    <div class="col-sm-8">
                                        <input name="diskNumber" class="form-control" type="text" value="${music.diskNumber}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">作 曲：</label>
                                    <div class="col-sm-8">
                                        <input name="composer" class="form-control" type="text" value="${music.composer}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">发行时间：</label>
                                    <div class="col-sm-8">
                                        <input class="form-control layer-date" name="taskPushTime" value="${music.pushTimeDis}" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">轨道号：</label>
                                    <div class="col-sm-8">
                                        <input  name="orbitalNumber" class="form-control" type="text" value="${music.orbitalNumber}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">风 格：</label>
                                    <div class="col-sm-8">
                                        <input  name="style" class="form-control" type="text" value="${music.style}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">版本号：</label>
                                    <div class="col-sm-8">
                                        <input  name="versionId" class="form-control" type="text" value="${music.versionId}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">需要设定时间：</label>
                                    <div class="col-sm-8">
                                        <input id="needSetTime" name="needSetTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${music.needSetTime}" placeholder="请输入需要设定时间：number">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">开示结束时间：</label>
                                    <div class="col-sm-8">
                                        <input id="shouEndTime" name="shouEndTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${music.shouEndTime}" placeholder="请输入开示结束时间：number">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">循环时间段：</label>
                                    <div class="col-sm-8">
                                        <input id="roundTime" name="roundTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${music.roundTime}" placeholder="请输入循环时间段：number">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">结束时间：</label>
                                    <div class="col-sm-8">
                                        <input id="downTime" name="downTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${music.downTime}" placeholder="请输入结束时间：number">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">止静时间开始：</label>
                                    <div class="col-sm-8">
                                        <input id="voiceStart" name="voiceStart" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${music.voiceStart}" placeholder="请输入止静时间开始：number">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">止静时间结束：</label>
                                    <div class="col-sm-8">
                                        <input id="voiceDown" name="voiceDown" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${music.voiceDown}" placeholder="请输入止静时间结束：number">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频图片：</label>

                                    <div class="col-sm-6">
                                        <input name="musicPic" class="form-control" type="text" value="${music.musicPic}" readonly>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="col-sm-3 control-label">
                                            <input type="file" class="addEdit" name="taskMusicFile" class="itemPic oas-filebutton-default">
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="hidden" name="idtSubResource"value="${music.idtSubResource}"/>
                                    <input type="hidden" name="resourceld" value=""/>
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
                                        <input  name="des" class="form-control" type="text" value="${music.des}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">子音频类型：</label>
                                    <div class="col-sm-8">
                                        <input name="type" class="form-control" type="text" value="${music.type}">
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </c:forEach>
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

//       更新内容
        $(document).on("click",".saveContent",function(){
            var userId = window.parent.document.getElementById("userId").defaultValue;
            $(".userId").val(userId)
            var con = $(this).closest(".ibox.float-e-margins").find(".contentForm");

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
                url: "${pageContext.request.contextPath}/admin/updateTaskContent",
                data:form,
                success: function(msg){
                    if (msg.code==1){
                        parent.layer.msg('编辑内容失败，请联系管理员。。。');
                        return;
                    } else {
                        layer.msg('操作成功。。。');
                        layer.close();
                    }
                }
            });

        });

//      保存音频
        $(document).on("click",".saveMusic",function(){

            var _this = $(this);
            var con = _this.closest(".ibox.float-e-margins").find(".musicForm");

            con.find(".itemId").val($("#itemId").val());
            con.find(".itemContentId").val($("#itemContentId").val());
            var userId = window.parent.document.getElementById("userId").defaultValue;
            $(".userId").val(userId);
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
                        parent.layer.msg('添加音频失败，请联系管理员。。。');
                        return;
                    }
                    layer.msg(msg.message, {time: 2000},function(){
                        layer.close();
                    });

                    _this.attr('disabled',"true");
                }
            });

        });

        /**
         *编辑音频
         */
        $(document).on("click",".editMusic",function(){
            var userId = window.parent.document.getElementById("userId").defaultValue;
            $(".userId").val(userId);
            var con = $(this).closest(".ibox.float-e-margins").find(".editMusicForm");
            var form = new FormData(con[0]);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time:0
            });
            var time_a = new Date().getTime();
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/updateTaskMusic",
                data:form,
               success: function(msg){
                   layer.close();
                   layer.msg("编辑成功", {time: 2000},function(){
                       layer.close();
                   });
               }
            });

        });

    //音频删除
        $(document).on("click",".del",function(){

            var userId = window.parent.document.getElementById("userId").defaultValue;
            var ibox = $(this).closest(".ibox.float-e-margins");
            var musicId = ibox.find(".musicId").val();
            if(musicId==''){
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
                    url: "${pageContext.request.contextPath}/admin/deleteTaskmusic?musicId="+musicId+"&userId="+userId,
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


        //复制音频模版模块
        $(document).on("click",".addMusic",function(){

            var html = template('musicTpl');
            $(".row").parent().append(html);
        });

        /**
         * 文件上传
         */
        $(document).on("change",".fileEdit",function(){

            debugger
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
     * 文件上传
     */
    $(document).on("change",".addEdit",function(){

        debugger
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
    /**
     * android音频文件编辑上传
     */
    $(document).on("change",".updatemusicfileA",function(){

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
    $(document).on("change",".updatemusicFile",function(){

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
    $(document).on("change",".updatevideoFile",function(){

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
    var userId = window.parent.document.getElementById("userId").defaultValue;
//    function resetredio() {
//        var a= document.getElementById("musicVersion");
//
//        // 移除属性,两种方式都可
//        console.log(a);
//        //$browsers.removeAttr("checked");
//        $a.attr("checked",false)
//    }
    </script>
</body>

</html>
