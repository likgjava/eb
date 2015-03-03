<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
.tableList a{
	color:#3366CC;
}
</style>

<input type="hidden" id="turnId" name="turnId" value="<c:if test="${turnId!=null}">${turnId}</c:if><c:if test="${param.turnId!=null}">${param.turnId}</c:if>">

<table class="tableList">
	<thead>
		<tr>
			<th style="width:150px;">供应商名称</th>
			<th style="width:10%;">最低报价</th>
			<th>报价商品<c:if test="${fn:indexOf(singlePrice,'单')>=0||param.singlePrice=='1'}">/最低报价(元)/服务条款</c:if></th>
			<c:if test="${fn:indexOf(singlePrice,'总')>=0||param.singlePrice=='0'}"><th>服务条款</th></c:if>
			<th style="width:14%;">报价详情</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="biddingRecord" items="${biddingRecordList}">
		<tr>
			<td id="orgName">
			<a href="javascript:void(0);" onclick="projectDetailRecord.goSupplierPage('${biddingRecord.supplier.objId}');return false;" >${biddingRecord.supplier.orgName }</a>
			<a href="javascript:void(0);" onclick="projectDetailRecord.showSignUpRecord('${biddingRecord.supplier.objId }');return false;" >[报名信息]</a>
			</td>	
			<td align ="right"><fmt:formatNumber value="${biddingRecord.goodsTotal }" pattern="#,##0.00#" /></td>		
			<td>
				<table style="border:0"> 
					<c:forEach var = "biddingRecordItem" items="${biddingRecord.biddingRecordItemSet}">
						<tr>
							<td>
							${biddingRecordItem.goods.productName }
							</td>
							<c:if test="${fn:indexOf(singlePrice,'单')>=0||param.singlePrice=='1'}">
								<td style="width:20%;"><fmt:formatNumber value="${biddingRecordItem.goodsTotal }" pattern="#,##0.00#" /></td>
								<td style="width:40%;">${biddingRecordItem.serviceContent }</td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</td>
			<c:if test="${fn:indexOf(singlePrice,'总')>=0||param.singlePrice=='0'}">
				<td>${biddingRecord.serviceContent }</td>
			</c:if>
			<td align="center">
				<a href="javascript:void(0);" onclick="projectDetailRecord.turndetail('${biddingRecord.supplier.objId}','${biddingRecord.bargainTurn.objId}');return false;">详情</a>
				<a href="javascript:void(0);" onclick="projectDetailRecord.chatdetail('${biddingRecord.supplier.objId}','${biddingRecord.bargainTurn.objId }');return false;">聊天记录</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<script type="text/javascript">
	var projectDetailRecord = {};

	//跳转供应商
	projectDetailRecord.goSupplierPage = function(supplierId){
		$.epsDialog({
			id:"showSupplierDetail",
			title:"供应商详情",
			url:$("#initPath").val()+"/ExOrgInfoController.do?method=getExAllBaseInfo&orgId="+supplierId
		})
	}

	//跳转商品
	projectDetailRecord.showGoodsDetail = function(goodsId){
		$.epsDialog({
			id:"showGoodsDetail",
			title:"商品详情",
			url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&isShowPic=true&objId="+goodsId
		})
	}

	//供应商轮次报价详情
	projectDetailRecord.turndetail = function(supplierId , turnId ){
		if(null==turnId||""==turnId){
			turnId = $("input[name=turnId]").val();
		}
		$.epsDialog({
			width:700,
			height:400,
			id:"toResultByTurnView",
			title:"供应商轮次报价详情",
			url:$("#initPath").val()+"/BuyResultXyghController.do?method=toResultByTurnView&supplierId="+supplierId+"&projectId="+$("#projectId").val()+"&turnId="+turnId
		})
	}
	
	//显示报名记录
	projectDetailRecord.showSignUpRecord = function(supplierId){
		$.epsDialog({
			id:"",
			title:"供应商报名信息",
			url:$("#initPath").val()+"/SupplierSignupController.do?method=toSupplierSignUpDetail&objId="+supplierId+"&projectId="+$("#projectId").val()
		})
	}

	//显示聊天记录
	projectDetailRecord.chatdetail = function(supplierId,turnId){
		if(!turnId){turnId = $("input[name=turnId]").val();}
		$.epsDialog({
			id:"chatRecordDiv",
			title:"报价聊天记录",
			url:$("#initPath").val()+"/BiddingChatController.do?method=toChatRecordDetailView&projId="+$("#projectId").val()+"&turnId="+turnId+"&orgInfoId="+supplierId
		})
	}
	
</script>
