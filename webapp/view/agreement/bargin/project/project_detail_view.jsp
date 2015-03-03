<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.formTag{
	border:1px solid #D1D7DC;
	background:none repeat scroll 0 0 transparent;
}
.formTag h4{
	background:none repeat scroll 0 0 #EFF7FE;
	border-bottom:1px solid #D1D7DC;
	line-height:20px;
	padding-left:10px;
	color:#444444;
}
.formTag li{
	border:none;
}
</style>

<input type="hidden" name="projectId" id="projectId" value="${param.projectId }">


<div id="epsTabs">
	<ul>
		<li>
			<a href="#projectView" class="refreshData" id = ""><span>项目基本信息</span></a>
		</li>
		<li>
			<a href="#ruleTurnView" class="refreshData" id = ""><span>轮次/规则信息</span></a>
		</li>
		<li>
			<a href="#extInfo" class="refreshData" id = "ProjectPayInfoController"><span>项目支付信息</span></a>
		</li>
		<li>
			<a href="#extInfo" class="refreshData" id = "ProjectContactInfoController"><span>项目联系人信息</span></a>
		</li>
		<li>
			<a href="#extInfo" class="refreshData" id = "ProjectSignInfoController"><span>项目报名要求</span></a>
		</li>
	</ul>
	<div id="projectView">
		<div class="formLayout form2Pa formTag">
			<h4><span><img src="view/resource/skin/skin07/img/leaderBg.png" />&nbsp;项目基本信息</span></h4>
			<ul>
				<li>
					<label>招标项目名称：</label>
					<span>${project.projName }</span>
				</li>
				<li>
					<label>项目编号：</label>
					<span>${project.projCode }</span>
				</li>
				<li class="fullLine">
					<label>报名时间：</label>
					<span><fmt:formatDate value="${project.signUpSTime }" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;截止至&nbsp;<fmt:formatDate value="${project.signUpETime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
				</li>
				<li class="fullLine">
					<label>竞标时间：</label>
					<span><fmt:formatDate value="${project.evalStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;截止至&nbsp;<fmt:formatDate value="${project.evalEndTime }"pattern="yyyy-MM-dd HH:mm:ss"/></span>
				</li>
				<li>
					<label>采购人：</label>
					<span>${project.buyersName }</span>
				</li>
				<li>
					<label>采购方式：</label>
					<span>${project.ebuyMethodCN }</span>
					<input id="singlePrice" type="hidden" name="singlePrice" value="<c:if test="${fn:indexOf(singlePrice,'单')<0}">0</c:if><c:if test="${fn:indexOf(singlePrice,'单')>=0}">1</c:if>">
				</li>
				<li>
					<label>总预算：</label>
					<span><fmt:formatNumber value="${project.budgetTotalMoney }" pattern="#,##0.00#" /></span>
				</li>
		    </ul>
		</div>
		
		<div class="orderManagement">
			<!-- 项目需求信息 -->
			<table class="frontTableList" id="goodsAndOption">
				<thead>
					<tr>
						<th>需求商品/描述</th>
						<th width="100px">需求数量</th>
						<th width="100px">计量单位</th>
						
						<c:if test="${buyerBudget!='0'}">
						<th width="150px">期望价格(元)</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
						<c:forEach var="requireItem" items="${requireItemList}">
							<tr class="goodsInfo">
								<td>
									<c:choose>
										<c:when test="${requireItem.goods!=null}">
											${requireItem.goods.productName }
										</c:when>
										<c:otherwise>
											${requireItem.descr }
										</c:otherwise>
									</c:choose>
									<!-- 有零配件  -->
									<c:if test="${!empty requireItem.requireGoodsAccess && fn:length(requireItem.requireGoodsAccess) > 0}">
										<a href="javascript:void(0);" title="商品零配件详情" onclick="projectDetail.showOrHideGoodsAccess(this);return false;"><img src="view/resource/skin/sysicon/16/goods_access.png" /></a>
									</c:if>
								</td>					
								<td align="right">${requireItem.goodsQty }</td>
								<td align="center">${requireItem.goodsUnit }</td>
								<c:if test="${buyerBudget!='0'}">
								<td align="right"><fmt:formatNumber value="${requireItem.goodsPrice }" pattern="#,##0.00#" /></td>
								</c:if>
							</tr>
							
							<!-- 零配件 -->
							<c:if test="${!empty requireItem.requireGoodsAccess && fn:length(requireItem.requireGoodsAccess) > 0}">
							<tr class="hidden">
								<td colspan="4">
									<div>
									<table>
										<tr>
										<c:forEach items="${requireItem.requireGoodsAccess}" var="requireGoodsAccess">
										<td>
											<div style="float:left">
												<img class="goodsImg" src='<c:url value="AttachmentController.do?method=showImg&objId=${requireGoodsAccess.goodsAccess.accessoryGoods.picture}" />' >
												<div class="fitting" style="float:left">
												${requireGoodsAccess.goodsAccess.accessoryGoods.productName}<br/>
												￥<fmt:formatNumber value="${requireGoodsAccess.accessPrice}" pattern="#,##0.00#"/>
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
				<tfoot>
					<tr>
						<td colspan="7">
							<ul>
									<li>金额总计：<strong>￥<span id="totalMoney"><fmt:formatNumber value="${project.budgetTotalMoney}" pattern="#,##0.00#" /></span></strong>元</li>
							</ul>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
	
	<c:if test="${buyResult!=null && fn:length(buyWinnerList)>0 }">
		<div class="formLayout form2Pa formTag" >
			<h4><span><img src="view/resource/skin/skin07/img/leaderBg.png" />&nbsp;成交结果</span></h4>	
			<ul>
				<c:forEach var="buyWinner" items="${buyWinnerList}">
				<li>
					<span>${buyWinner.sellerName } <span style="<c:if test="${buyWinner.resultType=='00'}">color:red;</c:if>">${buyWinner.resultTypeCn }</span></span>
				</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	
	<div id="ruleTurnView">
		<div class="formLayout form2Pa formTag">
			<h4><span><img src="view/resource/skin/skin07/img/leaderBg.png" />&nbsp;轮次安排</span></h4>
			<ul>
				<c:forEach var="bargainTurn" items="${bargainTurnList}">
			   	<li class="fullLine">
			   		<label>
				        <c:choose>
							<c:when test="${fn:length(bargainTurnList )==1 }">
								共 <strong>${fn:length(bargainTurnList)}</strong> 轮：
							</c:when>
							<c:otherwise>
								第 <strong>${bargainTurn.turnNo }</strong> 轮：
							</c:otherwise>
						</c:choose>
					</label>
					<span>
						最低降幅：${bargainTurn.totalCut}<c:choose>
								<c:when test="${bargainTurn.totalCutType=='01'}">(%)</c:when>						
								<c:otherwise>(元)</c:otherwise>
						 </c:choose>
					&nbsp;&nbsp;报价时间：<fmt:formatDate value="${bargainTurn.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;至&nbsp;<fmt:formatDate value="${bargainTurn.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</span>
			   	</li>
			   	</c:forEach>
			</ul>
		</div>
		<div class="formLayout form2Pa formTag">
		
		    <h4><span><img src="view/resource/skin/skin07/img/leaderBg.png" />&nbsp;项目规则</span></h4>
		    <ul>
		    	<c:forEach var="projrule" items="${projruleList}" varStatus="status">
		    		<c:if test="${projrule.resValue !=null&& projrule.resValue !=''}" >
						<li><label><img src="view/resource/skin/skin07/img/dot.png" />&nbsp;</label>
							<span class="">
								<c:if test="${projrule.code=='bargainNumber' && projrule.resValue=='1'}">
									${projrule.sysItemName}：单次报价
								</c:if>
								<c:if test="${projrule.code=='bargainNumber' && projrule.resValue=='2'}">
									${projrule.sysItemName}：多次报价
								</c:if>
								<c:if test="${projrule.code!='bargainNumber'}">
									${projrule.sysItemName}：${projrule.resValue}
								</c:if>
							</span>
						</li>
		    		</c:if>
				</c:forEach>
		    </ul>
		</div>
	</div>
	<div id="extInfo"></div>
</div>

<div class="formLayout">	
	<!-- 轮次报价信息 -->
	<div id="turnTabs">
		<ul>	
		<c:forEach var ="bargainTurn" items="${bargainTurnList}">
			<li>
				<a href="#turnData" class="refreshData" onclick="projectDetail.turnChange('${bargainTurn.objId }');return false;">轮次号：${bargainTurn.turnNo}</a>
			</li>
		</c:forEach>
		</ul>
		<div id="turnData">
			<%@ include file="/view/agreement/bargin/project/project_detail_record_turn.jsp" %>
		</div>
	</div>
</div>

<div class="conOperation">
	<c:if test="${project.projProcessStatus=='20 '}">
		<button type="button" onclick="projectDetail.operator('update')"><span>修改项目</span></button>
		<button type="button" onclick="projectDetail.operator('updateRule')"><span>修改规则</span></button>
		<button type="button" onclick="projectDetail.operator('submit')"><span>提交</span></button>
	</c:if>
	<c:if test="${project.projProcessStatus=='160'&& evalStartTime <= cureentDate && evalEndTime >= cureentDate }">
		<button type="button" onclick="projectDetail.operator('intoHall')"><span>进入竞价厅</span></button>
	</c:if>
	<c:if test="${project.projProcessStatus=='170'&& evalEndTime < cureentDate}">
		<button type="button" onclick="projectDetail.operator('confirm')"><span>确定成交结果</span></button>
	</c:if>
	<c:if test="${project.projProcessStatus=='200'}">
		<button type="button" onclick="projectDetail.operator('viewResult')"><span>查看成交结果</span></button>
	</c:if>
	
	<button type="button" name="historyBackBtn" ><span>返回</span></button>
	<c:if test="${project.projProcessStatus <= '170' && cureentDate < evalEndTime }">
		<button type="button" onclick="projectDetail.operator('sendInvitation')" ><span>发送邀请函</span></button>
	</c:if>
	
</div>

<script type="text/javascript">
var projectDetail = {};

//显示或隐藏商品零配件
projectDetail.showOrHideGoodsAccess = function(obj){
	$(obj).parent().parent().next().toggle();
}

//操作
projectDetail.operator = function(opType){

	//修改项目
	if("update"==opType){
		$('#conBody').loadPage($('#initPath').val()+"/BargainProjectController.do?method=toUpdateBargainProject&objId="+$("#projectId").val()+"&type=s");
	}
	
	//修改规则
	else if("updateRule"==opType){
		$('#conBody').loadPage($('#initPath').val()+"/BargainProjectController.do?method=toUpdateTurnAndRule&objId="+$("#projectId").val());
	}

	//提交
	else if("submit"==opType){
		var jsonObj = {};
		jsonObj.objId = $("#projectId").val();
		jsonObj.auditStatus = '01';//通过
		jsonObj.useStatus = '01';//有效
		$.getJSON($('#initPath').val()+'/BargainProjectController.do?method=createProject_desc',jsonObj, function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			//跳转至反拍项目列表
			$('button[name=historyBackBtn]').click();
		})
	}

	//进入竞价厅
	else if("intoHall"==opType){
		var url = $('#initPath').val()+"/BargainProjectController.do?method=toBuyerBargainPage&objId="+$("#projectId").val();
		window.open(url);
	}
	
	//确认结果
	else if("confirm"==opType){
		$("#conBody").loadPage($("#initPath").val()+"/BuyResultXyghController.do?method=toConfirmResultBySupplierView&projectId="+$("#projectId").val() );
	}

	//查看结果
	else if("viewResult"==opType){
		$("#conBody").loadPage($("#initPath").val()+"/BuyResultXyghController.do?method=toResultDetailView&projectId="+$("#projectId").val() );
	}

	//发送邀请函
	else if("sendInvitation"==opType){
		$("#conBody").loadPage($("#initPath").val()+"/InvitationController.do?method=toSendInvitationForm&projectId="+$("#projectId").val() );
	}
}

//轮次切换
projectDetail.turnChange = function(turnId){
	$("#turnData").loadPage($("#initPath").val()+"/BargainProjectController.do?method=loadMinRecordByTurnProjSupplierView&userType=buyer&projectId="+$("#projectId").val()+"&turnId="+turnId+"&singlePrice="+$("#singlePrice").val() );
}

$(document).ready(function(){
	$('#epsTabs').tabs({});

	//切换项目信息tab
	$("#epsTabs").find("ul li a").click(function(){
		if(this.id){
			$("#extInfo").loadPage($("#initPath").val()+"/"+this.id+".do?method=getInfoByProject&projectId="+$("#projectId").val());
		}
	});

	$('#turnTabs').tabs({}); 
})
</script>