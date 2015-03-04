<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var GoodsOptionalFittingForm = {};
//删除
GoodsOptionalFittingForm.remove=function(id){
	if(window.confirm("确定要删除此选配吗")){
		$.getJSON($('#initPath').val()+'/GoodsOptionalFittingController.do?method=remove',{objId:id},function(json){
			if(json.failure) {
				return;
			}
			$('span[id='+id+']').empty();
		});
	}
}
//禁用
GoodsOptionalFittingForm.disable=function(id){
	if(window.confirm("确定要禁用此选配吗")){
		$.getJSON($('#initPath').val()+'/GoodsOptionalFittingController.do?method=disableFitting',{objId:id},function(json){
			if(json.failure) {
				return;
			}
			$('span[id='+id+']').find('a[id='+id+']').addClass('hidden');
			$('span[id='+id+']').find('span').removeClass('hidden');
		});
	}
}
$(document).ready(function() {
	/**重复性验证-应该是某一参数的选配不能重复
	$.validator.addMethod("optionContentUnique",function(value,element,param){return uniqueHandler("GoodsOptionalFitting",param,value,"");},'该选配已存在');
		$("#GoodsOptionalFittingForm").validate({
			rules:{
				optionContent:{optionContentUnique:"optionContent"}
			}
	});	
	*/

	$("#GoodsOptionalFittingForm").validate();
	
	//保存选配
	$('button[id=saveOptionalFittingBtn]').click(function() {
		if(!$('#GoodsOptionalFittingForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($("#initPath").val()+"/GoodsOptionalFittingController.do?method=save",formToJsonObject('GoodsOptionalFittingForm'),function(json){
			if(json.success == "true"){
				//将已添加的选配追加到商品参数后
				var gpvId = "goodsParamValue_" + $("#goodsParamId").val();
				var optionHtml = '';
				optionHtml += '<span id="'+json.goodsOptionalFitting.objId+'">';
				optionHtml += '[选配:<a href="javascript:void(0);" onclick="GoodsModifyParamForm.view(\''+json.goodsOptionalFitting.objId+'\');return false;" >'+json.goodsOptionalFitting.optionContent+'</a>&nbsp;';

				if($('#disableFitting').val()=="1") {
					optionHtml += '<a  id="'+json.goodsOptionalFitting.objId+'" href="javascript:void(0);" onclick="GoodsOptionalFittingForm.disable(\''+json.goodsOptionalFitting.objId+'\');return false;">禁用</a><span class="hidden" name="disabledSpan"><font color="gray">已禁用</font></span>]</span>';
				}else {
					optionHtml += '<a  id="'+json.goodsOptionalFitting.objId+'" href="javascript:void(0);" onclick="GoodsOptionalFittingForm.remove(\''+json.goodsOptionalFitting.objId+'\');return false;">删除</a>]</span>';
				}
				$("tr td[id="+gpvId+"]").append(optionHtml);
				$('button[id=closeBtn]').click();
			}
			if(json.failure) {
				return;
			}
		});
	})
	//关闭
	$('button[id=closeBtn]').click(function() {
		if($("#_dialogID").val() != "")
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		else
			$('.epsDialogClose').trigger('click');
	})
})
</script>
<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>
<input type="hidden" name="disableFitting" id="disableFitting" value="${param.disableFitting}"/>
<form:form id="GoodsOptionalFittingForm" method="post"
	modelAttribute="goodsOptionalFitting">
	<div class="formLayout form2Pa">
	<input type="hidden" name="goodsParam.objId" id="goodsParamId" value="${goodsParamId}" />
	<ul>
		<li class="fullLine"><label>选配内容：</label> <input
			class="input_medium required" id="optionContent" name="optionContent"
			type="text" size="40" maxlength="50"/> <span class="eleRequired">*</span></li>
		<li class="formTextarea"><label>选配规格型号描述：</label> 
			<textarea name="specification" id="specification" class="required" maxlength="50"></textarea>
			<span class="eleRequired">*</span></li>
	</ul>
	</div>
	<div class="conOperation">
	<button type="button" id="saveOptionalFittingBtn"><span><spring:message
		code="globe.save" /></span></button>
	<button type="button" id="closeBtn"><span>关闭</span></button>
	</div>
</form:form>

