<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">


$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val($('#initPath').val()+"/FtlTemplatePropertyController.do")
	
	// 新增模板
	$('#addFtlTemplatePropertyBtn').click(function(){
		$('#conBody').loadPage($("#initPath").val()+"/FtlTemplatePropertyController.do?method=toCreateOrUpdate");
	});
	if($('#ftlTemplatePropertyId').val()!=""){
		$.getJSON($('#initPath').val()+'/FtlTemplatePropertyController.do?method=createOrUpdate',{objId:$('#ftlTemplatePropertyId').val()},function(json){
			jsonObjectToForm('ftlTemplatePropertyForm', json.ftlTemplateProperty);
			//$('#FCKContent_content').html("<h1>郭永荣</h1><hr><table><tr><th>jklj</th><th>jljk</th></tr><tr><td>jklj</td><td>jkljl</td></tr></table>")
		});

	}

	// 返回
	$('#returnFtlTemplatePropertyListBtn').click(function(){
		$('#conBody').loadPage($('#returnUrl').val());
	})
	
	// 保存模板
	$('#saveFtlTemplatePropertyBtn').click(function(){
		if(!$('#ftlTemplatePropertyForm').valid()){alert('请正确填写表单!');return;}
		$('#saveFtlTemplatePropertyBtn').attr("disabled",true);
		FCKeditor_BackValue(); 
		var ftlProperty = formToJsonObject('ftlTemplatePropertyForm');

		if(!$('#ftlTemplatePropertyForm').valid()){
			alert('请正确填写表单!');
			$('#saveFtlTemplatePropertyBtn').attr("disabled",false);
			return false;
		}
		$.getJSON($('#initPath').val()+'/FtlTemplatePropertyController.do?method=save', ftlProperty, function(json){
			if(json.result)alert(json.result);if(json.failure){$('#saveFtlTemplatePropertyBtn').attr("disabled",false);return};
    		if(json.success){
				alert('保存成功!');
				$('#conBody').loadPage($('#returnUrl').val());
        	}
		});
	});

	//监听回车事件
	$('#FCKContent_content').bind("keypress", function(event){
		if (event.keyCode == 13) {								   
			$("#FCKContent_content").focus();
			var sel = document.selection.createRange();
			sel.text="\n";
			return;
		}
	});
	
	//表单验证
	$("#ftlTemplatePropertyForm").validate();	
});

</script>
<div class="formLayout form2Pa">
<input type="hidden" id="ftlTemplatePropertyId" value="${param.objId }" />
<form action="" id="ftlTemplatePropertyForm">

<input type="hidden" name="fileName" id="fileName" />
<input type="hidden" name="objId" />
<ul>
	<li class="fullLine"><label>属性名称 ：</label> <input type="text" class="required" name="propertyTitle" id="propertyTitle" size="60%"/><span class="eleRequired">*</span></li>
	<li><label>属性分类 ：</label> <html:select selectedValue="0" styleClass="required" id="ftlType" name="ftlType" code="ftlTemplateType"><html:option value="">-请选择分类-</html:option></html:select><span class="eleRequired">*</span></li>
	<li><label>排序 ：</label> <input type="text" class="digits" name="sort" id="sort" /><span class="eleRequired"></span></li>
	<li class="formTextarea"><label>属性值：</label>	<textarea id="FCKContent_content" name="propertyValue" cols="60" rows="8" ></textarea></li> 
</ul>
<div class="conOperation">
	<button type="button" id="saveFtlTemplatePropertyBtn"><span>保存</span></button>
	<button type="button" id="returnFtlTemplatePropertyListBtn" tabindex="19"><span>返回</span></button>
</div>
</form>
</div>