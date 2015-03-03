<%@ page contentType="text/html;charset=UTF-8"%><%@page import="com.gpcsoft.srplatform.common.utils.SysInfo"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/resource/plug-in/jquery/jquery.scrollto.js'></script>

<div class="nextBar">
    <ol class="num4">
        <li class="current"><span class="first">1. 填写基本信息</span></li> 
        <li><span>2. 填写财务信息</span></li>
        <li><span>3. 填写法务信息</span></li>
        <li class="last"><span>4. 填写企业资质</span></li>
    </ol>
</div>
<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>请将企业的信息信息完善后在进行提交操作！提交后将会进入审核环节
		</li>
		<li id="pass" class="hidden">
			<em>友情提示：</em>您的机构信息已经审核通过.如果想变更机构信息(即变更机构名称、机构代码、经营范围),请点击
			“<a target="#conBody" href="OrgInfoModifyController.do?method=toModifyOrg&companyId=${orgInfo.company.objId}" style="color: blue;">机构变更</a>”。
			<br>查看变更历史和变更结果请点击“<a href="javascript:void(0);" id="viewHistoryTips" style="color: blue;">查看历史</a>”
		</li>
		<li id="waitingAudit" class="hidden">
			<em>友情提示：</em>您的信息正在审核中...
		</li>
		<li id="notPass" class="hidden">
			<em>友情提示：</em>您的信息审核不通过,请联系管理员或修改信息后重新提交审核!【审核意见】：${orgInfo.opinion}
		</li>
		<c:set var="roleType" value=""></c:set>
		<c:forEach var="role" items="${roles}" varStatus="status">
			<c:if test="${fn:contains(role.type,'4')==false}"><c:set var="roleType" value="${roleType}${fn:length(roleType)>0?',':''}${role.type}"></c:set></c:if>
		</c:forEach>
		<c:if test="${ ( orgInfo.supplierId!=null||orgInfo.buyerId!=null ) && ( orgInfo.supplierAuditStatus!='02' || orgInfo.buyerAuditStatus !='02' )  }">
		<li>
			<em>机构申请：</em>申请成为
        	<c:if test="${fn:contains(roleType,'b') && orgInfo.supplierAuditStatus != '01' }">【<a style="color: blue" href="OrgInfoController.do?method=toApplyOrgInfo" id="supplierRole" title="供应商" target="#conBody">供应商</a>】</c:if>
	        <c:if test="${fn:contains(roleType,'s') && orgInfo.buyerAuditStatus !='01'}">【<a style="color: blue" href="OrgInfoController.do?method=toApplyOrgInfo" id="buyerRole" title="采购人" target="#conBody">采购人</a>】</c:if>
	        <c:if test="${orgInfo.supplierAuditStatus=='01'}"><font color="red">【供应商】审核中</font></c:if>
	        <c:if test="${orgInfo.buyerAuditStatus=='01'}"><font color="red">【采购人】审核中</font></c:if>
		</li>
		</c:if>
	</ul>
</div>

