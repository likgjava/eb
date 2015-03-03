<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="ExBaseInfoForm" method="post"> 
	<div>
		<c:if test="${ param.parentCompanyId!=null && fn:length(param.parentCompanyId)>0 }">
			<!-- 父级公司id -->
			<input type="hidden" id="company.parent.objId" name="company.parent.objId" value="${param.parentCompanyId }">
		</c:if>
		<input type="hidden" name="useStatus" id="useStatus" value="00">
		<input type="hidden" name="appType" id="appType" value="${param.appType}">
		<input type="hidden" id="logo" name="logo" value="${orgInfo.logo}" />
		<div class="formLayout imgAndForm">
			<h4><span>机构基本信息</span></h4>
			<div class="k1" style="width: 210px;">
				<div class="img_250_2" id="newPreview" style="border:1px solid #D5D5D5; padding: 4px;">
					<img id="view" src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="200px" height="200px" />
				</div>
				<div style="text-align: center;"><input type="button" id="toCropZoomImgBut" value="选择图片" /></div>
			</div>
		
			<ul id="OrgInfoUl">
		    	<li class="fullLine">
		            <label for="orgName">企业名称：</label>
		            <input type="text" id="orgName" name="orgName" maxlength="50" class="required" size="60"/>
		            <span class="eleRequired">*</span> 
		        </li>
		        <li class="fullLine">
		            <label for="orgCode">机构代码：</label>
		            <input type="text" id="orgCode" name="orgCode" maxlength="50" size="60"/>
		        </li>
		        <li class="fullLine">
		            <label for="orgCode">企业法定代表人：</label>
		            <input type="text" id="croporate" name="company.croporate" maxlength="50" size="60"/>
		        </li>
		        <li class="fullLine">
		           <label for="tel">联系手机：</label>
		           <input type="text" id="mobilePhone" name="company.mobilePhone" maxlength="11" class="required cnMobile" size="60"/>
		           <span class="eleRequired">*</span> 
		        </li>
		        <li class="fullLine">
		           <label for="tel">办公电话：</label>
		           <input type="text" id="tel" name="company.tel" maxlength="12" class="cnPhone" size="60"/>
		        </li>
		        <li class="fullLine">
		           <label for="tel">办公传真：</label>
		           <input type="text" id="fax" name="company.fax" maxlength="12" class="cnPhone" size="60"/>
		        </li>
		        <li class="fullLine">
		           <label for="tel">企业email：</label>
		           <input type="text" id="email" name="company.email" maxlength="30" class="emaill" size="60"/>
		        </li>
		        <li class="fullLine">
		            <label for="town">行政区域名称：</label>
		         	<select name="province.objId" id="province"></select>
		            <select name="city.objId"  id="city" ><option value="">请选择</option></select>
		            <select name="town.objId"  id="town" class="required"><option value="">请选择</option></select>
		            <span class="eleRequired">*</span> 
				</li>
				<li class="fullLine">
		           <label for="postCode">邮编：</label>
		           <input type="text" id="postCode" name="company.postCode" maxlength="6" class="cnZipCode" size="60"/>
		        </li>
		    </ul>
	    </div>
	    <div class="formLayout form2Pa">
			<ul>
				<li class="formTextarea">
		           <label for="address">详细通信地址：</label>
		           <textarea name="company.address" id="address" maxlength="100"></textarea>
		        </li>
				<li>
		            <label for="isManufacturer">是否厂家：</label>
		         	<select name="isManufacturer" id="isManufacturer" class="required">
			         	<option value="0" selected="selected">否</option>
			         	<option value="1">是</option>
		         	</select>
					<span class="eleRequired">*</span> 
		        </li>
				<li>
		          	<label for="belongIndustry">所属行业：</label>
		          	<input type="text" id="belongIndustry_display" readonly="readonly" />
		          	<input type="hidden" name="belongIndustry.objId" id="belongIndustry" />
	    	  	</li>
				<li>
					<label for="purSbjct">企业类型：</label>
					<html:select id="entPrpt_ex" name="entPrpt_ex" code="biz.orgInfo.entprpt"></html:select>
				</li>
				<li>
	             	<label for="unitScape">企业规模：</label>
	             	<html:select id="unitScape" name="unitScape" code="biz.orgInfo.unitScape"></html:select>
	          	</li>
				<li>
					<label for="begainDate">开业日期：</label>
					<input type="text" name="begainDate" id="begainDate" size="40" class="required" readonly="readonly" value="2000-01-01"/>
					<span class="eleRequired">*</span>
				</li>
				<li class="fullLine">
					<label for="webUrl">企业网址：</label>
					<input type="text" name="webUrl" id="webUrl" size="40" class="url" value=""/> （请以http:// 开头）
				</li>
				<li class="formTextarea">
					<label for="purSbjct">经营范围：</label>
					<textarea name="bidForRange_1" id="bidForRange_1" maxlength="500" readonly="readonly" class="required"></textarea>
					<input type="hidden" id="bidForRange_2" />
					<input type="hidden" name="bidForRange" id="bidForRange" />
					<span class="eleRequired">*</span> 
	          	</li>
				<li class="formTextarea">
					<label for="mainProducts">主营产品：</label>
					<textarea name="mainProducts" id="mainProducts" maxlength="500"></textarea>
				</li>
				<li class="formTextarea">
					<label for="entCapacity">企业产能：</label>
					<textarea name="entCapacity" id="entCapacity" maxlength="500"></textarea>
				</li>
				<li class="formTextarea">
					<label for="descCn">企业简介：</label>
					<textarea name="descCn" id="descCn" maxlength="500"></textarea>
				</li>
				<li class="fullLine">
					<label for="createType">创建机构类型：</label>
					<c:choose>
					<c:when test="${!empty param.createType}">
						<c:if test="${param.createType=='buyer'}">
						<input type="checkbox" name="createType" value="buyer" disabled="disabled" checked="checked">采购人      
						<input type="checkbox" name="createType" value="supplier" disabled="disabled">供应商
						</c:if>
						<c:if test="${param.createType=='supplier'}">
						<input type="checkbox" name="createType" value="buyer" disabled="disabled">采购人      
						<input type="checkbox" name="createType" value="supplier" disabled="disabled" checked="checked">供应商
						</c:if>
					</c:when>
					<c:otherwise>
						<input type="checkbox" name="createType" value="buyer" checked="checked">采购人      
						<input type="checkbox" name="createType" value="supplier" checked="checked">供应商
					</c:otherwise>
					</c:choose>
				</li>
	    	</ul>
    	</div>
    	<div class="conOperation">
			<button type="button" id="orgInfoSave" class="next"><span>保存</span></button>
			<button type="button" id="orgInfoSubmit" class="next"><span>保存为正式</span></button>
			<button type="button" name="historyBackBtn" class="next"><span>返回</span></button>
		</div>
	</div>
