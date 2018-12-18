
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>慧日永明</title>
  <link rel="stylesheet" href="../css/common.css?v=1.0">
  <style>
    body {
      font-size: 0.16rem;
      /* position: relative; */
      width: 100%;
      height: 100%;
      /* overflow: hidden; */
      background: #f7f7f7;
      font-family: "PingFangSC";
    }

    .l {
      float: left;
    }

    .r {
      float: right;
    }

    .wrap {
      width: 100%;
      /* overflow: hidden; */
      position: relative;
    }

    .banner {
      z-index: 999;
    }

    .banner img {
      display: block;
    }

    .banner .banner_top {}

    .banner .banner_top img {
      width: 100%;
      display: block;
    }

    .banner .cut_off {
      height: 0.91rem;
      position: relative
    }

    .banner .cut_off img {
      width: 2.1rem;
      height: 0.56rem;
      position: absolute;
      left: 50%;
      margin-left: -1.05rem;
      bottom: 0;
    }

    .banner .banner_center_list {
      overflow: hidden;
      margin-top: 0.48rem;
    }

    .banner .banner_center_list ul li {
      overflow: hidden;
    }

    .banner .banner_center_list .lessons_text {
      width: 1.02rem;
      margin-left: 0.3rem;
      margin-top: 0.5rem;
    }

    .banner .banner_center_list .lessons_img {
      width: 1.9rem;
      margin-right: 0.11rem;
    }

    .banner .banner_center_list .audio_img {
      width: 1.73rem;
      margin-left: 0.3rem;
    }

    .banner .banner_center_list .audio_text {
      width: 0.84rem;
      margin-right: 0.56rem;
      margin-top: 0.33rem;
    }

    .banner .banner_center_list .scripture_text {
      width: 0.88rem;
      margin-left: 0.3rem;
      margin-top: 0.26rem
    }

    .banner .banner_center_list .scripture_img {
      width: 2.49rem;
      margin-right: 0.05rem
    }

    .banner .banner_center_list .group_img {
      width: 1.94rem;
      margin-left: 0.17rem;
    }

    .banner .banner_center_list .group_text {
      width: 0.91rem;
      margin-right: 0.58rem;
      margin-top: 0.44rem;
    }

    .banner .banner_bottom {
      margin-top: 0.41rem;
    }

    .banner .banner_bottom .abstract {
      position: relative;
    }

    .banner .banner_bottom .abstract img {
      width: 2.43rem;
      margin-left: -1.215rem;
      position: relative;
      left: 50%;
    }

    .banner .banner_bottom .notice {
      position: relative;
      margin-top: 0.3rem;
    }

    .banner .banner_bottom .notice img {
      width: 2.13rem;
      left: 50%;
      position: relative;
      margin-left: -1.065rem;
    }

    .banner .banner_bottom .jump {
      position: relative;
      margin-top: 0.46rem;
    }

    .banner .banner_bottom .jump img {
      width: 0.45rem;
      position: relative;
      left: 50%;
      margin-left: -0.225rem;
    }

    .banner .banner_bottom .title {
      position: relative;
      background: url('../assets/activity/bottom_bg.png') no-repeat;
      background-position: -0.24rem bottom;
      background-size: 1.33rem;
      margin-top: 0.58rem;
      padding-bottom: 0.4rem;
    }

    .banner .banner_bottom .title img {
      width: 1.33rem;
      position: relative;
      left: 50%;
      margin-left: -0.665rem;
    }

    .layer .layer_wrap {
      width: 100%;
      height: 100%;
      background: #444444;
      opacity: 0.8;
      top: 0;
      position: absolute;
    }

    .layer .show_toast {
      width: 2.396rem;
      height: 3.73rem;
      border-radius: 0.05rem;
      background: #f7f7f7;
      margin: 1.16rem 0.678rem 0 0.678rem;
      position: fixed;
      top: 0;
    }

    .layer .show_toast .input_text {
      color: #444444;
      font-size: 0.12rem;
      padding-left: 0.13rem;
      box-sizing: border-box;
      width: 1.98rem;
      position: relative;
      z-index: 999;
      border: none;
      background: #f7f7f7;
      height: 0.28rem;
        margin-top: 0.03rem;
        margin-bottom: 0.01rem;
      left: 50%;
      margin-left: -0.99rem;
      outline: none;
    }

    .layer .show_toast ul li {
      position: relative;
      height: 0.52rem;
    }

    .layer .show_toast .input_border {
      width: 2.05rem;
      position: absolute;
      left: 50%;
      margin-left: -1.025rem;
      z-index: 10;
      margin-top: 0.01rem;
      top:0;
    }

    .layer .show_toast .prompt {
      box-sizing: border-box;
      height: 0.2rem;
      width: 100%;
      padding-left: 0.332rem;
      line-height: 0.18rem;
      color: #d60707;
      font-size: 0.08rem;
      opacity: 0;
    }

    .cloud {
      width: 1.28rem;
      position: absolute;
      left: -0.348rem;
      top: 3.42rem;
    }

    .to_close {
      width: 0.39rem;
      position: absolute;
      left: 50%;
      margin-left: -0.195rem;
      bottom: -0.77rem;
    }

    .submit {
      width: 1.43rem;
      position: relative;
      left: 50%;
      margin-left: -0.715rem;
    }
  </style>
