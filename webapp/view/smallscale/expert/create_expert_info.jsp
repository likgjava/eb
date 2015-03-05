<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="expertInfoForm" method="post">
<div>
	<div class="formLayout imgAndForm">
		<input type="hidden" name="useStatus" id="useStatus" value="00">
		<input type="hidden" id="photo" name="photo" />
		<h4><span>专家基本信息</span></h4>
		<div class="k1" style="width: 170px;">
			<div class="img_250_2" id="newPreview" style="border:1px solid #D5D5D5; padding: 4px;">
				<img id="view" src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="160px" height="208px" />
			</div>
			<div style="text-align: center;"><input type="button" id="toCropZoomImgBut" value="选择照片" /></div>
		</div>
		<ul id="expertInfoUl">
	    	<li class="fullLine">
	            <label>用户名：</label>
	            <input type="text" id="usName" name="usName" maxlength="50" class="required" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label>姓名：</label>
	            <input type="text" id="name" name="name" maxlength="50" class="required" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label>性别：</label>
	            <input type="radio" id="sex1" name="sex" value="1" checked="checked"/>先生&nbsp;&nbsp;
	            <input type="radio" id="sex2" name="sex" value="0"/>女士
	        </li>
	        <li class="fullLine">
	            <label>政治面貌：</label>
             	<html:select id="politicalLandscape" name="politicalLandscape" code="smallscale.expert.politicalLandscape"></html:select>
	        </li>
	        <li class="fullLine">
	            <label>出生年月：</label>
	            <input type="text" name="birthday" id="birthday" size="30" readonly="readonly" class="required"/>
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>国家职业资格等级：</label>
             	<html:select id="professionQualificationLevel" name="professionQualificationLevel" code="smallscale.expert.professionQualificationLevel"></html:select>
	        </li>
	        <li class="fullLine">
	            <label>是否退休：</label>
	            <input type="radio" id="isRetired1" name="isRetired" value="0" checked="checked"/>未退休&nbsp;&nbsp;
	            <input type="radio" id="isRetired2" name="isRetired" value="1"/>已退休
	        </li>
	        <li class="fullLine">
	           <label>联系手机：</label>
	           <input type="text" id="mobile" name="mobile" maxlength="11" class="required cnMobile" size="60"/>
	           <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	           <label>Email：</label>
	           <input type="text" id="email" name="email" maxlength="30" class="emaill required" size="60"/>
	           <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	           <label>MSN：</label>
	           <input type="text" id="msn" name="msn" maxlength="30" class="msn" size="60"/>
	        </li>
	    </ul>
	</div>
	
	<div class="formLayout form2Pa">
		<ul>
	        <li class="fullLine">
	           <label>QQ：</label>
	           <input type="text" id="qq" name="qq" maxlength="30" class="qq" size="60"/>
	        </li>
			<li class="fullLine">
	           <label>办公电话：</label>
	           <input type="text" id="telOffice" name="telOffice" maxlength="20" class="cnPhone" size="60"/>
	        </li>
	        <li class="fullLine">
	           <label>传真：</label>
	           <input type="text" id="fax" name="fax" maxlength="20" class="cnPhone" size="60"/>
	        </li>
	        <li class="fullLine">
	            <label>所属地区：</label>
	         	<select name="province.objId" id="province"></select>
	            <select name="city.objId"  id="city" ><option value="">请选择</option></select>
	            <select name="district.objId"  id="district" class="required"><option value="">请选择</option></select>
	            <span class="eleRequired">*</span> 
			</li>
	        <li class="formTextarea">
	           <label>详细通信地址：</label>
	           <textarea name="address" id="address" maxlength="100"></textarea>
	        </li>
	        <li class="fullLine">
	          	<label>所属行业：</label>
	          	<input type="text" id="belongIndustry_display" readonly="readonly" />
	          	<input type="hidden" name="belongIndustry.objId" id="belongIndustry" />
    	  	</li>
			<li class="formTextarea">
				<label>评审品目：</label>
				<textarea name="appCategoryValue_1" id="appCategoryValue_1" maxlength="500" readonly="readonly" class="required"></textarea>
				<input type="hidden" id="appCategoryValue_2" />
				<input type="hidden" name="appCategoryValue" id="appCategoryValue" />
				<span class="eleRequired">*</span> 
          	</li>
			<li class="formTextarea">
				<label>评审区域：</label>
				<textarea name="appDistrictValue_1" id="appDistrictValue_1" maxlength="500" readonly="readonly" class="required"></textarea>
				<input type="hidden" id="appDistrictValue_2" />
				<input type="hidden" name="appDistrictValue" id="appDistrictValue" />
				<span class="eleRequired">*</span> 
          	</li>
	        <li class="fullLine">
	           <label>从事特长年限：</label>
	           <input type="text" name="specifyYear" id="specifyYear" size="3" maxlength="2" />年
	        </li>
	        <li class="formTextarea">
	           <label>特长描述：</label>
	           <textarea name="technicalExcellence" id="technicalExcellence" maxlength="100"></textarea>
	        </li>
	        <li class="formTextarea">
	           <label>经验描述：</label>
	           <textarea name="tenderExperience" id="tenderExperience" maxlength="100"></textarea>
	        </li>
	        <li class="fullLine">
	     		<label>荣誉证书：</label>
	     		<div id="honorFile" class="uploadFile"></div>
    	    </li>
    	</ul>
	</div>
	
   	<div class="conOperation">
		<button type="button" id="expertInfoSave" class="next"><span>保存为临时</span></button>
		<button type="button" id="expertInfoSubmit" class="next"><span>保存为正式</span></button>
		<button type="button" id="returnBtn" class="next"><span>返回</span></button>
	</div>
