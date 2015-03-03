<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/pubservice/css/goods_list.css"/>

<form id="ClassCategorySelectForm" name="ClassCategorySelectForm" method="post">
<input type="hidden" id="property_1" name="property_1" value="${param.property1}"/>
<input type="hidden" id="property_2" name="property_2" value="${param.property2}"/>
<input type="hidden" id="oldGoodsClassId" value="${param.goodsClassId}"/>

<div class="conSearch">
	<h4><span><spring:message code="globe.query"/></span></h4>
	<ul class="highclassSearch">
		<li>
			<label>商品分类关键字</label>
			<input id="condition" style="width:200px"/>
		</li>
		<li class="operationBtnDiv">
			<button id="SEARCHGOODSCLASS"><span><spring:message code="globe.query"/></span></button>
		</li>
	</ul>
</div>
<div class="partContainers">
	<div class="formLayout form2Pa">
	<h4><span>选择商品类目</span></h4>
		<ul>
			<li class="framePaCon">
			<h5><span>选择商品分类 </span></h5>
				<select size="10" id="goodsclass_select"></select>
			</li>
			<li class="framePaCon">
			<h5><span>选择采购品目</span></h5>
				<select size="10" id="goodscategory_select"></select>
			</li>
		</ul>
	    <div class="conOperation">
			<button id="sure" type="button" tabindex="18"><span>确定</span></button>
		</div>
  	</div>
</div>
</form>

<script>
var ClassCategorySelectForm={};
$(document).ready(function(){	
	//分类
	var option = {parameter:"objId",textfield:"goodsClassName",isfirstselect:"no"};  //默认值:{datatype:"json",textfield:"name",valuefiled:"objId","isfirstselect":"yes"};
	$('#goodsclass_select').FillOptions($('#initPath').val()+'/GoodsClassController.do?method=getObjectQuery&queryColumns=objId,goodsClassName', option ,function(datas){
		//显示原先的商品分类和采购品目
		var oldGoodsClassId = $('#oldGoodsClassId').val();
		if(oldGoodsClassId!=null && oldGoodsClassId!=""){
			$("#goodsclass_select").find("option[value="+oldGoodsClassId+"]").attr("selected", "selected");
			$.getJSON($('#initPath').val()+'/GoodsClassCategoryController.do?method=getAssignedPurcategory&classId='+oldGoodsClassId, function(json){
				$(json.result).each(function(index, e){
					$('#goodscategory_select').empty().append('<option selected=selected value='+e.objId+'>'+e.categoryName+'</option>');
				});
			});
		}
	});
	//品目
	$('#goodsclass_select').CascadingSelect($('#goodscategory_select'),$('#initPath').val()+'/GoodsClassCategoryController.do?method=getAssignedPurcategory',{parameter:"classId",textfield:"categoryName",isfirstselect:"no"},function(datas){});

	//确定
	$('#sure').click(function(){
		if($('#goodscategory_select').val() == null || $('#goodscategory_select').val() == ""){
			alert("请选择品目");
			return;
		}
		if($('#goodsclass_select').val() == null || $('#goodsclass_select').val() == ""){
			alert("请选择商品分类");
			return;
		}
		$('input[id='+$('#property_1').val()+'.objId]').val($('#goodsclass_select').val());
		$('input[id='+$('#property_2').val()+'.objId]').val($('#goodscategory_select').val());
		$('input[id='+$('#property_1').val()+'.name]').val($('#goodsclass_select').getSelectedText());
		$('input[id='+$('#property_2').val()+'.name]').val($('#goodscategory_select').getSelectedText());
		$('input[id='+$('#property_1').val()+'_'+$('#property_2').val()+'.id]').val($('#goodsclass_select').getSelectedText()+","+$('#goodscategory_select').getSelectedText());

		$('.epsDialogClose').trigger('click');
	})

	//搜索商品分类
	$("#SEARCHGOODSCLASS").click(function(){
		var temp = $("#condition").val();
		
		//读取数据
		var url = $('#initPath').val()+'/GoodsClassController.do?method=getObjectQuery&queryColumns=objId,goodsClassName';

		$.getJSON(url,{"goodsClassName":temp,"goodsClassName_op":"like"},function(json){
			$("#goodsclass_select").empty();
			$("#goodscategory_select").empty();
			$(json.result).each(function(i,n){
				$("#goodsclass_select").append('<option value='+n.objId+'>'+n.goodsClassName+'</option>');
			});
		});
		return false;
	})
})
</script>