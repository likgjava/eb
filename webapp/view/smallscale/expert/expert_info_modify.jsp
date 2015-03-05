<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!-- 导航显示 -->
<div class="nextBar">
    <ol class="num5">
        <li class="current"><span class="first">1. 填写基本信息</span></li> 
        <li><span>2. 填写教育背景</span></li>
        <li><span>3. 填写培训经历</span></li>
        <li><span>4. 填写任职经历</span></li>
        <li class="last"><span>5. 填写职称信息</span></li>
    </ol>
</div>
<!-- end 导航显示 -->

<form:form id="expertInfoForm" method="post" modelAttribute="expertInfo">
<input type="hidden" id="objId" name="expertInfo.objId" value="<c:out value="${expertInfo.objId}"/>"/>
<input type="hidden" id="objId" name="user.objId" value="<c:out value="${expertInfo.user.objId}"/>"/>
<input type="hidden" id="objId" name="employee.objId" value="<c:out value="${expertInfo.user.emp.objId}"/>"/>
<input type="hidden" id="townId" name="district.objId" value="<c:out value="${expertInfo.district.objId}"/>"/>
<input type="hidden" id="cityId" name="cityId" value="<c:out value="${expertInfo.district.parent.objId}"/>"/>
<input type="hidden" id="provinceId" name="provinceId" value="<c:out value="${expertInfo.district.parent.parent.objId}"/>"/>
<input type="hidden" id="oldUsName" name="oldUsName" value="${expertInfo.user.usName}"/>
<input type="hidden" id="oldEmail" name="oldEmail" value="${expertInfo.user.emp.email}"/>
<input type="hidden" name="useStatus" id="useStatus" value="${expertInfo.useStatus}">
<input type="hidden" id="honorFileId" value="${expertInfo.honorFile}">
<input type="hidden" id="role_type" value="${role_type}">
<input type="hidden" id="photo" name="photo" value="${expertInfo.photo}" />

