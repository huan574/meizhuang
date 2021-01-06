<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user.storeName}_${SYSTEM_SITE_NAME}-${SYSTEM_SITE_DESC}</title>
<meta name="keywords" content="${user.storeName}-${SYSTEM_SITE_NAME}-${SYSTEM_SITE_DESC}">
<meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-title" content="${user.storeName}">
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
<link rel="stylesheet" href="/static/css/semantic.css">
<script type="text/javascript" src="/static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript">
$(document).bind("mobileinit", function() {
　　$.mobile.ajaxEnabled = false;
});
var Dbrowser = '',AJPath = '',DTPath = '/',DTMob = '/';
</script>
<script type="text/javascript" src="/static/js/jquery.mobile.custom.min.js"></script>
<script type="text/javascript" src="/static/js/common.js"></script>

<style type="text/css">

.contentBody {
    max-width: 800px;
    margin: 0 auto;
}

.sub-screen-layer{position:absolute;top:0;bottom:0;left:0;
right:0px;
width:100%;height:100%;
background-color:#FFF;overflow:hidden;z-index:100;
}
</style>

<style type="text/css">
:root,body,html{height:100%;overflow:hidden;width:100%}*,
body{padding:0;margin:0}
*,body,h1,h2,h3,h4,h5,h6,p{margin:0}
.am-list-view-scrollview-content,:root,body,html{width:100%}
:root,html{line-height:1;font-size:12px;font-family:PingFang-SC-Medium,PingFangSC-Regular,"PingFang SC","Helvetica Neue",Helvetica,"Hiragino Sans GB","Microsoft YaHei","\5FAE\8F6F\96C5\9ED1",Arial,sans-serif;-webkit-transform:translateZ(0);transform:translateZ(0);-webkit-font-smoothing:antialiased;-moz-osx-font-smoothing:grayscale}
body{ -webkit-box-sizing:border-box;box-sizing:border-box}
*{ -webkit-tap-highlight-color:transparent}
a{text-decoration:none;color:#333}
img{border:none}
li{list-style:none}
input,textarea{ -webkit-appearance:none;resize:none}
div,p{ -webkit-overflow-scrolling:touch}
.good-item-row{background-color:#F5F5F5;overflow:auto;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-justify-content:space-between;-ms-flex-pack:justify;justify-content:space-between;-webkit-align-items:center;-ms-flex-align:center;align-items:center}
.topic-item-container:last-child{margin-bottom:0!important}
.icon-sousuo{font-size:4.53333333vw!important}
.search-btn .icon-sousuo{font-size:3.46666667vw!important}
.listview-container .am-list-view-scrollview-content{min-height:100%!important;top:0;left:0;bottom:0;right:0;margin:0}
.listview-container .am-pull-to-refresh-content-wrapper{min-height:100%!important}
.nav-list-container{width:100%;height:10.66vw;overflow-x:auto;overflow-y:hidden;background-color:#FFF;white-space:nowrap}
.nav-list-container .nav-item--container{display:inline-block;font-size:2.73333333vw;padding:0 3.2vw;height:10vw}
.am-list-footer,.am-list-header{padding:0!important}
.nav-list-container .nav-item--content{height:10vw;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-align-items:center;-ms-flex-align:center;align-items:center;-webkit-justify-content:center;-ms-flex-pack:center;justify-content:center;-webkit-box-sizing:border-box;box-sizing:border-box;border-bottom:2px solid transparent;color:#666}
.nav-list-container .nav-item--content.activated{color:#333;border-bottom:2px solid #111}
.nav-list-container .nav-item--content .item-text{margin:0 .26666667vw}
.am-list-view-scrollview{overflow-x:hidden}.am-list-header{margin:0}
.slider-decorator-0{z-index:1;bottom:4.26666667vw!important}
.am-carousel-wrap-dot>span{background-color:#FFF!important;border-radius:.53333333vw!important;width:3.2vw!important;height:1.06666667vw!important}
.am-carousel-wrap-dot.am-carousel-wrap-dot-active>span{width:11.73333333vw!important;background-color:#E4393C!important}
.am-modal-body{padding-bottom:0!important}
</style>

<style type="text/css">
.default-img{background-image:url(data:image/png;base64,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);background-repeat:no-repeat;background-size:100% 100%}.single-line-ellipsis{white-space:nowrap;-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden}.normal-font{color:#333;font-size:2.73333333vw}.shop-detail-content-container{width:100%;height:100%}
.shop-detail-content-container .shop-content-header-info{position:relative;width:100%;height:19.2vw;-webkit-box-sizing:border-box;box-sizing:border-box;padding:0 4.266vw;overflow:hidden}
.shop-detail-content-container .shop-content-header-info .info-top{width:100%;height:12.496vw;display:-webkit-flex;display:-ms-flexbox;
display:flex;-webkit-align-items:center;-ms-flex-align:center;align-items:center;-webkit-justify-content:space-between;
-ms-flex-pack:justify;justify-content:space-between;font-size:3.8vw;color:#333;font-weight:700}
.shop-detail-content-container .shop-content-header-info .info-bottom{width:100%;height:6.7vw;line-height:normal}
.shop-detail-content-container .shop-content-header-info .info-bottom .info-bottom-layer{display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-align-items:center;-ms-flex-align:center;align-items:center}
.shop-detail-content-container .shop-content-header-info .info-shop-name{ -webkit-flex-grow:0;-ms-flex-positive:0;flex-grow:0;
-webkit-flex-shrink:0;-ms-flex-negative:0;flex-shrink:0;max-width:50%;height:100%;
display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-align-items:center;-ms-flex-align:center;
align-items:center;white-space:nowrap;-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden}
.shop-detail-content-container .shop-content-header-info .info-shop-address{font-size:2.5vw;color:#999;vertical-align:middle}
.shop-detail-content-container .shop-content-header-info .icons-container{ -webkit-flex-grow:0;-ms-flex-positive:0;flex-grow:0;-webkit-flex-shrink:0;-ms-flex-negative:0;flex-shrink:0}
.shop-detail-content-container .shop-content-header-info .icons-container .icon{margin-left:4.266vw;font-size:3.8vw;color:#333;
font-weight:700;padding:1.066vw}
.shop-detail-content-container .shop-content-header-info .info-flag{width:3.733vw;margin-right:.8vw;vertical-align:middle}
.shop-detail-content-container .shop-top-tab{position:absolute;left:0;top:0;width:100%}
</style>

<style type="text/css">
	.good-item:nth-child(2){margin-right:0}
	.good-item .item-img--container{font-size:0;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-align-items:center;
	-ms-flex-align:center;align-items:center;-webkit-justify-content:center;-ms-flex-pack:center;justify-content:center;
	background-image:url(data:image/png;base64,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);background-repeat:no-repeat;background-size:100% 100%;position:relative}
	.good-item .item-img--container .item-img{ -webkit-flex-grow:1;-ms-flex-positive:1;flex-grow:1;-webkit-flex-shrink:1;-ms-flex-negative:1;flex-shrink:1}.good-item .recommend-icon{position:absolute;right:0;top:-.8vw;width:10.66666667vw;height:9.6vw}.good-item .item-title{height:9.6vw;font-size:3.2vw;color:#666;line-height:1.5;max-height:9.6vw;overflow:hidden}
	.good-item{ -webkit-box-sizing:border-box;box-sizing:border-box;background-color:#FFF;width:49.33vw}
	.good-item .item-img{width:49.33vw;height:49.33vw}
	.good-item .item-desc{ -webkit-box-sizing:border-box;box-sizing:border-box;padding:2.13333333vw}
	.good-item .item-price{margin-top:2.13333333vw;font-size:3.2vw;color:#333}
	.good-item .item-price .price-main{font-size:4.8vw}
</style>
<style type="text/css">
	.shop-detail-info-container{width:100%;height:100%;background-color:#F8F8F8;position:relative;overflow:auto}
	.shop-detail-info-container .detail-info-wrapper{ -webkit-box-sizing:border-box;box-sizing:border-box;padding:0 4.26666667vw;background-color:#FFF;margin-top:2.13333333vw}
	.shop-detail-info-container .detail-list-info-item{display:-webkit-flex;display:-ms-flexbox;display:flex;
	-webkit-align-items:flex-start;-ms-flex-align:start;align-items:flex-start;font-size:2.3vw;
	-webkit-box-sizing:border-box;box-sizing:border-box;border-bottom:1px solid #EAEAEA;padding:3.73333333vw 0}
	.shop-detail-info-container .detail-list-info-item .info-item-key{ -webkit-flex-grow:0;-ms-flex-positive:0;flex-grow:0;-webkit-flex-shrink:0;-ms-flex-negative:0;flex-shrink:0;margin-right:4.26666667vw;-webkit-flex-basis:8vw;-ms-flex-preferred-size:8vw;flex-basis:8vw;line-height:2.8vw}
	.shop-detail-info-container .detail-list-info-item .info-item-value{ -webkit-flex-grow:1;-ms-flex-positive:1;flex-grow:1;-webkit-flex-shrink:1;-ms-flex-negative:1;flex-shrink:1;color:#333;line-height:2.8vw}
	.shop-detail-info-container .detail-info-service-item{display:-webkit-flex;display:-ms-flexbox;display:flex;margin-bottom:2.13333333vw}
	.shop-detail-info-container .detail-info-service-item:last-child{margin-bottom:0}
	.shop-detail-info-container .service-item-desc{ -webkit-flex-grow:1;-ms-flex-positive:1;flex-grow:1;-webkit-flex-shrink:1;
	-ms-flex-negative:1;flex-shrink:1;white-space:pre-wrap;font-size:2.73333333vw;line-height:4.8vw;color:#333}
	.shop-detail-info-container .service-item-icon{ -webkit-flex-grow:0;-ms-flex-positive:0;flex-grow:0;-webkit-flex-shrink:0;
	-ms-flex-negative:0;flex-shrink:0;width:4.8vw;height:4.8vw;margin-right:4.26666667vw}
	.shop-detail-info-container .detail-item-wechat-qrcode{width:32vw;height:32vw;margin-top:2.13333333vw}
	
	@font-face {font-family: "icon-17";
	  src: url(//resource.17zwd.com/m/media/iconfont.9afad0a5.eot); /* IE9*/
	  src: url(//resource.17zwd.com/m/media/iconfont.9afad0a5.eot#iefix) format('embedded-opentype'), 
	  url('data:application/x-font-woff;charset=utf-8;base64,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') format('woff'),
	  url(//resource.17zwd.com/m/media/iconfont.d3239b6e.ttf) format('truetype'), 
	  url(//resource.17zwd.com/m/media/iconfont.0a95ef9b.svg#icon-17) format('svg'); /* iOS 4.1- */
	}
	.icon-17 {
	  font-family:"icon-17" !important;
	  font-size:16px;
	  font-style:normal;
	  -webkit-font-smoothing: antialiased;
	  -moz-osx-font-smoothing: grayscale;
	}
	.icon-liwu:before { content: "\E600"; }
	.icon-icon:before { content: "\E601"; }
	.icon-yunfuzhuang:before { content: "\E61E"; }
	.icon-eye:before { content: "\E676"; }
	.icon-youjiantou:before { content: "\E62B"; }
	.icon-jiantouarrow483:before { content: "\E695"; }
	.icon-txuzhuanhuan:before { content: "\E66D"; }
	.icon-banshenqun:before { content: "\E629"; }
	.icon-chenshan:before { content: "\E636"; }
	.icon-taozhuang:before { content: "\E612"; }
	.icon-lianyiqun:before { content: "\E632"; }
	.icon-dingwei:before { content: "\E66A"; }
	.icon-shuaxin:before { content: "\E641"; }
	.icon-nvzhuang:before { content: "\E604"; }
	.icon-zhiding:before { content: "\E8B8"; }
	.icon-yundongfu:before { content: "\E61A"; }
	.icon-yijianfankui:before { content: "\E61C"; }
	.icon-Txu_tshirt:before { content: "\E7C9"; }
	.icon-sousuo:before { content: "\E62F"; }
	.icon-Anvzhuang-xuefangshan:before { content: "\E624"; }
	.icon-huangguan:before { content: "\E608"; }
	.icon-sanjiaoxing:before { content: "\E603"; }
	.icon-shichangyingxiangli-:before { content: "\E65E"; }
	.icon-kinds:before { content: "\E62A"; }
	.icon-guanbi:before { content: "\E6AF"; }
	.icon-xinkuan:before { content: "\E64C"; }
	.icon-Icon_dianpu:before { content: "\E61D"; }
	.icon-guanbi1:before { content: "\E674"; }
	.icon-fanhui:before { content: "\E698"; }
	.icon-jiantou-copy:before { content: "\E602"; }
	.icon-icon_home:before { content: "\E61F"; }
	.icon-icon_phone:before { content: "\E620"; }
	.icon-icon_watch_g:before { content: "\E621"; }
	.icon-icon_shoping:before { content: "\E622"; }
	.icon-icon_cehua:before { content: "\E626"; }
	.icon-icon_dangkoziliao:before { content: "\E627"; }
	.icon-icon_xianshi:before { content: "\E646"; }
	.icon-icon_yincang:before { content: "\E648"; }
	.icon-icon_shouye:before { content: "\E672"; }
	.icon-icon_lianxishagjia:before { content: "\E68D"; }
	.icon-icon_search_delete:before { content: "\E710"; }
	.icon-icon_selected_l:before { content: "\E738"; }
	.icon-icon_jindian:before { content: "\E694"; }
	.icon-loading:before { content: "\E69B"; }
</style>
<style type="text/css">
	.shop-content-tab-container{width:100%;height:16vw;background-color:#FFF;display:-webkit-flex;display:-ms-flexbox;display:flex;
	-webkit-align-items:flex-start;-ms-flex-align:start;align-items:flex-start;-webkit-justify-content:space-around;
	-ms-flex-pack:distribute;justify-content:space-around;margin-bottom:2.133vw;position:relative}
	.shop-content-tab-container .shop-content-tab-item-container{ -webkit-flex-grow:0;-ms-flex-positive:0;flex-grow:0;
	-webkit-flex-shrink:0;-ms-flex-negative:0;flex-shrink:0;-webkit-box-sizing:border-box;box-sizing:border-box;height:100%;
	border-bottom:.533vw solid #FFF;padding-bottom:2.133vw;padding-top:3.2vw;text-align:center;font-size:2.73333333vw}
	.shop-content-tab-container .shop-content-tab-item-container.activated{border-bottom:.533vw solid #e4393c}
	.shop-content-tab-container .shop-content-tab-item-container .tab-item-title{margin-top:2.133vw}
	.shop-content-tab-container .am-pull-to-refresh-content{background-color:#FAFAFA!important}
</style>


</head>
<body>



<div class="contentBody">
<div class="sub-screen-layer">
<div class="shop-detail-content-container listview-container">
<div class="am-list-view-scrollview" style="position: relative; overflow: auto; height: 100%;">
<div class="am-list-view-scrollview-content am-list" style="position: absolute; min-width: 100%;">
<div class="am-pull-to-refresh-content-wrapper">
<div class="am-pull-to-refresh-content am-pull-to-refresh-down am-pull-to-refresh-transition" style="transform: translate3d(0px, 0px, 0px);">
<div>
<div class="am-list-header">
<div style="width: 100%; border: none; box-shadow: rgba(180, 180, 180, 0.5) 0px -1px 8px 0px;">
<div class="shop-content-header-info">
<div class="info-top">
<div class="info-shop-name">${user.storeName}</div>
<div class="icons-container">
<a href="javascript:;" onclick="javascript:history.go(-1);"><i class="icon icon-17 icon-guanbi1"></i></a>
<a href="/index"><i class="icon icon-17 icon-icon_shouye"></i></a>
</div>
</div>
<div class="info-bottom">
<div class="info-bottom-layer">
<img class="info-flag" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAcCAYAAAAAwr0iAAAAAXNSR0IArs4c6QAAA7xJREFUSA29Vl1IFFEUvufOqGU/KibobD302I/0Eli2u24I/VhCBEWQERUZQVEPBQUVvQQ9GUQvEvhgBYHRQ0ImPbTOzm4GEUWB9BBJ6VqEJWWZOjO3c2Ybm79dFXMvzN5zznfu9525fzvAAtpgNLqC6WaXEGxNADz7ELA3IPOGkKp+9A7m3oBobi4Qk6Ljv4mTgGBrLU7k9ur5Cki/7ruCI2q8iXP3RU2G280ETjcdDm8TBnsoGHPFnTlzsZFUgMQaFE17ZPNMCX2JRKomDPZKCFFhg/PRA8CXQomtq0gkhojfWgJx+TIfN8Sd+RYnQdIYN9ht0iRfop+j48YF7A6Tnae28se790bLwAcV0pvqIkKYT7Ayq5g8FcBwKQwAvlkWptkqWH7F6SWtFxZmq+8Y5msGbB0ucYnWfswOZOn3yhLbgEfmcxbcFQbOri5Zuqgcp1l1AW5njLR5ZTLeyzlvQnLTjf/zZJD7KjXtGcjQgKSj/xC/hfjNUFI7X9Ld/RVRenyNtEjT0iZUSar3BbCzvsy/AYPpd7/FYqVKIvGCAd+N4cmgXCTuVEKVxwlLb4yewnXeFZjH4QxpEja1B5antBZgcCNoAP0v/JrQH4hYbEEo2fOYAxxGMbwwHQ1YLywr2wcdHcbApuhewcwWBzplkoaSTFyzA1MFUEDZWn+K3sIGnT0WERmcMKwLREklbuM5OmfjAOwtW1i0U+ns/DUUjsVwi7djdS5uysXleUAa9jgr5nTITjc2Fovhbz0ouN6LkU9vEHqaOEn2YG3kOs7DHl4kbayKx/s/RaPV+qRI4OSUEO5sWORzKC+royKdcV+VlCDj26BQvzPRtvHOOIHra729sqX+tFQAtSRO3xD6pNkVKI5cxOkVJ06c8eCWjkZXmbpI4XSWBmVIEj9YpanthI2Ew2U/DdCwuNW+XIARLkOtoqp9PgwDWQugZFpPw9C70Swk39PoJOwvKC5S9bHxe7hkYQ9O7oQkyVurtHg8ALNCOQugjHRtpMkU4lYOAjNow1E+npYD1obNNphycmAWlCGAS9nysonjfXFxOnHinHYGbGHc8W14sRyy/Vw97vi2UEo7kivHxqadATtRWbvqGBI/tv1sPeUohfKxbLg3PuMZoIHD27cvHRsZ1fCoVXuJMj68Xli6OFze1fU9GPdHZ1UADafzLnSzFy8gxUUHLI3f/huCvv1deR5nxktgj8sIyDuc/4oZW94xW3HinHUBNGh5Kv4SuNiDl/tvesimGGF5bUORSAU9cxH9A19GWNA9jATSAAAAAElFTkSuQmCC" alt="">

<img class="info-flag" style="display: none" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAcCAYAAAAAwr0iAAAAAXNSR0IArs4c6QAAA4FJREFUSA29Vk1oU0EQbpImByla0ZaaVsReioqINE2iJpielBpEhfZiReilCEo9VFBQ6UXwLN4KHrSiB/GgYBULapX80FT8g2IuFgoJIhSxwUjSJn7fa/axb/NeYqjNg+3OzDcz3+7s7Ka2BpOvp6dne7FYnMTYYwLXbLLZbF8w+mZmZhbUYJtq6O7udsL2FsOnYmvU44gPzs7O5uU8dlmhjJXewPS/yZnaV8pNWf8MFUDpjxYKhWdADXbde+1C0W638yiei1Q6EUq/DcaPGC0CXKf5B/Luw1GkmV87grGxMc73MdabnJzkmChxNjhoWVpauoppiHKdvp3JZHIlnU5P27xebxDn/gpXTltMnRbAZl9BP/Q62tra2BCt9SKWeOzYtL/sGkoOdRHtjY2NQyhHtgrbAHA//L5X8RPwTZfLtQX+08KgzuQktz0ej8egDMKhoDoJHfgcrk0cJeuDLSPsFvM4fK9Eo9FF+C9a+BTISW7tCPAwPIbjJQtnmh+GQqFmJH6PwFPQDc+piAP2tLOz8xx1PGojmE4ITJ7hN1riXL2GBHElou3t7VshemXnktyay+UOdXV1PYhEIsmOjo5v2N1JYPpDBjmGcXxqaioH8gHcrHEFh6o99bcTicR1TcEfQxOGw+ER7kKAyhzMZDLaA4LVT8DvssAhf3U4HGFU6LfH4wmB/K6am77we0IOEafZZIUynuQNcHyDHXpUjDow7uACZZDdwtSPZjoQi8XmEbsXOn9JN2EYPsQlkPMwFykDhgoQoAN3A3GeuvohyXkQabvHbi7iMTlIcv4PAd9JjDJy2OZFhdR88hkaML/fvyufz0dgbDYAJQU7OotKsNQNgUBgczabfQdxdwmWp59Op5OLnJONQrZcAB14ntjxC4guESDNvAmnUf7p5eXlR5ADEibEHBZ6BAt9LQzqXHEBdEZpB9FU99RASef7UXaUxHE8Z9iwkm+ZWPUHKJVKfXK73STpLYteNVht4hp2ftsiRjdXXQA9+bOJN2IHxP16ZAUBZb+DZh6t4KJDpqXTUUlALwwj8UvJZCrSp6mpadgUNDFalc/EFf9V+nwb0XDsdt53s+8zmjKAN/6XGWhmq2kBTMD7jqbks+tWEqbQdH403YJir6j+8xGILCQA0THoGWGjTFut5IyveQEMAtEHEPbjvP9wUKaNWF2/YDDYwrEW0r8qdzE5jS9zcQAAAABJRU5ErkJggg==" alt="">
	<span class="info-shop-address" style="display: none">
		<a href="#">明通</a> / <a href="#">2F</a>  A2-154
	</span>
	<span class="info-shop-address">
		${user.tradeStall}
	</span>
 </div>
</div>
</div>
<div class="shop-content-tab-container">
<div class="shop-content-tab-item-container activated" id="goods-info">
 
<p style="color: rgb(51, 51, 51);">${productCount}</p>
<p class="tab-item-title" style="color: rgb(51, 51, 51);"><a href="#">全部产品</a></p>
</div>
<div class="shop-content-tab-item-container" id="gate-info">
<div class="icon-layer"><i class="icon icon-17 icon-icon_dangkoziliao" style="color: rgb(102, 102, 102);"></i></div>
<p class="tab-item-title" style="color: rgb(102, 102, 102);"><a href="#">档口资料</a></p>
</div>
</div>
</div>
</div>
<div class="am-list-body">
<div class="list-view-section-body">
<div class="row-separator"></div>



<div id="list-contact" class="shop-detail-info-container" style="display: none">
<div class="detail-info-wrapper">
<div class="detail-list-info-item"><span class="info-item-key">市场</span>
	<span class="info-item-value" style="display: none">
		<a href="#">明通</a> / <a href="#">2F</a>  A2-154
	</span>
	<span class="info-item-value">
	 	${user.tradeStall}
	</span>
</div>
<div class="detail-list-info-item"><span class="info-item-key">电话</span><span class="info-item-value"><span>${user.mobile}</span>
<button class="ui red button phone" data-msg="${user.mobile}">呼叫</button>
</span></div>
<div class="detail-list-info-item"><span class="info-item-key">微信</span><span class="info-item-value">${user.weixin}</span></div>
</div>


<div class="detail-info-wrapper" id="imgbox">
	<div class="twocodebox">
	<div class="detail-list-info-item">
	<p>微信二维码</p>
	</div>
	<div style="text-align:center;padding-bottom:100px;"><img src="/getFile/${user.weixinImg}/" alt="" width="60%"></div>
	</div>
</div>
</div>


<c:forEach items="${mapProducts}" var="item" >
<div id="list-goods" class="list-img" style="height: auto;    border: 1px solid #3a3434;
    padding: 20px;
    margin: 0 10px 20px; border-radius:10px;">
	<ul>
	<li style="line-height:30px;height:auto;font-weight:bold;">最近更新时间：${item.key}</li>
	<li style="line-height:20px;height:auto;">
	
	<c:forEach var="product" items="${item.value}">
		${product.offerProduct}<br>
	</c:forEach>
	
	</li>
	</ul>
</div>
</c:forEach>

<div style="height:55px"></div>

</div>
</div>
<div class="am-list-footer">
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>

<div class="fix-nav" id="fix-nav" style="display: none;">
        <div class="fix-nav-wrap">
            <img class="i-totop" src="/static/img/scroll-to-top-icon.png">
        </div>
    </div>
    
    
<div class="contentBody">
     <div style="height: 100px"></div>

	<div class="home_footer" style="z-index: 999999;">
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
<script type="text/javascript">
        jQuery(function($){
            $('#gate-info').click(function(){
            	 $('#list-contact').css("display","block");
            	 $('.list-img').css("display","none");
            	 $('#gate-info').addClass("activated");
            	 $('#goods-info').removeClass("activated");
            });
            $('#goods-info').click(function(){
            	 $('#list-contact').css("display","none");
            	 $('.list-img').css("display","block");
            	 $('#gate-info').removeClass("activated");
            	 $('#goods-info').addClass("activated");
            });
           
           $(".phone").click(function(){
           	  window.location.href = 'tel:'+$(this).attr("data-msg");
           })
            
        
            $(window).bind("scroll",function() { 
                initScroll() 
            });
            var $fixnav = $('#fix-nav');
            function initScroll()
            {
                if ($(window).scrollTop() > $(window).height()/4) {
                    $fixnav.show();
                } else {
                    $fixnav.hide();
                }
            }
            $('.fix-nav-wrap').bind('click', function(e){
                $(window).scrollTop(0);
            });
        });
        
    </script>

</body></html>