<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="ExBaseInfoForm" method="post" enctype="multipart/form-data"> 
	<div class="formLayout  form2Pa">
		<input type="hidden" name="useStatus" id="useStatus" value="00">
		<input type="hidden" name="appType" id="appType" value="${appType}">
		<input type="hidden" id="buyerStr" name="buyerStr" value=""/>
		
		<h4><span>机构基本信息</span></h4>
		<div class="k1">
			<div class="img_250_2" id="newPreview">
				<img id="view"  src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="200px" height="200px"></img>
			</div>
			<input name="pictureFile" type="file" id="pictureFile" size="22"/>
		</div>
		<ul id="OrgInfoUl">
	    	<li class="fullLine">
	            <label for="orgName">企业名称：</label>
	            <input type="text" id="orgName" name="orgName" maxlength="50" class="required" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label for="orgCode">机构代码：</label>
	            <input type="text" id="orgCode" name="orgCode" maxlength="50" class="required" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label for="orgCode">企业法定代表人：</label>
	            <input type="text" id="croporate" name="company.croporate" maxlength="50" class="required" size="60"/>
	            <span class="eleRequired">*</span> 
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
	        <li class="formTextarea">
	           <label for="address">详细通信地址：</label>
	           <textarea name="company.address" id="address" maxlength="100"></textarea>
	        </li>
	        <li class="fullLine">
	           <label for="postCode">邮编：</label>
	           <input type="text" id="postCode" name="company.postCode" maxlength="6" class="cnZipCode" size="60"/>
	        </li>
	    </ul>
	    </div>
	    
<div id="infoDiv">
	<ul>
		<li id="loadBuyerLi"><a href="#buyerInfo" id="loadBuyerBtn">采购人角色信息</a></li>
	</ul>
	
	<div id="buyerInfo"></div>
	
	<div class="conOperation">
			<button type="button" id="orgInfoSave" class="next"><span>保存</span></button>
			<button type="button" id="orgInfoSubmit" class="next"><span>保存为正式</span></button>
			<button type="button" id="returnBtn" class="next"><span>返回</span></button>
	</div>
</div>
</form:form>		

<script>
var OrganizationForm={};
//保存机构信息
OrganizationForm.submit=function(saveType){
    if(window.confirm("确定保存机构信息吗?")){
        $("#orgInfoSubmit").attr("disabled",true);
        $("#orgInfoSave").attr("disabled",true);

		var url = $('#initPath').val()+"/ExOrgInfoController.do?method=saveOrgInfo&createType=buyer";

    	if(saveType == 'save'){
    		$("#useStatus").val('00');
    	}else if(saveType == 'submit'){
    		$("#useStatus").val('01');
    	}

    	var jsonObj = formToJsonObject('ExBaseInfoForm');
    	if($("#BuyerInfoForm").html() != null){
    		jsonObj.buyerStr =  JSON.stringify(formToJsonObject('BuyerInfoForm','json'));
			$("#buyerStr").val(jsonObj.buyerStr);
    	}
    	
		//提交
    	$('#ExBaseInfoForm').ajaxSubmit({
    		url:url,
			dataType:'json',
			success:function(json){
    			alert("创建成功!");
    			$("#returnBtn").click();
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
	var $tabs=$("#infoDiv").tabs();
	$("#buyerInfo").loadPage(path+"/BuyerController.do?method=toModifyBuyer");
	
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
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=bidForRange_2&NAMES=bidForRange_1&className=PurCategory&action=listTop&isCheckBox=true',
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
	
	//返回
	$("#returnBtn").click(function(){
		$("#conBody").loadPage($("#initPath").val()+'/view/bizplatform/organization/manager/organization_manage_list.jsp?appType='+$('#appType').val());
	})
})
</script>