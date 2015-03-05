<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script src="<%=request.getContextPath()%>/view/srplatform/tree/DHtmlTree.js"></script>
<style>
.dialogTreeBox {background:#fff;}
.treeClos {background:#f9fdff; border:1px solid #c4d9ef; width:45%;}
.treeBtn {width:10%; padding-top:300px; text-align:center;}
.treeDetais {background:#f9fdff; border:1px solid #c4d9ef; padding:10px; width:20%;}
.treeBox {height:550px; overflow:auto; position: relative;}
.fullLineBtn {text-align:center; padding:2px; background:#c4d9ef;}
</style>

<input type="hidden" id="_className"  value="<c:out value="${param.className}"/>"/><!-- 类名 -->
<input type="hidden" id="_property"  value="<c:out value="${param.property}"/>"/>
<input type="hidden" id="_isCheckBox"  value="<c:out value="${param.isCheckBox}"/>"/><!-- 显示复选框 -->
<input type="hidden" id="_childNodeOnly"  value="<c:out value="${param.childNodeOnly}"/>"/><!-- 只能选择叶子节点 -->
<input type="hidden" id="_checkValues"  value="<c:out value="${param.checkValues}"/>"/>
<input type="hidden" id="_isOpen"  value="<c:out value="${param.isOpen}"/>"/>
<input type="hidden" id="_param"  value="<c:out value="${param.params}"/>"/><!-- 查询条件 -->
<input type="hidden" id="_ID"  value="<c:out value="${param.IDS}"/>"/><!-- 存放回填ID -->
<input type="hidden" id="_NAME"  value="<c:out value="${param.NAMES}"/>"/><!-- 存放回填Name -->
<input type="hidden" id="txtURL"  value="<c:out value="${param.txtURL}"/>"/>
<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/><!-- 弹出层ID -->
<input type="hidden" id="_checkStatus"  value="<c:out value="${param.checkStatus}"/>"/>
<input type="hidden" id="_andAllParent"  value="<c:out value="${param.andAllParent}"/>"/>
<input type="hidden" id="_columName"  value="<c:out value="${param.columName}"/>"/><!-- 查询的属性名 -->
<input type="hidden" id="_parentName"  value="<c:out value="${param.parentName}"/>"/><!-- 父类的属性名 -->

<table width="100%" cellpadding="10" border="0" class="dialogTreeBox">
<tbody>
	<tr>
		<td valign="top" class="treeClos"><div class="treeBox"><div id="dialogTreeBox"></div></div></td>
		<td class="treeBtn"><button type="button" id="addSelectedTreeItems" ><span>=&gt;</span></button></td>
			
		<td valign="top" class="treeDetais">
			搜索关键字<br><input type="text" id="keyWord" name="keyWord"><br>
        	<input type="button" id="searchButton" name="searchButton" value="搜索" ><br /><br />
			搜索结果<br>
			<div style="width: 100%; height: 170px;">
				<select id="searchResultList" ondblclick="addToList()" size="10" style="width: 150px; height: 160px;" multiple="multiple"></select>
				<br/>
				<span style="color: red;">注：双击添加到选中结果</span>
			</div>
			<br />
			选中结果<br>
			<div style="width: 100%; height: 170px;">
				<select id="selectedResultList" size="10" style="width: 150px; height: 160px;" multiple="multiple"></select>
			</div>
			<c:if test="${param.isCheckBox}">
				<input type="button" onclick="removeFromList()" value="除去">
			</c:if>
			<input type="button" id="_OK" value="确定">
			<input type="button" id="_clear" value="清空">
		</td>
	</tr>
	<tr><td colspan="3" class="fullLineBtn"><button id="_cancel"><span>关闭</span></button></td></tr>
</tbody>
</table>