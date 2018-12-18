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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/easyui/easyui.css"/>
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
            <%--内容修改--%>
            <div class="ibox float-e-margins" id="itemContent">
                <div class="ibox-title">
                    <h5>功课内容修改</h5>
                    <tr>&emsp; <a id="callBack"
                                  href="${pageContext.request.contextPath}/view/templates/admin/itemLesson/index.jsp">返回</a>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="content" method="post" action="">
                        <%--//用户ID--%>
                        <input id="itemContentId" type="hidden" name="itemContentId"
                               value="${map.content.itemContentId}">
                        <input class="userId" value="" name="userId" type="hidden">
                        <input type="hidden" id="itemId" name="itemId" class="itemId" value="${map.content.itemId}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">版本名称：<b style="color: red">*</b></label>
                            <div class="col-sm-8">
                                <input id="versionName" name="versionName" class="form-control" type="text"
                                       value="${map.content.versionName}" placeholder="如:释迦牟尼佛圣号（藏）">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">纯文字：</label>
                            <div class="col-sm-8">
                                <input id="text" name="text" class="form-control" type="text"
                                       value="${map.content.text}" placeholder="请输入纯文字内容">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>图片内容：<b
                                    style="color: red">*</b></label>
                            <div class="col-sm-6">
                                <input name="contentPic" class="form-control" type="text"
                                       value="${map.content.contentPic}" readonly>
                            </div>
                            <div class="col-sm-3">
                                <label class="col-sm-3 control-label">
                                    <input type="file" class="uploadFile itemPic oas-filebutton-default">
                                </label>
                            </div>

                        </div>
                        <%--<div class="form-group">--%>
                        <%--<label class="col-sm-3 control-label">来 源：<b style="color: red">*</b></label>--%>
                        <%--<div>--%>
                        <%--<label class="radio-inline">--%>
                        <%--<input type="radio" name="origin" id="origin" value="佛教文库" ${map.content.origin=='佛教文库'?'checked':''} checked> 佛教文库--%>
                        <%--</label>--%>
                        <%--<label class="radio-inline">--%>
                        <%--<input type="radio" name="origin" value="佛教典籍" ${map.content.origin=='佛教典籍'?'checked':''}> 佛教典籍--%>
                        <%--</label>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                        <%--<label class="col-sm-3 control-label">资源版本：<b style="color: red">*</b></label>--%>
                        <%--<div>--%>
                        <%--<label class="radio-inline">--%>
                        <%--<input type="radio" name="version" id="version" value="0" ${map.content.version=='0'?'checked':''} checked> 藏版--%>
                        <%--</label>--%>
                        <%--<label class="radio-inline">--%>
                        <%--<input type="radio" name="version" value="1" ${map.content.version=='1'?'checked':''}> 汉版--%>
                        <%--</label>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">视频版本号：<b style="color: red">*</b></label>
                            <div class="col-sm-8">
                                <input id="contentVersion" name="contentVersion" class="form-control" type="text"
                                       value="${map.content.contentVersion}" placeholder="如:供佛（藏）">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>视频文件：</label>

                            <div class="col-sm-6">
                                <input name="videoFile" class="form-control" type="text"
                                       value="${map.content.videoFile}" readonly>
                            </div>
                            <div class="col-sm-3">
                                <label class="col-sm-3 control-label">
                                    <input type="file" name="" class="uploadFile itemPic oas-filebutton-default">
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">有无语音识别：<b style="color: red">*</b></label>
                            <div>
                                <label class="radio-inline">
                                    <input type="radio" name="voiceCount" id="voiceCount1"
                                           value="1" ${map.content.voiceCount=='1'?'checked':''}> 有
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="voiceCount" id="voiceCount2"
                                           value="0" ${map.content.voiceCount=='0'?'checked':''} > 没有
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">android语音识别名称：</label>
                            <div class="col-sm-8">
                                <input id="voiceName" name="voiceName" class="form-control" type="text"
                                       value="${map.content.voiceName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>语音识别字典文件：</label>

                            <div class="col-sm-6">
                                <input name="voiceDic" class="form-control" type="text" value="${map.content.voiceDic}"
                                       readonly>
                            </div>
                            <div class="col-sm-3">
                                <label class="col-sm-3 control-label">
                                    <input type="file" class="uploadFile itemPic oas-filebutton-default">
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span
                                    class="oas-impInfo"></span>android语音识别文件：</label>

                            <div class="col-sm-6">
                                <input name="voiceLm" class="form-control" type="text" value="${map.content.voiceLm}"
                                       readonly>
                            </div>
                            <div class="col-sm-3">
                                <label class="col-sm-3 control-label">
                                    <input type="file" class="uploadFile itemPic oas-filebutton-default">
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">有无语速识别：<b style="color: red">*</b></label>
                            <div>
                                <label class="radio-inline">
                                    <input type="radio" name="speedCount" id="speedCount1"
                                           value="1" ${map.content.speedCount=='1'?'checked':''}> 有
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="speedCount" id="speedCount2"
                                           value="0" ${map.content.speedCount=='0'?'checked':''}> 没有
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>ios语速计数内容：</label>
                            <div class="col-sm-8">
                                <input name="voiceText" class="form-control" type="text"
                                       value="${map.content.voiceText}" placeholder="请输入ios语音技术字段">
                            </div>

                        </div>
                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary saveContent" id="btn1" type="button">保存</button>&nbsp;&nbsp;
                                <button class="btn btn-primary addContent" type="button">添加音频</button>&nbsp;&nbsp;
                            </div>
                        </div>
                        <input type="hidden" name="typeId" value="10000">
                    </form>
                </div>
            </div>
        </div>

        <%--音频修改--%>
        <c:forEach var="resource" items="${map.musicList}">
            <div class="col-sm-12 total">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>音频修改</h5>
                    </div>

                    <div class="ibox-content">
                        <form class="form-horizontal m-t" method="post" action="">
                            <input class="itemContentId" type="hidden" name="itemContentId"
                                   value="${resource.itemContentId}">
                            <input class="itemId" type="hidden" name="itemId" value="${resource.itemId}">
                            <input class="musicId" type="hidden" name="musicId" value="${resource.musicId}">
                            <input class="userId" value="" name="userId" type="hidden">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">音频名称：</label>
                                <div class="col-sm-8">
                                    <input name="musicName" class="form-control" type="text"
                                           value="${resource.musicName}" placeholder="请输入音频名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否默认音频版本：</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="defaultVersion"
                                               value="0" ${resource.defaultVersion=='0'?'checked':''}> 不是
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="defaultVersion"
                                               value="1" ${resource.defaultVersion=='1'?'checked':''}> 是
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">音频是否需要转：</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="mp3ToPcm"
                                               value="0" ${resource.mp3ToPcm=='0'?'checked':''}> 不需要转
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="mp3ToPcm"
                                               value="1" ${resource.mp3ToPcm=='1'?'checked':''}> 需要转pcm
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">版本号：</label>
                                <div class="col-sm-8">
                                    <input name="versionId" class="form-control" type="text"
                                           value="${resource.versionId}" placeholder="请输入音频版本号">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span
                                        class="oas-impInfo"></span>android版音频文件：</label>

                                <div class="col-sm-6">
                                    <input name="musicFileAndroid" class="form-control" type="text"
                                           value="${resource.musicFileAndroid}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" name=""
                                               class="itemPic oas-filebutton-default addmusicfileA uploadFile">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频文件：</label>
                                <div class="col-sm-6">
                                    <input name="musicFile" class="form-control" type="text"
                                           value="${resource.musicFile}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="uploadFile itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频字幕：</label>
                                <div class="col-sm-6">
                                    <input name="musicSubtitle" class="form-control" type="text"
                                           value="${resource.musicSubtitle}" readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="uploadFile itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">起腔结束时间：</label>
                                <div class="col-sm-8">
                                    <input name="endTime" class="form-control"
                                           onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"
                                           type="text" value="${resource.endTime}" placeholder="请输入起腔结束时间：number">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">开始时间：</label>
                                <div class="col-sm-8">
                                    <input name="startTime" class="form-control"
                                           onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"
                                           type="text" value="${resource.startTime}" placeholder="请输入开始时间：number">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">初始计数数量：</label>
                                <div class="col-sm-8">
                                    <input name="startNum" class="form-control"
                                           onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"
                                           type="text" value="${resource.startNum}" placeholder="请输入初始计数数量：number">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">步 长：</label>
                                <div class="col-sm-8">
                                    <input name="step" class="form-control"
                                           onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"
                                           type="text" value="${resource.step}" placeholder="请输入步 长：：number">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">步长数值：</label>
                                <div class="col-sm-8">
                                    <input name="stepNum" class="form-control"
                                           onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"
                                           type="text" value="${resource.stepNum}" placeholder="请输入步长数值：：number">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">单曲循环切入时间：</label>
                                <div class="col-sm-8">
                                    <input name="settingTime" class="form-control"
                                           onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"
                                           type="text" value="${resource.settingTime}" placeholder="请输入单曲循环切入时间：number">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频图片：</label>
                                <div class="col-sm-6">
                                    <input name="musicPic" class="form-control" type="text" value="${resource.musicPic}"
                                           readonly>
                                </div>
                                <div class="col-sm-3">
                                    <label class="col-sm-3 control-label">
                                        <input type="file" class="uploadFile itemPic oas-filebutton-default">
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary upSaveMusic" id="btn2" type="button">保存</button>&nbsp;&nbsp;
                                    <button class="btn btn-primary removeMusic" type="button">删除</button>
                                </div>
                            </div>
                            <input type="hidden" name="typeId" value="10000">
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</div>


