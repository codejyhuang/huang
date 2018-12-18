<%@ page contentType="text/html; charset=utf-8" %>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/easyui/easyui.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/easyui/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/easyui/jquery.easyui.min.js"></script>

    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12 total">
            <%--功课添加--%>
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>功课添加</h5>
                    <tr>&emsp;<a id="callBack"
                                 href="${pageContext.request.contextPath}/view/templates/admin/itemLesson/index.jsp">返回</a>
                </div>
                <div class="ibox-content">

                    <input id="itemContentId" type="hidden" name="itemContentId" value="">
                    <input id="itemId1" type="hidden" name="itemId" value="">

                    <form class="form-horizontal m-t" id="item">
                        <input class="userId" value="" name="userId" type="hidden">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">功课名称：<b style="color: red">*</b></label>
                            <div class="col-sm-8">
                                <input id="itemName" name="itemName" class="form-control" type="text"
                                       value="${resource.itemName}" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">功课别名：<b style="color: red">*</b></label>
                            <div class="col-sm-8">
                                <input id="aliasName" name="aliasName" class="form-control" type="text"
                                       value="${resource.aliasName}" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">功课描述：<b style="color: red">*</b></label>
                            <div class="col-sm-8">
                                <input id="titleDesc" name="titleDesc" class="form-control" type="text"
                                       value="${resource.titleDesc}" placeholder="">
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<label class="col-sm-3 control-label">是否可供：<b style="color: red">*</b></label>--%>
                            <%--<div>--%>
                                <%--<label class="radio-inline" style="margin-left: 17px">--%>
                                    <%--<input type="radio" name="isSupport" id="isSupport1" value="1"> 可供--%>
                                <%--</label>--%>
                                <%--<label class="radio-inline">--%>
                                    <%--<input type="radio" name="isSupport" id="isSupport2" value="0" checked> 不可供--%>
                                <%--</label>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">类型：</label>
                            <div class="label-bq1">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">法门：</label>
                            <div class="label-bq2">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">功德：</label>
                            <div class="label-bq3">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>功课简介：<b
                                    style="color: red">*</b></label>
                            <div class="col-sm-8">
                                <textarea name="itemIntro" placeholder="请填写功课简介的信息……"
                                          style="width: 500px;height: 100px"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>功德利益简介：<b
                                    style="color: red">*</b></label>
                            <div class="col-sm-8">
                                <textarea name="meritBenefitIntro" placeholder="请填写功德利益简介的信息……"
                                          style="width: 500px;height: 100px"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>做功课背景图片：<b
                                    style="color: red">*</b></label>
                            <div class="col-sm-6">
                                <input name="backgroundPic" class="form-control" type="text"
                                       value="${resource.backgroundPic}" readonly>
                            </div>
                            <div class="col-sm-3">
                                <label class="col-sm-3 control-label">
                                    <input type="file" class="uploadFile itemPic oas-filebutton-default">
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>功课计划背景图片：<b
                                    style="color: red">*</b></label>
                            <div class="col-sm-6">
                                <input name="bgBannerPic" class="form-control" type="text"
                                       value="${resource.bgBannerPic}" readonly>
                            </div>
                            <div class="col-sm-3">
                                <label class="col-sm-3 control-label">
                                    <input type="file" class="uploadFile itemPic oas-filebutton-default">
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>功课列表图片：<b
                                    style="color: red">*</b></label>
                            <div class="col-sm-6">
                                <input name="itemPic" class="form-control" type="text" value="${resource.itemPic}"
                                       readonly>
                            </div>
                            <div class="col-sm-3">
                                <label class="col-sm-3 control-label">
                                    <input type="file" class="uploadFile itemPic oas-filebutton-default">
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>功德利益图片：<b
                                    style="color: red">*</b></label>
                            <div class="col-sm-6">
                                <input name="meritBenefitPic" class="form-control" type="text"
                                       value="${resource.meritBenefitPic}" readonly>
                            </div>
                            <div class="col-sm-3">
                                <label class="col-sm-3 control-label">
                                    <input type="file" class="uploadFile itemPic oas-filebutton-default">
                                </label>
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
<%--模版--%>
<script id="contentTpl" type="text/html">
    <%--内容添加--%>
    <div class="ibox float-e-margins" id="itemContent">
        <div class="ibox-title">
            <h5>内容添加</h5>
        </div>
        <div class="ibox-content">
            <form class="form-horizontal m-t" id="content" method="post" action="">
                <%--//用户ID--%>
                <input class="userId" value="" name="userId" type="hidden">
                <input type="hidden" id="itemId" name="itemId" class="itemId" value="">
                <div class="form-group">
                    <label class="col-sm-3 control-label">版本名称：<b style="color: red">*</b></label>
                    <div class="col-sm-8">
                        <input id="versionName" name="versionName" class="form-control" type="text"
                               value="${resource.versionName}" placeholder="如:释迦牟尼佛圣号（藏）">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">纯文字：</label>
                    <div class="col-sm-8">
                        <input id="text" name="text" class="form-control" type="text" value="${resource.text}"
                               placeholder="请输入纯文字内容">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>图片内容：<b
                            style="color: red">*</b></label>
                    <div class="col-sm-6">
                        <input name="contentPic" class="form-control" type="text" value="${resource.contentPic}"
                               readonly>
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
                <%--<input type="radio" name="origin" id="origin" value="佛教文库" checked> 佛教文库--%>
                <%--</label>--%>
                <%--<label class="radio-inline">--%>
                <%--<input type="radio" name="origin" value="佛教典籍"> 佛教典籍--%>
                <%--</label>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                <%--<label class="col-sm-3 control-label">资源版本：<b style="color: red">*</b></label>--%>
                <%--<div>--%>
                <%--<label class="radio-inline">--%>
                <%--<input type="radio" name="version" id="version" value="0" checked> 藏版--%>
                <%--</label>--%>
                <%--<label class="radio-inline">--%>
                <%--<input type="radio" name="version" value="1"> 汉版--%>
                <%--</label>--%>
                <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <label class="col-sm-3 control-label">视频版本号：<b style="color: red">*</b></label>
                    <div class="col-sm-8">
                        <input id="contentVersion" name="contentVersion" class="form-control" type="text"
                               value="${resource.contentVersion}" placeholder="如:供佛（藏）">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>视频文件：</label>

                    <div class="col-sm-6">
                        <input name="videoFile" class="form-control" type="text" value="${resource.videoFile}" readonly>
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
                            <input type="radio" name="voiceCount" id="voiceCount1" value="1"> 有
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="voiceCount" id="voiceCount2" value="0" checked> 没有
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">android语音识别名称：</label>
                    <div class="col-sm-8">
                        <input id="voiceName" name="voiceName" class="form-control" type="text"
                               value="${resource.voiceName}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>语音识别字典文件：</label>

                    <div class="col-sm-6">
                        <input name="voiceDic" class="form-control" type="text" value="${resource.voiceDic}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>android语音识别文件：</label>

                    <div class="col-sm-6">
                        <input name="voiceLm" class="form-control" type="text" value="${resource.voiceLm}" readonly>
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
                            <input type="radio" name="speedCount" id="speedCount1" value="1"> 有
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="speedCount" id="speedCount2" value="0" checked> 没有
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>ios语速计数内容：</label>
                    <div class="col-sm-8">
                        <input name="voiceText" class="form-control" type="text" value="${resource.voiceText}"
                               placeholder="请输入ios语速技术字段">
                    </div>

                </div>
                <div class="form-group">
                    <div class="col-sm-8 col-sm-offset-3">
                        <button class="btn btn-primary saveContent" id="btn1" type="button">保存</button>&nbsp;&nbsp;
                        <button class="btn btn-primary addContent" type="button">添加内容</button>&nbsp;&nbsp;
                    </div>
                </div>
                <input type="hidden" name="typeId" value="10000">
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

        <div class="ibox-content">
            <form class="form-horizontal m-t" method="post" action="">
                <input class="itemId" type="hidden" name="itemId" value="">
                <input class="itemContentId" type="hidden" name="itemContentId" value="">
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
                               value="${resource.step}" placeholder="请输入步 长：：number">
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
                        <button class="btn btn-primary saveMusic" id="btn2" type="button">保存</button>&nbsp;&nbsp;
                        <button class="btn btn-primary addMusic" type="button">添加音频</button>
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
        $.ajax({
            type: "post",
            processData: false,
            contentType: false,
            url: "${pageContext.request.contextPath}/admin/findTagsList",
            data: "",
            success: function (data) {
                console.log(data);
                for (var i = 0; i < data.rows.length; i++) {
                    if (data.rows[i].tagType == 0) {  //流派
                        var str = "<label class='radio-inline'><input type='checkbox' name='tags3' value=" + data.rows[i].tagId + ">" + data.rows[i].tagName + "</label>"
                        $(".label-bq1").append(str);
                    }
                    if (data.rows[i].tagType == 1) {  //类别
                        var str = "<label class='radio-inline'><input type='checkbox' name='tags1' value=" + data.rows[i].tagId + ">" + data.rows[i].tagName + "</label>"
                        $(".label-bq2").append(str);
                    }
                    if (data.rows[i].tagType == 2) {  // 功效
                        var str = "<label class='radio-inline'><input type='checkbox' name='tags2' value=" + data.rows[i].tagId + ">" + data.rows[i].tagName + "</label>"
                        $(".label-bq3").append(str);
                    }

                }
//                $(".label-bq1").append("<label class='radio-inline'><input type='radio' name='tags3' value='-1' checked>不选择</label>");
//                $(".label-bq2").append("<label class='radio-inline'><input type='radio' name='tags1' value='-1' checked>不选择</label>");
//                $(".label-bq3").append("<label class='radio-inline'><input type='radio' name='tags2' value='-1' checked>不选择</label>");
//                $(".label-bq1").children(".radio-inline").eq(-1).children().attr("checked","checked");
//                $(".label-bq2").children(".radio-inline").eq(-1).children().attr("checked","checked");
//                $(".label-bq3").children(".radio-inline").eq(-1).children().attr("checked","checked");
            }
        });

        <%--//获取地址栏的信息--%>
        <%--function GetQueryString(name){--%>
        <%--var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");--%>
        <%--var r = window.location.search.substr(1).match(reg);--%>
        <%--if(r!=null)return  unescape(r[2]); return null;--%>
        <%--}--%>

        <%--var userId = window.parent.document.getElementById("userId").defaultValue;--%>
        <%--$(".userId").val(userId);--%>
        <%--var typeId = GetQueryString('typeId');--%>
        <%--$("#typeId").val(typeId);--%>
        <%--document.getElementById("callBack").href='${pageContext.request.contextPath}/view/templates/admin/newResource/index.jsp?typeId='+typeId;--%>

        var itemID = '';

        function addItemId() {
            var itemId_arr = document.querySelectorAll('.itemId');
            for (var i = 0; i < itemId_arr.length; i++) {
                itemId_arr[i].value = itemID;
                console.log("执行了");
            }
        };
        var itemContentId = "";

        function additemContentId() {
            var itemContentId_arr = document.querySelectorAll('.itemContentId');
            for (var i = 0; i < itemContentId_arr.length; i++) {
                itemContentId_arr[i].value = itemContentId;
            }
        }

