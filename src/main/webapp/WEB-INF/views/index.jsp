<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>首页</title>
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

<body>
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
<h1>欢迎光临</h1>
        </section>
    </section>
    <!--主要内容 结束-->
</section>
</body>
<!-- container section start -->

</html>
