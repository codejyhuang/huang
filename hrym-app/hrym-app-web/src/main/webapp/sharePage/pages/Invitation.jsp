<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">


<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>一封来自慧修行的邀请函</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/sharePage/css/common.css?v=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/sharePage/css/Invitation.css?v=1.0">
</head>

<body>
  <div class="wrap">
    <!-- 邀请页 -->
    <div class="header">
    </div>
    <div class="content">
      <div class="Invitation_name">
        <dl>
          <dt>
            <span class="group_name">${bean.uesrname}</span>邀请您加入</dt>
          <dd>${bean.group_name}</dd>
        </dl>
      </div>
      <img class="portrait_img" src="${bean.img_url}" alt="">
      <div class="Invitation_content">
        我创建了一个社群，以后有什么好东西可以一起分享和查看，还可以在线做功课，功课智能计数，进度智能管理。快来加入吧
      </div>
    </div>
    <div class="footer">
      <div class="jump"  onclick="jump()" >同意加入</div>
    </div>
  </div>
</body>
<script>
  function jump() {
    if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
     //苹果端
    //  window.location.href = "https://fir.im/Iosbeta";
    window.location.href = "https://itunes.apple.com/cn/app/%E6%85%A7%E4%BF%AE%E8%A1%8C/id1294578736?l=en&mt=8";
  } else if (/(Android)/i.test(navigator.userAgent)) {
      //安卓端
      // window.location.href = "https://fir.im/everglamming";
      window.location.href = "http://www.everglamming.com/app/app-hrym-release.apk";
  } else {
     //pc端
  };
  }
</script>

</html>