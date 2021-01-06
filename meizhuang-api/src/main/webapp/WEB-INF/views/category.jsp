<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>产品库_${SYSTEM_SITE_NAME}</title>
<meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width">
<meta http-equiv="Cache-Control" content="no-siteapp">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-title" content="产品库_${SYSTEM_SITE_NAME}">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<meta name="mobile-web-app-capable" content="yes">
<meta name="msapplication-TileColor" content="#007AFF">
<link rel="shortcut icon" href="/static/favicon.ico" type="image/x-icon">
<c:if test="${WEB_CACHE_CONTROL eq '1'}">
<meta http-equiv="expires" content="0">  
<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache"> 
</c:if>
<link rel="stylesheet" type="text/css" href="/static/css/style.css">
<link rel="stylesheet" href="/static/css/layer.css">
<script type="text/javascript" src="/static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/static/js/layer.js"></script>


<style>
.contentBody {
    max-width: 800px;
    margin: 0 auto;
    }
.public_box {
    padding: 10px;
    font-size: 13px;
    color: #4d4d4d;
}.public_inputtxt {
    width: 100%;
    border: 1px solid #ddd;
    border-radius: 5px;
    height: 40px;
    margin-bottom: 10px;
    box-sizing: border-box;
    text-indent: 10px;
    font-size: 14px;
    color: #666;
}.public_btn {
    background: #333;
    width: 100%;
    height: 45px;
    line-height: 45px;
    border: 0;
    font-size: 14px;
    color: #fff;
    text-align: center;
    display: block;
    border-radius: 4px;
}
.common_style_red {
    background: #ed4040 !important;
    color: #fff !important;
}

.category {
    width: 100%;
    overflow: hidden;
    clear: both;
    height: 100%;
    box-sizing: border-box;
    z-index: 0;
}

.nav-left {
    width: 28%;
    z-index: 10;
    float: left;
    box-sizing: content-box;
    height: 100%;
}
.nav-left li {
    height: 46px;
    line-height: 46px;
    padding-left: 3px;
    background: #f0f0f0;
    border-bottom: 1px solid #d3cccc;
    text-align: center;
    font-size: 14px;
    color: #666;
    cursor: pointer;
}
.nav-left li a{
display:block;
color: #666;
}
.nav-left li.show a{
color: #d30d0c;
}
.nav-left li.show {
	color: #d30d0c;
    background: url(/static/images/list-bg.png) no-repeat;
    background-color: #fff;
}

.nav-right {
    float: right;
    height: 100%;
    width: 72%;
}
.nav-right li {
    float: left;
    width: 33%;
    text-align: center;
    margin-bottom: 4px;
}

.nav-right li img{
max-width: 65px;
}

.nav-right li p{
	margin: 0px;
	padding: 2px;
}

.nav-right-d{
float: right;
height: 100%;
width: 72%;
}
.nav-right-d li{
width: 100%;
overflow: hidden;
padding: 3px
}

.nav-right-d li img{
max-width: 65px;
max-height: 65px;
height: 65px;
}
.nav-right-d li div:nth-child(1){
	float: left;
	width: 20%;
	text-align: center;
	
}
.nav-right-d li div:nth-child(2){
	float: right;
	width: 75%;
	height: 45px;
	padding-left:5%;
	padding-top: 20px;
}

</style>
</head>
<body style="">

<div class="contentBody">

<div id="head-bar">
<div class="head-bar">
<div class="head-bar-back" >
<a href="javascript:history.go(-1);" onclick="javascript:history.go(-1);" data-direction="reverse">
<img src="/static/images/icon-back.png" width="24" height="24"></a>
</div>
<div class="head-bar-title">产品库</div>

</div>
<div class="head-bar-fix"></div>
</div>

<div class="category">
   <div class="nav-left">
   			<ul>
   			    <c:forEach items="${fCategoryList}" var="item">
   			    	<li <c:if test="${fid eq item.id}"> class="show"  </c:if> >
   			    	<a href="/categoryClass?id=${item.id}">
   			    	${item.className}
   			    	</a>
   			    	</li>
   			    </c:forEach>
		    </ul>
  </div>
  
  <div class="nav-right" <c:if test="${sCategoryList ==null}"> style="display: none"  </c:if> >
   	   <ul>
   	    <c:forEach items="${sCategoryList}" var="item">
	   	   	<li>
	   	   	<a href="/categoryClass?id=${item.id}">
	   	   	<img src="/getFile/category/${item.img}/" alt="">
	   	   	<p>${item.className}</p></a>
	   	   	</li>
   	   	</c:forEach>
   	   	
   	   </ul>
  </div>
  <div class="nav-right-d" <c:if test="${tCategoryList ==null}"> style="display: none"</c:if> >
   	   <ul>
   	   <c:forEach items="${tCategoryList}" var="item">
   	   	<li>
   	   	<a href="#">
   	   	<div><img src="/getFile/category/${item.img}/" alt=""></div>
   	   	<div>${item.className}</div>
   	   	</a>
   	   	</li>
   	   	</c:forEach> 	   	
   	   </ul>
  </div>
</div>
 
 <div style="height: 100px"></div>
 <div class="home_footer">
        <ul class="navbar">
                        
            <li class="navbar-item active">
                <a href="/index">
                    <img src="/static/images/home_active.png" alt="">
                    <p>小猿找货</p>
                </a>
            </li>
            <li class="navbar-item">
                <a href="/uploadProduct">
                    <img src="/static/images/publish.png" alt="">
                    <p>上传报价</p>
                </a>
            </li>
         
         	<li class="navbar-item">
                <a href="/realTimeProduct">
                    <img src="/static/images/realtime.png" alt="">
                    <p>实时报价</p>
                </a>
            </li>
         
            <li class="navbar-item">
                <a href="/userInfo">
                    <img src="/static/images/me.png" alt="">
                    <p>我</p>
                </a>
            </li>
        </ul>

    </div>

</div>
</body></html>