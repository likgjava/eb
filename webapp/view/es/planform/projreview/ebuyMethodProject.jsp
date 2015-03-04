<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#selectYear").click(function(){
		$("#conBody").loadPage($('#initPath').val()+"/ProjectViewController.do?method=toEbuyMethodProject&year="+$("#year").val()+'');
	})
})
</script>
<div class="formLayout">

	<div id="formLayout form2Pa">
		<div id="setRuleContent">
		<div  class="conSearch">
		<ul>
		<li class="operationBtnDiv">
		<select id="year" >
		<option value="2001" <c:if test="${year=='2001' }">selected="selected"</c:if>>2001</option>
		<option value="2002" <c:if test="${year=='2002' }">selected="selected"</c:if>>2002</option>
		<option value="2003" <c:if test="${year=='2003' }">selected="selected"</c:if>>2003</option>
		<option value="2004" <c:if test="${year=='2004' }">selected="selected"</c:if>>2004</option>
		<option value="2005" <c:if test="${year=='2005' }">selected="selected"</c:if>>2005</option>
		<option value="2006" <c:if test="${year=='2006' }">selected="selected"</c:if>>2006</option>
		<option value="2007" <c:if test="${year=='2007' }">selected="selected"</c:if>>2007</option>
		<option value="2008" <c:if test="${year=='2008' }">selected="selected"</c:if>>2008</option>
		<option value="2009" <c:if test="${year=='2009' }">selected="selected"</c:if>>2009</option>
		<option value="2010" <c:if test="${year=='2010' }">selected="selected"</c:if>>2010</option>
		<option value="2011" <c:if test="${year=='2011' }">selected="selected"</c:if>>2011</option>
		</select>
		<button id="selectYear"><span>查询</span></button>
		</li>
		</ul>
		</div>
			<table>
				<tr>
				<td width="20%"><table class="tableList" id="SubProjectList">	
  		<thead>
      		<tr>
      			<th class="center" width="20%">年度</th>
      			<th class="center" width="20%">项目类型</th>
          		<th class="center" width="20%">采购方式</th>
          		<th width="20%">项目数量</th>
     		</tr>
		</thead>
	<tbody>
		<tr >
				<td class="center" width="20%">${year}</td>
				<td class="center" width="20%" rowspan="5">政府采购</td>
				<td class="right" width="20%">公开招标</td>
          		<td width="20%" class="amount">${openBidZfcg }</td>
          		
		</tr>
		<tr>
				<td class="center" width="20%">${year}</td>
				<td class="right" width="20%">邀请招标</td>
          		<td width="20%" class="amount">${inviteBidZfcg }</td>
          		
		</tr>
		<tr>
				<td class="center" width="20%">${year}</td>
          		<td class="right" width="20%">竞争性谈判</td>
          		<td width="20%" class="amount">${negotiate }</td>
		</tr>
		<tr>
				<td class="center" width="20%">${year}</td>
          		<td class="right" width="20%">询价</td>
          		<td width="20%" class="amount">${inquiry }</td>
		</tr>
		<tr>
				<td class="center" width="20%">${year}</td>
          		<td class="right" width="20%">单一来源</td>
          		<td width="20%" class="amount">${single_source }</td>
		</tr>
				<tr>
				<td class="center" width="20%">${year}</td>
				<td class="center" width="20%" rowspan="3">土地交易</td>
				<td class="right" width="20%">公开招标</td>
          		<td width="20%" class="amount">${openBidTdjy }</td>
          		
		</tr>
		<tr>
				<td class="center" width="20%">${year}</td>
				<td class="right" width="20%">挂牌</td>
          		<td width="20%" class="amount">${listTdjy }</td>
          		
		</tr>
		<tr>
				<td class="center" width="20%">${year}</td>
          		<td class="right" width="20%">拍卖</td>
          		<td width="20%" class="amount">${auctionTdjy }</td>
		</tr>
		<tr>
				<td class="center" width="20%">${year}</td>
				<td class="center" width="20%">产权交易</td>
          		<td class="right" width="20%">竞价</td>
          		<td width="20%" class="amount">${competition }</td>
		</tr>
		<tr>
				<td class="center" width="20%">${year}</td>
				<td class="center" width="20%" rowspan="2">建筑工程</td>
          		<td class="right" width="20%">公开招标</td>
          		<td width="20%" class="amount">${openBidJzgc }</td>
		</tr>
			<tr>
				<td class="center" width="20%">${year}</td>
          		<td class="right" width="20%">邀请招标</td>
          		<td width="20%" class="amount">${inviteBidJzgc }</td>
		</tr>
	</tbody>
    </table>
				</td>
				<td width="40%">
					<table width="98%" border="0" cellspacing="0" cellpadding="3" align="center">
  <tr> 
    <td valign="top" class="text" align="center"> <div id="chartdiv" align="center"> 
        FusionCharts. </div>
        <script type="text/javascript">
		   var chart = new FusionCharts("view/resource/plug-in/FusionChart/Charts/Doughnut3D.swf", "ChartId", "600", "350","0","0");
		   var url=$("#initPath").val()+"/ProjectViewController.do?method=ebuyMethodProject";
		    <%request.getSession().setAttribute("year",request.getParameter("year"));
		    %>
		   chart.setDataURL(url);		   
		   chart.render("chartdiv");
		</script>
	</td>
  </tr>
</table>
				</td>
				</tr>		
			</table>
	</div>
	</div>
</div>
			