</head>

<body>
  <div class="wrap">
    <!-- 活动页 -->
    <div class="banner">
      <div class="banner_top">
        <img src="../assets/activity/banner_top.png" alt="">
      </div>
      <div class="cut_off">
        <img src="../assets/activity/cut_off_img1.png" alt="">
      </div>
      <div class="banner_center_list">
        <ul>
          <li>
            <img class="l lessons_text" src="../assets/activity/lessons_text.png" alt="">
            <img class="r lessons_img" src="../assets/activity/lessons_img.png" alt="">
          </li>
          <li style="margin-top:0.45rem;">
            <img class="l audio_img" src="../assets/activity/audio_img.png" alt="">
            <img class="r audio_text" src="../assets/activity/audio_text.png" alt="">
          </li>
          <li>
            <div style="width:100%;height:0.67rem;background:url(../assets/activity/bg_cloud.png) no-repeat;background-size:1.01rem;background-position:-0.27rem bottom;">
            </div>
            <img class="l scripture_text" src="../assets/activity/scripture_text.png" alt="">
            <img class="r scripture_img" src="../assets/activity/scripture_img.png" alt="">
          </li>
          <li style="margin-top:0.7rem;">
            <img class="l group_img" src="../assets/activity/group_img.png" alt="">
            <img class="r group_text" src="../assets/activity/group_text.png" alt="">
          </li>
        </ul>
      </div>
      <div class="cut_off" style="height:1.07rem;">
        <img src="../assets/activity/cut_off_img2.png" alt="">
      </div>

      <div class="banner_bottom">
        <div class="abstract">
          <img src="../assets/activity/abstract.png" alt="">
        </div>
        <div class="notice">
          <img src="../assets/activity/notice.png" alt="">
        </div>
        <div class="jump">
          <img src="../assets/activity/jump.png" onclick="jump()" alt="">
        </div>
        <div class="title">
          <img src="../assets/activity/title.png" alt="">
        </div>
      </div>

    </div>
    <div class="layer" style="display:none;">
      <div class="layer_wrap"></div>
      <div class="show_toast_submit" style="display:none;width:2rem;height:0.5rem; position:fixed;top:50%;margin-top:-0.25rem;left:50%;margin-left:-1rem;background:#fff;text-align:center;line-height:0.5rem;border:1px solid #ccc;border-radius:5px;"></div>      
      <div class="show_toast">
        <ul style="margin-top:0.2rem;">
          <li>
            <input class="input_text input_name" type="text" value="" onblur="formatTest('name',this)" placeholder="输入姓名">
            <img class="input_border"  src="../assets/activity/border.png" />
            <div class="prompt test_name">
              请输入正确的姓名格式
            </div>
          </li>
          <li>
            <input class="input_text input_wx" type="text" value="" placeholder="输入微信/QQ(选填)">
            <img class="input_border" src="../assets/activity/border.png" />
            <div class="prompt test_wx">请输入微信号</div>
          </li>
          <li>
            <input class="input_text input_tel"  onblur="formatTest('tel',this)" type="text" value="" placeholder="输入手机号码">
            <img class="input_border" src="../assets/activity/border.png" />
            <div class="prompt test_tel">
              请输入正确的手机号码
            </div>
          </li>
          <li>
            <input class="input_text input_addr" type="text" onblur="formatTestNew(this)" value="" placeholder="输入收货地址">
            <img class="input_border" src="../assets/activity/border.png" />
            <div class="prompt test_addr">
              请输入收货地址
            </div>
          </li>
          <li>
            <input class="input_text input_email" type="text" value="" placeholder="输入电子邮箱(选填)">
            <img class="input_border" src="../assets/activity/border.png" />
            <div class="prompt test_email">
              请输入正确的邮箱地址
            </div>
          </li>
        </ul>
        <div style="position:relative;margin-top:0.12rem;">
          <img class="submit" onclick="toSubmit()" src="../assets/activity/submit_suc.png" alt="">
        </div>
        <img class="cloud" src="../assets/activity/cloud.png" alt="">
        <img class="to_close" onclick="toClose()" src="../assets/activity/close.png" alt="">
      </div>
    </div>
    <div class="login" style="display:none;width:2rem;height:0.5rem; position:fixed;top:2.5rem;left:50%;margin-left:-1rem;background:#fff;text-align:center;line-height:0.5rem;border:1px solid #ccc;border-radius:5px;">
      暂未登录，请登录
    </div>
  </div>
