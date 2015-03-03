<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供应商报价厅</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!-- 解决样式问题 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/button.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/form.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/table.css"/>
<!-- 解决样式问题 -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/baseEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/agreement/css/bargain_hall.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/hall/bargain_hall_common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/hall/supplier_goods_bargain.js"></script>
</head>
<body>
<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />

<input type="hidden" id="projId" value="${project_supplier.objId}" />
<input type="hidden" id="evalEndTime" value="<fmt:formatDate value="${project_supplier.evalEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
<input type="hidden" id="currentDate" value="${currentDate}"/>
<!-- 项目规则 -->
<input type="hidden" id="bargainNumber" value="${bargainNumber}"/>
<input type="hidden" id="minPrice" value="${minPrice}"/>
<input type="hidden" id="selfRanking" value="${selfRanking}"/>
<input type="hidden" id="buyerBudget" value="${buyerBudget}"/>
<input type="hidden" id="isEliminated" value="${isEliminated}" />

<!-- 当前供应商ID -->
<input type="hidden" id="myOrgInfoId" value="${myOrgInfoId}"/>
<input type="hidden" id="myOrgInfoName" value="${myOrgInfoName}"/>

<!-- 全局附件ID 和个数-->
<input type="hidden" name="_attachRelaID" value="" id="_attachRelaID"/>
<input type="hidden" name="_count" value="" id="_count"/>
     
     <div class="prodtail">
		<span class="ti">您好，<span style="color:#000000">${myOrgInfoName}</span>
		&nbsp;[<a onclick="javascript:bargainHallCommon.exitHall()" class="admin_blue" href="javascript:void(0);">退出报价厅</a>]
		</span>
	 </div>
	 <div class="prodtail2">
		<div class="pti">[${project_supplier.projCode}]${project_supplier.projName}</div>
		<div class="pot">
			采购单位：${project_supplier.buyersName}&nbsp;
			
			<c:if test="${buyerBudget}">
				<!-- 定义字符串进行截取，不要四舍五入 -->
				<c:set var="buybudget" value="${project_supplier.budgetTotalMoney}${''}" scope="request"></c:set>
				<c:set var="len" value="${fn:length(buybudget)}" scope="request"></c:set>
				采购预算：<span style="color:#E56700">￥</span><fmt:formatNumber value="${buybudget}" pattern="#,##0.00#" /> 万元
			</c:if>
		</div>
		<div class="prodtail3">
			<span class="app-box"><a name="bargainBtn" id="startChat" class="app-icon-a" href="javascript:void(0);"><em class="app-icon" style="background:url('/view/resource/skin/agreement/img/sms.png')" /></em><em>发起会话<span id="newMsg" style="color:red;"></span></em></a></span>
		</div>
		<div class="prodtail4" id="leftTimeLI">距离项目结束还有：<span class="ptime" style="">0天0时00分00秒</span></div>
	 </div>

  <!--报价信息 开始-->
  <div class="come_title">
  	<input type="hidden" id="bargainTurnId" value="${currentTurn.objId}"/>
    <h2>当前第<label id="turnNo">${currentTurn.turnNo}</label>轮<span id="turnTime" style="font-weight: 100; color: #FF6600">（${fn:replace(currentTurn.startTime,'.0','')} ~ ${fn:replace(currentTurn.endTime,'.0','')}）</span>
    	<c:forEach var="turn" varStatus="status" items="${bargainTurnList}">
    		<c:if test="${turn.turnNo < currentTurn.turnNo}">
    			<span name="turnSpan" id="${turn.turnNo}"><a onclick="viewHistoryTurn('${turn.objId}','${project_supplier.objId}','supplier');" href="javascript:void(0);">第${turn.turnNo}轮</a></span>
    		</c:if>
    	</c:forEach>
    	
    	    	最低降幅：<span id="totalCut">
			<c:if test="${currentTurn.totalCutType=='01'}">
				${currentTurn.totalCut}%
			</c:if>
			<c:if test="${currentTurn.totalCutType=='02'}">
				<fmt:formatNumber value="${currentTurn.totalCut}" pattern="#,##0.00#" />&nbsp;元
			</c:if>
    	</span>
    </h2>
  </div>
  <div class="k_all6">
  	<table width="100%" cellspacing="0" cellpadding="0" border="0">
    	<tbody>
        	<tr>
            	<td>
            	<!-- 循环需求条目 -->
            	<c:set value="" var="requireItemIds" scope="request"></c:set>
            	<c:forEach var="requireItem" items="${requireItemList}" varStatus="status">
            		<!-- 获取需求条目id逗号分隔字符串，获取所有需求的最低报价 -->
            		<c:set value="${requireItemIds},${requireItem.objId}" var="requireItemIds" scope="request"></c:set>
            		
            		<input type="hidden" id="requireItem${status.index+1}" value="${requireItem.objId}"/>
            		<input type="hidden" id="goodsId${status.index+1}" value="${requireItem.goods.objId}"/>
            	
            		<form id="SupplierGoodsBargainForm" method="post">
                	<div class="ys_bf">
					    <div class="reqdtail">
	                	   	<div style="text-indent:2em;"><span style="color: #0147A6">需求描述：</span>
	                	   		<a onclick="common.geToGoodsDetail('${requireItem.goods.objId}');" href="javascript:void(0);">${requireItem.goods.productName}&nbsp;${requireItem.goods.productCode}</a>
	                	   	</div>
	                	   	<div class="reqnum">
	                	   		<span style="color: #0147A6">数量：</span><span id="qty${status.index+1}">${requireItem.goodsQty}</span>${requireItem.goodsUnit}
						       	<br />
						       	<c:if test="${buyerBudget}">
						       		<span style="color: #0147A6">预算：</span>￥<span><fmt:formatNumber value="${requireItem.goodsTotal}" pattern="#,##0.00#" />元</span>
						       	</c:if>
	                	   	</div>
	                	   	<div class="reqmin">
	                	   		<div id="biddingRankChart_${requireItem.objId }" supplierIds="" minPrices=""></div>
	                	   	</div>
	                	   	<div class="reqopen"><a id="viewOrhide${status.index+1}" href="javascript:void(0);">折叠/展开</a></div>
					    </div>
                        
                        <div id="bargainSupplierListDiv${status.index+1}" style="margin-top:5px;clear:both; display:none;">
	                        <table width="100%" cellspacing="0" cellpadding="0" class="bk" style="margin-bottom:5px;background-color:#F3F8FB;">
				                <thead>
				                    <tr>
				                        <th style="width: 160px">报价(元)</th>
				                        <th style="width: 120px">上传报价文件</th>
				                        <th style="width: 180px">备注</th>
				                        <th style="width: 80px">操作</th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<tr id="bargainTR${status.index+1}">
									    <td align="center">
									        <input name="goodsPrice" id="goodsPrice${status.index+1}" type="text" style="width:100px;" class="money required"/>
									        <div>总计(元):￥<span id="goodsTotal${status.index+1}" style="color:red;"></span></div>
									    </td>
									    <td align="center">
									    	<a name="bargainBtn" id="uploadFile${status.index+1}" class="thickbox" href="javascript:void(0);">[上传报价文件]</a>
									    	<input type="hidden" id="bargainFile${status.index+1}" name="bargainFile" value=""/>
									    	<div></div>
									    </td>
									    <td align="center">
									        <textarea id="remark${status.index+1}" maxLength="1000" name="remark" style="width: 180px; height: 30px;"></textarea>
									    </td>
									    <td align="center">
									        <a name="bargainBtn" id="submitBargain${status.index+1}" class="thickbox" href="javascript:void(0);">[提交报价]</a>
									    </td>
									</tr>
				                </tbody>
	            			</table>
	            			
	            			<table width="100%" cellspacing="0" cellpadding="0" class="bk">
	            				<thead>
				                    <tr>
				                        <th style="width: 120px">报价时间</th>
				                        <th style="width: 160px">报价(元)</th>
				                        <th style="width: 120px">总计(元)</th>
				                        <th style="width: 120px">备注</th>
				                        <th style="width: 60px">报价文件</th>
				                        <th style="width: 60px">历史报价</th>
				                        <th style="width: 80px">操作</th>
				                    </tr>
				                </thead>
				                <tbody>
		            			<!-- 循环我的报价 -->
								<c:forEach var="bargain" items="${myBargain}" varStatus="status1">
									<c:if test="${bargain.key==requireItem.objId}">
										<c:forEach var="bargainItem" items="${bargain.value}" varStatus="status2">
										
										<input type="hidden" id="bargainDetailId${status.index+1}${status2.index+1}" value="${bargainItem[0]}"/>
										
										<tr>
										    <td>${bargainItem[2]}</td>
										    <td>
										    	<label>￥
										    	<input type="hidden" id="oldPrice${status.index+1}${status2.index+1}" value="${bargainItem[3]}"/>	
										    	<input requireindex="${status.index+1}"  name="myGoodsPrice" id="myGoodsPrice${status.index+1}${status2.index+1}" type="text" value="${bargainItem[3]}" disabled="disabled" class="money required" style="width:80px;"/>
										    	</label>
										    </td>
										    <td>￥<span id="myGoodsTotal${status.index+1}${status2.index+1}"><fmt:formatNumber value="${bargainItem[4]}" pattern="#,##0.00#" /></span></td>
										    <td title="${bargainItem[6]}">
										    	<c:choose><c:when test="${fn:length(bargainItem[6])<10}">${bargainItem[6]}</c:when><c:otherwise>${fn:substring(bargainItem[6],0,10)}...</c:otherwise></c:choose>
										    </td>
										    <td>
										    	<c:choose>
										    		<c:when test="${empty bargainItem[5] || fn:replace(bargainItem[5],' ','')==''}">--</c:when>
										    		<c:otherwise><a onclick="javascript:viewFile('${bargainItem[5]}');return false;" class="thickbox" href="javascript:void(0);">[查看]</a></c:otherwise>
										    	</c:choose>
										    </td>
										    <td><a onclick="javascript:viewHistory('${bargainItem[0]}');return false;" class="thickbox" href="javascript:void(0);">[查看]</a></td>
										    <td>
										    	<a name="bargainBtn" id="resetBargain${status.index+1}${status2.index+1}" class="thickbox" href="javascript:void(0);">[重新报价]</a>
										    	<a id="resetSubmitBargain${status.index+1}${status2.index+1}" class="thickbox hidden" href="javascript:void(0);">[提交]</a>
										    	<a id="resetCancelBargain${status.index+1}${status2.index+1}" class="thickbox hidden" href="javascript:void(0);">[取消]</a>
										    </td>
										</tr>
										</c:forEach>
									</c:if>
								</c:forEach>
								</tbody>
							</table>
	            		</div>
                    </div>
                    </form>
               </c:forEach>
               <!-- 需求条目ids -->
               <c:if test="${fn:startsWith(requireItemIds,',')}">
               		<c:set value="${fn:substring(requireItemIds,1,fn:length(requireItemIds))}" var="requireItemIds" scope="request"></c:set>
               </c:if>
               <input type="hidden" id="requireItemIds" value="${requireItemIds}"/>
               </td>
           </tr>
       </tbody>
	</table>
  </div>
  <!--报价信息 结束-->
  
<!--聊天窗口开始-->
<div id="tstart" class="hidden">
	<%@ include file="/view/agreement/bargin/project/hall/chat_window.jsp" %>
</div>
<!--聊天窗口结束-->
</body>
</html>