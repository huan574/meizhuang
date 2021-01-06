<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.meizhuang.utils.SystemParameterUtils,
com.meizhuang.entity.enums.SystemParameterEnum" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   
   String keys = SystemParameterUtils.get(SystemParameterEnum.SEARCH_HOT_KEYS);
   if(keys!=null){
   		request.setAttribute("keys", keys.split("@"));
   }
   
   String  adStationStatus = SystemParameterUtils.get(SystemParameterEnum.AD_STATION_STATUS);
  
   String  proclamationStatus = SystemParameterUtils.get(SystemParameterEnum.PROCLAMATION_STATUS);
   request.setAttribute("adStatus", adStationStatus);
   request.setAttribute("proclStatus", proclamationStatus);
   
%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>${SYSTEM_SITE_NAME}-${SYSTEM_SITE_DESC}</title>
    <meta name="keywords" content="${SYSTEM_SITE_NAME},${SYSTEM_SITE_DESC}">
    <meta name="description" content="${SYSTEM_SITE_NAME}-${SYSTEM_SITE_DESC}">
    <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width">
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-title" content="${SYSTEM_SITE_NAME}-价格查询系统">
    <meta name="apple-mobile-web-app-status-bar-style" content="default">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="msapplication-TileColor" content="#007AFF">
    <link rel="shortcut icon" href="/static/favicon.ico" type="image/x-icon">
    <c:if test="${ WEB_CACHE_CONTROL eq '1'}">
	<meta http-equiv="expires" content="0">  
	<meta http-equiv="pragma" content="no-cache">  
	<meta http-equiv="cache-control" content="no-cache"> 
	</c:if>
    
    <link rel="stylesheet" type="text/css" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/semantic.css">
    <link rel="stylesheet" href="/static/css/layer.css">
    <link rel="stylesheet" href="/static/css/sliders.css">

    <script type="text/javascript" src="/static/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="/static/js/common.js"></script>
	<script type="text/javascript" src="/static/js/page.js"></script>
	<script type="text/javascript" src="/static/js/layer.js"></script>
	<script type="text/javascript" src="/static/js/sliders.js"></script>
	
    <style type="text/css">
    