<%--音频添加--%>
<script id="musicTpl" type="text/html">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>音频添加</h5>
        </div>
        <div class="ibox-content">
            <form class="form-horizontal m-t" method="post" action="">
                <input class="itemContentId" type="hidden" name="itemContentId" value="">
                <input class="itemId" type="hidden" name="itemId" value="">
                <input class="userId" value="" name="userId" type="hidden">
                <div class="form-group">
                    <label class="col-sm-3 control-label">音频名称：</label>
                    <div class="col-sm-8">
                        <input id="musicName" name="musicName" class="form-control" type="text"
                               value="${resource.musicName}" placeholder="请输入音频名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">是否默认音频版本：</label>
                    <div>
                        <label class="radio-inline">
                            <input type="radio" name="defaultVersion" value="0" checked> 不是
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="defaultVersion" value="1"> 是
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">音频是否需要转：</label>
                    <div>
                        <label class="radio-inline">
                            <input type="radio" name="mp3ToPcm" value="0" checked> 不需要转
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="mp3ToPcm" value="1"> 需要转pcm
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">版本号：</label>
                    <div class="col-sm-8">
                        <input id="" name="versionId" class="form-control" type="text" value="${resource.versionId}"
                               placeholder="请输入音频版本号">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>android版音频文件：</label>

                    <div class="col-sm-6">
                        <input name="musicFileAndroid" class="form-control" type="text"
                               value="${resource.musicFileAndroid}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" name="" class="itemPic oas-filebutton-default addmusicfileA uploadFile">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频文件：</label>
                    <div class="col-sm-6">
                        <input name="musicFile" class="form-control" type="text" value="${resource.musicFile}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频字幕：</label>
                    <div class="col-sm-6">
                        <input name="musicSubtitle" class="form-control" type="text" value="${resource.musicSubtitle}"
                               readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">起腔结束时间：</label>
                    <div class="col-sm-8">
                        <input id="endTime" name="endTime" class="form-control"
                               onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text"
                               value="${resource.endTime}" placeholder="请输入起腔结束时间：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">开始时间：</label>
                    <div class="col-sm-8">
                        <input id="startTime" name="startTime" class="form-control"
                               onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text"
                               value="${resource.startTime}" placeholder="请输入开始时间：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">初始计数数量：</label>
                    <div class="col-sm-8">
                        <input id="startNum" name="startNum" class="form-control"
                               onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text"
                               value="${resource.startNum}" placeholder="请输入初始计数数量：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">步 长：</label>
                    <div class="col-sm-8">
                        <input id="step" name="step" class="form-control"
                               onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text"
                               value="${resource.step}" placeholder="请输入步 长：：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">步长数值：</label>
                    <div class="col-sm-8">
                        <input id="stepNum" name="stepNum" class="form-control"
                               onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text"
                               value="${resource.stepNum}" placeholder="请输入步 长：：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">单曲循环切入时间：</label>
                    <div class="col-sm-8">
                        <input id="settingTime" name="settingTime" class="form-control"
                               onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text"
                               value="${resource.settingTime}" placeholder="请输入单曲循环切入时间：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频图片：</label>
                    <div class="col-sm-6">
                        <input name="musicPic" class="form-control" type="text" value="${resource.musicPic}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-8 col-sm-offset-3">
                        <button class="btn btn-primary saveMusic" type="button">保存</button>&nbsp;&nbsp;
                        <%--<button class="btn btn-primary addMusic" type="button">添加音频</button>--%>
                    </div>
                </div>
                <input type="hidden" name="typeId" value="10000">
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
<script src="${pageContext.request.contextPath}/view/static/assets/js/content.js?v=1.0.0"></script>

