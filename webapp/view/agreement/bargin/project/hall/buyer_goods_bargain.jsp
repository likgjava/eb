<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>采购人竞价厅</title>
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

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/agreement/css/bargain_hall.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/supplierAgreement.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/button.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin01/css/listDetail.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/hall/bargain_hall_common.js"></script>
</head>
<body>
<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
<!-- 告诉bargain_hall_common.js请求定时器 -->
<input type="hidden" id="pageType" value="buyer" />

<form id="BuyerGoodsBargainForm" method="post">
	<input type="hidden" id="projId" value="${project_buyer.objId}" />
	<input type="hidden" id="evalEndTime" value="<fmt:formatDate value="${project_buyer.evalEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
	<input type="hidden" id="currentDate" value="${currentDate}"/>
	
	<!-- 当前人机构id -->
	<input type="hidden" id="myOrgInfoId" value="${myOrgInfoId}"/>
	<input type="hidden" id="myOrgInfoName" value="${myOrgInfoName}"/>
	
	<div class="prodtail">
		<span class="ti">您好，<span style="color:#000000">${myOrgInfoName}</span>
		&nbsp;[<a onclick="javascript:bargainHallCommon.exitHall()" class="admin_blue" href="javascript:void(0);">退出报价厅</a>]
		</span>
	</div>
	<div class="prodtail2">
		<!-- 定义字符串进行截取，不要四舍五入 -->
		<c:set var="buybudget" value="${project_buyer.budgetTotalMoney}${''}" scope="request"></c:set>
		<c:set var="len" value="${fn:length(buybudget)}" scope="request"></c:set>
		
		<div class="pti">[${project_buyer.projCode}]${project_buyer.projName}</div>
		<div class="pot">
			采购单位：${project_buyer.buyersName}&nbsp;
			采购预算：<span style="color:#E56700">￥</span><fmt:formatNumber value="${buybudget}" pattern="#,##0.00#" />万元
		</div>
		<div class="prodtail3">
			<span class="app-box"><a name="bargainBtn" id="startChat" class="app-icon-a" href="javascript:void(0);"><em class="app-icon" style="background:url('/view/resource/skin/agreement/img/sms.png')" /></em><em>发起会话<span id="newMsg" style="color:red;"></span></em></a></span>
			<span class="app-box"><a id="stopBargain" class="app-icon-a" href="javascript:void(0);"><em class="app-icon" style="background:url('/view/resource/skin/agreement/img/settings.png')" /></em><em>结束竞价</em></a></span>
		</div>
		<div class="prodtail4" id="leftTimeLI">距离项目结束还有：<span class="ptime" style="">0天0时00分00秒</span></div>
	</div>

  <!--报价信息 开始-->
  <div class="come_title">
  	<input type="hidden" id="bargainTurnId" value="${currentTurn.objId}"/>
    <h2>当前第<label id="turnNo">${currentTurn.turnNo}</label>轮<span id="turnTime" style="font-weight: 100; color: #FF6600">（${fn:replace(currentTurn.startTime,'.0','')} ~ ${fn:replace(currentTurn.endTime,'.0','')}）</span>
    	<c:forEach var="turn" varStatus="status" items="${bargainTurnList}">
    		<c:if test="${turn.turnNo < currentTurn.turnNo}">
    			<span name="turnSpan" id="${turn.turnNo}"><a onclick="viewHistoryTurn('${turn.objId}','${project_buyer.objId}','buyer');" href="javascript:void(0);">第${turn.turnNo}轮</a></span>
    		</c:if>
    	</c:forEach>
    </h2>
    <div class="title_help"></div>
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
                	<div class="ys_bf">
					   <div class="reqdtail">
                	   	<div style="text-indent:2em;"><span style="color: #0147A6">需求：</span>
                	   	<a href="javascript:void(0);" onclick="common.geToGoodsDetail('${requireItem.goods.objId}');">${requireItem.goods.productName}&nbsp;${requireItem.goods.productCode}</a>
                	   	</div>
                	   	<div class="reqnum">
                	   		<span style="color: #0147A6">数量：</span><span id="qty${status.index+1}">${requireItem.goodsQty}</span>${requireItem.goodsUnit}
					       	<br />
					       	<span style="color: #0147A6">预算：</span>￥<span><fmt:formatNumber value="${requireItem.goodsTotal}" pattern="#,##0.00#" />元</span>
                	   	</div>
                	   	<div class="reqmin">当前最低：￥<span name="minItemPrice" requireid="${requireItem.objId}">--</span></div>
                	   	<div class="reqopen"><a id="viewOrhide${status.index+1}" href="javascript:void(0);">折叠/展开</a></div>
					   </div>
					   
                        <div id="bargainBuyerListDiv${status.index+1}" style="margin-top:5px;clear:both; display:none;">
	            			<table id="table_${requireItem.objId}" width="100%" cellspacing="0" cellpadding="0" class="bk">
	            				<thead>
				                    <tr>
				                        <th style="width: 200px">供应商[红色为最低报价供应商]</th>
				                        <th style="width: 150px">报价商品/说明</th>
				                        <th style="width: 120px">报价时间</th>
				                        <th style="width: 110px">报价(元)</th>
				                        <th style="width: 110px">总计(元)</th>
				                        <th style="width: 120px">备注</th>
				                        <th style="width: 50px">报价文件</th>
				                        <th style="width: 50px">历史报价</th>
				                        <th style="width: 60px">剔除供应商</th>
				                    </tr>
				                </thead>
				                <tbody>
		            			<!-- 循环供应商的报价 -->
								<c:forEach var="bargain" items="${myBargain}" varStatus="status1">
									<c:if test="${bargain.key==requireItem.objId}">
										<c:forEach var="bargainItem" items="${bargain.value}" varStatus="status2">
										<tr <c:if test="${status2.index==0}">style="color:red"</c:if>>
											<!-- 判断是否为剔除供应商  开始 -->
											<c:set var="hasEliminate" value="0" />
											<c:set var="eliminateSupplierId" value="" />
									    	<c:forEach var="eliminateSupplier" items="${eliminateSupplierList}">
									    		<c:if test="${eliminateSupplier.supplier.objId == bargainItem[13]}"><c:set var="hasEliminate" value="1" /><c:set var="eliminateSupplierId" value="${eliminateSupplier.objId}" /></c:if>
									    	</c:forEach>
											<!-- 判断是否为剔除供应商  结束 -->
											
											<td title="${bargainItem[8]}" <c:if test="${hasEliminate == '1'}">class="delete-line"</c:if>>
												<c:choose><c:when test="${fn:length(bargainItem[8])<18}">${bargainItem[8]}</c:when><c:otherwise>${fn:substring(bargainItem[8],0,18)}...</c:otherwise></c:choose>
											</td>
											<td title="${bargainItem[1]}">
										    	<c:choose><c:when test="${fn:length(bargainItem[1])<16}">${bargainItem[1]}</c:when><c:otherwise>${fn:substring(bargainItem[1],0,15)}...</c:otherwise></c:choose>
										    	<c:if test="${bargainItem[7] != ' ' && bargainItem[12] == '01'}">
										    		<input title="商品比较" type="checkbox" name="compareCheckBox" gid="${bargainItem[7]}" gname="${bargainItem[10]}" gcode="${bargainItem[11]}" />
										    	</c:if>
										    </td>
										    <td>${bargainItem[2]}</td>
										    <td><span><fmt:formatNumber value="${bargainItem[3]}" pattern="#,##0.00#" /></span></td>
										    <td><span><fmt:formatNumber value="${bargainItem[4]}" pattern="#,##0.00#" /></span></td>
										    <td title="${bargainItem[6]}">
										    	<c:choose><c:when test="${fn:length(bargainItem[6])<10}">${bargainItem[6]}</c:when><c:otherwise>${fn:substring(bargainItem[6],0,10)}...</c:otherwise></c:choose>
										    </td>
										    <td align="center">
										    	<c:choose>
										    		<c:when test="${empty bargainItem[5] || empty fn:replace(bargainItem[5],' ','')}">--</c:when>
										    		<c:otherwise><a onclick="javascript:viewFile('${bargainItem[5]}');return false;" class="thickbox" href="javascript:void(0);">[查看]</a></c:otherwise>
										    	</c:choose>
										    </td>
										    <td><a onclick="javascript:viewHistory('${bargainItem[0]}');return false;" class="thickbox" href="javascript:void(0);">[查看]</a></td>
									    	<td align="center">
										    	<c:choose>
										    		<c:when test="${hasEliminate == '1'}"><a href="javascript:void(0);" onclick="bargainHallCommon.cancleEliminate('${eliminateSupplierId}');return false;" class="thickbox">[取消剔除]</a></c:when>
										    		<c:otherwise><a href="javascript:void(0);" onclick="bargainHallCommon.endSupplier('${bargainItem[13]}');return false;" class="thickbox">[剔除]</a></c:otherwise>
										    	</c:choose>
									    	</td>
										</tr>
										</c:forEach>
									</c:if>
								</c:forEach>
								</tbody>
							</table>
	            		</div>
                    </div>
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
  
  <!-- 商品对比 开始 -->
  <div id="goodsCompare" class="compareGoodsTips hidden">
	<h4 style="margin: 0px; padding: 0px;"><span>商品对比</span></h4>
	<ul style="margin: 0px; padding: 0px;"></ul>
	<div class="conOperation center" style="clear: both; text-align: center;">
		<button id="startGoodsCompare"><span>开始比较</span></button>
		<button onclick="BuyerGoodsBargainForm.removeCompare()"><span>取消对比</span></button>
	</div>
  </div>
  <!-- 商品对比 结束 -->
</form>
  <!--报价信息 结束-->

<!--聊天窗口开始-->
<div id="tstart" class="hidden">
	<%@ include file="/view/agreement/bargin/project/hall/chat_window.jsp" %>
</div>
<!--聊天窗口结束-->
</body>
</html>

<script>
var BuyerGoodsBargainForm = {};

//删除比较数据
BuyerGoodsBargainForm.removeCompare=function(goodsId){
	//单条删除
	if(goodsId){
		$("#C_"+goodsId).remove();
	
		//将对应的复选框选中状态去掉
		$(":checkbox[gid="+goodsId+"]").removeAttr("checked");
		//隐藏比较层
		if($('#goodsCompare ul').text() == "") {
			$("#goodsCompare").hide();
		}
	}
	//全部清除
	else{
		$('#goodsCompare ul').empty();
		$(":checkbox[name=compareCheckBox]").removeAttr("checked");
		$("#goodsCompare").hide(); //隐藏比较层
	}
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

	//比较复选框点击事件
	$("input[name=compareCheckBox]").live('click',function(){
		var goodsId = $(this).attr("gid");
		var goodsName = $(this).attr("gname");
		var goodsCode = $(this).attr("gcode");

		//选中
		if($(this).attr("checked")){
			$("#goodsCompare").removeClass("hidden").show();  //显示比较层
			if($("#C_"+goodsId).length == 0) {
				if($('#goodsCompare ul').find('li').length == 4) {  //最多可以比较四个
					alert("最多可以比较四个！")
					$(this).removeAttr("checked");
					return;
				}else {
					$('#goodsCompare ul').append('<li class=center id=C_'+goodsId+'>'+goodsName+' ('+goodsCode+')&nbsp;<a href="javascript:void(0);" onclick="BuyerGoodsBargainForm.removeCompare(\''+goodsId+'\');return false;"><span class="sysicon siExit" >&nbsp;</span></a></li>'); //追加比较数据
				}
			}
		}else { 
			BuyerGoodsBargainForm.removeCompare(goodsId);
		}
	})

	//商品比较
	$("#startGoodsCompare").click(function(){
		var objIds = "";
		$.each($('#goodsCompare ul li'),function(i,n){
			objIds += $(n).attr("id").split("_")[1] + ",";
		})
	
		if(objIds.length == 0) {
			return;
		}else {
			objIds = objIds.substring(0,objIds.length-1);
		}

		$.epsDialog({
		 	id:"compareDialog",
		    title:'商品对比',
		    width:920,
		    height:500,
			url:$('#initPath').val()+'/GoodsShowController.do?method=getCompareGoodsInfo&objIds='+objIds
		}); 
		return false;
	})
});
</script>