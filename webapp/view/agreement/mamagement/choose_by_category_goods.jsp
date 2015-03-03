<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>


<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/mamagement/choose_by_category_goods.js"></script>


<!-- 导航显示 -->
<div class="nextBar">
    <ol class="num4">
        <li class="done"><span class="first">1. 填写协议基本信息</span></li> 
        <li class="done current-prev"><span>2. 增加协议商品分类</span></li>
        <li class="last current"><span>3. 增加协议单品</span></li>
    </ol>
</div>

<input type="hidden" name="resource" id="resource" value="<c:out value="${param.resource}"/>"/>
<input type="hidden" name="agreementId" id="agreementId" value="<c:out value="${param.agreementId}"/>"/>
<input type="hidden" name="agreementSecondId" id="agreementSecondId" value="<c:out value="${param.agreementSecondId}"/>"/>
<input type="hidden" name="agreementType" id="agreementType" value="<c:out value="${param.agreementType}"/>"/>


<!--按分类挑选列表 -->
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
            <fmt:formatDate value="${agreement.period.beginDate }" pattern="yyyy-MM-dd"/>&nbsp;至&nbsp;
            <fmt:formatDate value="${agreement.period.endDate }" pattern="yyyy-MM-dd"/>
            </span>
        </li>
        <li>
            <label>协议区间：</label>
            <span id="area.areaName">${agreement.areaNames }</span>
			<form:hidden path="areaNames"></form:hidden>
        </li>
        <li >
            <label>备注：</label>
            <span id="memo">${agreement.memo }</span>
        </li>
    </ul>
    </form:form>
</div>


<div id="epsTabs" class="">
<ul>
    <li>
      <a href="#byCategory" id = "byCategoryClick"><span>按所选分类选择</span></a>
    </li>
    <li>
      <a href="#byGoods" id = "byGoodsClick"><span>选择单品</span></a>
    </li>
</ul>

<div id="byCategory">
</div>

<!--按单品挑选 -->
<div id="byGoods">
</div>

<div class="conOperation center">
    <button  type="button" id="chooseGoodsByCategoryPrev"><span>上一步</span></button>
    <button  type="button" id="chooseGoodsByCategoryReturn"><span><spring:message code="globe.return"/></span></button>
</div>

</div>



        
        

 
