<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="OrgInfoForm" method="post" modelAttribute="orgInfo" enctype="multipart/form-data"> 
        <input type="hidden" id="objId" name="objId" value="<c:out value="${orgInfo.objId}"/>"/>
        <input type="hidden" id="currentId" name="currentId" value="<c:out value="${orgInfo.currentId}"/>"/>
        <input type="hidden" id="useStatus" name="useStatus" value="<c:out value="${orgInfo.useStatus}"/>"/>
        <input type="hidden" id="auditStatus" name="auditStatus" value="<c:out value="${orgInfo.auditStatus}"/>"/>
        <input type="hidden" id="companyId" name="company.objId" value="<c:out value="${orgInfo.company.objId}"/>"/>
        <input type="hidden" id="supplierId" name="supplierId" value="<c:out value="${orgInfo.supplierId}"/>"/>
        <input type="hidden" id="buyerId" name="buyerId" value="<c:out value="${orgInfo.buyerId}"/>"/>
        <input type="hidden" id="agencyId" name="agencyId" value="<c:out value="${orgInfo.agencyId}"/>"/>
        <input type="hidden" id="supervisionId" name="supervisionId" value="<c:out value="${orgInfo.supervisionId}"/>"/>
        <input type="hidden" id="cmptDepId" name="cmptDepId" value="<c:out value="${orgInfo.cmptDepId}"/>"/>
        <input type="hidden" id="townId" name="townId" value="<c:out value="${orgInfo.company.town.objId}"/>"/>
        <input type="hidden" id="cityId" name="cityId" value="<c:out value="${orgInfo.company.town.parent.objId}"/>"/>
        <input type="hidden" id="provinceId" name="provinceId" value="<c:out value="${orgInfo.company.town.parent.parent.objId}"/>"/>
        <input type="hidden" id="supplierStr" name="supplierStr" value=""/>
        <input type="hidden" id="buyerStr" name="buyerStr" value=""/>
        <input type="hidden" id="agencyStr" name="agencyStr" value=""/>
        <input type="hidden" id="districtValue" name="districtValue" value=""/>
        <input type="hidden" name="reducedWidth" id="reducedWidth" value="120" />
		<input type="hidden" name="reducedHeight" id="reducedHeight" value="120" />
		<input type="hidden" id="saveStatus" name="saveStatus" value=""/>
        <input type="hidden" id="role_type" name="role_type" value="${role_type}"/>
        <input type="hidden" id="pic_WH_rule_str" name="pic_WH_rule_str" value="org_pic_width_height_rule"/>
        
	<div class="formLayout  form2Pa">
		<h4><span>机构基本信息</span></h4>
		<div class="k1">
			<div class="img_250_2" id="newPreview">
				<c:choose>
					<c:when test="${orgInfo.logo==null}">
						<img id="view"  src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="200px" height="200px"></img>
					</c:when>
					<c:otherwise>
						<img src="<c:url value="AttachmentController.do?method=showImg&objId=${orgInfo.logo}" />" width="200px" height="200px">
					</c:otherwise>
				</c:choose>
			</div>
			<input name="pictureFile" type="file" id="pictureFile" size="22"/>
		</div>
		<ul id="OrgInfoUl">
	    	<li class="fullLine">
	            <label for="orgName">机构名称：</label>
	            <input type="text" id="orgName" name="orgName" maxlength="50" <c:if test="${orgInfo.auditStatus=='02'||orgInfo.auditStatus=='01'}">disabled="disabled"</c:if> value="${orgInfo.orgName}" class="required" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label for="orgCode">机构代码：</label>
	            <input type="text" id="orgCode" name="orgCode" maxlength="15" <c:if test="${orgInfo.auditStatus=='02'||orgInfo.auditStatus=='01'}">disabled="disabled"</c:if> value="${orgInfo.orgCode}" class="required" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	           <label for="tel">联系手机：</label>
	           <input type="text" id="mobilePhone" name="company.mobilePhone" maxlength="11" value="${orgInfo.company.mobilePhone}" class="required cnMobile" size="60"/>
	           <span class="eleRequired">*</span> 
	        </li>
	    	<li class="fullLine">
	           <label for="tel">联系电话：</label>
	           <input type="text" id="tel" name="company.tel" value="${orgInfo.company.tel}" maxlength="20" class="cnPhone" size="60"/>
	        </li>
	        <li class="fullLine">
	            <label for="town">行政区域名称：</label>
	            <form:select path="" id="province">
	            	<c:if test="${orgInfo.company.town == null}">
	            		<form:option value="">请选择</form:option>
	            	</c:if>
	            	<form:options items="${province}" itemValue="code" itemLabel="name"/> 
	            </form:select>
	            <form:select path="" id="city">
	            	<form:options items="${city}" itemValue="code" itemLabel="name"/> 
	            </form:select>
	            <form:select path="districtId" id="company.town.objId">
	            	<form:options items="${town}" itemValue="code" itemLabel="name"/> 
	            </form:select>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="formTextarea">
	           <label for="orgAddress">详细通信地址：</label>
	           <textarea name="company.address" id="address" maxlength="100">${orgInfo.company.address}</textarea>
	        </li>
	        <li class="fullLine">
	           <label for="postCode">邮编：</label>
	           <input type="text" id="postCode" name="company.postCode" maxlength="6" value="${orgInfo.company.postCode}" class="cnZipCode" size="60"/>
	        </li>
	        
	        <c:if test ="${orgInfo.opinion!=null}">
		        <li class="fullLine">
		        	<label>审核意见：</label>
		        	<span>${orgInfo.opinion }(${orgInfo.auditStatusCN })</span>
		        </li>
	        </c:if>
	    </ul>
	</div>
