
<%@ page contentType="text/html; charset=utf-8"%>
<script id="bookContentTpl" type="text/html">
    <div class="ibox float-e-margins" id="tag" >
        <div class="ibox-title">
            <h5>经书内容添加</h5>
        </div>
        <div class="ibox-content" >
            <form class="form-horizontal m-t" id="content" method="post" action="" >
                <input type="hidden" id="itemId" name="itemId" class="itemId" value="">
                <input type="hidden" id="item_content_id" name="itemContentId" value="${resource.itemContentId}">
                <input type="hidden" id="content_id" name="contentId" value="${resource.contentId}">
                <input class="userId" name="userId" value="userId" type="hidden">
                <div class="form-group">
                    <label class="col-sm-3 control-label">版本名称:</label>
                    <div class="col-sm-8">
                        <input id="versionName" name="versionName" class="form-control" type="text" value="${resource.versionName}" placeholder="请输入版本名称 如：磕大头">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">文字内容:</label>
                    <div class="col-sm-8">
                        <input id="text" name="text" class="form-control" type="text" value="${resource.text}" placeholder="请输入纯文字">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">年代：</label>
                    <div class="col-sm-8">
                        <input class="form-control layer-date" name="yearsStr" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                        <label class="laydate-icon"></label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>文档内容：</label>
                    <div class="col-sm-6">
                        <input name="fileTxt" class="form-control" type="text" value="${resource.fileTxt}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile"  class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>图片内容：</label>
                    <div class="col-sm-6">
                        <input name="filePic" class="form-control" type="text" value="${resource.filePic}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile"  class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">来 源：</label>
                    <div>
                        <label class="radio-inline">
                            <input type="radio" name="origin" id="origin" value="佛教文库" ${resource.origin=='佛教文库'?'checked':''} checked> 佛教文库
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="origin" id="origin1" value="佛教典籍" ${resource.origin=='佛教典籍'?'checked':''}> 佛教典籍
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">资源版本：</label>
                    <div>
                        <label class="radio-inline">
                            <input type="radio" name="version" id="version" value="0" ${resource.version=='0'?'checked':''}  checked> 藏版
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="version" value="1" ${resource.version=='1'?'checked':''}> 汉版
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">视频版本号：</label>
                    <div class="col-sm-8">
                        <input id="contentVersion" name="contentVersion" class="form-control" type="text" value="${resource.contentVersion}" placeholder="如:供佛（藏）">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>视频文件：</label>

                    <div class="col-sm-6">
                        <input name="videoFile" class="form-control" type="text" value="${music.videoFile}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile" name="addvideoFile" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">出 处：</label>
                    <div class="col-sm-8">
                        <input id="source" name="source" class="form-control" type="text" value="${resource.source}" placeholder="请输入出处">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">作 者：</label>
                    <div class="col-sm-8">
                        <input id="author" name="author" class="form-control" type="text" value="${resource.author}" placeholder="请输入作者">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">译 者：</label>
                    <div class="col-sm-8">
                        <input id="translator" name="translator" class="form-control" type="text" value="${resource.translator}"placeholder="请输入译者">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">有无语音识别：</label>
                    <div>
                        <label class="radio-inline">
                            <input type="radio" name="voiceCount" id="voiceCount" value="1" ${resource.voiceCount=='1'?'checked':''}> 有
                        </label>
                        <label class="radio-inline">
                            &nbsp;&nbsp;&nbsp;<input type="radio" name="voiceCount" id="voiceCount1" value="2" ${resource.voiceCount=='2'?'checked':''} checked> 没有
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
                        <input name="voiceDic" class="form-control" type="text" value="${resource.voiceDic}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile" name="taskItemPic" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>语音识别模型文件：</label>
                    <div class="col-sm-6">
                        <input name="voiceLm" class="form-control" type="text" value="${resource.voiceLm}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile" name="taskItemPic" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-8 col-sm-offset-3">
                        <button type="button" class="btn btn-default btn3" id="btn3" data-dismiss="modal">关闭</button>&nbsp;&nbsp;
                        <button  class="btn btn-primary saveContent" id="btn1" type="button">保存</button>&nbsp;&nbsp;
                        <button class="btn btn-primary addContent" id="btn2" type="button" >添加内容</button>&nbsp;&nbsp;
                    </div>
                </div>
            </form>
        </div>
    </div>
</script>