<div>
	<div class="formLayout imgAndForm">
		<h4><span>专家基本信息</span></h4>
		<div class="k1" style="width: 170px;">
			<div class="img_250_2" id="newPreview" style="border:1px solid #D5D5D5; padding: 4px;">
				<c:choose>
					<c:when test="${expertInfo.photo==null}">
						<img src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="160px" height="208px" />
					</c:when>
					<c:otherwise>
						<img src="<c:url value="AttachmentController.do?method=showImg&objId=${expertInfo.photo}" />" width="160px" height="208px" />
					</c:otherwise>
				</c:choose>
			</div>
			<div style="text-align: center;"><input type="button" id="toCropZoomImgBut" value="选择照片" /></div>
		</div>
		<ul id="expertInfoUl">
	    	<li class="fullLine">
	            <label>用户名：</label>
	            ${expertInfo.user.usName}
	            <input type="hidden" name="user.usName" value="${expertInfo.user.usName}" />
	        </li>
	        <li class="fullLine">
	            <label>姓名：</label>
	            <input type="text" id="name" name="name" value="${expertInfo.user.emp.name}" maxlength="30" class="required" size="30"/>
				<span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label>性别：</label>
	            <input type="radio" id="sex1" name="sex" value="1" <c:if test="${expertInfo.user.emp.sex}" var="isMale">checked="checked"</c:if> />男&nbsp;&nbsp;
	            <input type="radio" id="sex2" name="sex" value="0" <c:if test="${!isMale}">checked="checked"</c:if>/>女
	        </li>
	        <li class="fullLine">
	            <label>政治面貌：</label>
	            <html:select id="politicalLandscape" name="politicalLandscape" code="smallscale.expert.politicalLandscape" selectedValue="${expertInfo.politicalLandscape}"></html:select>
	        </li>
	        <li class="fullLine">
	            <label>出生年月：</label>
	            <input type="text" id="birthday" name="birthday" value="<fmt:formatDate value="${expertInfo.birthday}" pattern="yyyy-MM-dd"/>" size="27" class="required" readonly="readonly"/>
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>国家职业资格等级：</label>
	            <html:select id="professionQualificationLevel" name="professionQualificationLevel" code="smallscale.expert.professionQualificationLevel" selectedValue="${expertInfo.professionQualificationLevel}"></html:select>
	        </li>
	        <li class="fullLine">
	            <label>是否退休：</label>
	            <input type="radio" id="isRetired1" name="isRetired" value="1" <c:if test="${expertInfo.isRetired}" var="hadRetired">checked="checked"</c:if>/>已退休&nbsp;&nbsp;
	            <input type="radio" id="isRetired2" name="isRetired" value="0" <c:if test="${!hadRetired}">checked="checked"</c:if>/>未退休
	        </li>
			<li class="fullLine">
	           <label>联系手机：</label>
	           <input type="text" id="mobile" name="mobile" value="${expertInfo.user.emp.mobile}" maxlength="11" class="required cnMobile" size="30"/>
	           <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	           <label>Email：</label>
	           <input type="text" id="email" name="email" value="${expertInfo.user.emp.email}" maxlength="30" class="emaill" size="30"/>
	        </li>
	        <li class="fullLine">
	           <label>MSN：</label>
	           <input type="text" id="msn" name="msn" value="${expertInfo.user.emp.msn}" maxlength="30" class="msn" size="30"/>
	        </li>
	    </ul>
	</div>

	<div class="formLayout form2Pa">
		<ul>
	        <li class="fullLine">
	           <label>QQ：</label>
	           <input type="text" id="qq" name="qq" value="${expertInfo.user.emp.qq}" maxlength="30" class="qq" size="30"/>
	        </li>
			<li class="fullLine">
	           <label>办公电话：</label>
	           <input type="text" id="telOffice" name="telOffice" value="${expertInfo.user.emp.telOffice}" maxlength="20" class="cnPhone" size="30"/>
	        </li>
	        <li class="fullLine">
	           <label>传真：</label>
	           <input type="text" id="fax" name="fax" value="${expertInfo.user.emp.fax}" maxlength="20" class="cnPhone" size="60"/>
	        </li>
	        <li class="fullLine">
	            <label>所属地区：</label>
	            <form:select path="" id="province">
	            	<c:if test="${expertInfo.district == null}">
	            		<form:option value="">请选择</form:option>
	            	</c:if>
	            	<form:options items="${province}" itemValue="code" itemLabel="name"/> 
	            </form:select>
	            <form:select path="" id="city">
	            	<form:options items="${city}" itemValue="code" itemLabel="name"/> 
	            </form:select>
	            <form:select path="" id="district.objId" cssClass="required">
	            	<form:options items="${town}" itemValue="code" itemLabel="name"/> 
	            </form:select>
	            <span class="eleRequired">*</span>
			</li>
	        <li class="formTextarea">
	           <label>详细通信地址：</label>
	           <textarea name="address" id="address" maxlength="100">${expertInfo.user.emp.address}</textarea>
	        </li>
	        <li class="fullLine">
          		<label>所属行业：</label>
          		<input type="text" id="belongIndustry_display" readonly="readonly" value="${expertInfo.belongIndustry.name}" size="30"/>
          		<input type="hidden" name="belongIndustry.objId" id="belongIndustry" value="${expertInfo.belongIndustry.objId}"/>
    	  	</li>
	        <li class="formTextarea">
				<label>评审品目：</label>
				<textarea name="appCategoryValue_1" id="appCategoryValue_1" maxlength="500" readonly="readonly" class="required">${expertInfo.appCategoryName}</textarea>
				<input type="hidden" id="appCategoryValue_2" />
				<input type="hidden" name="appCategoryValue" id="appCategoryValue" value="${expertInfo.appCategoryValue}"/>
				<span class="eleRequired">*</span> 
          	</li>
			<li class="formTextarea">
				<label>评审区域：</label>
				<textarea name="appDistrictValue_1" id="appDistrictValue_1" maxlength="500" readonly="readonly" class="required">${expertInfo.appDistinctName}</textarea>
				<input type="hidden" id="appDistrictValue_2" />
				<input type="hidden" name="appDistrictValue" id="appDistrictValue" value="${expertInfo.appDistrictValue}"/>
				<span class="eleRequired">*</span> 
          	</li>
	        <li class="fullLine">
	           <label>从事特长年限：</label>
	           <input type="text" name="specifyYear" id="specifyYear" value="${expertInfo.specifyYear}" size="3" maxlength="2" />年
	        </li>
	        <li class="formTextarea">
	           <label>特长描述：</label>
	           <textarea name="technicalExcellence" id="technicalExcellence" maxlength="2000">${expertInfo.technicalExcellence}</textarea>
	        </li>
	        <li class="formTextarea">
	           <label>经验描述：</label>
	           <textarea name="tenderExperience" id="tenderExperience" maxlength="2000">${expertInfo.tenderExperience}</textarea>
	        </li>
	        <li class="fullLine">
	     		<label>荣誉证书：</label>
	     		<div id="honorFile" class="uploadFile"></div>
    	    </li>
    	</ul>
    </div>
    
   	<div class="conOperation">
		<button type="button" id="expertInfoSaveOfExpert" class="next"><span>保存</span></button>
   		<!-- 运营管理员 开始 -->
   		<c:if test="${role_type=='3' && (expertInfo.auditStatus=='00' || expertInfo.auditStatus=='01')}">
			<button type="button" id="expertInfoSubmit" class="next"><span>保存为正式</span></button>
		</c:if>
		<!-- 运营管理员 结束 -->
		<!-- 专家 开始 -->
   		<c:if test="${role_type=='4'}">
			<button type="button" id="expertInfoSubmitOfExpert" class="next"><span>提交</span></button>
		</c:if>
		<!-- 专家 结束 -->
		<button type="button" id="returnBtn" class="next"><span>返回</span></button>
	</div>
