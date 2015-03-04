<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
	<div class="formLayout form2Pa">  
			<h5><span>投标单位报名记录</span></h5>
				<ul>
			<form id="signUprecordDetailForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="maxSize" value="10240"/>
			<input type="hidden" name="objId" id="objId" value="${signUprecord.objId}"/>
			<input type="hidden" name="fromDiv" id="fromDiv" value="<c:out value="${param.fromDiv}"/>"/>
			<input type="hidden" name="project.objId" id="project.objId" value="${signUprecord.project.objId}"/>
				<li>
					<label  class="short">投标单位名称：</label>
					<span>${signUprecord.supplier.orgName}</span>
				</li>
				<li>
					<label  class="short">项目(<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>)名称：</label>
					<span>${signUprecord.project.projName}</span>
				</li>
				<li>
					<label class="short">报名时间：</label>
					<span>${signUprecord.applyDate}</span>
				</li>
					<li>
					<label  class="short">联系人：</label>
					<input type="text" name="linker" id="linker" class="required" value="${signUprecord.linker}" />
					<span class="eleRequired">*</span>
					</li>
					
					<li>
						<label  class="short">身份证号：</label>
						<input type="text" name="idCard" id="idCard" class="required cnIdCard" value="${signUprecord.idCard}" />
						<span class="eleRequired">*</span> 	  
					</li>
					<li>
						<label  class="short">联系电话：</label>
						<input type="text" name="linkerTel" id="linkerTel" class="required" value="${signUprecord.linkerTel}" />
						<span class="eleRequired">*</span> 	  
					</li>
					<li>
						<label  class="short">联系地址：</label>
						<input type="text" name="address" id="address" value="${signUprecord.address}" />
					</li>
					<li>
						<label  class="short">邮编：</label>
						<input type="text" name="zipCode" id="zipCode" class="required cnZipCode"  value="${signUprecord.zipCode}" />
						<span class="eleRequired">*</span> 	  
					</li>
					<li>
					<label  class="short">备注：</label>
					<input type="text" name="memo" id="memo" value="${signUprecord.memo}" />
					</li>
					<li>
					<label  class="short">状态：</label>
					<span>${signUprecord.auditStatusCN}</span>
						<!-- 隐藏数据，提前表单时默认审核状态为待审核 -->
						<input type="hidden" name="auditStatus" id="auditStatusId" value="${signUprecord.auditStatus}"/>
						<!-- 隐藏数据，提前表单时默认报名状态为已报名 -->
						<input type="hidden" name="applyStatus" id="applyStatus" value="${signUprecord.applyStatus}"/>
					</li>
					
					<div id="signUpRespone" style="width: auto;margin-top: 2px;" >
		<table class="tableList" style="width:100%;">
	            <caption>报名指标响应</caption>
	              <tr>
	                <th style="text-align: center;">指标名称</th>
					<th style="text-align: center;">说明          </th>
					<th style="text-align: center;">报名响应</th>
					<th style="text-align: center;">响应附件</th>
	              </tr>
	            <tbody >
	            <c:set var="i" value="0"> </c:set>
	            	<c:forEach items="${signUpResponeList}" var="signUpRespone">
	              <tr>
	               <td class="center">
	                <input type="hidden" id="i">
	                <input type="hidden" id="signUpResponeId${i}" name="signUpResponeId${i}" value="${signUpRespone.objId}"/>
	                <input type="hidden" id="signUpCondFactorId${i}" name="signUpCondFactorId${i}" value="${signUpRespone.signUpCondFactor.objId}"/>
	               <span id="factorName${i}">${signUpRespone.signUpCondFactor.factorName }</span></td>
	                <td class="center"><textarea name='remark${i}' id='remark${i}' readonly="readonly">${signUpRespone.signUpCondFactor.remark }</textarea></td>
	                <td class="center"><textarea name='responseValue${i}' id='responseValue${i}' class="required" >${signUpRespone.responseValue}</textarea></td>
	                <td class="center" colspan="3">
	                	<input type="file" name="attachFile${i}" id="attachFile${i}" size="20" class="required"/>
	                	<input type="hidden" name="isUploadFile${i}" value="false"/>
						<input type="hidden" id="attachRelaId${i}" name="attachRelaId${i}"  value="${signUpRespone.signUpResponeFile.objId}"/>
						<c:if test='${null != signUpRespone.signUpResponeFile.objId && "" != signUpRespone.signUpResponeFile.objId}'>
							<a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${signUpRespone.signUpResponeFile.objId}">${signUpRespone.signUpResponeFile.viewName}</a>
							&nbsp;&nbsp;
							<a href="#" onClick="supplierDetail.removeFile('${i}');" id="removeFileEle${i}">删除</a>
						</c:if>
	                </td>
	              </tr>
	              <c:set var="i" value="${i+1}"></c:set>
	              </c:forEach>
	            </tbody>
		  </table>
		</div>
					
					
					
					
					
					</form>
					<li class="fullLine"><label class="short">附件：</label>
      					<span id="attachRelaId" class="uploadFile" >${signUprecord.attachRelaId}</span>
      				</li>
					</ul>
					
		
					
					
			<c:if test="${signUprecord.auditStatus==02}"> 
			<div id="supplierDetailsaveButtonDiv" class="conOperation">									
				<button id="supplierDetailsaveButton" type="button" tabindex="19"><span>提交</span></button>
				<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
			<!-- 如果是弹出层显示，则需要关闭按钮 -->
			<c:if test="${param.fromDiv == 'yes'}">
				<button id="supplierDetailReturnButton" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
			</c:if>
			</div>
			</c:if>		
	</div>
	
 <div id="historyView"></div>

