<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/rol/roleList.js"></script>

		
<div id="epsTabs">
    <ul>
      <li id="postRoleTab">
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

