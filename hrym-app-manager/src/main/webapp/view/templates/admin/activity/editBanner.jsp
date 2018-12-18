<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>活动列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">

<style>
    table{
        width:100px;
        table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
    }
    td{
        width:100%;
        word-break:keep-all;/* 不换行 */
        white-space:nowrap;/* 不换行 */
        overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
        /*text-overflow:ellipsis;!* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用*!*/
    }
    .table td:hover{
        overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
        overflow: scroll;   /* 滚动条*/
        white-space: nowrap;
        height: 10px;
    }
</style>
</head>

<body class="gray-bg">

    <!-- 按钮触发模态框 -->
    <!-- 模态框（Modal） -->
                <div class="wrapper wrapper-content animated fadeInRight">

                    <div class="ibox float-e-margins" id="itemContent">
                        <div class="ibox-title">
                            <h5>活动列表</h5>
                        </div>
                        <div class="ibox-content" >
                            <form class="form-horizontal m-t " id="bannerForm" method="post" action="" >
                                <input type="hidden" id="bannerId" name="bannerId" value="${list.bannerId}">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">开始时间：</label>
                                    <div class="col-sm-8">
                                        <input class="form-control layer-date"value="${list.startTimeis}" name="startTimeis" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                                        <label class="laydate-icon"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">结束时间：</label>
                                    <div class="col-sm-8">
                                        <input class="form-control layer-date" value="${list.endTimeis}" name="endTimeis" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                                        <label class="laydate-icon"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">活动标题：</label>
                                    <div class="col-sm-8">
                                        <input id="bannerTitle" name="bannerTitle" class="form-control" type="text" value="${list.bannerTitle}" >
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">活动描述：</label>
                                    <div class="col-sm-8">
                                        <input id="bannerDesc" name="bannerDesc" class="form-control" type="text" value="${list.bannerDesc}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">活动图片：</label>
                                    <div class="col-sm-6">
                                        <input name="bannerPic" class="form-control" type="text" value="${list.bannerPic}" readonly>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="col-sm-3 control-label">
                                            <input type="file" class="fileEdit" name="taskItemPic" class="itemPic oas-filebutton-default">
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">活动URL：</label>
                                    <div class="col-sm-8">
                                        <input id="bannerUrl" name="bannerUrl" class="form-control" type="text" value="${list.bannerUrl}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">自定义排序：</label>
                                    <div class="col-sm-8">
                                        <input id="sort" name="sort" class="form-control" type="text" value="${list.sort}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                <label class="col-sm-3 control-label">分享URL：</label>
                                <div class="col-sm-8">
                                    <input id="shareUrl" name="shareUrl" class="form-control" type="text" value="${list.shareUrl}" >
                                </div>
                            </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">是否需要登录：</label>
                                    <div>
                                        <label class="radio-inline">
                                            <input type="radio" name="needLogin" value="1" ${list.needLogin=='1'?'checked':''}> YES
                                        </label>
                                        <label class="radio-inline">
                                            &nbsp;&nbsp;&nbsp;<input type="radio" name="needLogin" value="0" ${list.needLogin=='0'?'checked':''}> NO
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">需要分享：</label>
                                    <div>
                                        <label class="radio-inline">
                                            <input type="radio" name="needShare"  value="1" ${list.needShare=='1'?'checked':''}> YES
                                        </label>
                                        <label class="radio-inline">
                                            &nbsp;&nbsp;&nbsp;<input type="radio" name="needShare" value="0" ${list.needShare=='0'?'checked':''} > NO
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">需要分享结果：</label>
                                    <div>
                                        <label class="radio-inline">
                                            <input type="radio" name="needResult"  value="1" ${list.needResult=='1'?'checked':''}> YES
                                        </label>
                                        <label class="radio-inline">
                                            &nbsp;&nbsp;&nbsp;<input type="radio" name="needResult" value="0" ${list.needResult=='0'?'checked':''} > NO
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">附加参数：</label>
                                    <div class="col-sm-8">
                                        <input id="additionalParam" name="additionalParam" class="form-control" type="text" value="${list.additionalParam}" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">资源类型：</label>
                                    <div>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" name="shareChannelA" value="1" ${list.shareChannelA=='1'?'checked':''}> 微信朋友圈
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" name="shareChannelB" value="1" ${list.shareChannelB=='1'?'checked':''}> 微信好友
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" name="shareChannelC" value="1" ${list.shareChannelC=='1'?'checked':''}> QQ空间
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" name="shareChannelD" value="1" ${list.shareChannelD=='1'?'checked':''}> QQ好友
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" name="shareChannelE" value="1" ${list.shareChannelE=='1'?'checked':''}> 微博
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">活动用途：</label>
                                    <div class="col-sm-8">
                                        <input id="bannerType" name="bannerType" class="form-control" type="text" value="${list.bannerType}" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}" >
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button  class="btn btn-primary updateAllBanner" id="btn1" type="button">保存&nbsp;&nbsp;
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>


    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/bootstrap.min.js?v=3.3.6"></script>

	<!-- Bootstrap table -->
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

    <!-- Peity -->
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/peity/jquery.peity.min.js"></script>

    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/view/static/assets/js/content.js?v=1.0.0"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/laydate/laydate.js"></script>


    <!-- Page-Level Scripts -->
    <script type="text/javascript">

       //活动更新保存
            $(document).on("click",".updateAllBanner",function(){
                var form = new FormData($(bannerForm)[0]);
                layer.msg('正在提交中，请稍等。。。', {
                    icon: 16,
                    shade: 0.01,
                    time:0
                });
                $.ajax({
                    type: "POST",
                    processData:false,
                    contentType:false,
                    url: "${pageContext.request.contextPath}/admin/updateAllBanner",
                    data:form,
                    success: function(msg){
                        debugger
                        if (msg.code==0){
                            document.getElementById("btn1").disabled=true;
                           layer.msg('操作成功。。。');
                            return;
                        } else {
                            layer.msg('编辑内容失败，请联系管理员。。。');
                        }
                        layer.close();
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

    </script>


</body>

</html>
