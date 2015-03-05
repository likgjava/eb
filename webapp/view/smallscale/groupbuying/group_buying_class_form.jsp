<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="groupBuyingClassForm" method="post" modelAttribute="groupBuyingClass">
	<input type="hidden" id="groupBuyingClassId" name="objId" value="${groupBuyingClass.objId}" />
	<div class="formLayout form2Pa">
		<ul>
	    	<li class="fullLine">
	            <label>商品分类：</label>
	            <input type="text" id="goodsClassName" class="sysicon siSearch" value="${groupBuyingClass.goodsClass.goodsClassName}" />
	            <input type="hidden" id="goodsClassId" name="goodsClass.objId" class="required" value="${groupBuyingClass.goodsClass.objId}" />
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>团购分类名称：</label>
	            <input type="text" id="name" name="name" class="required" value="${groupBuyingClass.name}" />
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label>显示在首页：</label>
	            <input type="radio" id="isShowIndex1" name="isShowIndex" <c:if test="${groupBuyingClass.isShowIndex}">checked='checked'</c:if> value="1" />是
	            <input type="radio" id="isShowIndex2" name="isShowIndex" <c:if test="${!groupBuyingClass.isShowIndex}">checked='checked'</c:if> value="0" />否
	        </li>
	    </ul>
	</div>
   	<div class="conOperation">
		<button type="button" id="groupBuyingClassSaveBut" class="largeBtn"><span>保存</span></button>
		<button type="button" id="groupBuyingClassReturnBut" class="largeBtn"><span>返回</span></button>
	</div>
</form:form>		

<script>
var GroupBuyingClassForm={};

//保存团购分类
GroupBuyingClassForm.save=function(){
	if(!$("#groupBuyingClassForm").valid()){
		alert("请正确填写表单!");return;
	}
	
    if(window.confirm("确定保存团购分类信息吗?")){
        $("#groupBuyingClassSaveBut").attr("disabled",true);
		var url = $('#initPath').val()+"/GroupBuyingClassController.do?method=saveGroupBuyingClass";

    	$('#groupBuyingClassForm').ajaxSubmit({
    		url:url,
			dataType:'json',
			success:function(json){
    			$("#groupBuyingClassReturnBut").click();
			},
			error:function(msg){
				alert(JSON.stringify(msg));
				$("#groupBuyingClassSaveBut").attr("disabled",false);
			}
		});	
	}
}

$(document).ready(function(){
	//商品分类唯一性验证
	var objId = $('#groupBuyingClassId').val();
	$.validator.addMethod("goodsClassUnique",function(value,element,param){return uniqueHandler("GroupBuyingClass",param,value,objId);},'团购分类中已存在该商品分类');
	$.validator.addMethod("nameUnique",function(value,element,param){return uniqueHandler("GroupBuyingClass",param,value,objId);},'团购分类中已存在该分类名称');
	$("#groupBuyingClassForm").validate({
		rules:{"goodsClass.objId":{goodsClassUnique:"GOODS_CLASS_ID"}},
		rules:{"name":{nameUnique:"name"}}
	});
	
	//保存
	$("#groupBuyingClassSaveBut").click(function(){
		GroupBuyingClassForm.save();
	})
	
	//返回
	$("#groupBuyingClassReturnBut").click(function(){
		$("#conBody").loadPage($('#initPath').val()+"/view/smallscale/groupbuying/group_buying_class_list.jsp");
	});

	//选择商品分类
	$('#goodsClassName').click(function(){
		$.epsDialog({
			id: 'selectGCD',
	        title:'选择商品分类',
	        width: 820,
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&dialogId=selectGCD&IDS=goodsClassId&NAMES=goodsClassName&className=GoodsClass&action=listTop&checkStatus=true',
	        onClose: function(){
		        if($('#name').val() == ""){
		        	$('#name').val($('#goodsClassName').val());
		        }
		        $("#goodsClassId").valid();
	      	}
	    });
	});

})
</script>