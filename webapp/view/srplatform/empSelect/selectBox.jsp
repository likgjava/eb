<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>  
<script src="<%=request.getContextPath()%>/view/srplatform/empSelect/selectBox.js"></script>
<style>
.form3Pa .framePaCon{float:left; min-height:350px;-height:350px;width:44%; border:0px;margin-left:3px}
.form3Pa .framePaCon select {width:98%; height:345px;align:center;}
.form3Pa .framePaCon2 {width:9%; float:left;min-height:350px; -height:345px;text-align:center; background:#999; position:relative;}
.form3Pa .framePaCon2 div {margin-top:150px;}  
div .fronAjaxPager .pages {margin-top:0px;width:100%;}
div .fronAjaxPager .pageButton {
    margin: 5px 2px 5px 5px;
}
</style>
<input type="hidden" id="_property"  value="<c:out value="${param.property}"/>"/>
<input type="hidden" id="_ID"  value="<c:out value="${param.IDS}"/>"/>
<input type="hidden" id="_NAME"  value="<c:out value="${param.NAMES}"/>"/>
<input type="hidden" id="_SHOWS"  value="<c:out value="${param.SHOWS}"/>"/>
<input type="hidden" id="_escapeValues"  value="<c:out value="${param.escapeValues}"/>"/>
<input type="hidden" id="_checkValues"  value="<c:out value="${param.checkValues}"/>"/>
<input type="hidden" id="_param"  value="<c:out value="${param.params}"/>"/>
<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>
<input type="hidden" id="_backfill"  value="<c:out value="${param.backfill}"/>"/>
<input type="hidden" id="_showAlreadySelect"  value="<c:out value="${param.showAlreadySelect}"/>"/>

<input type="hidden" id="_domainName"  value="<c:out value="${param.domainName}"/>"/>
<input type="hidden" id="_queryColumns"  value="<c:out value="${param.queryColumns}"/>"/>
<input type="hidden" id="_orderColumns"  value="<c:out value="${param.orderColumns}"/>"/>

<div class="conSearch">
	<h4><span><spring:message code="globe.query"/></span></h4>
    	<ul class="highclassSearch">
		  	<li>
				<label for="condition">关键字：</label>
				<input id="condition" style="width:300px"/>
			</li>
			<li class="operationBtnDiv">
				<button id="SEARCH"><span><spring:message code="globe.query"/></span></button>
			</li>
		</ul>
</div>
<div class="partContainers">
	<div class="formLayout form3Pa">
		<ul>
			<li class="framePaCon">
				<select size="20" id="toSelect" multiple="multiple" style="height:300px"><option>数据正在读取中...</option></select>
				
				<div id="boxPager" class="fronAjaxPager"></div>  
			</li>
			<li class="framePaCon2">
				<div>
					<button id="ADD"><span>添加&nbsp;&gt;</span></button>
					<button id="DELETE"><span>&lt;&nbsp;删除</span></button>
				</div>
			</li>
			<li class="framePaCon">
				<select size="20" id="alreadySelect" multiple="multiple"></select>
			</li>
		</ul>
	    <div class="conOperation">
			<button id="OK" type="button" tabindex="18"><span>确定</span></button>
			<button id="CLEAR" type="button" tabindex="19"><span>清空</span></button>
		</div>
  	</div>
</div>
