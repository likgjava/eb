<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">
	<h4>确定成交结果</h4>
	<input type="hidden" id="projectId" value="${project.objId }">
	<ul>
	<li class="fullLine">
		<label>项目名称：</label>
		<span>${project.projName }</span>
	</li>
	<li class="fullLine">
		<label for="">项目编号：</label>
		<span>${project.projCode }</span>
	</li>
	<li class="fullLine">
		<label for="">创建人：</label>
		<span>${project.createUser.emp.name }</span>
	</li>
	</ul>
</div>

<table class="tableList">
	<caption>待成交供应商</caption>
	<thead>
		<tr>
			<th>商品名称</th>
			<th>市场价</th>
			<th>单价</th>
			<th>数量</th>
			<th>金额</th>
			<th>报价供应商</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="map" items="${glist}">
				<tr>
					<td rowspan="${fn:length(map['subList'])}">
						${map['goodsName']}
					</td>
					<td align="right"><fmt:formatNumber value="${ map['subList'][0]['marketPrice']}" pattern="#,##0.00#" /></td>
					<td align="right"><fmt:formatNumber value="${ map['subList'][0]['goodsPrice']}" pattern="#,##0.00#" /></td>
					<td align="right">${ map['subList'][0]['goodsQty']}</td>
					<td align="right"><fmt:formatNumber value="${ map['subList'][0]['goodsTotal']}" pattern="#,##0.00#" /></td>
					<td>${ map['subList'][0]['supplierName']}</td>
				</tr>
				<c:if test="${fn:length(map['subList'])>1}">
					<c:forEach var ="subMap" items="${map['subList']}" begin="1" >
						<tr>
							<td align="right"><fmt:formatNumber value="${ subMap['marketPrice']}" pattern="#,##0.00#" /></td>
							<td align="right"><fmt:formatNumber value="${ subMap['goodsPrice']}" pattern="#,##0.00#" /></td>
							<td align="right">${ subMap['goodsQty']}</td>
							<td align="right"><fmt:formatNumber value="${ subMap['goodsTotal']}" pattern="#,##0.00#" /></td>
							<td>${ subMap['supplierName']}</td>
						</tr>
					</c:forEach>
				</c:if>
		</c:forEach>
	</tbody>
	<tfoot></tfoot>
</table>


<div class="conOperation">
	<c:if test="${fn:length(glist)>0}">
		<button type="button" onclick="confirmResultSupplier.confrimSupplier()"><span>确认成交供应商</span></button>
	</c:if>
	<button type="button" onclick="confirmResultSupplier.cancelSupplier()"><span>放弃成交</span></button>
	<button type="button" name="historyBackBtn"><span>返回</span></button>
</div>
<script type="text/javascript">
var confirmResultSupplier = {};

//供应商轮次报价详情
confirmResultSupplier.turndetail = function(supplierId){
	$.epsDialog({
		width:300,
		height:200,
		id:"toResultByTurnView",
		title:"供应商轮次报价详情",
		url:$("#initPath").val()+"/BuyResultXyghController.do?method=toResultByTurnView&supplierId="+supplierId+"&projectId="+$("#projectId").val()
	})
}

//确定成交供应商
confirmResultSupplier.confrimSupplier = function(){
	var checked = $("input[type=radio]:checked");
	if(checked.length==0){
		alert("请至少选择一个供应商作为成交供应商或放弃成交该项目！");
		return;
	}
	var supplierPrice = "";
	$.each($("input[type=radio]"),function(index,obj){
		if(supplierPrice==""){
				supplierPrice += $(obj).attr("id")+":"+$(obj).parent().find("span[id="+$(obj).attr("id")+"]").html()+":"+$(obj).attr("checked");
		}else{
			if(supplierPrice.indexOf($(obj).attr("id"))>=0){
				if($(obj).attr("checked")==true){
						supplierPrice = supplierPrice.replace($(obj).attr("id")+":"+$(obj).parent().find("span[id="+$(obj).attr("id")+"]").html()+":false",
								$(obj).attr("id")+":"+$(obj).parent().find("span[id="+$(obj).attr("id")+"]").html()+":true");
				}
			}else{
				supplierPrice += ","+$(obj).attr("id")+":"+$(obj).parent().find("span[id="+$(obj).attr("id")+"]").html()+":"+$(obj).attr("checked");
			}
		}
	})
	var param = {"confirmType":"confirm","projectId":$("#projectId").val(),"supplierPrice":supplierPrice};
	if(confirm("确定成交结果？")){
		$.getJSON($("#initPath").val()+"/BuyResultXyghController.do?method=saveBuyResult",param,function(json){
			if(json.success){
				alert("操作成功！");
				$("button[name=historyBackBtn]").click();
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

//切换供应商
confirmResultSupplier.checkRadio = function(e,value){
	$(e).parent().parent().find("td[id=goodsTotal]").html(formatAmount(Number(value),2));
}

$(document).ready(function(){

})
</script>