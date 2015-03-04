<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/esdemo/projectmanager/abeyance_list.js"></script>
<div class="formLayout">

	   <form id="orgInfoForm" method="post">
			<ul style="padding-top:0px">
				<li>
						<div>
						  <ol>
							  <li></li>
							    <li><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK001</a>---<a href='#' target='' onClick='javascript:projectList.modify(1)'>广东省招标采购中心采购服务器</a>---<font color="red">需要您发布<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></font>---(2000-09-01) </li>
							    <li><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK002</a>---<a href='#' target='' onClick='javascript:projectList.modify(1)'>广东省成套招标采购中心采购笔记本</a>---<font color="red">需要您起草<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></font>---(2000-09-01) </li>
							    <li><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK003</a>---<a href='#' target='' onClick='javascript:projectList.modify(1)'>广东省采购中心采购汽车</a>---<font color="red">需要您<dm:out value="local__OpenTendering__openBid_zh">开标</dm:out></font>---(2000-09-01) </li>
						  </ol>
							  
						</div>
			    </li>
			    
			  </ul> 		 
	</form>
</div>