</form:form>		

<script>
var OrganizationForm={};
//保存机构信息
OrganizationForm.submit=function(saveType){
	if($("input[name=createType]:checked").length < 1){
		alert("请选择要创建的机构类型！"); return ;
	}
    if(window.confirm("确定保存机构信息吗?")){
        $("#orgInfoSubmit").attr("disabled",true);
        $("#orgInfoSave").attr("disabled",true);
		var url = $('#initPath').val()+"/ExOrgInfoController.do?method=saveOrgInfo&createType="+$("input[name=createType]:checked").val();

    	if(saveType == 'save'){
    		$("#useStatus").val('00');
    	}else if(saveType == 'submit'){
    		$("#useStatus").val('01');
    	}

		//提交
    	$('#ExBaseInfoForm').ajaxSubmit({
    		url:url,
			dataType:'json',
			success:function(json){
    			alert("创建成功!");
    			$("button[name=historyBackBtn]").click();
			},
			error:function(msg){
				 alert(JSON.stringify(msg));
				 $("#orgInfoSubmit").attr("disabled",false);
				 $("#orgInfoSave").attr("disabled",false);
			}
		});	
	}
}

//提交
OrganizationForm.saveOrSubmit=function(saveType) {
	if(!$("#ExBaseInfoForm").valid()){
		alert("请正确填写表单!");return;
	}
	if($('select[id=city]').val()=="" || $('select[id=city]').val()==null){
		alert("请选择行政区域!");
		return;
	}
	if($('select[id=town]').val()=="" || $('select[id=town]').val()==null){
		alert("请选择行政区域!");
		return;
	}
	OrganizationForm.submit(saveType);
}

$(document).ready(function(){
	var path=$("#initPath").val();
	$("#begainDate").epsDatepicker();
	
	//唯一性验证
	$.validator.addMethod("orgCodeUnique",function(value,element,param){return uniqueHandler("OrgInfo",param,value,"");},'该机构代码已存在');
	$.validator.addMethod("orgNameUnique",function(value,element,param){return uniqueHandler("OrgInfo",param,value,"");},'该机构名称已存在');

	//机构表单验证
	$("#ExBaseInfoForm").validate({
		rules:{
			orgCode:{orgCodeUnique:"orgCode"},
			orgName:{orgNameUnique:"orgName"}
		}
	});

	//回填投标范围及类别
	if($('#bidForRange').val()!=null && $('#bidForRange').val()!= ""){
		var bidForRange=$('#bidForRange').val().split("##||##");
		$('#bidForRange_2').val(bidForRange[0]);
		$('#bidForRange_1').text(bidForRange[1]);
	}

	//选择品目（投标范围及类别）
	$("#bidForRange_1").click(function(e){
	    $.epsDialog({
	        title:'选择品目',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=bidForRange_2&NAMES=bidForRange_1&className=PurCategory&action=listTop&isCheckBox=true&childNodeOnly=true&checkStatus=true',
		    onClose: function(){ 
	      		$("#bidForRange").val($("#bidForRange_2").val()+"##||##"+$("#bidForRange_1").val());
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

    //行政区域联动
	var option = {parameter:"objId"};  //默认值:{datatype:"json",textfield:"name",valuefiled:"objId"};
	var provinceDatas=$('#province').FillOptions($('#initPath').val()+'/DistrictController.do?method=findTopDistrict',option);
    $('#province').CascadingSelect($('#city'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});
    $('#city').CascadingSelect($('#town'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});
    
	//保存为临时
	$("#orgInfoSave").click(function(){
		OrganizationForm.saveOrSubmit('save');
	})
	
	//保存为有效
	$("#orgInfoSubmit").click(function(){
		OrganizationForm.saveOrSubmit('submit');
	})

	//选择机构图片
	$("#toCropZoomImgBut").click(function(){
		var url = $('#initPath').val()+"/view/pubservice/application/cropzoomimg/crop_zoom_img.jsp?picWidth=200&picHeight=200&pic_WH_rule_str=org_pic_width_height_rule&propertyName=logo";
		$.epsDialog({
			title: '选择机构图片',
			url: url
		});
	});
})
</script>