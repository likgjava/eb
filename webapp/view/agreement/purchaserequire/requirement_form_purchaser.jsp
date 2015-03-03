<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="requirementForm" name="requirementForm" method="post"  method="post" enctype="multipart/form-data">
	<input type="hidden" id="objId" name="objId" value="${requirement.objId}"/>
	<input id="picture" type="hidden" name="picture" value="${requirement.picture}"/>
	<input id="pubStatus" name="pubStatus" value="00" type="hidden"><!-- 默认未发布-->
	<div class="formLayout form2Pa">
		<h4 class="title"><span>采购需求信息</span><span class="eleRequired"></span></h4>
		<ul>
			<li class="fullLine">
		 		<label>需求标题：</label>
		 		<input id="title" name="title" value="${requirement.title}" class="required" style="width:300px;">
		 	</li>
		 	<li>
		 		<label>采购品目：</label>
		 		
		 		<input id="category.objId" name="category.objId" value="<c:if test="${requirement.category!=null}">${requirement.category.objId}</c:if>" type="hidden">
		 		<input id="category.name" name="category.categoryName" value="${requirement.category.categoryName}" readonly="readonly" class="sysicon siSearch required" onclick="requirement_form_purchaser.selectCategory();">
		 	</li>
		 	<li>
		 		<label>采购数量：</label>
				<input id="purchaseQty" name="purchaseQty" maxlength="80" value="${requirement.purchaseQty}" class="digits required" type="text">
		 	</li>
		 	<li>
		 		<label>采购预算（元）：</label>
				<input id="purchaseBudget" name="purchaseBudget" value="${requirement.purchaseBudget}" class="money required" maxlength="80" type="text" >
		 	</li>
		 	<li>
		 		<label>供货区域：</label>
				<input type="hidden" name="districtId" id="district.objId" value="${requirement.districtId}">
				<input type="text" name="districtNames" id="district.name" value="${requirement.districtNames}" readonly="readonly" onclick="requirement_form_purchaser.selectDistrict();" class="sysicon siSearch required">
		 	</li>
		 	<li>
		 		<label>结束时间：</label>
		 		<input class="required" name="endTime" id="endTime"  value='<fmt:formatDate value="${requirement.endTime}" pattern="yyyy-MM-dd"/>'/>
		 	</li>
		 	<li>
		 		<label>电子邮箱：</label>
		 		<input id="email" name="email" type="text" value="${requirement.email}"  class="email required">
		 	</li>
		 	<li>
		 		<label>联系电话：</label>
		 		<input id="linkTel" name="linkTel" type="text" value="${requirement.linkTel}" class="required number">
		 	</li>		 	
		 	<li>
		 		<label>联系人：</label>
		 		<input id="linkMen" name="linkMen" type="text" value="${requirement.linkMen}" class="required">
		 	</li>
		</ul>
	</div>
    		
	<div>
		<h4 class="title" style="clear:both;"><span>具体描述</span></h4>
		<div id="htmlEditor"></div>
		<textarea id="discription" name="discription" class="hidden" maxlength="2000">${requirement.discription}</textarea>
	</div>
</form>

<div class="formLayout form2Pa">
	<h4><span>更多图片</span></h4>
	<div class="bd uploadFile2" id="additionPicture"></div>
</div>
	
<div class="conOperation">
	<button type="button" class="largeBtn" id="save" onclick="requirement_form_purchaser.saveRequirement('<c:if test="${requirement.auditStatus==null}">00</c:if><c:if test="${requirement.auditStatus!=null}">${requirement.auditStatus}</c:if>');"><span>保存</span></button>
	<button type="button" class="largeBtn" id="publish" onclick="requirement_form_purchaser.saveRequirement('01');"><span>提交</span></button>
	<button type="button" class="largeBtn" name="historyBackBtn"><span>返回</span></button>
</div>

<script type="text/javascript">
var requirement_form_purchaser = {};
var htmlEditor;

//选择使用区域
requirement_form_purchaser.selectDistrict = function(){
    $.epsDialog({
    	id:'districtDiv',
        title:'选择使用区域',
        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=district&className=District&action=listTop&isOpen=1&childNodeOnly=true&andAllParent=true&dialogId=districtDiv'
        ,onClose:function(){
        	if( $("input[name=districtId]").val() ){
        		var ids = $("input[name=districtId]").val().split(",");
        		$("input[name=districtId]").val( ids[ids.length-1] );
        	}
        }
    }); 
}

//选择品目
requirement_form_purchaser.selectCategory= function(){
    $.epsDialog({
    	id:'categoryDiv',
        title:'选择品目',
        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=category&className=PurCategory&action=listTop&isOpen=1&dialogId=categoryDiv'
    }); 
}

//保存或提交
requirement_form_purchaser.saveRequirement = function(type){

	if(type=='01'&&!confirm("确认提交？")){
		return;
	}
	$("#discription").val(htmlEditor.getValue());
	if(!$('#requirementForm').valid()){alert('请正确填写表单!');return;}
	var requirement = formToJsonObject("requirementForm");
	requirement.auditStatus = type;
	if(requirement.picture == "picture") requirement.picture = "";
	$.getJSON($("#initPath").val()+"/RequirementController.do?method=saveRequirement",requirement,function(json){
		if(json.success){
			$("button[name=historyBackBtn]").click();
		}
	})
}

$(document).ready(function(){
	
	//验证
	$("#requirementForm").validate();
	
	$("#endTime").epsDatepicker({});  //增加结束时间的规则

	//加载ExtJs的HTML编辑器
	htmlEditor = new Ext.form.HtmlEditor({
		height: 240,
		anchor: '100%'
	});
	new Ext.panel.Panel({
	    renderTo: 'htmlEditor',
	    layout: 'anchor',
	    border : false,
	    items: [htmlEditor]
	});
	htmlEditor.setValue($("#discription").val()+" ");

	//图片上传控件
	$('#additionPicture').loadPage($('#initPath').val()+'/AttachmentController.do?method=toUploadAjaxImg',{
			defineSelf:'picture',//存放关联id的属性名
			maxSize:'1024',
			fileType: 'gif|jpeg|jpg|bmp|png',
			quantity:'3',//附件最大数
			attachRelaId:$("#picture").val(),
			isView:'0',//是否只是显示图片不删除和上传
			maxWidth:80,//建议长
			maxHeight:80,//建议宽
			nopicPath:'AttachmentController.do?method=showImg'//提示图片  可以是AttachmentController.do?method=showImg
	});
})
</script>
