<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>服务条款_${SYSTEM_SITE_NAME}</title>
<meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width">
<meta http-equiv="Cache-Control" content="no-siteapp">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-title" content="服务条款_${SYSTEM_SITE_NAME}">
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
<div class="head-bar-title">服务条款</div>

</div>
<div class="head-bar-fix"></div>
</div>



<div style="width: 95%;margin: 0 auto;">
<p>
	<p class="MsoNormal">
		<span>欢迎</span>“您”（以下或称“用户”）与“基什通信服务（深圳）有限公司”（下称“基什通信服务”）共同签署本《猿报价服务条款》协议（下称“本协议”），本协议阐述之条款和条件适用于您使用猿报价基于互联网，以包含猿报价网站、客户端等在内的各种形态（包括未来技术发展出现的新的服务形态）向您提供的各项服务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>本协议为《猿报价服务条款》修订版本，自本协议发布之日起，猿报价网站各处所称</span>“猿报价服务条款”均指本协议。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		一、接受协议
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1.1 本协议内容包括协议正文及所有猿报价已经发布或将来可能发布并采取合理途径通知的各类规则。所有规则为协议不可分割的一部分，与协议正文具有同等法律效力。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1.2您在点击同意本协议之前，应当认真阅读本协议。请您务必审慎阅读、充分理解各条款内容，特别是免除或者限制责任的条款、法律适用和争议解决条款。免除或者限制责任的条款将以粗体下划线标识，您应重点阅读。如您对协议有任何疑问，可登录猿报价服务中心（网址为https://yuanbaojia.com）向猿报价客服咨询。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1.3当您按照注册页面提示填写信息、阅读并同意本协议且完成全部注册程序，或以任何方式进入猿报价网站并使用服务即表示您已充分阅读、理解并同意接受本协议的条款和条件。阅读本协议的过程中，如果您不同意本协议或其中任何条款约定，您应立即停止注册程序并停止使用服务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		二、定义
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2.1猿报价网站：指基什通信服务主办运营的面向企业间贸易 (B-TO-B)的在线信息发布，包含猿报价网站（所涉域名为 yuanbaojia.com，下同）及客户端。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2.2基什通信服务：指“基什通信服务（深圳）有限公司”，系猿报价网站的经营者。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2.3猿报价服务：指基什通信服务基于互联网，以包含猿报价网站、客户端等在内的各种形态（包括未来技术发展出现的新的服务形态）向您提供的各项服务（下称“服务”）。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2.4猿报价网站规则：指在猿报价网站规则频道（域名为yuanbaojia.com）内已发布及后续发布的全部规则、解读、公告等内容以及猿报价在论坛、帮助中心等渠道发布的各类规则、实施细则、产品流程说明、公告等。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2.5猿报价平台：指包括猿报价网站、报价猿（域名为 baojiayuan.com）等网站及客户端。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		三、协议范围
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		3.1签约主体
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		本协议由您与猿报价共同缔结，本协议对您与猿报价具有同等合同效力。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		3.2补充协议
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		由于互联网高速发展，您与猿报价签署的本协议列明的条款并不能完整罗列并覆盖您与猿报价所有权利与义务，现有的约定也不能保证完全符合未来发展的需求。因此，猿报价法律声明、隐私政策、猿报价网站规则均为本协议的补充协议，与本协议不可分割且具有同等法律效力。如您使用猿报价服务，视为您同意上述补充协议。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		四、账户注册与使用
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.1用户资格
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.1.1您确认，在您完成注册程序或以其他猿报价允许的方式实际使用服务时，您应当具备中华人民共和国法律规定的与您行为相适应的民事行为能力。若您不具备前述与您行为相适应的民事行为能力，则您及您的监护人应依照法律规定承担因此而导致的相应后果，且猿报价有权终止向您提供服务并注销您的账户。如您代表一家公司或其他法律主体在猿报价网站注册，则您声明和保证，您有权使该公司或该法律主体受本协议的约束。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.1.2此外，您还需确保您不是任何国家、国际组织或者地域实施的贸易限制、制裁或其他法律、规则限制的对象，否则您可能无法正常注册及使用服务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.2账户注册
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.2.1在您按照注册页面提示填写信息、阅读并同意本协议且完成全部注册程序后，或在您按照激活页面提示填写信息、阅读并同意本协议且完成全部激活程序后，或您以其他猿报价允许的方式实际使用猿报价服务时，您即受本协议的约束。您可以使用您提供或确认的邮箱、手机号码或者猿报价允许的其它方式作为登录手段进入猿报价网站。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.2.2您了解并同意，如您系在猿报价网站完成的注册程序，只要您注册成功，您即可以获得您的网站登录名（以下或称“账户”）。您可以通过您的登录名直接登录猿报价网站，无需另行设置账户。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.2.3由于您的账户关联您的信息及猿报价网站商业信息，您的账户仅限您本人使用。未经猿报价网站同意，您直接或间接授权第三方使用您账户或获取您账户项下信息的行为无效。如猿报价网站根据您账户的使用行为等情况判断您账户的使用可能危及您的账户安全及/或猿报价网站信息安全的，猿报价网站可拒绝提供相应服务或终止本协议。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.3账户转让
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		由于用户账户关联用户信用信息，仅当有法律明文规定、司法裁定或经猿报价网站同意，并符合猿报价网站规则规定的账户转让流程的情况下，您可进行账户的转让。您的账户一经转让，该账户项下权利义务一并转移。除此外，您的账户不得以任何方式转让，否则猿报价网站有权追究您的违约责任，且由此产生的相应责任均由您承担。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.4实名认证
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		为使您更好地使用猿报价网站的各项服务，保障您的账户安全，猿报价网站可要求您按国家法律法规的规定完成实名认证。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.5不活跃账户回收
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		如您的账户同时符合以下条件，您的猿报价网站登录名可能被注销，不能再登陆猿报价网站，所有猿报价网站服务将同时终止：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（一）未按照国家相关法律法规以及猿报价网站要求通过实名认证；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（二）连续</span>6个月未使用您的邮箱、手机或猿报价网站认可的其他方式和密码登录过猿报价网站，也未登录过其他任一猿报价网站平台；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（三）不存在未到期的有效业务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.6注册信息管理
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.6.1在完成注册或激活流程时，您应当按猿报价网站相应页面的提示准确完整地提供并及时更新您的信息，以使之始终保持真实、及时，完整和准确。在国家法律法规有明确规定要求猿报价网站作为平台服务提供者必须对用户（如卖家等）的信息进行核实的情况下，猿报价网站将依法不时地对您的信息进行检查核实，您应当配合提供最新、真实、完整、有效的信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.6.2如猿报价网站按您最后一次提供的信息与您联系未果、您未按猿报价网站的要求及时提供信息、您提供的信息存在明显不实或行政司法机关核实您提供的信息无效的，您将承担因此对您自身、他人及猿报价网站造成的全部损失与不利后果。猿报价网站可向您发出询问或要求整改的通知，并要求您进行重新认证，直至中止、终止对您提供部分或全部猿报价网站服务，除法律明确规定外猿报价网站对此不承担责任。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.6.3您设置的账户登录名及昵称不得违反国家法律法规及猿报价网站规则的相应管理规定，否则猿报价网站可回收您的登录名及昵称，并按猿报价网站规则进行相应的处理。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.7账户安全
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.7.1您的账户为您自行设置并由您保管，猿报价网站任何时候均不会主动要求您提供您的账户密码。因此，建议您务必保管好您的账户，并确保您在每个上网时段结束时退出登录并以正确步骤离开猿报价网站。账户因您主动泄露或因您遭受他人攻击、诈骗等行为导致的损失及后果，猿报价网站并不承担责任，您应通过司法、行政等救济途径向侵权行为人追偿。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.7.2除猿报价网站存在过错外，您应对您账户项下的所有行为结果（包括但不限于在线签署各类协议、发布信息、询/报价、采购产品、订购服务及披露信息等）负责。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4.7.3如发现任何未经授权使用您账户登录猿报价网站或其他可能导致您账户遭窃、遗失的情况，建议您立即通知猿报价网站，并授权猿报价网站将该信息同步给猿报价网站平台。您理解猿报价网站对您的任何请求采取行动均需要合理时间，且猿报价网站应您请求而采取的行动可能无法避免或阻止侵害后果的形成或扩大，除猿报价网站存在法定过错外，猿报价网站不承担责任。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		五、服务及规范
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>您有权在猿报价网站上享受店铺管理、产品及</span>/或服务的报价、销售与推广、产品及/或服务的询价、采购与评价、交易争议处理等服务。猿报价网站提供的服务内容众多，具体您可登录猿报价网站浏览。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.1店铺管理
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.1.1通过在猿报价网站创建店铺，您可发布各类产品及/或服务信息，并与其他用户达成交易。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.1.2由于店铺与账户的不可分性，店铺转让实质为店铺经营者账户的转让，店铺转让的相关要求与限制请适用本协议4.3账户转让条款。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.1.3如您通过下架全部产品短暂关停您的店铺，您仍应当对您店铺关停前已达成的交易继续承担发货、纠纷及投诉处理等交易保障责任。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.2产品及/或服务的报价、销售与推广
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.2.1通过猿报价网站提供的服务，您有权通过文字、图片等形式在猿报价网站上发布产品及/或服务信息、进行报价、招揽和物色交易对象、达成交易。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.2.2您应当确保您对您在猿报价网站上发布的产品及/或服务享有相应的权利，您不得在猿报价网站上销售以下产品及/或提供以下服务：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（一）国家禁止或限制的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（二）侵犯他人知识产权或其它合法权益的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（三）猿报价网站规则、公告、通知或各平台与您单独签署的协议中已明确说明不适合在猿报价网站上销售及</span>/或提供的。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.2.3您应当遵守诚实信用原则，确保您所发布的产品及/或服务信息真实、与您实际所销售的产品及/或提供的服务相符，并在交易过程中切实履行您的交易承诺。您应当维护猿报价网站良性的市场竞争秩序，不得贬低、诋毁竞争对手，不得干扰猿报价网站上进行的任何交易、活动，不得以任何不正当方式提升或试图提升自身的信用度，不得以任何方式干扰或试图干扰猿报价网站的正常运作。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.2.4您有权自行决定产品及/或服务的促销及推广方式，猿报价网站亦为您提供了形式丰富的促销推广工具。您的促销推广行为应当符合国家相关法律法规及猿报价网站的要求。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.2.5依法纳税是每一个公民、企业应尽的义务，您应对销售额/营业额超过法定免征额部分及时、足额地向税务主管机关申报纳税。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.3产品及/或服务的询价、采购与评价
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.3.1当您在猿报价网站询价、采购产品及/或服务时，请您务必仔细确认采购产品的品名、价格、数量、型号、规格、尺寸或服务的时间、内容、限制性要求等重要事项，并在采购下单时核实您的联系地址、电话、收货人等信息。如您填写的收货人非您本人，则该收货人的行为和意思表示产生的法律后果均由您承担。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.3.2您充分了解并同意，猿报价网站是一个商业贸易采购平台，而并非面向消费者的消费购买市场，故您的询价、采购行为应当基于真实的贸易需求，不得存在对产品及/或服务实施恶意询价、采购、恶意维权等扰乱猿报价网站正常交易秩序的行为。基于维护猿报价网站交易秩序及交易安全的需要，猿报价网站发现上述情形时可主动执行关闭相关交易订单等操作。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.3.3您有权在猿报价网站提供的评价系统中对与您达成交易的其他用户产品及/或服务进行评价。您的所有评价行为应遵守猿报价网站规则的相关规定，评价内容应当客观真实，不应包含任何污言秽语、色情低俗、广告信息及法律法规与本协议列明的其他禁止性信息；您不应以不正当方式帮助他人提升信用或利用评价权利对其他用户实施威胁、敲诈勒索。猿报价网站可按照猿报价网站规则的相关规定对您实施上述行为所产生的评价信息进行删除或屏蔽。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.3.4 如您在猿报价网站询价、采购的产品及/或服务为用于对外出口的商品及/或服务：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.3.4.1 您承诺遵守所有适用的进出口管制、贸易限制与经济制裁相关法律法规。 如您违反前述承诺的，猿报价网站可在发现上述情形时停止提供相关服务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.3.4.2您需确保您不是任何国家、地区或国际组织实施的贸易限制、经济制裁或其他法律法规限制的对象，也未直接或间接为前述对象提供资金、商品或服务，否则您应当停止使用猿报价网站提供的服务，同时您理解违反前述要求可能会造成您无法正常注册及使用猿报价网站服务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.3.5 针对通过猿报价网站平台取得的商品、服务、软件及技术，您承诺遵守所有适用的进出口管制、贸易限制与经济制裁相关法律法规。基于维护交易秩序及交易安全的需要，如您违反前述承诺的，猿报价网站可在发现上述情形时主动执行关闭相关交易订单及作出账户处置等操作。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.4交易争议处理
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.4.1 您可通过猿报价网站所支持的交易方式与其他用户达成交易，并遵守相应交易与支付规则。您理解并同意，猿报价网站仅作为用户物色交易对象，就产品和服务的交易进行协商，以及获取各类与贸易相关的服务的地点，猿报价网站不会参与用户间的交易，亦不会涉及用户间因交易而产生的法律关系及法律纠纷。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.4.2您在猿报价网站交易过程中与其他用户发生争议的，您或其他用户中任何一方均有权选择以下途径解决：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（一）与争议相对方自主协商；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（二）使用猿报价网站提供的争议调处服务；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（三）向有关行政部门投诉；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（四）根据与争议相对方达成的仲裁协议（如有）提请仲裁机构仲裁；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（五）向人民法院提起诉讼。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5.4.3如您依据猿报价网站规则使用猿报价网站的争议调处服务，则表示您认可并愿意履行猿报价网站的客服作为独立的第三方根据其所了解到的争议事实并依据猿报价网站规则所作出的调处决定（包括调整相关订单的交易状态、判定将争议款项的全部或部分支付给交易一方或双方、执行相应的保证金赔付等）。在猿报价网站调处决定作出前，您可选择上述其他争议处理途径解决争议以中止猿报价网站的争议调处服务。如您对调处决定不满意，您仍有权采取其他争议处理途径解决争议，但通过其他争议处理途径未取得终局决定前，您仍应先履行调处决定。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		六、费用
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		6.1基什通信服务为猿报价网站向您提供的服务付出了大量的成本，除猿报价网站明示的收费业务外，猿报价网站向您提供的服务目前是免费的。如未来猿报价网站向您收取合理费用，猿报价网站会采取合理途径并提前通过法定程序以本协议约定的有效方式通知您，确保您有充分选择的权利。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		6.2您因进行交易、向猿报价网站获取有偿服务或接触猿报价网站服务器而发生的所有应纳税赋，以及相关硬件、软件、通讯、网络服务及其他方面的费用均由您自行承担。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		七、责任限制
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		7.1猿报价网站依照法律规定履行基础保障义务，但对于下述不可抗力或其他第三人因素原因导致的合同履行障碍、履行瑕疵、履行延后或履行内容变更等情形，猿报价网站并不承担相应的违约责任：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（一）因自然灾害、罢工、暴乱、战争、政府行为、司法行政命令等不可抗力因素；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（二）因电力供应故障、通讯网络故障等公共服务因素或第三人因素；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（三）在猿报价网站已尽善意管理的情况下，因常规或紧急的设备与系统维护、设备与系统故障、网络信息与数据安全等因素。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		7.2猿报价网站仅向您提供本协议约定之服务，您了解猿报价网站上的信息系用户自行发布，且可能存在风险和瑕疵。猿报价网站将通过依照法律规定建立相关检查监控制度尽可能保障您在猿报价网站的合法权益及良好体验。同时，鉴于猿报价网站具备存在海量信息及信息网络环境下信息与实物相分离的特点，猿报价网站作为网络交易平台无法逐一审查产品及/或服务的信息，无法逐一审查交易所涉及的产品及/或服务的质量、安全以及合法性、真实性、准确性，对此您应谨慎判断，如发现存在危害信息和行为，请您及时同猿报价网站进行联系和投诉，猿报价网站将对有害信息和行为进行处理。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		7.3 您理解并同意，在争议调处服务中，猿报价网站的客服并非专业人士，仅能以普通人的认知对用户提交的凭证进行判断，猿报价网站不保证争议调处决定一定符合您的期望，除法律明确规定及存在故意或重大过失外，猿报价网站对争议调处决定不承担责任。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		八、用户信息的保护及授权
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		8.1个人信息的保护
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		猿报价网站非常重视用户个人信息（即能够独立或与其他信息结合后识别用户身份的信息）的保护，在您使用猿报价网站提供的服务时，您同意猿报价网站按照在猿报价网站上公布的隐私政策收集、存储、使用、披露和保护您的个人信息。猿报价网站希望通过隐私政策向您清楚地介绍猿报价网站对您个人信息的处理方式，因此猿报价网站建议您完整地阅读隐私政策，以帮助您更好地保护您的隐私权。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		8.2非个人信息的保证与授权
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		8.2.1您声明并保证，您对您所发布的信息拥有相应、合法的权利。否则，猿报价网站可对您发布的信息依法或依本协议进行删除或屏蔽。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		8.2.2您应当确保您所发布的信息不包含以下内容：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（一）违反国家法律法规禁止性规定的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（二）政治宣传、封建迷信、淫秽、色情、赌博、暴力、恐怖或者教唆犯罪的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（三）欺诈、虚假、不准确或存在误导性的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（四）侵犯他人知识产权或涉及第三方商业秘密及其他专有权利的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（五）侮辱、诽谤、恐吓、涉及他人隐私等侵害他人合法权益的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（六）存在可能破坏、篡改、删除、影响猿报价网站任何系统正常运行或未经授权秘密获取猿报价网站及其他用户的数据、个人资料的病毒、木马、爬虫等恶意软件、程序代码的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（七）其他违背社会公共利益或公共道德或依据相关猿报价网站协议、规则的规定不适合在猿报价网站上发布的。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		8.2.3对于您提供、发布及在使用猿报价网站服务中形成的除个人信息外的文字、图片、视频、音频等非个人信息，均不会因您的上传、发布等行为发生知识产权、肖像权等权利的转移。除非猿报价网站另行明确说明，在法律允许的范围和法律规定的保护期限内，您免费授予猿报价网站及其关联公司、无地域限制的许可使用（包括存储、使用、复制、修订、编辑、发布、展示、翻译、分发您的非个人信息或制作其派生作品，并以已知或日后开发的形式、媒体或技术将上述信息纳入其它作品内等）权利及可再授权给其他第三方使用的权利，以及可以自身名义对第三方侵权行为取证及提起诉讼的权利。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		8.2.4为方便您使用猿报价网站平台相关服务，您授权猿报价网站将您在账户注册和使用猿报价网站服务过程中提供、形成的信息传递给猿报价网站平台等其他相关服务提供者，或从猿报价网站平台等其他相关服务提供者获取您在注册、使用相关服务期间提供、形成的信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		九、用户的违约及处理
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.1违约认定
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		发生如下情形之一的，视为您违约：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（一）使用猿报价网站服务时违反有关法律法规规定的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（二）违反本协议或本协议补充协议（即本协议第</span>3.2条）约定的。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		为适应电子商务发展和满足海量用户对高效优质服务的需求，您理解并同意，猿报价网站可在猿报价网站规则中约定违约认定的程序和标准。如：猿报价网站可依据您的用户数据与海量用户数据的关系来认定您是否构成违约；您有义务对您的数据异常现象进行充分举证和合理解释，否则将被认定为违约。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.2违约处理措施
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.2.1您在猿报价网站上发布的信息构成违约的，猿报价网站可根据相应规则立即对相应信息进行删除、屏蔽处理或对您的产品进行下架、删除、监管。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.2.2您在猿报价网站上实施的行为，或虽未在猿报价网站上实施但对猿报价网站及其用户产生影响的行为构成违约的，猿报价网站可依据相应规则对您执行账户扣分、限制参加营销活动、中止向您提供部分或全部服务、划扣违约金等处理措施。如您的行为构成根本违约的，猿报价网站可关闭您的账户，终止向您提供服务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.2.3当您违约的同时存在欺诈、售假、盗用他人账户等特定情形或您存在危及他人交易安全或账户安全风险时，猿报价网站会依照您行为的风险程度采取取消收款、资金止付等强制措施。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.2.4猿报价网站可将对您上述违约行为处理措施信息以及其他经国家行政或司法机关生效法律文书确认的违法信息在猿报价网站上予以公示。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.3赔偿责任
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.3.1如您的行为使猿报价网站及/或其关联公司遭受损失，您应根据本协议履行赔偿责任，您需赔偿猿报价网站的因此遭受的损失（如有）包括：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（一）支付的合理律师费、诉讼费、消除影响所支出的必要费用；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（二）因行政处罚、司法裁判、法定标准范围内的调解等对外支出的罚款、违约金或赔偿金；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（三）商誉损失、合理预期利益损失（如会员的流失、支出减少、消费频次降低及店铺经营者收入减少等），具体金额可以第三方独立合理分析、猿报价网站根据大数据合理分析及其他计算收益、损失的合理方法所得出的相关估算标准作为计算依据；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（四）因此遭受的其他损失。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.3.2如您的行为使猿报价网站及/或其关联公司遭受第三人主张权利，猿报价网站及/或其关联公司可在对第三人承担金钱给付等义务后就全部损失向您追偿。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.3.3如因您的行为使得第三人遭受损失或您怠于履行调处决定，您授权并同意猿报价网站及/或其关联公司出于保护社会公共利益或保护其他用户合法权益目的，可指示猿报价网站自您的账户中划扣相应款项进行支付。如您的余额或保证金不足以支付相应款项的，您同意委托猿报价网站使用自有资金代您支付上述款项（但猿报价网站并无此义务），您应当返还该部分费用并赔偿因此造成猿报价网站的全部损失。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.3.4您同意猿报价网站指示基什通信服务公司自您的账户中划扣相应款项支付上述赔偿款项。如您账户中的款项不足以支付上述赔偿款项的，猿报价网站及/或关联公司可直接抵减您在猿报价网站及/或其关联公司其它协议项下的款项及/或权益，并可继续追偿。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.3.5 猿报价网站因故意或重大过失违约导致您利益受损的，猿报价网站将以您的直接实际损失为计算依据向您履行赔偿责任。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.4特别约定
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.4.1如您向猿报价网站及/或其关联公司的雇员或顾问等提供实物、现金、现金等价物、劳务、旅游等价值明显超出正常商务洽谈范畴的利益，则可视为您存在商业贿赂行为。发生上述情形的，猿报价网站可按照猿报价网站规则进行相应处理，并经提前通知后终止与您的所有合作并向您收取违约金及/或赔偿金，该等金额以猿报价网站因您的贿赂行为而遭受的经济损失和商誉损失作为计算依据。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.4.2如您因严重违约导致猿报价网站终止本协议时，出于维护平台秩序及保护其他用户合法权益的目的，猿报价网站及/或其关联公司可对与您在其他协议项下的合作采取中止甚或终止协议的措施，并以本协议约定的有效方式通知您。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9.4.3如猿报价网站与您签署的其他协议及猿报价网站及/或其关联公司与您签署的协议中明确约定了对您在本协议项下合作进行关联处理的情形，则猿报价网站出于维护平台秩序及保护其他用户合法权益的目的，可在收到指令时中止甚至终止协议，并以本协议约定的有效方式通知您。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		十、协议的变更
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		10.1猿报价网站可根据国家法律法规变化及维护市场秩序、保护用户合法权益需要，不时修改本协议、补充协议，变更后的协议、补充协议（下称“变更事项”）将通过法定程序并以本协议约定的有效通知方式通知您。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		10.2如您不同意变更事项，您有权于变更事项确定的生效日前联系猿报价网站反馈意见。如反馈意见得以采纳，猿报价网站将酌情调整变更事项。如您对已生效的变更事项仍不同意的，您应当于变更事项确定的生效之日起停止使用猿报价网站服务，变更事项对您不产生效力；如您在变更事项生效后仍继续使用猿报价网站服务，则视为您同意已生效的变更事项。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		十一、有效通知
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		11.1有效联系方式
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		您在注册成为猿报价网站用户，并接受猿报价网站服务时，您应该向猿报价网站提供真实有效的联系方式（包括您的电子邮件地址、联系电话、联系地址等），对于联系方式发生变更的，您有义务及时更新有关信息，并保持可被有效联系的状态。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		您在注册猿报价网站用户时生成的用于登陆猿报价网站接收站内信、系统消息等即时信息的会员账户（包括子账户），也作为您的有效联系方式。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		猿报价网站将向您的上述联系方式的其中之一或其中若干向您送达各类通知，而此类通知的内容可能对您的权利义务产生重大的有利或不利影响，请您务必及时关注。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>您有权通过您注册时填写的手机号码或者电子邮箱获取您感兴趣的产品</span>/服务广告信息、促销优惠等商业性信息；您如果不愿意接收此类信息，您有权通过猿报价网站提供的相应的退订功能进行退订。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		11.2通知的送达
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		11.2.1猿报价网站通过上述联系方式向您发出通知，其中以电子的方式发出的书面通知，包括但不限于在猿报价网站公告，向您提供的联系电话发送手机短信，向您提供的电子邮件地址发送电子邮件，向您的账户发送信息、系统消息以及站内信信息，在发送成功后即视为送达；以纸质载体发出的书面通知，按照提供联系地址交邮后的第五个自然日即视为送达。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		11.2.2对于在猿报价网站上因交易活动引起的任何纠纷，您同意司法机关（包括但不限于人民法院）可以通过手机短信、电子邮件等现代通讯方式或邮寄方式向您送达法律文书（包括但不限于诉讼文书）。您指定接收法律文书的手机号码、电子邮箱或猿报价网站信息等联系方式为您在猿报价网站注册、更新时提供的手机号码、电子邮箱联系方式以及在注册猿报价网站用户时生成猿报价网站账号，司法机关向上述联系方式发出法律文书即视为送达。您指定的邮寄地址为您的法定联系地址或您提供的有效联系地址。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		您同意司法机关可采取以上一种或多种送达方式向您达法律文书，司法机关采取多种方式向您送达法律文书，送达时间以上述送达方式中最先送达的为准。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		您同意上述送达方式适用于各个司法程序阶段。如进入诉讼程序的，包括但不限于一审、二审、再审、执行以及督促程序等。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		你应当保证所提供的联系方式是准确、有效的，并进行实时更新。如果因提供的联系方式不确切，或不及时告知变更后的联系方式，使法律文书无法送达或未及时送达，由您自行承担由此可能产生的法律后果。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		十二、协议终止
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		12.1协议终止的情形
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		12.1.1您有权通过以下任一方式终止本协议：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（一）在满足猿报价网站要求的账户注销条件时您通过网站自助服务注销您的账户的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（二）变更事项生效前您停止使用猿报价网站服务并明示不愿接受变更事项的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（三）您明示不愿继续使用猿报价网站服务，且符合猿报价网站要求的终止条件的。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		12.1.2出现以下情况时，猿报价网站可以本协议约定的有效方式通知您终止本协议：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（一）您的实名认证身份无效、不再合法存续或无法有效核实；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（二）您违反本协议约定，猿报价网站依据违约条款终止本协议的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（三）您盗用他人账户、发布违禁信息、欺诈、售假、扰乱市场秩序、采取不正当手段牟利等行为，猿报价网站依据猿报价网站规则对您的账户予以关闭的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（四）除上述情形外，因您多次违反猿报价网站规则相关规定且情节严重，猿报价网站依据猿报价网站规则对您的账户予以关闭的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（五）您的账户被猿报价网站依据本协议回收的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（六）您在猿报价网站平台有欺诈、发布或销售假冒伪劣</span>/侵权产品、侵犯他人合法权益或其他严重违法违约行为的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（七）猿报价网站基于合理理由相信您的行为可能会使您、猿报价网站用户等相关方发生严重损害或产生法律责任；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（八）其它应当终止服务的情况。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		12.2协议终止后的处理
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		12.2.1本协议终止后，除法律有明确规定外，猿报价网站无义务向您或您指定的第三方披露您账户中的任何信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		12.2.2本协议终止后，猿报价网站仍享有下列权利：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（一）继续保存您留存于猿报价网站的本协议第八条所列的各类信息；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（二）对于您过往的违约行为，猿报价网站仍可依据本协议向您追究违约责任。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		12.2.3本协议终止后，对于您在本协议存续期间产生的交易订单，猿报价网站可通知交易相对方并根据交易相对方的意愿决定是否关闭该等交易订单；如交易相对方要求继续履行的，则您应当就该等交易订单继续履行本协议及交易订单的约定，并承担因此产生的任何损失或增加的任何费用。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		十三、链接
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>猿报价网站或第三者均可提供与其他万维网网站或资源的链接。由于猿报价网站并不控制该等网站和资源，您理解并同意，猿报价网站并不对该等外在网站或资源的可用性负责，且不认可该等网站或资源上或可从该等网站或资源获取的任何内容、宣传、产品、服务或其他材料，也不对其等负责或承担任何责任。您进一步理解和同意，对于任何因使用或信赖从此类网站或资源上获取的此类内容、宣传、产品、服务或其他材料而造成（或声称造成）的任何直接或间接损失，除非法律明确规定应由猿报价网站承担责任情形外，猿报价网站均不承担责任。</span> 
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		十四、法律适用、管辖及其他
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		14.1本协议之效力、解释、变更、执行与争议解决均适用中华人民共和国大陆地区法律；如无相关法律规定的，则应参照商业惯例和（或）行业惯例。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		14.2您与猿报价网站仅为独立订约人关系。本协议无意结成或创设任何代理、合伙、合营、雇佣与被雇佣或特性授权与被授权关系。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		14.3除双方另有约定外，您同意猿报价网站因经营业务需要有权将本协议项下的权利义务就部分或全部进行转让，并采取合理途径提前通过法定程序以本协议约定的有效方式通知您。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		14.4因本协议或猿报价网站服务所引起或与其有关的任何争议，由猿报价网站与您协商解决。协商不成时，任何一方均可向被告住所地人民法院提起诉讼。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		14.5倘若本协议任何条款被裁定为无效或不可强制执行，该项条款应被撤销，而其余条款应予遵守和执行。条款标题仅为方便参阅而设，并不以任何方式界定、限制、解释或描述该条款的范围或限度。猿报价网站未就您或其他人士的某项违约行为采取行动，并不表明猿报价网站撤回就任何继后或类似的违约事件采取行动的权利。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<h3 align="center" style="text-align:center;">
		<b>隐私政策</b><b></b>
	</h3>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		提示条款
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>您的信任对我们非常重要，我们深知个人信息对您的重要性，我们将按法律法规要求，采取相应安全保护措施，尽力保护您的个人信息安全可控。鉴此，猿报价网站服务提供者（或简称</span>“我们”）制定本《隐私政策》（下称“本政策”）并提醒您：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		本政策适用于猿报价网站产品或服务。如我们关联公司（范围详见定义部分）的产品或服务中使用了猿报价网站提供的产品或服务但未设独立的隐私政策的，则本政策同样适用于该部分产品或服务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		需要特别说明的是，本政策不适用于其他第三方向您提供的服务，第三方向您提供的服务适用其向您说明的隐私权政策。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>在使用猿报价网站各项产品或服务前，请您务必仔细阅读并透彻理解本政策，特别是以粗体</span>/粗体下划线标识的条款，您应重点阅读，在确认充分理解并同意后使用相关产品或服务。一旦您开始使用猿报价网站各项产品或服务，即表示您已充分理解并同意本政策。如对本政策内容有任何疑问、意见或建议，您可通过猿报价网站服务中心与我们联系。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>第一部分</span> <span>定义</span>
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>猿报价网站：指猿报价网站（所涉域名为</span> yuanbaojia.com、yuanbaojia.cn、apeprice.com）及客户端。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		猿报价网站服务提供者：指猿报价网站的网络及软件技术服务提供者基什通信服务（深圳）有限公司。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>关联公司：指基什集团有限公司（</span>Kish Inc.）披露的猿报价网站服务提供者的关联公司。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		个人信息：指以电子或者其他方式记录的能够单独或者与其他信息结合识别特定自然人身份或者反映特定自然人活动情况的各种信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>个人敏感信息：指包括身份证件号码、个人生物识别信息、银行账号、财产信息、行踪轨迹、交易信息、</span>14岁以下（含）儿童信息等的个人信息（我们将在本隐私权政策中对具体个人敏感信息以粗体进行显著标识）。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		个人信息删除：指在实现日常业务功能所涉及的系统中去除个人信息的行为，使其保持不可被检索、访问的状态。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		&nbsp;<span>除另有约定外，本政策所用定义与《猿报价网站服务条款》中的定义具有相同的涵义。</span>
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>第二部分</span> <span>隐私政策</span>
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		本隐私政策部分将帮助您了解以下内容：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、我们如何收集和使用您的个人信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、我们如何使用Cookie和同类技术
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		3、我们如何共享、转让、公开披露您的个人信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4、我们如何保护您的个人信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5、您如何管理您的个人信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		6、我们如何处理未成年人的个人信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		7、您的个人信息如何在全球范围转移
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		8、本隐私政策如何更新
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9、如何联系我们
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		一、我们如何收集和使用您的信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>在您使用我们的产品及</span>/或服务时，我们需要/可能需要收集和使用的您的个人信息包括如下两种：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、为实现向您提供我们产品及/或服务的基本功能，您须授权我们收集、使用的必要的信息。如您拒绝提供相应信息，您将无法正常使用我们的产品及/或服务；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、为实现向您提供我们产品及/或服务的附加功能，您可选择授权我们收集、使用的信息。如您拒绝提供，您将无法正常使用相关附加功能或无法达到我们拟达到的功能效果，但并不会影响您正常使用我们产品及/或服务的基本功能。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		您理解并同意：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、我们致力于打造多样的产品和服务以满足您的需求。因我们向您提供的产品和服务种类众多，且不同用户选择使用的具体产品/服务范围存在差异，相应的，基本/附加功能及收集使用的个人信息类型、范围等会有所区别，请以具体的产品/服务功能为准；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、为给您带来更好的产品和服务体验，我们在持续努力改进我们的技术，随之我们可能会不时推出新的或优化后的功能，可能需要收集、使用新的个人信息或变更个人信息使用目的或方式。对此，我们将通过更新本政策、弹窗、页面提示等方式另行向您说明对应信息的收集目的、范围及使用方式，并为您提供自主选择同意的方式，且在征得您明示同意后收集、使用。在此过程中，如果您有任何疑问、意见或建议的，您可通过猿报价网站服务中心与我们联系，我们会尽快为您作出解答。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		我们会出于本政策所述的以下目的，收集和使用您的个人信息：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（一）</span> &nbsp;<span>帮助您成为我们的会员</span>
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、基础会员服务
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		为成为我们的会员，以便我们为您提供会员服务，您需要提供手机号码、联系人姓名，并创建会员名和密码。如果您仅需使用浏览、搜索等基本服务，您不需要注册成为我们的会员及提供上述信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		对于需要通过猿报价网站平台账户才能使用的服务，我们可能会根据您提供的上述信息校验您的会员身份，确保我们是在为您本人提供服务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、附加会员服务
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		实人认证：您可以根据认证要求向我们提供相应的身份信息（身份证、护照、户口本及其他身份证件信息）、生物识别特征（静态或动态的面部特征）以完成实人认证。若您要使用账户增强保护功能，除可选择完成上述实人认证外，您还可以选择开启声纹密保或者扫脸登录，提供声纹或者面部特征信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		其他会员角色服务：如果您提供以下额外资料补全账号信息，将有助于我们给您提供更个性化的产品或服务：您的真实姓名、性别、固定电话、联系方式、职位、主营行业等。如果您不提供这些信息，将会影响到您使用个性化的产品或服务，但不会影响使用猿报价网站基本浏览、搜索、采购功能。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		在您主动注销账号时，我们将根据适用法律法规的要求尽快使其匿名或删除您的个人信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（二）为您展示和推送产品或服务
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		为改善我们的产品或服务、向您提供个性化的信息搜索及交易服务，我们会根据您的浏览及搜索记录、设备信息、位置信息、订单信息，提取您的浏览、搜索偏好、行为习惯、位置信息等特征，基于特征标签进行间接人群画像并展示、推送信息，用于展示、推送信息和可能的商业广告。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		我们努力保障您的浏览体验。如果您不想接受我们给您发送的商业广告，您可通过短信提示回复退订或我们提供的其他方式进行退订或关闭。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>在您使用我们提供的站内搜索服务时，我们也同时提供了不针对您个人特征的通用排序选项，您可以在搜索结果页面点击</span>“筛选”并选择其中的“通用排序”。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		在您打开首页或进入完成交易页面时，您可以通过长按商品或服务的图片实现对我们展示的商品或服务内容的控制。您可以选择屏蔽类似商品，或者直接选择屏蔽商品或服务所属的偏好类目。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		此外，我们也会为了不断改进和优化上述的功能来使用您的上述信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（三）向您提供产品或服务
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、您向我们提供的信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		为便于向您提供我们的产品或服务，我们会记录您提供的信息，例如您提供的联系人姓名、联系电话等。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>我们的一些产品或服务需要付费才能使用，当您需要使用这些付费产品或服务时，您需要提供您的银行卡信息及</span>/或绑定支付宝服务，以便我们了解您的支付状态。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		您可以通过猿报价网站为其他人订购产品或服务，您需要提供该实际订购人的前述个人信息。向我们提供该实际订购人的前述个人信息之前，您需确保您已经取得其授权同意。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、我们在您使用服务过程中收集的信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		为向您提供更契合您需求的页面展示和搜索结果、了解产品适配性、识别账号异常状态，我们会收集关于您使用的服务以及使用方式的信息并将这些信息进行关联，这些信息包括：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>设备信息：我们会根据您在软件安装及使用中授予的具体权限，接收并记录您所使用的设备相关信息（例如设备型号、操作系统版本、设备设置、唯一设备标识符、应用列表信息等软硬件特征信息）、设备所在位置相关信息（例如</span>IP地址、GPS位置以及能够提供相关信息的Wi-Fi接入点、蓝牙和基站等传感器信息）。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>日志信息：当您使用我们的网站或客户端提供的产品或服务时，我们会自动收集您对我们服务的详细使用情况，作为有关网络日志保存。例如您的搜索查询内容、</span>IP地址、浏览器的类型、电信运营商、使用的语言、访问日期和时间及您访问的网页记录等。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		请注意，单独的设备信息、日志信息等是无法识别特定自然人身份的信息。如果我们将这类非个人信息与其他信息结合用于识别特定自然人身份，或者将其与个人信息结合使用，则在结合使用期间，这类非个人信息将被视为个人信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		为展示您账号的订单信息，我们会收集您在使用我们服务过程中产生的订单信息用于向您展示及便于您对订单进行管理。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>当您与我们联系时，我们可能会保存您的通信</span>/通话记录和内容或您留下的联系方式等信息，以便与您联系或帮助您解决问题，或记录相关问题的处理方案及结果。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		3、我们通过间接获得方式收集到的您的个人信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		为确认交易状态及为您提供交易争议解决服务，我们会通过您基于交易所选择的交易对象、交易方式、支付机构、物流公司等收集与交易进度相关的您的交易、支付、物流信息，或将您的交易信息共享给上述服务提供者。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		您可通过猿报价网站账号在我们提供的链接入口使用我们关联公司提供的产品或服务，为便于我们基于关联账号共同向您提供一站式服务并便于您统一进行管理，我们在猿报价网站集中展示您的信息或推荐您感兴趣的信息，包括来自猿报价网站、报价猿网站等功能发现、使用上述服务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		当您通过我们产品或服务使用上述服务时，您授权我们根据实际业务及合作需要从我们关联公司处接收、汇总、分析我们确认其来源合法或您授权同意其向我们提供的您的个人信息或交易信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		如您拒绝提供上述信息或拒绝授权，可能无法使用我们关联公司的相应产品或服务，或者无法展示相关信息，但不影响使用猿报价网站浏览、搜索、交易等核心服务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（四）为您提供收藏、加购、关注与分享功能
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>在您浏览我们网站或客户端的过程中，您可以选择对感兴趣的商品及</span>/或服务进行收藏、添加至购物车、与您感兴趣的商家/品牌/其他会员建立关注关系、通过我们提供的功能组件向其他第三方分享信息。在您使用上述功能的过程中，我们会收集包括您的收藏及添加购物车的记录、关注关系、分享历史在内的服务日志信息用于实现上述功能及其他我们明确告知的目的。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（五）帮助您完成下单及订单管理
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>当您在我们的产品及</span>/或服务中订购具体商品及/或服务时，我们会通过系统为您生成购买该商品及/或服务的订单。在下单过程中,您需至少提供您的收货人姓名、收货地址、收货人联系电话，同时该订单中会载明您所购买的商品及/或服务信息、具体订单号、订单创建时间、您应支付的金额，我们收集这些信息是为了帮助您顺利完成交易、保障您的交易安全、查询订单信息、提供客服与售后服务及其他我们明确告知的目的。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>您可以通过猿报价网站为其他人订购商品及</span>/或服务，您需要提供该实际订购人的前述个人信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>为便于您了解查询订单信息并对订单信息进行管理，我们会收集您在使用我们服务过程中产生的订单信息用于向您展示及便于您对订单进行管理。</span> 
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>您可额外填写</span>/选择包括其他联系电话、收货时间在内的更多附加信息以确保商品或服务的准确送达。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（六）</span> <span>帮助您完成支付</span> 
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>为完成订单支付，您需要提供支付宝账户、微信账户、网上银行或其他第三方支付方式并选择付款方式，我们会将您的猿报价网站平台账户会员名、对应的支付宝账户用户名、微信账户、网上银行用户名或第三方支付账户会员名、订单支付相关信息、订单安全相关设备信息及其他反洗钱法律要求的必要信息通过在应用程序中嵌入的支付宝、微信账户、网上银行或第三方支付平台软件工具开发包（</span>SDK）等方式与相应网上银行或第三方支付平台公司共享。如您选择由其他金融机构为您提供支付服务的，我们还会将您的包括银行卡号、有效期在内的银行卡支付必要信息与您选择的相应金融机构共享。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>您可以请求其他人为您付款，那么您需要提供代付人的支付宝账户等第三方支付账户及</span>/或手机号码。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		为使我们及时获悉并确认您的支付进度及状态，为您提供售后与争议解决服务，您同意我们可自您所选择的交易对象、支付宝公司或您选择的其他金融机构处收集与支付进度相关信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（七）</span> <span>帮助向您完成商品或服务的交付</span>
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>为保证您购买的商品及</span>/或服务能够顺利、安全、准确送达，我们会向为猿报价网站提供物流信息系统和技术服务的快递100披露订单相关配送信息，并由其根据商品及/或服务提供主体的指定向相应的物流配送主体同步相关配送信息。您知晓并同意相应物流配送主体不可避免地获知及使用您的配送信息，用于完成交付目的。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		为使我们及时获悉并确认交付进度及状态，向您提供售后与争议解决服务，您同意我们可自物流相关服务主体处收集与交付进度相关信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（八）</span> <span>客服及争议处理</span>
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		当您与我们联系或提出售中售后、争议纠纷处理申请时，为了保障您的账户及系统安全，我们需要您提供必要的个人信息以核验您的会员身份。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>为便于与您联系、尽快帮助您解决问题或记录相关问题的处理方案及结果，我们可能会保存您与我们的通信</span>/通话记录及相关内容（包括账号信息、订单信息、您为了证明相关事实提供的其他信息，或您留下的联系方式信息），如果您针对具体订单进行咨询、投诉或提供建议的，我们会使用您的账号信息和订单信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		为了提供服务及改进服务质量的合理需要，我们还可能使用的您的其他信息，包括您与客服联系时您提供的相关信息，您参与问卷调查时向我们发送的问卷答复信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（九）</span> <span>为您提供评论、问答等信息公开发布功能</span>
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>您可通过我们为您提供的评论、问答、论坛、晒单分享、直播及其他信息发布功能公开发布信息，包括作为会员可发布图文</span>/视频/直播内容、发表评价及问答内容，以及作为卖家发布商品及/或服务相关信息、店铺相关信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		我们可能会根据您的会员类型及网络日志信息判断您是否可享受对应功能权限（例如只有曾购买过某商品的会员才可通过问大家对其他会员针对该商品的提问做出回答）。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		请注意，您公开发布的信息中可能会涉及您或他人的个人信息甚至个人敏感信息，如您在评价时选择上传包含个人信息的图片。请您更加谨慎地考虑，是否在使用我们的服务时共享甚至公开分享相关信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（十）</span> <span>为您提供安全保障</span>
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		请注意，为确保会员身份真实性、向您提供更好的安全保障，您可以向我们提供身份证、面部特征等信息完成实名认证。如您拒绝提供上述信息，可能无法使用账号管理、开通旺铺、发布产品、报价、继续可能存在风险的交易等服务，但不会影响您使用浏览、搜索等服务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>为提高您使用我们及我们关联公司、合作伙伴提供服务的安全性，保护您或其他用户或公众的人身财产安全免遭侵害，更好地预防钓鱼网站、欺诈、网络漏洞、计算机病毒、网络攻击、网络侵入等安全风险，更准确地识别违反法律法规或猿报价网站相关协议规则的情况，我们在应用程序中嵌入我们关联公司开发的应用安全</span>SDK收集您的设备信息、服务日志信息，并可能使用或整合您的会员信息、交易信息、设备信息、有关网络日志以及我们关联公司、合作伙伴取得您授权或依据法律共享的信息，来综合判断您账号及交易风险、进行身份验证、检测及防范安全事件，并依法采取必要的记录、审计、分析、处置措施。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（十一）</span> <span>为您提供其他附加服务</span>
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>为向您提供更便捷、更优质、个性化的产品及</span>/或服务，努力提升您的体验，我们在向您提供的以下附加服务中可能会收集和使用您的个人信息。如果您不提供这些信息，不会影响您使用猿报价网站的浏览、搜索、购买等基本服务，但您可能无法获得这些附加服务给您带来的用户体验。这些附加服务包括：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、基于位置信息的附加服务：我们会在您开启位置权限后访问获取您的位置信息，根据您的位置信息为您提供更契合您需求的页面展示、产品及/或服务，比如向您推荐附近的优惠信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、基于相机/摄像头的附加服务：您可在开启相机/摄像头权限后使用该功能进行扫码用于登录、购物、领取优惠信息，拍摄照片或视频用于评价、分享，以及特定场景下经您授权的人脸识别等功能。当您使用该附加功能进行人脸识别时我们会收集您的面部特征，且严格在经您授权同意的范围内使用，未来如我们拟使用您的面部信息为您提供其他产品及/或服务功能的，我们会再次与您确认。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		3、基于相册（图片库/视频库）的图片/视频访问及上传的附加服务：您可在开启相册权限后使用该功能上传您的照片/图片/视频，以实现更换头像、发表评论/分享、拍照购物或与客服沟通提供证明等功能。我们可能会通过您所上传的照片/图片来识别您需要购买的商品或服务，或使用包含您所上传照片或图片的评论信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4、基于麦克风的语音技术相关附加服务：您可在开启麦克风权限后使用麦克风实现语音购物功能，或与客服联系或与客服机器人实现语音交互，在这些功能中我们会收集您的录音内容以识别您的购物需求，或响应您的客服及争议处理等需求。请您知晓，即使您已同意开启麦克风权限，我们也仅会在您主动点击客户端内麦克风图标或录制视频时通过麦克风获取语音信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5、基于通讯录信息的附加服务：我们将在您开启通讯录权限后收集您的通讯录信息使您在购物时可以更便利地取用您通讯录内的联系人信息，无需再手动输入，包括您可以直接为通讯录中的联系人手机号进行充值；此外，为提升您在使用我们产品及/或服务过程中的社交互动分享乐趣，与您认识的人分享购物体验，在经您同意的前提下，我们也可能对您联系人的姓名和电话号码进行加密收集，帮助您判断您的通讯录联系人是否同为我们的会员进而在猿报价网站为你们的交流建立联系，同时，会员可选择开启或关闭好友隐私权限决定自己是否可被其他会员通过手机号码搜索、联系。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		6、基于日历的附加服务：在您开启我们可读取/写入您日历的权限后，我们将收集您的日历信息用于向您提供购物或领取权益相关记录及提醒。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		7、基于电话状态的附加服务：为保障您的账号与使用安全，您需要授权我们读取本机识别码。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>您理解并同意，上述附加服务可能需要您在您的设备中开启您的位置信息</span> <span>（地理位置）、摄像头（相机）、相册（图片库）、麦克风（语音）、通讯录及</span>/或日历的访问权限，以实现这些权限所涉及信息的收集和使用。您可在您的设备设置或我们客户端“我的-设置-隐私—系统权限”中逐项查看上述权限的状态，并可自行决定这些权限随时的开启或关闭。请您注意，您开启任一权限即代表您授权我们可以收集和使用相关个人信息来为您提供对应服务，您一旦关闭任一权限即代表您取消了授权，我们将不再基于对应权限继续收集和使用相关个人信息，也无法为您提供该权限所对应的服务。但是，您关闭权限的决定不会影响此前基于您的授权所进行的信息收集及使用。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（十二）其他用途
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、若你提供的信息中含有其他用户的个人信息，在向猿报价网站提供这些个人信息之前，您需确保您已经取得合法的授权。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、若我们将信息用于本政策未载明的其他用途，或者将基于特定目的收集而来的信息用于其他目的，或者我们主动从第三方处获取您的个人信息，均会事先获得您的同意。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		若我们从第三方处间接获取您的信息的，我们会在收集前明确以书面形式要求该第三方在已依法取得您同意后收集个人信息，并向您告知共享的信息内容，且涉及敏感信息的在提供给我们使用前需经过您的明确确认，要求第三方对个人信息来源的合法性和合规性作出承诺，如第三方有违反行为的，我们会明确要求对方承担相应法律责任；同时，我们的专业安全团队对个人信息会进行安全加固（包括敏感信息报备、敏感信息加密存储、访问权限控制等）。我们会使用不低于我们对自身用户个人信息同等的保护手段与措施对间接获取的个人信息进行保护。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（十三）征得授权同意的例外
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		根据相关法律法规规定，以下情形中收集您的个人信息无需征得您的授权同意：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、与国家安全、国防安全有关的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、与公共安全、公共卫生、重大公共利益有关的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		3、与犯罪侦查、起诉、审判和判决执行等有关的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4、出于维护个人信息主体或其他个人的生命、财产等重大合法权益但又很难得到您本人同意的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5、所收集的个人信息是您自行向社会公众公开的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		6、从合法公开披露的信息中收集个人信息的，如合法的新闻报道、政府信息公开等渠道；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		7、根据您的要求签订合同所必需的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		8、用于维护所提供的产品或服务的安全稳定运行所必需的，例如发现、处置产品或服务的故障；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		9、为合法的新闻报道所必需的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		10、学术研究机构基于公共利益开展统计或学术研究所必要，且对外提供学术研究或描述的结果时，对结果中所包含的个人信息进行去标识化处理的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		11、法律法规规定的其他情形。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		请知悉，根据适用的法律，若我们对个人信息采取技术措施和其他必要措施进行处理，使得数据接收方无法重新识别特定个人且不能复原，或我们可能会对收集的信息进行去标识化地研究、统计分析和预测，用于改善猿报价网站的内容和布局，为商业决策提供产品或服务支撑，以及改进我们的产品和服务（包括使用匿名数据进行机器学习或模型算法训练），则此类处理后数据的使用无需另行向您通知并征得您的同意。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		如我们停止运营猿报价网站产品或服务，我们将及时停止继续收集您个人信息的活动，将停止运营的通知以逐一送达或公告的形式通知您，对所持有的个人信息进行删除或匿名化处理。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>二、我们如何使用</span>Cookie和同类技术
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（一）</span>Cookie
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>为确保网站正常高效运转、为您获得更轻松的访问体验、向您推荐您可能感兴趣的内容，我们会在您的计算机或移动设备上存储相关信息：这些信息可能是</span>Cookie、Flash Cookie，或您的浏览器或关联应用程序提供的其他本地存储（统称“Cookie”）。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>请您理解，我们的某些服务只能通过使用</span>Cookie才可得到实现。如果您的浏览器或浏览器附加服务允许，您可以修改对Cookie的接受程度或者拒绝我们的Cookie，但拒绝我们的Cookie在某些情况下可能会影响您安全访问网站和使用我们提供的服务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>如果您的浏览器或浏览器附加服务允许，您可修改对</span>Cookie的接受程度或拒绝我们的Cookie。有关详情，请参见 AboutCookies.org。但如果您这么做，在某些情况下可能会影响您安全访问我们的网站，且可能需要在每一次访问我们的网站时更改用户设置。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（二）网站信标和像素标签
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>除</span>Cookie外，我们还会在网站上使用网站信标和像素标签等其他同类技术。例如，我们向您发送的电子邮件可能含有链接至我们网站内容的地址链接，如果您点击该链接，我们则会跟踪此次点击，帮助我们了解您的产品或服务偏好以便于我们主动改善客户服务体验。网站信标通常是一种嵌入到网站或电子邮件中的透明图像。借助于电子邮件中的像素标签，我们能够获知电子邮件是否被打开。如果您不希望自己的活动以这种方式被追踪，则可以随时从我们的寄信名单中退订。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		三、我们如何共享、转让、公开披露您的个人信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（一）共享
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		&nbsp;&nbsp;<span>我们不会与猿报价网站服务提供者以外的公司、组织和个人共享您的个人信息，但以下情况除外：</span>
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、在获取明确同意的情况下共享：获得您的明确同意后，我们会与其他方共享您的个人信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、在法定情形下的共享：我们可能会根据法律法规规定、诉讼争议解决需要，或按行政、司法机关依法提出的要求，对外共享您的个人信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		3、在您主动选择情况下共享：您通过猿报价网站购买商品或服务，我们会根据您的选择，将您的订单信息中与交易有关的必要信息共享给相关商品或服务的提供者，以实现您的交易及售后服务需求。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4、与关联公司间共享：为便于我们基于关联账号共同向您提供服务，推荐您可能感兴趣的信息或保护猿报价网站关联公司或其他用户或公众的人身财产安全免遭侵害，您的个人信息可能会与我们的关联公司共享。我们只会共享必要的个人信息（如为便于您使用猿报价网站账号使用我们关联公司产品或服务，我们会向关联公司共享您必要的账号信息），如果我们共享您的个人敏感信息或关联公司改变个人信息的使用及处理目的，将再次征求您的授权同意。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5、与授权合作伙伴共享：仅为实现本隐私政策中声明的目的，我们的某些服务将由我们和授权合作伙伴共同提供。我们可能会与合作伙伴共享您的某些个人信息，以提供更好的客户服务和用户体验。例如，在您上网采购网站上的产品时，我们必须与物流服务提供商共享您的个人信息才能安排送货，或者安排合作伙伴提供服务。我们仅会出于合法、正当、必要、特定、明确的目的共享您的个人信息，并且只会共享提供服务所必要的个人信息。我们的合作伙伴无权将共享的个人信息用于与产品或服务无关的其他用途。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		目前，我们的授权合作伙伴包括以下类型：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（</span>1）广告、分析服务类的授权合作伙伴。除非得到您的许可，否则我们不会将您的个人身份信息（指可以识别您身份的信息，例如姓名或电子邮箱，通过这些信息可以联系到您或识别您的身份）与提供广告、分析服务的合作伙伴共享。我们会向这些合作伙伴提供有关其广告覆盖面和有效性的信息，而不会提供您的个人身份信息，或者我们将这些信息进行汇总，以便它不会识别您个人。例如，只有在广告主同意遵守我们的广告发布准则后，我们才可能会告诉广告主他们广告的效果如何，或者有多少人看了他们广告或在看到广告后安装了应用，或者向这些合作伙伴提供不能识别个人身份的统计信息（例如“男性，25-29岁，位于北京”），帮助他们了解其受众或客户。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（</span>2）供应商、服务提供商和其他合作伙伴。我们将信息发送给支持我们业务的供应商、服务提供商和其他合作伙伴，这些支持包括提供技术基础设施服务、分析我们服务的使用方式、衡量广告和服务的有效性、提供客户服务、支付便利或进行学术研究和调查。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		对我们与之共享个人信息的公司、组织和个人，我们会与其签署严格的数据保护协定，要求他们按照我们的说明、本隐私政策以及其他任何相关的保密和安全措施来处理个人信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>为了使您能给使用上述服务及功能，我们的应用中会嵌入授权合作伙伴的</span>SDK或其他类似的应用程序。如您使用华为/Oppo/Vivo/小米手机时，华为/Oppo/Vivo/小米Push SDK需要读取您的设备识别号、联网相关信息，用于推送/下发通知栏消息；如您使用高德提供的地图服务，高德SDK需要您打开设备位置权限并收集设备所在位置、设备识别号、联网相关信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>我们会对授权合作伙伴获取有关信息的应用程序接口（</span>API）、软件工具开发包（SDK）进行严格的安全检测，并与授权合作伙伴约定严格的数据保护措施，令其按照我们的委托目的、服务说明、本隐私权政策以及其他任何相关的保密和安全措施来处理个人信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（二）转让
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		我们不会将您的个人信息转让给任何公司、组织和个人，但以下情况除外：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、在获取明确同意的情况下转让：获得您的明确同意后，我们会向其他方转让您的个人信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、在猿报价网站服务提供者发生合并、收购或破产清算情形，或其他涉及合并、收购或破产清算情形时，如涉及到个人信息转让，我们会要求新的持有您个人信息的公司、组织继续受本政策的约束，否则我们将要求该公司、组织和个人重新向您征求授权同意。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（三）公开披露
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		我们仅会在以下情况下，公开披露您的个人信息：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、获得您明确同意或基于您的主动选择，我们可能会公开披露您的个人信息；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、如果我们确定您出现违反法律法规或严重违反猿报价网站相关协议规则的情况，或为保护猿报价网站及其关联公司用户或公众的人身财产安全免遭侵害，我们可能依据法律法规或猿报价网站相关协议规则征得您同意的情况下披露关于您的个人信息，包括相关违规行为以及猿报价网站已对您采取的措施。例如，若您销售假货而严重违反猿报价网站规则，我们可能会公开披露您的店铺主体信息与处罚情况。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（四）共享、转让、公开披露个人信息时事先征得授权同意的例外
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		以下情形中，共享、转让、公开披露您的个人信息无需事先征得您的授权同意：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、与国家安全、国防安全有关的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、与公共安全、公共卫生、重大公共利益有关的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		3、与犯罪侦查、起诉、审判和判决执行等有关的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4、出于维护您或其他个人的生命、财产等重大合法权益但又很难得到本人同意的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5、您自行向社会公众公开的个人信息；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		6、从合法公开披露的信息中收集个人信息的，如合法的新闻报道、政府信息公开等渠道。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		根据法律规定，共享、转让经去标识化处理的个人信息，且确保数据接收方无法复原并重新识别个人信息主体的，不属于个人信息的对外共享、转让及公开披露行为，对此类数据的保存及处理将无需另行向您通知并征得您的同意。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		四、我们如何保护您的个人信息安全
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>（一）我们已采取符合业界标准、合理可行的安全防护措施保护您提供的个人信息安全，防止个人信息遭到未经授权访问、公开披露、使用、修改、损坏或丢失。例如，在您的浏览器与服务器之间交换数据（如信用卡信息）时受</span>SSL（Secure Socket Layer）协议加密保护；我们同时对猿报价网站提供HTTPS（Hyper Text Transfer Protocol over Secure Socket Layer）协议安全浏览方式；我们会使用加密技术提高个人信息的安全性；我们会使用受信赖的保护机制防止个人信息遭到恶意攻击；我们会部署访问控制机制，尽力确保只有授权人员才可访问个人信息；以及我们会举办安全和隐私保护培训课程，加强员工对于保护个人信息重要性的认识。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（二）我们有行业先进的以数据为核心，围绕数据生命周期进行的数据安全管理体系，从组织建设、制度设计、人员管理、产品技术等方面多维度提升整个系统的安全性。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（三）我们会采取合理可行的措施，尽力避免收集无关的个人信息。我们只会在达成本政策所述目的所需的期限内保留您的个人信息，除非法律有强制的存留要求，例如《中华人民共和国电子商务法》要求商品和服务信息、交易信息保存时间自交易完成之日起不少于三年。而我们判断前述期限的标准包括：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、完成与您相关的交易目的、维护相应交易及业务记录、应对您可能的查询或投诉；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、保证我们为您提供服务的安全和质量；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		3、您是否同意更长的留存期间；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4、是否存在保留期限的其他特别约定。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		在您的个人信息超出保留期间后，我们会根据适用法律的要求删除您的个人信息，或使其匿名化处理。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（四）互联网并非绝对安全的环境，我们强烈建议您不要使用非猿报价网站推荐的通信方式发送个人信息。您可以通过我们的服务建立联系和相互分享。当您通过我们的服务创建交流、交易或分享时，您可以自主选择沟通、交易或分享的对象，作为能够看到您的交易内容、联络方式、交流信息或分享内容等相关信息的第三方。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		在使用猿报价网站服务进行网上交易时，您不可避免地要向交易对方或潜在的交易对方披露自己的个人信息，如联络方式或联系地址。请您妥善保护自己的个人信息，仅在必要的情形下向他人提供。如您发现自己的个人信息尤其是您的账号或密码发生泄露，请您立即联络猿报价网站服务中心，以便我们根据您的申请采取相应措施。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		请注意，您在使用我们服务时自愿共享甚至公开分享的信息，可能会涉及您或他人的个人信息甚至个人敏感信息。请您更加谨慎地考虑，是否在使用我们的服务时共享甚至公开分享相关信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		请使用复杂密码，协助我们保证您的账号安全。我们将尽力保障您发送给我们的任何信息的安全性。如果我们的物理、技术或管理防护设施遭到破坏，导致信息被非授权访问、公开披露、篡改或毁坏，导致您的合法权益受损，我们将承担相应的法律责任。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（五）我们将不定期更新并公开安全风险、个人信息安全影响评估报告等有关内容，您可通过猿报价网站公告方式获得。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（六）在不幸发生个人信息安全事件后，我们将按照法律法规的要求向您告知：安全事件的基本情况和可能的影响、我们已采取或将要采取的处置措施、您可自主防范和降低风险的建议、对您的补救措施等。事件相关情况我们将以邮件、信函、电话、推送通知等方式告知您，难以逐一告知个人信息主体时，我们会采取合理、有效的方式发布公告。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		同时，我们还将按照监管部门要求，上报个人信息安全事件的处置情况。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		五、您如何管理您的个人信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		您可以通过以下方式访问及管理您的个人信息：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（一）访问您的个人信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		您有权访问您的个人信息，法律法规规定的例外情况除外。您可以通过以下方式自行访问您的个人信息：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>账号信息</span>——如果您希望访问或编辑您的账号中的个人基本资料信息和支付信息、更改您的密码、添加安全信息或关闭您的账号等，您可以通过登录账号通过“账号管理”执行此类操作。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>搜索信息</span>——您可以登录账号通过“我的足迹”访问或清除您的搜索历史记录。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		订单信息：您可以在猿报价网站中查阅或清除您的订单记录、交易记录等。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>如果您无法通过上述路径访问该等个人信息，您可以随时通过猿报价网站服务中心与我们取得联系。我们将在</span>15天内回复您的访问请求。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>对于您在使用我们的产品或服务过程中产生的其他个人信息，我们将根据本条</span>“（七）响应您的上述请求”中的相关安排向您提供。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（二）更正或补充您的个人信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>当您发现我们处理的关于您的个人信息有错误时，您有权要求我们做出更正或补充。您可以通过</span>“（一）访问您的个人信息”中列明的方式提出更正或补充申请。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（三）删除您的个人信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>您可以通过</span>“（一）访问您的个人信息”中列明的方式删除您的部分个人信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		在以下情形中，您可以向我们提出删除个人信息的请求：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、如果我们处理个人信息的行为违反法律法规；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、如果我们收集、使用您的个人信息，却未征得您的明确同意；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		3、如果我们处理个人信息的行为严重违反了与您的约定；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4、如果您不再使用我们的产品或服务，或您主动注销了账号；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5、如果我们永久不再为您提供产品或服务。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		若我们决定响应您的删除请求，我们还将同时尽可能通知从我们处获得您的个人信息的主体，要求其及时删除，除非法律法规另有规定，或这些主体获得您的独立授权。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		当您或我们协助您删除相关信息后，因为适用的法律和安全技术，我们可能无法立即从备份系统中删除相应的信息，我们将安全地存储您的个人信息并将其与任何进一步处理隔离，直到备份可以清除或实现匿名。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（四）改变您授权同意的范围
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>每个业务功能可能需要一些基本的个人信息才能得以完成（见本隐私政策</span>“第二部分”）。除此之外，对于额外个人信息的收集和使用，您可以通过登录账号通过“账号管理”或与猿报价网站服务中心联系给予或收回您的授权同意。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		当您收回同意后，我们将不再处理相应的个人信息。但您收回同意的决定，不会影响此前基于您的授权而开展的个人信息处理。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（五）个人信息主体注销账号
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>您可以自行在</span>“账号注销”页面（例如，猿报价网站网站“账号管理-账号注销”）提交账号注销申请。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		在您主动注销账号之后，我们将停止为您提供产品或服务，根据适用法律的要求删除您的个人信息，或使其匿名化处理。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（六）约束信息系统自动决策
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		在某些业务功能中，我们可能仅依据信息系统、算法等在内的非人工自动决策机制做出决定。如果这些决定显著影响您的合法权益，您有权要求我们做出解释，我们也将在不侵害猿报价网站商业秘密或其他用户权益、社会公共利益的前提下提供申诉方法。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		（七）响应您的上述请求
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		为保障安全，您可能需要提供书面请求，或以其他方式证明您的身份。我们可能会先要求您验证自己的身份，然后再处理您的请求。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>我们将在</span>15天内做出答复。如您不满意，还可以通过猿报价网站服务中心发起投诉。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		对于您合理的请求，我们原则上不收取费用，但对多次重复、超出合理限度的请求，我们将视情收取一定成本费用。对于那些无端重复、需要过多技术手段（例如，需要开发新系统或从根本上改变现行惯例）、给他人合法权益带来风险或者非常不切实际的请求，我们可能会予以拒绝。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		在以下情形中，按照法律法规要求，我们将无法响应您的请求：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、与国家安全、国防安全有关的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、与公共安全、公共卫生、重大公共利益有关的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		3、与犯罪侦查、起诉、审判和执行判决等有关的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4、有充分证据表明个人信息主体存在主观恶意或滥用权利的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5、响应您的请求将导致您或其他个人、组织的合法权益受到严重损害的；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		6、涉及商业秘密的。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		六、我们如何处理未成年人的个人信息
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		如果没有父母或监护人的同意，未成年人不得创建自己的用户账号。如您为未成年人的，建议您请您的父母或监护人仔细阅读本隐私政策，并在征得您的父母或监护人同意的前提下使用我们的服务或向我们提供信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		对于经父母或监护人同意使用我们的产品或服务而收集未成年人个人信息的情况，我们只会在法律法规允许、父母或监护人明确同意或者保护未成年人所必要的情况下使用、共享、转让或披露此信息。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		七、您的个人信息如何在全球范围转移
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		我们在中华人民共和国境内运营中收集和产生的个人信息，存储在中国境内，以下情形除外：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、法律法规有明确规定；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、获得您的明确授权。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		针对以上情形，我们会确保依据本隐私政策对您的个人信息提供足够的保护。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		八、本隐私政策如何更新
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		我们的隐私政策可能变更。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		未经您明确同意，我们不会限制您按照本隐私政策所应享有的权利。我们会在专门页面上发布对隐私政策所做的任何变更。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		对于重大变更，我们还会提供更为显著的通知（包括我们会通过猿报价网站公示的方式进行意见征集、公告通知甚至向您提供弹窗提示）。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		&nbsp;<span>本政策所指的重大变更包括但不限于：</span>
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、我们的服务模式发生重大变化。如处理个人信息的目的、处理的个人信息类型、个人信息的使用方式等；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、我们在控制权等方面发生重大变化。如并购重组等引起的信息控制者变更等；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		3、个人信息共享、转让或公开披露的主要对象发生变化；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		4、您参与个人信息处理方面的权利及其行使方式发生重大变化；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		5、我们负责处理个人信息安全的责任部门、联络方式及投诉渠道发生变化；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		6、个人信息安全影响评估报告表明存在高风险。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		我们还会将本隐私权政策的旧版本在猿报价网站存档，供您查阅。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		九、如何联系我们
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		<span>您可以通过以下方式与我们联系，我们将在</span>15天内回复您的请求：
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		1、如对本政策内容有任何疑问、意见或建议，您可通过猿报价网站服务中心与我们联系；
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		2、我们还设立了个人信息保护专职部门，如发现个人信息可能被泄露，您可以通过kish1927@hotmail.com与我们联系，办公地址：深圳福田华强北赛格科技园106栋914室法务服务台。
	</p>
	<p class="MsoNormal">
		&nbsp;
	</p>
	<p class="MsoNormal">
		如果您对我们的回复不满意，特别是您认为我们的个人信息处理行为损害了您的合法权益，您还可以通过向被告住所地有管辖权的法院提起诉讼来寻求解决方案。
	</p>
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