</body>
<script src="../js/ajax.js"></script>
<script>
    function hrefObj() {
      var localhref = window.location.href;
      if (localhref.indexOf('?') > -1) {
        var localarr = localhref.split('?')[1].split('&');
        var tempObj = {};
        for (var i = 0; i < localarr.length; i++) {
          tempObj[localarr[i].split('=')[0]] = localarr[i].split('=')[1];
        }
        return tempObj;
      } else {
        return null
      }
    }

  function jump() { //提交联系方式
    if (hrefObj().uuid === "0") { //是否登录
      document.querySelector(".login").style.display = "block";
    setTimeout(function () {
      document.querySelector(".login").style.display = "none";
    }, 2000);
  } else {
    document.querySelector("body").style.overflow = "hidden"
    document.querySelector(".layer").style.display = "block"
  }
  }

  function toClose() { //关闭蒙层弹窗
    document.querySelector("body").style.overflow = "visible";
    document.querySelector(".layer").style.display = "none";
  }
  var flag_name = false;
  var flag_tel = false;
  var flag_email = false;

  function formatTest(type, elm) {  //onblur 提交信息判断
    if (type === "name") {
      if (elm.value === "") {
        document.querySelector(".test_name").style.opacity = 0;
        flag_name = false;
        return
      }
      var reg = /[^\u4e00-\u9fa5]/; //验证是否全为中文
      !reg.test(elm.value) ? (document.querySelector(".test_name").style.opacity = 0, flag_name = true) : (document.querySelector(
        ".test_name").style.opacity = 1, flag_name = false);
    } else if (type === "tel") { //手机号验证
      if (elm.value === "") {
        document.querySelector(".test_tel").style.opacity = 0;
        flag_tel = false;
        return
      }
      var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
      myreg.test(elm.value) ? (document.querySelector(".test_tel").style.opacity = 0, flag_tel = true) : (document.querySelector(
        ".test_tel").style.opacity = 1, flag_tel = false);
    } else if (type === "email") { //邮箱格式验证
      if (elm.value === "") {
        document.querySelector(".test_email").style.opacity = 0;
        flag_email = false;
        return
      }
      var myReg = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
      myReg.test(elm.value) ? (document.querySelector(".test_email").style.opacity = 0, flag_email = true) : (document.querySelector(
        ".test_email").style.opacity = 1, flag_email = false);
    }

  }

  function toSubmit() {  //提交判定 是否为空
    if (document.querySelector(".input_addr").value !== "" &&
      flag_name && flag_tel) {
        var uuid = hrefObj().uuid;
        var userName = document.querySelector(".input_name").value;
        var wx = document.querySelector(".input_wx").value;
        var phone = document.querySelector(".input_tel").value;
        var address = document.querySelector(".input_addr").value;
        var email = document.querySelector(".input_email").value;
        ajax({
        type:"GET",
        url:"${pageContext.request.contextPath}/hrym/api/activity/updateUserInfo",
        dataType:"json",
        data:{uuid:uuid,userName:userName,wx:wx,phone:phone,address:address,email:email},
        success: function (data) {
          if (data == 1) {
            document.querySelector(".show_toast").style.display = "none";
            document.querySelector(".show_toast_submit").style.display = "block";
            document.querySelector(".show_toast_submit").innerHTML = "结缘成功";
            setTimeout(function () {
              document.querySelector(".show_toast_submit").style.display = "none";
              document.querySelector("body").style.overflow = "visible";
              document.querySelector(".layer").style.display = "none";
              document.querySelector(".show_toast").style.display = "block";
            }, 1500)
          } else {
            document.querySelector(".show_toast").style.display = "none";
            document.querySelector(".show_toast_submit").style.display = "block";
            document.querySelector(".show_toast_submit").innerHTML = "提交失败，请重试";
            setTimeout(function () {
              document.querySelector(".show_toast_submit").style.display = "none";
              document.querySelector("body").style.overflow = "visible";
              document.querySelector(".layer").style.display = "none";
              document.querySelector(".show_toast").style.display = "block";
            }, 1500)
          }
        },
        error: function (err) {
          //  document.querySelector("body").style.overflow = "visible";
          document.querySelector(".show_toast").style.display = "none";
          document.querySelector(".show_toast_submit").style.display = "block";
          document.querySelector(".show_toast_submit").innerHTML = "提交失败，请重试";
          setTimeout(function () {
            document.querySelector(".show_toast_submit").style.display = "none";
            document.querySelector("body").style.overflow = "visible";
            document.querySelector(".layer").style.display = "none";
            document.querySelector(".show_toast").style.display = "block";
          }, 1500)

        }
      })
    } else {

      if (document.querySelector(".input_name").value === "") {
        document.querySelector(".test_name").style.opacity = 1;
      }

      if (document.querySelector(".input_tel").value === "") {
        document.querySelector(".test_tel").style.opacity = 1;
      };

      if (document.querySelector(".input_addr").value === "") {
        document.querySelector(".test_addr").style.opacity = 1;
      } else {
        document.querySelector(".test_addr").style.opacity = 0;
      };
    }
  }

  function formatTestNew(elm) {
    if (elm.value !== "") {
      elm.parentNode.querySelector(".prompt").style.opacity = 0;
    }
  }

  
</script>

</html>