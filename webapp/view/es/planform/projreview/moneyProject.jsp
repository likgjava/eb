<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#selectYear").click(function(){
		$("#conBody").loadPage($('#initPath').val()+"/ProjectViewController.do?method=toMoneyProject&year="+$("#year").val()+'');
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
          		<th width="20%">月份</th>
          		<th width="20%">资金金额（元）</th>
     		</tr>
		</thead>
	<tbody>
		<tr>
				<td class="center" width="20%">${year}</td>
          		<td width="20%">1月</td>
          		<td width="20%"><fmt:formatNumber  type="currency" value="${m1 }"></fmt:formatNumber></td>
          		
		</tr>
		<tr>
				<td class="center" width="20%">${year}</td>
          		<td width="20%">2月</td>
          		<td width="20%"><fmt:formatNumber  type="currency" value="${m2 }"></fmt:formatNumber></td>
          		
		</tr>
		<tr>
          		<td class="center" width="20%">${year}</td>
          		<td width="20%">3月</td>
          		<td width="20%"><fmt:formatNumber  type="currency" value="${m3 }"></fmt:formatNumber></td>
		</tr>
		<tr>
          		<td class="center" width="20%">${year}</td>
          		<td width="20%">4月</td>
          		<td width="20%"><fmt:formatNumber  type="currency" value="${m4 }"></fmt:formatNumber></td>
		</tr>
		<tr>
          		<td class="center" width="20%">${year}</td>
          		<td width="20%">5月</td>
          		<td width="20%"><fmt:formatNumber  type="currency" value="${m5 }"></fmt:formatNumber></td>
		</tr>
		<tr>
          		<td class="center" width="20%">${year}</td>
          		<td width="20%">6月</td>
          		<td width="20%"><fmt:formatNumber  type="currency" value="${m6 }"></fmt:formatNumber></td>
		</tr>
		<tr>
          		<td class="center" width="20%">${year}</td>
          		<td width="20%">7月</td>
          		<td width="20%"><fmt:formatNumber  type="currency" value="${m7 }"></fmt:formatNumber></td>
		</tr>
		<tr>
          		<td class="center" width="20%">${year}</td>
          		<td width="20%">8月</td>
          		<td width="20%"><fmt:formatNumber  type="currency" value="${m8 }"></fmt:formatNumber></td>
		</tr>
		<tr>
          		<td class="center" width="20%">${year}</td>
          		<td width="20%">9月</td>
          		<td width="20%"><fmt:formatNumber  type="currency" value="${m9 }"></fmt:formatNumber></td>
		</tr>
		<tr>
          		<td class="center" width="20%">${year}</td>
          		<td width="20%">10月</td>
          		<td width="20%"><fmt:formatNumber  type="currency" value="${m10 }"></fmt:formatNumber></td>
		</tr>
		<tr>
          		<td class="center" width="20%">${year}</td>
          		<td width="20%">11月</td>
          		<td width="20%"><fmt:formatNumber  type="currency" value="${m11 }"></fmt:formatNumber></td>
		</tr>
		<tr>
          		<td class="center" width="20%">${year}</td>
          		<td width="20%">12月</td>
          		<td width="20%"><fmt:formatNumber  type="currency" value="${m12 }"></fmt:formatNumber></td>
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
		   var chart = new FusionCharts("view/resource/plug-in/FusionChart/Charts/Column3D.swf", "ChartId", "600", "350", "0", "0");
		   chart.setDataURL("<%=request.getContextPath()%>/ProjectViewController.do?method=moneyProject");
		   <%request.getSession().setAttribute("year",request.getParameter("year"));
		    %>		   
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
								  