//        保存功课
        $(document).on("click", ".saveItem", function () {
            var typeId = document.getElementById("typeId").value;
            var userId = window.parent.document.getElementById("userId").defaultValue;
            $(".userId").val(userId);
            var form = new FormData($(item)[0]);
            console.log(form);
            layer.msg('正在提交中，请稍等。。。', {
                icon: 16,
                shade: 0.01,
                time: 0
            });
            $.ajax({
                type: "POST",
                processData: false,
                contentType: false,
                url: "${pageContext.request.contextPath}/admin/insertResourceItemLesson",
                data: form,
                success: function (msg) {
                    console.log(msg);
                    //复制内容模版模块
                    var html = template('contentTpl');
                    $(".row").parent().append(html);
                    itemID = msg.rows;
                    addItemId();
                    document.getElementById("btn").disabled = true;
                    //$("#itemId").val(msg.rows.itemId);
                    layer.msg(msg.message, {time: 2000}, function () {
                        layer.close();
                    });
                }
            });

        });

//        保存内容
        $(document).on("click", ".saveContent", function () {
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
                time: 0
            });
            $.ajax({
                type: "POST",
                processData: false,
                contentType: false,
                url: "${pageContext.request.contextPath}/admin/insertContentLesson?typeId=" + typeId,
                data: form,
                success: function (msg) {
                    console.log(msg);
                    //打开音频模版模块
                    var html = template('musicTpl');
                    $(".row").parent().append(html);
                    $(".itemContentId").val(msg.rows.itemContentId);
                    $(".itemId").val(msg.rows.itemId);
                    itemID = msg.rows.itemId;
                    addItemId();
                    itemContentId = msg.rows.itemContentId;
                    additemContentId();
                    _this.attr('disabled', "true");
                    layer.msg(msg.message, {time: 2000}, function () {
                        layer.close();
                    });
                }
            });

        });
//      保存音频
        $(document).on("click", ".saveMusic", function () {
            var _this = $(this);
            addItemId()
            additemContentId();
            //用户ID
            var userId = window.parent.document.getElementById("userId").defaultValue;
            $(".userId").val(userId);
//            console.log($("#itemContentId").val());
//            $(".itemContentId").val($("#itemContentId").val());
            var con = _this.closest(".form-horizontal");
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
                    _this.attr('disabled', "true");

                    layer.msg(msg.message, {time: 2000}, function () {
                        layer.close();
                    });
                }
            });

        });

        $(document).on("click", ".addContent", function () {
            console.log(itemID);
            //复制内容模版模块
            var html = template('contentTpl');
            $(".row").parent().append(html);
            addItemId();

        });
        $(document).on("click", ".addMusic", function () {
            //复制音频模版模块
            var html = template('musicTpl');
            $(".row").parent().append(html);
            addItemId();
            additemContentId();

        });
    });

    /**
     * 文件上传
     */
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
                layer.msg('上传成功。。。', {time: 2500}, function () {

                    layer.close();
                });
            }
        });

    });

</script>

</body>

</html>