<script language="javascript">
var supplierDetail = {};


supplierDetail.removeFile = function(num){
	$("#removeFileEle"+num).prev('a').remove();
	$("#removeFileEle"+num).remove();
	$("[name=attachRelaId"+num+"]").val("");
}


$(document).ready(function(){
	 //附件
	if($('#auditStatusId').val()=='02'){
		$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
			defineSelf:'attachRelaId',//存放关联id的属性名
			attachRelaId:$("#attachRelaId").text()
		});
	}else{
		$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
			defineSelf:'attachRelaId',//存放关联id的属性名
			attachRelaId:$("#attachRelaId").text(),
			readOnly:'yes'
		});
	}
	
	$("#supplierDetailReturnButton").click(function(){
		$("#epsDialogCloseNoReload").click();
	});
	//修改操作
	$("#supplierDetailsaveButton").click(function(){
	
		$("#auditStatusId").val("00");
		//移除样式
		$("#signUprecordDetailForm").find("li").removeClass("eleDisable");

		var i = $('input[id=i]').length;  //报名响应个数

		// 更新文件空状态
		$("#signUpRespone").find('input[type=file]').each(function(i,n){
			
			var fileName = $(n).attr("name").toString();
			var isUploadFileObj = $("input[name=isUploadFile"+fileName.substring(fileName.length-1,fileName.length)+"]");
			var attachRelaIdObj = $("input[name=attachRelaId"+fileName.substring(fileName.length-1,fileName.length)+"]");
			if($(attachRelaIdObj).val()!=""){
				$(n).removeClass("required"); 
			}
			
			if(n.value == ""){
				$(isUploadFileObj).val("false");
			}else{
				$(isUploadFileObj).val("true");
			}
		})	
       
		if(!$('#signUprecordDetailForm').valid()){alert('请正确填写表单!');return;}

		if(confirm('你确定提交吗？')){
		var attachRelaId=$("input[name=attachRelaId]").val();
		$('#signUprecordDetailForm').ajaxSubmit({
			url:$('#initPath').val()+"/SignUprecordController.do?method=updateSignUprecord&attachRela="+attachRelaId+"&isAJAX=true&num="+i,
			dataType:'json',
			success:function(json){
				if(json.result)alert(json.result);if(json.failure)return;
					$('#epsDialogCloseReload').click();
					$("#myDesktop").click();
			},
			error:function(msg){
				alert(msg);
			}
		});		
			}
	});
});


//操作历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=02');
});

</script>