<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>历史轮次报价信息</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/agreement/css/bargain_hall.css"/>
</head>
<body>
<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />

<form id="HistoryTurnInfoForm" method="post">
	<div class="prodtail2">
		<!-- 定义字符串进行截取，不要四舍五入 -->
		<c:set var="buybudget" value="${project_detail.budgetTotalMoney}${''}" scope="request"></c:set>
		<c:set var="len" value="${fn:length(buybudget)}" scope="request"></c:set>
		
		<div class="pti">[${project_detail.projCode}]${project_detail.projName}</div>
		<div class="pot">
			采购单位：${project_detail.buyersName}&nbsp;
			采购预算：<span style="color:#E56700">￥</span><fmt:formatNumber value="${fn:substring(buybudget,0,len-4)}.${fn:substring(buybudget,len-4,len)}" pattern="#,##0.00#" />万元
		</div>
	</div>

  <!--报价信息 开始-->
  <div class="come_title">
  	<input type="hidden" id="bargainTurnId" value="${currentTurn.objId}"/>
    <h2>第<label id="turnNo">${currentTurn.turnNo}</label>轮<span id="turnTime" style="font-weight: 100; color: #00A3D4">（${fn:replace(currentTurn.startTime,'.0','')} 至  ${fn:replace(currentTurn.endTime,'.0','')}）</span></h2>
  </div>
  <div class="k_all6">
  	<table width="100%" cellspacing="0" cellpadding="0" border="0">
    	<tbody>
        	<tr>
            	<td>
            	<!-- 循环需求条目 -->
            	<c:forEach var="requireItem" items="${requireItemList}" varStatus="status">
            		<!-- 获取需求条目id逗号分隔字符串，获取所有需求的最低报价 -->
                	<div class="ys_bf">
                	   <div class="reqdtail">
                	   	<div style="text-indent:2em;"><span style="color: #0147A6">需求：</span>
                	   		<c:choose>
                	   			<c:when test="${!empty requireItem.descr}">${requireItem.descr}</c:when>
                	   			<c:otherwise>${requireItem.goods.productName}</c:otherwise>
                	   		</c:choose>
                	   	</div>
                	   	<div class="reqnum">
                	   		<span style="color: #0147A6">数量：</span><span id="qty${status.index+1}">${requireItem.goodsQty}</span>${requireItem.goodsUnit}
					       	<br />
					       	<span style="color: #0147A6">预算：</span>￥<span><fmt:formatNumber value="${requireItem.goodsTotal}" pattern="#,##0.00#" />元</span>
                	   	</div>
                	   	<div class="reqmin">最低报价：￥<span name="minItemPrice" requireid="${requireItem.objId}">--</span></div>
                	   	<div class="reqopen"><a id="viewOrhide${status.index+1}" href="javascript:void(0);">折叠/展开</a></div>
					   </div>
                        
                        <div id="bargainBuyerListDiv${status.index+1}" style="margin-top:5px;clear:both;display:none;">
	            			<table id="table_${requireItem.objId}" width="100%" cellspacing="0" cellpadding="0" class="bk">
	            				<thead>
				                    <tr>
				                        <th style="width: 200px">供应商[红色为最低报价供应商]</th>
				                        <th style="width: 120px">报价时间</th>
				                        <th style="width: 110px">报价(元)</th>
				                        <th style="width: 110px">总计(元)</th>
				                        <th style="width: 140px">备注</th>
				                        <th style="width: 60px">报价文件</th>
				                        <th style="width: 60px">历史报价</th>
				                    </tr>
				                </thead>
				                <tbody>
		            			<!-- 循环供应商的报价 -->
								<c:forEach var="bargain" items="${myBargain}" varStatus="status1">
									<c:if test="${bargain.key==requireItem.objId}">
										<c:forEach var="bargainItem" items="${bargain.value}" varStatus="status2">
										<tr <c:if test="${status2.index==0}">style="color:red"</c:if>>
											<td title="${bargainItem[8]}">
												<c:choose><c:when test="${fn:length(bargainItem[8])<18}">${bargainItem[8]}</c:when><c:otherwise>${fn:substring(bargainItem[8],0,18)}...</c:otherwise></c:choose>
											</td>
										    <td align="center">${bargainItem[2]}</td>
										    <td align="right"><span><fmt:formatNumber value="${bargainItem[3]}" pattern="#,##0.00#" /></span></td>
										    <td align="right"><span><fmt:formatNumber value="${bargainItem[4]}" pattern="#,##0.00#" /></span></td>
										    <td title="${bargainItem[6]}">
										    	<c:choose><c:when test="${fn:length(bargainItem[6])<10}">${bargainItem[6]}</c:when><c:otherwise>${fn:substring(bargainItem[6],0,10)}...</c:otherwise></c:choose>
										    </td>
										    <td align="center">
										    	<c:choose>
										    		<c:when test="${empty bargainItem[5] || empty fn:replace(bargainItem[5],' ','')}">--</c:when>
										    		<c:otherwise><a onclick="javascript:viewFile('${bargainItem[5]}');return false;" class="thickbox" href="javascript:void(0);">[查看]</a></c:otherwise>
										    	</c:choose>
										    </td>
										    <td align="center"><a onclick="javascript:viewHistory('${bargainItem[0]}');return false;" class="thickbox" href="javascript:void(0);">[查看]</a></td>
										</tr>
										</c:forEach>
									</c:if>
								</c:forEach>
								</tbody>
							</table>
	            		</div>
                    </div>
               </c:forEach>
               </td>
           </tr>
       </tbody>
	</table>
  </div>
</form>
  <!--报价信息 结束-->
</body>
</html>

<script>
var HistoryTurnInfoForm = {};

//查看历史报价
function viewHistory(bargainDetailId) {
	$.epsDialog({
        title:'历史报价',
        url:$('#initPath').val()+'/view/agreement/bargin/project/hall/bargain_history.jsp?bargainDetailId='+bargainDetailId,
        width: 500,
		height: 300    
	});
}
//查看报价文件
function viewFile(fileId) {
	$.epsDialog({
		  id:'toBargainFileView',
	      title:'报价文件',
	      url:'view/agreement/bargin/project/hall/bidding_file.jsp?relId='+fileId,
	      width: 600,
		  height: 200    
	})
}

$(document).ready(function(){
	//折叠/展开
	$('a[id^=viewOrhide]').click(function(){
		var index = $(this).attr('id').replace('viewOrhide','');
		if($('div[id=bargainBuyerListDiv'+index+']').css('display')=='block') {
			$('div[id=bargainBuyerListDiv'+index+']').hide();
		} else {
			$('div[id=bargainBuyerListDiv'+index+']').show();
		}
	})
	//默认第一个展开
	$('a[id=viewOrhide1]').click();

	//需求最低报价
	$('span[name=minItemPrice]').each(function(i,n){
		$(n).text($('table[id=table_'+$(n).attr('requireid')+']').find('tr').eq(1).find('td').eq(3).text());
	})
});
</script>