</form:form>		
<!-- 角色信息开始 -->
<div id="infoDiv">
	<ul>
		<li id="loadSupplierLi" class="hidden"><a href="#supplierInfo" id="loadSupplierBtn">供应商角色信息</a></li>
		<li id="loadBuyerLi" class="hidden"><a href="#buyerInfo" id="loadBuyerBtn">采购人角色信息</a></li>
		<li id="loadAgencyLi" class="hidden"><a href="#agencyInfo" id="loadAgencyBtn">代理机构角色信息</a></li>
	</ul>
	
	<!-- 供应商角色信息 -->
	<div id="supplierInfo"></div>
	<!-- 采购人角色信息 -->
	<div id="buyerInfo"></div>
	<!-- 代理机构角色信息-->
	<div id="agencyInfo"></div>
	
	<div class="formTips attention hidden" id="waitingAudit">
	<ul>
		<li>
			<em>友情提示：</em>您的信息正在审核中...
		</li>
	</ul>
	</div>
	<div class="formTips attention hidden" id="notPass">
	<ul>
		<li>
			<em>友情提示：</em>您的信息审核不通过,请联系管理员或修改信息后重新提交审核!
		</li>
	</ul>
	</div>
	
	<div class="conOperation">
		<button type="button" id="orgInfoSave"><span>保存</span></button>
		
		<!-- 运营管理员 -->
		<c:if test="${role_type=='3'}">
			<button type="button" id="orgInfoSubmit"><span>保存为正式</span></button>
		</c:if>
		<!-- 机构管理员 -->
		<c:if test="${role_type=='4'}">
			<button type="button" class="hidden" id="submitOfOrg"><span>提交</span></button>
		</c:if>
		<button type="button" id="viewHistory"><span>查看历史</span></button>
	</div>
</div>

