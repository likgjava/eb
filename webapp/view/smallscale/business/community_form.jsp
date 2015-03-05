<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="CommunityForm" name="CommunityForm" method="post" modelAttribute="community" enctype="multipart/form-data">
<form:hidden path="objId"></form:hidden>
<div id = "addCommunity" class="formLayout formPa">
    <h4><span>社区信息</span></h4>

	<input type="hidden" id="picture" name="picture" value="${community.picture}" />
	<div class="k1" style="width: 210px;">
		<div class="img_250_2" id="newPreview" style="border:1px solid #D5D5D5; padding: 4px;">
			<img id="view" src="<%=request.getContextPath()%><c:choose><c:when test="${community.picture!=null}">AttachmentController.do?method=showImg&objId=${community.picture}</c:when><c:otherwise>/view/resource/skin/bizplatform/img/orginfo_add.gif</c:otherwise></c:choose>" width="200px" height="150px" />
		</div>
		<div style="text-align: center;"><input type="button" id="toCropZoomImgBut" value="选择图片" /></div>
	</div>
	<ul>
		<li id="nameLi">
			<label>社区名称：</label>
			<form:input path="communityName" cssClass="required" cssStyle="width:250px;"></form:input>
            <span class='eleRequired'>*</span>
		</li>	
		<li>
			<label>社区群号：</label>
			<form:input path="groupId" cssClass="required" cssStyle="width:100px;"></form:input>
            <span class='eleRequired'>*</span>
		</li>
		<li>
	       	<label>投标类别：</label>
	       	<input type="hidden" id="tenderCategorys.objId" name="goodsClassNames" value="<c:if test="${fn:length(fn:split(community.tenderCategorys,'##||##'))>0}">${fn:split(community.tenderCategorys,'##||##')[0] }</c:if>"/>
        	    <input type="text" id="tenderCategorys.name" name="goodsClassNames" class="sysicon siSearch" value="<c:if test="${fn:length(fn:split(community.tenderCategorys,'##||##'))>0}">${fn:split(community.tenderCategorys,'##||##')[1] }</c:if>" size="70" readonly="readonly"/>
        	   	<input type="hidden" name="tenderCategorys" value="${community.tenderCategorys }"/>
		</li>
		
		<li>
	       	<label>所属机构：</label>
	       	<c:choose>
	       		<c:when test="${orgInfo!=null}">
	       			<input type="hidden" name="orgInfo.objId" id="orgInfoId" value="${orgInfo.objId}"/>
	       			<span>${orgInfo.orgName}</span>
	       		</c:when>
	       		<c:otherwise>
			       	<input type="hidden" name="orgInfo.objId" id="orgInfoId" value="${community.orgInfo!=null?community.orgInfo.objId:''}"/>
		        	<input type="text" name="orgInfo.orgName" id="orgInfoName" value="${community.orgInfo!=null?community.orgInfo.orgName:''}" class="sysicon siSearch" value="" size="70" readonly="readonly"/>
	       		</c:otherwise>
	       	</c:choose>
		</li>
		
		<li>
			<label>特色社区：</label>
			<form:select path="isSpecial">
				<form:option value="true">是</form:option>
				<form:option value="false">否</form:option>
			</form:select>				
		</li>
		<li>
			<label>推荐社区：</label>
			<form:select path="isRecommended">
				<form:option value="true">是</form:option>
				<form:option value="false">否</form:option>
			</form:select>				
		</li>
		<li>
			<label>是否显示：</label>
			<form:select path="isDisplay">
				<form:option value="true">是</form:option>
				<form:option value="false">否</form:option>
			</form:select>				
		</li>
		
		<li>
			<label>是否公开：</label>
			<form:select path="isPublic">
				<form:option value="true">是</form:option>
				<form:option value="false">否</form:option>
			</form:select>				
		</li>
		
		<li class="formTextarea">
			<label>备注：</label>
			<form:textarea path="memo"/>				
		</li>
	</ul>
</div>
</form:form>

<div class="conOperation center">
  	<button  id="CommunitySave" type="button" onclick="saveCommunity();"><span><spring:message code="globe.save"/></span></button>
  	<button  id="CommunityReturn" name="historyBackBtn" type="button" ><span><spring:message code="globe.return"/></span></button>
</div>
	
<script type="text/javascript">
var CommunityForm={};

saveCommunity = function(){
	if(!$('#CommunityForm').valid()){alert('请正确填写表单!');return;}
	var  CommunityFormJSON = formToJsonObject("CommunityForm");
	$.getJSON($("#initPath").val()+"/CommunityController.do?method=saveCommunity" ,CommunityFormJSON ,function(json){
		if(json.success){
			alert("操作成功！");
			$("button[name=historyBackBtn]").click();//返回
		}else{
			alert("操作失败！");
		}
	})
}

$(document).ready(function(){

	var objid = $('#objId').val();
	//唯一性验证
	$.validator.addMethod("communityNameUnique",function(value,element,param){return uniqueHandler("Community",param,value,objid);},'该社区名称已存在');
	//表单验证
	$("#CommunityForm").validate({
		rules:{
			communityName:{communityNameUnique:"communityName"}
		}
	});	

    //选择品目
    $('input[id=tenderCategorys.name]').click(function(e){
	    $.epsDialog({
	        title:'请选择品目',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=tenderCategorys&className=PurCategory&action=listTop&isOpen=1&isCheckBox=true&checkValues='+$("input[id=tenderCategorys.objId]").val(),
	        onClose:function(){
				$("input[name=tenderCategorys]").val($("input[id=tenderCategorys.objId]").val()+"##||##"+$("input[id=tenderCategorys.name]").val());
		    }
	    }); 
	});

    //选择选择机构
    $('input[id=orgInfoName]').click(function(e){
    	var url =$('#initPath').val()+'/view/bizplatform/organization/manager/select_orginfo.jsp?Hname=orgInfoName&Hid=orgInfoId';
        $.epsDialog({
            title:'请选择所属机构',
            url:url
        });
	});

	//选择机构图片
	$("#toCropZoomImgBut").click(function(){
		var url = $('#initPath').val()+"/view/pubservice/application/cropzoomimg/crop_zoom_img.jsp?picWidth=200&picHeight=150&propertyName=picture"+($("#picture").val()?("&oldAttachmentId="+$("#picture").val()):"");
		$.epsDialog({
			title: '选择机构图片',
			url: url
		});
	});
	
});
</script>
