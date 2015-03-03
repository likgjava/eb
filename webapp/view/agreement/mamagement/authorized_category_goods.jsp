<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/mamagement/authorized_category_goods.js"></script>

<input type="hidden" name="resource" id="resource" value="<c:out value="${param.resource}"/>"/>
<input type="hidden" name="agreementId" id="agreementId" value="<c:out value="${param.agreementId}"/>"/>

<!-- 一级协议信息 -->
<div class="formLayout form2Pa">
	<form:form id="agreementForm" method="post" modelAttribute="agreement">
	<form:hidden path="objId"></form:hidden>
    <h4><span>协议信息</span></h4>
    <ul>
        <li>
            <label>协议名称：</label>
            <span id="name">${agreement.name}</span>
        </li>   
        <li>
            <label>签订时间：</label>
            <span id="creTime">${agreement.creTime }</span>
        </li>
        <li>
            <label>甲方(代理机构)：</label>
            <span id="org.orgName">${agreement.org.orgName }</span>
        </li>    
        <li>
            <label>乙方(经销商)：</label>
            <span id="org.orgName">${agreement.supplier.orgName }</span>
        </li>      
        <li class="fullLine">
            <label>协议期间：</label>
            <span id="period.periodName">
            <fmt:formatDate value="${agreement.period.beginDate }" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;至&nbsp;
            <fmt:formatDate value="${agreement.period.endDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
            </span>
        </li>
        <li>
            <label>协议区间：</label>
            <span id="area.areaName">${agreement.areaNames }</span>
			<form:hidden path="area.objId"></form:hidden>
        </li>
        <li >
            <label>备注：</label>
            <span id="memo">${agreement.memo }</span>
        </li>
    </ul>
    </form:form>
</div>

<div id="authorizedVenderTabs" class="formLayout">
	<ul>
		<li><a href="#authorizedCategory" id="authorizedCategoryClick">授权/未授权分类</a></li>
		<li><a href="#authorizedGoods" id="authorizedGoodsClick">授权/未授权单品</a></li>
	</ul>
	<div id="authorizedCategory">
	
	<div class="operationBtnDiv" style="text-align:right">
			<button onclick="authorizedCategoryOrGoods.authorizedCategoryaddVender()"><span>新增供货商</span></button>
	</div>
	  <table class="frontTableList" id="authorizedCategoryList">
	      <thead>
	      	<tr>
	          <th class="left">商品分类</th>
	          <th class="left">商品品牌</th>
	          <th class="operation">供货商</th>
	          <th class="operation">供货区间</th>
	          <th class="operation">供货期间</th>
	          <th class="operation">操作</th>
	      	</tr>
	      </thead>
	      <tbody>
	      </tbody>
	    </table>
	</div>
	
	<div id="authorizedGoods">   
	
	<div class="operationBtnDiv" style="text-align:right">
		<button onclick = "authorizedCategoryOrGoods.authorizedGoodsaddVender()"><span>新增供货商</span></button>
	</div> 
	    <table class="frontTableList" id="authorizedGoodsList">
	      <thead>
	      	<tr>
	          <th class="left" >商品名称</th>
	          <th class="left" >商品品牌</th>
	          <th class="left" >商品分类</th>
	          <th class="left" >规格型号</th>
         	  <th class="operation" >供货商</th>
	          <th class="operation" >供货区间</th>
	          <th class="operation" >供货期间</th>
	          <th class="operation">操作</th>
	          
	      	</tr>
	      </thead>
	      <tbody>
	      </tbody>
	    </table>
	</div>
	
	<div class="operationBtnDiv">
		<button  id="" onclick="authorizedCategoryOrGoods.authorizedCategoryOrGoodsReturn()" type="button" ><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