</div>
</form:form>		

<script>
/**
 * 管理员修改专家信息页面
 * create by likg
 */
var ExpertInfoForm={};
//保存或提交专家信息
ExpertInfoForm.saveOrSubmit=function(saveType){
	if(!$("#expertInfoForm").valid()){
		alert("请正确填写表单!");return;
	}

	$("#townId").val($("select[id=district.objId]").val());

	var saveMsg = "确定保存专家信息的修改？";
	if(saveType=='submitOfExpert'){
		saveMsg = "确认提交给管理员进行审核吗？\n（如果您已通过审核并未修改信息,请勿提交！）";
	}
	
    if(window.confirm(saveMsg)){
        $("#expertInfoSubmit").attr("disabled",true);

    	if(saveType == 'saveOfManager'){
    		$("#useStatus").val('00');
    	}else if(saveType == 'submitOfManager'){
    		$("#useStatus").val('01');
    	}
    	
		var url = $('#initPath').val()+"/ExpertInfoController.do?method=updateExpertInfo&saveType="+saveType;
		//保存或提交
    	$('#expertInfoForm').ajaxSubmit({
    		url:url,
			dataType:'json',
			success:function(json){
    			if(saveType=='submitOfExpert'){
	    			alert("提交成功！请耐心等待管理员审核！");
	    		}else{
	    			alert("保存成功！");
	    		}
    			$("#returnBtn").click();
			},
			error:function(msg){
				alert(JSON.stringify(msg));
				$("#expertInfoSubmit").attr("disabled",false);
			}
		});	
	}
}

