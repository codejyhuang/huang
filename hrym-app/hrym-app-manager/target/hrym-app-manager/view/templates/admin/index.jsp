<%@ page import="org.apache.activemq.broker.UserIDBroker" %>
<%@ page import="java.io.Console" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>慧日永明运营后台</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>

    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.min.css?v=4.4.0"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <i class="fa fa-area-chart"></i>
                                        <strong class="font-bold">慧日永明</strong>
                                    </span>
                                </span>
                        </a>
                    </div>
                    <div class="logo-element">慧日永明后台
                    </div>
                </li>
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope">菜单栏</span>
                </li>
                <%--系统主页--%>
                <c:forEach var="list" items="${menuList}">
                    <c:choose>
                        <c:when test="${list.menuName=='dubbo监控'}">
                            <li>
                                <a class="J_menuItem" href="http://www.everglamming.com:8080/">
                                    <i class="fa fa-home"></i>
                                    <span class="nav-label">${list.menuName}</span>
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="#">
                                    <i class="fa fa fa-cog"></i>
                                    <span class="nav-label">${list.menuName}</span>
                                    <span class="fa arrow"></span>
                                </a>
                                <c:forEach var="sub" items="${list.menus}">
                                    <c:choose>
                                        <c:when test="${sub.menuName=='文章编辑'}">
                                            <ul class="nav nav-second-level">
                                                <li>
                                                    <a class="J_menuItem"
                                                       href="http://www.everglamming.com/textEdit/index.html">${sub.menuName}</a>
                                                </li>
                                            </ul>
                                        </c:when>
                                        <c:when test="${sub.menuName=='新文章编辑'}">
                                            <ul class="nav nav-second-level">
                                                <li>
                                                    <a class="J_menuItem"
                                                       href="https://www.everglamming.com/hrym-web/dist/index.html">${sub.menuName}</a>
                                                </li>
                                            </ul>
                                        </c:when>
                                        <c:otherwise>
                                            <ul class="nav nav-second-level">
                                                <li>
                                                    <a class="J_menuItem"
                                                       href="${pageContext.request.contextPath}/${sub.page}">${sub.menuName}</a>
                                                </li>
                                            </ul>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i
                        class="fa fa-bars"></i> </a>
                    <div class="scc">
                        登录者：${userName}
                    </div>
                    <input type="hidden" name="" id="userId" value="${userId}">
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown">
                            <i class="fa fa-user"></i> <span class="label label-primary "></span>
                        </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/logout">
                                    <div>
                                        <i class="fa fa-remove"></i>

                                        注销
                                        <span class="pull-right text-muted small"></span>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe id="J_iframe" width="100%" height="100%"
                    src="${pageContext.request.contextPath}/view/templates/admin/welcome.jsp" frameborder="0"
                    data-id="index_v1.html" seamless></iframe>
        </div>
    </div>
    <!--右侧部分结束-->
</div>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/view/static/assets/js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/view/static/assets/js/hAdmin.js?v=4.1.0"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/view/static/assets/js/index.js"></script>
</body>
<style>
    .scc {
        margin: auto;
        /*width: 100px;*/
        padding-top: 15px;
        margin-left: 70%;
    }
</style>
<script>
</script>
</html>
