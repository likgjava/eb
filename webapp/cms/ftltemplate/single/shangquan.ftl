<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/smallscale/css/shangquan.css"/>

<!--系统内容-->
<div id="shangquanCommunityInfo" class="communityInfo">
	<!-- 左栏  开始 -->
	<div id="contentSub" class="shangquanLeft">
		<!-- 陶朱公商圈章程   开始 -->
		<div class="leftCol">
			<h2>陶朱公商圈章程</h2>
			<p>1、效法先贤商圣陶朱公，追求义、利之和谐统一；君子爱财、取之有道；利已利他，几度聚财，几度散财。</p>
			<p>2、缔造纯净、祥和、阳光的交易环境；道法自然、返璞归真。</p>
			<p>3、弘扬商道，坚守诚信；摒弃权谋，远离尔虞我诈，杜绝暗箱操作，鄙视以权谋私；崇尚公平、合理之交易规则，追求利益之共赢与人性之美好。</p>
			<p>4、群英际会，激浊扬清；领略时代理念之精华，品味商界智慧之盛宴。</p>
			<div class="more"><a href="http://www.ebid360.com/xygh/IndexViewController.do?method=index&contentMainUrl=/xygh/view/staticpags/foot/ff8080812eec2137012f00bca3612906.html&contentSubUrl=/xygh/view/staticpags/load/left_cms.html">...查看详细内容</a></div>    
		</div>
		<!-- 陶朱公商圈章程  结束 -->
		<!-- 商圈文化   开始  -->
		<div class="leftCol">
			<h2>${sqwh_index.name!}</h2>
			<div class="img"><img src="view/resource/skin/smallscale/img/shangquangFl.png" alt=""/></div>
			<h4>陶朱公范蠡像</h4>
			<ul class="zixun">
				<@f.gpcsoft list=sqwh ftl="list2" titLen=13 dataFormat=''/>
			</ul>  
			<div class="more"><a href="javascript:void(0);" id="${sqwh_index.url!}" class="sq_index">...更多商圈文化</a></div>       
		</div>
		<!-- 商圈文化   结束 -->
		<!-- 诸子百家与传统文化   开始 -->
		<div class="leftCol">
			<h2>${zzbjyctwh_index.name!}</h2>
	       	<h4>${bjdbrw_index.name!}</h4>
			<#if bjdbrw?size!=0>
		       <#list bjdbrw as l>
			        <div class="center"><img src="${l.imgUrl!}" style="width:140px;height:140px;"/></div>
			        <div class="center">${l.title!}</div>
			        <div class="center"><a href="javascript:void(0);" id="${l.url!}" class="sq_index">${l.str2!}</a></div>
		       </#list>
		    </#if>
	        <h4>${bjxxjh_index.name!}</h4>
	        <ul class="zixun">
				<@f.gpcsoft list=bjxxjh ftl="list2" titLen=13 dataFormat=''/>
	        </ul>
	        <div class="more"><a href="javascript:void(0);" id="${zzbjyctwh_index.url!}" class="sq_index">...更多传统文化</a></div>
		</div>
		<!-- 诸子百家与传统文化    结束 -->
	</div>
    <!-- 左栏  结束-->
    
    <!-- 中间栏 开始-->
    <div id="contentMain" class="shangquanCenter">
		<!-- conTitle 开始-->
		<div id="conTitle" class="hidden">
			<div class="navCurrent"></div>
      	</div>
      	<!-- conTitle 结束-->
      	<!-- conBody 开始 -->
		<div id="conBody">
      	<!-- 商圈资讯  开始 -->
      	<div class="centerCol">
			<h2>${sqzx_index.name!}</h2>
           	<ul class="zixun">
				<@f.gpcsoft list=sqzx ftl="sqzxList" titLen=20 dataFormat='yyyy-MM-dd'/>
           	</ul>
          	<div class="more"><a href="javascript:void(0);" id="${sqzx_index.url!}" class="sq_index" class="right">更多</a></div>
		</div>
      	<!-- 商圈资讯  结束 -->
      	<!-- 最新商务活动  开始 -->
		<div class="centerCol">
			<h2>最新${swhd_index.name!}</h2>
			<#if swhd?size!=0>
		       <#list swhd as l>
					<div class="newActivity">
						<p><img src="${l.imgUrl!}" /></p>
						<ul>
							<li><label>商务主题：</label> <span><a href="${l.str3!}" target="_blank">${l.getTit(18)}</a></span></li>
							<li><label>组织机构：</label> <span>${l.author!}</span></li>
							<li><label>商务类型：</label> <span>${l.str1!}</span></li>
							<li><label>开始时间：</label> <span>${(l.date1)!?string("yyyy-MM-dd")}</span></li>
							<li><label>结束时间：</label> <span>${(l.date2)!?string("yyyy-MM-dd")}</span></li>
							<li><label>规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;模：</label> <span>${l.str2!}</span></li>
							<li><label>活动专员：</label> <span>${l.str8!}&nbsp;&nbsp;${l.str4!}</span></li>
						</ul>
					</div>
		       </#list>
		    </#if>
        	<div class="more"><a href="javascript:void(0);" id="${swhd_index.url!}" class="sq_index" class="right">更多</a></div>
		</div>
        <!-- 最新商务活动  结束 -->
        <!-- 商圈最新主题  开始 -->
		<div class="centerCol">
			<h2>商圈最新主题</h2>
			<div id="newShangquanTopicDiv">
			</div>
		</div>
        <!-- 商圈最新主题  结束 -->
        <!-- 商圈服务项目  开始 -->
        <div class="centerCol">
        	<h2>商圈服务项目</h2>
			<div class="serverProject">
				<ul>
					<li><span class="title">【协议供货】</span><span class="content">联盟采购  企业团购  一起争取最低价！</span></li>
					<li><span class="title">【交易速配】</span><span class="content">买家卖家  千里姻缘e线牵  买的轻松 买的容易！</span></li>
					<li><span class="title">【展贸洽会】</span><span class="content">产品推介  订单上门！</span></li>
					<li><span class="title">【行业会议】</span><span class="content">组织行业各路资源  共同探讨行业发展大计！</span></li>
					<li><span class="title">【采购代理】</span><span class="content">采购不用愁  我们来帮您。买的快速  买的便宜  买的质优！</span></li>
					<li><span class="title">【商业沙龙】</span><span class="content">以商会友  商脉通天下！</span></li>
				</ul>
			</div>
		</div>
        <!-- 商圈服务项目  结束 -->
        <!-- 最新加入会员  开始 -->
        <div class="centerCol">
			<%@ include file="/view/smallscale/business/new_business_member.jsp" %>
		</div>
        <!-- 最新加入会员  结束 -->
      </div>
      <!-- conBody 结束-->
    </div>
    <!-- 中间栏 结束 -->
    <!-- 右栏  开始 -->
    <div id="contentSupp" class="shangquanRight">
		<!-- 商道.名人堂  开始  -->
		<div class="rightCol">
			<h2><a href="javascript:void(0);" id="${sd_mrt_index.url!}" class="sq_index">${sd_mrt_index.name!}</a></h2>
			<#if sd_mrt?size!=0>
		       <#list sd_mrt as l>
			        <div class="center"><img src="${l.imgUrl!}" style="width:140px;height:140px;"/></div>
			        <div class="center">${l.title!}</div>
			        <div class="center"><a href="javascript:void(0);" id="${l.url!}" class="sq_index">${l.str2!}</a></div>
		       </#list>
		    </#if>
		</div>
		<!-- 商道.名人堂  结束  -->
	    <!-- 名人访谈   开始  -->
		<div class="rightCol">
			<h2><a href="javascript:void(0);" id="${mrft_index.url!}" class="sq_index">${mrft_index.name!}</a></h2>
			<ul class="zixun">
				<@f.gpcsoft list=mrft ftl="list" titLen=17 dataFormat=''/>
			</ul>
      	</div>
      	<!-- 名人访谈   结束  -->
      	<!-- 展会信息   开始  -->
		<div class="rightCol">
			<h2><a href="javascript:void(0);" id="${zhxx_index.url!}" class="sq_index">${zhxx_index.name!}</a></h2>
			<#if zhxx?size!=0>
		       <#list zhxx as l>
		       		<div style="dispaly:block;overflow:hidden;padding:2px;white-space:nowrap;margin-bottom:5px;padding-right:2px;clear:both;">
						<p style="display:inline;float:left;"><img src="${l.imgUrl!}" style="width:70px;height:60px;"/></p>
						<ul style="float:left;width:150px;">
							<li><b><a title="${l.title!}：${l.description!}" href="${l.outUrl!}" target="_blank"><#if l.title?length lt 12>${l.title!}<#else>${l.title[0..11]}...</#if></a></b></li>
							<li>
								<font style="color:#444;size:11px;">
								时间：${(l.date1)!?string('MM月dd日')}-${(l.date2)!?string('MM月dd日')}
								<br/>
								地点：<span title="${l.str1!}"><#if l.str1?length lt 9>${l.str1!}<#else>${l.str1[0..8]}...</#if></span>
								</font>
							</li>
						</ul>
					</div>
		       </#list>
		    </#if>
		</div>
		<!-- 展会信息  结束  -->
    </div>
    <!-- 右栏  结束 -->
