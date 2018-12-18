<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Bootstrap 实例 - 简单的轮播（Carousel）插件</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/view/static/assets/js/bootstrap.min.js"></script>

	<link rel="shortcut icon" href="favicon.ico">
	<link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<script type="text/javascript">
    $(function(){
        $("#slidershow").carousel({
            interval:2000
        });
        $("#slidershow a.left").click(function(){
            $(".carousel").carousel("prev");
        });
        $("#slidershow a.right").click(function(){
            $(".carousel").carousel("next");
        });
    });
</script>
<body>

<div id="slidershow" class="carousel slide">
	<!-- 设置图片轮播的顺序 -->
	<ol class="carousel-indicators">
		<li class="active" data-target="#slidershow" data-slide-to="0"></li>
		<li data-target="#slidershow" data-slide-to="1"></li>
		<li data-target="#slidershow" data-slide-to="2"></li>
	</ol>
	<!-- 设置轮播图片 -->
	<div class="carousel-inner"style="height: 700px;width: 100%;">
		<div class="item active">
			<a href="##"><img src="${pageContext.request.contextPath}/view/static/assets/img/WechatIMG125.jpeg" style="height: 700px;width: 100%;" alt=""></a>
			<div class="carousel-caption">
				<h2>欢迎使用慧日永明慧修行APP后台管理</h2>

			</div>
		</div>
		<div class="item" style="height: 700px;width: 100%;">
			<a href="##"><img src="${pageContext.request.contextPath}/view/static/assets/img/center (1).jpeg" style="height: 700px;width: 100%;" alt=""></a>
			<div class="carousel-caption">
				<h2>欢迎使用慧日永明慧修行APP后台管理</h2>
			</div>
		</div>
		<div class="item">
			<a href="##"><img src="${pageContext.request.contextPath}/view/static/assets/img/1450158632448237.jpg" style="height: 700px;width: 100%;" alt=""></a>
			<div class="carousel-caption">
				<h2>欢迎使用慧日永明慧修行APP后台管理</h2>
			</div>
		</div>
	</div>
	<a class="left carousel-control" href="#slidershow" role="button">
		<span class="glyphicon glyphicon-chevron-left"></span>
	</a>
	<a class="right carousel-control" href="#slidershow" role="button">
		<span class="glyphicon glyphicon-chevron-right"></span>
	</a>
</div>
</body>
</html>