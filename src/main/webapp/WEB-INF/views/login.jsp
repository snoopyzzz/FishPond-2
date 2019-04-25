<%--
  Created by IntelliJ IDEA.
  User: 钊钊
  Date: 2018/12/11
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>登录</title>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body>

<form>
    username:<input type="text" name="username"><br>
    password:<input type="password" name="password"><br>
    <input type="button" value="submit" id="submit">
</form>

</body>

<script type="text/javascript">
    $(function(){
        $("#submit").click(function(){
            var url = "login";
            var params = {"username":$("input[type='text']").val(), "password":$("input[type='password']").val()};

            $.ajax({
                "url" : url,
                "data" : params,
                "type" : "post",
                "success" : function(data) {
                    // 参数为json类型的对象
                    var aa = eval('(' + data + ')');
                    if (aa.success){
                        $(location).attr('href', '/FishPond/index');
                    }else {
                        alert(aa.msg);
                    }
                },
            });

        });
    });
</script>

</html>
