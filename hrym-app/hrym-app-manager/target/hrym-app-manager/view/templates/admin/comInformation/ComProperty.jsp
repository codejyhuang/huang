<%--
  Created by IntelliJ IDEA.
  User: hrym13
  Date: 2017/12/5
  Time: 下午2:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品属性</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/view/static/assets/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/view/static/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <%--表头--%>
            <div class="page-header">
            </div>
                <h2 class="text-center text-info">
                  <strong>商品属性详情</strong>
                </h2>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>大小</th>
                    <th>颜色</th>
                    <th>款式</th>
                    <th>版本</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>TB - Monthly</td>
                    <td>01/04/2012</td>
                    <td>Default</td>
                </tr>
                <tr class="success">
                    <td>1</td>
                    <td>TB - Monthly</td>
                    <td>01/04/2012</td>
                    <td>Approved</td>
                </tr>
                <tr class="error">
                    <td>2
                    </td>
                    <td>TB - Monthly</td>
                    <td>02/04/2012</td>
                    <td>Declined</td>
                </tr>
                <tr class="warning">
                    <td>3</td>
                    <td>TB - Monthly</td>
                    <td>03/04/2012</td>
                    <td>Pending</td>
                </tr>
                <tr class="info">
                    <td>4</td>
                    <td>TB - Monthly</td>
                    <td>04/04/2012</td>
                    <td>Call in to confirm</td>
                </tr>
                </tbody>
            </table>
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


</body>
</html>