<form:form id="ExBaseInfoForm" method="post" modelAttribute="orgInfo"> 
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
        <input type="hidden" id="isModify" name="isModify" value=""/>
        <input type="hidden" name="reducedWidth" id="reducedWidth" value="400" />
		<input type="hidden" name="reducedHeight" id="reducedHeight" value="300" />
		<input type="hidden" id="saveType" name="saveType" value=""/>
		<input type="hidden" id="districtValue" name="districtValue" value=""/>
		<input type="hidden" id="role_type" name="role_type" value="${role_type}"/>
		<input type="hidden" id="logo" name="logo" value="${orgInfo.logo}" />
		<!--旧的code和name,经营范围-->
		<input type="hidden" id="oldCode" name="oldCode" value="${orgInfo.orgCode}"/>
		<input type="hidden" id="oldName" name="oldName" value="${orgInfo.orgName}"/>
        
	<div class="formLayout imgAndForm">
		
		<div class="k1" style="width: 210px;">
			<div class="img_250_2" id="newPreview" style="border:1px solid #D5D5D5; padding: 4px;">
				<c:choose>
					<c:when test="${orgInfo.logo==null}">
						<img id="view" src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="200px" height="200px"></img>
					</c:when>
					<c:otherwise>
						<img src="<c:url value="AttachmentController.do?method=showImg&objId=${orgInfo.logo}" />" width="200px" height="200px">
					</c:otherwise>
				</c:choose>
			</div>
			<div style="text-align: center;"><input type="button" id="toCropZoomImgBut" value="选择图片" /></div>
		</div>
		
		<ul id="OrgInfoUl">
	    	<li class="fullLine">
	            <label for="orgName">企业名称：</label>
	            <input type="text" id="orgName" name="orgName" <c:if test="${orgInfo.auditStatus=='02'||orgInfo.auditStatus=='01'}">disabled="disabled"</c:if> maxlength="50" value="${orgInfo.orgName}" class="required" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label for="orgCode">机构代码：</label>
	            <input type="text" id="orgCode" name="orgCode" <c:if test="${orgInfo.auditStatus=='02'||orgInfo.auditStatus=='01'}">disabled="disabled"</c:if> maxlength="15" value="${orgInfo.orgCode}" size="60"/>
	        </li>
	        <c:if test="${showSiteName }">
	        <li class="fullLine">
	            <label for="orgSite">机构子域名：</label>
	            http://<%=SysInfo.getInstance().getSitename()%>/<input type="text" name="orgSite" id="orgSite" value="${orgInfo.company.orgSite}" class="required orgSite" size="30"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        </c:if>
	        <li class="fullLine">
	            <label for="croporate">企业法定代表人：</label>
	            <input type="text" id="croporate" name="company.croporate" maxlength="50" value="${orgInfo.company.croporate}" size="60"/>
	        </li>
	        <li class="fullLine">
	           <label for="mobilePhone">联系手机：</label>
	           <input type="text" id="mobilePhone" name="company.mobilePhone" maxlength="11" value="${orgInfo.company.mobilePhone}" class="required cnMobile" size="60"/>
	           <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	           <label for="tel">办公电话：</label>
	           <input type="text" id="tel" name="company.tel" value="${orgInfo.company.tel}" maxlength="20" class="cnPhone" size="60"/>
	        </li>
	        <li class="fullLine">
	           <label for="fax">办公传真：</label>
	           <input type="text" id="fax" name="company.fax" value="${orgInfo.company.fax}" maxlength="20" class="cnPhone" size="60"/>
	        </li>
	        <li class="fullLine">
	           <label for="email">企业email：</label>
	           <input type="text" id="email" name="company.email" value="${orgInfo.company.email}" maxlength="50" class="email" size="60"/>
	        </li>
	        <li class="fullLine">
	            <label for="town">行政区域名称：</label>
	            <form:select path="" id="province" cssStyle="width:120px;">
	            	<c:if test="${orgInfo.company.town == null}">
	            		<form:option value="">请选择</form:option>
	            	</c:if>
	            	<form:options items="${province}" itemValue="code" itemLabel="name"/> 
	            </form:select>
	            <form:select path="" id="city" cssStyle="width:120px;">
	            	<form:options items="${city}" itemValue="code" itemLabel="name"/> 
	            </form:select>
	            <form:select path="districtId" id="company.town.objId" cssStyle="width:120px;">
	            	<form:options items="${town}" itemValue="code" itemLabel="name"/> 
	            </form:select>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	           <label for="postCode">邮编：</label>
	           <input type="text" id="postCode" name="company.postCode" maxlength="6" value="${orgInfo.company.postCode}" class="cnZipCode" size="60"/>
	        </li>
	    </ul>
	   </div>
	   <div class="formLayout form2Pa">
	    <ul>
	        <li class="formTextarea">
	           <label for="address">详细通信地址：</label>
	           <textarea name="company.address" id="address" maxlength="100">${orgInfo.company.address}</textarea>
	        </li>
          <li class="fullLine">
          	<label for="belongIndustry">所属行业：</label>
          	<input type="text" id="belongIndustry_display" readonly="readonly" value="${orgInfo.belongIndustry.name}" size="60"/>
          	<input type="hidden" name="belongIndustry.objId" id="belongIndustry" value="${orgInfo.belongIndustry.objId}"/>
    	  </li>
    	  <li>
             <label for="entPrpt">企业类型：</label>
             <html:select selectedValue="${orgInfo.entPrpt}" id="entPrpt" name="entPrpt" code="biz.orgInfo.entprpt"></html:select>
          </li>
          <li>
             <label for="unitScape">企业规模：</label>
             <html:select selectedValue="${orgInfo.unitScape}" id="unitScape" name="unitScape" code="biz.orgInfo.unitScape"></html:select>
          </li>
          <li class="fullLine">
             <label for="begainDate">开业日期：</label>
             <input type="text" name="begainDate" id="begainDate" value="${orgInfo.begainDate}" class="required" size="40" readonly="readonly"/>
             <span class="eleRequired">*</span> 
          </li>
          <li class="fullLine">
             <label for="webUrl">企业网址：</label>
             <input type="text" name="webUrl" id="webUrl" class="url" value="${orgInfo.webUrl}" size="60"/> （请以http:// 开头）
          </li>
          
          <li class="formTextarea">
             <label for="bidForRange">经营范围：</label>
             <textarea name="bidForRange_1" id="bidForRange_1" <c:if test="${orgInfo.auditStatus=='02'||orgInfo.auditStatus=='01'}">disabled="disabled"</c:if> maxlength="500" readonly="readonly" class="required"></textarea>
             <input type="hidden" id="bidForRange_2" />
             <input type="hidden" name="bidForRange" id="bidForRange" value="${orgInfo.bidForRange}"/>
             <span class="eleRequired">*</span> 
          </li>
          <li class="formTextarea">
             <label for="mainProducts">主营产品：</label>
             <textarea name="mainProducts" id="mainProducts" maxlength="500">${orgInfo.mainProducts}</textarea>
          </li>
          <li class="formTextarea">
             <label for="entCapacity">企业产能：</label>
             <textarea name="entCapacity" id="entCapacity" maxlength="500">${orgInfo.entCapacity}</textarea>
          </li>
    	  <li class="formTextarea">
             <label for="descCn">企业简介：</label>
         	 <textarea name="descCn" id="descCn" maxlength="500">${orgInfo.descCn}</textarea>
		  </li>
    	</ul>
	</div>
	
	<div class="conOperation">
		<button type="button" class="hidden" id="orgInfoSave"><span>保存</span></button>
		
		<!-- 运营管理员 -->
		<c:if test="${role_type=='3'}">
			<c:if test="${orgInfo.auditStatus!='02'}">
			<button type="button" id="orgInfoSubmit"><span>保存正式</span></button>
			</c:if>
		</c:if>
		<!-- 机构管理员 -->
		<c:if test="${role_type=='4'}">
			<button type="button" class="hidden" id="submitOfOrg"><span>提交</span></button>
		</c:if>
		<button type="button" id="viewHistory"><span>查看历史</span></button>
	</div>