<!-- jQuery Validation plugin javascript-->
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/validate/messages_zh.min.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/laydate/laydate.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        var itemId = "";
        var itemContentId = "";
//       更新内容
        $(document).on("click", ".saveContent", function () {
            var userId = window.parent.document.getElementById("userId").defaultValue;
            $(".userId").val(userId)
            var _this = $(this);
            var con = $(this).closest(".ibox.float-e-margins").find(".form-horizontal");
            var form = new FormData(con[0]);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time: 0
            });
            $.ajax({
                type: "POST",
                processData: false,
                contentType: false,
                url: "${pageContext.request.contextPath}/admin/updateContentLesson",
                data: form,
                success: function (msg) {
                    if (msg.code == 1) {
                        parent.layer.msg('编辑内容失败，请联系管理员。。。');
                        return;
                    } else {
                        _this.attr('disabled', true);
                        layer.msg('操作成功。。。');
                        layer.close();
                    }
                }
            });
        });
//        更新音频
        $(document).on("click", ".upSaveMusic", function () {
            var userId = window.parent.document.getElementById("userId").defaultValue;
            $(".userId").val(userId)
            var _this = $(this);
            var con = $(this).closest(".ibox.float-e-margins").find(".form-horizontal");
            var form = new FormData(con[0]);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time: 0
            });
            $.ajax({
                type: "POST",
                processData: false,
                contentType: false,
                url: "${pageContext.request.contextPath}/admin/updateResourceMusicLesson",
                data: form,
                success: function (msg) {
                    if (msg.code == 1) {
                        parent.layer.msg('编辑内容失败，请联系管理员。。。');
                        return;
                    } else {
                        _this.attr('disabled', true);
                        layer.msg('操作成功。。。');
                        layer.close();
                    }
                }
            });
        });
