<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@page import="java.util.*,com.gpcsoft.epp.buyresult.domain.*" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/buyresult/buy_winner_list_view.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>	
<div >
  <h4><span></span></h4>
				    
				    <table class="tableList">
				    	<thead>
				    		<tr>
				    			<th class="center">投标单位</th>
				    			<th style="width: 10%" class="center">结果</th>
				    			<th style="width: 10%" class="center">状态</th>
				    			<th style="width: 20%" class="center">操作</th>
				    		</tr>
				    	</thead>
				    	<tbody>
				    		<%
				    		List<BuyWinner> winners = (List<BuyWinner>)request.getAttribute("winners");
				    		for (Iterator iterator = winners.iterator(); iterator.hasNext();) {
				    			BuyWinner winner = (BuyWinner)iterator.next(); 
				    			%>
				    			<tr>
					    			<td><%=winner.getSellerName() %></td>
					    			<td class="center"><%=winner.getResultTypeCn() %></td>
					    			<%
					    			    Map<String,String> isSendMap =(Map<String,String>)request.getAttribute("isSendMap");
					    					%>
					    					<td class="center"><%=isSendMap.get(winner.getSelllerId())%></td>
					    					<% 	
					    			%>
					    			<td class="center">
					    			<a class="abtn" onclick="buy_winner_list.edit('<%=winner.getObjId() %>','${param.subProjectId}')" value="查看通知书">查看通知书</a>
					    			</td>
				    			</tr>
				    			<%
				    		}
				    		%>
				    	</tbody>
				    </table>
</div>
<div id="draftNoticeDiv"></div>
					    			