<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>议价厅</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/table.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/button.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/bargainingHall.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/talk/s_talk_hall_supplier.js"></script>
</head>
<body>
<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
<input type="hidden" id="bargainId" value="<%=request.getParameter("bargainId")%>"/>
<input type="hidden" id="projectObjId" value="${talkProject.objId}" />
<input type="hidden" id="buyerObjId" value="${talkProject.buyersId}" />
<input type="hidden" id="orgId" value="${orgInfo.objId }" />
<input type="hidden" id="orgName" value="${orgInfo.orgName }" />
<input type="hidden" id="isLogin" value="${param.isLogin }" />

<!--页面容器 开始-->
<div class="container">  
	<h1 class="yjt" id="bargainProject"><span>${talkProject.projName}</span></h1>
	<div class="countdown">
      	剩余议价时间：<span class="highlight" id="endDate">0天0小时0分</span>
      	<input type="hidden" id="evalEndTime" value="" name="<fmt:formatDate value="${talkProject.evalEndTime}" pattern="yyyy/MM/dd HH:mm:ss"/>"/>
      	<button id="toBargainBtn" type="button" class="toQuote"><span>我要报价</span></button>
      	<button id="quit" type="button" class="exitBargainingRoom"><span>退出议价厅</span></button>  
	</div>
	<div class="webContent">
		<div class="formLayout form2Pa" id="bargainInfo">
			<form id="MyBargainingForm" method="post">
				<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
				<input type="hidden" name="type" id="type" value="<c:out value="${param.type}"/>"/>
				<h4><span>议价单信息</span></h4>
				<ul></ul>
			</form>
		</div> 
		<div class="frameMS31">
			<div class="frameMain" style="overflow:none;">
				<div id="supplierNameList">
					<div id="goodsContent">
						<h3><span id="currentOrgInfoName">${talkProject.buyersName}</span></h3>
						<div id="c1" class="chat" style="overflow: auto; height: 326px;">
							<ul></ul>
						</div>
					</div>
				</div>
			</div>
             
			<div class="frameSub" style="overflow:none;">
				<div class="priceHistory" id="priceHistory">
					<h4><span>报价历史</span></h4>
					<div style="overflow: auto; height: 330px;">
						<ul></ul>
					</div>
				</div>
			</div>
		</div>
          
		<div class="messaging">
			<label id="sendOrgInfoLabel" title="${orgInfo.orgName }">${fn:substring(orgInfo.orgName,-1,8)}...</label>
			<textarea name="sent" id="contentArea"></textarea>
            <button id="sendSupplierContentBtn" type="button"></button>
		</div>
	</div>
</div>
<!--页面容器 结束-->
</body>
</html>