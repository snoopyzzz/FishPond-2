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
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
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

<body onload="getList();getPondData();getPondDataChart()">
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
            <div style="float: left">
                <section class="panel" style="width: 500px;">
                    <div id="main" style="height:200px;width:500px;">
                        <div id="temp" style="height:200px;width:500px;"/><br/>
                    </div>
                </section>
                <section class="panel" style="width: 500px;">>
                    <div id="main2" style="height:200px;width:500px;">
                        <div id="oxygen" style="height:200px;width:500px;"/><br/>
                    </div>
                </section>
                <section class="panel" style="width: 500px;">>
                    <div id="main3" style="height:200px;width:500px;">
                        <div id="ph" style="height:200px;width:500px;"/><br/>
                    </div>
                </section>
            </div>
            <div style="float: right">
                <table class="table" id="table" border="2" style="text-align: center;"/><br/>
                <table id="table2" border="2" style="text-align: center;"/><br/>
                <div id="div"/>
            </div>

        </section>
    </section>
    <!--主要内容 结束-->
</section>
</body>
<!-- container section start -->
<script type="text/javascript">
    function $G(){
        var Url=top.window.location.href;
        var u,g,StrBack='';
        if(arguments[arguments.length-1]=="#")
            u=Url.split("#");
        else
            u=Url.split("?");
        if (u.length==1) g='';
        else g=u[1];

        if(g!=''){
            gg=g.split("&");
            var MaxI=gg.length;
            str = arguments[0]+"=";
            for(i=0;i<MaxI;i++){
                if(gg[i].indexOf(str)==0) {
                    StrBack=gg[i].replace(str,"");
                    break;
                }
            }
        }
        return StrBack;
    }
    var pondId=$G("pondId");

    //分页
    function getList() {
        $.ajax({
            type : "GET",
            url : "get/" +pondId,
            success : function(data) {
                var a = eval('(' + data + ')');
                var b = a.data;
                var html = '  <tr bgcolor="#87CEFF">\n' +
                    '    <th>池塘编号</th>\n' +
                    '    <th>所养鱼</th>\n' +
                    '    <th>温度设备串口号</th>\n' +
                    '    <th>含氧量设备串口号</th>\n' +
                    '    <th>Ph值设备串口号</th>\n' +
                    '    <th>备注</th>\n' +
                    '    <th>创建人</th>\n' +
                    '    <th>创建时间</th>\n' +
                    '    <th>修改人</th>\n' +
                    '    <th>修改时间</th>\n' +
                    '  </tr>';

                html = html + '<tr>';
                html = html + '<td>' + b.name + '</td>';
                html = html + '<td>' + b.fishName + '</td>';
                html = html + '<td>' + b.comTemperature + '</td>';
                html = html + '<td>' + b.comOxygen + '</td>';
                html = html + '<td>' + b.comPh + '</td>';
                html = html + '<td>' + b.remark + '</td>';
                html = html + '<td>' + b.createUsername + '</td>';
                html = html + '<td>' + b.createTime + '</td>';
                html = html + '<td>' + b.updateUsername + '</td>';
                html = html + '<td>' + b.updateTime + '</td>';
                html = html + '</tr>';
                $('#table').html(html);
            }
        });
    }

    function getPondData(pageNum, pondId) {
        $.ajax({
            type : "GET",
            data : { pageNum : pageNum,
                pondId  : $G("pondId")},
            url : "findPageByPondId",
            success : function(data) {
                var a = eval('(' + data + ')');
                var b = a.data.list;
                var html = '  <tr bgcolor="#87CEFF">\n' +
                    '    <th></th>\n' +
                    '    <th>#</th>\n' +
                    '    <th>温度</th>\n' +
                    '    <th>含氧量</th>\n' +
                    '    <th>ph值</th>\n' +
                    '    <th>记录时间</th>\n' +
                    '    <th>修改人</th>\n' +
                    '    <th>修改时间</th>\n' +
                    '  </tr>';
                for ( var i = 0; i < b.length; i++) {//循环json对象，拼接tr,td的html
                    var num = i+1;
                    html = html + '<tr>';
                    html = html + '<td>' + '<input type="radio" name="rd" value= ' + b[i].id + ' />'+ '</td>';
                    html = html + '<td>' + num + '</td>';
                    html = html + '<td>' + b[i].temperature + '</td>';
                    html = html + '<td>' + b[i].oxygen + '</td>';
                    html = html + '<td>' + b[i].ph + '</td>';
                    html = html + '<td>' + b[i].recordTime + '</td>';
                    html = html + '<td>' + b[i].updateUsername + '</td>';
                    html = html + '<td>' + b[i].updateTime + '</td>';
                    html = html + '</tr>';
                }
                var page = a.data;
                $('#table2').html(html);//通过jquery方式获取table，并把tr,td的html输出到table中

                var html2 = "";
                var nextPage = page.pageNum + 1;
                html2 = html2 + '总记录数：' + page.total + '条'+ '</br>';
                html2 = html2 + '第' + page.pageNum + '页/共' + page.pages + '页'+ '</br>';
                html2 = html2 + '<a href="javascript:;" onclick="getPondData(' + 1 + ')">' + '首页' + '</a>' +'&nbsp;';
                html2 = html2 + '<a href="javascript:;" onclick="getPondData(' + page.prePage + ')">' + '上一页' + '</a>' +'&nbsp;';
                html2 = html2 + '<a href="javascript:;" onclick="getPondData(' + nextPage + ')">' + '下一页' + '</a>' +'&nbsp;';
                html2 = html2 + '<a href="javascript:;" onclick="getPondData(' + page.total + ')">' + '尾页' + '</a>' +'&nbsp;';
                $('#div').html(html2);
            }
        });
    }

    function breedSuggest() {
        $.ajax({
            type : "GET",
            url : "dataAlarm/" +pondId,
            success : function(data) {
                var aa = eval('(' + data + ')');
                var bb = aa.data;
                var html = "";
                if(bb != null){
                    html = html + bb;
                    $('#divSuggest').html(html);
                }
            }
        });
    }

    function dataAlarm() {
        $.ajax({
            type : "GET",
            url : "dataAlarm/" +pondId,
            success : function(data) {
                var aa = eval('(' + data + ')');
                var bb = aa.data;
                if(bb != null){
                    alert(bb);
                }
            }
        });
    }

    function getPondDataChart() {
        $.ajax({
            type : "GET",
            url : "getPreviousData/" + pondId,
            success : function(data) {
                var a = eval('(' + data + ')');
                var b = a.data;
                var dataTest = new Array();
                for ( var i = 0; i < b.length; i++) {//循环json对象，拼接tr,td的html
                    dataTest[i] = b[i];
                }

            }
        });
    }

    $(function(){
        setInterval(getDataChart,5000);
        setInterval(breedSuggest,3000);
    })

    function getDataChart() {
        $.ajax({
            type: "GET",
            url: "getPreviousData/" + pondId,
            success: function (data) {
                var a = eval('(' + data + ')');
                var b = a.data;
                var dataTemp = new Array();
                var dataOxygen = new Array();
                var dataPh = new Array();
                var timeTest = new Array();
                for (var i = 0; i < b.length; i++) {//循环json对象，拼接tr,td的html
                    dataTemp[i] = b[i].temperature;
                    dataOxygen[i] = b[i].oxygen;
                    dataPh[i] = b[i].ph;
                    timeTest[i] = b[i].recordTime;
                }
                var tempTitle = {
                    text: '温度'
                };
                var tempXAxis = {
                    categories: timeTest
                };
                var tempYAxis = {
                    title: {
                        text: 'Temperature (\xB0C)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                };

                var tempTooltip = {
                    valueSuffix: '\xB0C'
                }

                var tempLegend = {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                };

                var tempSeries = [
                    {
                        name: '温度',
                        data: dataTemp
                    },
                ];
                var temp = {};

                temp.title = tempTitle;
                temp.xAxis = tempXAxis;
                temp.yAxis = tempYAxis;
                temp.tooltip = tempTooltip;
                temp.legend = tempLegend;
                temp.series = tempSeries;


                var oxygenTitle = {
                    text: '含氧量'
                };
                var oxygenXAxis = {
                    categories: timeTest
                };
                var oxygenYAxis = {
                    title: {
                        text: 'Oxygen (\xB0C)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                };

                var oxygenTooltip = {
                    valueSuffix: '\xB0C'
                }

                var oxygenLegend = {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                };

                var oxygenSeries = [
                    {
                        name: '含氧量',
                        data: dataOxygen
                    },
                ];
                var oxygen = {};

                oxygen.title = oxygenTitle;
                oxygen.xAxis = oxygenXAxis;
                oxygen.yAxis = oxygenYAxis;
                oxygen.tooltip = oxygenTooltip;
                oxygen.legend = oxygenLegend;
                oxygen.series = oxygenSeries;

                var phTitle = {
                    text: 'Ph值'
                };
                var phXAxis = {
                    categories: timeTest
                };
                var phYAxis = {
                    title: {
                        text: 'Ph (\xB0C)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                };

                var phTooltip = {
                    valueSuffix: '\xB0C'
                }

                var phLegend = {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                };

                var phSeries = [
                    {
                        name: 'Ph值',
                        data: dataPh
                    },
                ];
                var ph = {};

                ph.title = phTitle;
                ph.xAxis = phXAxis;
                ph.yAxis = phYAxis;
                ph.tooltip = phTooltip;
                ph.legend = phLegend;
                ph.series = phSeries;

                $('#temp').highcharts(temp);
                $('#oxygen').highcharts(oxygen);
                $('#ph').highcharts(ph);
            }
        });
    }
</script>
</html>
