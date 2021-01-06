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
   
%>

<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<title>排行榜_${SYSTEM_SITE_NAME}</title>
<meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width">
<meta http-equiv="Cache-Control" content="no-siteapp">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-title" content="排行榜_${SYSTEM_SITE_NAME}">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<meta name="mobile-web-app-capable" content="yes">
<link rel="shortcut icon" href="/static/favicon.ico" type="image/x-icon">
<c:if test="${WEB_CACHE_CONTROL eq '1'}">
	<meta http-equiv="expires" content="0">  
	<meta http-equiv="pragma" content="no-cache">  
	<meta http-equiv="cache-control" content="no-cache"> 
</c:if>
<link rel="stylesheet" type="text/css" href="/static/css/style.css">
<link rel="stylesheet" href="/static/css/semantic.css">
<link rel="stylesheet" href="/static/css/index.css">
<link rel="stylesheet" href="/static/css/layer.css">


<script type="text/javascript" src="/static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/static/js/layer.js"></script>



<style>

.contentBody {
    max-width: 800px;
    margin: 0 auto;
    }

@charset "utf-8";
* {
    word-break: break-all;
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", "Lucida Grande", Helvetica, Arial, Verdana, "Microsoft YaHei";
}

body {
    margin: 0;
    font-size: 14px;
    color: #333333;
    background: #ffffff;
    min-width: 320px;
    /* max-width: 640px; */
    margin: 0 auto;
}


/*-webkit-user-select:none;*/

ul li {
    list-style-type: none;
}

ol li {
    list-style-type: decimal;
}

ul,
form {
    margin: 0px;
    padding: 0px;
}

form {
    margin: 0px;
}

td {
    font-size: 14px;
}

img {
    border: none;
}

a:link,
a:visited,
a:active,
a:hover {
    color: #333333;
    text-decoration: none;
}

a.b:link,
a.b:visited,
a.b:active,
a.b:hover {
    text-decoration: none;
    color: #007AFF;
}

a,
button,
input {
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    -webkit-tap-highlight-color: transparent;
}

input,
textarea,
select {
    outline: none;
}.f_red {
    color: red;
}

.f_blue {
    color: blue;
}

.f_green {
    color: green;
}

.f_orange {
    color: #FF6600;
}

.f_gray {
    color: #666666;
}
.list-img {
    height: 84px;
    background: #FFFFFF;
    overflow: hidden;
    clear: both;
    border-bottom: #E5E5E5 1px solid;
    padding: 0 12px;
}

.list-img img {
    float: left;
    margin: 12px 12px 0 0;
}

.list-img ul {
    margin: 12px 0 0 0;
}

.list-img li {
    height: 30px;
    line-height: 30px;
    overflow: hidden;
}



