<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" name="appType" id="appType" value="${param.appType }">
<input id="singlePrice" name="singlePrice" type="hidden" value="${singlePrice }"/>
<input id="projectId" name="" type="hidden" value="${projectHall_Supplier.objId }">
<div class="formLayout form2Pa" id="projInfo">
	<h4>项目信息</h4>
	<ul>
		<li class="fullLine">
			<label>项目名称：</label>
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
	         	<span id="idealArea">￥<fmt:formatNumber value="${projectHall_Buyer.budgetTotalMoney}" pattern="#,##0.00#" />（元）</span>
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


<div class="formLayout">
<table class="tableList" id="goodsAndOption">
	<caption>商品需求信息</caption>
	<thead>
		<tr>
		<th>商品名称</th>
		<th>计量单位</th>
		<th>数量</th>
		<th>市场价(元)</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var ="requireItem" items="${requireItemList}">
	<tr>
		<td>${requireItem.goods.productName }
			<!-- 有礼包 -->
			<c:if test="${!empty requireItem.requireGoodsGifts && fn:length(requireItem.requireGoodsGifts) > 0}">
				<a href="javascript:void(0);" title="商品礼包详情" onclick="confirmResultSupplier.showOrHideGoodsGift(this);return false;"><img src="view/resource/skin/sysicon/16/goods_gift.png" /></a>
			</c:if>
			<!-- 有零配件  -->
			<c:if test="${!empty requireItem.requireGoodsAccess && fn:length(requireItem.requireGoodsAccess) > 0}">
				<a href="javascript:void(0);" title="商品零配件详情" onclick="confirmResultSupplier.showOrHideGoodsAccess(this);return false;"><img src="view/resource/skin/sysicon/16/goods_access.png" /></a>
			</c:if>
		</td>
		<td align="center">${requireItem.goodsUnit }</td>
		<td align="right">${requireItem.goodsQty }</td>
		<td align="right"><fmt:formatNumber value="${requireItem.marketPrice }" pattern="#,##0.00#" /></td>
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
</table>
</div>

<table class="tableList">
	<caption>待成交供应商</caption>
	<thead>
		<tr>
			<th style="width:20px;" ><input type="checkbox" name="checkAll"  onclick = "confirmResultSupplier.checkAllOrNot(this)" ></th>
			<th>供应商名称</th>
			<th>最低报价(元)</th>
			<th>报价商品</th>
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
									<a href="javascript:void(0);" onclick="confirmResultSupplier.showGoodsDetail('${biddingRecordItem.requireItem.goods.objId}');return false;" >
									${biddingRecordItem.requireItem.goods.productName }</a>
								</c:when>
								<c:otherwise>
									${biddingRecordItem.requireItem.descr }
								</c:otherwise>
							</c:choose>
							</td>
							<c:if test="${singlePrice=='1'||projectHall_Supplier.ebuyMethod=='05'}">
								<td><fmt:formatNumber value="${biddingRecordItem.goodsTotal }" pattern="#,##0.00#" /></td>
							</c:if>
							<c:if test="${singlePrice=='1'&&projectHall_Supplier.ebuyMethod=='06'}">
								<td>${biddingRecordItem.serviceContent }</td>
							</c:if>
						</tr>
						<c:set var="serviceContent" value="${serviceContent }${biddingRecordItem.serviceContent}"/>
					</c:forEach>
				</table>
			</td>
			<c:if test="${singlePrice!='1' && projectHall_Supplier.ebuyMethod!='05'}">
				<td>${biddingRecord.serviceContent }</td>
			</c:if>
			<td align="center">
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
	<button type="button" onclick="confirmResultSupplier.reback();"><span>返回</span></button>
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
		id:"showSupplierDetail",
		title:"供应商详情",
		url:$("#initPath").val()+"/ExOrgInfoController.do?method=getExAllBaseInfo&orgId="+supplierId
	})
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

	var dealSupplier = [];
	var lostSupplier = [];

	$.each( $("input[name=checkCell]") ,function( index , obj ){
		if($(obj).attr("checked")){
			dealSupplier.push( $(obj).attr("value") );
		}else{
			lostSupplier.push( $(obj).attr("value") );
		}
	})
	
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
	var param = {
		"confirmType":"confirm",
		"projectId":$("#projectId").val(),
		"supplierPrice":supplierPrice,
		"dealSupplier":dealSupplier.toString(),
		"lostSupplier":lostSupplier.toString()
		};

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
		content:'<div class="formLayout form2Pa"><ul><li class="formTextarea"><label for="">放弃原因：</label><textarea id="opinion"></textarea>'+
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
				confirmResultSupplier.reback();
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
	window.location.reload();
}

//全选或全不选事件
confirmResultSupplier.checkAllOrNot = function(e){
	$("input[name^=check]").attr("checked",$(e).attr("checked"));
}

//显示或隐藏商品礼包
confirmResultSupplier.showOrHideGoodsGift = function(obj){
	$(obj).parent().parent().next().toggle();
}
//显示或隐藏商品零配件
confirmResultSupplier.showOrHideGoodsAccess = function(obj){
	$(obj).parent().parent().next().toggle();
}

$(document).ready(function(){
})
</script>