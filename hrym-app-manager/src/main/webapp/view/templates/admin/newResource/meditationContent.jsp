
<%@ page contentType="text/html; charset=utf-8"%>
<script id="meditationContentTpl" type="text/html">
    <div class="ibox float-e-margins" id="tag" >
        <div class="ibox-title">
            <h5>禅修内容添加</h5>
        </div>
        <div class="ibox-content" >
            <form class="form-horizontal m-t" id="content" method="post" action="" >
                <input type="hidden" id="itemId" class="itemId" name="itemId" value="">
                <input type="hidden" id="item_content_id" name="item_content_id" value="${resource.itemContentId}">
                <input type="hidden" id="content_id" name="contentId" value="${resource.contentId}">
                <input class="userId" name="userId" value="userId" type="hidden">
                <div class="form-group">
                    <label class="col-sm-3 control-label">版本名称:</label>
                    <div class="col-sm-8">
                        <input id="contentName" name="contentName" class="form-control" type="text" value="${resource.contentName}" placeholder="请输入版本名称 如：三板一钟止大静">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频文件：</label>
                    <div class="col-sm-6">
                        <input name="musicFile" class="form-control" type="text" value="${resource.musicFile}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile" name="addMusicFile" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>音频字幕：</label>
                    <div class="col-sm-6">
                        <input name="musicSubtitle" class="form-control" type="text" value="${resource.musicSubtitle}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile" name="addMusicSubtitle" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>结束音频文件：</label>
                    <div class="col-sm-6">
                        <input name="endMusicFile" class="form-control" type="text" value="${resource.endMusicFile}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile" name="addEndMusicFile" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>视频文件：</label>
                    <div class="col-sm-6">
                        <input name="videoFile" class="form-control" type="text" value="${resource.videoFile}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label class="col-sm-3 control-label">
                            <input type="file" class="uploadFile" name="addVideoFile" class="itemPic oas-filebutton-default">
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">音频时间编辑：</label>
                    <div>
                        <label class="radio-inline">
                            <input type="radio" name="isMusicEdit" value="0" ${resource.isMusicEdit=='0'?'checked':''} checked > 不可编辑
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="isMusicEdit" value="1" ${resource.isMusicEdit=='1'?'checked':''}> 可编辑
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">单曲循环切入时间：</label>
                    <div class="col-sm-8">
                        <input id="settingTime" name="settingTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.settingTime}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">固定音频时长：</label>
                    <div class="col-sm-8">
                        <input id="fixedMusicTime" name="fixedMusicTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.fixedMusicTime}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">固定视频时长：</label>
                    <div class="col-sm-8">
                        <input id="fixedVideoTime" name="fixedVideoTime" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.fixedVideoTime}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">版本号：</label>
                    <div class="col-sm-8">
                        <input id="versionId" name="versionId" class="form-control" type="text" value="${resource.versionId}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">止静时间开始：</label>
                    <div class="col-sm-8">
                        <input  name="voiceStart" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.voiceStart}" placeholder="请输入止静时间开始：number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">止静时间结束：</label>
                    <div class="col-sm-8">
                        <input  name="voiceDown" class="form-control" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" type="text" value="${resource.voiceDown}" placeholder="请输入止静时间结束：number">
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