</div> 
<!-- 商圈列表  开始 -->
<div class="communityList" id="recommendCommunityList">
	<%@ include file="/view/smallscale/business/recommond_business_area.jsp" %>
</div>
<!-- 商圈列表 结束 -->


<script type="text/javascript">
var shangquanIndex={};

//跳转到社区和主题列表页面(显示更多‘最新加入会员’)
shangquanIndex.toShowBusinessMemberListView  = function(){
	$('#sysContent').loadPage($('#initPath').val()+'/BusinessMemberShowController.do?method=toShowBusinessMemberListView&rp=20&page=1');
}

//显示采购人或供应商详情
shangquanIndex.showOrgDetail = function(orgInfoId, buyerId, supplierId) {
	if(orgInfoId==null || orgInfoId==""){return ;}
	if(buyerId!=null && buyerId!=""){
		var targetUrl = $('#initPath').val()+"/BuyerShowController.do?method=getBuyerInfo::objId=" + buyerId;
		var contentSuppUrl = $('#initPath').val()+'/BuyerShowController.do?method=getRecommendBuyer::rp=10::page=1';
		window.open( $('#initPath').val()+"/IndexViewController.do?method=index&contentMainUrl="+targetUrl+"&contentSuppUrl="+contentSuppUrl );
	}else{
		common.goToOrgShop(orgInfoId);
	}
}
$(document).ready(function(){
    //商圈选中样式
	changeTabsCss('bizarea');

	$('a.sq_index').click(function(){
	 	var contentSubUrl = "view/staticpags/load/leftSq.html";
		window.open ($('#initPath').val()+"/IndexViewController.do?method=index&viewUrl="+$(this).attr('id')+"&contentSubUrl="+contentSubUrl);
	});

	//加载最新的话题
	$("#newShangquanTopicDiv").loadPage($("#initPath").val()+"/ForumTopicShowController.do?method=getIndexTopicList&rp=10");
})
</script>
