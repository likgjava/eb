<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input id="projectId" name="projectId" type="hidden" value="${param.projectId }">
<input id="supplierId" name="supplierId" type="hidden" value="${param.supplierId }">

<div class="formLayout form2Pa">
	<ul>
		<li><label>机构名称：</label> <span id="orgName">${signUprecord.supplier.orgName}</span>
		</li>
		<li><label>机构代码：</label> <span id="orgCode">${signUprecord.supplier.orgCode}</span>
		</li>
		<li><label>联系手机：</label> <span id="company.mobilePhone">${signUprecord.supplier.company.mobilePhone}</span>
		</li>
		<li><label>联系电话：</label> <span id="company.tel">${signUprecord.supplier.company.tel}</span>
		</li>
		<li><label>行政区域：</label> <span id="distinctName">${signUprecord.supplier.distinctName}</span>
		</li>
		<li><label>邮编：</label> <span id="company.postCode">${signUprecord.zipCode}</span>
		</li>
		<li class="fullLine"><label>详细通信地址：</label> <span id="company.address">${signUprecord.supplier.company.address}</span>
		</li>
	</ul>
</div>


<div id="epsTabs">
	<c:if test="${param.turnId==null}">
	<ul>
		<c:forEach var ="tabCell" items="${tabResult}">
		<li>
			<a href="#newBrand" onclick="supplierTurnResult.changeTab('${tabCell[0]}');return false;" class="refreshData"><span>轮次号：${tabCell[1] }</span></a>
		</li>
		</c:forEach>
	</ul>
	</c:if>
	<div id="newBrand">
		<table class="tableList" id="dataList" >
		<thead>
			<tr>
				<th>报价时间</th>
				<th>总金额</th>
				<th>报价详情</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${firstResult}">
			<tr>
				<td class="center">
				<fmt:formatDate value="${result[2] }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="right"><fmt:formatNumber value="${result[1] }" pattern="#,##0.00#" /></td>
				<td align="center"><a href="javascript:void(0);" onclick="supplierTurnResult.recordItem('${result[0]}');return false;" >详情</a></td>				
			</tr>
			</c:forEach>
		</tbody>
		<tfoot></tfoot>
		</table>
	</div>
</div>


<div class="conOperation">
	<button type="button" onclick="supplierTurnResult.close()" ><span>关闭</span></button>
</div>
<script type="text/javascript">

var supplierTurnResult = {};
//关闭
supplierTurnResult.close = function(){
	$("#toResultByTurnView").find('.epsDialogClose').trigger('click');
}

//按轮次切换
supplierTurnResult.changeTab = function(turnId){
	$.getJSON($("#initPath").val()+"/BuyResultXyghController.do?method=getSingleTurnRecordDate",
			{"turnId":turnId,"projectId":$("input[name=projectId]").val(),"supplierId":$("input[name=supplierId]").val()},function(json){
				if(json.success){
					$("#dataList").find("tbody").find("tr").remove();
					$.each(json.list,function(index,obj){
						$("#dataList").find("tbody").append('<tr>'+
								'<td class="center">'+obj[2]+'</td>'+
								'<td align="right">'+formatAmount(Number(obj[1]),2)+'</td>'+
								'<td align="center"><a href="javascript:void(0);" onclick="supplierTurnResult.recordItem(\''+obj[0]+'\');return false;" >详情</a></td>'+	
								'</tr>');
					});
				}
	})
}

//报价详情
supplierTurnResult.recordItem = function(recordId){
	$.epsDialog({
		id:"recordItemDiv",
		title:"商品报价详情",
		url:$("#initPath").val()+"/BuyResultXyghController.do?method=toRecordItemByRecordView&recordId="+recordId+"&singlePrice="+$("#singlePrice").val(),
		width:800,
		heihgt:600
	})
}

$(document).ready(function(){
	//初始化tabs
	$('#epsTabs').tabs({}); 
})

</script>