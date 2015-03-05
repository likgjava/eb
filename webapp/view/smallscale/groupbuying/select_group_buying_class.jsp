<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="HName" value="${param.name}" />
<input type="hidden" id="HGoodsClassId" value="${param.goodsClassId}" />

<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="selectGGCSearchForm">
		<ul >
			<li>
				<label>团购分类名称：</label>
				<input type="text" name="name" id="name" />
				<input type="hidden" name="name_op" value="like" />
			</li>
			<li>
				<label>商品分类名称：</label>
				<input type="text" name="goodsClass.goodsClassName" />
				<input type="hidden" name="goodsClass.goodsClassName_op" value="like" />
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<table class="frontTableList" id="selectGroupBuyingClassList">
	<thead>
		<tr>
			<th class="omission" omiLength="10">团购分类名称</th>
			<th class="omission" omiLength="10">商品分类名称</th>
			<th class="center">显示在首页</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<div class="conOperation">	
	<button  id="close" type="button"><span>关闭</span></button>
</div>

<script>
var SelectGroupBuyingClass = {};
SelectGroupBuyingClass.getOperatorStr=function(aData) {
	var operatorHtml = "";
	operatorHtml += '<td class="operation">';
	operatorHtml += '<a title="选择" href="javascript:void(0);" onclick="SelectGroupBuyingClass.select(\''+aData.objId+'\',\''+aData.name+'\',\''+aData["goodsClass.objId"]+'\');return false;">选择</a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//选择团购分类，回填数据
SelectGroupBuyingClass.select=function(objId,name,goodsClassId) {
	if($("#HName").val() != ""){
		$("#"+$("#HName").val()).val(name);
	}
	if($("#HGoodsClassId").val() != ""){
		$("#"+$("#HGoodsClassId").val()).val(goodsClassId);
	}
	$("#close").click();
}

$(document).ready(function(){
	//加载团购分类列表
	SelectGroupBuyingClass.oTable = $('#selectGroupBuyingClassList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'name,goodsClass.goodsClassName,isShowIndex',
		'hiddenColumns':'goodsClass.objId',
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=isShowIndex]").html(aData.isShowIndex=='true' ? '是' : '否');
			//添加操作按钮
			$(nRow).append(SelectGroupBuyingClass.getOperatorStr(aData))
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/GroupBuyingClassController.do?method=list",
		"params":{},
		'searchZone':'selectGGCSearchForm'
	});

	//查询
	$("#query").click(function() {
		SelectGroupBuyingClass.oTable.fnDraw();
	})

	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
})
</script>
