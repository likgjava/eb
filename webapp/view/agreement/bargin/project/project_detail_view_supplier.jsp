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
			<!-- 项目基本信息-->
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
				</li>
				<c:if test="${buyerBudget!='0'}">
				<li>
					<label>总预算(元)：</label>
					<span><fmt:formatNumber value="${project.budgetTotalMoney }" pattern="#,##0.00#" /></span>
				</li>
				</c:if>
			</ul>
		</div>
		

		
		<div class="formLayout form2Pa formTag" >
			<h4><span><img src="view/resource/skin/skin07/img/leaderBg.png" />&nbsp;报名信息</span></h4>	
			<!-- 供应商报名信息-->
			<ul>
				<li>
					<label>联系人：</label>
					<span>${signUprecord.linker }</span>
				</li>
				<li>
					<label>身份证号：</label>
					<span>${signUprecord.idCard }</span>
				</li>
				<li class="fullLine">
					<label>联系地址：</label>
					<span>${signUprecord.address }</span>
				</li>
				<li>
					<label>联系电话：</label>
					<span>${signUprecord.linkerTel }</span>
				</li>
				<li>
					<label>邮编：</label>
					<span>${signUprecord.zipCode }</span>
				</li>
				<li class="fullLine">
					<label>报名文件：</label>
					<div class="uploadFile"><input id="attachRelaId" type="hidden" value="${signUprecord.attachRelaId }"><span id="attachRelaId"></span></div>
				</li>
				<li class="fullLine">
					<label>备注：</label>
					<span>${signUprecord.memo }</span>
				</li>
			</ul>
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
								</td>					
								<td align="right">${requireItem.goodsQty }</td>
								<td align="center">${requireItem.goodsUnit }</td>
								<c:if test="${buyerBudget!='0'}">
								<td align="right"><fmt:formatNumber value="${requireItem.goodsPrice }" pattern="#,##0.00#" /></td>
								</c:if>
							</tr>
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
	<div id="ruleTurnView">
		<div class="formLayout form2Pa formTag" >
			<h4><span><img src="view/resource/skin/skin07/img/leaderBg.png" />&nbsp;项目规则</span></h4>	
			<!-- 项目规则-->
			<ul>
				<c:forEach var="projrule" items="${projruleList}" varStatus="status">
		    		<c:if test="${projrule.resValue !=null&& projrule.resValue !=''}" >
						<li><label><img src="view/resource/skin/skin07/img/dot.png" />&nbsp;</label>
							<span class="">
								<c:if test="${projrule.code=='bargainNumber' && projrule.resValue=='0'}">
									${projrule.sysItemName}：单次报价
								</c:if>
								<c:if test="${projrule.code=='bargainNumber' && projrule.resValue=='1'}">
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
			<%@ include file="/view/agreement/bargin/project/project_detail_record_turn_supplier.jsp" %>
		</div>
	</div>
</div>

<div class="conOperation">
	<c:if test="${project.projProcessStatus=='160'&& evalStartTime <= cureentDate && evalEndTime >= cureentDate }">
		<button type="button" onclick="projectDetail.operatorSupplier('intoHall')"><span>进入竞价厅</span></button>
	</c:if>
	<c:if test="${project.projProcessStatus=='200'}">
		<button type="button" onclick="projectDetail.operatorSupplier('viewResult')"><span>查看结果</span></button>
	</c:if>
	<button type="button" name="historyBackBtn"><span>返回</span></button>
</div>


<script type="text/javascript">
var projectDetail = {};

//操作
projectDetail.operatorSupplier = function(opType){
	//进入竞价厅
	if("intoHall"==opType){
		var url = $('#initPath').val()+"/BargainProjectController.do?method=toSupplierBargainPage&objId="+$("#projectId").val();
		window.open(url);
	}
	
	//查看结果
	else if("viewResult"==opType){
		$("#conBody").loadPage($("#initPath").val()+"/BuyResultXyghController.do?method=toResultDetailView&projectId="+$("#projectId").val()+"&userType=supplier" );
	}
}

//显示聊天记录
projectDetail.chatdetail = function(recordId){
	$.epsDialog({
		id:"chatRecordDiv",
		title:"报价聊天记录",
		url:$("#initPath").val()+"/view/agreement/bargin/bidding/chat_list.jsp?recordId="+recordId
	})
}

//供应商轮次报价详情
projectDetail.turndetail = function(supplierId , turnId ){
	$.epsDialog({
		width:700,
		height:400,
		id:"toResultByTurnView",
		title:"供应商轮次报价详情",
		url:$("#initPath").val()+"/BuyResultXyghController.do?method=toResultByTurnView&supplierId="+supplierId+"&projectId="+$("#projectId").val()+"&turnId="+turnId
	})
}

//轮次切换
projectDetail.turnChange = function(turnId){
	$("#turnData").loadPage($("#initPath").val()+"/BargainProjectController.do?method=loadMinRecordByTurnProjSupplierView&userType=supplier&projectId="+$("#projectId").val()+"&turnId="+turnId+"&singlePrice="+$("#singlePrice").val() );
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

	$('span[id=attachRelaId]').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=attachRelaId&isSelect=yes&attachRelaId='+$("input[id=attachRelaId]").val());
	
})
</script>