</form:form>		

<script>
var ExBaseInfoForm={};
/*保存机构的修改信息*/
ExBaseInfoForm.submit=function(saveType){
	var saveMsg = "确定保存企业信息的修改?";
	if(saveType=='submitOfOrg'){
		saveMsg = "将会提交给管理员进行审核(如果您未对信息做修改,请勿提交),确认提交吗？";
	}
	$('#saveType').val(saveType);
    if(window.confirm(saveMsg)){
        $("#orgInfoSave").attr("disabled","disabled");
        $("#orgInfoSubmit").attr("disabled","disabled");
        $("#submitOfOrg").attr("disabled","disabled");

        $("#districtValue").val($("#province").val() + "," + $("#city").val() + "," + $("select[id=company.town.objId]").val() + "##||##" + $('#province').getSelectedText() + "," + $('#city').getSelectedText() + "," + $("select[id=company.town.objId]").getSelectedText());

		var url = $('#initPath').val()+"/ExOrgInfoController.do?method=saveEntBaseInfo";
		
    	//提交
    	$('#ExBaseInfoForm').ajaxSubmit({
    		url:url,
			dataType:'json',
			success:function(json){
    				//如果提交，更改myDesktop.jsp里的供应商或采购人状态值
					if(saveType=='submitOfOrg'){
						if($('#supplierId').val() != "") {
							$('#currSuppStatus').val('01');
						}
						if($('#buyerId').val() != "") {
							$('#currBuyStatus').val('01');
						}
					}
	    			if(saveType=='submitOfOrg'){
	    				alert("已提交，等待审核");
	    			}else {
	    				alert("保存成功!");
	    			}
	    			if($('#role_type').val()=='4') {
						$("#conBody").loadPage($("#initPath").val()+'/ExOrgInfoController.do?method=toEntBaseInfo&orgId='+json.newOrgInfoId);
	    			}else if($('#role_type').val()=='3') {
		    			/**协议供货
		    				$("#conBody").loadPage('view/bizplatform/organization/manager/organization_manage_list.jsp?appType=XYGH');
		    			*/
		    			$("#conBody").loadPage('view/bizplatform/organization/manager/organization_manage_list.jsp?appType=XEJY');
	    			}
					$("#orgInfoSave").removeAttr("disabled");
					$("#orgInfoSubmit").removeAttr("disabled");
					$("#submitOfOrg").removeAttr("disabled");
			},
			error:function(msg){
				alert(msg);
				$("#orgInfoSave").removeAttr("disabled");
				$("#orgInfoSubmit").removeAttr("disabled");
				$("#submitOfOrg").removeAttr("disabled");
			}
		});	
	}
}

