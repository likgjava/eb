<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script>
//比较两个时间的大小
function cha(timeStr1, timeStr2){
	var time1 = new Date(Date.parse(timeStr1));
	var time2 = new Date(Date.parse(timeStr2));
	return time1.getTime() - time2.getTime();
}
$(document).ready(function(){
	var currentTime = $("#currentTime").val();
	$("#bulletinListDetail").find(".photo").each(function(i, domEle){
		var bulletinType = $(domEle).find("input[name=bulletinType]").val(); //公告类型
		var startTime = ''; //开始时间
		var endTime = ''; //结束时间
		
		//招标公告
		if(bulletinType == '01'){
			startTime = $(domEle).find("input[name=signUpSTime]").val();
			endTime = $(domEle).find("input[name=signUpETime]").val();
		}else{
			startTime = $(domEle).find("input[name=evalStartTime]").val();
			endTime = $(domEle).find("input[name=evalEndTime]").val();
		}

		var imgName = 'notes.png';

		//未开始
		if(cha(startTime, currentTime)>0){
			if(bulletinType == '01'){imgName='nostart_z.png';}else if(bulletinType == '12'){imgName='nostart.png';}
		}
		//已结束
		else if(cha(currentTime, endTime)>0){
			if(bulletinType == '01'){imgName='over_z.png';}else if(bulletinType == '12'){imgName='over.png';}
		}
		//进行中
		else if(cha(startTime, currentTime)<0 && cha(endTime, currentTime)>0){
			if(bulletinType == '01'){imgName='being_z.png';}else if(bulletinType == '12'){imgName='being.png';}
		}

		$(domEle).find("span").html('<img src="<c:url value="view/resource/skin/pubservice/img/'+imgName+'" />">');
	});
});
</script>
<input id="currentTime" type="hidden" name="currentTime" value="<fmt:formatDate value="${currentTime}" pattern="yyyy/MM/dd HH:mm:ss"/>">
<div class="list-content" id="bulletinListDetail">
	<ul class="list-view hlisting sell">
		<c:forEach var="bulletin" items="${PAGERESULT.data}">
			<li class="list-item">
				<h3 class="summary"><a class="EventCanSelect" href="javascript:void(0);" onclick="common.goToBulletinDetail('${bulletin.objId}');return false;">${bulletin.bullTitle}</a></h3>
		 		<div class="photo">
		 			<input type="hidden" name="bulletinType" value="${bulletin.bullType}">
		 			<input type="hidden" name="evalStartTime" value="<fmt:formatDate value="${bulletin.project.evalStartTime}" pattern="yyyy/MM/dd HH:mm:ss"/>">
		 			<input type="hidden" name="evalEndTime" value="<fmt:formatDate value="${bulletin.project.evalEndTime}" pattern="yyyy/MM/dd HH:mm:ss"/>">
		 			<input type="hidden" name="signUpSTime" value="<fmt:formatDate value="${bulletin.signUpSTime}" pattern="yyyy/MM/dd HH:mm:ss"/>">
		 			<input type="hidden" name="signUpETime" value="<fmt:formatDate value="${bulletin.signUpETime}" pattern="yyyy/MM/dd HH:mm:ss"/>">
					<a href="javascript:void(0);" onclick="common.goToBulletinDetail('${bulletin.objId}');return false;"><span></span></a>
				</div>
		 		<ul class="attribute nomargin"><!-- style="float:right;" by yucy -->
		            <li class="price"> 采购金额: <em style="float:right;"> ￥<fmt:formatNumber value="${bulletin.project.budgetTotalMoney}" pattern="#,##0.00#" /></em></li>
		            <li class="place"> ${bulletin.project.createUser.emp.company.town.parent.parent.name},${bulletin.project.createUser.emp.company.town.parent.name},${bulletin.project.createUser.emp.company.town.name}</li>
		        </ul>
		        <div class="extend">采购方式：${bulletin.project.ebuyMethodCN}</div>
				<p class="seller lister hCard">
					采购单位：${bulletin.project.createUser.emp.company.name}<br/>
					<c:choose>
						<c:when test="${bulletin.bullType == '01'}">
							 报名时间：<font color="red"><fmt:formatDate value="${bulletin.signUpSTime}" pattern="yyyy-MM-dd HH:mm"/> ~ <fmt:formatDate value="${bulletin.signUpETime}" pattern="yyyy-MM-dd HH:mm"/></font>
						</c:when>
						<c:when test="${bulletin.bullType == '12'}">
							报价时间：<font color="red"><fmt:formatDate value="${bulletin.project.evalStartTime}" pattern="yyyy-MM-dd HH:mm"/> ~ <fmt:formatDate value="${bulletin.project.evalEndTime}" pattern="yyyy-MM-dd HH:mm"/></font>
						</c:when>
					</c:choose>
		        </p>
		    </li>
		</c:forEach>
	</ul>
</div>
<div>
	<%@ include file="/view/pubservice/common/pageDirection.jsp" %>
</div>