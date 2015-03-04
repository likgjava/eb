<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formTips attention">
	<ul><li><spring:message code="pubservice.assignmodifier.assignSupplierForGoods"/></li></ul>
</div>	
<div class="partContainers">
	<div class="formLayout form3Pa">
	<input name="goodsModifierId" id="goodsModifierId" type="hidden" value="${param.objId}"/>
	<input name="goodsClassId" id="goodsClassId" type="hidden" value="${param.goodsClassId}"/>
	<input name="goodsBrandId" id="goodsBrandId" type="hidden" value="${param.goodsBrandId}"/>
	<input name="supplierId" id="supplierId" type="hidden" value="${param.supplierId}"/>
		<ul>
			<li class="framePaCon">
			<h5><span>选择商品品牌</span></h5>
				<select size="10" id="goodsbrand"></select>
			</li>
			<li class="framePaCon">
			<h5><span>选择商品分类(可多选) </span></h5>
				<select size="10" id="goodsclass" multiple="multiple"></select>
			</li>
			<li class="framePaCon3">
			<h5><span>选择供应商</span></h5>
			<input type="text" id="supplierName" value=""/><span><a href="javascript:void(0);" id="querySuppliersBtn">查询</a>&nbsp;<a href="javascript:void(0);" id="cleanSupplierNameBtn">清空</a></span>
				<select size="10" id="suppliers"></select>
			</li>
		</ul>
	    <div class="conOperation">
			<button id="addGoodsBtn" type="button" tabindex="18"><span>提交</span></button>
			<button id="closeGoodsBtn" type="button" tabindex="18"><span>关闭</span></button>
		</div>
  	</div>
</div>

<script>
//加载供应商
function getSupplierToSelect(supplierName){
	supplierName = native2ascii(supplierName);
	$('#suppliers').empty().append("正在加载数据...");
	$('#suppliers').FillOptions($('#initPath').val()+'/OrgInfoController.do?method=getAllSupplierByOrgName&orgName='+supplierName,{textfield:"orgName",isfirstselect:"yes"},function(datas){
		$('#suppliers option[value='+$("#supplierId").val()+']').attr("selected",true);
	});
}
$(document).ready(function(){	
	// 供应商列表
	$('#suppliers').FillOptions($('#initPath').val()+'/OrgInfoController.do?method=getObjectQuery&queryColumns=objId,supplierId,orgName&supplierId=null&supplierId_op=!is&useStatus=01&isOff=1&order=createTime&order_flag=true',{textfield:"orgName",isfirstselect:"yes"},function(datas){
		$('#suppliers option[value='+$("#supplierId").val()+']').attr("selected",true);
		
		// 品目分类品牌联动
		$('#goodsbrand').FillOptions($('#initPath').val()+'/GoodsBrandController.do?method=getAllGoodsBrandByOrgId&objId='+$("#goodsBrandId").val(),{textfield:"brandName",isfirstselect:"yes"},function(datas){
			$('#goodsbrand option[value='+$("#goodsBrandId").val()+']').attr("selected",true);
			$('#goodsbrand').change();
		});
		$('#goodsbrand').CascadingSelect($('#goodsclass'),$('#initPath').val()+'/GoodsClassCategoryController.do?method=getUnSpecifiedGoodsClass&objId='+$("#goodsClassId").val(),{parameter:"goodsBrandId",textfield:"goodsClassName"},function(datas){},function(){
			if($("#goodsBrandId").val() == $('#goodsbrand option:selected').val()) {
				$('#goodsclass option[value='+$("#goodsClassId").val()+']').attr("selected",true);
			}else {
				$('#goodsclass option[value='+$("#goodsClassId").val()+']').remove();
			}
		});
	});

	 $('#goodsbrand option[value='+$("#goodsBrandId").val()+']').attr("selected",true);
	

	// 查询供应商
	$("#querySuppliersBtn").click(function(){
		getSupplierToSelect($('#supplierName').val());
	})
	// 清空查询条件
	$("#cleanSupplierNameBtn").click(function(){
		$('#supplierName').val(""); 
	})
	// 关闭
	$("#closeGoodsBtn").click(function(){
		$('#epsDialogClose').click(); 
	})
	
	// 提交
	$("#addGoodsBtn").click(function(){
		var goodsClassSelectIds = $('#goodsclass option:selected').map(function(){
			  return $(this).val();
		}).get().join(",") ; 
		
		var goodsBrandSelectId = $('#goodsbrand').val();; 
		var supplierSelectId = $('#suppliers').val();
		if(null == goodsBrandSelectId || "" == goodsBrandSelectId){
			alert("请选择商品品牌");
			return;
		}
		if(null == goodsClassSelectIds || "" == goodsClassSelectIds){
			alert("请选择商品分类");
			return;
		}
		if(null == supplierSelectId || "" == supplierSelectId){
			alert("请选择供应商");
			return;
		}
		var jsonObj = {};
		jsonObj.objId = $("#goodsModifierId").val();
		jsonObj.goodsClassIds = goodsClassSelectIds;
		jsonObj.goodsBrandId = goodsBrandSelectId;
		jsonObj.supplierId = supplierSelectId;
		
		$.getJSON($("#initPath").val()+"/GoodsModifierController.do?method=saveGoodsModifier",jsonObj ,function(json){
			 if(json.result)alert(json.result);
		     if(json.failure){return;}
			$('#epsDialogClose').click(); 
		});
	})
})
</script>


