<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title>文章发表</title>
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

    <script src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js?v=2.1.4"></script>


<%--富文本编辑器--%>
    <%--<script src="http://code.jquery.com/jquery-1.11.3.js"></script>--%>
    <!-- include libraries -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/summernote/dist/bootstrap.css" />
    <%--<script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.js"></script>--%>
    <%--<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.js"></script>--%>

    <!-- include summernote -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/summernote/dist/summernote-bs4.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/summernote/dist/summernote-bs4.js"></script>

    <style>
        .div{text-align: center }
    </style>
</head>

<body>
    <div class="container" style="width: auto ;height: auto">
    <div class="page-header div " style="color: #8a6d3b "><h1>文章管理</h1></div>
    <div class="container ">
        <div class="row clearfix">
            <div class="col-md-12 column ">
                <form class="form-horizontal m-t" id="article" method="post" action="${pageContext.request.contextPath}/admin/preview" enctype="multipart/form-data" style="float:left;width: 1000px">
                    <input type="hidden"name="idtArticle"id="idtArticle" value=""/>
                    <div class="container">
                        <div class="row clearfix">
                            <div class="col-md-6 column">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">文章名称：</label>
                                    <div class="col-sm-8">
                                        <input id="articleTitle" name="articleTitle" class="form-control" type="text" style="width: 250px">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 column">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">专栏名称：</label>
                                    <div class="col-sm-8"style="width: 1234px">
                                        <select class="input-group col-md-3" id="columnName" name="specialColumnId" style="width: 250px;height: 30px">

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <div class="row clearfix">
                            <div class="col-md-6 column">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">作者：</label>
                                    <div class="col-sm-8">
                                        <input id="author" name="author" class="form-control" type="text" style="width: 250px">
                                    </div>
                                </div><br>
                            </div>
                            <div class="col-md-6 column">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="oas-impInfo"></span>背景图片：</label>

                                    <div class="col-sm-6">
                                        <input name="articlePic"id="Pic" class="form-control"style="width: 200px" type="text" readonly>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="col-sm-3 control-label">
                                            <input type="file" class="fileEdit" name="Pic" class="itemPic oas-filebutton-default">
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--富文本--%>
                    <div class="form-group " >
                        <label for="contents">文章编辑</label>
                            <textarea  name="text" class="summernote" id="contents" title="Contents">${back}</textarea>
                    </div>
                    <div class="div">
                        <button class="btn btn-success save" id="btn" type="button" onclick="save();">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="submit"id="button" class="btn btn-primary s">预览</button>
                    </div>
                    <br>
                </form><br>
            </div>
        </div>
    </div>
</div>



</body> <!-- 全局js -->
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/zTree/jquery.ztree.all.min.js"></script>

<script src="${pageContext.request.contextPath}/view/static/assets/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/jrender.js"></script>
<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/view/static/assets/js/content.js?v=1.0.0"></script>

<!-- jQuery Validation plugin javascript-->
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/validate/messages_zh.min.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/laydate/laydate.js"></script>


  <script type="text/javascript">

      $(function() {
          $('.summernote').on('summernote.init', function() {
              console.log('summernote initialize!')
          }).on('summernote.change', function() {
              console.log(' changed content ')
          }).on('summernote.keyup', function(event) {
              console.log('you can use keyboard event', event);
          }).on('summernote.enter', function(event) {
              console.log('check enter key ');
          }).summernote(
              {
                  height: 450,
                  callbacks: {
                      onImageUpload: function (files) { //the onImageUpload API
                          var img = sendFile(files[0]);
                      }
                  }
              }

              );

      });

      $('form').on('click', ".look",function (e) {
          e.preventDefault();
          var a = $('.summernote').summernote('code');
          var b = $('.summernote').val();
          var form = new FormData($(content)[0]);
      });

      function sendFile(file) {
          var data = new FormData();
          data.append("file", file);

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
              data:data,
              success: function(url){
                  $(".summernote").summernote('insertImage', url, 'image name');
                  layer.msg('图片上传成功',{time:2000},function(){
                      layer.close();
                  });
              }
          });
      }

      <!-- 专栏下拉框 -->
      $(document).ready(function () {

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
      });

      //文章保存
          function save() {
              debugger
              layer.confirm('提交后不可修改，确定提交吗?', {icon: 3, title:'提示'}, function(index){
                  var form = new FormData($(article)[0]);
                  layer.msg('正在提交中，请稍等。。。', {
                      icon: 16,
                      shade: 0.01,
                      time:0
                  });
                  $.ajax({
                      type: "POST",
                      processData:false,
                      contentType:false,
                      url: "${pageContext.request.contextPath}/admin/preUrl",
                      data:form,
                      success: function(msg){
                          if (msg==""){
                              layer.close();
                              layer.msg("操作失败",{time:2000},function () {
                              });
                              return;
                          }
                          layer.close();
                          document.getElementById("btn").disabled=true;
                          layer.msg("操作成功", {time: 2000},function(){
                              layer.close();
                          });

                      }
                  });
              });
      }
      //文章预览
      function preview(){
              var text = $('#contents').val();
          window.location='${pageContext.request.contextPath}/admin/preview?text='+encodeURI(encodeURI(text,"UTF-8"));
      }

      //背景图上传
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
          console.log(oMyFrom)
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

</html>
