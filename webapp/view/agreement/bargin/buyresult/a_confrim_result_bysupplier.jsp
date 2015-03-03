<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" name="appType" id="appType" value="${param.appType }">
<input id="singlePrice" name="singlePrice" type="hidden" value="${singlePrice }"/>
<input id="projectId" name="" type="hidden" value="${projectHall_Supplier.objId }">
<div class="formLayout form2Pa" id="projInfo">
	<ul>
		<li class="fullLine">
			<label>当前项目：</label>
			<span id="currentProj">${projectHall_Supplier.projName}</span>
		</li>
		<li class="fullLine">
			<label>项目编号：</label>
			<span id="projCode">${projectHall_Supplier.projCode}</span>
		</li>
        <li class="fullLine">
            <label>${projectHall_Supplier.ebuyMethodCN}周期：</label>
         	<span id="bargainStartAndEnd">
         	<fmt:formatDate value="${projectHall_Supplier.evalStartTime}" pattern="yyyy-MM-dd HH:mm"/> 至 
         	<fmt:formatDate value="${projectHall_Supplier.evalEndTime}" pattern="yyyy-MM-dd HH:mm"/></span>
        </li>
        <%--根据项目规则判断是否显示--%>
        <c:forEach var="projRule" items="${projruleList}" varStatus="status">
        	<c:if test="${projRule.code=='idealArea' && projRule.resValue=='1'}">
	        <li class="fullLine">
	            <label>理想值：</label>
	         	<span id="idealArea">￥<fmt:formatNumber value="${projectHall_Supplier.budgetTotalMoney}" pattern="#,##0.00#" />（元）</span>
	        </li>
	        </c:if>
        	<c:if test="${projRule.code=='maxPrice' && projRule.resValue=='1'}">
	        <li class="fullLine">
	            <label>最高报价：</label>
	         	<span id="maxPrice">￥<fmt:formatNumber value="${maxPrice}" pattern="#,##0.00#" />（元）</span>
	        </li>
	        </c:if>
	        <c:if test="${projRule.code=='minPrice' && projRule.resValue=='1'}">
	        <li class="fullLine">
	            <label>最低报价：</label>
	         	<span id="minPrice">￥<fmt:formatNumber value="${minPrice}" pattern="#,##0.00#" />（元）</span>
	        </li>
	        </c:if>
	        <c:if test="${projRule.code=='averagePrice' && projRule.resValue=='1'}">
	        <li class="fullLine">
	            <label>平均报价：</label>
	         	<span id="averagePrice">￥<fmt:formatNumber value="${avgPrice}" pattern="#,##0.00#" />（元）</span>
	        </li>
	        </c:if>
        </c:forEach>
	</ul>
</div>
<table class="tableList">
	<caption>待成交供应商</caption>
	<thead>
		<tr>
			<th style="width:20px;" ><input type="checkbox" name="checkAll"  onclick = "confirmResultSupplier.checkAllOrNot(this)" ></th>
			<th>供应商名称</th>
			<th>最低报价（元）</th>
			<th>报价商品<c:if test="${singlePrice=='1'}">/最低报价/服务条款</c:if></th>
			<c:if test="${singlePrice!='1'&& projectHall_Supplier.ebuyMethod!='05'}">
			<th>服务条款</th>
			</c:if>
			<th>报价详情</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="biddingRecord" items="${biddingRecordList}">
		<tr>
			<td><input type="checkbox" name="checkCell" value="${biddingRecord.supplier.objId }"></td>
			<td id="orgName" ><a href="javascript:void(0);" onclick="confirmResultSupplier.showDetail('${biddingRecord.supplier.objId }',null);return false;"  >${biddingRecord.supplier.orgName }</a></td>	
			<td align ="right"><fmt:formatNumber value="${biddingRecord.goodsTotal }" pattern="#,##0.00#" /></td>		
			<td>
				<table style="border:0"> 
				<c:set var="serviceContent" value=""/>
					<c:forEach var = "biddingRecordItem" items="${biddingRecord.biddingRecordItemSet}">
						<tr>
							<td>
							<c:choose>
								<c:when test="${biddingRecordItem.requireItem.goods != null }">
									<a href="javascript:void(0);" onclick="confirmResultSupplier.showGoodsDetail('${biddingRecordItem.requireItem.goods.objId}',null);return false;" >
									${biddingRecordItem.requireItem.goods.productName }</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0);" onclick="confirmResultSupplier.showGoodsDetail('${biddingRecordItem.goods.objId}',null);return false;" >
									${biddingRecordItem.goods.productName }</a>
								</c:otherwise>
							</c:choose>
							</td>
							<c:if test="${singlePrice=='1'}">
								<td><fmt:formatNumber value="${biddingRecordItem.goodsTotal }" pattern="#,##0.00#" /></td>
								<td>${biddingRecordItem.serviceContent }</td>
							</c:if>
						</tr>
						<c:set var="serviceContent" value="${serviceContent } ${biddingRecordItem.serviceContent}"/>
					</c:forEach>
				</table>
			</td>
			<c:if test="${singlePrice!='1'&& projectHall_Supplier.ebuyMethod!='05'}">
				<td>${biddingRecord.serviceContent }</td>
			</c:if>
			<td class="operation">
				<a href="javascript:void(0);" onclick="confirmResultSupplier.turndetail('${biddingRecord.supplier.objId}');return false;">详情</a>
				<a href="javascript:void(0);" onclick="confirmResultSupplier.chatdetail('${biddingRecord.supplier.objId}','${projectHall_Supplier.objId }');return false;" >聊天记录</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
	
	<tfoot></tfoot>
