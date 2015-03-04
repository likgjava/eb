<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>	
<form id="GoodsManageSearchForm">
	<ul >
		<li>
			<label for="goodsClass.goodsclassName">供应商名称：</label>
			<input type="text" id="supplierName" value=""/>
		</li>
		<li class="operationBtnDiv">
			<button type="button" id="querySuppliersBtn"><span>查询</span></button>
		</li>
	</ul>
</form>
</div>

<!-- 评价信息tab页 -->
<div class="formLayout formPa" id="orgListDiv">
	<ul class="condiscusstable" id="modifySupplierListUl">
	</ul>
</div>
<div id="orgListPager" class="fronAjaxPager"></div>   

<input name="goodsModifierId" id="goodsModifierId" type="hidden" value="${param.objId}"/>
<input name="goodsClassId" id="goodsClassId" type="hidden" value="${param.goodsClassId}"/>
<input name="goodsBrandId" id="goodsBrandId" type="hidden" value="${param.goodsBrandId}"/>
<input name="supplierId" id="supplierId" type="hidden" value="${param.supplierId}"/>
<div class="conOperation hidden" id="confirmBtn_top">
		<button id="addGoodsBtn_top" type="button" tabindex="18"><span>确定</span></button>
		<button id="closeGoodsBtn_top" type="button" tabindex="18"><span>关闭</span></button>
</div>
<div class="conOperation" id="confirmBtn_but">
	<button id="addGoodsBtn_but" type="button" tabindex="18"><span>确定</span></button>
	<button id="closeGoodsBtn_but" type="button" tabindex="18"><span>关闭</span></button>
</div>

<script>
// 加载供应商
function getSupplierToSelect(supplierName){

 	var params = {queryColumns:"objId,orgName","specifiesId":$('#supplierId').val()};

 	if(supplierName){
	 	params.orgName = supplierName;
	 	params.orgName_op="like";
 	}

 	params.order="createTime";//一定要加按时间排序
 	
	//加载div分页
	$('#orgListPager').ajaxPager({   
			url:$('#initPath').val()+'/OrgInfoController.do?method=getSpecifiesIdPage',
			rp:15,
			data:params,
			startPage:1, 
			fnCallbackDraw: function(json,pageIndex){
				$("#modifySupplierListUl").empty()
				if(json.rows.length>0){
					$.each(json.rows,function(index,obj){
						var levelHtml = '<li'+(obj.objId==$('#supplierId').val()?' style="background:pink;"':'')+'>'
						+'<span><input name="supplierLi" type="radio" '+(obj.objId==$('#supplierId').val()?'checked="checked"':'')+' value="'+obj.objId+'"/>'+obj.orgName+'</span></li>';
						$("#modifySupplierListUl").append(levelHtml);
					})
				}else{
					$("#modifySupplierListUl").append('<div class="sorry">无数据！</div>');
				}
			}
	});
}
$(document).ready(function(){	
	// 加载供应商
	getSupplierToSelect();
	// 选中供应商
	$('#modifySupplierListUl').find("li").live("click",function(){
		$('#modifySupplierListUl').find("li").css({background:""});
		$(this).find(":radio").attr("checked",true);
		$(this).css({background:"pink"});
	});
	// 查询供应商
	$("#querySuppliersBtn").click(function(){
		$('#modifySupplierListUl').empty().append("正在加载数据...");
		getSupplierToSelect($('#supplierName').val());
	})
	// 清空查询条件
	$("#cleanSupplierNameBtn").click(function(){
		$('#supplierName').val(""); 
	})
	// 关闭窗口
	$("button[id^=closeGoodsBtn_]").click(function(){
		$('#epsDialogClose').click(); 
	})
	$("button[id^=addGoodsBtn_]").click(function(){
		var jsonObj = {};
		jsonObj.objId = $("#goodsModifierId").val();
		jsonObj.goodsClassId = $("#goodsClassId").val();
		jsonObj.goodsBrandId = $("#goodsBrandId").val();
		jsonObj.supplierId = $('#modifySupplierListUl').find(":radio:checked").val();
		if(null == jsonObj.supplierId || "" == jsonObj.supplierId){
			alert("请选择供应商");
			return;
		}
		$.getJSON($("#initPath").val()+"/GoodsModifierController.do?method=updateGoodsModifier",jsonObj ,function(json){
			 if(json.result)alert(json.result);
		     if(json.failure){return;}
			$('#epsDialogClose').click(); 
		});
	})
})
</script>


