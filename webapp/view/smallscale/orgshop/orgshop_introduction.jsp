<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
.list label{ font-weight:bold; }
</style>

<!--公司介绍-->
<div class="introduction">
	<div class="titleright">
		<h2 class="marginright">公司介绍</h2>
	</div>
	<div class="conrig">
		<img class="orgImg" src="<%=request.getContextPath()%>/view/resource/skin/orgshop/img/figure.jpg" height="103" width="96" class="marlf"/>
		<div class="list">
	  		<ul>
				<li><label>所属行业：</label><span>${supplier.orgInfo.belongIndustry.name}</span></li>
				<li><label>企业类型：</label><span>${supplier.orgInfo.entPrptCN}</span></li>
				<li><label>人员规模：</label><span>${supplier.orgInfo.unitScapeCN}</span></li>
				<li><label>开业日期：</label><span>${supplier.orgInfo.begainDate}</span></li>
	   			<li><label>法定代表人：</label><span>${supplier.orgInfo.company.croporate}</span></li>
	   			<li><label>公司网址：</label><span><a href="${supplier.orgInfo.webUrl}" target="_blank">${supplier.orgInfo.webUrl}</a></span></li>
	   			
	   			<li><label>公司地址：</label><span>${supplier.orgInfo.company.address}</span></li>
	  			<li><label>企业简介：</label><span>${supplier.orgInfo.descCn}</span></li>
				<li><label>企业产能：</label><span>${supplier.orgInfo.entCapacity}</span></li>
	   			<li><label>经营范围：</label><span>${supplier.orgInfo.bidForRangeName}</span></li>
	   			<li><label>主营产品：</label><span>${supplier.orgInfo.mainProducts}</span></li>
	   		</ul>
   		</div>
	</div>
</div>