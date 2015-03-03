<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>${requirement.title}-阳光易购采购交易平台</title>
<meta name="keywords" content="采购，政府采购，企业采购，求购信息，供应，供应信息" />
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/seller.css" />

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
</head>
<body>
<div id="container">
	<!-- 头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!-- 头部结束-->
    <!--主要内容 开始-->
  	<div id="sysContent" class="page">
		<!--Content start--> 
		<div id="content" class="buy-cont w952 layout-p32a24">
	            <!--[if !IE]>OfferMain start<![endif]-->
				<a name="playerStart" id="playerStart"></a>
					<div class="offer-main-wraper clearfix">
						<div class="offer-title">
							<h1>${requirement.title}</h1>
						</div>
						<div class="offer-prototype clearfix">
							<div class="offer-attr">
								<div class="offer-attr-wrap">
									<!--专业化参数-->
									<table class="attr-list">
							            <tbody>
							                <tr>
							                    <td class="attr-t">采购品目：</td>
							                    <td><span>${requirement.category.categoryName}</span>
							                    </td>
							                </tr>
							                <tr>
							                    <td>区域名称：</td>
							                    <td><span>${requirement.districtNames}</span></td>
							                </tr>
							                <tr>
							                    <td>采购数量：</td>
							                    <td><span>${requirement.purchaseQty}</span></td>
							                </tr>
							                <tr>
							                    <td>采购预算：</td>
							                    <td><em class="em"><fmt:formatNumber value="${requirement.purchaseBudget}" pattern="#,##0.00#" /></em>（元）</td>
							                </tr>
							         </tbody>
									</table>
									
									<div class="quote-w">
									    <a href="javascript:requrement_show_detail.regRequirementDiv('${requirement.objId}');" class="quote-btn dmtrack" id="toRegRequirement" <c:if test="${remainSignUpTime<=0}">style="display:none"</c:if> ><span>点此报价</span></a>
									</div>
									<!--报价、发布日期、过期日期、所在地-->
										<table class="attr-list-add">
										   <tbody>
										   <tr>
											<td class="attr-t">发布日期：</td>
											<td><fmt:formatDate value="${requirement.pubTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										</tr>
										<tr>
											<td>过期日期：</td>
											<td><fmt:formatDate value="${requirement.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										</tr>
											<tr>
										    <td>所&nbsp;&nbsp;在&nbsp;地：</td>
										    <td><span class="wordwarp">${requirement.districtNames}</span></td>
										</tr>
										    	</tbody>
										</table>
								</div>
							</div>
	                        <div class="offer-gallery">
								<div id="showImg" style="padding-left:15px;">
							 			<ul id="goodsImg">
							 				<c:if test="${fn:length(images) <= 0}">
												<li><img src="<%=request.getContextPath()%>/view/resource/images/404.gif" id="mainImg"></img></li>
											</c:if>
											<c:forEach var="image" items="${images}" varStatus = "status" >
												<li><img src="<%=request.getContextPath()%>/AttachmentController.do?method=showImg&objId=${image.objId}&fileNameSuffix=_320*320" <c:if test="${status.index==0}">id="mainImg"</c:if> /></li>
											</c:forEach>										
										</ul>
								</div>
							</div>
							<!--会员信息-->
							
	<div class="member-basic">
		<div class="member-basic-timer">
			<ul>
				<c:choose>
					<c:when test="${remainSignUpTime > 0}">
						<li><span class="on">剩余报名时间：</span><div id='remainTime' name="${remainSignUpTime}"><strong>0天0时00分00秒</strong></div></li>
					</c:when>
					<c:otherwise>
						<li><span class="over">报名时间已结束！</span></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<!--买家信息 开始-->
		<div class="member-basic-inner clearfix">
			<div class="mm-hd"><h2>买家信息</h2></div>
			<c:choose>
				<c:when test="${isShowContact==true}"><!-- 显示联系方式 -->
				<div class="company-box">
					<c:if test="${requirement.isAnonymous==true}">
					<h3><a class="dmtrack" href="javascript:void(0);" onclick="common.geToBuyerDetail('${requirement.pubOrg.buyerId}');return false;">${requirement.pubOrg.orgName }</a></h3>
				    <div class="info-box">
				    	<p>联系人：<span class="wordwarp">${requirement.linkMen}</span></p>
				    	<p>联系电话：<span class="contact-link">${requirement.linkTel}</span></p>
				    	<p>电子邮件：<span class="contact-link">${requirement.email}</span></p>
				        <p>所在地区：<span class="wordwarp">${requirement.districtNames}</span></p>
				    </div>
					</c:if>
					<c:if test="${requirement.isAnonymous!=true}">
					<spring:message code="smallscale.requirement.link"></spring:message>
					</c:if>
				</div>
				</c:when>
				<c:otherwise><!-- 不显示联系方式 -->
				<div class="mm-bd clearfix no-login">
					<div class="free-mm-tips"><p>您是普通会员，无法查看买家联系方式</p></div>
					<p class="mm-p">升级为商圈会员，随之电话联系买家。<a href="javascript:void(0);" onclick="requrement_show_detail.toBizarea();" class="dmtrack">了解商圈会员服务</a></p>
					<p class="mm-p">咨询热线：010-88356236</p>
				</div>
				</c:otherwise>
			</c:choose>
		</div>
		<!--买家信息 结束-->
	<div class="ww-download-widget">
		<div class="postoffer">
			<a href="javascript:void(0);" onclick="requrement_show_detail.torequirementform();" class="dmtrack">我也要发布需求信息</a>
		</div>
	</div>
	</div>
	</div>
				<div class="offer-desc-box">
					<div class="od-hd"><h3>详细信息</h3></div>
					<div class="od-bd">
						<div class="offer-desc fd-editor"><span class="at_0">${requirement.discription}</span></div>
						<div class="fav2report">
							<a href="javascript:void(0)" title="将此公告推荐给好友" onclick="requrement_show_detail.shareRequirement('${requirement.objId}');return false;"><img src="<%=request.getContextPath() %>/view/resource/skin/skin07/img/map.gif"/> 推荐</a>&nbsp;&nbsp;
							<a href="javascript:void(0)" title="打印页面内容" onclick="requrement_show_detail.print();"><img src="<%=request.getContextPath() %>/view/resource/skin/skin07/img/printer.png"/> 打印</a>&nbsp;&nbsp;
							<a href="javascript:self.close()" title="关闭本页面"><img src="<%=request.getContextPath() %>/view/resource/skin/sysicon/16/cross.png"/> 关闭本页</a>
						</div>
					</div>
				</div>
				</div>
				<div class="similar-product-widget">
					<div class="similar-product-hd">
						<h3>更多同类采购信息</h3>
					</div>
					<div class="similar-product-bd">
						<table class="similar-product-table">
							<thead>
								<tr>
									<th class="spt-title">标题</th>
									<th>采购品目</th>
									<th>采购数量</th>
									<th>采购预算</th>
									<th>发布时间</th>
									<th>采购区域</th>
									<th>结束时间</th>
								</tr>
							</thead>
							<tbody>	
									<c:forEach var ="requirementSame" items="${requirementList}">
										<tr>
											<td class="spt-title"><a href="<%=request.getContextPath()%>/RequirementInfo/${requirementSame.objId}" target="_blank" class="dmtrack">${requirementSame.title }</a></td>
											<td>${requirementSame.category.categoryName }</td>
											<td>${requirementSame.purchaseQty}</td>
											<td><fmt:formatNumber value="${requirementSame.purchaseBudget}" pattern="#,##0.00#" /></td>
		                					<td><fmt:formatDate value="${requirementSame.pubTime}" pattern="yyyy-MM-dd"/></td>
		                					<td>${requirementSame.districtNames}</td>
		                					<td><fmt:formatDate value="${requirementSame.endTime}" pattern="yyyy-MM-dd"/></td>
										</tr>
									</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
	</div>
	</div>
	<!--主要内容 结束-->
	<!-- 脚开始 -->
	<div class="footer">
	    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!-- 脚结束 -->
	<!--在线客服开始-->
	<%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
	<!--在线客服结束-->
