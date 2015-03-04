<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.styleRC {text-align:center;color: red;}
.styleR {text-align: center;}
.sysDis {display: none;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/moreProjectListPage.js"></script>
<div class="partContainers">
  <div class="formLayout form2Pa">        
	<form id="searchUnitForm">
    	<ul>
			 <li class="fullLine functionBtnDiv">
				<label>名称：</label>
				<input type="text" name="unitName" id="unitName_Id"/>
				&nbsp;&nbsp;&nbsp;<button name="searchUnit" id="searchUnitId"><span>搜索</span></button>
				&nbsp;&nbsp;&nbsp;<button name="sureUnit" id="sureUnit"><span>确定</span></button>
				&nbsp;&nbsp;&nbsp;<button name="closeUnit" id="closeUnit"><span>关闭</span></button>
			</li>
		</ul>
	</form>
	</div>
<table class="tableList" id="unit_li">	
	<tbody id="expertRuleTableId">
			<c:forEach items="${projectList}" var="project" varStatus="i">
			<tr>
			<td class="styleR"><input type="checkbox" id="${project.objId}" name="project_check" value="${project.tenderTypeCN}" /></td>
			<td>${project.projName}</td>
			</tr>
			</c:forEach>
	</tbody>
 </table>
				<div class="functionBtnDiv styleR">
					<button type="button" id="sureUnitUp"><span>确定</span></button>
					<button type="button" id="closeUnitUp"><span>关闭</span></button>
				</div>
</div>