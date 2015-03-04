<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">
var hottagsFrom = {};

//选择标签对象
hottagsFrom.selectObject = function(){
	if($("#tagsType").val()=="04"){
	    $.epsDialog({
	    	id:'districtDiv',
	        title:'请选择区域',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=tagsobj&className=District&action=listTop&isOpen=1&dialogId=districtDiv'
	    }); 
	}else if($("#tagsType").val()=="05"){
		$.epsDialog({
	    	id:'goodsClassDiv',
	        title:'请选择商品分类',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=tagsobj&className=GoodsClass&action=listTop&isOpen=1&dialogId=goodsClassDiv'
	    });
	}else if($("#tagsType").val()=="06"){
		$.epsDialog({
	    	id:'purCategoryDiv',
	        title:'请选择采购品目',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=tagsobj&className=PurCategory&action=listTop&isOpen=1&dialogId=purCategoryDiv'
	    });
	}
}

//保存
hottagsFrom.saveTags = function(){
	if(!$('#HotTagsFrom').valid()){return;}
	var hottags = formToJsonObject('HotTagsFrom');
	$.getJSON($("#initPath").val()+"/HotTagsController.do?method=save",hottags,function(json){
		if(json.success){
			alert("操作成功！");
			hottagsFrom.returnBack();
		}else{
			alert("操作失败！");
		}
	})
}

//返回
hottagsFrom.returnBack = function(){
	$("#conBody").loadPage($("#initPath").val()+"/HotTagsController.do");
}

$(document).ready(function(){
	//回填select
	$("#tagsType").val($("#tagsTypeValue").val());
})

</script>

<form:form id="HotTagsFrom" name="HotTagsFrom" method="post" modelAttribute="hotTags">
	<div id = "addHotTags" class="formLayout form2Pa">
		<h4><span>标签信息</span></h4>
		<ul>
			<li class="fullLine">
				<label>标签类型：</label>
				<input type="hidden" id="tagsTypeValue" value="${hotTags.tagsType}" >
				<html:select selectedValue="0" id="tagsType" name="tagsType" code="tagstype" >
	    		</html:select>
			</li>
			<li class="fullLine">
				<label>标签名称：</label>
				<form:hidden path="tagsId" id="tagsobj.objId"/>
				<form:input path="tagsName" id="tagsobj.name" cssClass="required" onclick="hottagsFrom.selectObject()"/>
				<span class="eleRequired">*</span>
			</li>	
			<li class="formTextarea">
				<label>标签描述：</label>
				<form:textarea path="tagsDscr"/>
			</li>
		</ul>
	</div>
	<div class="conOperation center">
	 	<button type="button" onclick="hottagsFrom.saveTags()" ><span><spring:message code="globe.save"/></span></button>
	 	<button type="button" onclick="hottagsFrom.returnBack()"><span><spring:message code="globe.return"/></span></button>
	</div>
</form:form>