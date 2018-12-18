<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>用户详情</title>
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

    <div class="row">
        <div class="col-sm-5">
            <dl class="dl-horizontal">

                <dl class="dl-horizontal" style="margin-left: 120px">
                    <p>
                        <h1>
                             <b>
                             用户基本信息
                             </b>
                        </h1>
                    </p>
                    
                </dl>
                <h3><br>
                    <dt>用户ID：</dt>
                    <dd>${userlist.uuid}</dd><br>
                    <dt>用户称呼：</dt>
                    <dd>${userlist.nickName}</dd><br>
                    <dt>用户身份：</dt>
                    <dd>${userlist.userTatus}</dd><br>
                    <dt>注册方式：</dt>
                    <dd>${userlist.source}</dd><br>
                    <dt>注册详情：</dt>
                    <dd>${userlist.registrable}</dd><br>
                    <dt>性别：</dt>
                    <dd>${userlist.sex} </dd><br>
                    <c:if test="${userlist.sex =='0'}">
                <dd>女</dd>
                </c:if>
                <c:if test="${userlist.sex =='1'}">
                    <dd>男</dd>
                </c:if>
                    <dt>出生日期：</dt>
                    <dd>${userlist.ymd}</dd><br>
                    <dt>注册时间：</dt>
                    <dd>${userlist.createdTime}</dd><br>
                    <dt>最近登录时间：</dt>
                    <dd>${userlist.updatedTime}</dd><br>
                </h3>
            </dl>
        </div>
        <div class="col-sm-7" id="cluster_info">
            <dl class="dl-horizontal">
                <dl class="dl-horizontal" style="margin-left: 23px">
                    <p><h1><b>
                    实名认证</b>
                </h1></p>

                </dl>
                <h3><br>
                    <dt>姓名：</dt>
                    <dd>${userlist.realName}</dd><br>
                    <dt>身份证号：</dt>
                    <dd>${userlist.identityCardNo}</dd><br>
                    <dt>身份证正面照：</dt>
                    <dd><img width="240px"height="100px" src="${userlist.identityCardUrl1}"></dd><br>
                    <dt>身份证反面照：</dt>
                    <dd><img width="245px"height="100px" src="${userlist.identityCardUrl2}"></dd>
                    <br><dt>审核结果：</dt>
                    <dd>${userlist.identityAuthState}</dd>

                </h3>

            </dl>
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