.reach{width:96%;margin:0 auto}
.reach-txt{font-size:1.1rem;color:#9a9a9a;height:30px;line-height:30px;margin-left:14px}
.reach-txt span{padding-right:.5rem;cursor:pointer}
.reach-txt span.hover{color:#e4393c;font-weight:700}
.reach-box{width:100%;height:50px;position:relative;border-right:none}
.reach-box .r_soo .r_so{position:absolute;width:100%;background:#fff;border:2px solid #e4393c;top:38px;padding:10px;z-index:11}
.reach-box .r_soo .r_so .history_title{color:#b4b4b4;font-size:14px;height:18px;line-height:18px;margin:7px 0 8px;overflow:hidden}
.reach-box .r_soo .r_so .history_title i{width:17px;height:18px;float:left;padding-right:3px}
.reach-box .r_soo .r_so .history_word{margin-top:12px;overflow:hidden;line-height:25px;width:100%}
.reach-box .r_soo .r_so .history_word .b{background:#f6f6f6;border:1px solid #e5e5e5;margin:0 10px 10px 0;float:left;color:#e4393c;padding:0 7px;font-size:12px}
.reach-box .r_soo .r_so .history_word a:hover{background:#e4393c;color:#fff}
#page{margin-bottom:2.6rem}
.reach-box .r_soo .r_so .hotSearch_tt{clear:both;color:#b4b4b4;font-size:14px;height:18px;line-height:18px;margin-top:7px}
.reach-box .r_soo .r_so .hotSearch_tt i{width:17px;height:18px;float:left;padding-right:3px}
.reach-box .r_soo .r_so .hotSearch_word{margin-top:12px}
.reach-box .r_soo .r_so .hotSearch_word div{height:28px;line-height:28px;padding:1px 8px 1px 8px;clear:both;overflow:hidden;cursor:pointer}
.reach-box .r_soo .r_so .hotSearch_word div span{float:right;font-size:14px}
.reach-box .r_soo .r_so .hotSearch_word label{display:block;width:100%;background:#fafafa;font-size:14px;color:#00c;text-align:right}
.reach-box .r_soo{width:100%}
.reach-text{float:left;margin:2px 0 2px 2px;width:70%;height:44px;
line-height:36px;background:#fff;text-indent:10px;border:1px solid #e4393c;border-radius:0}
.reach-btn{float:left;margin-top:2px;width:29%;height:44px;border:0;color:#fff;background:#e4393c;
text-align:center;font-size:1.1rem;cursor:pointer;overflow:hidden;text-align:text-align: center}

.search-link-list{float:left;text-align:center;margin:.4rem auto;margin-left:0}
.search-link-list a{font-size:13px;color:#9a9a9a}
.topic-main-container{height:auto;float:left;margin-bottom:10px}
.topic-main-container .lii{font-size:14px;color:#fc2428}
.topic-main-container ul li{float: left;font-size: 14px;padding-left: 1.2rem;color: #909090;}
.topic-main-container .lii1{margin: 7px 5px;}.topic1{margin-left:.3rem}

.pc{display: block;}
.sj{display: none;}
.top-banner-container{width:100%;position:relative;z-index:90;background:#fff;height:100px;overflow:hidden}

.top-banner-container .x_float_icon .icon-zwd{font-size:14px;cursor:pointer}

.top-banner-container .clear{height:1px;_width:100%;_overflow:hidden}

.top-banner-container .banner-item{text-align:center;overflow:hidden;height:100px;margin:0 auto}

.top-banner-container .xxx-banner-item-container{margin:0 auto;position:relative;z-index:2;text-align:center}

.top-banner-container .getBoxShadow{box-shadow:0 0 10px #888}

.top-banner-container .top-xxx-container{height:100px;position:relative;z-index:2;margin:0 auto}

.top-banner-container .top-xxx-container,.top-banner-container .x-banner-list{position:relative;z-index:2;margin:0 auto}

.top-banner-container .x2{right:-22px;box-sizing:border-box;width:20px;height:20px}

.top-banner-container img{
    max-height: 100px;
    max-width: 100%;
    min-width: 100%;}

.contentBody {
    max-width: 800px;
    margin: 0 auto;
    }
    
.about{
font-size:12px;
margin-top: 8px;
}
.about a{
 margin-left:10px;
 margin-right: 10px;
}
#proclamations{

white-space: nowrap;
width:95%;
margin:0 auto;
margin-top:5px;
overflow: hidden;
cursor: pointer;
}
#proclamations .pro_item{
line-height: 25px;
}
</style>

<body>


  


 <div class="contentBody">

    
	
	<div id="logo" class="noTapColor use-festival">
            <a href="/"><img src="/static/images/logo.png" alt=""></a>
            <div class="desc">—— 全球美妆3C数码批发市场报价平台 ——</div>
     </div>	
    <div id="page">
        <div class="reach">
            <div class="trend-search" style="margin-bottom:0px;">
            <form id="destoon_search" action="/search" onsubmit="return Dsearch(1);">
                <input type="hidden" name="_m" value="diy/search" id="q_type">
                <div class="reach-box">
                    <div class="r_soo">
                        <input name="kw" id="destoon_kw" type="text" class="reach-text" value="" placeholder="请输入产品关键词" 
                        onfocus="if(this.value==&#39;请输入产品关键词&#39;) this.value=&#39;&#39;;" 
                        autocomplete="off" x-webkit-speech="" speech="" style="border-radius: 10px 0 0 10px;">
                    </div>
                    <button type="submit" value="搜 索" class="reach-btn"  style="border-radius: 0 10px 10px 0;">小猿找货</button>
                </div>
            </form></div>
		<div id="search_list" style="margin-top: -20px;display: none;" class="ui vertical pointing menu"></div>
            <div class="search-link-list">
            <c:forEach var="i" items="${keys}">
			   <a href="/search?kw=${i}"> <c:out value="${i}"/></a>&nbsp;
			</c:forEach>
			</div>




        </div>
    </div>
    
    <c:choose>
	<c:when test="${ adStatus eq '0' }">
 	 
	
	<div class="sliders-wraper" id="rotation-1">
	<ul class="sliders clear">
		<c:if test="${last_adstation != null}">
		<li class="slider" style="background:purple">
		<c:choose>
		<c:when test="${last_adstation.clickResult eq 1}">
			<a href="/h5page/${last_adstation.h5Id}"><img src="/getFile/ad/${last_adstation.adImg}/" style="width: 100%; height: 120px"/></a>
		</c:when>
		 <c:otherwise>
		 	<img src="/getFile/ad/${last_adstation.adImg}/" style="width: 100%; height: 120px"/>
		 </c:otherwise>
		</c:choose>
		</li>
		</c:if>
		<c:forEach var="item" items="${adstations}">
			<li class="slider" style="background:pink">
			<c:choose>
			<c:when test="${item.clickResult eq 1}">
				<a href="/h5page/${item.h5Id}"><img src="/getFile/ad/${item.adImg}/" style="width: 100%; height: 120px"/></a>
			</c:when>
			 <c:otherwise>
				<img src="/getFile/ad/${item.adImg}/" style="width: 100%; height: 120px"/>
			 </c:otherwise>
			</c:choose>
			</li>
		</c:forEach>
		<c:if test="${first_adstation != null}">
			<li class="slider" style="background:pink">
			<c:choose>
			<c:when test="${first_adstation.clickResult eq 1}">
				<a href="/h5page/${first_adstation.h5Id}"><img src="/getFile/ad/${first_adstation.adImg}/" style="width: 100%; height: 120px"/></a>
			</c:when>
			 <c:otherwise>
			 	<img src="/getFile/ad/${first_adstation.adImg}/" style="width: 100%; height: 120px"/>
			 </c:otherwise>
			</c:choose>
			</li>
		</c:if>
		
	</ul>
	<!-- 焦点 -->
	<div class="pagenation" style="display: none">
		<div class="page page-active"><a></a></div>
		<div class="page"><a></a></div>
		<div class="page"><a></a></div>
		<div class="page"><a></a></div>
		<div class="page"><a></a></div>
	</div>
	<!-- 左右 -->
    <span class='prev rotation-btn'><</span>
 	<span class='next rotation-btn'>></span>
	</div>
	
</c:when>
 <c:otherwise>
 </c:otherwise>
</c:choose>
    
    <c:choose>
	<c:when test="${ proclStatus eq '0' }">
		
		
		  <div id="proclamations">
		   <c:forEach var="item" items="${proclamations}">
		   		<c:choose>
					<c:when test="${item.clickResult eq 1}">
					<div class="pro_item"><a href="/h5page/${item.h5Id}">${item.text}</a></div>
					</c:when>
			 		<c:otherwise>
			 		<div class="pro_item"><span>${item.text}</span></div>
			 		</c:otherwise>
				</c:choose>
			</c:forEach>
		  </div> 
	
		<%-- <style type="text/css">
		.pad_right{ padding-right:2.0em;}
		#scroll_div {height:26px;overflow: hidden;white-space: nowrap;width:95%;margin:0 auto; line-height: 26px}
		#scroll_begin,#scroll_end {display: inline;}
		</style>
		
		<div id="scroll_div" class="fl"> 
		  <div id="scroll_begin">
		   <c:forEach var="item" items="${proclamations}">
		   		<c:choose>
					<c:when test="${item.clickResult eq 1}">
					<a href="/h5page/${item.h5Id}" class="scroll_item">${item.text}</a>
					</c:when>
			 		<c:otherwise>
			 		<a href="#" class="scroll_item">${item.text}</a>
			 		</c:otherwise>
				</c:choose>
			</c:forEach>
		  </div> 
		  <div id="scroll_end"></div>
		 </div> 
		 
		<script type="text/javascript">
		 //文字滚动
		function ScrollTextLeft(){ 
		 var speed=30;
		  var MyMar = null;
		 var scroll_begin = document.getElementById("scroll_begin"); 
		 var scroll_end = document.getElementById("scroll_end"); 
		 var scroll_div = document.getElementById("scroll_div"); 
		 scroll_end.innerHTML=scroll_begin.innerHTML; 
		 function Marquee(){ 
		  if(scroll_end.offsetWidth-scroll_div.scrollLeft<=0) 
		   scroll_div.scrollLeft-=scroll_begin.offsetWidth; 
		  else
		   scroll_div.scrollLeft++; 
		  } 
		  MyMar=setInterval(Marquee,speed); 
		  scroll_div.onmouseover = function(){
		　　　　　　　clearInterval(MyMar);
		　　　　　}
		 　　　　scroll_div.onmouseout = function(){
		　　　　　　　MyMar = setInterval(Marquee,speed); 　　　　
		　　　　　}  
		}
		setTimeout(function() {
			ScrollTextLeft();
		}, 1000);
		
		</script> --%>
	   
    </c:when>
	</c:choose>
    
    <!--p3-->
    <div class="tool-container">
        <div class="tool-header" style="display: none">
            <div style="display: none">常用功能</div>
            <div></div>
        </div>
        <div class="tool-content">
            
            <div class="tool-list">
           		<div class="tool-item">
                    <a href="/marketClass">
                        <img src="/static/images/home/shichang.png" alt="">
                        <p>市场</p>
                    </a>
                </div>
                <div class="tool-item">
                    <a href="http://www.hengtaizb.com/Mobile/index.html">
                        <img src="/static/images/home/hangqingzoushi.png" alt="">
                        <p>行情走势</p>
                    </a>
                </div>
                <div class="tool-item">
                    <a href="http://www.baojiayuan.com">
                        <img src="/static/images/home/baojiayuan.png" alt="">
                        <p>报价猿</p>
                    </a>
                </div>
               
                <div class="tool-item">
                    <a href="/categoryClass">
                        <img src="/static/images/home/chanpinku.png" alt="">
                        <p>产品库</p>
                    </a>
                </div>
                <div class="tool-item">
                    <a href="/identifyInfo">
                        <img src="/static/images/home/jianbie.png" alt="">
                        <p>鉴别</p>
                    </a>
                </div>
                
                
            </div>
            <div class="tool-list">
                <%-- <div class="tool-item">
                    <a href="${wuliuUrl}">
                        <img src="/static/images/home/kuajingwuliu.png" alt="">
                        <p>跨境物流</p>
                    </a>
                </div> --%>
                <div class="tool-item">
                    <a href="/getFile/yuanbaojia.apk">
                        <img src="/static/images/home/yuanbaojia_app.png" alt="">
                        <p>安卓APP</p>
                    </a>
                </div>
                <div class="tool-item">
                    <a href="${daifaUrl}">
                        <img src="/static/images/home/paotuidaifa.png" alt="">
                        <p>跑腿代发</p>
                    </a>
                </div>
               <div class="tool-item">
                    <a href="${miaohuanUrl}" target="_blank">
                        <img src="/static/images/home/miaohuanxia.png" alt="">
                        <p>秒换侠</p>
                    </a>
                </div>
               
                <div class="tool-item">
                    <a href="${recycleUrl}">
                        <img src="/static/images/home/huishouyuan.png" alt="">
                        <p>回收猿</p>
                    </a>
                </div>
                
                <div class="tool-item">
                    <a href="${merchantUrl}">
                        <img src="/static/images/home/shangjiaqun.png" alt="">
                        <p>商家群</p>
                    </a>
                </div>
                
                
               
                
            </div>
        </div>
    </div>
<c:choose>
<c:when test="${ recommend_gate != null }">
    <div class="tool-content">
        <ul class="tool-list">
            <li class="lii"><img src="/static/images/pinpai.png" style="vertical-align: text-bottom;">${HOME_TIP1_TITLE}</li>
            <br style="clear:both;">
            <c:forEach var="gate" items="${recommend_gate}">
				<li class="tool-item">
				<a href="/dangkou_goods?gate=${gate.uid}&random=${gate.random}">
				<img src="/getFile/logo/${gate.logoImg}/">
				<p>${gate.storeName}</p>
				</a>
				</li>
			</c:forEach>
				        
			
		</ul>
     </div>
     
   </c:when>                 
 </c:choose>
 
 
 <div class="tool-content" style="display: none">
        <ul class="tool-list">
            <li class="lii"><img src="/static/images/partner.png" style="vertical-align: text-bottom;">合作伙伴</li>
            <br style="clear:both;">
            
			<li class="tool-item">
				<a href="https://mall.jd.hk/index-612497.html" target="_blank">
				<img src="/static/images/partner/youxinmeizhuang.png">
				<p>友欣美妆</p>
				</a>
			</li>
			<li class="tool-item">
				<a href="https://sjsm.tmall.com/" target="_blank">
				<img src="/static/images/partner/sjsmtmall.png">
				<p>三际数码</p>
				</a>
			</li>
			<li class="tool-item">
				<a href="https://mall.jd.com/index-755598.html" target="_blank">
				<img src="/static/images/partner/shenglishuma.png">
				<p>盛力数码</p>
				</a>
			</li>	        
			<li class="tool-item">
				<a href="https://mall.jd.com/index-628361.html" target="_blank">
				<img src="/static/images/partner/kezushuma.png">
				<p>科族数码</p>
				</a>
			</li>
			<li class="tool-item">
				<a href="http://www.hengtaizb.com/" target="_blank">
				<img src="/static/images/partner/hengtaizb.png">
				<p>恒泰珠宝</p>
				</a>
			</li>
			<li class="tool-item">
				<a href="https://www.9ji.com/" target="_blank">
				<img src="/static/images/partner/9ji.png">
				<p>九机网</p>
				</a>
			</li>
			<li class="tool-item">
				<a href="https://www.gearbest.com/" target="_blank">
				<img src="/static/images/partner/gearbest.png">
				<p>环球易购</p>
				</a>
			</li>
			<li class="tool-item">
				<a href="https://www.banggood.com/" target="_blank">
				<img src="/static/images/partner/banggood.png">
				<p>棒谷</p>
				</a>
			</li>
			<li class="tool-item">
				<a href="https://www.eraspace.com/erafone" target="_blank">
				<img src="/static/images/partner/Erafone.png">
				<p>Erafone</p>
				</a>
			</li>
			<li class="tool-item">
				<a href="http://www.szchdx.cn/" target="_blank">
				<img src="/static/images/partner/szchdx.png">
				<p>长宏</p>
				</a>
			</li>
		</ul>
  </div>
 
    
    <div style="height: 10px"></div>
    
    <div style="text-align: center;">
      <img alt="" src="/static/images/home_wx.png/" style="width: 95%">
      <div>
         	关注“猿报价”公众号，置顶使用更方便
      </div>
      <!-- 
      <div class="about">
        <a id="ab" href="/about">关于我们</a>
        <a id="bc" href="/business">商务合作</a>
        <a id="ts" href="/service">服务条款</a>
        <a id="ln" href="/law">法律声明</a>
      </div> -->
      
      <div class="about">
        <a id="ab" href="${aboutUrl}">关于我们</a>
        <a id="bc" href="${businessUrl}">商务合作</a>
        <a id="ts" href="${serviceUrl}">服务条款</a>
        <a id="ln" href="${lawUrl}">法律声明</a>
      </div>
      
      <div style="margin-top: 8px; font-size: 12px">
	      <a data-v-668ccbf5="" href="https://beian.miit.gov.cn" target="_blank" class="record__box-url">粤ICP备19076853号</a>
	      <a data-v-668ccbf5="" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=44030402004051" 
	      target="_blank" class="record__box-url"><img src="/static/images/security.png">粤公网安备 44030402004051号</a>
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

    <script type="text/javascript">
        jQuery(function($) {
        
        	$('.pro_item span').bind("click",function(){
		
				var text = $(this).text();
				
				 layer.open({
					    content: text
					    ,btn: '关闭'
					});
				
			})
        	
            $(".reach-txt span").click(function() {
                $(this).addClass('hover').siblings().removeClass('hover');
            });


            $(window).bind("scroll", function() {
                initScroll()
            });
            var $fixnav = $('#fix-nav');

            function initScroll() {
                if ($(window).scrollTop() > $(window).height() / 4) {
                    $fixnav.show();
                } else {
                    $fixnav.hide();
                }
            }
            $('.fix-nav-wrap').bind('click', function(e) {
                $(window).scrollTop(0);
            });
        });

        function copy(val) {
            let transfer = document.createElement('input');
            document.body.appendChild(transfer);
            transfer.value = val; // 这里表示想要复制的内容
            transfer.focus();
            transfer.select();
            if (document.execCommand('copy')) {
                document.execCommand('copy');
            }
            transfer.blur();
            alert('复制成功');
            document.body.removeChild(transfer);

        }
        
        //adIMG sliders
        var r2 = rotation({
        	el: 'rotation-1',
        	mode: 'easy-in-out', //运动曲线
        	auto: true,//开启自动轮播
        	btn: true, //左右按钮
        	focusBtn: false//焦点
        })
        window.onresize = function(){
        	r2 && r2.refresh()
        }
    </script>
<script src="/static/js/keyword.js"></script>



<script>
$('body').click(function () {
    $('#search_list').css('display','none');
});

//绑定变更事件
/**
var oldSearchContent = '';
$('#destoon_kw').keyup(function () {
    //搜索关键字
    var newSearchContent = $('#destoon_kw').val();
    if(newSearchContent!=oldSearchContent){
        searchName(newSearchContent);
        oldSearchContent =  newSearchContent;
    }
})
**/

/**
 * 搜过关键字
 * @param content
 */
function searchName(content){
    if(searchList.length == 0) return;
    var html = '';
    $('#search_list').css('display','block');
    $('#search_list').html('');
    //查询
    var newSearchList = []
    var sear=new RegExp(content);
    searchList.forEach(function (v) {
        if(sear.test(v['keyword'])){
             newSearchList.push(v['keyword'])
        }
    })
    for(var i=0 ; i<newSearchList.length;i++){
        html+= '<a  class="item" onclick="searchgoods(\''+newSearchList[i]+'\')">'+newSearchList[i]+' </a>'
    }
    $('#search_list').html(html);
}

function searchgoods(key){
    window.location.href = '/search?kw='+ key;
}
</script>

<script src="/static/js/mod.js"></script>
<script type="text/javascript" src="/static/js/common_layout.js"></script>



<script type="text/javascript">
  $('#localCount').html('+' + (window.localStorage.getItem("localCount") || 0));
</script>

 </div>

</body></html>