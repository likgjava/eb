<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
td.title {color:#A1030A; text-align:right;}
</style>

<input type="hidden" name="projectId" id="projectId" value="${param.projectId }" />
<input type="hidden" name="userType" id="userType" value="${param.userType }" />
<input type="hidden" name="projName" id="projName" value="${project.projName }" />

<div class="">
	<!-- 项目基本信息-->
	<table class="tableList">
		<caption>采购项目信息</caption>
		<tbody>
			<tr>
				<td class="title">采购项目名称：</td>
				<td>${project.projName }</td>
				<td class="title">项目编号：</td>
				<td>${project.projCode }</td>
			</tr>
			<tr>
				<td class="title">竞标时间：</td>
				<td colspan="3"><fmt:formatDate value="${project.evalStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;截止至&nbsp;<fmt:formatDate value="${project.evalEndTime }"pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td class="title">采购人：</td>
				<td>${project.buyersName }</td>
				<td class="title" align="right">采购方式：</td>
				<td>${project.ebuyMethodCN }</td>
			</tr>
			<tr>
				<td class="title">联系人：</td>
				<td>${project.monitor.name }</td>
				<td class="title">电子邮件：</td>
				<td>${project.monitor.email }</td>
			</tr>
			<tr>
				<td class="title" align="right">理想值(元)：</td>
				<td colspan="3">${project.budgetTotalMoney }</td>
			</tr>
		</tbody>
		<tfoot></tfoot>
	</table>
	<br>
	
	<!-- 项目需求信息 -->
	<table class="tableList">
		<caption>项目需求信息</caption>
		<thead>
		<tr>
			<th>需求商品/描述</th>
			<th>需求数量</th>
			<th>计量单位</th>
			<th>期望价格(元)</th>
		</tr>
		</thead>
		<tbody id="goodsAndOption">
		<c:forEach var="requireItem" items="${requireItemList}">
			<tr>
				<td>
				<c:choose>
					<c:when test="${requireItem.goods!=null}">
						${requireItem.goods.productName }
					</c:when>
					<c:otherwise>
						${requireItem.descr }
					</c:otherwise>
				</c:choose>
				<!-- 礼包 -->
				<c:if test="${!empty requireItem.requireGoodsGifts && fn:length(requireItem.requireGoodsGifts) > 0}">
					<a href="javascript:void(0);" title="商品礼包详情" onclick="projectDetail.showOrHideGoodsGift(this);return false;"><img src="view/resource/skin/sysicon/16/goods_gift.png" /></a>
				</c:if>
				</td>					
				<td align="right">${requireItem.goodsQty }</td>
				<td align="center">${requireItem.goodsUnit }</td>
				<td align="right"><fmt:formatNumber value="${requireItem.goodsPrice }" pattern="#,##0.00#" /></td>
			</tr>
			
			<!-- 礼包 -->
			<c:if test="${!empty requireItem.requireGoodsGifts && fn:length(requireItem.requireGoodsGifts) > 0}">
			<tr class="hidden">
				<td colspan="4">
					<div>
					<table>
						<tr>
						<c:forEach items="${requireItem.requireGoodsGifts}" var="requireGoodsGift">
						<td>
						   <div style="float:left">
								<img class="goodsImg" src='<c:url value="AttachmentController.do?method=showImg&objId=${requireGoodsGift.goodsGift.giftPicture}" />' >
							 	<div class="fitting" style="float:left">
							 	${requireGoodsGift.goodsGift.giftName}<br/>
							 	￥<fmt:formatNumber value="${requireGoodsGift.giftPrice}" pattern="#,##0.00#"/><br/>
							 	</div>
						   </div>
						</td>
						</c:forEach>
						</tr>
					</table>
					</div>
				</td>
			</tr>
			</c:if>
		</c:forEach>
		</tbody>
		<tfoot></tfoot>
	</table>
	<br/>
	
	<!-- 参与供应商信息 -->
	<div class="formLayout form2Pa">
		<h4><span>参与供应商信息</span></h4>
		<ul>
			<c:forEach var="signUprecord" items="${signUprecordList}">
			<li>
				<span>${signUprecord.supplierName }</span>
	        </li>
			</c:forEach>
		</ul>
	</div>
</div>

<div class="conOperation">
	<c:if test="${(param.userType == 'buyer')}">
		<c:if test="${project.useStatus=='00' || evalStartTime>cureentDate}">
			<button type="button" onclick="projectDetail.operator('remove')"><span>删除</span></button>
		</c:if>
		<c:if test="${project.evalStartTime==null || project.evalEndTime==null || project.useStatus!='01'}">
			<button type="button" onclick="projectDetail.operator('update')"><span>修改项目</span></button>
			<button type="button" onclick="projectDetail.operator('submit')"><span>提交</span></button>
		</c:if>
		<c:if test="${project.projProcessStatus=='170' && evalEndTime < cureentDate}">
			<button type="button" onclick="projectDetail.operator('confirm')"><span>确定成交结果</span></button>
		</c:if>
		<c:if test="${(project.projProcessStatus=='170' && evalEndTime < cureentDate) || project.projProcessStatus=='200'}">
			<button type="button" onclick="projectDetail.operator('invalid')"><span>作废</span></button>
		</c:if>
	</c:if>
	<c:if test="${(param.userType=='buyer') || (param.userType=='supplier')}">
		<c:if test="${project.projProcessStatus=='160' && evalStartTime < cureentDate && evalEndTime > cureentDate }">
			<button type="button" onclick="projectDetail.operator('intoHall')"><span>进入议价厅</span></button>
		</c:if>
		<c:if test="${(project.projProcessStatus=='170' && evalEndTime < cureentDate) || project.projProcessStatus=='200'}">
			<button type="button" onclick="projectDetail.operator('evaluate')"><span>评价</span></button>
			<button type="button" onclick="projectDetail.operator('complaints')"><span>投诉</span></button>
			<button type="button" onclick="projectDetail.operator('report')"><span>举报</span></button>
		</c:if>
	</c:if>
	
	<c:if test="${project.projProcessStatus=='200'}">
		<button type="button" onclick="projectDetail.operator('viewResult')"><span>查看成交结果</span></button>
	</c:if>
	<button type="button" name="historyBackBtn" ><span>返回</span></button>
</div>

<script type="text/javascript">
/** 议价项目详情页面 */
var projectDetail = {};

//显示或隐藏商品礼包
projectDetail.showOrHideGoodsGift = function(obj){
	$(obj).parent().parent().next().toggle();
}

//操作
projectDetail.operator = function(opType){
	var projectId = $("#projectId").val(); //项目id
	var userType = $("#userType").val(); //用户类型
	
	//修改项目
	if("update"==opType){
		$('#conBody').loadPage($('#initPath').val()+"/TalkProjectController.do?method=toUpdateTalkProjectView&objId="+projectId);
	}
	//提交
	else if("submit"==opType){
		var jsonObj = {};
		jsonObj.objId = projectId;
		jsonObj.auditStatus = '01';//通过
		jsonObj.useStatus = '01';//有效
		$.getJSON($('#initPath').val()+'/TalkProjectController.do?method=updateTalkProject',jsonObj, function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			$('button[name=historyBackBtn]').click();
		});
	}
	//进入议价厅
	else if("intoHall"==opType){
		var url = $('#initPath').val()+"/TalkProjectController.do?method=toBuyerTalkHall&inType="+userType+"&objId="+projectId;
		loadPage_openModelWindow(url,"970");
	}
	//确认成交结果
	else if("confirm"==opType){
		$("#conBody").loadPage($("#initPath").val()+"/BuyResultXyghController.do?method=toConfirmResultBySupplierView&projectId="+projectId );
	}
	//查看成交结果
	else if("viewResult"==opType){
		$("#conBody").loadPage($("#initPath").val()+"/BuyResultXyghController.do?method=toResultDetailView&projectId="+projectId+"&userType="+userType);
	}
	//作废项目
	else if("invalid" == opType){
		projectDetail.remove(projectId, "invalid");
	}
	//删除项目
	else if("remove" == opType){
		projectDetail.remove(projectId, "remove");
	}
	//评价
	else if("evaluate"== opType){
		$.epsDialog({
			id:"evaluateDailog", 
			title:"对参与项目的机构评价",
			url:$("#initPath").val()+"/view/agreement/bargin/project/project_evaluate_div.jsp?userType="+userType+"&projectId="+projectId+"&projectName="+native2ascii($("#projName").val())
		});
	}
	//投诉
	else if("complaints"== opType){
		projectDetail.openComplainDiv(projectId,userType,'tell');
	}
	//举报
	else if("report"== opType){
		projectDetail.openComplainDiv(projectId,userType,'complain');
	}
}

//删除或作废项目
projectDetail.remove=function(objId,removeType){
	var url = $('#initPath').val() + "/BargainProjectController.do?method=removeRBProject&type="+removeType;
	var msg = "确定删除该项目吗";
	if(removeType=="invalid") { msg = "确定作废该项目吗"; }
	
	if(window.confirm(msg)){
		$.getJSON(url,{"objId":objId},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			$("#conBody").loadPage($("#initPath").val()+"/view/agreement/bargin/project/bargain/bargain_project_list.jsp");
		});
	}
}

//投诉 /举报
projectDetail.openComplainDiv = function(projectId,userType,type){
	$.epsDialog({
		id:"complainDiv", 
		title:"选择参与项目的机构",
		url:$("#initPath").val()+"/view/agreement/bargin/project/project_complain_div.jsp?userType="+userType+"&projectId="+projectId+"&type="+type+"&ebuyMethod=05"
	});
}

$(document).ready(function(){
})
</script>
