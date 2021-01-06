<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>用户中心_${SYSTEM_SITE_NAME}</title>
<meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width">
<meta http-equiv="Cache-Control" content="no-siteapp">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-title" content="用户中心_${SYSTEM_SITE_NAME}">
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
<link rel="stylesheet" type="text/css" href="/static/css/ui-my.css">
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
<style id="tsbrowser_video_independent_player_style" type="text/css">
      [tsbrowser_force_max_size] {
        width: 100% !important;
        height: 100% !important;
        left: 0px !important;
        top: 0px !important;
        margin: 0px !important;
        padding: 0px !important;
        transform: none !important;
      }
      [tsbrowser_force_fixed] {
        position: fixed !important;
        z-index: 9999 !important;
        background: black !important;
      }
      [tsbrowser_force_hidden] {
        opacity: 0 !important;
        z-index: 0 !important;
      }
      [tsbrowser_hide_scrollbar] {
        overflow: hidden !important;
      }
      [tsbrowser_display_none] {
        display: none !important;
        visibility: hidden !important;
        opacity: 0 !important;
      }
      [tsbrowser_force_show] {
        display: black !important;
        visibility: visible !important;
        opacity: 0;
      }</style></head>
<body style="">

<div class="contentBody">

<div id="head-bar">
<div class="head-bar">
<div class="head-bar-back">
<a href="javascript:javascript:history.go(-1);" onclick="javascript:history.go(-1);" data-direction="reverse">
<img src="/static/images/icon-back.png" width="24" height="24"></a>
</div>
<div class="head-bar-title">用户中心

<a href="/loginOut" style="float: right; margin-right: 5px; font-size: 15px">退出</a>
</div>



</div>
</div>







<div class="box notranslate">
  <div class="aui-container">
		<div class="aui-page">
			<div class="aui-page-my">
				<div class="aui-my-info">
					<div class="aui-my-info-back"></div>
					<a href="javascript:;" class="">
						<img src="/static/images/my/icon-png/head.jpg" class="aui-my-avatar">
					</a>
					<div class="aui-mt-location aui-l-red"></div>
				</div>
				<div class="aui-l-content">
					<div class="aui-menu-list aui-menu-list-clear">
						<ul>
							<li class="b-line">
								<a href="/personalInformation?type=2">
									<div class="aui-icon"><img src="/static/images/my/icon-home/my-in1.png"></div>
									<h3>个人信息</h3>
									<div class="aui-time"><i class="aui-jump"></i></div>
								</a>
							</li>
							<li class="b-line">
							   <c:if test="${productCount  gt 0 }">
								   <a href="/dangkou_goods?gate=${user.uid}&random=${user.random}">
										<div class="aui-icon"><img src="/static/images/my/icon-home/my-in2.png"></div>
										<h3>我的档口</h3>
										<div class="aui-time"><i class="aui-jump"></i></div>
									</a>
							   </c:if>
								 <c:if test="${productCount  eq 0 || empty productCount}">
								   <a href="javascript:tipProduct();">
										<div class="aui-icon"><img src="/static/images/my/icon-home/my-in2.png"></div>
										<h3>我的档口</h3>
										<div class="aui-time"><i class="aui-jump"></i></div>
									</a>
							   </c:if>
							</li>
							<li class="b-line" style="display: none">
								<a href="my-secure.html">
									<div class="aui-icon"><img src="/static/images/my/icon-home/my-in3.png"></div>
									<h3>安全中心</h3>
									<div class="aui-time"><i class="aui-jump"></i></div>
								</a>
							</li>
							<li class="b-line" style="display: none">
								<a href="my-up.html">
									<div class="aui-icon"><img src="/static/images/my/icon-home/my-in4.png"></div>
									<h3>设置</h3>
									<div class="aui-time"><i class="aui-jump"></i></div>
								</a>
							</li>
							<li class="b-line">
								<a href="/uploadProduct">
									<div class="aui-icon"><img src="/static/images/my/icon-home/my-in8.png"></div>
									<h3>上传报价</h3>
									<div class="aui-time"><i class="aui-jump"></i></div>
								</a>
							</li>
							<li class="b-line">
								<a href="/productManage">
									<div class="aui-icon"><img src="/static/images/my/icon-home/my-in5.png"></div>
									<h3>报价管理</h3>
									<div class="aui-time"><i class="aui-jump"></i></div>
								</a>
							</li>
							<li class="b-line">
								<a href="/upPwd">
									<div class="aui-icon"><img src="/static/images/my/icon-home/my-in6.png"></div>
									<h3>修改密码</h3>
									<div class="aui-time"><i class="aui-jump"></i></div>
								</a>
							</li>
							
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


     <div style="height: 100px"></div>

   <div class="home_footer">
       
        
        <ul class="navbar">
                        
            <li class="navbar-item">
                <a href="/index">
                    <img src="/static/images/home.png" alt="">
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
         
            <li class="navbar-item active">
                <a href="/userInfo">
                    <img src="/static/images/me_active.png" alt="">
                    <p>我</p>
                </a>
            </li>
        </ul>

    </div>

</div>
<c:if test="${ msg != null }">
  <script type="text/javascript">
		      //提示
			  layer.open({
			    content: "${msg}"
			    ,skin: 'msg'
			    ,time: 3
			  });
		</script>
</c:if>
<script type="text/javascript">
	function tipProduct(){
		layer.open({content: '你还没有上传过产品报价',skin: 'msg' ,time: 3 });
	}
</script>

</body></html>