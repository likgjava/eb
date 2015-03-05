<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
$(document).ready(function(){
	//显示机构详情
	showWellOrgInfoDetail = function(objId) {
		//判断是供应商还是采购人
		$.getJSON($('#initPath').val()+"/OrgInfoController.do?method=getObjectQuery&queryColumns=buyerId", {"objId":objId} , function(json){
			//采购人
			if(json.result[0]["buyerId"]) {
				common.geToBuyerDetail(json.result[0]["buyerId"]);
			}
			//供应商
			else{
				common.goToOrgShop(objId);//供应商跳到商铺页面
			}
		});
	}

	//跳转到社区和主题列表页面
	toShowBusinessMemberListView  = function(){
		$('#sysContent').loadPage($('#initPath').val()+'/BusinessMemberShowController.do?method=toShowBusinessMemberListView&rp=20&page=1');
	}
});
</script>
         
<div class="businessN">
	<h3>商圈会员</h3>
	<c:forEach var="recommendMem" items="${recommendMenberList}">
	<div class="specialChoice">
	    <h4>
	    	<a href="javascript:void(0);" onclick="showWellOrgInfoDetail('${recommendMem.orgInfo.objId}');return false;" title="${recommendMem.orgInfo.orgName}">
	    		<strong>
	    		<c:choose>
			  		<c:when test="${fn:length(recommendMem.orgInfo.orgName)> 10 }">${fn:substring(recommendMem.orgInfo.orgName,0,9) }…</c:when>
			  		<c:otherwise>${recommendMem.orgInfo.orgName}</c:otherwise>
				</c:choose>
				</strong>
	    	</a>
	    </h4>
	    <p class="personalPic">
	    	<a href="javascript:void(0);" onclick="showWellOrgInfoDetail('${recommendMem.orgInfo.objId}');return false;" title="${recommendMem.orgInfo.objId}">
	    		<img src="AttachmentController.do?method=showImg&objId=${recommendMem.orgInfo.logo}"/>
	    	</a>
	    </p> 
	    <p class="personalInfo">
	    	<c:choose>
	  			<c:when test="${fn:length(recommendMem.orgInfo.descCn)> 25 }">${fn:substring(recommendMem.orgInfo.descCn,0,25) }… </c:when>
	  			<c:otherwise>${recommendMem.orgInfo.descCn }</c:otherwise>
	  		</c:choose>
	    	<a href="javascript:void(0);" onclick="showWellOrgInfoDetail('${recommendMem.orgInfo.objId}');return false;">[ 详情 ]</a>
	    </p> 
	</div>
	</c:forEach>
</div>
<span class="more"><a href="javascript:void(0);" onclick="toShowBusinessMemberListView();" title="更多" class="right">更多</a></span>