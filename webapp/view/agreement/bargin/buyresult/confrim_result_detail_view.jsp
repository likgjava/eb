<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" name="dealSupplier" value="${param.dealSupplier}"/>
<input type="hidden" name="lostSupplier" value="${param.lostSupplier}"/>
<input type="hidden" name="isSelectMin" value="${param.isSelectMin}"/>

<c:set var ="detailIds" value=""/>
<!-- 标志位 -->
<c:set var="supplierId" value=""/>
<c:set var="cellTotal" value="0"/>
<c:set var="saveTotal" value="0"/>

<table class="tableList" style="width:100%;">
<c:forEach var="biddingRecordObject" items="${biddingRecordObjectList}" varStatus="status">
	<c:set var ="detailIds" value="${detailIds}${detailIds==''?'':','}${biddingRecordObject[9]}"/>
	<c:if test="${supplierId!=biddingRecordObject[0]}">
		<tr>
			<th style="text-align:left;">${biddingRecordObject[4]}</th>
			<c:set var="supplierId" value="${biddingRecordObject[0]}"/>
			<th>金额</th>
			<th>节省金额</th>
		</tr>
	</c:if>
	<tr>
		<td>${biddingRecordObject[5]}</td>
		<td align="center"><fmt:formatNumber value="${biddingRecordObject[2]}" pattern="#,##0.00#"/>元</td>
		<td align="center"><span class="save_total"><fmt:formatNumber value="${biddingRecordObject[10]-biddingRecordObject[2]}" pattern="#,##0.00#"/></span>元</td>
	</tr>
	
	<c:set var="cellTotal" value="${cellTotal+biddingRecordObject[2]}"/>
	<c:set var="saveTotal" value="${saveTotal+(biddingRecordObject[10]-biddingRecordObject[2])}"/>
	
	<c:if test="${ (status.index+1) ==fn:length(biddingRecordObjectList) || biddingRecordObject[0]!=biddingRecordObjectList[status.index+1][0]}">
		<tr>
			<td align="center"><font color="red"><strong>合计</strong></font></td>
			<td align="center"><font color="red"><strong><fmt:formatNumber value="${cellTotal}" pattern="#,##0.00#"/>元</strong></font></td>
			<td align="center"><font color="red"><strong><fmt:formatNumber value="${saveTotal}" pattern="#,##0.00#"/>元</strong></font></td>
			
			<c:set var="cellTotal" value="0"/>
			<c:set var="saveTotal" value="0"/>
		</tr>
	</c:if>
</c:forEach>
</table>

<br/>
<div class="formLayout formPa">
	<ul>
		<li class="formTextarea">
			<label>备注<c:if test="${param.isSelectMin=='false'}">（不选择推荐的供应商的原因）</c:if>：</label>
			<textarea name="opinion"></textarea>
		</li>
	</ul>
</div>

<input type="hidden" name="detailIds" value="${detailIds}">
	
<div class="conOperation">
	<button id="confirmResultBtn" onclick="project_detail_view.confirmResult();"><span>确定</span></button>
	<button onclick="$('.epsDialogClose').trigger('click');"><span>关闭</span></button>
</div>
	
<script type="text/javascript">
confirm_result_detail = {};
//确定结果
project_detail_view.confirmResult = function(){

	if( $("input[name=isSelectMin]").val()=='false' && !$("textarea[name=opinion]").val() ){
		alert("您没有选择推荐的供应商,请填写理由！");
		return; 
	}

	//灰掉按钮
	$("#operationDiv button").attr("disabled",true);
	$.getJSON($("#initPath").val()+"/BuyResultXyghController.do?method=saveBuyResultAndResultItem",
		{
			"dealSupplier":$("input[name=dealSupplier]").val(),
			"lostSupplier":$("input[name=lostSupplier]").val(),
			"detailIds":$("input[name=detailIds]").val(),
			"projectId":$("#projectId").val(),
			"opinion":$("textarea[name=opinion]").val(),
			"confirmType":"confirm"
		}
	,function(json){
		if(json.success){
			alert("操作成功！");
			project_detail_view.success();
		}else{
			//释放按钮
			$("#operationDiv button").attr("disabled",false);
		}
	})
}

//确认成功后的操作
project_detail_view.success = function(){
	$("#operationDiv").html(null);
	$("input[name=confirmResult]").remove();
	//$("button[name=toConfirmResultBtn]").remove();
	$.each( $("input[name=dealSupplier]").val().split(",") , function(index,obj){
		$("a[name=supplier"+obj+"]").parent().parent().addClass("stamp");
		$("a[name=supplier"+obj+"]").before('<input type="radio" checked="checked" value="'+obj+'" name="chckOrder">');
	})
	//只有采购人才能操作生成订单authorize ifAnyGranted="createOrder"才会有canCreateOrder值
	if($("input[name=canCreateOrder]").val()){
	
		$("button[name=toConfirmResultBtn]").replaceWith('<button type="button" name="toCraeteOrderBtn" class="base_btns7" onclick="project_detail_view.order()"><span>生成订单</span></button>');
		$("button[name=toCraeteOrderBtn]" ).qtip({
			        content: {
			        	text:"点击此类标记处,生成订单",
			        	title:{
		        			text:"",
					        button:"关闭"
		        		}
			    	}, // Set the tooltip content to the current corner
			        position: {
			           corner: {
			              tooltip: 'bottomRight', // Use the corner...
			              target: 'topLeft' // ...and opposite corner
			           }
			        },
			        show: {
			           when: false, // Don't specify a show event
			           ready: true // Show the tooltip when ready
			        },
			        hide: false, // Don't specify a hide event
			        style: {
			           border: {
			              width: 2,
			              radius: 4
			           },
			           padding: 10, 
			           textAlign: 'center',
			           tip: true, // Give it a speech bubble tip with automatic corner detection
			           name: 'cream' // Style it according to the preset 'cream' style
			        }
		});
		
	}else{
		$("button[name=toConfirmResultBtn]").remove();
	}
	
	$('.epsDialogClose').trigger('click');
}

$(document).ready(function(){
	project_detail_view.caculator('minPriceRecordTableConfirm');
})
</script>
	