<script>
var OrganizationModify={};
/*保存机构的修改信息*/
OrganizationModify.submit=function(saveStatus){
	var saveMsg = "确定保存机构信息的修改?";
	if(saveStatus=='01'){
		saveMsg = "将会提交给管理员进行审核(如果您未对信息做修改,请勿提交),确认提交吗？";
	}
	$('#saveStatus').val(saveStatus);
    if(window.confirm(saveMsg)){
    	$("#orgInfoSave").attr("disabled","disabled");
        $("#orgInfoSubmit").attr("disabled","disabled");
        $("#submitOfOrg").attr("disabled","disabled");

		//将orgInfo的信息作为自动绑定的对象传入后台，将扩展机构的信息用字符串方式传入后台
		var url = $('#initPath').val()+"/OrgInfoController.do?method=saveModifyOrgInfo";
    	var jsonObj = formToJsonObject('OrgInfoForm');
		if($("#SupplierInfoForm").html() != null){
			jsonObj.supplierStr =  JSON.stringify(formToJsonObject('SupplierInfoForm','json'));
    		$("#supplierStr").val(jsonObj.supplierStr);
    	}
    	if($("#BuyerInfoForm").html() != null){
    		jsonObj.buyerStr =  JSON.stringify(formToJsonObject('BuyerInfoForm','json'));
			$("#buyerStr").val(jsonObj.buyerStr);
    	}
    	if($("#AgencyInfoForm").html() != null){
    		jsonObj.agencyStr =  JSON.stringify(formToJsonObject('AgencyInfoForm','json'));
    		$("#agencyStr").val(jsonObj.agencyStr);
    	}

		//提交
    	$('#OrgInfoForm').ajaxSubmit({
    		url:url,
			dataType:'json',
			success:function(json){
    			if(json.result=='success') {
    				if(saveStatus=='01'){
	    				alert("已提交，等待审核");
	    			}else {
	    				alert("保存成功!");
	    			}
    				if($('#role_type').val()=='4') {
						$("#conBody").loadPage($("#initPath").val()+'/OrgInfoController.do?method=toModifyOrgInfo&orgId='+json.newOrgInfoId);
	    			}else if($('#role_type').val()=='3') {
	    				$("#conBody").loadPage('view/bizplatform/organization/manager/organization_manage_list.jsp?appType=XYGH');
	    			}
					$("#orgInfoSave").removeAttr("disabled");
					$("#orgInfoSubmit").removeAttr("disabled");
					$("#submitOfOrg").removeAttr("disabled");
    			} else {
    				alert(ascii2native(json.result));
    				$("#orgInfoSave").removeAttr("disabled");
					$("#orgInfoSubmit").removeAttr("disabled");
					$("#submitOfOrg").removeAttr("disabled");
    			}
			},
			error:function(msg){
				alert(JSON.stringify(msg));
				$("#orgInfoSave").removeAttr("disabled");
				$("#orgInfoSubmit").removeAttr("disabled");
				$("#submitOfOrg").removeAttr("disabled");
			}
		});	
	}
}

//保存或提交
OrganizationModify.saveOrSubmit=function(saveStatus) {
	if(!$("#OrgInfoForm").valid()){
		alert("请正确填写表单!");return;
	}
	if($('select[id=company.town.objId]').val()==""){
		alert("请选择行政区域!");
		return;
	}
	if($("#SupplierInfoForm").html() != null) {
		if(!$("#SupplierInfoForm").valid()){
			alert("请正确填写表单!");return;
		}
	}
	if($("#BuyerInfoForm").html() != null) {
		if(!$("#BuyerInfoForm").valid()){
			alert("请正确填写表单!");return;
		}
	}
	if($("#AgencyInfoForm").html() != null) {
		if(!$("#AgencyInfoForm").valid()){
			alert("请正确填写表单!");return;
		}
	}
	OrganizationModify.submit(saveStatus);
}

