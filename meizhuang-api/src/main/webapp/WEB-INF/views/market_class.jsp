<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>逛市场_${SYSTEM_SITE_NAME}</title>
<meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width">
<meta http-equiv="Cache-Control" content="no-siteapp">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-title" content="逛市场_${SYSTEM_SITE_NAME}">
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


.city li{
   	float: left;

    text-align: center;
    padding:1%;
    width: 23%;
}
.city a{
    display: block;
    line-height:40px;
    background: #dedede;
    border-radius: 6px;
    font-weight: 400;
    
}
.city .active{
	background: #adadad;
	font-weight: bold;
}


.m_type{
 clear:both;
background: #f5f5f5;
overflow: hidden;
width: 98%;
height: 100%;
min-height:290px;
margin: auto;
border-radius: 6px;
padding: 3px;

}
.mt_item{
  float: left;
  width: 39.86vw;
  max-width: 165px;
  
}
.mt_item li{
	padding:1%;
	text-align: center;
}
.mt_item li a{
	display: block;
    line-height:40px;
    font-weight: 400;
    border-radius: 6px;
}
.mt_item .active{
	background: #adadad;
	font-weight: bold;
}
.mt_location, .mt_dangkuo{
  float:right;
  width: 50%;
}

.mt_location li, .mt_dangkuo li{
    padding: 1%;
    text-align: center;
        border-bottom: 1px solid #eee;
}
.mt_location li a, .mt_dangkuo li a{
	display: block;
    line-height:40px;
    font-weight: 400;
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
<div class="head-bar-title">逛市场</div>

</div>
<div class="head-bar-fix"></div>
</div>



<div class="market">
   <div class="city">
     <ul>
     <c:forEach items="${cityClass}" var="item">
     <li><a 
     <c:if test="${cid eq item.id}">
     class="active" 
     </c:if>
     href="/marketClass?id=${item.id}">${item.className}</a></li>
     </c:forEach>
     
     </ul>
   </div>
   
  
   
   <div class="m_type">
   	<ul class="mt_item">
   	<c:forEach items="${mClass}" var="item">
   		<li><a <c:if test="${mid eq item.id}"> class="active"  </c:if> href="/marketClass?id=${item.id}">${item.className}</a></li>
   	</c:forEach>
   	</ul>
   	<c:choose>
     <c:when test="${userInfoList != null}">
     	<ul  class="mt_dangkuo">
	   		<c:forEach items="${userInfoList}" var="item">
	   			<li><a href="/dangkou_goods?gate=${item.uid}&random=${item.random}">
	   			
	   		
	   			<c:choose>
     			<c:when test="${item.marketSname != null and item.uid != null}">
     			${item.marketSname}-
     			</c:when>
     			<c:otherwise>
     			${item.marketSname}
     			</c:otherwise>
     			</c:choose>
	   			
	   			${item.storeName}</a></li>
	   		</c:forEach>
	   	</ul>
     </c:when>
     <c:otherwise>
     	<ul class="mt_location">
	   	<c:forEach items="${lClass}" var="item">
	   		<li><a href="/marketClass?id=${item.id}">${item.className}</a></li>
	   	</c:forEach>
	   	</ul>
     </c:otherwise>
   	</c:choose>
   	
   	
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