<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script src="<%=request.getContextPath()%>/view/goods/goodsclass/goodsclass_select_tree.js"></script>

<input type="hidden" id="_className"  value="<c:out value="${param.className}"/>"/>
<input type="hidden" id="_property"  value="<c:out value="${param.property}"/>"/>
<input type="hidden" id="_isCheckBox"  value="<c:out value="${param.isCheckBox}"/>"/>
<input type="hidden" id="_childNodeOnly"  value="<c:out value="${param.childNodeOnly}"/>"/>
<input type="hidden" id="_checkValues"  value="<c:out value="${param.checkValues}"/>"/>
<input type="hidden" id="_isOpen"  value="<c:out value="${param.isOpen}"/>"/>
<input type="hidden" id="_param"  value="<c:out value="${param.params}"/>"/>
<input type="hidden" id="_ID"  value="<c:out value="${param.IDS}"/>"/>
<input type="hidden" id="_NAME"  value="<c:out value="${param.NAMES}"/>"/>
<input type="hidden" id="txtURL"  value="<c:out value="${param.txtURL}"/>"/>
<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>
<div style="float: left; border: 1px solid #A0C6EA; width: 40%; height: 450px; overflow:auto;">
	<div id="dialogTreeBox" style="zoom:1;">
	</div>
</div>
<div style="float: left; width: 15%; padding-top: 30%; text-align: center;" class="operationBtnDiv">
	<ul>
		<li>
			<button type="button" id="addSelectedTreeItems"><span>添加 &nbsp;&gt;</span></button>
		</li>
		<li>
			<button type="button" id="removeSelectedTreeItems"><span>&lt;&nbsp;移除</span></button>
		</li>
		<li>
			<button id="_clear" type="button"><span>清&nbsp;空</span></button>
		</li>
	</ul>
</div>
<div style="border: 1px solid #A0C6EA; float: left; height: 450px; width: 40%; text-indent: 10px;overflow:auto;">
	<h5 class="center">已选中值</h5>
	<ul id="checkedTreeItems">
	</ul>
</div>

<div class="conOperation">
	<button id="_cancel"><span>取消</span></button>
</div>