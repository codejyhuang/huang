<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/plugins/zTree/zTreeStyle/zTreeStyle.css" rel="stylesheet">

</head>
<!-- 按钮触发模态框 -->
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    添加文章内容
                </h4>
            </div>
            <div class="row form-group" style="margin-left: 100px">
                <div class="col-sm-8">
                    <div class="tabs-container"><br><br>
                        <ul class="nav nav-tabs" style="width: 420px">
                            <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true" name="contentType">正文</a>
                            </li>
                            <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">引用</a>
                            </li>
                            <li class=""><a data-toggle="tab" href="#tab-3" aria-expanded="false">图片</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active"><br>
                                <form class="form-horizontal m-t" id="ArticleContent" method="post" action="" enctype="multipart/form-data" style="float:left;">
                                    <input type="hidden"name="contentType"id="contentType1" value="0"/>
                                    <input type="hidden"name="articleId"class="idtArticleA" value="${artilelist.idtArticle}"/>
                                    <input  type="hidden" class="idtArticleContent" name="idtArticleContent" value="">
                                    <textarea name=" contentValue"cols="80" rows="15" style="height: 220px;width: 400px"></textarea>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="button" style="margin-bottom: auto;margin-right: 120px" onclick="saveArticleContent();">保存正文</button>&nbsp;&nbsp;
                                        </div>
                                    </div>
                                </form>

                            </div>
                            <div id="tab-2" class="tab-pane "><br>
                                <form class="form-horizontal m-t" id="ArticleContent1" method="post" action="" enctype="multipart/form-data" style="float:left;">
                                    <input type="hidden"name="contentType"id="contentType2" value="1"/>
                                    <input type="hidden"name="articleId"class="idtArticleA" value="${artilelist.idtArticle}"/>
                                    <input  type="hidden" class="idtArticleContent" name="idtArticleContent" value="">
                                    <textarea name=" contentValue"  cols="40" rows="15" style="height: 220px;width: 400px"></textarea>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="button"style="margin-bottom: auto;margin-right: 120px" onclick="saveArticleContent1();">保存引用</button>&nbsp;&nbsp;
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div id="tab-3" class="tab-pane"><br>
                                <form class="form-horizontal m-t" id="ArticleContent2" method="post" action="" enctype="multipart/form-data" style="float:left;">
                                    <input type="hidden"name="contentType"id="contentType3" value="2"/>
                                    <input type="hidden"name="articleId"class="idtArticleA" value="${artilelist.idtArticle}"/>
                                    <input  type="hidden" class="idtArticleContent" name="idtArticleContent" value="">                            <div style="width: 400px;height: 200px;">
                                    <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 600px; max-height: 400px; line-height: 10px;">
                                        <img id="picImg" style="width: 100%;height: auto;max-height: 140px;" src="http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg">
                                    </div>
                                    <textarea name=" contentValue"  cols="40" rows="15" style="height: 80px;width: 400px"></textarea>
                                </div><br><br><br><br>
                                    <div class="col-sm-6">
                                        <input name="pic" class="form-control" type="hidden"  readonly>
                                    </div>
                                    <div class="col-sm-8">
                                        <label class="col-sm-3 control-label">
                                            <input type="file" class="addFile"  class="itemPic oas-filebutton-default">
                                        </label>
                                    </div><br><br><br>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="button"style="margin-bottom: auto;margin-right: 120px" onclick="saveArticleContent2();">保存图片</button>&nbsp;&nbsp;
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--- 折叠--->
            <div class="col-md-6 column" id="div" style="margin-top: 10px;display:none">
                <div class="info">
                    <span render-html="content"></span>
                    <%--<ul render-loop="seven_days">--%>
                    <%--<li>--%>
                    <%--<span render-fun="get_content" render-key="seven_days.weather"></span>--%>
                    <ul render-loop="contentList">
                        <%--<li render-html="test.haha"></li>--%>
                        <li>
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion"
                                           href=".collapse">
                                            <span render-fun="get_content" render-key="contentList.title"></span>
                                        </a>
                                    </h4>
                                </div>
                                <div class="panel-collapse collapse">

                                    <div class="panel-body">
                                        <span render-html="contentList.contentValue"></span>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <%--</li>--%>
                    <%--</ul>--%>
                </div>
            </div>
            <div class="col-md-6 column" id="div1" style="margin-top: 10px;display:none">
                <div class="info1">
                    <span render-html="content"></span>
                    <%--<ul render-loop="seven_days">--%>
                    <%--<li>--%>
                    <%--<span render-fun="get_content" render-key="seven_days.weather"></span>--%>
                    <ul render-loop="contentList">
                        <%--<li render-html="test.haha"></li>--%>
                        <li>
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion"
                                           href=".collapse">
                                            <span render-fun="get_content" render-key="contentList.title"></span>
                                        </a>
                                    </h4>
                                </div>
                                <div class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <span render-html="contentList.contentValue"></span>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <%--</li>--%>
                    <%--</ul>--%>
                </div>
            </div>
            <div class="col-md-6 column" id="div2" style="margin-top: 10px;display:none">
                <%--图片加文字--%>
                <div class="info2">
                    <span render-html="content"></span>
                    <ul render-loop="contentList">
                        <li>
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion"
                                           href=".collapse">
                                            <span render-fun="get_content" render-key="contentList.title"></span>
                                        </a>
                                    </h4>
                                </div>
                                <div class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <img style="width: 100%;height: auto;max-height: 140px;" id="Img" src="http://202.104.112.185:8089/group1/M00/00/04/wKgAHFm3hcyAJq4pAAC7WM3IeXI950.jpg">
                                        <span render-html="contentList.contentValue"></span>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <%--</li>--%>
                    <%--</ul>--%>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--- 模态框结束--->

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>文章发表</h5><a href="${pageContext.request.contextPath}/view/templates/admin/association/Aindex.jsp">返回</a>
                        </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="ArticleForm" method="post" action="" enctype="multipart/form-data">
                                        <input type="hidden" name="idtArticle" id="idtArticle" value="${artilelist.idtArticle}"/>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">文章名称：</label>
                                        <div class="col-sm-8">
                                            <input id="articleTitle" name="articleTitle" class="form-control" type="text" style="width: 300px" value="${artilelist.articleTitle}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">专栏名称：</label>
                                        <div class="col-sm-8">
                                            <select class="input-group col-md-3" id="columnName" name="specialColumnId" style="width: 250px;height: 30px">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">作者：</label>
                                        <div class="col-sm-8">
                                            <input id="author" name="author" class="form-control" type="text" style="width: 300px" value="${author.author}">
                                        </div>
                                    </div><br>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>背景图片：</label>

                                        <div class="col-sm-6">
                                            <input name="articlePic"id="Pic" class="form-control" type="text" value="${artilelist.articlePic}" readonly>
                                        </div>
                                        <div class="col-sm-3">
                                               <label class="col-sm-3 control-label">
                                                   <input type="file" class="fileEdit" name="Pic" class="itemPic oas-filebutton-default" value="${artilelist.articlePic}">
                                               </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary updateAllArticle"id="btn" type="button"style="margin-bottom: auto;margin-right: 120px" >文章编辑</button>&nbsp;&nbsp;
                                            <button class="btn btn-primary"id="btn1" data-toggle="modal" data-target="#myModal" type="button"style="margin-bottom: auto;margin-right: 120px" >添加内容</button>&nbsp;&nbsp;
                                        </div>
                                    </div>
                                        <br>
                                </form>
                            </div>
                                <div class="form-horizontal m-t">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"></label>
                                        <div class="col-sm-8">
                                            <ul class="nav nav-tabs" style="width: 420px">
                                                <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true" name="contentType">修改内容</a>
                                                </li>
                                            </ul>
                                            <div id="myTabContent" class="tab-content">
                                                <c:forEach var="list" items="${artilelist.articleContentList}" varStatus="vs">
                                                    <c:if test="${list.contentType==0}">
                                                        <div class="tab-pane active"><br>
                                                            <div class="panel-group" id="">
                                                                <div class="panel panel-default">
                                                                    <div class="panel-heading">
                                                                        <h4 class="panel-title">
                                                                            <a data-toggle="collapse" data-parent="#accordion"
                                                                               href="#${vs.index}">
                                                                                    ${list.title}
                                                                            </a>
                                                                        </h4>
                                                                    </div>
                                                                    <div id="${vs.index}" class="panel-collapse collapse">
                                                                        <div class="panel-body">
                                                                            <form class="form-horizontal m-t ArticleContenta"  method="post" action="" enctype="multipart/form-data" style="float:left;">
                                                                                <input name="idtArticleContent" type="hidden" value="${list.idtArticleContent}"/>
                                                                                <input name="contentType"type="hidden" value="${list.contentType}"/>
                                                                                <input type="hidden"name="articleId" value="${list.articleId}"/>
                                                                                <textarea name=" contentValue" cols="80" placeholder="${list.contentValue}"  rows="15" style="height:300px;width: 700px">${list.contentValue}</textarea>
                                                                                <div class="form-group">
                                                                                    <div class="col-sm-8 col-sm-offset-3">
                                                                                        <button class="btn btn-primary updateArticleContenta" type="button" style="margin-bottom: auto;margin-right: 120px" >编辑正文</button>&nbsp;&nbsp;
                                                                                        <button class="btn btn-primary deteleArticleContentA" type="button" style="margin-bottom: auto;margin-right: 120px;">删除正文</button>&nbsp;&nbsp;
                                                                                    </div>
                                                                                </div>

                                                                            </form>

                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--折叠1结束-->
                                                        </div>
                                                    </c:if>
                                                <!--- 标签1结束-->
                                                    <c:if test="${list.contentType==1}">
                                                        <div class="tab-pane active"><br>
                                                            <div class="panel-group">
                                                                <div class="panel panel-info">
                                                                    <div class="panel-heading">
                                                                        <h4 class="panel-title">
                                                                            <a data-toggle="collapse" data-parent="#accordion"
                                                                               href="#${vs.index}">
                                                                                    ${list.title}
                                                                            </a>
                                                                        </h4>
                                                                    </div>
                                                                    <div id="${vs.index}" class="panel-collapse collapse">
                                                                        <div class="panel-body updateb">
                                                                            <form class="form-horizontal m-t ArticleContentb" method="post" action="" enctype="multipart/form-data" style="float:left;">
                                                                                <input name="idtArticleContent"type="hidden" value="${list.idtArticleContent}"/>
                                                                                <input name="contentType" type="hidden" value="${list.contentType}"/>
                                                                                <input type="hidden"name="articleId" value=""/>
                                                                                <textarea name=" contentValue"cols="80" rows="15"  style="height:150px;width: 700px">${list.contentValue}</textarea>
                                                                                <div class="form-group">
                                                                                    <div class="col-sm-8 col-sm-offset-3">
                                                                                        <button class="btn btn-primary updateArticleContentb" type="button"style="margin-bottom: auto;margin-right: 120px">编辑引用</button>&nbsp;&nbsp;
                                                                                        <button class="btn btn-primary deteleArticleContentB" type="button" style="margin-bottom: auto;margin-right: 120px;">删除引用</button>
                                                                                    </div>
                                                                                </div>
                                                                            </form>

                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--折叠2结束-->
                                                        </div>
                                                    </c:if>
                                                <!-- 标签二结束--->

                                                    <c:if test="${list.contentType==2}">
                                                        <div class="tab-pane active"><br>
                                                            <div class="panel-group">
                                                                <div class="panel panel-warning">
                                                                    <div class="panel-heading">
                                                                        <h4 class="panel-title">
                                                                            <a data-toggle="collapse" data-parent="#accordion"
                                                                               href="#${vs.index}">
                                                                                ${list.title}
                                                                            </a>
                                                                        </h4>
                                                                    </div>
                                                                    <div id="${vs.index}" class="panel-collapse collapse">
                                                                        <div class="panel-body">
                                                                            <form class="form-horizontal m-t ArticleContentc" method="post" action="" enctype="multipart/form-data" style="float:left;">
                                                                                <input name="idtArticleContent" type="hidden" value="${list.idtArticleContent}"/>
                                                                                <input name="contentType"type="hidden" value="${list.contentType}"/>
                                                                                <input type="hidden"name="articleId" value=""/>
                                                                                <div style="width: 400px;height: 200px;">
                                                                                    <div class="fileinput-preview fileinput-exists thumbnail">
                                                                                        <img id="picImg1" style="width: 100%;height: auto;max-height: 140px;" src="${list.pic}">
                                                                                    </div>
                                                                                    <textarea name=" contentValue" placeholder="${list.contentValue}" cols="40" rows="15" style="height: 80px;width: 400px">${list.contentValue}</textarea>
                                                                                </div><br><br><br><br>
                                                                                <div class="col-sm-6">
                                                                                    <input name="pic" class="form-control" type="hidden" value="${list.pic}" readonly>
                                                                                </div>
                                                                                <div class="col-sm-8">
                                                                                    <label class="col-sm-3 control-label">
                                                                                        <input type="file" class="addFile"  class="itemPic oas-filebutton-default">
                                                                                    </label>
                                                                                </div><br><br><br>
                                                                                <div class="form-group">
                                                                                    <div class="col-sm-8 col-sm-offset-3">
                                                                                        <button class="btn btn-primary updateArticleContentc" type="button" style="margin-bottom: auto;margin-right: 40px;float: left" >编辑图片</button>&nbsp;&nbsp;
                                                                                        <button class="btn btn-primary deteleArticleContentC" type="button">删除</button>
                                                                                    </div>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--折叠3结束-->
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                                <!-- 标签三结束--->
                                            </div>
                                        </div>
                                    </div>
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
    <script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/zTree/jquery.ztree.all.min.js"></script>
    <script type="text/javascript">


        $(document).ready(function () {
            <!-- 专栏下拉框 -->
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/findColumnName",
                success: function(msg){
                    var columnNameList = msg.rows;
                    for (var i=0; i < columnNameList.length; i++){
                        var id = columnNameList[i].idtSpecialColumn;
                        $("#columnName").append("<option name='specialColumnId' value="+id+">" + columnNameList[i].columnName + "</option>" );
                    }

                }
            });

            //文章更新保存
            $(document).on("click",".updateAllArticle",function(){

                var form = new FormData($(ArticleForm)[0]);
                $.ajax({
                    beforeSend: function(XMLHttpRequest) {
                        layer.msg('提交中，请稍等。。。', {
                            icon: 16
                            ,shade: 0.01,
                            time:1000
                        });
                    },
                    type: "POST",
                    processData:false,
                    contentType:false,
                    url: "${pageContext.request.contextPath}/admin/updateAllArticle",
                    data:form,

                    success: function(msg){
                        if (msg.code==1){
                            parent.layer.msg('编辑内容失败，请联系管理员。。。');
                            return;
                        } else {
                            location.reload();
                        }
                    }
                });

            });

            //正文更新保存
            $(document).on("click",".updateArticleContenta",function(){

                var con = $(this).closest(".panel-body").find(".ArticleContenta");
                var form = new FormData($(con)[0]);
                $.ajax({
                    type: "POST",
                    processData:false,
                    contentType:false,
                    url: "${pageContext.request.contextPath}/admin/updateArticleContent",
                    data:form,
                    success: function(msg){
                        if (msg.code==1){
                            parent.layer.msg('编辑内容失败，请联系管理员。。。');
                            return;
                        } else {
                            parent.layer.msg('提交中，请稍等。。。', {
                                icon: 16
                                ,shade: 0.01
                                ,time:1000
                            });
                        }
                    }
                });

            });


            <%--//文章正文删除--%>
            $(document).on("click",".deteleArticleContentA",function(){

                var con = $(this).closest(".panel-body").find(".ArticleContenta");
                var form = new FormData($(con)[0]);
                layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
                    $.ajax({
                        type: "POST",
                        processData:false,
                        contentType:false,
                        url: "${pageContext.request.contextPath}/admin/deleteArticleContent",
                        data:form,
                        success: function(msg){
                            layer.msg('已成功删除。。。', {time: 2000},function(){
                                location.reload();

                                layer.close(index);
                            });
                        }
                    });
                });

            });
            <%--//文章引用删除--%>
            $(document).on("click",".deteleArticleContentB",function(){

                var con = $(this).closest(".updateb").find(".ArticleContentb");
                var form = new FormData($(con)[0]);
                layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
                    $.ajax({
                        type: "POST",
                        processData:false,
                        contentType:false,
                        url: "${pageContext.request.contextPath}/admin/deleteArticleContent",
                        data:form,
                        success: function(msg){
                            layer.msg('已成功删除。。。', {time: 2000},function(){
                                location.reload();

                                layer.close(index);
                            });
                        }
                    });
                });

            });

            <%--//文章图片加文字删除--%>
            $(document).on("click",".deteleArticleContentC",function(){

                var con = $(this).closest(".panel-body").find(".ArticleContentc");
                var form = new FormData($(con)[0]);
                layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
                    $.ajax({
                        type: "POST",
                        processData:false,
                        contentType:false,
                        url: "${pageContext.request.contextPath}/admin/deleteArticleContent",
                        data:form,
                        success: function(msg){
                            layer.msg('已成功删除。。。', {time: 2000},function(){
                                location.reload();

                                layer.close(index);
                            });
                        }
                    });
                });

            });



            //引用更新保存
            $(document).on("click",".updateArticleContentb",function(){

                var con = $(this).closest(".updateb").find(".ArticleContentb");
                var form = new FormData($(con)[0]);
                $.ajax({
                    type: "POST",
                    processData:false,
                    contentType:false,
                    url: "${pageContext.request.contextPath}/admin/updateArticleContent",
                    data:form,
                    success: function(msg){
                        if (msg.code==1){
                            parent.layer.msg('编辑内容失败，请联系管理员。。。');
                            return;
                        } else {
                            parent.layer.msg('提交中，请稍等。。。', {
                                icon: 16
                                ,shade: 0.01
                                ,time:1000
                            });
                            location.reload();
                        }
                    }
                });

            });
            //图片更新保存
            $(document).on("click",".updateArticleContentc",function(){

                var con = $(this).closest(".panel-body").find(".ArticleContentc");
                var form = new FormData($(con)[0]);
                $.ajax({
                    type: "POST",
                    processData:false,
                    contentType:false,
                    url: "${pageContext.request.contextPath}/admin/updateArticleContent",
                    data:form,
                    success: function(msg){
                        if (msg.code==1){
                            parent.layer.msg('编辑内容失败，请联系管理员。。。');
                            return;
                        } else {
                            parent.layer.msg('提交中，请稍等。。。', {
                                icon: 16,
                                shade: 0.01,
                                time:1000
                            });
                            location.reload();
                        }
                    }
                });

            });

        });



        //文件上传
        $(document).on("change",".fileEdit",function(){

            var _this = $(this);
            var oMyForm = new FormData();
            if(!!!_this[0].files[0])
                return;
            oMyForm.append("file", _this[0].files[0]);

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
                        document.getElementById("Pic").src=msg;
                        _this.closest(".form-group").find(".form-control").val(msg);
                        layer.close();
                    });
                }
            });

        });
        $(document).on("change",".addFile",function(){

            var _this = $(this);
            var oMyForm = new FormData();
            if(!!!_this[0].files[0])
                return;
            oMyForm.append("file", _this[0].files[0]);

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
                        document.getElementById("picImg").src=msg;
                        _this.closest(".form-group").find(".form-control").val(msg);
                        layer.close();
                    });
                }
            });

        });




        //文章内容保存
        function saveArticleContent(){
            var form = new FormData($(ArticleContent)[0]);
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/insertArticleContent",
                data:form,
                success: function(msg){
                    if (msg.code==1){
                        parent.layer.msg('请先添加文章。。。');
                        return;
                    }
                    layer.msg(msg.message, {time: 2000},function(){

                        layer.close();
                        layer.msg('正在提交中，请稍等。。。', {
                            icon: 16
                            ,shade: 0.01
                            ,time:1000
                        });
                        location.reload();
                    });
                    //回显获取的数据
                    $(".info").renderValues(msg.rows,{
                        'get_content':function(item,value) {
                            $(item).html(value);
                        }
                    });
                    document.getElementById("div").style.display="";
                }
            });
        }

        //文章引用保存
        function saveArticleContent1(){
            var form = new FormData($(ArticleContent1)[0]);
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/insertArticleContent",
                data:form,
                success: function(msg){
                    if (msg.code==1){
                        parent.layer.msg('请先添加文章。。。');
                        return;
                    }
                    layer.msg(msg.message, {time: 2000},function(){

                        parent.layer.msg('提交中，请稍等。。。', {
                            icon: 16,
                            shade: 0.01,
                            time:1000
                        });
                        location.reload();
                    });
                    //回显获取的数据
                    $(".info1").renderValues(msg.rows,{
                        'get_content':function(item,value) {
                            $(item).html(value);
                        }
                    });
                    document.getElementById("div1").style.display="";

                }
            });
        }


        //文章图片保存
        function saveArticleContent2(){
            var form = new FormData($(ArticleContent2)[0]);
            $.ajax({
                type: "POST",
                processData:false,
                contentType:false,
                url: "${pageContext.request.contextPath}/admin/insertArticleContent",
                data:form,
                success: function(msg){
                    if (msg.code==1){
                        parent.layer.msg('请先添加文章。。。');
                        return;
                    }
                    layer.msg(msg.message, {time: 2000},function(){

                        parent.layer.msg('正在提交中，请稍等。。。', {
                            icon: 16,
                            shade: 0.01,
                            time:1000,
                        });
                        location.reload();
                    });

                    //回显获取的数据
                    $(".info2").renderValues(msg.rows,{
                        'get_content':function(item,value) {
                            $(item).html(value);
                        }
                    });
                    document.getElementById("div2").style.display="";
                }
            });
        }

        //图片文件上传
        $(document).on("change",".addFile",function(){

            var _this = $(this);
            var oMyForm = new FormData();
            if(!!!_this[0].files[0])
                return;
            oMyForm.append("file", _this[0].files[0]);

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
                        document.getElementById("picImg").src=msg;
                        _this.closest(".form-group").find(".form-control").val(msg);
                        layer.close();
                    });
                }
            });

        });

    </script>

</body>

</html>