$(document).ready(function(){
	var path=$("#initPath").val();
	var $tabs=$("#infoDiv").tabs();

	var validId = "";
	if($("#useStatus").val() == "00"){//临时
		validId = $("#currentId").val();
		if(validId=="") {
			validId = $("#objId").val();
		}
	}else if($("#useStatus").val() == "01"){//有效
		validId = $("#objId").val();
	}
	
	var jsonCodeObj= {};
	jsonCodeObj["id"]=validId;
	jsonCodeObj["property"]="orgCode";

	var jsonNameObj= {};
	jsonNameObj["id"]=validId;
	jsonNameObj["property"]="orgName";
	
	//唯一性验证
	$.validator.addMethod("orgCodeUnique",function(value,element,param){return bizUniqueHandler("OrgInfoController.do?method=checkOrgUnique&orgCode="+$("#orgCode").val(),jsonCodeObj);},'该机构代码已存在');
	$.validator.addMethod("orgNameUnique",function(value,element,param){return bizUniqueHandler("OrgInfoController.do?method=checkOrgUnique&orgName="+$("#orgName").val(),jsonNameObj);},'该机构名称已存在');
	
	//机构表单验证
	$("#OrgInfoForm").validate({
		rules:{
			orgCode:{orgCodeUnique:"orgCode"},
			orgName:{orgNameUnique:"orgName"}
		}
	});	

	//变更机构待审核,屏蔽保存和提交按钮
	if($('#auditStatus').val()=='01') {
		$('#orgInfoSave').addClass('hidden');
		$('#submitOfOrg').addClass('hidden');
		//审核中显示
		$('#waitingAudit').removeClass('hidden');
	}else if($('#auditStatus').val()=='02'){
		$('#orgInfoSave').removeClass('hidden');
		$('#pass').removeClass('hidden');
	}else if($('#auditStatus').val()=='00'){
		$('#orgInfoSave').removeClass('hidden');
		$('#submitOfOrg').removeClass('hidden');
	}else if($('#auditStatus').val()=='03'){
		$('#orgInfoSave').removeClass('hidden');
		$('#submitOfOrg').removeClass('hidden');
		$('#notPass').removeClass('hidden');
	}
	
	//如果扩展信息Id不为空，显示tab，并且读取数据
	if($("#agencyId").val() != ""){
		$("#loadAgencyLi").show();
		$("#agencyInfo").loadPage(path+"/AgencyController.do?method=toModifyAgency&agencyId="+$("#agencyId").val());
		$tabs.tabs('select', 2);//切换到当前选项卡
	}
	if($("#buyerId").val() != ""){
		$("#loadBuyerLi").show();
		$("#buyerInfo").loadPage(path+"/BuyerController.do?method=toModifyBuyer&buyerId="+$("#buyerId").val());
		$tabs.tabs('select', 1);//切换到当前选项卡
	}
	if($("#supplierId").val() != ""){
		$("#loadSupplierLi").show();
		$("#supplierInfo").loadPage(path+"/SupplierController.do?method=toModifySupplier&supplierId="+$("#supplierId").val());
		$tabs.tabs('select', 0);//切换到当前选项卡
	}

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
	})
	//行政区域联动
	var option = {parameter:"objId"};  //默认值:{datatype:"json",textfield:"name",valuefiled:"objId"};
    $('#province').CascadingSelect($('#city'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});
    $('#city').CascadingSelect($('select[id=company.town.objId]'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});
    
    $("#province").val($("#provinceId").val());
    $("#city").val($("#cityId").val());
    $("select[id=company.town.objId]").val($("#townId").val());

  	//保存修改
	$("#orgInfoSave").click(function(){
		OrganizationModify.saveOrSubmit('00');
	})
	//机构管理员提交
	$("#submitOfOrg").click(function(){
		OrganizationModify.saveOrSubmit('01');
	})
	//运营管理员保存为正式
	$("#orgInfoSubmit").click(function(){
		OrganizationModify.saveOrSubmit('02');
	})

	//查看历史
	$("#viewHistory").click(function(){
		var url = $('#initPath').val()+"/OrgInfoController.do?method=getOrgHistory&id="+$("#objId").val();
		$.epsDialog({
	        title:'机构历史',
	        url:url
	    }); 
	})
})
</script>