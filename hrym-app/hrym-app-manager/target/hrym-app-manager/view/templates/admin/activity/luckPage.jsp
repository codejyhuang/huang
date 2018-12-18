
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>年会节庆等活动常用数字滚动抽奖js特效代码</title>
    <meta name="keywords" content="年会,节庆,活动,常用,数字滚动,抽奖,js特效代码">
    <meta name="description" content="年会节庆等活动常用数字滚动抽奖js特效代码。">
    <script src="${pageContext.request.contextPath}/view/static/assets/js/jquery.min.js?v=2.1.4"></script>
    <script type="text/javascript">
        function stops(){
            return false;
        }
        document.oncontextmenu=stops;
    </script>
    <style>
        *{margin:0;padding:0;}
        img{display:block;}
        i{font-style:normal;}
        .vetically{justify-content:center;align-items:center;display:-webkit-flex;}
        .prize_con{position: absolute;width: 100%;height: 100%;background: url(http://www.jsdaima.com/Upload/1486610025/firstp_bg.jpg) no-repeat left top / 100% 100%;overflow: hidden;}
        .prize_grade{font-size:98px;color: #ffe9af;text-align: center;margin: 60px auto 0;}
        .prize_list{width:55%;height:230px;margin: 20px auto 55px;text-align: center;overflow: hidden;}
        .prize_list ul{width:100%;font-size:0;}
        .prize_list li{display:inline-block;font-size:45px;color:#f1bf90;text-align: center;width:50%;line-height:100px;font-family:Arial;}
        .start{width: 250px;height: 90px;margin:0 auto;cursor:pointer;}
        .prize_set{position: absolute;right: 60px;bottom:140px;font-size: 16px;color: #f7f3e8;line-height: 30px;}
        .prize_set li{display: inline-block;margin-left: 20px;}
        .set_grade select,.set_people input, .set_money input{background: #fff;width:110px;height:36px;border:1px solid #8f0000;margin-left: .1rem;color: #000;padding-left:10px;}
    </style>
</head>

<body>
<div class="wrap">
    <div class="prize_con">
        <p class="prize_grade"><span>许愿抽奖</span></p>
        <div class="prize_list vetically">
            <ul id="list">
                <li>000000</li>
                <li>000000</li>
            </ul>
        </div>

        <p class="start"><img src="http://www.jsdaima.com/Upload/1486610025/prize_start.png" alt=""></p>
        <%--<ul class="prize_set">--%>
            <%--<li class="set_grade">愿望--%>
                <%--<select id="set_grade">--%>
                    <%--<option>选择愿望</option>--%>
                    <%--<option>愿望一</option>--%>
                    <%--<option>愿望二</option>--%>
                    <%--<option>愿望三</option>--%>
                <%--</select>--%>
            <%--</li>--%>
            <%--&lt;%&ndash;<li class="set_people">人数<input type="tel" placeholder="输入中奖人数" id="prizeCount"></li>&ndash;%&gt;--%>
        <%--</ul>--%>
    </div>
</div>
<input type="hidden" value="0" id="prize_btn">

<script>
    var myNumber;
    var arr = [];
    var code = [];

    $(document).ready(function () {

        $.ajax({
            type: "POST",
            processData:false,
            contentType:false,
            url: "${pageContext.request.contextPath}/admin/findAll",
            success: function(msg){

                if (msg.code==0){
                    var list = msg.rows;
                    for(var i=0;i<list.length;i++){
                        var id= list[i].id;
                        var username = list[i].userName;
                        var phone = list[i].phone;
                        code.push(id+":"+username+phone);
                    }
                    return;
                } else {
                    alert("请求异常")
                }
            }
        })
    })
    /*随机所有的code并且不重复*/
    function showRandomNum(num) {
        var id = "";
        var li = "";
        for(var i = 0; i < code.length; i++){
            arr[i] = i;
        }
        arr.sort(function(){
            return 0.5 - Math.random();
        });

        for(var i = 0; i < num; i++){
            var index = arr[i];
            var str = code[index];
            var id = str.split(":")[0];
            var content = str.split(":")[1];
            li += '<li>'+content+'</li>'+'<li style="display:none">'+id+'</li>';
        }

        $(".prize_list ul").html(li);

    }

    $(function () {
        $(".start").click(function(){
            if($("#prize_btn").val() == 0){
//                if($("#set_grade").val() == "选择愿望") {
//                    alert("请选择愿望");
//                    return;
//                }else{
                    $("#prize_btn").val(1);
//                    var num = $("#prizeCount").val();
                    var num = 1;
                    $(this).find("img").attr("src","http://www.jsdaima.com/Upload/1486610025/prize_stop.png");

                    myNumber = setInterval(function(){
                        showRandomNum(num);
                    }, 30);
//                }
            }else{
                $("#prize_btn").val(0);
                clearInterval(myNumber);
                $(this).find("img").attr("src","http://www.jsdaima.com/Upload/1486610025/prize_start.png");
//                var a=document.getElementById("list").getElementsByTagName("li")[0].innerHTML;
                var id=document.getElementById("list").getElementsByTagName("li")[1].innerHTML;

                $.ajax({
                    type: "POST",
                    processData:false,
                    contentType:false,
                    url: "${pageContext.request.contextPath}/admin/updateWinner?id="+id,
                    success: function(msg){

                        if (msg.code==0){
                            return;
                        } else {
                            alert("请求异常")
                        }
                    }
                })
            }
        })

        //回车键控制开始和停止
        $(document).keydown(function (event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if (e && e.keyCode == 13) { // enter 键
                $(".start").click();
            }
        });

        $("#set_grade").change(function(){
            $(".prize_grade span").text($(this).val());
        });

        $("#prizeMoney").keyup(function(){
            $(".prize_grade i").text($(this).val());
        });
    });
</script>

</body>

</html>
