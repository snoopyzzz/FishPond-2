<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>鱼首页</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="<%=basePath%>img/favicon.png">

    <!-- Bootstrap CSS -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="<%=basePath%>css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="<%=basePath%>css/elegant-icons-style.css" rel="stylesheet" />
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" />
    <!-- full calendar css-->
    <link href="<%=basePath%>assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />
    <link href="<%=basePath%>assets/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" />
    <!-- easy pie chart-->
    <link href="<%=basePath%>assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <!-- owl carousel -->
    <link rel="stylesheet" href="<%=basePath%>css/owl.carousel.css" type="text/css">
    <link href="<%=basePath%>css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
    <!-- Custom styles -->
    <link rel="<%=basePath%>stylesheet" href="css/fullcalendar.css">
    <link href="<%=basePath%>css/widgets.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">
    <link href="<%=basePath%>css/style-responsive.css" rel="stylesheet" />
    <link href="<%=basePath%>css/xcharts.min.css" rel=" stylesheet">
    <link href="<%=basePath%>css/jquery-ui-1.10.4.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
    <script src="<%=basePath%>js/html5shiv.js"></script>
    <script src="<%=basePath%>js/respond.min.js"></script>
    <script src="<%=basePath%>js/lte-ie7.js"></script>
    <![endif]-->
</head>

<body onload="getList()">
<!-- container section start -->
<section id="container" class="">

    <!--菜单 开始-->
    <aside>
        <div id="sidebar"  class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu">
                <li class="active">
                    <a class="" href="<%=basePath%>index">
                        <i class="icon_house_alt"></i>
                        <span>主页</span>
                    </a>
                </li>
                <li class="active">
                    <a class="" href="<%=basePath%>fish">
                        <i class="icon_house_alt"></i>
                        <span>鱼</span>
                    </a>
                </li>
                <li class="active">
                    <a class="" href="<%=basePath%>pond">
                        <i class="icon_house_alt"></i>
                        <span>池塘</span>
                    </a>
                </li>
                <li class="active">
                    <a class="" href="<%=basePath%>historyData">
                        <i class="icon_house_alt"></i>
                        <span>历史数据</span>
                    </a>
                </li>
            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <!--菜单 结束-->

    <!--主要内容 开始-->
    <section id="main-content">
        <section class="wrapper">
            <input type="button" value="新增" id="addSubmit">
            <input type="button" value="修改" id="modifySubmit">
            <input type="button" value="删除" id="delSubmit">
            <table class="table" id="table" border="2" style="text-align: center;"/>
            <div id="div"/>
        </section>
    </section>
    <!--主要内容 结束-->
</section>
</body>
<!-- container section start -->

<script type="text/javascript">

    //新增
    $(function() {
        $("#addSubmit").click(function() {
            //var id=$("input[name='rd']:checked").val();
            $(location).attr('href', '/FishPond/fish/add');
        });
    });
    //修改
    $(function() {
        $("#modifySubmit").click(function() {
            var id=$("input[name='rd']:checked").val();
            if (id == null){
                alert("请选择要删除的条目");
                return;
            }
            $(location).attr('href', '/FishPond/fish/modify?id=' + id);
        });
    });

    //删除
    $(function() {
        $("#delSubmit").click(function() {
            var id=$("input[name='rd']:checked").val();
            $.ajax({
                type : "POST",
                url : "fish/delete/" + id,
                success : function() {
                    getList();
                }
            });
        });
    });

    //分页
    function getList(pageNum) {
        $.ajax({
            type : "GET",
            data : { pageNum : pageNum},
            url : "fish/findPage",
            success : function(data) {
                var a = eval('(' + data + ')');
                var b = a.data.list;
                var html = '  <tr bgcolor="#87CEFF">\n' +
                    '    <th></th>\n' +
                    '    <th style="text-align: center;">#</th>\n' +
                    '    <th style="text-align: center;">名字</th>\n' +
                    '    <th style="text-align: center;">详情</th>\n' +
                    '  </tr>';
                for ( var i = 0; i < b.length; i++) {//循环json对象，拼接tr,td的html
                    var num = i+1;
                    html = html + '<tr>';
                    html = html + '<td>' + '<input type="radio" name="rd" value= ' + b[i].id + ' />'+ '</td>';
                    html = html + '<td>' + num + '</td>';
                    html = html + '<td>' + b[i].name + '</td>';
                    html = html + '<td>' + '<a href="fish/get?id=' + b[i].id + '">' + '查看' + '</a>' + '</td>';
                    html = html + '</tr>';
                }
                var page = a.data;
                $('#table').html(html);//通过jquery方式获取table，并把tr,td的html输出到table中

                var html2 = "";
                var nextPage = page.pageNum + 1;
                html2 = html2 + '总记录数：' + page.total + '条'+ '</br>';
                html2 = html2 + '第' + page.pageNum + '页/共' + page.pages + '页'+ '</br>';
                html2 = html2 + '<a href="javascript:;" onclick="getList(' + 1 + ')">' + '首页' + '</a>' +'&nbsp;';
                html2 = html2 + '<a href="javascript:;" onclick="getList(' + page.prePage + ')">' + '上一页' + '</a>' +'&nbsp;';
                html2 = html2 + '<a href="javascript:;" onclick="getList(' + nextPage + ')">' + '下一页' + '</a>' +'&nbsp;';
                html2 = html2 + '<a href="javascript:;" onclick="getList(' + page.total + ')">' + '尾页' + '</a>' +'&nbsp;';
                $('#div').html(html2);
            }
        });
    }
</script>


</html>
