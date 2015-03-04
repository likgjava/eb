<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.styleRC {text-align:center;color: red;}
.styleR {text-align: center;}
.sysDis {display: none;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/expertrule/experts_unit.js"></script>
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

	  <input type="hidden" name="unit" id="unit_Id" value=""/>
      <input type="hidden" name="unitName" id="unit_Name" value=""/>
<div id="chooseUnit" class="styleRC">
	<span id="warnId">暂时没有选择单位</span>
</div>
<table class="tableList" id="unit_li">	
	<tbody id="expertRuleTableId">
			<c:forEach items="${unitArr}" var="unit" varStatus="i">
		<tr>
					<td class="styleR"><input type="checkbox" onclick="expertUnit.checkValue('${unit.column_value}','${unit.column_name}')" id="unit_${unit.column_value}" name="unitArr" value="${unit.column_value}" column_name="${unit.column_name}"/></td>
					<td>${unit.column_name}</td>
		</tr>
			</c:forEach>
	</tbody>
 </table>
				<div class="functionBtnDiv styleR">
	                  <input type="hidden" name="unitType" id="unitType_Id" value="${unitType}"/>
					<button type="button" id="sureUnitUp"><span>确定</span></button>
					<button type="button" id="closeUnitUp"><span>关闭</span></button>
				</div>
</div>