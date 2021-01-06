<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>上传商品报价_${SYSTEM_SITE_NAME}</title>
<meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width">
<meta http-equiv="Cache-Control" content="no-siteapp">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-title" content="上传商品报价_${SYSTEM_SITE_NAME}">
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
<a href="javascript:history.go(-1);" onclick="javascript:history.go(-1);" data-direction="reverse">
<img src="/static/images/icon-back.png" width="24" height="24"></a>
</div>
<div class="head-bar-title">上传商品报价</div>

</div>
</div>




 	<div style="text-align: center;">
 	   <form action="/uploadProductDo" method="post">
         <h5>报价信息（直接按照一行一产品的格式，直接复制添加就可以，价格后面不能有其他字符</h5>
         <textarea id="msg" class="textarea" name="products"  style="width: 90%;min-height: 380px;max-height: 380px; ">  </textarea>
         <div style="margin: 0 auto; width: 280px;height: 45px">
         <button class="public_btn login_btn common_style_red" style="width: 130px;float: left;" type="submit">提交报价信息</button>
         <button id="del_but" class="public_btn login_btn common_style_red" style="width: 130px;float: left; margin-left: 20px;" type="button">删除所有报价</button>
       </form>  		
    </div>
         
         <div style="text-align: left;margin: 0 auto; width:300px; clear:both;  margin-top: 10px; font-size: 14px">
          		格式一行一种产品报价 例如:<br>
				SK-2前男友面膜 520<br>
				祖马龙蓝风铃 30ml 265<br>
				圣罗兰YSL小金条23号 200<br>
				香奈儿COCO 淡香 50ml 520<br>
				圣罗兰YSL自由之水 30ml 369<br>
				华为P40 Pro+ 8+256G 亮黑 7900<br>
				苹果蓝牙耳机AirPods Pro 白 1350<br>
				华为蓝牙耳机FreeBuds 3 无线版 白 800<br>
				小米10 至尊版 16+512G 黑银透明 5999<br>
				苹果iPhone 12 Pro Max 512G 海军蓝 12999<br>
				苹果iPad10.2寸 WiFi 32G 灰 2000<br>
				华为平板M6 10.8寸 WiFi 4+64G 金 2000<br>
				苹果AppleWatch S5蜂窝版40mm X32运动黑 250<br>
				华为MateBook X 2020版 i7+16+1TB 绿 11500
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
            <li class="navbar-item active">
                <a href="/uploadProduct">
                    <img src="/static/images/publish_active.png" alt="">
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
 jQuery(function($) {
 	$("#del_but").click(function(){
 	
		layer.open({
		    content: '您确定要删除你上传过的产品报价吗？'
		    ,btn: ['是', '否']
		    ,yes: function(index){
		      
		      
		      $.post("/delAllProduct",{},function(res){
				  		var data = res;
						layer.open({
						    content: data.msg
						    ,skin: 'msg'
						    ,time: 3
						});
				  });
		      layer.close(index);
		    }
	     });
	     
 		
 	});
 });
</script>

</body></html>