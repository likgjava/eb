<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/esdemo/projectmanager/abeyance_list.js"></script>
<div class="partContainers">
	<!-- 包组列表 -->
	<table class="tableList" id="SubProjectList">
  		<thead>
      		<tr>
          		<th class="center">编号</th>
          		<th>名称</th>
          		<th>采购方式</th>
          		<th class="operation">委托单位</th>
     		</tr>
		</thead>
	<tbody>
		<tr>
				<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK001</a></td>
          		<td>服务器采购委托书</td>
          		<td>公开招标</td>
          		<td class="operation">广东省公安厅</td>
		</tr>
		<tr>
				<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK002</a></td>
          		<td>笔记本采购委托书</td>
          		<td>公开招标</td>
          		<td class="operation">广州市教育局</td>
		</tr>
		<tr>
				<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK003</a></td>
          		<td>广东省采购中心采购汽车</td>
          		<td>公开招标</td>
          		<td class="operation">广州市政府</td>
		</tr>
	</tbody>
    </table>
</div>