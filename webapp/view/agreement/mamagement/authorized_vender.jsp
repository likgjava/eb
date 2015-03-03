<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/mamagement/authorized_vender.js"></script>

<input type="hidden" name="resource" id="resource" value="<c:out value="${param.resource}"/>"/>
<input type="hidden" name="venderId" id="venderId" value="<c:out value="${param.venderId}"/>"/>
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


<!-- 中部按钮 -->
<div class="operationBtnDiv" style="text-align:right">
	<button id="addVender"><span>新增供货商</span></button>
</div>

<div id="authorizedVenderTabs">
	<!-- tab标签 -->
	<ul id = "agreementSecondTab">
	</ul>
	<div id ="employeeInfo">
		<!-- 详情 -->
		<div class="formLayout form1Pa" id="agreementSecondDetail">
			<form id = "agreementSecond">
			<input type="hidden" name="agreeSecondId" id="agreeSecondId" value="<c:out value="${param.agreeSecondId}"/>"/>
		    <h4><span>二级协议信息</span></h4>
		    
		    <div class="functionBtnDiv right">
					<button type="button" id="modifyVender"><span>修改</span></button>
			</div>
		    
		    <ul>
		        <li>
		            <label>协议名称：</label>
		            <span id="name"></span>
		        </li> 
		        
		        <li>
		            <label>供货期间：</label>
		            <span id="beginDate"></span>至<span id="endDate"></span>
		        </li>
		        
		        <li>
		            <label>供货区间：</label>
		            <span id="area.areaName"></span>
		        </li> 
		    </ul>
		    </form>
		</div>
		
		<!-- 表单 -->
		<div class="formLayout form1Pa hidden" id="agreementSecondFormDiv">
		  	<form id = "agreementSecondForm">
		 		<h4><span>二级协议信息</span></h4>
		 		
		 		<div class="functionBtnDiv right">
					<button type="button" id="saveVender"><span>保存</span></button>
				</div>
		 		
				<ul>
					<li>
						<label>协议名称：</label>
						<input type="text" size="40" name="name" id="name" class="required"/><span class="eleRequired">*</span>
					</li>
					<li>
						<label>供货区间：</label>
						<input type="hidden" name="area.objId" id="areaId" value="" size="20" />
						<input type="text" size="40" name="area.areaName" id="areaName" class="required"/>
						<a href="javascript:void(0);" id="area"><img alt="选择" src="<%=request.getContextPath()%>/view/agreement/mamagementDemo/ico/find.jpg"></a>
					</li>
					<li>
						<label>供货期间：</label>
						<input type="text" name="beginDate" id="beginDate" />
						至
						<input type="text" name="endDate" id="endDate" /><em>*</em>
						<span class="eleWarning"></span>
					</li>
					<li>
						<label>附件：</label>
						<div id="content" class="uploadFile"></div>
					</li>
				</ul>
				
		    </form>
 		</div>
		
		
		<div class="formLayout">
		
	  		<div class="functionBtnDiv right">
	  			<input type="hidden"  id="goodsClassId" name="goodsClassId" value="">
				<button type="button" id="addClass"><span>新增授权分类</span></button>
			</div>
		    <h4><span>二级协议分类</span></h4>
			<table class="frontTableList" id="categoryList">
			    <thead>
			    	<tr>
			        <th class="center">商品分类</th>
			        <th class="center">商品品牌</th>
			        <th >操作</th>
			    	</tr>
			    </thead>
			    <tbody>
			    </tbody>
			</table>
			
			
			<div class="functionBtnDiv right">
				<input type="hidden"  id="goodsId" name="goodsId" value="">
				<button type="button" id="addGoods"><span>新增授权商品</span></button>
			</div>
		    <h4><span>二级协议单品</span></h4>
			<table class="frontTableList" id="goodsList">
			  <thead>
			  	<tr>
			      <th class="center">商品名称</th>
			      <th class="center">规格型号</th>
			      <th >操作</th>
			  	</tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>
		</div>
	</div>
	
</div>
  
<div class="operationBtnDiv">
	<button  id="" onclick="authorizedVenderForm.authorizedVenderReturn()" type="button" ><span><spring:message code="globe.return"/></span></button>
</div>
  