</div>
</form:form>		

<script>
/**
 * 管理员创建专家页面
 * create by likg
 */
var ExpertInfoForm={};

//保存或提交专家信息
ExpertInfoForm.saveOrSubmit=function(saveType){
	if(!$("#expertInfoForm").valid()){
		alert("请正确填写表单!");return;
	}
	
    if(window.confirm("确定保存专家信息吗?")){
        $("#expertInfoSave").attr("disabled",true);
        $("#expertInfoSubmit").attr("disabled",true);
		var url = $('#initPath').val()+"/ExpertInfoController.do?method=saveExpertInfo";

    	if(saveType == 'save'){
    		$("#useStatus").val('00');
    	}else if(saveType == 'submit'){
    		$("#useStatus").val('01');
    	}

		//保存或提交
    	$('#expertInfoForm').ajaxSubmit({
    		url:url,
			dataType:'json',
			success:function(json){
	    		alert("创建成功!");
	    		$("#returnBtn").click();
			},
			error:function(msg){
				alert(JSON.stringify(msg));
				$("#expertInfoSave").attr("disabled",false);
				$("#expertInfoSubmit").attr("disabled",false);
			}
		});	
	}
}

$(document).ready(function(){
	$("#birthday").epsDatepicker();

	//工作年限，控制不能输入非数字
	$("#specifyYear").inputFillter({type:'int'});
	
	//唯一性验证
	$.validator.addMethod("userNameUnique",function(value,element,param){return uniqueHandler("User",param,value,"");},'该用户已存在');
	$.validator.addMethod("emailUnique",function(value,element,param){return uniqueHandler("Employee",param,value,"");},'该邮箱地址已存在');
	//用户表单验证
	$("#expertInfoForm").validate({
		rules:{
			usName:{userNameUnique:"usName"},
			email:{emailUnique:"email"}
		}
	});

	//上传专家荣誉证书的附件
	$('#honorFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=honorFile');

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

    //行政区域联动
	var option = {parameter:"objId"};
	var provinceDatas=$('#province').FillOptions($('#initPath').val()+'/DistrictController.do?method=findTopDistrict',option);
    $('#province').CascadingSelect($('#city'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});
    $('#city').CascadingSelect($('#district'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});

	//选择所属行业
 	$("#belongIndustry_display").click(function(e){
	    $.epsDialog({
	        title:'选择所属行业',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=belongIndustry&NAMES=belongIndustry_display&className=Industry&action=listTop'
	    }); 
 	});
 	
	//保存为临时
	$("#expertInfoSave").click(function(){
		ExpertInfoForm.saveOrSubmit('save');
	})
	
	//保存为正式
	$("#expertInfoSubmit").click(function(){
		ExpertInfoForm.saveOrSubmit('submit');
	})
	
	//返回
	$("#returnBtn").click(function(){
		$("#conBody").loadPage($("#initPath").val()+'/view/smallscale/expert/expert_manage_list.jsp');
	});

	//选择专家照片
	$("#toCropZoomImgBut").click(function(){
		var url = $('#initPath').val()+"/view/pubservice/application/cropzoomimg/crop_zoom_img.jsp?picWidth=160&picHeight=208&pic_WH_rule_str=expert_pic_width_height_rule&propertyName=photo";
		$.epsDialog({
			title: '选择专家照片',
			url: url
		});
	});

})
</script>