</table>


<div class="conOperation">
	<c:if test="${fn:length(biddingRecordList)>0}">
		<button type="button" onclick="confirmResultSupplier.confrimSupplier()"><span>确认成交供应商</span></button>
	</c:if>
	<button type="button" onclick="confirmResultSupplier.cancelSupplier()"><span>放弃成交</span></button>
	<button type="button" name="historyBackBtn"><span>返回</span></button>
</div>
<script type="text/javascript">
var confirmResultSupplier = {};

//显示商品详情
confirmResultSupplier.showGoodsDetail = function(goodsId,tabId) {
	$.epsDialog({
		id:"showGoodsDetail",
		title:"商品详情",
		url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&isShowPic=true&objId="+goodsId
	})
}

//显示供应商详情
confirmResultSupplier.showDetail = function(supplierId,tabId) {
	$.epsDialog({
		id:"showOrgDetail",
        title:'供应商详情',
		url:$("#initPath").val()+"/ExOrgInfoController.do?method=getExAllBaseInfo&orgId="+supplierId
    }); 
}

//显示聊天记录
confirmResultSupplier.chatdetail = function(orgInfoId,projId){
	$.epsDialog({
		id:"chatRecordDiv",
		title:"报价聊天记录",
		url:$("#initPath").val()+"/BiddingChatController.do?method=toChatRecordDetailView&orgInfoId="+orgInfoId+"&projId="+projId
	})
}

//供应商轮次报价详情
confirmResultSupplier.turndetail = function(supplierId){
	$.epsDialog({
		width:700,
		height:400,
		id:"toResultByTurnView",
		title:"供应商轮次报价详情",
		url:$("#initPath").val()+"/BuyResultXyghController.do?method=toResultByTurnView&supplierId="+supplierId+"&projectId="+$("#projectId").val()
	})
}


//确定成交供应商
confirmResultSupplier.confrimSupplier = function(){
	var checked = $("input[name=checkCell]:checked");
	if(checked.length==0){
		alert("请至少选择一个供应商作为成交供应商或放弃成交该项目！");
		return;
	}
	var supplierPrice = "";
	$.each($("input[name=checkCell]"),function(index,obj){
		if(index==0){
			supplierPrice += $(obj).val()+":"+$(obj).parent().parent().find("td[id=orgName]").find("a").html()+":"+$(obj).attr("checked");
		}else{
			supplierPrice += ","+$(obj).val()+":"+$(obj).parent().parent().find("td[id=orgName]").find("a").html()+":"+$(obj).attr("checked");
		}
	})
	var param = {"confirmType":"confirm","projectId":$("#projectId").val(),"supplierPrice":supplierPrice};

	//alert(JSON.stringify(param));return ;
	if(confirm("确定成交结果？")){
		$.getJSON($("#initPath").val()+"/BuyResultXyghController.do?method=saveBuyResult",param,function(json){
			if(json.success){
				alert("操作成功！");
				$("#conBody").loadPage($("#initPath").val()+"/BuyResultXyghController.do?method=toResultDetailView&projectId="+$("#projectId").val()+"&appType="+$("#appType").val() );				
			}
		})
	}
}

//放弃成交
confirmResultSupplier.cancelSupplier = function(){

	$.epsDialog({
		id:"givupOpinionDiv",
		title:"放弃成交",
		width:500,
		height:200,
		content:'<div class="formLayout form1Pa"><ul><li class="formTextarea"><label for="">放弃原因：</label><textarea id="opinion"></textarea>'+
			'</li></ul></div><div class="conOperation"><button type="button" id="confirmGiveup"><span>确定放弃</span></button>'+
			'<button type="button" id="closeGiveup"><span>关闭</span></button></div>'
	});
	//确认放弃
	$("#confirmGiveup").click(function(){
		var param = {"confirmType":"giveup","projectId":$("#projectId").val(),"opinion":$("#opinion").val()};
		$.getJSON($("#initPath").val()+"/BuyResultXyghController.do?method=saveBuyResult",param,function(json){
			if(json.success){
				alert("操作成功！");
				$("#givupOpinionDiv").find(".epsDialogClose").click();
				$("button[name=historyBackBtn]").click();
			}
		})
	})
	//关闭
	$("#closeGiveup").click(function(){
		$("#givupOpinionDiv").find(".epsDialogClose").click();
	})
}

//返回
confirmResultSupplier.reback = function(){
	
}

//全选或全不选事件
confirmResultSupplier.checkAllOrNot = function(e){
	$("input[name^=check]").attr("checked",$(e).attr("checked"));
}



$(document).ready(function(){
})
</script>