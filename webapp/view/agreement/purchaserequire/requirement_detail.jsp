<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="requirementForm" method="post" modelAttribute="requirement">
	<form:hidden path="objId"/>
		
	<div class="formLayout form2Pa">
		<h4 class="title"><span>采购需求信息</span><span class="eleRequired"></span></h4>
		<ul>
			<li class="fullLine">
		 		<label>需求标题：</label>
		 		<span>${requirement.title}</span>
		 	</li>
		 	<li>
		 		<label>采购品目：</label>
		 		<span>${requirement.category.categoryName}</span>
		 	</li>
		 	<li>
		 		<label>发布机构：</label>
		 		<span>${requirement.pubOrg.orgName}</span>
		 	</li>
		 	<li>
		 		<label>采购数量：</label>
		 		<span>${requirement.purchaseQty}</span>
		 	</li>
		 	<li>
		 		<label>采购预算：</label>
		 		<fmt:formatNumber value="${requirement.purchaseBudget}" pattern="#,##0.00#" /> 元
		 	</li>
		 	<li>
		 		<label>供货区域：</label>
		 		<span>${requirement.districtNames}</span>
		 	</li>
		 	<li>
		 		<label>结束时间：</label>
		 		<fmt:formatDate value="${requirement.endTime}" pattern="yyyy-MM-dd"/>
		 	</li>
		 	<li>
		 		<label>电子邮箱：</label>
		 		<span>${requirement.email }</span>
		 	</li>
		 	<li>
		 		<label>联系电话：</label>
		 		<span>${requirement.linkTel }</span>
		 	</li>		 	
		 	<li>
		 		<label>联系人：</label>
		 		<span>${requirement.linkMen}</span>
		 	</li>
		 	<li>
		 		<label>发布时间：</label>
		 		<fmt:formatDate value="${requirement.pubTime}" pattern="yyyy-MM-dd"/>
		 	</li>
		 	<li class="fullLine">
		 		<label>创建人：</label>
		 		<span>${requirement.createUser.usName}</span>
		 	</li>
		</ul>
	</div>
    		
	<c:if test="${requirement.discription!=null && requirement.discription != ''}">
	<div class="formLayout form2Pa">
		<h4 class="title" style="clear:both;"><span>具体描述</span></h4>
		<div>${requirement.discription}</div>
	</div>
	</c:if>
	
	<div class="formLayout form2Pa">
	<h4><span>更多图片</span></h4>
		<input type="hidden" name="picture" id="picture" value="${requirement.picture }">
		<div class="uploadFile2" id="additionPicture"></div>
	</div>
	
	<div class="conOperation ">
		<button type="button" class="largeBtn" id="close_gview" onclick="requirement_audit.close();"><span>关闭</span></button>
	</div> 
</form:form>

<script type="text/javascript">
var requirement_audit = {};

requirement_audit.close = function(){
	if($("#dailogId").val()){
		$("#"+$("#dailogId").val()).find('.epsDialogClose').trigger('click');
	}else{
		$('.epsDialogClose').trigger('click');
	}
}

$(document).ready(function(){
	//更多图片
	$('#additionPicture').loadPage($('#initPath').val()+'/AttachmentController.do?method=toUploadAjaxImg',
			{
				defineSelf:'additionPicture',//存放关联id的属性名
				maxSize:'1024',
				fileType: 'gif|jpeg|jpg|bmp|png',
				quantity:'3',//附件最大数
				attachRelaId:$("input[name=picture]").val(),
				isView:'1'//是否只是显示图片不删除和上传
				,maxWidth:80,//建议长
				maxHeight:80,//建议宽
				nopicPath:'AttachmentController.do?method=showImg'//提示图片  可以是AttachmentController.do?method=showImg
			}
	);
})
</script>
