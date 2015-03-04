<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/esdemo/projectmanager/abeyance_list.js"></script>
<div class="formLayout">
	 <form id="orgInfoForm" method="post">
			<ul style="padding-top:0px">
				<li>
						<div>

							  <li></li>
							  <!--<authz:authorize ifAnyGranted="view/es/planform/consign/consignListForAccept.jsp">
	        		 				<li><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK001</a>---<a href='#' target='' onClick='javascript:projectList.modify(1)'>广东省招标采购中心采购服务器</a>---<font color="red">需要您发布招标公告</font>---(2000-09-01) </li>
	      	  				  </authz:authorize>
							  <authz:authorize ifAnyGranted="view/esdemo/projectmanager/project_list_exception.jsp">
	    						<li><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK002</a>---<a href='#' target='' onClick='javascript:projectList.modify(1)'>广东省成套招标采购中心采购笔记本</a>---<font color="red">需要您起草公告</font>---(2000-09-01) </li>
	       	 				  </authz:authorize>							    
							  <authz:authorize ifAnyGranted="ProjectController.do?method=searchProjectListDljg&userType=buyer">
	    						<li><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK003</a>---<a href='#' target='' onClick='javascript:projectList.modify(1)'>广东省采购中心采购汽车</a>---<font color="red">需要您开标</font>---(2000-09-01) </li>
	       	 				  </authz:authorize>
	       	 				  <authz:authorize ifAnyGranted="view/es/planform/toolshow/project_info_show.jsp">
	    						<li><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK003</a>---<a href='#' target='' onClick='javascript:projectList.modify(1)'>广东省采购中心采购汽车</a>---<font color="red">需要您开标</font>---(2000-09-01) </li>
	       	 				  </authz:authorize> -->    

							  
						</div>
			    </li>
			    
			  </ul> 		 
	</form>
</div>