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
          		<th>采购品目</th>
          		<th>采购数量</th>
          		<th class="operation">中标单位</th>
     		</tr>
		</thead>
	<tbody>
		<tr>
				<td class="center"><a href='#' class='next' target='' onClick='javascript:projectList.modify(1)'>AH-GK001</a></td>
          		<td>计算机</td>
          		<td>100台</td>
          		<td class="operation">清华同方</td>
		</tr>
	</tbody>
    </table>
</div>