//保存或提交
ExBaseInfoForm.saveOrSubmit=function(saveType) {
	if(!$("#ExBaseInfoForm").valid()){
		alert("请正确填写表单!");return;
	}
	if($('select[id=company.town.objId]').val()=="" || $('select[id=company.town.objId]').val()==null){
		alert("请选择行政区域!");
		return;
	}
	if($('select[id=city]').val()=="" || $('select[id=city]').val()==null){
		alert("请选择行政区域!");
		return;
	}
	ExBaseInfoForm.submit(saveType);
}

$(document).ready(function(){
	var path=$("#initPath").val();
	$("#begainDate").epsDatepicker();
	
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
	$.validator.addMethod("orgCodeUnique",function(value,element,param){
		if($("#orgCode").val()==null || $("#orgCode").val()==''){ //orgCode为空时不验证
			return true;
		}else{
			return bizUniqueHandler("OrgInfoController.do?method=checkOrgUnique&orgCode="+native2ascii($("#orgCode").val()),jsonCodeObj);
		}
	},'该机构代码已存在');
	$.validator.addMethod("orgNameUnique",function(value,element,param){return bizUniqueHandler("OrgInfoController.do?method=checkOrgUnique&orgName="+native2ascii($("#orgName").val()),jsonNameObj);},'该机构名称已存在');

	//机构子域名验证
	$.validator.addMethod("orgSiteUnique",function(value,element,param){return uniqueHandler("Organization",param,value,$("#companyId").val());},'该机构域名已存在');
	
	//机构表单验证
	$("#ExBaseInfoForm").validate({
		rules:{
			orgCode:{orgCodeUnique:"orgCode"},
			orgName:{orgNameUnique:"orgName"},
			orgSite:{orgSiteUnique:"orgSite"}
		}
	});	

	//回填投标范围及类别
	if($('#bidForRange').val()!=null && $('#bidForRange').val()!= ""){
		var bidForRange=$('#bidForRange').val().split("##||##");
		$('#bidForRange_2').val(bidForRange[0]);
		$('#bidForRange_1').text(bidForRange[1]);
	}

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
		$('#submitOfOrg').removeClass('hidden');
	}else if($('#auditStatus').val()=='03'){
		$('#submitOfOrg').removeClass('hidden');
		$('#notPass').removeClass('hidden');
	}

	//行政区域联动
	var option = {parameter:"objId"};  //默认值:{datatype:"json",textfield:"name",valuefiled:"objId"};
    $('#province').CascadingSelect($('#city'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});
    $('#city').CascadingSelect($('select[id=company.town.objId]'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});
    
    $("#province").val($("#provinceId").val());
    $("#city").val($("#cityId").val());
    $("select[id=company.town.objId]").val($("#townId").val());
    
	//保存修改
	$("#orgInfoSave").click(function(){
		ExBaseInfoForm.saveOrSubmit('save');
	})
	
	//提交
	$("#orgInfoSubmit").click(function(){
		ExBaseInfoForm.saveOrSubmit('submit');
	})
	
	//机构管理员提交
	$("#submitOfOrg").click(function(){
		ExBaseInfoForm.saveOrSubmit('submitOfOrg');
	})
	
	//选择所属行业
 	$("#belongIndustry_display").click(function(e){
	    $.epsDialog({
	        title:'选择所属行业',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=belongIndustry&NAMES=belongIndustry_display&className=Industry&action=listTop'
	    }); 
 	});
	
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
	
	//查看历史
	$("#viewHistory").click(function(){
		var url = $('#initPath').val()+"/OrgInfoController.do?method=getOrgHistory&id="+$("#objId").val();
		$.epsDialog({
	        title:'机构变更历史',
	        url:url
	    }); 
	})

	//查看历史操作提示
	$("#viewHistoryTips").click(function(){
		$("#viewHistory").ScrollTo(800).qtip({
            content: "点此按钮查看历史和变更结果",
            position: {corner: {tooltip: "bottomRight", target: "topLeft"}},
            show: { when: false, ready: true },
            hide: { when: { event: 'click' } },
            style: {
               border: {width: 3,radius: 3 },
               padding: 10, 
               textAlign: 'center',
               tip: true,
               name: 'cream'
            }
         });
	})

	//选择机构图片
	$("#toCropZoomImgBut").click(function(){
		var url = $('#initPath').val()+"/view/pubservice/application/cropzoomimg/crop_zoom_img.jsp?picWidth=200&picHeight=200&pic_WH_rule_str=org_pic_width_height_rule&propertyName=logo&oldAttachmentId="+$("#logo").val();
		$.epsDialog({
			title: '选择机构图片',
			url: url
		});
	});
})
</script>