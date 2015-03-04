<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/esdemo/projectmanager/abeyance_list.js"></script>
<div class="partContainers">
	<!-- 包组列表 -->
	<table class="tableList" id="SubProjectList">
  		<thead>
      		<tr>
          		<th class="center">招标名称</th>
          		<th><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>类型</th>
          		<th>发布日期</th>
          		<th class="operation">采购方式</th>
     		</tr>
		</thead>
	<tbody>
		<tr>
				<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>广东省招标采购中心采购服务器</a></td>
          		<td>采购预告</td>
          		<td>(2000-09-01)</td>
          		<td class="operation">公开招标</td>
		</tr>
		
		<tr>
				<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>广东省采购中心采购汽车</a></td>
          		<td><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></td>
          		<td>(2000-09-01)</td>
          		<td class="operation">公开招标</td>
		</tr>
	</tbody>
    </table>
</div>