<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/project.js"></script>

<div id="projectTitle">当前项目：${project.projName} <span class="detailsSwitch" id="projectInfoClick">查看详情</span></div>
<!-- 公有数据隐藏显示 -->
<!-- 项目ID -->
<input type="hidden" value="${project.objId }" name="projectId" id="projectId"/>
<!-- 用户类型 -->
<input type="hidden" value="${param.userType }" name="userType" id="userType"/>
<div id="projectInfo" class="formLayout form2Pa" style="display: none;"></div>
<div id="projectNav" ></div>
<div id="projectContent">项目操作信息</div>
