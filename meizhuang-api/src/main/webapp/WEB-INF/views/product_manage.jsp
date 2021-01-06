<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>商品报价管理_${SYSTEM_SITE_NAME}</title>
<meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width">
<meta http-equiv="Cache-Control" content="no-siteapp">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-title" content="商品报价管理_${SYSTEM_SITE_NAME}">
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

<link rel="stylesheet" href="/static/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="/static/layuiadmin/style/admin.css" media="all">


<script type="text/javascript" src="/static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/static/js/layer.js"></script>
<script src="/static/layuiadmin/layui/layui.js"></script>


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
<div class="head-bar-title">商品报价管理</div>

</div>
</div>

<div class="test-table-reload-btn" style="margin-bottom: 6px; margin-top: 6px;display: none">
		<form class="layui-form" action="" lay-filter="search">
								<div class="layui-inline layui-form-item">
									<label class="layui-form-label">创建时间</label>
									<div class="layui-input-inline">
										<input type="text" class="layui-input" id="ctime" name="sctime"
											placeholder="yyyy-MM-dd" lay-key="1">
									</div>
								</div>
								<button class="layui-btn layui-form-item margin-l-10"
									lay-submit="" lay-filter="search">搜索</button>
		 </form>
		</div>

<table class="layui-hide" id="test-table-totalRow" lay-filter="test-table-totalRow"></table>
<script type="text/html" id="test-table-totalRow-toolbarDemo">
	<div class="layui-btn-container">
		<button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="batchDel">批量删除</button>
	</div>							
</script>

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

<script>
			layui.config({
				base: '/static/layuiadmin/' //静态资源所在路径
			}).extend({
				index: 'lib/index' //主入口模块
			}).use(['index', 'table', 'laydate', 'form'], function() {
				var $ = layui.jquery;
				var table = layui.table;
				
				//表单重新渲染
				function renderForm() {
					layui.use('form', function() {
						var form = layui.form;
						form.render();
					});
				}
				
				table.render({
					elem: '#test-table-totalRow',
					toolbar:'#test-table-totalRow-toolbarDemo',
					autoSort: false,
					url: '/productManageList',
					method: 'post',
					title: '报价管理',
					totalRow: true,
					initSort: {field:'ctime', type:'desc'},
					where: {sortField:'ctime', sortType:'desc'},
					cols: [[
						  {type:'checkbox',fixed:'left'},
						  {field:'offerProduct', title:'商品报价', width:'280',align:'center'},
						  {field:'ctime', title:'创建时间', width:'170',align:'center',sort:true},
						 /*  {field:'mtime', title:'更新时间', width:'170',align:'center',sort:true}, */
						  
				          
				    ]],
					page: true,
					limits: [20,30],
					limit: 20
				});

				table.on('sort(test-table-totalRow)', function(obj) {
					table.reload('test-table-totalRow', {
						initSort : obj,
						where : {
							sortField : obj.field,
							sortType : obj.type
						}
					});
				});
				
				//监听提交
				var form = layui.form;
				form.on('submit(search)', function(data) {
					//layer.alert(JSON.stringify(data.field), {title: '最终的提交信息'})
					//执行重载
					table.reload('test-table-totalRow', {
						page : {
							curr : 1
						//重新从第 1 页开始
						},
						where : data.field
					});
					return false;
				});

				//日期时间选择器
				var laydate = layui.laydate;
				laydate.render({
					elem : '#ctime',
					range: '~',
					type: 'datetime'
				});


				//头工具栏事件
			    table.on('toolbar(test-table-totalRow)', function(obj){
			      var checkStatus = table.checkStatus(obj.config.id);
			      var data = checkStatus.data;
			      switch(obj.event){
			       
			        case 'batchDel':
			        	if(data.length < 1) {
								layer.msg('至少选择一项');
								return;
							}
							//var uids = "-1";
							var ids = "-1";
							for(var i = 0; i < data.length; i++) {
								//uids.push(data[i].uid);
								ids += ","+data[i].id;
							}
							
							layer.confirm('请确认选择的商户报价删除?', function(index){
								var jsonD = {
										ids:ids
								}
								$.post('/batchDel', jsonD,
										function(res) {
											var data = res;
											if (data.success) {
												layer.closeAll();
												layer.msg("操作成功", {
													icon : 6
												});
												table.reload('test-table-totalRow');
											} else {
												layer.msg(data.msg, {
													icon : 5
												});
											}
								});
								layer.close(index);
								return false;
							});
							
			        break;
			        
			       
			      };
			    });
				
			  
				//修改提交
				form.on('submit(updateMerchant)', function(data) {
					//layer.alert(JSON.stringify(data.field), {title: '最终的提交信息'});
					$.post('/flow/userInfo/updateStatus',
							data.field, function(res) {
								var data = res;
								if (data.success) {
									layer.closeAll();
									layer.msg("修改成功", {
										icon : 6
									});
									table.reload('test-table-totalRow');
								} else {
									layer.msg(data.msg, {
										icon : 5
									});
								}
							});
					return false;
				});
				
				
			  
				//关闭弹窗
				$('.closeBtn').click(function() {
					layer.closeAll();
				});
			});
</script>

</body></html>