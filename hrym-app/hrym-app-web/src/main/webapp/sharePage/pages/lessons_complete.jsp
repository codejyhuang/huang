<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>${bean.uesrname}的功课动态</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/sharePage/css/common.css?v=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/sharePage/css/lessons_complete.css?v=1.0">
  <style>
    /* complete */
  </style>
</head>

<body>
  <div class="wrap">
    <!-- 功课完成页 -->
    <div class="header">
      <div class="Sharing">
        好友分享
      </div>
      <div class="portrait">
        <img class="portrait_img" src="${bean.img_url}" alt="APP头像" style="width:100%;height: 100%;border-radius: 50%;">
      </div>
    </div>
    <div class="content">
      <div class="share_name">
        <dl>
          <!-- <%--<dt>${bean.uesrname}</dt>--%> -->
          <dd>来自您的好友&nbsp;
            <span class="name_mark">${bean.uesrname}</span>&nbsp;的分享</dd>
        </dl>
      </div>
      <div class="share_content">
        <dl>
          <dt>功课完成</dt>
          <dd>
            <ul class="lessons_list">
              <li class="lessons_list_first">
               功课名称:&nbsp;<span class="lessons_name">${bean.lessons_name}</span>
              </li>
              <li>
                功课目标:&nbsp;
                <span class="lessons_target">${bean.lessons_target}</span>
              </li>
              <li class="showAll">
                完成期限:&nbsp;
                <span class="lessons_term">${bean.lessons_term}</span>
              </li>
              <li class="showAll">
                开始时间:&nbsp;
                <span class="lessons_statrtAt">${bean.lessons_statrtAt}</span>
              </li>
              <li class="showToday">
                已经完成:&nbsp;
                <span class="lessons_completedAll">${bean.lessons_completed_all}</span>
              </li>
              <li class="showToday">
                今日建议:&nbsp;
                <span class="lessons_proposal">${bean.lessons_proposal}</span>
              </li>
              <li class="showToday">
                今日完成:&nbsp;
                <span class="lessons_complToday">${bean.lessons_completed_today}</span>
              </li>
            </ul>
          </dd>
        </dl>
      </div>
    </div>
    <div class="footer">
      <!-- <input class="jump" type="button" onclick="jump()" value="我也要做" /> -->
      <div class="jump" onclick="jump()">我也要做</div>
    </div>
  </div>
</body>
<script>
  if (2 == '${bean.type}'){
      var showToday=document.getElementsByClassName("showToday")

      for(var i=0 ;i<showToday.length;i++){
          showToday[i].style.display ="none";
      }
  }
  if (1 == '${bean.type}'){
      var showToday=document.getElementsByClassName("showAll")

      for(var i=0 ;i<showToday.length;i++){
          showToday[i].style.display ="none";//
      }
  }

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