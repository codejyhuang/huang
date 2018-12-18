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

    <%--富文本编辑器--%>
    <script src="http://code.jquery.com/jquery-1.11.3.js"></script>
    <!-- include libraries -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.css" />
    <script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.js"></script>
    <%--<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.js"></script>--%>

    <!-- include summernote -->
    <link rel="stylesheet" href="/summernote/dist/summernote-bs4.css">
    <script type="text/javascript" src="/summernote/dist/summernote-bs4.js"></script>
</head>

<body>

<%--富文本编辑器--%>
<div class="container">
    <form id="form" action="${pageContext.request.contextPath}/admin/preview" novalidate method="post">
        <%--<div class="form-group">--%>
        <%--<label for="input">Text</label>--%>
        <%--<input type="text" class="form-input" id="input" value="Title">--%>
        <%--</div>--%>
        <div class="form-group">
            <label for="contents">文章编辑</label>
            <textarea name="text" class="summernote" id="contents" title="Contents"></textarea>
        </div>
        <button  class="btn btn-sucess submit" type="button">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="submit" class="btn btn-sucess">预览</button>
    </form>
</div>

</body>
<!-- 全局js -->
<%--<script src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js?v=2.1.4"></script>--%>
<script src="${pageContext.request.contextPath}/view/static/assets/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/jrender.js"></script>
<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/view/static/assets/js/content.js?v=1.0.0"></script>

<!-- jQuery Validation plugin javascript-->
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/validate/messages_zh.min.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/zTree/jquery.ztree.all.min.js"></script>

  <script type="text/javascript">

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
      function saveArticle(){
          var form = new FormData($(article)[0]);
          $.ajax({
              type: "POST",
              processData:false,
              contentType:false,
              url: "${pageContext.request.contextPath}/admin/insertAllArticle",
              data:form,
              beforeSend: function(XMLHttpRequest) {
                  layer.msg('提交中，请稍等。。。', {
                      icon: 16
                      ,shade: 0.01
                  });
              },
              success: function(msg){
                  debugger
                  document.getElementById("btn").disabled=true;
                  document.getElementById("idtArticleA").value=msg.rows.articleId;
                  document.getElementById("idtArticleB").value=msg.rows.articleId;
                  document.getElementById("idtArticleC").value=msg.rows.articleId;
                  layer.msg(msg.message, {time: 2000},function(){
                      layer.close();
                  });

              }
          });
      }
      //背景图上传
      $(document).on("change",".fileEdit",function(){

          debugger
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

      $(function() {
          $('.summernote').on('summernote.init', function() {
              console.log('summernote initialize!')
          }).on('summernote.change', function() {
              console.log(' changed content ')
          }).on('summernote.keyup', function(event) {
              console.log('you can use keyboard event', event);
          }).on('summernote.enter', function(event) {
              console.log('check enter key ');
          }).summernote({ height : 300 });

      });
//      $('form').on('submit', function (e) {
//          e.preventDefault();
//          alert($('.summernote').summernote('code'));
//          alert($('.summernote').val());
//      });

      $('form').on('click', ".look",function (e) {
          e.preventDefault();
          var a = $('.summernote').summernote('code');
          var b = $('.summernote').val();
          var form = new FormData($(content)[0]);
//          window.location="/admin/preview?"+"text="+encodeURI(b);
//          debugger
          <%--$.ajax({--%>
              <%--type: "POST",--%>
              <%--processData:false,--%>
              <%--contentType:false,--%>
              <%--url: "${pageContext.request.contextPath}/admin/preview",--%>
              <%--data: form,--%>
              <%--success: function(msg){--%>
              <%--}--%>
          <%--})--%>
          <%--window.location="${pageContext.request.contextPath}/admin/preview?text="+encodeURI(encodeURI(b,"UTF-8"));--%>
      });
  </script>

</html>