.reach{width:90%;margin:20px auto}
.reach-txt{font-size:1.1rem;color:#9a9a9a;height:30px;line-height:30px;margin-left:14px}
.reach-txt span{padding-right:0.5rem;cursor:pointer}
.reach-txt span.hover{color:#e4393c;font-weight:700}
.reach-box{width: 100%;height: 40px;position: relative;border-right: none;}
.reach-box .r_soo .r_so{position:absolute;width:100%;background:#ffffff;border:2px solid #e4393c;top:38px;padding:10px;z-index:11}
.reach-box .r_soo .r_so .history_title{color:#b4b4b4;font-size:14px;height:18px;line-height:18px;margin:7px 0 8px;overflow:hidden}
.reach-box .r_soo .r_so .history_title i{background:url(img/linkbg.png) no-repeat -0px -0px;width:17px;height:18px;float:left;padding-right:3px}
.reach-box .r_soo .r_so .history_word{margin-top:12px;overflow:hidden;line-height:25px;width:100%}
.reach-box .r_soo .r_so .history_word .b{background:#f6f6f6;border:1px solid #e5e5e5;margin:0 10px 10px 0;float:left;color:#e4393c;padding:0 7px;font-size:12px}
.reach-box .r_soo .r_so .history_word a:hover{background:#e4393c;color:#fff}
#page{margin-bottom:2.5rem}
.reach-box .r_soo .r_so .hotSearch_tt{clear:both;color:#b4b4b4;font-size:14px;height:18px;line-height:18px;margin-top:7px}
.reach-box .r_soo .r_so .hotSearch_tt i{background:url(img/linkbg.png) no-repeat -25px -0px;width:17px;height:18px;float:left;padding-right:3px}
.reach-box .r_soo .r_so .hotSearch_word{margin-top:12px}
.reach-box .r_soo .r_so .hotSearch_word div{height:28px;line-height:28px;padding:1px 8px 1px 8px;clear:both;overflow:hidden;cursor:pointer}
.reach-box .r_soo .r_so .hotSearch_word div span{float:right;font-size:14px}
.reach-box .r_soo .r_so .hotSearch_word label{display:block;width:100%;background:#FAFAFA;font-size:14px;color:#0000CC;text-align:right}
.reach-box .r_soo{width:100%;_overflow:hidden;_float:left}
.reach-text{float:left;margin:2px 0 2px 2px;width:70%;height:44px;line-height:46px;background:#fff;text-indent:10px;
border: 1px solid #e4393c;border-radius: 0;}
.reach-btn{float:left;margin-top: 2px;width: 23%;height:44px;border:0;color:#fff;
background:#e4393c;text-align:center;font-size:1.1rem;cursor:pointer;overflow:hidden;text-align: text-align: center;}
#logo{height:57px;padding-bottom:30px!important;text-align:center;margin-top:3.3rem}
#logo.use-festival img{max-width:217px;height:100%}
.search-link-list{float:left;text-align:center;margin:0.9rem auto;margin-left:0}
.search-link-list a{font-size:14px;color:#9a9a9a}
</style>
</head>
<body>
  <div class="contentBody">
	<div id="head-bar">
		<div class="head-bar">
			<div class="head-bar-back">
			<a href="javascript:;" onclick="javascript:history.go(-1);"><img src="/static/images/icon-back.png" width="24" height="24"></a>
			</div>
			<div class="head-bar-title">搜索结果</div>
		</div>
		
	</div>

<div class="reach">
	<div class="trend-search" style="margin-bottom:0px;">
	            <form id="destoon_search" action="/search" onsubmit="return Dsearch(1);">
	                <input type="hidden" name="_m" value="diy/search" id="q_type">
	                <div class="reach-box">
	                    <div class="r_soo">
	                        <input name="kw" id="destoon_kw" type="text" class="reach-text" value="${kw}" placeholder="请输入产品关键词" onfocus="if(this.value=='请输入产品关键词') this.value='';" autocomplete="off" x-webkit-speech="" speech="" style="border-radius: 10px 0 0 10px;">
	                    </div>
	                    <button type="submit" value="搜&nbsp;索" class="reach-btn" style="border-radius: 0 10px 10px 0;">搜索</button>
	                </div>
	            </form></div>
	<div id="search_list" style="margin-top: -20px;display: none;" class="ui vertical pointing menu"></div>
	            <div class="search-link-list">
				 <c:forEach var="i" items="${keys}">
			   		<a href="/search?kw=${i}"> <c:out value="${i}"/></a>&nbsp;
				</c:forEach>
			</div>
</div>

<div class="search_result">
	<table id="data_table" cellspacing="0" style="display: table;">
            <thead class="to_top">
                <tr>
                    <th class="date">日期</th>
                    <th class="shop">商家</th>
                    <th class="msg">产品信息+报价</th>
                    <th class="call">咨询</th>
                </tr>
            </thead>
            <tbody id="data_list">
    
       <c:forEach var="group" items="${productGroupList}">
       		<tr class="goods bg_pro" data-goods_type="1" data-gid="11901" data-sid="99708">
	            <td class="date" rowspan="2">
	                <div>08-22 15:42</div>
	                <!-- <div><a class="ui red label">优选</a></div> -->
	            </td>
	            <td class="shop">
	                <div class="name">${group.tradeStall}</div>
	            </td>
	            <td class="msg">
	            <c:forEach var="product" items="${group.productList}">                            
	               ${product.offerProduct}<br>
	            </c:forEach>
	            </td>
	            <td class="call">
	                <div>
	                       <button class="ui green button wx" data-msg="${group.weixin}" data-img="${group.weixinImg}">微信</button>
	                </div>
	                <div>
	                       <button class="ui red button phone" data-msg="${group.mobile}">呼叫</button>
	                </div>
	            </td>
        	</tr>
        	<tr class="bg_pro">
            <td class="data_action" colspan="4">
                <button class="ui basic green button btn_msg" data-uid="${group.uid}">留言</button>
                <button class="ui basic green button btn_req" data-uid="${group.uid}">投诉</button>
                <button class="ui red button btn_copy">复制</button>
            </td>
       	 </tr>
       </c:forEach>
    
        <tr class="goods bg_pro" data-goods_type="1" data-gid="11901" data-sid="99708" data-goods="美心双白莲月饼 193">
            <td class="date" rowspan="2">
                <div>08-22 15:42</div>
                <!-- <div><a class="ui red label">优选</a></div> -->
            </td>
            <td class="shop">
                <div class="name">曼哈mymymy H1172</div>
            </td>
            <td class="msg">美心双白莲月饼 193</td>
            <td class="call">
                <div>
                       <button class="ui green button wx" data-msg="ykjpeihuo">微信</button>
                </div>
                <div>
                       <button class="ui red button phone" data-msg="13928475714">呼叫</button>
                </div>
            </td>
        </tr>
        <tr class="bg_pro">
            <td class="data_action" colspan="4">
                <button class="ui basic green button btn_msg">留言</button>
                <button class="ui basic green button btn_req">投诉</button>
                <button class="ui red button btn_copy">复制</button>
            </td>
        </tr>
    
        <tr class="goods bg_pro" data-goods_type="1" data-gid="11874" data-sid="116230" data-goods="保儿3+；191  流心月饼港版239箱起238  双黄双白月饼：193">
            <td class="date" rowspan="2">
                <div>08-22 14:33</div>
                <!-- <div><a class="ui red label">优选</a></div> -->
            </td>
            <td class="shop">
                <div class="name">紫荆城一楼A116</div>
            </td>
            <td class="msg">保儿3+；191  流心月饼港版239箱起238  双黄双白月饼：193</td>
            <td class="call">
                <div>
                    
                        <button class="ui green button wx" data-msg="10075369611">微信</button>
                    
                </div>
                <div>
                    
                        
                            <button class="ui red button phone" data-msg="10075369611">呼叫</button>
                        
                    
                </div>
            </td>
        </tr>
        <tr class="bg_pro">
            <td class="data_action" colspan="4">
                <button class="ui basic green button btn_msg">留言</button>
                <button class="ui basic green button btn_req">投诉</button>
                <button class="ui red button btn_copy">复制</button>
            </td>
        </tr>
    
    
        <tr class="goods bg_pro" data-gid="4948102" data-sid="114459" data-goods="香港半岛奶黄月饼 323">
            <td class="date" rowspan="2">08-23 11:29</td>
            <td class="shop">
                <div class="name">芭锦国际<br>加微信</div>
            </td>
            <td class="msg">香港半岛奶黄月饼 323</td>
            <td class="call">
                <div>
                    
                        <button class="ui green button wx" data-msg="syjhk888">微信</button>
                    
                </div>
                <div>
                    
                        
                            <button class="ui red button phone" data-msg="13714010719">呼叫</button>
                        
                    
                </div>
            </td>
        </tr>
        <tr class="bg_pro">
            <td class="data_action" colspan="4">
                <button class="ui basic green button btn_msg">留言</button>
                <button class="ui basic green button btn_req">投诉</button>
                <button class="ui red button btn_copy">复制</button>
            </td>
        </tr>
    
        <tr class="goods" data-gid="5267698" data-sid="98458" data-goods="美心流心月饼港版240">
            <td class="date" rowspan="2">08-22 14:08</td>
            <td class="shop">
                <div class="name">和泰美妆 曼哈H1176（1096隔壁）<br>曼哈A座 和泰美妆（H1112对面） H1176</div>
            </td>
            <td class="msg">美心流心月饼港版240</td>
            <td class="call">
                <div>
                    
                        <button class="ui green button wx" data-msg="coobo2013">微信</button>
                    
                </div>
                <div>
                    
                        
                            <button class="ui red button phone" data-msg="18926041139">呼叫</button>
                        
                    
                </div>
            </td>
        </tr>
        <tr>
            <td class="data_action" colspan="4">
                <button class="ui basic green button btn_msg">留言</button>
                <button class="ui basic green button btn_req">投诉</button>
                <button class="ui red button btn_copy">复制</button>
            </td>
        </tr>
    
        <tr class="goods bg_tr" data-gid="5264840" data-sid="116422" data-goods="迪士尼快乐欢聚月饼礼盒 82">
            <td class="date" rowspan="2">08-22 12:28</td>
            <td class="shop">
                <div class="name">雄泰商行B4058<br>雄泰B4058</div>
            </td>
            <td class="msg">迪士尼快乐欢聚月饼礼盒 82</td>
            <td class="call">
                <div>
                    
                        <button class="ui green button wx" data-msg="wxid_04wll7zh1toh22">微信</button>
                    
                </div>
                <div>
                    
                        
                            <button class="ui red button phone" data-msg="17727851398">呼叫</button>
                        
                    
                </div>
            </td>
        </tr>
        <tr class="bg_tr">
            <td class="data_action" colspan="4">
                <button class="ui basic green button btn_msg">留言</button>
                <button class="ui basic green button btn_req">投诉</button>
                <button class="ui red button btn_copy">复制</button>
            </td>
        </tr>
    
        <tr class="goods" data-gid="5280233" data-sid="97819" data-goods="迪士尼240g快乐欢聚月饼礼盒 红色 6个装 82">
            <td class="date" rowspan="2">08-22 16:03</td>
            <td class="shop">
                <div class="name">龙兴达<br></div>
            </td>
            <td class="msg">迪士尼240g快乐欢聚月饼礼盒 红色 6个装 82</td>
            <td class="call">
                <div>
                    
                        <button class="ui green button wx" data-msg="13714220438">微信</button>
                    
                </div>
                <div>
                    
                        
                            <button class="ui red button phone" data-msg="13590444438">呼叫</button>
                        
                    
                </div>
            </td>
        </tr>
        <tr>
            <td class="data_action" colspan="4">
                <button class="ui basic green button btn_msg">留言</button>
                <button class="ui basic green button btn_req">投诉</button>
                <button class="ui red button btn_copy">复制</button>
            </td>
        </tr>
    
        <tr class="goods bg_tr" data-gid="5264843" data-sid="116422" data-goods="迪士尼快乐酷奇月饼礼盒 150">
            <td class="date" rowspan="2">08-22 12:28</td>
            <td class="shop">
                <div class="name">雄泰商行B4058<br>雄泰B4058</div>
            </td>
            <td class="msg">迪士尼快乐酷奇月饼礼盒 150</td>
            <td class="call">
                <div>
                    
                        <button class="ui green button wx" data-msg="wxid_04wll7zh1toh22">微信</button>
                    
                </div>
                <div>
                    
                        
                            <button class="ui red button phone" data-msg="17727851398">呼叫</button>
                        
                    
                </div>
            </td>
        </tr>
        <tr class="bg_tr">
            <td class="data_action" colspan="4">
                <button class="ui basic green button btn_msg">留言</button>
                <button class="ui basic green button btn_req">投诉</button>
                <button class="ui red button btn_copy">复制</button>
            </td>
        </tr>
    
        <tr class="goods" data-gid="5264845" data-sid="116422" data-goods="美心月饼礼盒 钢铁侠礼盒 漫威礼盒 整套出265 单出150">
            <td class="date" rowspan="2">08-22 12:28</td>
            <td class="shop">
                <div class="name">雄泰商行B4058<br>雄泰B4058</div>
            </td>
            <td class="msg">美心月饼礼盒 钢铁侠礼盒 漫威礼盒 整套出265 单出150</td>
            <td class="call">
                <div>
                    
                        <button class="ui green button wx" data-msg="wxid_04wll7zh1toh22">微信</button>
                    
                </div>
                <div>
                    
                        
                            <button class="ui red button phone" data-msg="17727851398">呼叫</button>
                        
                    
                </div>
            </td>
        </tr>
        <tr>
            <td class="data_action" colspan="4">
                <button class="ui basic green button btn_msg">留言</button>
                <button class="ui basic green button btn_req">投诉</button>
                <button class="ui red button btn_copy">复制</button>
            </td>
        </tr>
    
        
    
</tbody>
            </table>
</div>

<div class="list-img" style="height: auto;margin:25px; background:#fff;">
<ul>
<li style="line-height:30px;height:30px;text-align:center;color:#bbb;">我是有底线的</li>
</ul>
</div>




<div style=" height: 100px;"></div>
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
         
            <li class="navbar-item active">
                <a href="/userInfo">
                    <img src="/static/images/me_active.png" alt="">
                    <p>我</p>
                </a>
            </li>
        </ul>
</div>
</div>

<script src="/static/js/keyword.js"></script>

<script>

window.localStorage.setItem("mSearchKey", "复原蜜");

$('body').click(function () {
    $('#search_list').css('display','none');
});

//绑定变更事件
var oldSearchContent = '';
$('#destoon_kw').keyup(function () {
    //搜索关键字
    var newSearchContent = $('#destoon_kw').val();
    if(newSearchContent!=oldSearchContent){
        searchName(newSearchContent);
        oldSearchContent =  newSearchContent;
    }
})



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
  window.localStorage.setItem("mSearchKey", key);
  window.location.href = '/index.php?_m=diy%2Fsearch&kw='+ key;
}


</script>
  


<script type="text/javascript">
	
jQuery(function($){
	
	$('.wx').click(function(){
		
		var wxImg = $(this).attr("data-img");
		
		var wxOpen = "微信号:"+$(this).attr('data-msg')+
		"<br>长按二维码图片加好友";
		if(wxImg!='' && wxImg!=null){
			wxOpen=wxOpen+"<br>"+
			"<img src='/file/getImage/"+wxImg+"/"+"' width='220' heigth='220'/>";
		}
		
		data-img
		 layer.open({
			    content: wxOpen
			    ,btn: '关闭'
			});
		
	})
	
	$('.phone').click(function(){
		
		var phoneOpen = "<a href='tel:"+$(this).attr('data-msg')+"'>手机号:"+$(this).attr('data-msg')
		+" 点击手机号可呼叫</a>"
		 layer.open({
			    content: phoneOpen
			    ,btn: '关闭'
			});
		
	})
	
	$('.btn_msg').click(function(){
		
		var mc = "<textarea  placeholder='亲爱滴,给商户留言请带上你的联系方式' style='width:100%;height:100px' id='msg_leave'></textarea>";
		
		var thisBtn = $(this);

		layer.open({
			  title: [
			    '给商户留言',
			    'background-color:#8DCE16; color:#fff;'
			  ]
			  ,content: mc
			  ,btn: ['提交', '取消']
			  ,yes:function(index){
				  
				  var msgLeave = $("#msg_leave").val();
				  var dataId = $(thisBtn).attr('data-uid');
				  var destoon_kw = $("#destoon_kw").val();
				  
				  $.post("/leaveWord",{uid:dataId, leave_word:msgLeave,key:destoon_kw},function(res){
				  		var data = res;
						if(data.success) {
							//提示
						  layer.open({
						    content: '留言成功'
						    ,skin: 'msg'
						    ,time: 3 
						  });
						} else {
							layer.open({
						    content: data.msg
						    ,skin: 'msg'
						    ,time: 3 
						  });
						}
				  });
				  
				
				  layer.close(index)
				  
			  }
		      ,no:function(index){
		    	  
		      }
		});
	})
	
	$('.btn_req').click(function(){
		
		var mc = "<textarea  placeholder='亲爱滴,投诉商户请带上你的联系方式及投诉原因' style='width:100%;height:100px' id='msg_complaint'></textarea>";
		var thisBtn = $(this);
		layer.open({
			  title: [
			    '投诉商户',
			    'background-color:#0DCE16; color:#fff;'
			  ]
			  ,content: mc
			  ,btn: ['提交', '取消']
			  ,yes:function(index){
				  
				  var msg_complaint = $("#msg_complaint").val();
				  var dataId = $(thisBtn).attr('data-uid');
				  var destoon_kw = $("#destoon_kw").val();
				  
				  $.post("/complaint",{uid:dataId, complaint:msg_complaint,key:destoon_kw},function(res){
				  		var data = res;
						if(data.success) {
							//提示
						  layer.open({
						    content: '投诉已提交,系统正在审核'
						    ,skin: 'msg'
						    ,time: 3 
						  });
						} else {
							layer.open({
						    content: data.msg
						    ,skin: 'msg'
						    ,time: 3 
						  });
						}
				  });
				  
				
				  layer.close(index)
			  }
		      ,no:function(index){
		    	  
		      }
		});
	})
	
});
</script>


</body></html>