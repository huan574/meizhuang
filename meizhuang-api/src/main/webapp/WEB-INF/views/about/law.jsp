<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>法律声明_${SYSTEM_SITE_NAME}</title>
<meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width">
<meta http-equiv="Cache-Control" content="no-siteapp">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-title" content="法律声明_${SYSTEM_SITE_NAME}">
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
}.user_login_2017  {
    padding: 30px;
}.user_login_2017 .log_links {
    text-align: center;
    margin-top: 20px;
}.or {
    position: relative;
    height: 50px;
    line-height: 50px;
    text-align: center;
}.or span {
    position: relative;
    z-index: 8;
    display: inline-block;
    padding: 0 15px;
    background: #fff;
    color: #808080;
    font-size: 14px;
}.or .line {
    position: absolute;
    width: 100%;
    height: 1px;
    border-bottom: 1px solid #ddd;
    z-index: 7;
    top: 24px;
    left: 0;
}.order-btn-1 {
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
<div class="head-bar-title">法律声明</div>

</div>
<div class="head-bar-fix"></div>
</div>



<div style="width: 95%;margin: 0 auto;">
<p>
	<br />
</p>
<p class="MsoNormal">
	<p class="MsoNormal">
		权利归属
	</p>
	<p class="MsoNormal">
		<span>除非猿报价网站（指猿报价网站及客户端，下同）另行声明，猿报价网站内的所有产品、技术、软件、程序、数据及其他信息（包括文字、图标、图片、照片、音频、视频、图表、色彩组合、版面设计等）的所有权利（包括版权、商标权、专利权、商业秘密及其他相关权利）均归猿报价网站服务提供者及</span>/或其关联公司所有。未经猿报价网站服务提供者及/或其关联公司许可，任何人不得以包括但不限于通过机器人、蜘蛛等程序或设备监视、复制、传播、展示、镜像、上载、下载等方式擅自使用猿报价网站内的任何内容。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>猿报价网站的</span>“猿报价”、“yuanbaojia”等文字及/或标识，以及猿报价网站的其他标识、徽记、产品和服务名称（包括但不限于“报价猿”等）均为猿报价网站服务提供者及/或其关联公司在中国和其他国家的商标，如有宣传、展示等任何使用需要，您必须取得猿报价网站服务提供者及/或其关联公司事先书面授权。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>其他猿报价网站服务提供者及</span>/或其关联公司没有主张权利的文字及/或标识、徽记、产品和服务名称则可能是它们各自所有权人的商标，在未获得相应权利人同意的情况下，您不应理解为有权使用被展示于猿报价网站的前述商标。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		责任限制
	</p>
	<p class="MsoNormal">
		猿报价网站上的店铺、商品信息（包括店铺名称、公司名称、联系人及联络信息、产品的描述和说明、相关图片、视频等）由用户自行提供并上传，并由用户对其提供并上传的信息承担相应法律责任。猿报价网站服务提供者对此另有约定的，将在相关的协议或其他法律文本中与您进行明确。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		猿报价网站转载的作品（包括论坛内容）出于传递更多信息之目的，并不意味我们赞同其观点或已经证实其内容的真实性。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<h3 align="center" style="text-align:center;">
		<b>知识产权保护</b><b></b>
	</h3>
	<p class="MsoNormal">
		我们尊重知识产权，反对并打击侵犯知识产权的行为。知识产权权利人若认为猿报价网站内容（包括但不限于猿报价网站用户发布的商品信息）侵犯其合法权益的，可以通过猿报价网站产权客服进行投诉，我们将在收到知识产权权利人合格通知后依据相应的法律法规以及猿报价网站规则及时处理。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<h3 align="center" style="text-align:center;">
		<b>著作权与商标声明</b><b></b>
	</h3>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		著作权
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		本网站著作权属于基什通信服务（深圳）有限公司所有。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		商标
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>猿报价，</span>yuanbaojia，小猿找货，报价猿，baojiayuan，以及他们的图标和/或其它本网站中提及的猿报价网站的产品，是基什通信服务（深圳）有限公司的商标或注册商标。本网站所提到的真实的公司和产品的名称可能是其各自所有者的商标。本网站所例举的公司、机构、产品、人士和事件是虚构的，不应认定或推断为任何实际公司、机 构、产品、人士或事件。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		未经本公司明确授予的任何权利均予保留。
	</p>
</p>
<p>
	<br />
</p>
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