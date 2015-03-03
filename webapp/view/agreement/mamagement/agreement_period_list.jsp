<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/mamagement/agreement_period_list.js"></script>
 
<!-- 查询条件 -->
<div class="conSearch" >
  <h4><span>搜索</span></h4>
  	<ul>
  	<li>
  	<label>年度：</label>
  	<select id="annual">
	  	<option value="all">全部</option>
		<c:forEach items="${annual}" var="item" >
			<option value="${item}"><c:out value="${item}"/>年度</option>
		</c:forEach>
	</select>
	</li>
	</ul>
</div>

<div class="formTips attention">
	<ul>
		<li>
			<em>维护议价期间:</em>
			新增期间请点击<span class="sysicon siAdd"><a onclick="AgreementPeriodList.add();return false;" href="javascript:void(0);"><strong>新增协议期间</strong></a></span>
			批量删除请点击<a onclick="AgreementPeriodList.del();return false;" href="javascript:void(0);"><strong>删除协议期间</strong></a>
		</li>
	</ul>
</div>

<!--期间列表 -->
<table class="frontTableList" id="agreementPeriodList">
  <thead>
  	<tr>
      <th>协议期间名称</th>
      <th class="date">开始日期</th>
      <th class="date">结束日期</th>
      <th>操作</th>
  	</tr>
  </thead>
  <tbody>
  </tbody>
</table>
        
        

 
