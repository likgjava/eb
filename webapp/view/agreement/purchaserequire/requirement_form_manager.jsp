<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="requirementForm" name="requirementForm">
	<input type="hidden" id="objId" name="objId" value="${requirement.objId}"/>
	<input type="hidden" id="auditStatus" name="auditStatus" value="02"/>
	<input type="hidden" id="pubOrg.objId" name="pubOrg.objId" value="${requirement.pubOrg.objId}"/>
	<input type="hidden" id="submitRelaId" name="picture" value="${requirement.picture}"/>
		
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
		 		<input id="category.name" name="category.categoryName" value="${requirement.category.categoryName}" readonly="readonly" class="sysicon siSearch required" onclick="requirement_form_manager.selectCategory();">
		 	</li>
		 	<li>
		 		<label>采购数量：</label>
				<input id="purchaseQty" name="purchaseQty" maxlength="80" value="${requirement.purchaseQty}" class="digits required" type="text">
		 	</li>
		 	<li>
		 		<label>单个预算（元）：</label>
				<input id="purchaseBudget" name="purchaseBudget" value="${requirement.purchaseBudget}" class="money required" maxlength="80" type="text" >
		 	</li>
		 	<li>
		 		<label>供货区域：</label>
				<input type="hidden" name="districtId" id="district.objId" value="${requirement.districtId}">
				<input type="text" name="districtNames" id="district.name" value="${requirement.districtNames}" readonly="readonly" onclick="requirement_form_manager.selectDistrict();" class="sysicon siSearch required">
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
		 	<li class="fullLine">
		 		<label>是否显示联系方式：</label>
				<html:select id="isAnonymous" name="isAnonymous" selectedIndex="${requirement.isAnonymous?0:1}">
					<html:option value="1">是</html:option>
					<html:option value="0">否</html:option>
				</html:select>		 		
		 	</li>
		</ul>
	</div>
    		
	<div>
		<h4 class="title" style="clear:both;"><span>具体描述</span></h4>
		<div id="htmlEditor"></div>
		<textarea id="discription" name="discription" class="hidden" maxlength="2000">${requirement.discription}</textarea>
	</div>
	<div class="formLayout form2Pa">
		<h4><span>更多图片</span></h4>
		<div class="bd uploadFile2" id="picture"></div>
	</div>
</form>

	
<div class="conOperation ">
	<button type="button" class="largeBtn" id="save" onclick="requirement_form_manager.saveRequirement('<c:if test="${requirement.pubStatus==null}">00</c:if><c:if test="${requirement.pubStatus!=null}">${requirement.pubStatus}</c:if>');"><span>保存</span></button>
	<c:if test="${requirement.pubStatus=='00'}">
		<button type="button" class="largeBtn" id="publish" onclick="requirement_form_manager.saveRequirement('01');"><span>发布</span></button>
	</c:if>
	<button type="button" class="largeBtn" name="historyBackBtn"><span>返回</span></button>
</div> 

<script type="text/javascript">
var requirement_form_manager = {};
var htmlEditor;

requirement_form_manager.close = function(){
	if($("#dailogId").val()){
		$("#"+$("#dailogId").val()).find('.epsDialogClose').trigger('click');
	}else{
		$('.epsDialogClose').trigger('click');
	}
}

//选择使用区域
requirement_form_manager.selectDistrict = function(){
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
requirement_form_manager.selectCategory= function(){
    $.epsDialog({
    	id:'categoryDiv',
        title:'选择品目',
        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=category&className=PurCategory&action=listTop&isOpen=1&dialogId=categoryDiv'
    }); 
}

//保存或发布
requirement_form_manager.saveRequirement = function(type){
	$("#discription").val(htmlEditor.getValue());
	if(!$('#requirementForm').valid()){alert('请正确填写表单!');return;}
	var requirement = formToJsonObject("requirementForm");
	if(requirement.picture == "picture") requirement.picture = "";
	requirement.pubStatus = type;
	requirement.discription = $("#discription").val();
	$.getJSON($("#initPath").val()+"/RequirementController.do?method=saveRequirement",requirement,function(json){
		if(json.success){
			$("button[name=historyBackBtn]").click();
		}
	})
}

$(document).ready(function(){
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
	
	//验证
	$("#requirementForm").validate();
	
	$("#endTime").epsDatepicker({});  //增加结束时间的规则

	//图片上传控件
	$('#picture').loadPage($('#initPath').val()+'/AttachmentController.do?method=toUploadAjaxImg',{
			defineSelf:'picture',//存放关联id的属性名
			maxSize:'1024',
			fileType: 'gif|jpeg|jpg|bmp|png',
			quantity:'3',//附件最大数
			attachRelaId:$("input[id=submitRelaId]").val(),
			isView:'0',//是否只是显示图片不删除和上传
			maxWidth:80,//建议长
			maxHeight:80,//建议宽
			nopicPath:'AttachmentController.do?method=showImg'//提示图片  可以是AttachmentController.do?method=showImg
	});
	

})
</script>