</div>
</body>

<script type="text/javascript">

var requrement_show_detail = {};

requrement_show_detail.signUpInterval = "";

//推荐项目需求（通过发送邮件分享给好友）
requrement_show_detail.shareRequirement = function(requirementId){
	//判断是否登录
	if(common.isLogin(true,"请先登录再进行推荐！")){
		$.epsDialog({
	        title:'将此需求信息推荐给好友',
	        width:320,
	        height:150,
	        url:$('#initPath').val()+'/view/agreement/purchaserequire/share_requirement.jsp?requirementId='+requirementId
		});
	}
}


//打印方法
requrement_show_detail.print = function(){
	$("#sysContent").printArea();
}

//定时刷新时间
requrement_show_detail.flushRemainSignUpTime=function(){
	var remainTime = parseInt($("#remainTime").attr("name"));
	if(remainTime <= 0){
		clearInterval(requrement_show_detail.signUpInterval);
		$("#toRegRequirement").hide();
		return ;
	}
	$("#remainTime").html("<strong>"+getRemainTime(remainTime)+"</strong>");
	$("#remainTime").attr("name", (remainTime-1000));
}

//弹出报名窗口
requrement_show_detail.regRequirementDiv = function(requirementId){
	//是否已经登录
	if(common.isLogin(true,true)){
		//验证是否有过报名
		$.getJSON($("#initPath").val()+"/RequirementRegController.do?method=checkRequirementReged",{objId:requirementId},function(json){
			if(json.isReged!='true'){
				//是否控制为供应商if(!common.isHasRole('s')){alert("请成为供应商后再操作！"); return ; }
				$.epsDialog({
					id:"requirementDiv",
					title:"需求报名",
					url:$("#initPath").val()+"/RequirementRegController.do?method=toRequirementRegForm&requirementId="+requirementId
				})
			}else{
				alert("该需求您已经报名！");
			}
		});
	}
}


//跳转到发布需求
requrement_show_detail.torequirementform = function(){
	if(common.isLogin(true,true)){
		window.open($("#initPath").val()+"/RequirementController.do?method=toChooseCategory");
	}
}

//查看商圈会员服务
requrement_show_detail.toBizarea = function(){
	fnRemoveOtherMain();
	$("#sysContent").loadPage($('#initPath').val()+'/BusinessMemberShowController.do?method=toShangQuanIndexView&rp=10');
	return false;
}

$(document).ready(function(){
	$('ul.#goodsImg').bxGallery({maxwidth:'220',maxheight:'220'});//图片显示控件

	//需求报名已经结束
	if($("#remainTime").attr("name") > 0) {
		requrement_show_detail.signUpInterval = setInterval("requrement_show_detail.flushRemainSignUpTime()", 1000);
	}
})
</script>
</html>