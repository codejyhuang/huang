<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hrym13
  Date: 2018/3/14
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>精美的表格样式</title>
    <style type="text/css">
        <!--
        body, table {
            font-size: 12px;
        }

        table {
            table-layout: fixed;
            empty-cells: show;
            border-collapse: collapse;
            margin: 0 auto;
        }

        td {
            height: 20px;
        }

        h1, h2, h3 {
            font-size: 12px;
            margin: 0;
            padding: 0;
        }

        .title {
            background: #FFF;
            border: 1px solid #9DB3C5;
            padding: 1px;
            width: 90%;
            margin: 20px auto;
        }

        .title h1 {
            line-height: 31px;
            text-align: center;
            background-repeat: repeat-x;
            background-position: 0 0;
            color: #FFF;
        }

        .title th, .title td {
            border: 1px solid #CAD9EA;
            padding: 5px;
        }

        /*这个是借鉴一个论坛的样式*/
        table.t1 {
            border: 1px solid #cad9ea;
            color: #666;
            text-align: center;
        }

        table.t1 th {
            background-repeat: repeat-x;
            height: 30px;
        }

        table.t1 td, table.t1 th {
            border: 1px solid #cad9ea;
            padding: 0 1em 0;
        }

        table.t1 tr.a1 {
            background-color: #f5fafe;
        }

        -->
    </style>

</head>

<body>
<div style="margin-top: 3%">

</div>
<table width="90%" id="mytab" border="1" class="t1">
    <div style="text-align: center">
        <caption><h4>表名称：${tableName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            今日活跃数：${integer}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            注册总数：${diurnalData.count}</h4>
        </caption>
    </div>
    <thead>
    <th width="10%">用户ID</th>
    <th width="10%">用户昵称</th>
    <th width="10%">用户真实姓名</th>
    <th width="20%">用户最近登录时间</th>
    <th width="20%">用户创建时间</th>
    </thead>
    <c:forEach var="list" items="${list}">
        <tr class="${list.nickName}">
            <td>${list.uuid }</td>
            <td>${list.nickName }</td>
            <td>${list.realName }</td>
            <td>${list.recentTimes}</td>
            <td>${list.createdTimes}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>