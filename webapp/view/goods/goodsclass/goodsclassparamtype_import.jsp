<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="goodsClassId"  value="<c:out value="${param.goodsClassId}"/>"/>
<div class="formLayout form2Pa">
	<ul id="goodsClassList"></ul>
	<ul class="hidden" id="goodsClassPatternList">
		<li class="fullLine">
			<span style="padding-left: 150px;"><input type="checkbox" checked="checked" /></span>
			<span></span>
		</li>
	</ul>
	
	<div class="conOperation">
		<button type="button" id="saveGoodsClassParamBtn"><span>导入</span></button>
		<button type="button" id="closeGoodsClassParamBtn"><span>关闭</span></button>
	</div>
</div>

<script>
$(document).ready(function(){
	//显示商品分类
	var goodsClassIds = $("#importGoodsClassIds").val().split(',');
	var goodsClassNames = $("#importGoodsClassNames").val().split(',');
	for(var i=0; i<goodsClassIds.length; i++){
		var cloneDom = $("#goodsClassPatternList").find("li:eq(0)").clone();
		$(cloneDom).find("input").val(goodsClassIds[i]);
		$(cloneDom).find("span:last").text(goodsClassNames[i]);
		$("#goodsClassList").append(cloneDom);
	}
	
	//导入
	$("#saveGoodsClassParamBtn").click(function(){
		var goodsClassIds = "";
		$("#goodsClassList").find("input:checked").each(function(index, domE){
			if(index != 0){ goodsClassIds += ","; }
			goodsClassIds += $(domE).val();
		});

		if(goodsClassIds == ""){alert("至少选择一个商品分类进行参数导入！"); return ;}

		$.getJSON($("#initPath").val()+"/GoodsClassParamTypeController.do?method=saveGoodsClassParamTypeBatch",{"goodsClassId":$("#goodsClassId").val(), "goodsClassIds":goodsClassIds},function(json){
			if(json.success == "true"){
				alert("操作成功！");
				$("#importSuccess").val("1"); //标记导入成功
				$("#closeGoodsClassParamBtn").click();
			}
			if(json.failure) {
				alert(json.result);
				return;
			}
		});
	})
	
	//关闭
	$("#closeGoodsClassParamBtn").click(function(){
		if($("#_dialogID").val() != "")
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		else
			$('.epsDialogClose').trigger('click');
	})
});

</script>