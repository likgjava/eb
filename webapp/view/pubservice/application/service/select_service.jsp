<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="_selectServiceDialogID"  value="<c:out value="${param.dialogId}"/>"/>
<div class="conSearch">
	<form id="selectServiceSearchForm" >
		<input type="hidden" id="Hname" value="${param.Hname}"/>
		<input type="hidden" id="Hid" value="${param.Hid}"/>
		<input type="hidden" id="Hprice" value="${param.Hprice}"/>
		<input type="hidden" id="HunitCN" value="${param.HunitCN}"/>
		<ul>
	 		<li>
	     		<label>服务名称：</label>
	     		<input type="text" name="serviceName" id="serviceName">
	     		<input type="hidden" name="serviceName_op" value="like">
	 		</li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span>查询</span></button>
 	 		</li>
   		</ul>
	</form>
</div>

<table class="frontTableList" id="serviceBaseList">
	<thead>
		<tr>
			<th class="center">服务图片</th>
			<th class="omission" omiLength="20">服务名称</th>
			<th class="r">价格</th>
			<th class="hidden">金额单位</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<div class="conOperation">
	<button id="selectServiceClose" type="button"  class="largeBtn" ><span><spring:message code="globe.close"/></span></button>
	<button id="selectServiceClear" type="button"  class="largeBtn" ><span>清除</span></button>
</div>

<script>
var SelectServiceList={};

$(document).ready(function(){
	SelectServiceList.oTable = $('#serviceBaseList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'servicePic,serviceName,servicePrice,serviceUnit',
		'alias' : 'servicePic,serviceName,servicePrice,serviceUnitCN',
		'hiddenColumns':'',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			SelectServiceList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=servicePic]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["servicePic"]+'" CURSOR="hand" width="60px" height="60px"/>');
			$(nRow).find("td[name=servicePrice]").empty().append(formatAmount(aData.servicePrice,2)+"元/"+aData.serviceUnitCN);
			
			$(nRow).append("<td class='operation'><a name='selectService' href='javascript:void(0);'>选择</a></td>").find('a[name=selectService]').click(function(){
				$("#"+$("#Hname").val()).val(aData.serviceName);
				$("#"+$("#Hid").val()).val(aData.objId);
				if($("#Hprice").val()!=null && $("#Hprice").val()!=""){
					$("#"+$("#Hprice").val()).val(aData.servicePrice);
				}
				if($("#HunitCN").val()!=null && $("#HunitCN").val()!=""){
					$("#"+$("#HunitCN").val()).val(aData.serviceUnitCN);
				}
				$("#selectServiceClose").click();
			});
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/ServiceBaseController.do?method=list",
		"params":{"objId":$("#objId").val(),"objId_op":"!=", "useStatus":"02", "useStatus_op":"!="},
		'searchZone':'selectServiceSearchForm'
	});
	
	//查询
	$("#query").click(function(){
		SelectServiceList.oTable.fnDraw();
	})
	
	//关闭
	$("#selectServiceClose").click(function(){
		if($("#_selectServiceDialogID").val() != ""){
			$(document.getElementById($("#_selectServiceDialogID").val())).find('.epsDialogClose').trigger('click');
		}else{
			$('.epsDialogClose').trigger('click');
		}
	})
	
	//清空
	$("#selectServiceClear").click(function(){
		$("#"+$("#Hname").val()).val('');
		$("#"+$("#Hid").val()).val('');
		$("#selectServiceClose").click();
	})
});
</script>