//        删除音频
        $(document).on("click", ".removeMusic", function () {
            var userId = window.parent.document.getElementById("userId").defaultValue;
//            $(".userId").val(userId);
            var id = $(this).closest(".form-horizontal").find(".musicId").val();
            var ibox = $(this).closest(".ibox.float-e-margins");
            console.log(id);
            layer.confirm('确定删除吗?', {icon: 3, title: '提示'}, function (index) {
                $.ajax({
                    type: "GET",
                    dataType: "json",
                    contentType: "application/json",
                    <%--url: "${pageContext.request.contextPath}/admin/deleteResourceMusicLesson",--%>
                    url:"${pageContext.request.contextPath}/admin/updateIsExitByMusicId",
                    data: {"id": id,"userId":userId},
                    success: function (msg) {
                        console.log(msg);
                        layer.msg(msg.message, {time: 2000}, function () {
                            layer.close(index);
                            if (msg.code == '0') {
                                ibox.remove();
                            }
                        });
                    }
                });
            });
        });
//       添加音频
        $(document).on("click", ".addContent", function () {
            console.log("点击添加音频");
            itemContentId = $("#itemContentId").val();
            itemId = $("#itemId").val();
            //复制内容模版模块
            var html = template('musicTpl');
            $(".row").parent().append(html);
            $(".itemId:last").val(itemId);
            $(".itemContentId:last").val(itemContentId);
        });

//      保存新添加的音频
        $(document).on("click", ".saveMusic", function () {
            var _this = $(this);
            //用户ID
            var userId = window.parent.document.getElementById("userId").defaultValue;
            $(".userId").val(userId);
//            $(".itemContentId").val($("#itemContentId").val());
            var con = $(this).closest(".form-horizontal");
            var form = new FormData(con[0]);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time: 0
            });
            $.ajax({
                type: "POST",
                processData: false,
                contentType: false,
                url: "${pageContext.request.contextPath}/admin/insertResourceMusicLesson",
                data: form,
                success: function (msg) {
                    console.log(msg);
                    if (msg.code == 1) {
                        parent.layer.msg('请先添加功课和功课内容哦。。。');
                        return;
                    }
                    _this.attr('disabled', true);

                    layer.msg(msg.message, {time: 2000}, function () {
                        layer.close();
                    });
                }
            });

        });

//      文件上传
        $(document).on("change", ".uploadFile", function () {

            var _this = $(this);
            var oMyForm = new FormData();
            if (!!!_this[0].files[0])
                return;
            oMyForm.append("file", _this[0].files[0]);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time: 0
            });

            $.ajax({
                type: "POST",
                processData: false,
                contentType: false,
                url: "${pageContext.request.contextPath}/admin/uploadFile",
                data: oMyForm,
                success: function (msg) {
                    if (msg == '') {
                        parent.layer.msg('上传失败，请重新上传。。。');
                        return;
                    }
                    _this.closest(".form-group").find(".form-control").val(msg);

                    layer.msg('上传成功。。。', {time: 2000}, function () {

                        layer.close();
                    });
                }
            });

        });

    });

</script>

</body>

</html>
