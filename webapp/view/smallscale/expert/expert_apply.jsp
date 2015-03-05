<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="expertInfoForm" method="post" modelAttribute="expertInfo" enctype="multipart/form-data">
<input type="hidden" id="objId" name="expertInfo.objId" value="<c:out value="${expertInfo.objId}"/>"/>
<input type="hidden" id="townId" name="district.objId" value="<c:out value="${expertInfo.district.objId}"/>"/>
<input type="hidden" id="cityId" name="cityId" value="<c:out value="${expertInfo.district.parent.objId}"/>"/>
<input type="hidden" id="provinceId" name="provinceId" value="<c:out value="${expertInfo.district.parent.parent.objId}"/>"/>
<input type="hidden" id="honorFileId" value="${expertInfo.honorFile}">
<input type="hidden" id="pic_WH_rule_str" name="pic_WH_rule_str" value="expert_pic_width_height_rule">

	<div class="formLayout  form2Pa">
		<h4><span>专家基本信息</span></h4>
		<div class="k1">
			<div class="img_250_2" id="newPreview">
				<img id="view"  src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="200px" height="200px"></img>
			</div>
			<input name="pictureFile" type="file" id="pictureFile" size="22"/>
		</div>
		<ul id="expertInfoUl">
	    	<li class="fullLine">
	            <label>用户名：</label>
	            <span>${user.usName}</span>
	        </li>
	        <li class="fullLine">
	            <label>是否退休：</label>
	            <input type="radio" id="isRetired1" name="isRetired" value="1" <c:if test="${expertInfo.isRetired}" var="hadRetired">checked="checked"</c:if>/>已退休&nbsp;&nbsp;
	            <input type="radio" id="isRetired2" name="isRetired" value="0" <c:if test="${!hadRetired}">checked="checked"</c:if>/>未退休
	        </li>
	        <li class="fullLine">
          		<label>所属行业：</label>
          		<input type="text" id="belongIndustry_display" readonly="readonly" value="${expertInfo.belongIndustry.name}" size="30"/>
          		<input type="hidden" name="belongIndustry.objId" id="belongIndustry" value="${expertInfo.belongIndustry.objId}"/>
    	  	</li>
	        <li class="fullLine">
	            <label>国家职业资格等级：</label>
	            <html:select id="professionQualificationLevel" name="professionQualificationLevel" code="smallscale.expert.professionQualificationLevel" selectedValue="${expertInfo.professionQualificationLevel}"></html:select>
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
	    </ul>
	    <br/><br/>
	    
		<ul>
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
	           <textarea name="technicalExcellence" id="technicalExcellence">${expertInfo.technicalExcellence}</textarea>
	        </li>
	        <li class="formTextarea">
	           <label>经验描述：</label>
	           <textarea name="tenderExperience" id="tenderExperience">${expertInfo.tenderExperience}</textarea>
	        </li>
	        <li class="fullLine">
	     		<label>荣誉证书：</label>
	     		<div id="honorFile" class="uploadFile"></div>
    	    </li>
    	</ul>
    	<div class="conOperation">
			<button type="button" id="expertInfoSubmit" class="next"><span>申请</span></button>
		</div>
	</div>
</form:form>		

<script>
/**
 * 申请为专家页面
 * create by likg
 */
var ExpertInfoForm={};

//保存专家信息
ExpertInfoForm.saveOrSubmit=function(){
	if(!$("#expertInfoForm").valid()){
		alert("请正确填写表单!");return;
	}

	$("#townId").val($("select[id=district.objId]").val());
	
    if(window.confirm("确定申请为专家吗?")){
        $("#expertInfoSubmit").attr("disabled",true);
        var url = $('#initPath').val()+"/ExpertInfoController.do?method=saveExpertInfoOfApply";

		//保存
    	$('#expertInfoForm').ajaxSubmit({
    		url:url,
			dataType:'json',
			success:function(json){
	    		if(json.result=='success') {
	    			alert('申请成功，请耐心等待管理员的审核！');
	    			$('#conBody').loadPage($('#initPath').val()+"/ExpertInfoController.do?method=toApplyExpertView");
				}else{
					alert(ascii2native(json.result));
				}
			},
			error:function(msg){
				alert(JSON.stringify(msg));
				$("#expertInfoSubmit").attr("disabled",false);
			}
		});	
	}
}

$(document).ready(function(){
	$("#birthday").epsDatepicker();

	//工作年限，控制不能输入非数字
	$("#specifyYear").inputFillter({type:'int'});
	
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
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=appCategoryValue_2&NAMES=appCategoryValue_1&className=PurCategory&action=listTop&isCheckBox=true',
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

	//预览图片
	$("#pictureFile").change(function(){
		var filePath = $("#pictureFile").val();
		var fileName = filePath.replace(/.+\\([^\\]+)/,'$1');
		var i = fileName.lastIndexOf('.');       	 //从右边开始找第一个'.'
		var len = fileName.length;                	 //取得总长度
		var str = fileName.substring(len,i+1);    	 //取得后缀名
		var exName = "PNG,BMP,JPG,GIF";       		 //允许的后缀名
		var k = exName.indexOf(str.toUpperCase());	 //转成大写后判断
		if(k==-1){                                	 //没有符合的
		    alert("上传文件错误！只能上传"+exName);
			this.value="";
		}else{
			$("#newPreview").find('img').attr("src",preViewPic(this));
		}
	});
	
	//行政区域联动
	var option = {parameter:"objId"};
    $('#province').CascadingSelect($('#city'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});
    $('#city').CascadingSelect($('select[id=district.objId]'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});

    $("#province").val($("#provinceId").val());
    $("#city").val($("#cityId").val());
    $("select[id=district.objId]").val($("#townId").val());
    
	//选择所属行业
 	$("#belongIndustry_display").click(function(e){
	    $.epsDialog({
	        title:'选择所属行业',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=belongIndustry&NAMES=belongIndustry_display&className=Industry&action=listTop'
	    }); 
 	});
 	
	//保存
	$("#expertInfoSubmit").click(function(){
		ExpertInfoForm.saveOrSubmit();
	})
	
})
</script>