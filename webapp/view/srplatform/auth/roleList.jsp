<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/roleList.js"></script>

<div class="treePage frameMainSub frameMs12 fullScreen ">
	<div class="treeOutside frameMain">
		<div id="organizationTree" style="overflow-x: auto;overflow-y: hidden"  class="treeContentDiv"></div>
	</div>
	<div class="epsContentMenu" >
	  <ul>
	  </ul>
	</div>
	
	
	<div class="treeRight frameSub" id="treeRight">
		
		<div id="epsTabs">
		    <ul>
		      <li >
		        <a href="#roleListAllId"><span id="roleListAll">机构角色</span></a>
		      </li>
		      <li id="roleListDefaultIdTab">
		        <a href="#roleListDefaultId"><span id="roleListDefault">系统角色</span></a>
		      </li>
		    </ul>
		    
		    <div id="roleListAllId">
		    </div>
		    <div id="roleListDefaultId" >
		    </div>
	 	</div>
	</div>
</div>

