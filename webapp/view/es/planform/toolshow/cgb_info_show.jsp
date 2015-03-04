<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/esdemo/projectmanager/abeyance_list.js"></script>
<div class="partContainers">
	<!-- 包组列表 -->
	<h5><span><strong>项目进度列表</strong></span></h5>
	<table class="tableList" id="SubProjectList">	
  		<thead>
      		<tr>
          		<th class="center">编号</th>
          		<th>名称</th>
          		<th>采购方式</th>
          		<th class="operation">项目进度</th>
     		</tr>
		</thead>
	<tbody>
		<tr>
				<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK001</a></td>
          		<td>山东省招标采购中心采购服务器</td>
          		<td>公开招标</td>
          		<td class="operation">发布<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></td>
		</tr>
		<tr>
				<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK002</a></td>
          		<td>山东省成套招标采购中心采购笔记本</td>
          		<td>公开招标</td>
          		<td class="operation">签署合同</td>
		</tr>
		<tr>
				<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK003</a></td>
          		<td>山东省采购中心采购汽车</td>
          		<td>公开招标</td>
          		<td class="operation">审核<dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></td>
		</tr>
	</tbody>
    </table>
</div>
					  