$(document).ready(function(){
	var path=$("#initPath").val();
	$("#birthday").epsDatepicker();

	//工作年限，控制不能输入非数字
	$("#specifyYear").inputFillter({type:'int'});

    //行政区域联动
	var option = {parameter:"objId"};
    $('#province').CascadingSelect($('#city'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});
    $('#city').CascadingSelect($('select[id=district.objId]'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});

    $("#province").val($("#provinceId").val());
    $("#city").val($("#cityId").val());
    $("select[id=district.objId]").val($("#townId").val());

	//上传专家荣誉证书的附件
	var honorFileId = $('#expertInfoForm').find('input[id=honorFileId]').val();
	if(null!=honorFileId && ""!=honorFileId){
		$('#honorFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=honorFile&attachRelaId='+honorFileId);
	}else{
		$('#honorFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=honorFile');
	}

	//回填评审品目
	if($('#appCategoryValue').val()!=null && $('#appCategoryValue').val()!= ""){
		var appCategoryValue=$('#appCategoryValue').val().split("##||##");
		$('#appCategoryValue_2').val(appCategoryValue[0]);
		$('#appCategoryValue_1').text(appCategoryValue[1]);
	}
	//选择评审品目
	$("#appCategoryValue_1").click(function(e){
	    $.epsDialog({
	        title:'选择评审品目',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=appCategoryValue_2&NAMES=appCategoryValue_1&className=PurCategory&action=listTop&isCheckBox=true&checkStatus=true',
		    onClose: function(){ 
	      		$("#appCategoryValue").val($("#appCategoryValue_2").val()+"##||##"+$("#appCategoryValue_1").val());
	      	}
	    }); 
	});
	
	//回填评审区域
	if($('#appDistrictValue').val()!=null && $('#appDistrictValue').val()!= ""){
		var appDistrictValue=$('#appDistrictValue').val().split("##||##");
		$('#appDistrictValue_2').val(appDistrictValue[0]);
		$('#appDistrictValue_1').text(appDistrictValue[1]);
	}
	//选择评审区域
	$("#appDistrictValue_1").click(function(e){
	    $.epsDialog({
	        title:'选择评审区域',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=appDistrictValue_2&NAMES=appDistrictValue_1&className=District&action=listTop&isCheckBox=true',
		    onClose: function(){ 
	      		$("#appDistrictValue").val($("#appDistrictValue_2").val()+"##||##"+$("#appDistrictValue_1").val());
	      	}
	    }); 
	});

	//选择所属行业
 	$("#belongIndustry_display").click(function(e){
	    $.epsDialog({
	        title:'选择所属行业',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=belongIndustry&NAMES=belongIndustry_display&className=Industry&action=listTop'
	    }); 
 	});
    
	//专家保存修改的内容
	$("#expertInfoSaveOfExpert").click(function(){
		ExpertInfoForm.saveOrSubmit('saveOfExpert');
	});
	//专家提交修改的内容
	$("#expertInfoSubmitOfExpert").click(function(){
		ExpertInfoForm.saveOrSubmit('submitOfExpert');
	});
	
	//管理员保存为正式
	$("#expertInfoSubmit").click(function(){
		ExpertInfoForm.saveOrSubmit('submitOfManager');
	})
	
	//返回
	$("#returnBtn").click(function(){
		var url = $("#initPath").val()+'/view/smallscale/expert/expert_manage_list.jsp';
		if($("#role_type").val() == '4'){ //专家
			url = $("#initPath").val()+'/ExpertInfoController.do?method=toExpertInfoModifyView&viewName=expertInfoModifyTipView';
		}
		$("#conBody").loadPage(url);
	})

	//选择专家照片
	$("#toCropZoomImgBut").click(function(){
		var url = $('#initPath').val()+"/view/pubservice/application/cropzoomimg/crop_zoom_img.jsp?picWidth=160&picHeight=208&pic_WH_rule_str=expert_pic_width_height_rule&propertyName=photo&oldAttachmentId="+$('#photo').val();
		$.epsDialog({
			title: '选择专家照片',
			url: url
		});
	});

})
</script>